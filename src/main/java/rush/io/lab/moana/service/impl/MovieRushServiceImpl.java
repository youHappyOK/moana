package rush.io.lab.moana.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import rush.io.lab.moana.dao.MovieInfoDao;
import rush.io.lab.moana.dao.SuccessRushDao;
import rush.io.lab.moana.exception.RushCloseException;
import rush.io.lab.moana.exception.RushException;
import rush.io.lab.moana.exception.RushRepeatException;
import rush.io.lab.moana.exception.RushUriIllegalException;
import rush.io.lab.moana.mail.SendMail;
import rush.io.lab.moana.model.MovieInfo;
import rush.io.lab.moana.model.SuccessRush;
import rush.io.lab.moana.service.MovieRushService;
import rush.io.lab.moana.util.RushStateEnum;
import rush.io.lab.moana.vo.RushMovieCallBack;
import rush.io.lab.moana.vo.TransmitRushURI;

/**
 * MovieRushServiceʵ��
 *
 * @author Jin
 * @since 2016��12��21��
 */

@Service
public class MovieRushServiceImpl implements MovieRushService {

	//�����̬��������uri�Ĳ���
	private static final String RANDOMCODE = "15223090649$luojin_mail@163.com"; 
	
	@Autowired
	private MovieInfoDao movieInfoDao;
	
	@Autowired
	private SuccessRushDao successRushDao;	
	
	@Autowired
	private SendMail sendMail;
	
	@Override
	public List<MovieInfo> getMovieList() {
		return movieInfoDao.selectAll();
	}

	@Override
	public MovieInfo getMovie(long movieId) {
		return movieInfoDao.selectById(movieId);
	}


	@Override
	public TransmitRushURI returnRushUri(long movieId) {
		MovieInfo movieInfo = movieInfoDao.selectById(movieId);
		
		//û�д˵�Ӱid
		if(movieInfo == null) {
			return new TransmitRushURI(false, movieId);
		}
		
		long currentTime = new Date().getTime();
		long startTime = movieInfo.getStartTime().getTime();
		long endTime = movieInfo.getEndTime().getTime();
		
		String randomURI = this.getRandomURI(movieId);
		
		//������δ��ʼ���������Ѿ�����
		if(currentTime < startTime || currentTime > endTime) {
			return new TransmitRushURI(false, randomURI, movieId, currentTime, startTime, endTime);
		}
		
		//�������ڽ����У��򷵻�����uri��ַ�ķ�װ����TransmitRushuri
		return new TransmitRushURI(true, randomURI, movieId);
	}

	 /**
	 * ����movieId��RANDOMCODE��̬�����������uri����ֹ�û�����֮ǰ��ֱ���µ�
	 * @param @param movieId
	 * @param @return
	 * @return String
	 */
	private static String getRandomURI(long movieId) {
		String base = RANDOMCODE + movieId;
		String randomURI = DigestUtils.md5DigestAsHex(base.getBytes());//ͨ��MD5ʵ�ֶ�̬�������uri������
		return randomURI;
	}
	
	@Override
	public RushMovieCallBack rushMovie(long movieId, String userMail, String uri)
			throws RushUriIllegalException, RushException, RushRepeatException, RushCloseException{

		//�û�����Я����URI�Բ��Ϸ�������ɵ�randomURI��URI�Ƿ���˵���û��۸���URI
		if(uri == null || !uri.equals(getRandomURI(movieId))){
			throw new RushUriIllegalException("rush uri illegal");
		}
		
		//���Ȳ���SUCCESS_RUSH������MOVIE_INFO���м���棬��һ������ͨ��spring���������ʵ��

		Date rushTime = new Date();
		int insertCount = successRushDao.insert(movieId, userMail);
		if (insertCount <= 0) {
			// �ظ�����������ʱ�쳣����������ͨ��spring���������rollback
			throw new RushRepeatException("repeat rush");
		} else {
			int updateCount = movieInfoDao.decreaseMovieNumber(movieId,
					rushTime);
			if (updateCount <= 0) {
				throw new RushCloseException("movie has closed");
			} else {
				// �����ɹ�
				SuccessRush successRush = successRushDao
						.selectByIdAndMail(movieId, userMail);
//				MovieInfo movieInfo = movieInfoDao.selectById(movieId);
//				System.out.println(movieInfo);
//				successRush.SetMovieInfo(movieInfo);
				try {
					//�����ʼ�
					sendMail.sendMessage(successRush);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return new RushMovieCallBack(movieId,
						RushStateEnum.SUCCESS_RUSH, successRush);
			}
		}
	}

}

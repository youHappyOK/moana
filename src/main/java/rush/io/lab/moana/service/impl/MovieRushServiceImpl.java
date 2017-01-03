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
 * MovieRushService实现
 *
 * @author Jin
 * @since 2016年12月21日
 */

@Service
public class MovieRushServiceImpl implements MovieRushService {

	//随机动态生成抢购uri的参数
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
		
		//没有此电影id
		if(movieInfo == null) {
			return new TransmitRushURI(false, movieId);
		}
		
		long currentTime = new Date().getTime();
		long startTime = movieInfo.getStartTime().getTime();
		long endTime = movieInfo.getEndTime().getTime();
		
		String randomURI = this.getRandomURI(movieId);
		
		//抢购还未开始或者抢购已经结束
		if(currentTime < startTime || currentTime > endTime) {
			return new TransmitRushURI(false, randomURI, movieId, currentTime, startTime, endTime);
		}
		
		//抢购正在进行中，则返回抢购uri地址的封装对象TransmitRushuri
		return new TransmitRushURI(true, randomURI, movieId);
	}

	 /**
	 * 跟据movieId和RANDOMCODE动态生成随机抢购uri，防止用户抢购之前就直接下单
	 * @param @param movieId
	 * @param @return
	 * @return String
	 */
	private static String getRandomURI(long movieId) {
		String base = RANDOMCODE + movieId;
		String randomURI = DigestUtils.md5DigestAsHex(base.getBytes());//通过MD5实现动态随机抢购uri的生成
		return randomURI;
	}
	
	@Override
	public RushMovieCallBack rushMovie(long movieId, String userMail, String uri)
			throws RushUriIllegalException, RushException, RushRepeatException, RushCloseException{

		//用户访问携带的URI对不上服务端生成的randomURI，URI非法，说明用户篡改了URI
		if(uri == null || !uri.equals(getRandomURI(movieId))){
			throw new RushUriIllegalException("rush uri illegal");
		}
		
		//首先插入SUCCESS_RUSH表，再在MOVIE_INFO表中减库存，是一个事务，通过spring事务管理器实现

		Date rushTime = new Date();
		int insertCount = successRushDao.insert(movieId, userMail);
		if (insertCount <= 0) {
			// 重复抢购。运行时异常，这样才能通过spring事务管理器rollback
			throw new RushRepeatException("repeat rush");
		} else {
			int updateCount = movieInfoDao.decreaseMovieNumber(movieId,
					rushTime);
			if (updateCount <= 0) {
				throw new RushCloseException("movie has closed");
			} else {
				// 抢购成功
				SuccessRush successRush = successRushDao
						.selectByIdAndMail(movieId, userMail);
//				MovieInfo movieInfo = movieInfoDao.selectById(movieId);
//				System.out.println(movieInfo);
//				successRush.SetMovieInfo(movieInfo);
				try {
					//发送邮件
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

package rush.io.lab.moana.service;

import java.util.List;

import rush.io.lab.moana.exception.RushCloseException;
import rush.io.lab.moana.exception.RushException;
import rush.io.lab.moana.exception.RushRepeatException;
import rush.io.lab.moana.model.MovieInfo;
import rush.io.lab.moana.vo.RushMovieCallBack;
import rush.io.lab.moana.vo.TransmitRushURI;

/**
 * ҵ��ӿ� MovieInfoService
 *
 * @author Jin
 * @since 2016��12��21��
 */

public interface MovieRushService {

	/**
	 * ��ѯ���е�ӰƱ��¼
	 * @return List<MovieInfo>
	 */
	List<MovieInfo> getMovieList();
	
	/**
	 * ��ѯ������ӰƱ��¼
	 * @param movieId
	 * @return MovieInfo
	 */
	MovieInfo getMovie(long movieId);
	
	/**
	 * ����һ�����������ط���˶�̬���ɵ�����URL������װ������TransmitRushURL����û�����򷵻ؿ�URL
	 * @param movieId
	 */
	TransmitRushURI returnRushUri(long movieId);
	
	/**
	 * ִ����������
	 * @param movieId
	 * @param userMail
	 * @param url ���ڱȽ��Ƿ������˶�̬����������randomURL��ȣ���ֹ�û�������ʼ֮ǰ��ֱ���µ�
	 */
	RushMovieCallBack rushMovie(long movieId, String userMail, String uri) throws RushException, RushRepeatException, RushCloseException;
}


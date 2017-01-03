package rush.io.lab.moana.service;

import java.util.List;

import rush.io.lab.moana.exception.RushCloseException;
import rush.io.lab.moana.exception.RushException;
import rush.io.lab.moana.exception.RushRepeatException;
import rush.io.lab.moana.model.MovieInfo;
import rush.io.lab.moana.vo.RushMovieCallBack;
import rush.io.lab.moana.vo.TransmitRushURI;

/**
 * 业务接口 MovieInfoService
 *
 * @author Jin
 * @since 2016年12月21日
 */

public interface MovieRushService {

	/**
	 * 查询所有电影票记录
	 * @return List<MovieInfo>
	 */
	List<MovieInfo> getMovieList();
	
	/**
	 * 查询单个电影票记录
	 * @param movieId
	 * @return MovieInfo
	 */
	MovieInfo getMovie(long movieId);
	
	/**
	 * 抢购一旦开启，返回服务端动态生成的抢购URL，并封装到对象TransmitRushURL；如没开启则返回空URL
	 * @param movieId
	 */
	TransmitRushURI returnRushUri(long movieId);
	
	/**
	 * 执行抢购操作
	 * @param movieId
	 * @param userMail
	 * @param url 用于比较是否与服务端动态生成抢购的randomURL相等，防止用户抢购开始之前就直接下单
	 */
	RushMovieCallBack rushMovie(long movieId, String userMail, String uri) throws RushException, RushRepeatException, RushCloseException;
}


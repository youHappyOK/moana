package rush.io.lab.moana.dao;

import java.util.Date;
import java.util.List;

import rush.io.lab.moana.model.MovieInfo;

/**
 * movieInfo DAO interface
 *
 * @author Jin
 * @since 2016年12月17日
 */
public interface MovieInfoDao {
	
	/**
	 * 电影票库存减一
	 * @param movieId
	 * @param rushTime 抢购时间
	 * @return 返回update成功的行数
	 */
	int decreaseMovieNumber(long movieId, Date rushTime);
	
	MovieInfo selectById(long movieId);
	
	/**
	 * 查询所有电影票信息
	 * @param 
	 * @return List<MovieInfo>
	 */
	List<MovieInfo> selectAll();
}

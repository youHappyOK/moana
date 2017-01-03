package rush.io.lab.moana.dao;

import rush.io.lab.moana.model.SuccessRush;

/**
 * successRush Dao interface
 *
 * @author Jin
 * @since 2016年12月17日
 */
public interface SuccessRushDao {
	
	/**
	 * 插入抢购明细信息
	 * @param movieId
	 * @param userMail
	 * @return 返回insert成功的行数,返回1代表插入成功；返回0表示联合主键冲突，插入不成功
	 */
	int insert(long movieId, String userMail);
	
	/**
	 * 通过movieid和mail查询抢购明细表 返回查询结果withMovieInfo
	 * @param movieId
	 * @param userMail
	 * @return
	 */
	SuccessRush selectByIdAndMail(long movieId, String userMail);
}

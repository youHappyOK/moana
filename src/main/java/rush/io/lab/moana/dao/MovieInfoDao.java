package rush.io.lab.moana.dao;

import java.util.Date;
import java.util.List;

import rush.io.lab.moana.model.MovieInfo;

/**
 * movieInfo DAO interface
 *
 * @author Jin
 * @since 2016��12��17��
 */
public interface MovieInfoDao {
	
	/**
	 * ��ӰƱ����һ
	 * @param movieId
	 * @param rushTime ����ʱ��
	 * @return ����update�ɹ�������
	 */
	int decreaseMovieNumber(long movieId, Date rushTime);
	
	MovieInfo selectById(long movieId);
	
	/**
	 * ��ѯ���е�ӰƱ��Ϣ
	 * @param 
	 * @return List<MovieInfo>
	 */
	List<MovieInfo> selectAll();
}

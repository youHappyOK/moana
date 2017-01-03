package rush.io.lab.moana.dao;

import rush.io.lab.moana.model.SuccessRush;

/**
 * successRush Dao interface
 *
 * @author Jin
 * @since 2016��12��17��
 */
public interface SuccessRushDao {
	
	/**
	 * ����������ϸ��Ϣ
	 * @param movieId
	 * @param userMail
	 * @return ����insert�ɹ�������,����1�������ɹ�������0��ʾ����������ͻ�����벻�ɹ�
	 */
	int insert(long movieId, String userMail);
	
	/**
	 * ͨ��movieid��mail��ѯ������ϸ�� ���ز�ѯ���withMovieInfo
	 * @param movieId
	 * @param userMail
	 * @return
	 */
	SuccessRush selectByIdAndMail(long movieId, String userMail);
}

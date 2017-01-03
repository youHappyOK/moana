package rush.io.lab.moana.dao.impl;

import javax.annotation.Resource;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.springframework.stereotype.Repository;

import rush.io.lab.moana.dao.SuccessRushDao;
import rush.io.lab.moana.model.MovieInfo;
import rush.io.lab.moana.model.SuccessRush;


/**
 * SuccessRushDao实现
 * 
 * @author LuoJin
 * @since 2016年12月20日
 */

@Repository
public class SuccessRushDaoImpl implements SuccessRushDao {
	
	private static final String PACKAGESTRING = "javax.jdo.query.SQL";
	
	@Resource(name="pmf")
	private PersistenceManagerFactory pmf;
	
	@Override
	public int insert(long movieId, String userMail) {
		
//		PersistenceManager pm = JdoUtil.getPm();
//		Transaction tx = JdoUtil.getTx();
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		int ret;
		
		try
		{
		    tx.begin();
		    Query q = pm.newQuery(PACKAGESTRING, "insert ignore into SUCCESS_RUSH(MOVIE_ID,USER_MAIL,STATE) values (" +
		    		movieId + ",'" + userMail + "',0)");
		    ret = ((Long)q.execute()).intValue();
		    //System.out.println(ret);
		    tx.commit();
		}
		finally
		{
		    if (tx.isActive())
		    {
		        tx.rollback();
		    }
		    //pm.close();
		}
		return ret;
	}

	@Override
	public SuccessRush selectByIdAndMail(long movieId, String userMail) {
		
//		PersistenceManager pm = JdoUtil.getPm();
//		Transaction tx = JdoUtil.getTx();
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		String sql1 = "select * from SUCCESS_RUSH where MOVIE_ID = " + movieId + " and USER_MAIL = '" + userMail + "'";
		
		SuccessRush sr = new SuccessRush(movieId, userMail);
		try
		{
		    tx.begin();
		    Query q1 = pm.newQuery(PACKAGESTRING, sql1);
		    q1.setClass(SuccessRush.class);
		    q1.setUnique(true);
		    sr = (SuccessRush)q1.execute();

		    tx.commit();
		}
		finally
		{
		    if (tx.isActive())
		    {
		        tx.rollback();
		    }
		    //pm.close();
		}
		return sr;
	}
	

}

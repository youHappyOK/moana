package rush.io.lab.moana.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rush.io.lab.moana.dao.MovieInfoDao;
import rush.io.lab.moana.model.MovieInfo;


/**
 * MovieInfoDao实现
 * 
 * @author LuoJin
 * @since 2016年12月20日
 */
@Repository
public class MovieInfoDaoImpl implements MovieInfoDao{
	
	private static final String PACKAGESTRING = "javax.jdo.query.SQL";
	
	//通过spring注入取到，而不是通过jdohelper
	@Resource(name="pmf")
	private PersistenceManagerFactory pmf;
	
	@Override
	public int decreaseMovieNumber(long movieId, Date rushTime) {

		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		int ret;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			tx.begin();
			String rushTimeString = sf.format(rushTime);
			Query q = pm.newQuery(PACKAGESTRING, "UPDATE MOVIE_INFO SET NUMBER = NUMBER-1 "
					+ "WHERE MOVIE_ID = " + movieId + " AND START_TIME <= '" + rushTimeString 
		    		+ "' AND END_TIME >= '" + rushTimeString + "' AND NUMBER > 0");
			ret = ((Long)q.execute()).intValue();
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
	public MovieInfo selectById(long movieId) {
	
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
		    tx.begin();
		    Query query = pm.newQuery(PACKAGESTRING, "SELECT * FROM MOVIE_INFO WHERE MOVIE_ID = " + movieId);
		    query.setClass(MovieInfo.class);
		    query.setUnique(true);
		    MovieInfo mi = (MovieInfo)query.execute(); 
		    tx.commit();
			return mi;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			//pm.close();
		}
	}

	public List<MovieInfo> selectAll() {
		
//		PersistenceManager pm = JdoUtil.getPm();
//		Transaction tx = JdoUtil.getTx();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try {
		    tx.begin();
		    Query query = pm.newQuery(PACKAGESTRING, "SELECT * FROM MOVIE_INFO ORDER BY CREATE_TIME DESC");
		    query.setClass(MovieInfo.class);
		    List mis = (List)query.execute();
		    tx.commit();
		    return mis;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			//pm.close();
		}
	}
	
}

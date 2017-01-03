package rush.io.lab.moana.dao;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rush.io.lab.moana.dao.MovieInfoDao;
import rush.io.lab.moana.model.MovieInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"classpath:spring/spring-main.xml",
		"classpath:spring/spring-dao.xml"})
public class MovieInfoDaoTest {

	@Resource
	private MovieInfoDao mid;

	@Test
	public void testDecreaseMovieNumber() {
		Date rushTime = new Date();
		int ret = mid.decreaseMovieNumber(1000L, rushTime);
		System.out.println(ret);
	}

	@Test
	public void testSelectById() {
		MovieInfo mi = mid.selectById(1001);
		System.out.println(mi);
	}

	@Test
	public void testSelectAll() {
		List<MovieInfo> mis = mid.selectAll();
		for (MovieInfo m : mis) {
            System.out.println(m); 
        }
	}

}

package rush.io.lab.moana.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.qos.logback.classic.Logger;
import rush.io.lab.moana.exception.RushCloseException;
import rush.io.lab.moana.exception.RushRepeatException;
import rush.io.lab.moana.exception.RushUriIllegalException;
import rush.io.lab.moana.model.MovieInfo;
import rush.io.lab.moana.vo.RushMovieCallBack;
import rush.io.lab.moana.vo.TransmitRushURI;
/**
 * ≤‚ ‘service
 *
 * @author Jin
 * @since 2016ƒÍ12‘¬22»’
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"classpath:spring/spring-main.xml",
		"classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml"})
public class MovieRushServiceTest {

	@Autowired
	private MovieRushService movieRushService;
	
	@Test
	public void testGetMovieList() {
		List<MovieInfo> list = movieRushService.getMovieList();
		for (MovieInfo m : list) {
            System.out.println(m); 
        }
	}

	@Test
	public void testGetMovie() {
		System.out.println(movieRushService.getMovie(1000));
	}

	@Test
	public void testReturnRushUrl() {
		long movieId = 1002;
		TransmitRushURI t =  movieRushService.returnRushUri(movieId);
		System.out.println(t);//url=aacbf8106ef2f0415d67719bdd8bdf0d
	}

	@Test
	public void testRushMovie() {
		String userMail = "596371011@qq.com";
		long movieId = 1001;
		TransmitRushURI t =  movieRushService.returnRushUri(movieId);
		RushMovieCallBack rushMovieCallBack;
		try{
			rushMovieCallBack = movieRushService.rushMovie(movieId, userMail, t.getURI());
			System.out.println(rushMovieCallBack);
		} catch(RushRepeatException e) {
			System.out.println(e.getMessage());
		} catch(RushUriIllegalException e) {
			System.out.println(e.getMessage());
		} catch(RushCloseException e) {
			System.out.println(e.getMessage());
		} 
	}

}

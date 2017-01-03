package rush.io.lab.moana.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rush.io.lab.moana.dao.SuccessRushDao;
import rush.io.lab.moana.model.SuccessRush;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"classpath:spring/spring-main.xml",
		"classpath:spring/spring-dao.xml"})

public class SuccessRushDaoTest {

	@Resource
	private SuccessRushDao srd;
	
	@Test
	public void testInsert() {
		int count = srd.insert(10012L, "l_mail@163.com");
		System.out.println(count);
	}

	@Test
	public void testSelectByIdAndMailWithMovieInfo() {
		SuccessRush sr = srd.selectByIdAndMail(10012L, "l_mail@163.com");
		System.out.println(sr);
		System.out.println(sr.getMovieInfo());
	}

}

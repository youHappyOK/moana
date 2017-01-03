package rush.io.lab.moana.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import rush.io.lab.moana.exception.RushCloseException;
import rush.io.lab.moana.exception.RushRepeatException;
import rush.io.lab.moana.exception.RushUriIllegalException;
import rush.io.lab.moana.model.MovieInfo;
import rush.io.lab.moana.model.User;
import rush.io.lab.moana.service.MovieRushService;
import rush.io.lab.moana.util.RushStateEnum;
import rush.io.lab.moana.vo.*;


/**
 * Action层实现
 *
 * @author Jin
 * @since 2016年12月31日
 */

@Controller
@RequestMapping("/tickets")
public class RushAction {
	
	@Autowired
	private MovieRushService movieRushService;
	

	/**
	 * 转向用户登录界面
	 * @param model
	 * @return
	 * @throws Exception String
	 */
	@RequestMapping("/login")
	public String login(Model model) throws Exception {
		// 数据获取传到页面

		return "login";
	}
	
	/**
	 * 登录action
	 * @param model
	 * @param userName
	 * @param password
	 * @return String
	 */
	@RequestMapping(value = "/loginsubmit")
	public String login(Model model,String username, String password, HttpSession session, HttpServletResponse arg1) {
		User user = new User(username, password);
		session.setAttribute("user", user);
		Cookie cookie = new Cookie("usermail", username + "@qq.com");
		cookie.setMaxAge(7*24*60*60);
		arg1.addCookie(cookie);
		return "forward:/tickets/movielist";
	}
	
	/**
	 * 查询列表action
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/movielist")
	public String getList(Model model) {
		List<MovieInfo> movieList = movieRushService.getMovieList();
		model.addAttribute("movielist", movieList);
		return "movielist";
	}
 
	
	/**
	 * 查询详细action
	 * @param model
	 * @param movieId
	 * @return String
	 */
	@RequestMapping(value = "/movie/{movieId}")
	public String getMovie(Model model, @PathVariable("movieId") Long movieId) {
		if (movieId == null) {
			return "redirect:/tickets/movielist";
		}
		MovieInfo movieInfo = movieRushService.getMovie(movieId);
		if (movieInfo == null) {
			return "forward:/tickets/movielist";
		}
		model.addAttribute("movieInfo", movieInfo);
		return "detail";
	}
	
	/**
	 * 返回抢购uri action
	 * @param movieId
	 * @param model
	 * @return JsonResultInfo<TransmitRushURI>
	 */
	@RequestMapping(value = "/returnuri/{movieId}")
	public @ResponseBody JsonResultInfo<TransmitRushURI> returnURI(@PathVariable("movieId") Long movieId, Model model) {
		JsonResultInfo<TransmitRushURI> result ;
		try{
			TransmitRushURI transmitRushURI = movieRushService.returnRushUri(movieId);
			result = new JsonResultInfo<TransmitRushURI>(true, transmitRushURI);
		}catch(Exception e){
			result = new JsonResultInfo<TransmitRushURI>(false, e.getLocalizedMessage());
		}
		return result;
	}
	
	/**
	 * 返回系统当前时间
	 * @return JsonResultInfo<Long>
	 */
	@RequestMapping(value = "/currenttime")
	public @ResponseBody JsonResultInfo<Long> time(){
		Date now = new Date();
		return new JsonResultInfo<Long>(true, now.getTime());
	}
	
	/**
	 * 抢购action
	 * @param movieId
	 * @param uri
	 * @param userMail
	 * @return JsonResultInfo<RushMovieCallBack>
	 */
	@RequestMapping(value = "/rush/{movieId}/{uri}")
	public @ResponseBody JsonResultInfo<RushMovieCallBack> rush(@PathVariable("movieId") Long movieId, 
			@PathVariable("uri") String uri, 
			@CookieValue(value="usermail", required=false) String userMail ){
		
		if(userMail==null)
			return new JsonResultInfo<RushMovieCallBack>(true, "未登录");
		
		try{
			RushMovieCallBack rushMovieCallBack = movieRushService.rushMovie(movieId, userMail, uri);
			return new JsonResultInfo<RushMovieCallBack>(true, rushMovieCallBack);
		}catch(RushRepeatException e){
			RushMovieCallBack ex = new RushMovieCallBack(movieId, RushStateEnum.REPEAT_RUSH);
			return new JsonResultInfo<RushMovieCallBack>(true, ex);
		}catch(RushCloseException e){
			RushMovieCallBack ex = new RushMovieCallBack(movieId, RushStateEnum.END_RUSH);
			return new JsonResultInfo<RushMovieCallBack>(true, ex);
		}catch(RushUriIllegalException e) {
			RushMovieCallBack ex = new RushMovieCallBack(movieId, RushStateEnum.URL_ILLEGAL);
			return new JsonResultInfo<RushMovieCallBack>(true, ex);
		}catch(Exception e){
			RushMovieCallBack ex = new RushMovieCallBack(movieId, RushStateEnum.UNKNOWN_EXCEPTION);
			return new JsonResultInfo<RushMovieCallBack>(true, ex);
		}
	}
}

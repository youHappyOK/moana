package rush.io.lab.moana.vo;


import rush.io.lab.moana.model.SuccessRush;
import rush.io.lab.moana.util.RushStateEnum;


/**
 * 执行抢购操作后返回对象
 * @author Jin
 * @since 2016年12月21日
 */
public class RushMovieCallBack {

	private long movieId;
	private int state;
	private String stateInfo;
	private SuccessRush sr;
	
	public RushMovieCallBack(long movieId, RushStateEnum rse) {
		this.movieId = movieId;
		this.state = rse.getState();
		this.stateInfo = rse.getStateInfo();
	}


	public RushMovieCallBack(long movieId, RushStateEnum rse,  SuccessRush sr) {
		this.movieId = movieId;
		this.state = rse.getState();
		this.stateInfo = rse.getStateInfo();
		this.sr = sr;
	}


	public long getMovieId() {
		return movieId;
	}


	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public String getStateInfo() {
		return stateInfo;
	}


	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}


	public SuccessRush getSr() {
		return sr;
	}


	public void setSr(SuccessRush sr) {
		this.sr = sr;
	}


	@Override
	public String toString() {
		return "RushMovieCallBack [movieId=" + movieId + ", state=" + state
				+ ", stateInfo=" + stateInfo + ", sr=" + sr + "]";
	}
	
	
}

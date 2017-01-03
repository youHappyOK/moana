package rush.io.lab.moana.model;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Transactional;

/**
 * SUCCESS_RUSH��ʵ��ӳ��
 * 
 * @author LuoJin
 * @since 2016��12��19��
 */
@PersistenceCapable(table="SUCCESS_RUSH")
public class SuccessRush {
	
	public SuccessRush(long movieId, String userMail){
		this.movieId = movieId;
		this.userMail = userMail;
	}
	
	@Persistent(primaryKey="true")
	@Column(name="MOVIE_ID")
	private long movieId;
	
	@Persistent(primaryKey="true")
	@Column(name="USER_MAIL")
	private String userMail;
	
	@Persistent
	@Column(name="STATE")
	private short state;
	
	@Persistent
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	@NotPersistent
	private MovieInfo movieInfo; //����1 N ������ѯ
	
	public MovieInfo getMovieInfo() {
		return movieInfo;
	}

	public void SetMovieInfo(MovieInfo movieInfo) {
		this.movieInfo = movieInfo;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "SuccessRush [movieId=" + movieId + ", userMail=" + userMail
				+ ", state=" + state + ", createTime=" + createTime + "movieInfo=" + movieInfo +"]";
	}
	
	
	
}

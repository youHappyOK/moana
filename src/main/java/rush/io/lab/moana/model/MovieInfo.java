package rush.io.lab.moana.model;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * movie_info表的实体映射对象
 *
 * @author Jin
 * @since 2016年12月17日
 */
@PersistenceCapable(table="MOVIE_INFO")
public class MovieInfo {
	
	@Persistent(primaryKey="true", valueStrategy=IdGeneratorStrategy.INCREMENT)
	@Column(name="MOVIE_ID")
	private long movieId;
	
	@Persistent
	@Column(name="NAME")
	private String name;
	
	@Persistent
	@Column(name="NUMBER")
	private int number;
	
	@Persistent
	@Column(name="START_TIME")
	private Date startTime;
	
	@Persistent
	@Column(name="END_TIME")
	private Date endTime;
	
	@Persistent
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	public long getMovieId() {
		return movieId;
	}
	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "MovieInfo [movieId=" + movieId + ", name=" + name + ", number="
				+ number + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", createTime=" + createTime + "]";
	}

	
	
	
}

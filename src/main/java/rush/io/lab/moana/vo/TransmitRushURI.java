package rush.io.lab.moana.vo;

public class TransmitRushURI {

	private boolean rush; //是否开启抢购
	private String uri; //对抢购movieId加密生成URI
	private long movieId;
	private long now; //系统当前时间
	private long start; //抢购开始时间
	private long end; //抢购结束时间
	
	public TransmitRushURI(boolean isRush, long movieId, long now, long start,
			long end) {
		super();
		this.rush = isRush;
		this.movieId = movieId;
		this.now = now;
		this.start = start;
		this.end = end;
	}
	
	public TransmitRushURI(boolean isRush, String uri, long movieId, long now, long start,
			long end) {
		super();
		this.rush = isRush;
		this.uri = uri;
		this.movieId = movieId;
		this.now = now;
		this.start = start;
		this.end = end;
	}

	public TransmitRushURI(boolean isRush, long movieId) {
		super();
		this.rush = isRush;
		this.movieId = movieId;
	}

	public TransmitRushURI(boolean isRush, String URI, long movieId) {
		super();
		this.rush = isRush;
		this.uri = URI;
		this.movieId = movieId;
	}

	public boolean isRush() {
		return rush;
	}

	public void setExpose(boolean isRush) {
		this.rush = isRush;
	}

	public String getURI() {
		return uri;
	}

	public void setURI(String URI) {
		this.uri = URI;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "TransmitRushURI [isRush=" + rush + ", URI=" + uri
				+ ", movieId=" + movieId + ", now=" + now + ", start=" + start
				+ ", end=" + end + "]";
	}
	
	
}

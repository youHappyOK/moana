package rush.io.lab.moana.vo;

public class TransmitRushURI {

	private boolean rush; //�Ƿ�������
	private String uri; //������movieId��������URI
	private long movieId;
	private long now; //ϵͳ��ǰʱ��
	private long start; //������ʼʱ��
	private long end; //��������ʱ��
	
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

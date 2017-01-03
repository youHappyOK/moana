package rush.io.lab.moana.exception;

/**
 * 抢购URL非法异常
 *
 * @author Jin
 * @since 2016年12月22日
 */
public class RushUriIllegalException extends RushException {
	private static final long serialVersionUID = 113L;

	public RushUriIllegalException(String message) {
		super(message);
	}
	
	public RushUriIllegalException(String message, Throwable cause) {
		super(message, cause);
	}
}

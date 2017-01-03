package rush.io.lab.moana.exception;

/**
 * 重复抢购异常
 *
 * @author Jin
 * @since 2016年12月21日
 */
public class RushRepeatException extends RushException{
	private static final long serialVersionUID = 111L;

	public RushRepeatException(String message) {
		super(message);
	}

	public RushRepeatException(String message, Throwable cause) {
		super(message,cause);
	}
}

package rush.io.lab.moana.exception;

/**
 * �ظ������쳣
 *
 * @author Jin
 * @since 2016��12��21��
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

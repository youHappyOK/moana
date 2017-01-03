package rush.io.lab.moana.exception;

/**
 * 所有抢购异常的父类异常
 *
 * @author Jin
 * @since 2016年12月21日
 */
public class RushException extends RuntimeException{
	private static final long serialVersionUID = 110L;

	public RushException(String message) {
		super(message);
	}
	
	public RushException(String message, Throwable cause) {
		super(message,cause);
	}
}

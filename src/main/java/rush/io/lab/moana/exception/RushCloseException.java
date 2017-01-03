package rush.io.lab.moana.exception;

/**
 * 抢购结束关闭异常
 *
 * @author Jin
 * @since 2016年12月21日
 */
public class RushCloseException extends RushException{
	private static final long serialVersionUID = 112L;

	public RushCloseException(String message) {
		super(message);
	}
	
	public RushCloseException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

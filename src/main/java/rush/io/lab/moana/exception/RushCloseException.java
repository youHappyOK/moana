package rush.io.lab.moana.exception;

/**
 * ���������ر��쳣
 *
 * @author Jin
 * @since 2016��12��21��
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

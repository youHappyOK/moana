package rush.io.lab.moana.exception;

/**
 * ���������쳣�ĸ����쳣
 *
 * @author Jin
 * @since 2016��12��21��
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

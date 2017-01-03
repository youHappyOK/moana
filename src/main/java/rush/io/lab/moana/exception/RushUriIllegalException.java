package rush.io.lab.moana.exception;

/**
 * ����URL�Ƿ��쳣
 *
 * @author Jin
 * @since 2016��12��22��
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

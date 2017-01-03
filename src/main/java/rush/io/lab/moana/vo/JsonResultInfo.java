package rush.io.lab.moana.vo;

/**
 * ǰ������ݽ���ͳһ��װ��
 *
 * @author Jin
 * @since 2016��12��31��
 */
public class JsonResultInfo<T> {
	
	private T resultInfo;
	private boolean flag;
	private String message;
	
	public JsonResultInfo(boolean flag, String message) {
		this.flag = flag;
		this.message = message;
	}
	public JsonResultInfo(boolean flag, T resultInfo) {
		this.flag = flag;
		this.resultInfo = resultInfo;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public T getResultInfo() {
		return resultInfo;
	}
	public void getResultInfo(T resultInfo) {
		this.resultInfo = resultInfo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}

package rush.io.lab.moana.util;

/**
 * ����״̬ö��
 *
 * @author Jin
 * @since 2016��12��21��
 */
public enum RushStateEnum {
	
	SUCCESS_RUSH(1,"�����ɹ�"),
	END_RUSH(0,"��������"),
	REPEAT_RUSH(-1, "�ظ�����"),
	UNKNOWN_EXCEPTION(-2,"δ֪�쳣"),
	URL_ILLEGAL(-3,"url�Ƿ�");
	
	private int state;
	private String stateInfo;
	
	private RushStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public static RushStateEnum stateOf(int index){
		for(RushStateEnum state:values()){
			if(state.getState() == index){
				return state;
			}
		}
		return null;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
}

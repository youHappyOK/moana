package rush.io.lab.moana.util;

/**
 * 抢购状态枚举
 *
 * @author Jin
 * @since 2016年12月21日
 */
public enum RushStateEnum {
	
	SUCCESS_RUSH(1,"抢购成功"),
	END_RUSH(0,"抢购结束"),
	REPEAT_RUSH(-1, "重复抢购"),
	UNKNOWN_EXCEPTION(-2,"未知异常"),
	URL_ILLEGAL(-3,"url非法");
	
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

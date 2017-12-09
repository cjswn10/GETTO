package exerList;

public class ExerListDTO {
	String userId;
	String exeDate;
	String exeTime;
	int useKcal;
	String exeName;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getExeDate() {
		return exeDate;
	}
	public void setExeDate(String exeDate) {
		this.exeDate = exeDate;
	}
	public String getExeTime() {
		return exeTime;
	}
	public void setExeTime(String exeTime) {
		this.exeTime = exeTime;
	}
	public int getUseKcal() {
		return useKcal;
	}
	public void setUseKcal(int userKcal) {
		this.useKcal = userKcal;
	}
	public String getExeName() {
		return exeName;
	}
	public void setExeName(String exeName) {
		this.exeName = exeName;
	}
}

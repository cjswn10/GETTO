package user;
/**사용자 관리를 위해 필요한 도메인클래스. USERINFO 테이블에 해당하는 getter/setter를 가짐*/
public class UserDTO {
	private String userid;
	private String password;
	private String name;
	private String address1;
	private String address2;
	private String phone;
	private String gender;
	private String birthday;
	private int height;
	private int weight;
	private int bmi;
	private int disid;
	
	public int getDisid() {
		return disid;
	}
	public void setDisid(int disid) {
		this.disid = disid;
	}
	public String getUserId() {
		return userid;
	}	
	public void setUserId(String userId) {
		this.userid = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getBmi() {
		return bmi;
	}
	public void setBmi(int bmi) {
		this.bmi = bmi;
	}
	
	public boolean isMatchPassword(String pw){
		if(pw != null && pw.equals(getPassword())) return true;
		else return false;
	}
	
	
}

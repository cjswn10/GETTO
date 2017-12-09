package dietList;

public class dietListDTO {
	String userid;
	String foodname;
	int kcal;
	String eatdate;
	String eattime;
	
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getEatdate() {
		return eatdate;
	}
	public void setEatdate(String eatdate) {
		this.eatdate = eatdate;
	}
	public String getEattime() {
		return eattime;
	}
	public void setEattime(String eattime) {
		this.eattime = eattime;
	}
	public int getKcal() {
		return kcal;
	}
	public void setKcal(int kcal) {
		this.kcal = kcal;
	}
	public String getFoodname() {
		return foodname;
	}
	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}
	
}

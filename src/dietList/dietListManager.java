package dietList;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import badfood.BadDTO;
import badfood.BadManager;
import food.FoodDTO;
import food.FoodManager;
import user.UserDTO;
import user.UserManager;
import user.UserNotFoundException;


public class dietListManager {
	private static dietListManager dietListMan = new dietListManager();
	private dietListDAO dietListDAO;
	private dietListManager() {
		try {
			dietListDAO = new dietListDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	public static dietListManager getInstance() {
		return dietListMan;
	}
	
	public boolean FoodWarning(UserDTO dto, String foodname) throws SQLException{
		BadManager bm = BadManager.getInstance();
		boolean check = true;
		List<BadDTO> badFoodList = bm.findBadFood(dto.getDisid());
		
		FoodManager fm = FoodManager.getInstance();
		for(BadDTO badDto : badFoodList)
			if(fm.findfoodName(badDto.getFoodid()).equals(foodname))
					check = false;//질병에 나쁜 음식을 먹었을 시 false 리턴
			
		return check;
		
	}
	
	public int insert(String userid, dietListDTO dietList) throws SQLException, UserNotFoundException {
		UserManager um = UserManager.getInstance();
		UserDTO dto = um.findUser(userid);
		
		dietListDAO.insert(dietList);
		
		if(FoodWarning(dto, dietList.getFoodname())){
			return 1;			
		}
		else{//질병에 나쁜 음식 섭취시 !
			return 0;
		}
	}
	
	public List<dietListDTO> finddietList()
			throws SQLException {
			return dietListDAO.finddietList();
		}
	
	public ArrayList<dietListDTO> dietInfoCheck(String userid) throws SQLException {
		ArrayList<dietListDTO> dto = dietListDAO.findUserdietList(userid);
		ArrayList<dietListDTO> resultList = new ArrayList<dietListDTO>();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String strDate = df.format(calendar.getTime());
		for(dietListDTO dietDto : dto){
			if(dietDto.getEatdate().equals(strDate))
				resultList.add(dietDto);
		}

		return resultList;
	}
	public ArrayList<dietListDTO> dietInfoCheckDate(String userid, String date) throws SQLException {
		ArrayList<dietListDTO> dto = dietListDAO.findUserdietList(userid);
		ArrayList<dietListDTO> resultList = new ArrayList<dietListDTO>();
	
		for(dietListDTO dietDto : dto){
			if(dietDto.getEatdate().equals(date))
				resultList.add(dietDto);
		}

		return resultList;
	}
}

package food;

import goodfood.GoodDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class FoodManager {
	private static FoodManager foodMan = new FoodManager();
	private FoodDAO foodDAO;
	
	private FoodManager() {
		try {
			foodDAO = new FoodDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static FoodManager getInstance() {
		return foodMan;
	}
	
	public int insert(FoodDTO food) throws SQLException {
		List<FoodDTO> foodList = foodDAO.findfoodList();
		int i = 0;
		for(FoodDTO fd : foodList)
			i++;
		food.setFoodid(i);
		return foodDAO.insert(food);
	}
	
	public List<FoodDTO> findGoodFoodList(List<GoodDTO> goodList){
		List<FoodDTO> foodList = new ArrayList<FoodDTO>();
		
		for(GoodDTO dto : goodList){
			FoodDTO fDto = new FoodDTO();
			try {
				fDto = foodDAO.findFood(dto.getFoodid());
				
			} catch (SQLException e) {e.printStackTrace();}
			foodList.add(fDto);
		}
		
		return foodList;
	}
	public List<FoodDTO> findfoodList()
			throws SQLException {
			return foodDAO.findfoodList();
	}

	public String findfoodName(int foodid) throws SQLException{
		return foodDAO.findFoodName(foodid);
		
	}
	
	
}


	
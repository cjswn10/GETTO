package exercise;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import food.FoodDAO;
import food.FoodDTO;
import food.FoodManager;

public class ExeManager {
	private static ExeManager exeMan = new ExeManager();
	private ExeDAO ExeDAO;
	
	private ExeManager() {
		try {
			ExeDAO = new ExeDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static ExeManager getInstance() {
		return exeMan;
	}
	
	public List<ExeDTO> findExe() throws SQLException {
			return ExeDAO.findExeList();
		}
	public ArrayList<ExeDTO> ExeList() throws SQLException{
		ArrayList<ExeDTO> all =ExeDAO.findExeList();
		ArrayList<ExeDTO> result = new ArrayList<ExeDTO>();
		
		System.out.print("\nExeManager들어옴\n");
		
		for (ExeDTO dto : all){
			result.add(dto);
		}
		
		System.out.print("넣기완료\n");
		
		return result;
		}
	
}

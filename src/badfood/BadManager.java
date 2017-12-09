package badfood;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dietList.dietListDTO;
import badfood.BadDAO;
import badfood.BadDTO;

public class BadManager {
	private static BadManager badMan = new BadManager();
	private BadDAO badDAO;
	
	private BadManager() {
		try {
			badDAO = new BadDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static BadManager getInstance() {
		return badMan;
	}

	public List<BadDTO> findBadFood(int disid)throws SQLException {
		ArrayList<BadDTO> dtoList = badDAO.findBadFood();
		ArrayList<BadDTO> resultList = new ArrayList<BadDTO>();
		for(BadDTO badDto : dtoList)
			if(badDto.getDisid() == disid)
				resultList.add(badDto);
			return resultList;
	}
	
}
               
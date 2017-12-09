package exerList;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import exerList.ExerListDAO;
import exerList.ExerListDTO;
import exerList.ExerListManager;
import exercise.ExeDTO;
import exercise.ExeDAO;
import exercise.ExeManager;


public class ExerListManager {
	private static ExerListManager ExerListMan = new ExerListManager();
	private ExerListDAO ExerListDAO;

	private ExerListManager() {
		try {
			ExerListDAO = new ExerListDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	public static ExerListManager getInstance() {
		return ExerListMan;
	}
	
	public int insert(ExerListDTO ExerList) throws SQLException {
	
		return ExerListDAO.insert(ExerList);
	}
	
	public ArrayList<ExerListDTO> ExerInfoCheck(String userid) throws SQLException {
		ArrayList<ExerListDTO> dto = ExerListDAO.findUserExerList(userid);
		ArrayList<ExerListDTO> resultList = new ArrayList<ExerListDTO>();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String strDate = df.format(calendar.getTime());
		for(ExerListDTO ExerDto : dto){
			
			if(ExerDto.getExeDate().equals(strDate))
				resultList.add(ExerDto);
				
		}

		return resultList;
	}
	
	public ArrayList<ExerListDTO> ExerInfoCheckDate(String userid, String date) throws SQLException {
		ArrayList<ExerListDTO> dto = ExerListDAO.findUserExerList(userid);
		ArrayList<ExerListDTO> resultList = new ArrayList<ExerListDTO>();
		
		for(ExerListDTO ExerDto : dto){
			if(ExerDto.getExeDate().equals(date))
				resultList.add(ExerDto);
		}

		return resultList;
	}
}

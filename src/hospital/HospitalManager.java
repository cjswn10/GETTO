package hospital;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class HospitalManager {
	private static HospitalManager hospitalMan = new HospitalManager();
	private HospitalDAO hospitalDAO;
	
	private HospitalManager() {
		try {
			hospitalDAO = new HospitalDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static HospitalManager getInstance() {
		return hospitalMan;
	}
	
	public ArrayList<HospitalDTO> findHosAddress(String userAddress) throws SQLException {
		return hospitalDAO.findhos(userAddress);		
	}
	
	public ArrayList<HospitalDTO> findHosArea(String userAddress) throws SQLException {
		ArrayList<HospitalDTO> all = hospitalDAO.findhos(userAddress);//모든병원정보
		
		ArrayList<HospitalDTO> select = new ArrayList<HospitalDTO>();
		
		for(HospitalDTO dto : all){
			if(dto.getHosaddress().equals(userAddress))
				select.add(dto);
		}
		return select;		
	}
	
	public ArrayList<HospitalDTO> findHosAddressAndArea(String userAddress, String hosArea) throws SQLException {
		return hospitalDAO.findHosAddressAndArea(userAddress, hosArea);
		
	}

}
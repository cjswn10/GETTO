package disease;

import java.sql.SQLException;
import java.util.List;

import user.UserDTO;
import user.UserNotFoundException;
import disease.DiseaseDAO;
import disease.DiseaseDTO;

public class DiseaseManager {
	private static DiseaseManager diseaseMan = new DiseaseManager();
	private DiseaseDAO DiseaseDAO;
	
	private DiseaseManager() {
		try {
			DiseaseDAO = new DiseaseDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static DiseaseManager getInstance() {
		return diseaseMan;
	}
	
	public DiseaseDTO findDisease(int disId) throws SQLException{
		return DiseaseDAO.findDisease(disId);
	}
	public List<DiseaseDTO> findDiseaseList()
			throws SQLException {
			return DiseaseDAO.findDiseaseList();
	}

}

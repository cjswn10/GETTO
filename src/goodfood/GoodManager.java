package goodfood;

import java.sql.SQLException;
import java.util.List;

import goodfood.GoodDAO;
import goodfood.GoodDTO;

public class GoodManager {
	private static GoodManager goodMan = new GoodManager();
	private GoodDAO goodDAO;
	
	private GoodManager() {
		try {
			goodDAO = new GoodDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static GoodManager getInstance() {
		return goodMan;
	}

	public List<GoodDTO> RecFood(int disid)
			throws SQLException {
			return goodDAO.RecFood(disid);
	}
	
}

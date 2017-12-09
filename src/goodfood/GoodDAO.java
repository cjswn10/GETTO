package goodfood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GoodDAO {
	private DataSource ds;
	
	public GoodDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	
	

	public List<GoodDTO> RecFood(int disid) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		try {
			String Query = "SELECT goodfoodid, foodid FROM GOODFOOD WHERE DISID=?";

			con = ds.getConnection();
			pstmt = con.prepareStatement(Query);
			pstmt.setInt(1, disid);
			rs = pstmt.executeQuery();

			List<GoodDTO> goodList = new ArrayList<GoodDTO>();
			while(rs.next()) {
				GoodDTO good = new GoodDTO();
				
				good.setGoodfoodid(rs.getInt("goodfoodid"));			
				//good.setDisid(rs.getInt("disid"));
				good.setFoodid(rs.getInt("foodid"));
				
				goodList.add(good);				
			}
			return goodList;
		}  finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}

	}
	
}

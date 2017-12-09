package badfood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BadDAO {
	private DataSource ds;
	
	public BadDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	
	

	public ArrayList<BadDTO> findBadFood() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ArrayList<BadDTO> badList = new ArrayList<BadDTO>();
		ResultSet rs = null;
		try {
			String Query = "SELECT * FROM BADFOOD";
	
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(Query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BadDTO bad = new BadDTO();
				bad.setbadfoodid(rs.getInt("badfoodid"));
				bad.setDisid(rs.getInt("disid"));
				bad.setFoodid(rs.getInt("foodid"));
				badList.add(bad);					
				
			} 
		
			
			return badList;
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

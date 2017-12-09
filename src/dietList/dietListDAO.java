package dietList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import food.FoodDTO;
import user.UserDTO;

public class dietListDAO {
private DataSource ds;
	
	public dietListDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}
	
	public int insert(dietListDTO dietList) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			String Query = "INSERT INTO DIETLIST VALUES (?, ?, ?, ?, ?)";
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(Query);

			pstmt.setString(1, dietList.getUserid());
			pstmt.setString(2, dietList.getFoodname());
			pstmt.setInt(3, dietList.getKcal());
			pstmt.setString(4, dietList.getEatdate());
			pstmt.setString(5, dietList.getEattime());
			
			
			
			int result = pstmt.executeUpdate();
			return result;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( con != null ){
				con.close();
			}
		}
	}
	
	public ArrayList<dietListDTO> findUserdietList(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<dietListDTO> userDietList = new ArrayList<dietListDTO>();
		
		try {
			String Query = "SELECT * FROM DIETLIST WHERE userid=?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(Query);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			
			while ( rs.next() ){
				dietListDTO dietInfo = new dietListDTO();
				dietInfo.setUserid(userId);
				dietInfo.setFoodname(rs.getString("foodname"));
				dietInfo.setKcal(rs.getInt("kcal"));
				dietInfo.setEatdate(rs.getString("eatdate"));
				dietInfo.setEattime(rs.getString("eattime"));

				userDietList.add(dietInfo);
			}
			return userDietList;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	public ArrayList<dietListDTO> finddietList() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<dietListDTO> dietList = new ArrayList<dietListDTO>();
		
		String query = "select * from dietlist";
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				dietListDTO dietInfo = new dietListDTO();
				
				dietInfo.setUserid(rs.getString("userid"));
				dietInfo.setFoodname(rs.getString("foodname"));
				dietInfo.setKcal(rs.getInt("kcal"));
				dietInfo.setEatdate(rs.getString("eatdate"));
				dietInfo.setEattime(rs.getString("eattime"));
				
					
				dietList.add(dietInfo);					
			} 
		

		} catch(SQLException e){e.printStackTrace();
		}finally {
			pstmt.close();
			rs.close();
		}
		return dietList;
		
	}
	
}

package exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import food.FoodDTO;

public class ExeDAO {
private DataSource ds;
	
	public ExeDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}
	
	public ArrayList<ExeDTO> findExeList() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ExeDTO> ExeList = new ArrayList<ExeDTO>();

		String query = "select * from exer";
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ExeDTO exercise = new ExeDTO();
				
				exercise.setExeid(rs.getInt("exeid"));
				exercise.setExename(rs.getString("exename"));
				exercise.setExekcal(rs.getInt("exekcal"));
				exercise.setExepicture(rs.getString("exepicture"));
				exercise.setBmi(rs.getString("bmi"));
				exercise.setExeway(rs.getString("exeway"));
				
				ExeList.add(exercise);
				}
		
		} catch(SQLException e){e.printStackTrace();
		}finally {
			pstmt.close();
			rs.close();
		}
		return ExeList;
		
	}
	
	
}

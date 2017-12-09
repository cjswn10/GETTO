package exerList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class ExerListDAO {
private DataSource ds;

public ExerListDAO() throws Exception {
	Context init = new InitialContext();
	ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
}

public int insert(ExerListDTO ExerList) throws SQLException{
	
	Connection con = null;
	PreparedStatement pstmt = null;
	try{
		String Query = "INSERT INTO EXERCISELIST VALUES (?, ?, ?, ?, ?)";
		
		con = ds.getConnection();
		pstmt = con.prepareStatement(Query);
		
		int kcal, time = 0;
		
		if (ExerList.getExeTime().equals("30분")){
			time = 1;
		} else if (ExerList.getExeTime().equals("1시간")) {
			time = 2;
		} else if (ExerList.getExeTime().equals("1시간 30분")) {
			time = 3;
		} else if (ExerList.getExeTime().equals("2시간")) {
			time = 4;
		} else if (ExerList.getExeTime().equals("2시간 30분")) {
			time = 5;
		} else if (ExerList.getExeTime().equals("3시간")) {
			time = 6;
		} else if (ExerList.getExeTime().equals("3시간 30분")) {
			time = 7;
		} else if (ExerList.getExeTime().equals("4시간")) {
			time = 8;
		} else if (ExerList.getExeTime().equals("4시간 30분")) {
			time = 9;
		} else if (ExerList.getExeTime().equals("5시간")) {
			time = 10;
		}
		
		kcal = ExerList.getUseKcal() * time;
		

		System.out.print("\nExerListDAO : "+ExerList.getUserId()+"\n"+ExerList.getUseKcal()+"\n");
		pstmt.setString(1, ExerList.getUserId());
		pstmt.setString(2, ExerList.getExeName());
		pstmt.setInt(3, kcal);
		pstmt.setString(4, ExerList.getExeDate());
		pstmt.setString(5, ExerList.getExeTime());
			
		int result=pstmt.executeUpdate();
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

public ArrayList<ExerListDTO> findUserExerList(String userId) throws SQLException {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ArrayList<ExerListDTO> userExerList = new ArrayList<ExerListDTO>();
	
	try {
		String Query = "SELECT * FROM EXERCISELIST WHERE userid=?";
		con = ds.getConnection();
		pstmt = con.prepareStatement(Query);
		pstmt.setString(1, userId);
		rs = pstmt.executeQuery();
		
		
		while ( rs.next() ){
			ExerListDTO ExerInfo = new ExerListDTO();
			ExerInfo.setUserId(userId);
			ExerInfo.setExeDate(rs.getString("ExeDate"));
			ExerInfo.setExeTime(rs.getString("ExeTime"));
			ExerInfo.setUseKcal(rs.getInt("usekcal"));
			ExerInfo.setExeName(rs.getString("ExeName"));

			userExerList.add(ExerInfo);
		}
		return userExerList;
	} finally {
		if ( pstmt != null ){
			pstmt.close();
		}			
		if ( con != null ){
			con.close();
		}
	}
}



}

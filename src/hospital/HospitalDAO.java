package hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class HospitalDAO {
private DataSource ds;
	
	public HospitalDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}
	
	public ArrayList<HospitalDTO> findhos(String userAddress) throws SQLException {
		ArrayList <HospitalDTO> hospitalList = new ArrayList<HospitalDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("hospitalDAO 실행");
		
		try {
			String Query = "SELECT * FROM HOSPITAL WHERE hosaddress=?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(Query);
			pstmt.setString(1, userAddress);
			rs = pstmt.executeQuery();
			
			HospitalDTO hospital = null;
			while ( rs.next() ){
				hospital = new HospitalDTO();
				hospital.setHosid(rs.getInt("hosid"));
				hospital.setHosname(rs.getString("hosname"));
				hospital.setHosaddress(rs.getString("hosaddress"));
				hospital.setHosaddress2(rs.getString("hosaddress2"));
				hospital.setHosphone(rs.getString("hosphone"));
				hospital.setHosarea(rs.getString("hosarea"));
				hospitalList.add(hospital);
			}
			
		} catch(SQLException e){e.printStackTrace();
		
		}finally {

			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
			
		}
		return hospitalList;
	}
	
	public ArrayList<HospitalDTO> findHosAddressAndArea(String userAddress, String hosArea) throws SQLException {
		ArrayList <HospitalDTO> hospitalList2 = new ArrayList<HospitalDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("hospitalDAO 실행");
		
		try {
			String Query = "SELECT * FROM HOSPITAL WHERE hosaddress=? AND hosarea=?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(Query);
			pstmt.setString(1, userAddress);
			pstmt.setString(2, hosArea);
			
			rs = pstmt.executeQuery();
			
			HospitalDTO hospital = null;
			while ( rs.next() ){
				hospital = new HospitalDTO();
				hospital.setHosid(rs.getInt("hosid"));
				hospital.setHosname(rs.getString("hosname"));
				hospital.setHosaddress(rs.getString("hosaddress"));
				hospital.setHosaddress2(rs.getString("hosaddress2"));
				hospital.setHosphone(rs.getString("hosphone"));
				hospital.setHosarea(rs.getString("hosarea"));
				hospitalList2.add(hospital);
			}
			
		} catch(SQLException e){e.printStackTrace();
		
		}finally {

			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
			
		}
		return hospitalList2;
	}
}


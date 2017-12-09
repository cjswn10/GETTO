package disease;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dietList.dietListDTO;

public class DiseaseDAO {
private DataSource ds;
	
	public DiseaseDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	
	public DiseaseDTO findDisease(int disID) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String query = "select * from disease where disid=?";
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, disID);
			rs = pstmt.executeQuery();
			DiseaseDTO  disease = null;
			if(rs.next()){
			disease = new DiseaseDTO();
				disease.setDisname(rs.getString("disname"));
				disease.setDisid(rs.getInt("disid"));
			}
				return disease;
		}finally {
			pstmt.close();
			
		}
	
		
		
	}
	public ArrayList<DiseaseDTO> findDiseaseList() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<DiseaseDTO> diseaseList = new ArrayList<DiseaseDTO>();
		
		String query = "select * from disease";
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				DiseaseDTO disease = new DiseaseDTO();
				
				disease.setDisid(rs.getInt("disid"));
				disease.setDisname(rs.getString("disname"));
						
				diseaseList.add(disease);					
			} 
		

		} catch(SQLException e){e.printStackTrace();
		}finally {
			pstmt.close();
			rs.close();
		}
		return diseaseList;
		
	}
	
}

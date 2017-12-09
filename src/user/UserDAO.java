package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


/*DBMS �������*/
public class UserDAO {
	private DataSource ds;
	
	public UserDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
}		
	
	/*USERINFO ���̺� ���ο� ����� ���ڵ� ����*/
	public int create(UserDTO user) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String Query = "INSERT INTO USERINFO VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		try{
			
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(Query);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getAddress1());
			pstmt.setString(5, user.getAddress2());
			pstmt.setString(6, user.getPhone());
			pstmt.setString(7, user.getGender());
			pstmt.setString(8, user.getBirthday());
			pstmt.setInt(9, user.getHeight());
			pstmt.setInt(10, user.getWeight());
			pstmt.setInt(11, user.getBmi());
			pstmt.setInt(12, user.getDisid());
			
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
	
	
	/*����� ��������*/
	public int update(UserDTO user) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer updateQuery = new StringBuffer();
			updateQuery.append("UPDATE USERINFO SET ");
			updateQuery.append("password=?, address1=?, address2=?, phone=?, height=?, weight=?, bmi=?, disid=? ");
			updateQuery.append("WHERE userid=? ");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(updateQuery.toString());

			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getAddress1());
			pstmt.setString(3, user.getAddress2());
			pstmt.setString(4, user.getPhone());
			pstmt.setInt(5, user.getHeight());
			pstmt.setInt(6, user.getWeight());
			pstmt.setInt(7, user.getBmi());
			pstmt.setInt(8, user.getDisid());
			pstmt.setString(9, user.getUserId());
			
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
	

	/**
	 * ����� ���̵� ������ �����ͺ��̽����� ã�� User ������ Ŭ������ 
	 * �����Ͽ� ��ȯ.
	 */
	public UserDTO findUser(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String Query = "SELECT * FROM USERINFO WHERE userid=?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(Query);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			UserDTO user = null;
			if ( rs.next() ){
				user = new UserDTO();
				user.setUserId(userId);
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setAddress1(rs.getString("address1"));
				user.setAddress2(rs.getString("address2"));
				user.setPhone(rs.getString("phone"));
				user.setGender(rs.getString("gender"));
				user.setBirthday(rs.getString("birthday"));
				user.setHeight(rs.getInt("height"));
				user.setWeight(rs.getInt("weight"));
				user.setBmi(rs.getInt("bmi"));
				user.setDisid(rs.getInt("disid"));

			}
			return user;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	/**
	 * ���ڷ� ���޵Ǵ� ���̵� ������ ����ڰ� �����ϴ����� 
	 * ������ �Ǻ�. 
	 */
	public boolean existedUser(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer existedQuery = new StringBuffer();
			existedQuery.append("SELECT count(*) FROM USERINFO ");
			existedQuery.append("WHERE userid=? ");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(existedQuery.toString());
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			int count = 0;
			if ( rs.next() ){
				count = rs.getInt(1);
			}
			if ( count == 1 ) {
				return true;
			} else {
				return false;
			}
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}		
	}
	
	/**
	 * ����� ���̵� �ش��ϴ� ����ڸ� ����.
	 */
	public int remove(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer removeQuery = new StringBuffer();
			removeQuery.append("DELETE FROM USERINFO ");
			removeQuery.append("WHERE userid=? ");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(removeQuery.toString());
			pstmt.setString(1, userId);
			
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
	
	public UserDTO findBmi(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String Query = "SELECT * FROM USERINFO WHERE userid=?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(Query);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			UserDTO user = null;
			if ( rs.next() ){
				user = new UserDTO();
				user.setUserId(userId);
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setAddress1(rs.getString("address1"));
				user.setAddress2(rs.getString("address2"));
				user.setPhone(rs.getString("phone"));
				user.setGender(rs.getString("gender"));
				user.setBirthday(rs.getString("birthday"));
				user.setHeight(rs.getInt("height"));
				user.setWeight(rs.getInt("weight"));
				user.setBmi(rs.getInt("bmi"));
				user.setDisid(rs.getInt("disid"));

			}
			return user;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
		
		
	}
		
	/*ȸ������ userid ������ list�������� ���*/
	public List<UserDTO> findUserList() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT * ");
			findQuery.append("FROM USERINFO ORDER BY userid");
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
		
			rs = pstmt.executeQuery();

			List<UserDTO> userList = new ArrayList<UserDTO>();
				
			 while (rs.next()) {
					UserDTO user = new UserDTO();
					user.setUserId(rs.getString("userid"));
					user.setPassword(rs.getString("password"));
					user.setName(rs.getString("name"));
					user.setAddress1(rs.getString("address1"));
					user.setAddress2(rs.getString("address2"));
					user.setPhone(rs.getString("phone"));
					user.setGender(rs.getString("gender"));
					user.setBirthday(rs.getString("birthday"));
					user.setHeight(rs.getInt("height"));
					user.setWeight(rs.getInt("weight"));
					user.setBmi(rs.getInt("bmi"));
					user.setDisid(rs.getInt("disid"));
					
					userList.add(user);					
				};				
			return userList;
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

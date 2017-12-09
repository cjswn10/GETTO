package food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class FoodDAO {

private DataSource ds;
	
	public FoodDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		}	
	
	/*음식 생성*/
	public int insert(FoodDTO food) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			String Query = "INSERT INTO FOOD VALUES (?,?,?,?)";
			con = ds.getConnection();
			pstmt = con.prepareStatement(Query);
			pstmt.setInt(1, food.getFoodid());
			pstmt.setString(2, food.getFoodname());
			pstmt.setInt(3, food.getFoodkcal());
			pstmt.setString(4, food.getFoodDetail());
			
			int result = pstmt.executeUpdate();
			System.out.print(food.getFoodid() + ":"+ food.getFoodname() + ":" + food.getFoodkcal() + ":" +food.getFoodDetail());
			
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
	
	/*foodId로 음식 찾아서 전달*/
	public FoodDTO findFood(int foodId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String Query = "SELECT foodname, foodDetail FROM FOOD WHERE foodid=?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(Query);
			pstmt.setInt(1, foodId);
			rs = pstmt.executeQuery();
			
			FoodDTO food = null;
			if ( rs.next() ){
				food = new FoodDTO();
				food.setFoodid(foodId);
				food.setFoodname(rs.getString("foodname"));
				food.setFoodDetail(rs.getString("foodDetail"));

			}
			return food;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	/*
	//병명을 받아서 음식추천 전달
	public List<FoodDTO> RecFood(int disid) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			List<FoodDTO> foodList = null;
			ResultSet rs = null;
			try {
				String Query = "SELECT * FROM FOOD F JOIN GOODFOOD GF ON F.FOODID = GF.FOODID WHERE DISID=?";
		
				con = ds.getConnection();
				pstmt = con.prepareStatement(Query);
				rs = pstmt.executeQuery();
				
				while(rs != null) {
					foodList = new ArrayList<FoodDTO>();
					
					while(rs.next()) {
						FoodDTO food = new FoodDTO();
						food.setFoodid(rs.getInt("foodid"));
						food.setFoodname(rs.getString("foodname"));
						food.setFoodkcal(rs.getInt("foodkcal"));
						food.setFoodDetail(rs.getString("fooddetail"));
						
						
						foodList.add(food);					
					} 
				}
				
			} catch(SQLException e){e.printStackTrace();
			}finally {
				pstmt.close();
				rs.close();
			}
			return foodList;
		}
	
	*/
	public ArrayList<FoodDTO> findfoodList() throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<FoodDTO> foodList = new ArrayList<FoodDTO>();
			
			String query = "select * from food";
			
			try {
				con = ds.getConnection();
				
				pstmt = con.prepareStatement(query);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					FoodDTO food = new FoodDTO();
					food.setFoodid(rs.getInt("foodid"));
					food.setFoodname(rs.getString("foodname"));
					food.setFoodkcal(rs.getInt("foodkcal"));
					food.setFoodDetail(rs.getString("fooddetail"));
						
					foodList.add(food);					
				} 
			

			} catch(SQLException e){e.printStackTrace();
			}finally {
				pstmt.close();
				rs.close();
			}
			return foodList;
			
		}
	/*foodId로 음식이름만 찾아서 전달*/
	public String findFoodName(int foodId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String Query = "SELECT foodname FROM FOOD WHERE foodid=?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(Query);
			pstmt.setInt(1, foodId);
			rs = pstmt.executeQuery();
			
			String foodName = "";
			if ( rs.next() ){
				foodName = rs.getString("foodname");
			}
			return foodName;
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
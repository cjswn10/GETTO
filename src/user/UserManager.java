package user;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class UserManager {
	private static UserManager userMan = new UserManager();
	private UserDAO userDAO;

	private UserManager() {
		try {
			userDAO = new UserDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static UserManager getInstance() {
		return userMan;
	}
	
	public int create(UserDTO user) throws SQLException, ExistedUserException {
		if (userDAO.existedUser(user.getUserId())) {
			throw new ExistedUserException(user.getUserId() + "는 존재하는 아이디입니다.");
		}
		return userDAO.create(user);
	}

	public int update(UserDTO user) throws SQLException {
		return userDAO.update(user);
	}	

	public int remove(String userId) throws SQLException {
		return userDAO.remove(userId);
	}

	public UserDTO findUser(String userId)
		throws SQLException, UserNotFoundException {
		UserDTO user = userDAO.findUser(userId);

		if (user == null) {
			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
		}		
		return user;
	}

	public boolean login(String userId, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		UserDTO user = findUser(userId);

		if (!user.isMatchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}
	
	public List<UserDTO> findUserList()
			throws SQLException {
			return userDAO.findUserList();
		}
	
	public UserDTO findBmi(String userId) throws SQLException { //특정 회원의 bmi 구하기 
		 UserDTO user1 = userDAO.findBmi(userId);
		 return user1;
	}
	public int calBirthYear(UserDTO dto) throws SQLException{ //회원의 나이 계산
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String strDate = df.format(calendar.getTime());
		String birth = dto.getBirthday();
		String userYearDate = birth.substring(0, 4);
		int birthYear = Integer.parseInt(strDate) - Integer.parseInt(userYearDate) + 1;
		return birthYear;
	}
	
	public double getAvgBmi() throws SQLException{ //평균 bmi 구하기
		List<UserDTO> userList = findUserList();
		double totalBmi = 0, i = 0, avgBmi = 0;
		for(UserDTO dto : userList){
			totalBmi += dto.getBmi();
			i++;
		}
		avgBmi =totalBmi / i; //Math 함수를 이용해 double의 소수점 둘째짜리까지만 나타나게 계산!
		double temp = avgBmi*Math.pow(10,2);
		temp = Math.floor(temp + 0.5);
		temp *= Math.pow(10, -2);
		/*pow(a,b) 는 a^b를 리턴 floor(a) 는 a의 소수점을 버림해서 리턴*/
		return temp;
	}

	
	public double[] getBmiByAge() throws SQLException{ //연령대별 Bmi
		double[] totalBmiList = new double[5];// 10대, 20대, 30대, 4-50대, 60대이상
		int[] count = new int[5];
		double[] avgBmiList = new double[5];
		for(int i = 0; i <5; i++){
			totalBmiList[i] = 0;
			avgBmiList[i] = 0;
			count[i] = 0;
		}
		int i;
		List<UserDTO> userList = findUserList();
		int year = 0; 
		for(UserDTO dto : userList){
			year = calBirthYear(dto);
			if(year <= 19){ //10대
				totalBmiList[0] += dto.getBmi();
				count[0]++;
			}
			else if(year >= 20 && year <= 29){ //20대
				totalBmiList[1] += dto.getBmi();
				count[1]++;
			}
			else if(year >= 30 && year <= 39){ //30대
				totalBmiList[2] += dto.getBmi();
				count[2]++;
			}
			else if(year >= 40 && year <= 59){ //4-50대
				totalBmiList[3] += dto.getBmi();
				count[3]++;
			}
			else{ //60대 이상
				totalBmiList[4] += dto.getBmi();
				count[4]++;
			}
		}
		
		double temp;
		for(i = 0; i < 5; i++){
			temp = totalBmiList[i]/count[i];
			temp = temp*Math.pow(10,2);
			temp = Math.floor(temp + 0.5);
			temp *= Math.pow(10, -2);
			avgBmiList[i] = temp; //소수점 둘째짜리까지 계산 해서 저장
			if(count[i] == 0) avgBmiList[i] = 0;
		}
		
		
		return avgBmiList;
	}
	
	public int[] countUser() throws SQLException{ //연령대별 인원수 반환
		int[] count = new int[5];
		List<UserDTO> userList = findUserList();
		int year = 0; 
		for(UserDTO dto : userList){
			year = calBirthYear(dto);
			if(year <= 19){ //10대
				count[0]++;
			}
			else if(year >= 20 && year <= 29){ //20대
				count[1]++;
			}
			else if(year >= 30 && year <= 39){ //30대
				count[2]++;
			}
			else if(year >= 40 && year <= 59){ //4-50대
				count[3]++;
			}
			else{ //60대 이상
				count[4]++;
			}
		}
		return count;
	}
	public double getAvgAge() throws SQLException{ //평균 연령대 구하기
		List<UserDTO> userList = findUserList();
		double totalAge = 0, i = 0, avgAge = 0;

		for(UserDTO dto : userList){
			totalAge += calBirthYear(dto);
			i++;
		}
		avgAge =totalAge / i; //Math 함수를 이용해 double의 소수점 둘째짜리까지만 나타나게 계산!
		double temp = avgAge*Math.pow(10,2);
		temp = Math.floor(temp + 0.5);
		temp *= Math.pow(10, -2);
		
		return temp;
	}
	
	public int findUserDisId(String userId) throws SQLException{
		UserDTO user1 = userDAO.findUser(userId);
		return user1.getDisid();
	}
	public String findUserAddress(String userId) throws SQLException {
		UserDTO user = userDAO.findUser(userId);
		return user.getAddress1();
	}
	
/*
	private UserDAO getUserDAO(){
		try {
			return new UserDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	*/
}

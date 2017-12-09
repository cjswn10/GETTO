package user;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * UserDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
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
			throw new ExistedUserException(user.getUserId() + "�� �����ϴ� ���̵��Դϴ�.");
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
			throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
		}		
		return user;
	}

	public boolean login(String userId, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		UserDTO user = findUser(userId);

		if (!user.isMatchPassword(password)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return true;
	}
	
	public List<UserDTO> findUserList()
			throws SQLException {
			return userDAO.findUserList();
		}
	
	public UserDTO findBmi(String userId) throws SQLException { //Ư�� ȸ���� bmi ���ϱ� 
		 UserDTO user1 = userDAO.findBmi(userId);
		 return user1;
	}
	public int calBirthYear(UserDTO dto) throws SQLException{ //ȸ���� ���� ���
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String strDate = df.format(calendar.getTime());
		String birth = dto.getBirthday();
		String userYearDate = birth.substring(0, 4);
		int birthYear = Integer.parseInt(strDate) - Integer.parseInt(userYearDate) + 1;
		return birthYear;
	}
	
	public double getAvgBmi() throws SQLException{ //��� bmi ���ϱ�
		List<UserDTO> userList = findUserList();
		double totalBmi = 0, i = 0, avgBmi = 0;
		for(UserDTO dto : userList){
			totalBmi += dto.getBmi();
			i++;
		}
		avgBmi =totalBmi / i; //Math �Լ��� �̿��� double�� �Ҽ��� ��°¥�������� ��Ÿ���� ���!
		double temp = avgBmi*Math.pow(10,2);
		temp = Math.floor(temp + 0.5);
		temp *= Math.pow(10, -2);
		/*pow(a,b) �� a^b�� ���� floor(a) �� a�� �Ҽ����� �����ؼ� ����*/
		return temp;
	}

	
	public double[] getBmiByAge() throws SQLException{ //���ɴ뺰 Bmi
		double[] totalBmiList = new double[5];// 10��, 20��, 30��, 4-50��, 60���̻�
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
			if(year <= 19){ //10��
				totalBmiList[0] += dto.getBmi();
				count[0]++;
			}
			else if(year >= 20 && year <= 29){ //20��
				totalBmiList[1] += dto.getBmi();
				count[1]++;
			}
			else if(year >= 30 && year <= 39){ //30��
				totalBmiList[2] += dto.getBmi();
				count[2]++;
			}
			else if(year >= 40 && year <= 59){ //4-50��
				totalBmiList[3] += dto.getBmi();
				count[3]++;
			}
			else{ //60�� �̻�
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
			avgBmiList[i] = temp; //�Ҽ��� ��°¥������ ��� �ؼ� ����
			if(count[i] == 0) avgBmiList[i] = 0;
		}
		
		
		return avgBmiList;
	}
	
	public int[] countUser() throws SQLException{ //���ɴ뺰 �ο��� ��ȯ
		int[] count = new int[5];
		List<UserDTO> userList = findUserList();
		int year = 0; 
		for(UserDTO dto : userList){
			year = calBirthYear(dto);
			if(year <= 19){ //10��
				count[0]++;
			}
			else if(year >= 20 && year <= 29){ //20��
				count[1]++;
			}
			else if(year >= 30 && year <= 39){ //30��
				count[2]++;
			}
			else if(year >= 40 && year <= 59){ //4-50��
				count[3]++;
			}
			else{ //60�� �̻�
				count[4]++;
			}
		}
		return count;
	}
	public double getAvgAge() throws SQLException{ //��� ���ɴ� ���ϱ�
		List<UserDTO> userList = findUserList();
		double totalAge = 0, i = 0, avgAge = 0;

		for(UserDTO dto : userList){
			totalAge += calBirthYear(dto);
			i++;
		}
		avgAge =totalAge / i; //Math �Լ��� �̿��� double�� �Ҽ��� ��°¥�������� ��Ÿ���� ���!
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

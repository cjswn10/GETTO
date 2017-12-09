package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;

import user.*;


public class userListAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		UserManager manager = UserManager.getInstance();
		List<UserDTO> userList = manager.findUserList();
		
		double userAvgBmi = manager.getAvgBmi(); //ȸ������ ��� bmi
		double[] bmiByAge = manager.getBmiByAge(); //���ɴ뺰 ��� bmi ���� double �迭
		double userAvgAge = manager.getAvgAge(); //ȸ������ ��� ����
		int[] countUser = manager.countUser();
		
		//userList��ü�� response�� �����Ͽ� ����.
		request.setAttribute("userList", userList);		
		request.setAttribute("avgBmi", userAvgBmi);
		request.setAttribute("avgAge", userAvgAge);
		request.setAttribute("bmiByAge", bmiByAge);
		request.setAttribute("countUser", countUser);
		//�̵��� �������� ����.
		ActionForward forward = new ActionForward();
		forward.setPath("stats.jsp");
				
		return forward;
	}
}

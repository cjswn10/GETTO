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
		
		double userAvgBmi = manager.getAvgBmi(); //회원들의 평균 bmi
		double[] bmiByAge = manager.getBmiByAge(); //연령대별 평균 bmi 담은 double 배열
		double userAvgAge = manager.getAvgAge(); //회원들의 평균 연령
		int[] countUser = manager.countUser();
		
		//userList객체를 response에 저장하여 전달.
		request.setAttribute("userList", userList);		
		request.setAttribute("avgBmi", userAvgBmi);
		request.setAttribute("avgAge", userAvgAge);
		request.setAttribute("bmiByAge", bmiByAge);
		request.setAttribute("countUser", countUser);
		//이동할 페이지를 결정.
		ActionForward forward = new ActionForward();
		forward.setPath("stats.jsp");
				
		return forward;
	}
}

package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import user.UserDTO;
import user.UserManager;
import controller.ActionForward;
import food.FoodDTO;
import food.FoodManager;

public class BmiSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (request.getMethod().equals("GET")) {	// request an insert form
			ActionForward forward = new ActionForward();
			forward.setPath("ExerciseRecommend.jsp");
			forward.setRedirect(true);
			return forward;
			
		}
		HttpSession session = request.getSession(true); //���ǰ� �ҷ�����!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		
		ActionForward forward = new ActionForward();
		//�̵��� �������� ����.
		try {
			UserManager manager = UserManager.getInstance();
			manager.findBmi((String)session.getAttribute("userid"));
//
//			//foodList��ü�� response�� �����Ͽ� ����.
//			request.setAttribute("UserList", UserList);		

			forward.setPath("ExerciseRecommend.m2?command=findExe");
			forward.setRedirect(true);
			
		} catch (Exception e) {e.getStackTrace();}
			return forward;
	}
	

}

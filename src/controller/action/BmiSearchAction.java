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
		HttpSession session = request.getSession(true); //세션값 불러오기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		
		ActionForward forward = new ActionForward();
		//이동할 페이지를 결정.
		try {
			UserManager manager = UserManager.getInstance();
			manager.findBmi((String)session.getAttribute("userid"));
//
//			//foodList객체를 response에 저장하여 전달.
//			request.setAttribute("UserList", UserList);		

			forward.setPath("ExerciseRecommend.m2?command=findExe");
			forward.setRedirect(true);
			
		} catch (Exception e) {e.getStackTrace();}
			return forward;
	}
	

}

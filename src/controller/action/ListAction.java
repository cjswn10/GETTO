package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDTO;
import user.UserManager;
import controller.ActionForward;

public class ListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("listAction"+request.getMethod());
	
		// action == null 값일 경우 index.jsp 실행 
		
		HttpSession session = request.getSession(true);
		String userid = (String)session.getAttribute("userid");
		
		UserManager userManager = UserManager.getInstance();
		UserDTO user = userManager.findUser(userid);
		request.setAttribute("user", user);	
		ActionForward forward = new ActionForward();
		forward.setPath("index.jsp");
		return forward;
	}

}

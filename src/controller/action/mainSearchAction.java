package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDTO;
import user.UserManager;
import controller.ActionForward;

public class mainSearchAction implements Action {
	
	public ActionForward execute(
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {	
		HttpSession session = request.getSession(true);
		String userId = (String)session.getAttribute("userid");

		UserManager manager = UserManager.getInstance();
		UserDTO user = manager.findUser(userId);
		
		request.setAttribute("user", user);		
		ActionForward forward;
		if("admin".equals(userId)) //관리자모드
		{
			System.out.print(userId);
			forward = new ActionForward();
			forward.setPath("adminMain.jsp"); 
		}
		else
		{
		forward = new ActionForward();
		forward.setPath("main.jsp");
		}		
		return forward;
	}
}

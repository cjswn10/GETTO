package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.*;
import user.PasswordMismatchException;
import user.UserDTO;
import user.UserManager;
/* 아이디 비밀번호 입력후 */

public class LoginAction implements Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		System.out.println(request.getMethod());
		
		if (request.getMethod().equals("GET")) {	// request a login form
			ActionForward forward = new ActionForward();
			forward.setPath("login.jsp");
			forward.setRedirect(true);
			return forward;		
		}
		
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		ActionForward forward = new ActionForward();

		
		UserManager userManager = UserManager.getInstance();
		UserDTO user = userManager.findUser(userid);
		request.setAttribute("user", user);	

		
		try {
			//모델에 로그인 처리를 위임.
			UserManager manager = UserManager.getInstance();
			manager.login(userid, password);
			
			//세션에 사용자 이이디 저장.
			HttpSession session = request.getSession(true);
			session.setAttribute("userid", userid);
			
			//이동할 페이지를 결정.
			
			forward.setPath("index.m2");
			forward.setRedirect(true);

		} catch (Exception e) {
		
			request.setAttribute("exception", e);
	
			forward.setPath("login.jsp");
			forward.setRedirect(false);					
		}	
		return forward;
	}
}

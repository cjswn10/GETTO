package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.*;

import user.UserDTO;
import user.UserManager;


public class ViewAction implements Action {
	/**
	 * UserManager의 findUser메써드를 호출하여 반환된 
	 * User를 response에 저장하는 소스코드가 들어간다. 
	 * user_view.jsp에서 response에 저장된 User를 사용하게 된다.
	 */
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response)
		throws Exception {
		HttpSession session = request.getSession(true);
		
		String userId = session.getAttribute("userid").toString();
		
		UserManager manager = UserManager.getInstance();
		UserDTO user = manager.findUser(userId);
		
		request.setAttribute("user", user);		
		ActionForward forward = new ActionForward();
		DiseaseSearchAction dsAction = new DiseaseSearchAction();
		dsAction.execute(request, response);
		forward.setPath("user_modify.jsp");
				
		return forward;
	}
}

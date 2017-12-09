package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.*;

import user.UserDTO;
import user.UserManager;


public class ViewAction implements Action {
	/**
	 * UserManager�� findUser�޽�带 ȣ���Ͽ� ��ȯ�� 
	 * User�� response�� �����ϴ� �ҽ��ڵ尡 ����. 
	 * user_view.jsp���� response�� ����� User�� ����ϰ� �ȴ�.
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

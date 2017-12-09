package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;

import user.UserManager;


public class DeleteAction implements Action {
	/**
	 * request에 전달된 ID 이용. BoardManager의 delete메써드를 
	 * 호출하여 해당 게시물을 삭제하는 소스코드가 들어간다.
	 */
	public ActionForward execute(
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		String userId = request.getParameter("userId");
		
		UserManager manager = UserManager.getInstance();
		manager.remove(userId);

		ActionForward forward = new ActionForward();
		forward.setPath("user_list.m2");
		forward.setRedirect(true);
			
		return forward;		
	}
}

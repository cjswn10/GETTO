package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.*;

public class LogoutAction implements Action {
	public ActionForward execute(
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		//���ǿ� ����� ����� ���̵� �� ���� ����
		HttpSession session = request.getSession();
		session.removeAttribute("userId");
		session.invalidate();
		
		//�̵��� �������� ����.
		ActionForward forward = new ActionForward();
		forward.setPath("login.m2");
		forward.setRedirect(true);
				
		return forward;
	}
}

package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDTO;
import user.UserManager;
import controller.ActionForward;


public class searchUserListAction implements Action {

	public ActionForward execute(
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		UserManager manager = UserManager.getInstance();
		List<UserDTO> userList = manager.findUserList();
		
		request.setAttribute("userList", userList);
		ActionForward forward = new ActionForward();

		
		forward.setPath("admin_userList.jsp");

		return forward;
	}

}
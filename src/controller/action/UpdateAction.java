package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import user.UserDTO;
import user.UserManager;


public class UpdateAction implements Action {
	/**
	 * request에 저장되어 있는 인자값으로 User객체를 생성하여 
	 * UserManager의 update메써드를 호출하여 기존의 사용자 정보를 수정한다. 
	 */
	public ActionForward execute(
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		ActionForward forward = new ActionForward();

		System.out.println(request.getMethod());
		
		if (request.getMethod().equals("GET")) {	// request a login form
			String userId = request.getParameter("userId");

			UserManager manager = UserManager.getInstance();
			UserDTO user = manager.findUser(userId);
	
			request.setAttribute("user", user);
			forward.setPath("user_modify.jsp");
		} 
		else {
			float height = (float)(Integer.parseInt(request.getParameter("height")) * 0.01);
			float weight = Integer.parseInt(request.getParameter("weight"));
			int bmi = (int)weight /(int)(height*height); // 비만도 계산 공식
		
			UserDTO user = new UserDTO();
			user.setUserId(request.getParameter("userId"));
			user.setPassword(request.getParameter("password"));
			user.setName(request.getParameter("name"));
			user.setAddress1(request.getParameter("address1"));
			user.setAddress2(request.getParameter("address2"));
			user.setPhone(request.getParameter("phone"));
			//user.setGender(request.getParameter("gender"));
			//user.setBirthday(request.getParameter("birthday"));
			user.setHeight(Integer.parseInt(request.getParameter("height")));
			user.setWeight(Integer.parseInt(request.getParameter("weight")));
			user.setBmi(bmi);
			user.setDisid(Integer.parseInt(request.getParameter("userDisease")));
			
			UserManager manager = UserManager.getInstance();
			manager.update(user);
				
			forward.setPath("main.m2?command=mainSearch");
			forward.setRedirect(true);
		}
		
		return forward;
	}
}

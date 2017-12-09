package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.*;
import sun.rmi.runtime.Log;
import user.ExistedUserException;
import user.UserDTO;
import user.UserManager;


public class InsertAction implements Action {
	/**
	 * request�� ����Ǿ� �ִ� ���ڰ����� User��ü�� �����Ͽ� 
	 * UserManager�� create�޽�带 ȣ���Ͽ� ���ο� �Խù���
	 * �Է��Ѵ�. 
	 * �Է��� �Ϸ��� �� 
	 */
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response)throws Exception {

		System.out.println(request.getMethod());
		
		if (request.getMethod().equals("GET")) {	// request an insert form
				ActionForward forward = new ActionForward();
				forward.setPath("user_write.jsp");
				forward.setRedirect(true);
				return forward;
		}
		float height = (float)(Integer.parseInt(request.getParameter("height"))*0.01);
		float weight = Integer.parseInt(request.getParameter("weight"));
		int bmi = (int)(weight/(height*height));
		
		System.out.println("InsertAction �񸸵�:" + bmi);
		System.out.println(request.getParameter("userid"));
		
		UserDTO user = new UserDTO();
		user.setUserId(request.getParameter("userid"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setAddress1(request.getParameter("address1"));
		user.setAddress2(request.getParameter("address2"));
		user.setPhone(request.getParameter("phone"));
		user.setGender(request.getParameter("gender"));
		user.setBirthday(request.getParameter("birthday"));
		user.setHeight(Integer.parseInt(request.getParameter("height")));
		user.setWeight(Integer.parseInt(request.getParameter("weight")));
		user.setBmi(bmi);
		user.setDisid(Integer.parseInt(request.getParameter("userDisease")));
		
		ActionForward forward = new ActionForward();
		try {
			UserManager manager = UserManager.getInstance();
			manager.create(user);

			forward.setPath("login.jsp");
			forward.setRedirect(true);
					
		} catch (ExistedUserException e) {
			
			request.setAttribute("exception", e);
			forward.setPath("user_write.jsp");
			forward.setRedirect(false);					
		}	
			
		return forward;
	}

}

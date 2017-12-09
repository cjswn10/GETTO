package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.Action;


/**
 * @web.servlet name="user" 
 * 				display-name="User Manager Servlet" 
 * 
 * @web.servlet-mapping url-pattern="*.m2"
 */

@SuppressWarnings("serial")
public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		ActionForward forward = null;
		Action action = null;
		try {
			/*
			 * CommandFactory에 요청에 의하여 전달된 action을 전달하여
			 * 해당하는 Action객체를 생성한다.
			 */
			UserCommandFactory cf = UserCommandFactory.getInstance();

			String command = request.getParameter("command");
			System.out.print("\n결과: " + command);
			if (command == null) {
				
				command = "list";
			}
	
			action = cf.getAction(command);
			
			forward = action.execute(request, response);
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}

		/*
		 * Action에 의해서 전달된 User, List객체들이 response객체를 통하여 전달된다.
		 */
		if ( forward.isRedirect()) {
			response.sendRedirect(forward.getPath()); 
		} else {
			RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
			rd.forward(request, response);			
		}		
	}
}

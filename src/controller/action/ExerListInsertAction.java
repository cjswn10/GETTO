package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import exerList.ExerListDTO;
import exerList.ExerListManager;

public class ExerListInsertAction implements Action{

	public ActionForward execute(
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
			System.out.println(request.getMethod());
			if (request.getMethod().equals("GET")) {	// request an insert form
				ActionForward forward = new ActionForward();
				forward.setPath("ExerCheck.jsp");
				forward.setRedirect(true);
				return forward;
		}
			String exeName = request.getParameter("exename");
			ExerListDTO ExerList = new ExerListDTO();
			
			ExerList.setUserId(request.getParameter("userid"));
			
			if(exeName.equals("userChoice")) //직접입력의 경우 input 값을 가져옴
				ExerList.setExeName(request.getParameter("exename2"));
			else
				ExerList.setExeName(request.getParameter("exename"));
			
			ExerList.setUseKcal(Integer.parseInt(request.getParameter("usekcal")));
			ExerList.setExeDate(request.getParameter("exedate"));
			ExerList.setExeTime(request.getParameter("exetime"));

			ActionForward forward = new ActionForward();
		
			try {
				ExerListManager manager = ExerListManager.getInstance();
				manager.insert(ExerList);
				
				forward.setPath("ExerCheck.m2?command=exerSearch");
				forward.setRedirect(true);

						
			} catch (Exception e) {e.getStackTrace();}	
			return forward;
		}

}

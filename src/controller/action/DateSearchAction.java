package controller.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import dietList.dietListDTO;
import dietList.dietListManager;
import exerList.ExerListDTO;
import exerList.ExerListManager;
import exercise.ExeDTO;
import exercise.ExeManager;

public class DateSearchAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			HttpSession session = request.getSession(true); //세션값 불러오기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			
			System.out.print("\nDateSearchAction들어옴");
				
			String date = request.getParameter("inputdate");
			
			System.out.print("\n입력한 날짜: "+date+"\n");
			ExerListManager manager2 = ExerListManager.getInstance();
			List<ExerListDTO> ExerList = manager2.ExerInfoCheckDate((String)session.getAttribute("userid"), date);
			
			request.setAttribute("userExerList", ExerList);	
			
			dietListManager manager = dietListManager.getInstance();
			List<dietListDTO> dietList = manager.dietInfoCheckDate((String)session.getAttribute("userid"), date);
			
			request.setAttribute("userDietList", dietList);
			
			request.setAttribute("inputDate", date);
			
			//이동할 페이지를 결정.
			ActionForward forward = new ActionForward();
			forward.setPath("DateCheck.jsp");
					
			return forward;
		}

}

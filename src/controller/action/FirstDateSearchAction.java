package controller.action;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import dietList.dietListDTO;
import dietList.dietListManager;
import exerList.ExerListDTO;
import exerList.ExerListManager;

public class FirstDateSearchAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			HttpSession session = request.getSession(true); //세션값 불러오기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			
			System.out.print("\nFirstDateSearchAction들어옴");
				
			ExerListManager manager2 = ExerListManager.getInstance();
			List<ExerListDTO> ExerList = manager2.ExerInfoCheck((String)session.getAttribute("userid"));
			
			request.setAttribute("userExerList", ExerList);	
			
			dietListManager manager = dietListManager.getInstance();
			List<dietListDTO> dietList = manager.dietInfoCheck((String)session.getAttribute("userid"));
			
			request.setAttribute("userDietList", dietList);
			
			
			DecimalFormat df = new DecimalFormat("00");
			Calendar calendar = Calendar.getInstance();
			
			String year = Integer.toString(calendar.get(Calendar.YEAR)); //년도를 구한다
			String month = df.format(calendar.get(Calendar.MONTH) + 1); //달을 구한다
			String day = df.format(calendar.get(Calendar.DATE)); 
			String date = year+month+day;
			System.out.print("\n"+date);
			
			
			//이동할 페이지를 결정.
			ActionForward forward = new ActionForward();
			forward.setPath("DateCheck.jsp");
					
			return forward;
		}
}

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
			HttpSession session = request.getSession(true); //���ǰ� �ҷ�����!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			
			System.out.print("\nFirstDateSearchAction����");
				
			ExerListManager manager2 = ExerListManager.getInstance();
			List<ExerListDTO> ExerList = manager2.ExerInfoCheck((String)session.getAttribute("userid"));
			
			request.setAttribute("userExerList", ExerList);	
			
			dietListManager manager = dietListManager.getInstance();
			List<dietListDTO> dietList = manager.dietInfoCheck((String)session.getAttribute("userid"));
			
			request.setAttribute("userDietList", dietList);
			
			
			DecimalFormat df = new DecimalFormat("00");
			Calendar calendar = Calendar.getInstance();
			
			String year = Integer.toString(calendar.get(Calendar.YEAR)); //�⵵�� ���Ѵ�
			String month = df.format(calendar.get(Calendar.MONTH) + 1); //���� ���Ѵ�
			String day = df.format(calendar.get(Calendar.DATE)); 
			String date = year+month+day;
			System.out.print("\n"+date);
			
			
			//�̵��� �������� ����.
			ActionForward forward = new ActionForward();
			forward.setPath("DateCheck.jsp");
					
			return forward;
		}
}

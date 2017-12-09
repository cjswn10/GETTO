package controller.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDTO;
import user.UserManager;
import controller.ActionForward;
import dietList.dietListDTO;
import dietList.dietListManager;
import exerList.ExerListDTO;
import exerList.ExerListManager;
import exercise.ExeDTO;
import exercise.ExeManager;
import food.FoodDTO;
import food.FoodManager;

public class ExerSearchAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
			HttpSession session = request.getSession(true); //���ǰ� �ҷ�����!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			
			ExeManager manager = ExeManager.getInstance();
			ArrayList<ExeDTO> ExeList = manager.ExeList();
			
			//exeList��ü�� response�� �����Ͽ� ����.
			request.setAttribute("exeList", ExeList);		
				
			UserManager userManager = UserManager.getInstance();
			UserDTO user = userManager.findUser((String)session.getAttribute("userid"));
			
			request.setAttribute("user", user);	
			
			
			ExerListManager manager2 = ExerListManager.getInstance();
			List<ExerListDTO> ExerList = manager2.ExerInfoCheck((String)session.getAttribute("userid"));
			
			request.setAttribute("userExerList", ExerList);	
		
			//�̵��� �������� ����.
			ActionForward forward = new ActionForward();
			forward.setPath("ExerCheck.jsp");
					
			return forward;
		}
}

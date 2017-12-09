package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDTO;
import user.UserManager;
import controller.ActionForward;
import dietList.*;
import food.FoodDTO;
import food.FoodManager;

public class FoodSearchAction implements Action {

	/**
	 * UserManager�� list�޽�带 ȣ���Ͽ� 
	 * List�� response�� �����ϴ� �ҽ��ڵ尡 ����. 
	 * list.jsp���� response�� ����� List��ü�� �̿��Ѵ�.
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		HttpSession session = request.getSession(true); //���ǰ� �ҷ�����!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		String userId = (String)session.getAttribute("userid");
		FoodManager manager = FoodManager.getInstance();
		List<FoodDTO> FoodList = manager.findfoodList();
		
		UserManager userManager = UserManager.getInstance();
		UserDTO user = userManager.findUser(userId);
		
		request.setAttribute("user", user);	
		
		//foodList��ü�� response�� �����Ͽ� ����.
		request.setAttribute("foodList", FoodList);		
			
		dietListManager manager2 = dietListManager.getInstance();

		List<dietListDTO> dietList = manager2.dietInfoCheck(userId);
		
		request.setAttribute("userDietList", dietList);	
	
		//�̵��� �������� ����.
		ActionForward forward = new ActionForward();
		forward.setPath("DietCheck.jsp");
				
		return forward;
	}
}

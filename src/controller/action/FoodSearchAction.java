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
	 * UserManager의 list메써드를 호출하여 
	 * List를 response에 저장하는 소스코드가 들어간다. 
	 * list.jsp에서 response에 저장된 List객체를 이용한다.
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		HttpSession session = request.getSession(true); //세션값 불러오기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		String userId = (String)session.getAttribute("userid");
		FoodManager manager = FoodManager.getInstance();
		List<FoodDTO> FoodList = manager.findfoodList();
		
		UserManager userManager = UserManager.getInstance();
		UserDTO user = userManager.findUser(userId);
		
		request.setAttribute("user", user);	
		
		//foodList객체를 response에 저장하여 전달.
		request.setAttribute("foodList", FoodList);		
			
		dietListManager manager2 = dietListManager.getInstance();

		List<dietListDTO> dietList = manager2.dietInfoCheck(userId);
		
		request.setAttribute("userDietList", dietList);	
	
		//이동할 페이지를 결정.
		ActionForward forward = new ActionForward();
		forward.setPath("DietCheck.jsp");
				
		return forward;
	}
}

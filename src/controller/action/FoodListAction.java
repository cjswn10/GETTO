package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import food.FoodDTO;
import food.FoodManager;

public class FoodListAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		FoodManager manager = FoodManager.getInstance();
		List<FoodDTO> foodList = manager.findfoodList();
		System.out.print("foodList확인 : " + foodList.get(0).getFoodname());
		//userList객체를 response에 저장하여 전달.
		request.setAttribute("foodList", foodList);		
		
		//이동할 페이지를 결정.
		ActionForward forward = new ActionForward();
		forward.setPath("DietCheck.jsp");
				
		return forward;
	}
}
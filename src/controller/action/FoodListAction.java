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
		System.out.print("foodListȮ�� : " + foodList.get(0).getFoodname());
		//userList��ü�� response�� �����Ͽ� ����.
		request.setAttribute("foodList", foodList);		
		
		//�̵��� �������� ����.
		ActionForward forward = new ActionForward();
		forward.setPath("DietCheck.jsp");
				
		return forward;
	}
}
package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.ExistedUserException;
import user.UserDTO;
import user.UserManager;
import controller.ActionForward;
import food.FoodDTO;
import food.FoodManager;

public class FoodInsertAction implements Action {

	public ActionForward execute(
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		FoodDTO food = new FoodDTO();
		food.setFoodname(request.getParameter("foodname"));
		food.setFoodkcal(Integer.parseInt(request.getParameter("foodkcal")));
		food.setFoodDetail(request.getParameter("foodinfo"));
		System.out.print("test1");
		ActionForward forward = new ActionForward();
		FoodManager manager = FoodManager.getInstance();
		manager.insert(food);
		request.setAttribute("check", "ok");
		forward.setPath("adminMain.jsp");

			
		return forward;
	}

}
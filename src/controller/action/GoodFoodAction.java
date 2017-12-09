package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDTO;
import user.UserManager;
import controller.ActionForward;
import disease.DiseaseDTO;
import disease.DiseaseManager;
import exerList.ExerListManager;
import food.FoodDTO;
import food.FoodManager;
import goodfood.GoodDTO;
import goodfood.GoodManager;

public class GoodFoodAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession(true);
		
		UserManager userManager = UserManager.getInstance();
		GoodManager manager = GoodManager.getInstance();
		FoodManager manager2 = FoodManager.getInstance();
		DiseaseManager disManager = DiseaseManager.getInstance();
		
		int userDisId = userManager.findUserDisId(session.getAttribute("userid").toString());
		DiseaseDTO dis = disManager.findDisease(userDisId);
		request.setAttribute("disname", dis.getDisname());	

		int selectDisId = Integer.parseInt(request.getParameter("disid"));
		
		List<GoodDTO> goodFoodList = manager.RecFood(selectDisId);		
		List<FoodDTO> foodList = manager2.findGoodFoodList(goodFoodList);
		List<DiseaseDTO> diseaseList = disManager.findDiseaseList();
		
		//goodList객체를 response에 저장하여 전달.
				
		//request.setAttribute("disname", dis.getDisname());
		request.setAttribute("goodFoodList", goodFoodList);	
		request.setAttribute("foodList", foodList);
		request.setAttribute("diseaseList", diseaseList );

		//이동할 페이지를 결정.
		ActionForward forward = new ActionForward();
		forward.setPath("foodrecommend.jsp");
		
		return forward;
	}
}

package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserManager;
import food.FoodDTO;
import food.FoodManager;
import goodfood.GoodManager;
import goodfood.GoodDTO;
import controller.ActionForward;
import disease.DiseaseDTO;
import disease.DiseaseManager;

public class RecFoodAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(true);
		
		UserManager userManager = UserManager.getInstance();
		GoodManager manager = GoodManager.getInstance();
		FoodManager manager2 = FoodManager.getInstance();
		DiseaseManager disManager = DiseaseManager.getInstance();
		//여기서 disid를 받아와야해!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		int disId = userManager.findUserDisId(session.getAttribute("userid").toString());

		List<GoodDTO> userGoodList = manager.RecFood(disId);
		
		List<FoodDTO> foodList = manager2.findGoodFoodList(userGoodList);
		
		List<DiseaseDTO> diseaseList = disManager.findDiseaseList();
				
		//goodList객체를 response에 저장하여 전달.
		DiseaseDTO dis = disManager.findDisease(disId);
				
		request.setAttribute("disname", dis.getDisname());
		request.setAttribute("userGoodList", userGoodList);	
		request.setAttribute("foodList", foodList);
		request.setAttribute("diseaseList", diseaseList );

		//이동할 페이지를 결정.
		ActionForward forward = new ActionForward();
		forward.setPath("foodrecommend.jsp");
		
		
		return forward;
	}
}

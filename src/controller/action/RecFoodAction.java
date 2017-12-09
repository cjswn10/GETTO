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
		//���⼭ disid�� �޾ƿ;���!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		int disId = userManager.findUserDisId(session.getAttribute("userid").toString());

		List<GoodDTO> userGoodList = manager.RecFood(disId);
		
		List<FoodDTO> foodList = manager2.findGoodFoodList(userGoodList);
		
		List<DiseaseDTO> diseaseList = disManager.findDiseaseList();
				
		//goodList��ü�� response�� �����Ͽ� ����.
		DiseaseDTO dis = disManager.findDisease(disId);
				
		request.setAttribute("disname", dis.getDisname());
		request.setAttribute("userGoodList", userGoodList);	
		request.setAttribute("foodList", foodList);
		request.setAttribute("diseaseList", diseaseList );

		//�̵��� �������� ����.
		ActionForward forward = new ActionForward();
		forward.setPath("foodrecommend.jsp");
		
		
		return forward;
	}
}

package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import user.ExistedUserException;
import user.UserManager;
import controller.ActionForward;
import dietList.dietListDTO;
import dietList.dietListManager;
import food.FoodDTO;
import food.FoodManager;

public class DietListInsertAction implements Action{
	public ActionForward execute(
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

			System.out.println(request.getMethod());
			System.out.print("받는값확인:" + request.getParameter("userid") + "//"+request.getParameter("foodname") + " / " + request.getParameter("foodkcal")+  " / " +request.getParameter("eatdate") +" / " + request.getParameter("eattime"));
			if (request.getMethod().equals("GET")) {	// request an insert form
				ActionForward forward = new ActionForward();
				forward.setPath("DietCheck.jsp");
				forward.setRedirect(true);
				return forward;
		}
			HttpSession session = request.getSession(true); //세션값 불러오기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			String userid = (String)session.getAttribute("userid");
			String foodName = request.getParameter("foodname");
			dietListDTO dietList = new dietListDTO();
			dietList.setUserid(request.getParameter("userid"));
			if(foodName.equals("userChoice")) //직접입력의 경우 input 값을 가져옴
				foodName = request.getParameter("foodname2");
			dietList.setFoodname(foodName);
			dietList.setKcal(Integer.parseInt(request.getParameter("foodkcal")));
			dietList.setEatdate(request.getParameter("eatdate"));
			dietList.setEattime(request.getParameter("eattime"));
			
			int foodCheck = 1;
			
			ActionForward forward = new ActionForward();
		
			try {
				String check = "";
				dietListManager manager = dietListManager.getInstance();
				foodCheck = manager.insert(userid, dietList);
				if(foodCheck == 1)
					check = "정상";
				else
					check = "나쁨";
				request.setAttribute("foodCheck", check);
				request.setAttribute("foodname", foodName);
				FoodSearchAction foodSearchAction = new FoodSearchAction();
				forward = foodSearchAction.execute(request, response);
						
			} catch (Exception e) {e.getStackTrace();}	
			return forward;
		}
}

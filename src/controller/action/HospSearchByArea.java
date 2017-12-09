package controller.action;

import java.util.ArrayList;

import hospital.HospitalDTO;
import hospital.HospitalManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserManager;
import controller.ActionForward;
public class HospSearchByArea implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(request.getMethod());
		
		if (request.getMethod().equals("GET")) {
			ActionForward forward = new ActionForward();
			forward.setPath("HospitalSearch.jsp");
			forward.setRedirect(true);
			return forward;
			
		}
		
		HttpSession session = request.getSession(true); //세션값 불러오기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		String hosArea = request.getParameter("hospi");
		System.out.print("고객이 선택한 진료분야" + hosArea); 
		
		ActionForward forward = new ActionForward();
		try {
			HospitalManager hosManager = HospitalManager.getInstance();
			UserManager userManager = UserManager.getInstance();
			String userAddress = userManager.findUserAddress(session.getAttribute("userid").toString());
			ArrayList<HospitalDTO> hospitalList2 = hosManager.findHosAddressAndArea(userAddress, hosArea);
			
			for(HospitalDTO dto : hospitalList2){
				System.out.println("선택된 진료분야" + dto.getHosarea());
				System.out.println("선택된 주소" + dto.getHosaddress());
			}

			request.setAttribute("hospitalList2", hospitalList2);
			forward.setPath("HospitalSearch.m2?command=hospitalSearch");

					
		} catch (Exception e) {e.getStackTrace();}	
		return forward;
	}
	
	

}

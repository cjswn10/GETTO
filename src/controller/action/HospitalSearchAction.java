package controller.action;

import java.util.ArrayList;
import java.util.List;

import hospital.HospitalDTO;
import hospital.HospitalManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserManager;
import controller.ActionForward;
import disease.DiseaseDTO;
import disease.DiseaseManager;

public class HospitalSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		if (request.getMethod().equals("GET")) {
			ActionForward forward = new ActionForward();
			forward.setPath("HospitalSearch.jsp");
			forward.setRedirect(true);
			return forward;
	
		}
		
		UserManager userManager = UserManager.getInstance();
		HospitalManager hosManager1 = HospitalManager.getInstance();
		HospitalManager hosManager2 = HospitalManager.getInstance();
		String userAddress = userManager.findUserAddress(session.getAttribute("userid").toString());
		ArrayList<HospitalDTO> hospitalList = hosManager1.findHosAddress(userAddress);
		ArrayList<HospitalDTO> selectArea = hosManager2.findHosArea(userAddress);
 	
		System.out.print(selectArea.get(0));
		request.setAttribute("hospitalList", hospitalList);
		request.setAttribute("selectArea", selectArea);
		
		ActionForward forward = new ActionForward();
		forward.setPath("HospitalSearch.jsp");
		
		
		return forward;
	}

}

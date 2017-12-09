package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import disease.DiseaseDTO;
import disease.DiseaseManager;

public class DiseaseSearchAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(true);
		
		DiseaseManager disManager = DiseaseManager.getInstance();
		
		List<DiseaseDTO> diseaseList = disManager.findDiseaseList();
		
		request.setAttribute("diseaseList", diseaseList );
		
		ActionForward forward = new ActionForward();
		forward.setPath("user_write.jsp");
		
		return forward;
	
	}
}

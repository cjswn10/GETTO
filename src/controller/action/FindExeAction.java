package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDTO;
import user.UserManager;
import controller.ActionForward;
import dietList.dietListDTO;
import exercise.ExeDTO;
import exercise.ExeManager;

public class FindExeAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession(true); //세션값 불러오기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		System.out.println("findExeAction 실행");
		ExeManager manager = ExeManager.getInstance();
		List<ExeDTO> ExeList = manager.findExe();

		//foodList객체를 response에 저장하여 전달.
		System.out.println("manager.findExe완료");
		
		request.setAttribute("ExeList", ExeList);
		
		System.out.println("setAttribute완료");
		UserManager manager2 = UserManager.getInstance();
		UserDTO UserList = manager2.findBmi((String)session.getAttribute("userid"));
		
		request.setAttribute("UserList", UserList);	
		//이동할 페이지를 결정.
		ActionForward forward = new ActionForward();
		forward.setPath("ExerciseRecommend.jsp");
				
		return forward;
	}

}

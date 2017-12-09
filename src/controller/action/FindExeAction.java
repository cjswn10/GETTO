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
		
		HttpSession session = request.getSession(true); //���ǰ� �ҷ�����!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		System.out.println("findExeAction ����");
		ExeManager manager = ExeManager.getInstance();
		List<ExeDTO> ExeList = manager.findExe();

		//foodList��ü�� response�� �����Ͽ� ����.
		System.out.println("manager.findExe�Ϸ�");
		
		request.setAttribute("ExeList", ExeList);
		
		System.out.println("setAttribute�Ϸ�");
		UserManager manager2 = UserManager.getInstance();
		UserDTO UserList = manager2.findBmi((String)session.getAttribute("userid"));
		
		request.setAttribute("UserList", UserList);	
		//�̵��� �������� ����.
		ActionForward forward = new ActionForward();
		forward.setPath("ExerciseRecommend.jsp");
				
		return forward;
	}

}

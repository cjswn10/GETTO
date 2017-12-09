package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;


public interface Action {
	public ActionForward execute(
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception;
}

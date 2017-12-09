<%
	session = request.getSession();

	if ((session == null) || (session.getAttribute("userid") == null)) {
//		response.sendRedirect("login.m2?command=loginForm");
		response.sendRedirect("login.m2?command=login");
	}
%>
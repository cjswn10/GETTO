<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="java.util.*" %>
<%@page import="user.*" %>
<%@ include file="loginCheck.jsp" %>
<html>
<head>
<title>����� ����</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="../css/getto.css" type="text/css">
<script>
function dietList() {
	f.command.value = "foodSearch";
	f.target="main";
	f.action="DietCheck.m2";
	f.submit();
}

function exerList() {
	f.command.value = "exerSearch";
	f.target = "main";
	f.action = "ExerCheck.m2";
	f.submit();
}
function dateList() {
	f.command.value="firstdateSearch";
	f.target="main";
	f.action="DateCheck.m2";
	f.submit();
}
function logout() {	
	f.command.value = "logout";
	f.target="_top";
	f.action="login.jsp";
	f.submit();
}

function goUpdate(){
	f.command.value = "view";
	f.target = "main";
	f.action = "user_modify.m2";
	f.submit();
}

</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>

<form name="f" method="get">
<input type="hidden" name="command" value="foodSearch">
<input type="hidden" name="userid" value=<%=session.getAttribute("userid") %>>
<% String id = session.getAttribute("userid").toString(); %>
<table width=150 border=0 cellpadding=0 cellspacing=0 align="center">
<tr>
	<td colspan=2 ><%= session.getAttribute("userid")%>��<br>
	�ݰ����ϴ�!<br><br>
	</td>
</tr>
<tr>
	<td><input type="image" src="image/logout.jpg" width=60 value="�α׾ƿ�" onClick="logout()"></td>
	<td><input type="image" src="image/modify.jpg" width=60 value="�� ���� ����" onClick="goUpdate()"></td>
</tr>
</table>
<br><br><br>
<tr>
	<td height="100"><input type="image" src="image/yellowdiet.jpg" width=150 value="�Ľ��� ����" onClick="dietList()"></td>
</tr>
<br><br>
<tr>
	<td height="100"><input type="image" src="image/yellowexer.jpg" width=150 value="� ����" onClick="exerList()"></td>
</tr>
<br><br>
<tr>
	<td height="100"><input type="image" src="image/yellowdate.jpg" width=150 value="��¥�� ����Ʈ����" onClick="dateList()"></td>
</tr>
</table>
</form>
</body>
</html>

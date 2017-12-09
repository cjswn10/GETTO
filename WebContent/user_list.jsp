<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="java.util.*" %>
<%@page import="user.*" %>
<%@ include file="loginCheck.jsp" %>
<html>
<head>
<title>사용자 관리</title>
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
	<td colspan=2 ><%= session.getAttribute("userid")%>님<br>
	반갑습니다!<br><br>
	</td>
</tr>
<tr>
	<td><input type="image" src="image/logout.jpg" width=60 value="로그아웃" onClick="logout()"></td>
	<td><input type="image" src="image/modify.jpg" width=60 value="내 정보 수정" onClick="goUpdate()"></td>
</tr>
</table>
<br><br><br>
<tr>
	<td height="100"><input type="image" src="image/yellowdiet.jpg" width=150 value="식습관 내역" onClick="dietList()"></td>
</tr>
<br><br>
<tr>
	<td height="100"><input type="image" src="image/yellowexer.jpg" width=150 value="운동 내역" onClick="exerList()"></td>
</tr>
<br><br>
<tr>
	<td height="100"><input type="image" src="image/yellowdate.jpg" width=150 value="날짜별 리스트보기" onClick="dateList()"></td>
</tr>
</table>
</form>
</body>
</html>

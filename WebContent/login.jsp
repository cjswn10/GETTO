<%@page contentType="text/html; charset=euc-kr" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>건강을 GETTO -로그인</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="css/getto.css" type="text/css">
<script>

function userWrite() {
	//f.target="_blank";
	f.action = "user_write.m2";
	f.command.value = "diseaseSearch";
	f.submit();
}

function login() {
	if ( f.userid.value == "" ) {
		alert("사용자 아이디를 입력하십시요.");
		f.userid.focus();
		return false;
	} 
	if ( f.password.value == "" ) {
		alert("비밀번호를 입력하십시요.");
		f.password.focus();
		return false;
	}	
	
	f.command.value = "login";
	f.action = "login.m2";
	f.submit();
}
</script>

<style>
body {background:url(image/bg3.png); }
</style>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>

<c:if test="${not empty exception}">
<c:out value="${exception.getMessage()}" />
</c:if>
<center><center><br><br><br><img src="image/getto_logo.png" width="200"><br><br>
	<form name="f" method="post">
	  <input type="hidden" name="command"/>
	  <table border="0" cellpadding="0" cellspacing="1" width="300" bgcolor="BBBBBB">
		  <tr>
			<td><img src="image/id.jpg" width="110"></td>
			<td width=250 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:150" name="userid" value="">
			</td>
		  </tr>
		  <tr>
			<td><img src="image/pw.jpg" width="110"></td>
			<td width=250 bgcolor="ffffff" style="padding-left:10">
				<input type="password" style="width:150" name="password">
			</td>
		  </tr>
	  </table>
	  </form>

	  <table width=590 border=0 cellpadding=0 cellspacing=0>
		  <tr>
			<td align=center>
				<input type="image" src="image/login.jpg" width=70 value="로그인" onClick="login()"> &nbsp;
				<input type="image" src="image/userjoin.jpg" width=70 value="회원가입" onClick="userWrite()">
			</td>
		</tr>
	  </table>
</center>
</body>
</html>
<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="user.*" %>
<%@ include file="loginCheck.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
UserDTO user = (UserDTO)request.getAttribute("user");
%>
<html>
<head>
<title>사용자 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="../css/user.css" type="text/css">
<script>
function userModify() {
	
	if(f.userDisease.value=="default"){
		alert("질병을 선택해주세요");
		return false;
	}
	
	if ( f.password.value == "" ) {
		alert("비밀번호를 입력하십시요.");
		f.password.focus();
		return false;
	}
	
	if ( f.password2.value == "" ) {
		alert("비밀번호를 입력하십시요.");
		f.password.focus();
		return false;
	}

	f.command.value = "update";
	f.action = "user_modify.m2";
	f.submit();
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br><center>
<table width=780 border=0 cellpadding=0 cellspacing=0>
	<tr>
	  <td width="20"></td>
	  <td>
  <!--contents-->
	  <table width=590 border=0 cellpadding=0 cellspacing=0>
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>사용자 관리 - 사용자 추가</b></td>
		  </tr>
	  </table>  
	  <br>
	  
	  <!-- write Form  -->
	  <form name="f" method="post">
	  <input type="hidden" name="command"/>
	  <input type="hidden" name="userId" value="<%= session.getAttribute("userid")%>"/>	  
	  <table border="0" cellpadding="0" cellspacing="1" width="590" bgcolor="BBBBBB">
		  <tr>
			<td width=120 align=center bgcolor="E6ECDE" height="22">사용자 아이디</td>
			<td width=470 bgcolor="ffffff" style="padding-left:10">
				<%= session.getAttribute("userid")%>
			</td>
		  </tr>
 		  <tr>
			<td width=120 align=center bgcolor="E6ECDE" height="22">비밀번호</td>
			<td width=470 bgcolor="ffffff" style="padding-left:10">
				<input type="password" style="width:150" name="password" value="${user.password}">
			</td>
		  </tr> 
		  <tr>
			<td width=120 align=center bgcolor="E6ECDE" height="22">비밀번호 확인</td>
			<td width=470 bgcolor="ffffff" style="padding-left:10">
				<input type="password" style="width:150" name="password2" value="${user.password}">
			</td>
		  </tr>
		  <tr>
			<td width=120 align=center bgcolor="E6ECDE" height="22">이름</td>
			<td width=470 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:240" name="name" value="${user.name}">
			</td>
		  </tr>
		  <tr>
			<td width=120 align=center bgcolor="E6ECDE" height="22">주소</td>
			<td width=470 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:240" name="address1" value="${user.address1}">
			</td>
		  </tr>
		  <tr>
			<td width=120 align=center bgcolor="E6ECDE" height="22">상세 주소</td>
			<td width=470 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:240" name="address2" value="${user.address2}">
			</td>
		  </tr>
		  <tr>
			<td width=120 align=center bgcolor="E6ECDE" height="22">연락처</td>
			<td width=470 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:240" name="phone" value="${user.phone}">
			</td>
		  </tr>	
		  <tr>
			<td width=120 align=center bgcolor="E6ECDE" height="22">키</td>
			<td width=470 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:240" name="height" value="${user.height}"> cm
			</td>
		  </tr>	
		  <tr>
			<td width=120 align=center bgcolor="E6ECDE" height="22">몸무게</td>
			<td width=470 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:240" name="weight" value="${user.weight}"> kg
			</td>
		  </tr>	
		  <tr>
		  <td width=120 align=center bgcolor="E6ECDE" height="22">관심있는 병</td>
			<td width=470 bgcolor="ffffff" style="padding-left:10">
				<select name="userDisease">
				<option value="default">선택하세요</option>
				<c:forEach var="disease" items="${diseaseList}">
				<option value="${disease.disid}">${disease.disname}</option>
				</c:forEach></select>
			</td>
		  </tr>
	  </table>
	  </form>
	  <br>
	  
	  <table width=590 border=0 cellpadding=0 cellspacing=0>
		  <tr>
			<td align=center>
			<input type="image" src="image/modifyline.jpg" width=60 onClick="userModify()"> &nbsp;
			<input type="image" src="image/reset.jpg" width=60 value="뒤로" onClick="javascript:history.back()">
			</td>
		  </tr>
	  </table>

	  </td>
	</tr>
</table>  
</center>
</body>
</html>
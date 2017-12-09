<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="loginCheck.jsp" %>
<%@page import="user.*" %>
<%
	UserDTO user = (UserDTO)request.getAttribute("user");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel=stylesheet href="css/getto.css" type="text/css">
<title>Insert title here</title>
<script>
function foodSearch() {
	f.command.value = "foodSearch";
	f.action = "foodSearch.m2";
	f.submit();
}
</script>
</head>
<body><center>
<table>
<tr>
<%
String url = "";
String info = "";
if(user.getBmi() >= 25){ 
	url = "image/fat.png";
	info ="과체중입니다. 식습관을 조절하세요!";
}
else if (user.getBmi() >=20 && user.getBmi() < 24){
	url = "image/average.png";
	info ="정상체중입니다. 식습관을 유지하세요!";
}
else{
	url = "image/slim.png";
	info ="저체중입니다. 건강에 위험하니 주의하세요!";
}
%>
<td><img src="<%=url %>" height=500/><br><br><br>
내비만도 : <b>${user.bmi}</b>%<br>
<%=info %>
</td>
<td>
<center>
<%-- <input type="button" value="내 식습관 관리하기" onClick="foodSearch()">
<input type="button" value="내 운동 관리하기" onClick="location.href='DietCheck.jsp'"><br>
<input type="button" value="기간 별 건강지표 조회" onClick="location.href='DietCheck.jsp'"> --%>
</center>
</td>
</tr>
</table>
</center>
</body>
</html>
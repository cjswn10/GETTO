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
	info ="��ü���Դϴ�. �Ľ����� �����ϼ���!";
}
else if (user.getBmi() >=20 && user.getBmi() < 24){
	url = "image/average.png";
	info ="����ü���Դϴ�. �Ľ����� �����ϼ���!";
}
else{
	url = "image/slim.png";
	info ="��ü���Դϴ�. �ǰ��� �����ϴ� �����ϼ���!";
}
%>
<td><img src="<%=url %>" height=500/><br><br><br>
���񸸵� : <b>${user.bmi}</b>%<br>
<%=info %>
</td>
<td>
<center>
<%-- <input type="button" value="�� �Ľ��� �����ϱ�" onClick="foodSearch()">
<input type="button" value="�� � �����ϱ�" onClick="location.href='DietCheck.jsp'"><br>
<input type="button" value="�Ⱓ �� �ǰ���ǥ ��ȸ" onClick="location.href='DietCheck.jsp'"> --%>
</center>
</td>
</tr>
</table>
</center>
</body>
</html>
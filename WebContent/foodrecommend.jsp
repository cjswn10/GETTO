<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<%@page import="java.util.*" %>
<%@page import="disease.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	List<DiseaseDTO> diseaseList = (List<DiseaseDTO>)request.getAttribute("diseaseList");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel=stylesheet href="css/getto.css" type="text/css">
<script>
function foodSearch() {
	f.command.value = "goodFood";
	f.action = "foodrecommend.m2";
	f.submit();
}
</script>
<style>
body { font-size: 10pt; font-family: AvantGarde-BookOblique, 맑은 고딕; background:url(image/bg3.png); }
textarea{
border:none;
font-size:10pt;
color:#004a80;
border-collapse:collapse;
}

</style>
</head>
<body>
<h2 align=center>질병에 따른 음식 추천</h2>

<table align=center>
<tr align=top>

<td valign=top>
<table>
 <form name=userDisease method="post">
 <tr align=center height=30>
  <td>나의 관심있는 병 : </td>
  <td> ${disname} </td>
 </tr>
</form>
</table>
</td>

<td rowspan="2">
<table border=0 width=600 cellpadding=1>
 <form name=foodlist>
 <tr align=center height=30>
  <td bgcolor=lightgrey align=center>질병에 좋은 음식</td>
 </tr>
 <tr align=center>
  <td>
  <c:forEach var="food" items="${foodList}">
		${food.foodname} : ${food.foodDetail}<br>
  </c:forEach>	
  </td>
 </tr>
 </form>
</table>
</td>
</tr>

<tr>
	<td>	
<form name=f method="post">
<input type="hidden" name="command" value="goodFood">
 <table border=0 width=220>
 <tr align=center height=30>
  <td>병명 </td>
 <td><select name="disid">
<c:forEach var="disease" items="${diseaseList}">
		<option value="${disease.disid}">${disease.disname}</option>
</c:forEach>	
 </select></td>
 </tr>
 <tr>
  <td colspan=2 align="center">
  <%--<input type="submit"value="찾기" onClick="foodSearch()">
  --%><input type="image" src="image/search.jpg" width=60 value="찾기" onClick="foodSearch()"></td>
 </tr> 
 </table>
</form>
	
	</td>
</tr>
</table>

</body>
</html>

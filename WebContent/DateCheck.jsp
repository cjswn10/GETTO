<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="exerList.*" %>
<%@page import="dietList.*" %>
<%@ include file="loginCheck.jsp" %>
<%
	List<ExerListDTO> userExerList = (List<ExerListDTO>)request.getAttribute("userExerList");
	List<dietListDTO> userDietList = (List<dietListDTO>)request.getAttribute("userDietList");
%>
<script>
function findDate() {
	if (f.inputdate.value == "") {
		alert("��ȸ�� ��¥�� �Է��ϼ���.");
		f.inputdate.focus();
		return false;
	}
	if (f.inputdate.value.length != 8) {
		alert("��¥�� ���Ŀ� �°� �Է��ϼ���.");
		f.inputdate.focus();
		return false;
	}
	f.target="main";
	f.command.value="dateSearch";
	f.action="DateCheck.m2"
	f.submit();
}
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<center><h2>��¥�� ����Ʈ����</h2></center>

<br>
<center>
<form name="f" method="post">
<input type="hidden" name="command" value="dateSearch">
<input type="hidden" name="userid" value=<%=session.getAttribute("userid") %>>
 ��¥�Է�:&nbsp;<input type="text" name="inputdate" size="10" max="8">
 &nbsp;(ex:20141201)&nbsp;
 <input type="image" src="image/search.jpg" width=60 value="��ȸ" onClick="findDate()">
</form>
<%
String date = (String)request.getAttribute("inputDate");
if(date == null)
	date = "������ ����� & �Ľ��� ����";
else //date�� ���� ��� ���� �� (��¥�Է��� ��ȸ��)
	date = "�Է��� ��¥ " + date +" �� ����� & �Ľ�������";
%>
<br>
<h4><%=date %></h4>

</center>
<br>
<table border="0" align="center" width="500" cellpadding=0 cellspacing=0>
<tr>
 <td colspan=3 bgcolor=lightgrey>
 <center><h3>�����</h3></center>
 </td> 
 </tr>
 <tr>
  <th>��̸�</th>
 	<th>�ð�</th>
 	<th>�Ҹ�Į�θ�</th>
 </tr>
 
  <c:forEach var="exe" items="${userExerList}">
  <tr>
 	<td>${exe.exeName}</td>
 	<td>${exe.exeTime}</td>
 	<td>${exe.useKcal}</td>
 </tr>
 </c:forEach> 
 </table>

 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <table border="0" align="center" width="500" cellpadding=0 cellspacing=0>
 <tr>
  <td colspan=3 bgcolor=lightgrey>
   <center><h3>�Ľ��� ����</h3></center>
  </td>
 </tr>
 <tr>
  <th>����</th>
  <th>�ð�</th>
  <th>����Į�θ�</th>
 </tr>
 <c:forEach var="diet" items="${userDietList}">
 <tr>
 	<td>${diet.foodname}</td>
 	<td>${diet.eattime}</td>
 	<td>${diet.kcal}</td>		
 </tr>
 </c:forEach> 

</table>
</body>
</html>
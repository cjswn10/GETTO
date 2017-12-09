<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page import="hospital.*" %>
    <%@page import="java.util.*" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%
	ArrayList<HospitalDTO> hospitalSearchList = (ArrayList<HospitalDTO>)request.getAttribute("hospitalList");
 ArrayList<HospitalDTO> hospitalSearchByAreaList = (ArrayList<HospitalDTO>)request.getAttribute("hospitalList");

%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>병원검색</title>

<script>
function HospSearchByArea() {
	f.targat = "main";
	f.command.value="hospSearchByArea";
	f.action="HospitalSearch.m2";
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

<form name="f"  method="post">
<input type="hidden" name="command" value="hospSearchByArea">
<center><table width=1000>
<tr>
<td width="600"><h2>내 주변 병원</h2></td>
<td colspan="3"><h2>선택한 진료분야에 따른 주변 병원</h2></td>
</tr>
<tr valign="top">
<td rowspan="2">
<c:forEach var ="hos2" items="${hospitalList}">
병원이름  : ${hos2.hosname}<br>
병원주소 : ${hos2.hosaddress}  ${hos2.hosaddress2}<br>
병원 번호 : ${hos2.hosphone}<br><br>
</c:forEach>
</td>
<td>진료분야 :</td>
<td><select name="hospi">
<c:forEach var="hos1" items="${selectArea}">
	<option value="${hos1.hosarea}">${hos1.hosarea}</option>
</c:forEach>
</select></td>
<td><input type="image" src="image/search.jpg" width=60 value="찾기" onClick="HospSearchByArea()"></td>
</tr>
<tr valign="top">
<td colspan="3">
<c:forEach var ="hos3" items="${hospitalList2}">
병원이름  :  ${hos3.hosname}<br>
병원주소 : ${hos3.hosaddress}  ${hos3.hosaddress2}<br>
병원 번호 : ${hos3.hosphone}<br><br>
</c:forEach></td>
</tr>
</table>

</table></center>
</form>

</body>
</html>
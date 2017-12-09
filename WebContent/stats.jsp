<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="user.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
var p = new pie();
p.add("jan", 100);
p.add("feb", 200);
p.render("pieCanvas", "Pie Graph")
</script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<center>


<%
//비만도 기준 계산
String bmi = "";
Object objBmi = request.getAttribute("avgBmi");
double avgBmi = Double.parseDouble(objBmi.toString());
	if (avgBmi >= 25)
		bmi += "과체중";
	else if (avgBmi >= 20 && avgBmi < 24)
		bmi += "표준체중";
	else if (avgBmi < 19)
		bmi += "저체중";
%>
<h3>Getto 회원 통계</h3>
<form name="f">
회원들의 평균 비만도 : 약 ${avgBmi} % (<%=bmi%>)<br>
<img src="pie2.jsp">
<!-- 비만도와 연령대는 manager에서 계산한 뒤 반올림한 값으로 전달됨 -->
<br><br>
회원들의 평균 연령대 : 약 ${avgAge} 세<br>

<img src="pie.jsp">
</form>
</center>
</body>
</html>
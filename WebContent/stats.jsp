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
//�񸸵� ���� ���
String bmi = "";
Object objBmi = request.getAttribute("avgBmi");
double avgBmi = Double.parseDouble(objBmi.toString());
	if (avgBmi >= 25)
		bmi += "��ü��";
	else if (avgBmi >= 20 && avgBmi < 24)
		bmi += "ǥ��ü��";
	else if (avgBmi < 19)
		bmi += "��ü��";
%>
<h3>Getto ȸ�� ���</h3>
<form name="f">
ȸ������ ��� �񸸵� : �� ${avgBmi} % (<%=bmi%>)<br>
<img src="pie2.jsp">
<!-- �񸸵��� ���ɴ�� manager���� ����� �� �ݿø��� ������ ���޵� -->
<br><br>
ȸ������ ��� ���ɴ� : �� ${avgAge} ��<br>

<img src="pie.jsp">
</form>
</center>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%@page import="java.util.*" %>
 <%@ page import = "user.*" %>
 <%@ page import = "exercise.*" %>
 
 <%@ include file="loginCheck.jsp" %>
 <%
	UserDTO User = (UserDTO)request.getAttribute("UserList");
 	List<ExeDTO> Exercise = (List<ExeDTO>)request.getAttribute("ExeList");
 	ArrayList<ExeDTO> tmp = new ArrayList<ExeDTO>();
 	ExeDTO tmpDTO = new ExeDTO();
 	System.out.println("Exercise" + Exercise.get(0).getExename());
 	System.out.println("Bmi" + User.getBmi());
%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>� ��õ������ </title>
<script>
</script>
<style>
body { font-size: 10pt; font-family: AvantGarde-BookOblique, ���� ���; background:url(../images/bg3.png); }


textarea{
border:none;
font-size:10pt;
color:#004a80;
border-collapse:collapse;
}

</style>
</head>
<body>
<table align=center>
<tr>
<th>��̸� </th>
<th>�Ҹ�Į�θ�</th>
<th>� ���</th>
<th>��̹���</th>

<h3><center><%=User.getUserId() %>����  �񸸵�<b> <%= User.getBmi() %></b></h3>


<%	
	String bmi = "";
	int userBmi = User.getBmi();
	int i;
	String exeName = "";
	String EXEbmi = "";
	String exeway = "";
	int kcal = 0;
	
	
	if (userBmi >= 23)
		bmi = "��ü��";
	else if (userBmi >= 19 && userBmi < 23)
		bmi = "ǥ��ü��";
	else if (userBmi <= 18)
		bmi = "��ü��";
	
	if (bmi.equals("��ü��"))
		%><h3>��ü�� ��õ� �Դϴ�.<br><br></h3> <%
	if (bmi.equals("ǥ��ü��"))
		%><h3>ǥ��ü�� ��õ� �Դϴ�.<br><br></h3><%
	if (bmi.equals("��ü��"))
		%><h3>��ü�� ��õ� �Դϴ�.<br><br></h3><%

	Iterator<ExeDTO> ExeIter = Exercise.iterator();
	
	while (ExeIter.hasNext() ) {
		ExeDTO exercise1 = (ExeDTO)ExeIter.next();
		
		exeName = exercise1.getExename();
		kcal = exercise1.getExekcal();
		EXEbmi = exercise1.getBmi();
		exeway = exercise1.getExeway();
		
		if (bmi.equals("��ü��") && EXEbmi.equals("��ü��"))
			tmp.add(exercise1);
		
		if (bmi.equals("ǥ��ü��") && EXEbmi.equals("ǥ��ü��"))
				tmp.add(exercise1);
		
		if (bmi.equals("��ü��") && EXEbmi.equals("��ü��"))
				tmp.add(exercise1);
		
	
	}

	for(i= 0; i < tmp.size(); i++) {
	
		%>
		<tr><th><%= tmp.get(i).getExename() %></th>
		<th><%= tmp.get(i).getExekcal() %>Kcal</th>
		<th><%= tmp.get(i).getExeway()  %></th>
		<td><img src="<%=tmp.get(i).getExepicture() %>" /></tr>
		
		<%} %>
				
</td>

</tr>
</table>

</body>
</html>
<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="exerList.*" %>
<%@page import="exercise.*" %>
<%@page import="user.*" %>
<%@ include file="loginCheck.jsp" %>
<%
	List<ExeDTO> exeList = (List<ExeDTO>)request.getAttribute("exeList");
	List<ExerListDTO> userExerList = (List<ExerListDTO>)request.getAttribute("userExerList");
	UserDTO user = (UserDTO)request.getAttribute("user");
	
	System.out.print(exeList.get(0).getExename()+"\n");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>

function back() {
	f.target="main";
	f.command.value="mainSearch";
	f.action = "main.m2";
	f.submit();
}
function userExerCreate() {
	if(f.usekcal.value == ""){
		alert("�Һ��� Į�θ��� �Է��ϼ���.");
		f.usekcal.focus();
		return false;
	}
	if(f.exedate.value == ""){
		alert("��� ��¥�� �Է��ϼ���.");
		f.exedate.focus();
		return false;
	}
	if(f.exetime.value == ""){
		alert("��� �ð��� �Է��ϼ���.");
		f.exetime.focus();
		return false;
	}
	if(f.exename.value == "userChoice" && f.exename2.value == ""){
		alert("�����Է��� ��� � �̸��� �Է����ּ���.");
		f.exename2.focus();
		return false;
	}
	if(f.exedate.value.length != 8){
		alert("��� ��¥�� ���Ŀ� �°� ���ּ���.");
		f.eatdate.focus();
		return false;
	}
	if(f.exename.value != "userChoice" && f.exename2.value != ""){
		alert("�����Է� �ÿ��� ��̸� �Է¶��� �̿����ּ���.");
		f.foodname2.focus();
		return false;
	}
	f.command.value = "exerlistinsert";
	f.action = "ExerCheck.m2";
	f.submit();
}

</script>
<style>
body { font-size: 10pt; font-family: AvantGarde-BookOblique, ���� ���; background:url(image/bg3.png); }

textarea{
border:none;
font-size:10pt;
color:#004a80;
border-collapse:collapse;
}

</style>
</head>

<body>
<table>
<%

System.out.print(user.getBmi());

String uri = "";
String info = "";

if(user.getBmi() >= 25){ 
	uri = "image/fat.png";
	info ="��ü���Դϴ�. ��� ������ �ϼ���!";
}
else if (user.getBmi() >=20 && user.getBmi() < 24){
	uri = "image/average.png";
	info ="����ü���Դϴ�. ������ ����� ���� �����ϼ���!";
}
else{
	uri = "image/slim.png";
	info ="��ü���Դϴ�. �ǰ��� ���� ��� ������ �ϼ���!";
}
%>


<%int totalKcal = 0;
for (ExerListDTO list : userExerList) {
	totalKcal += list.getUseKcal();
}
%>
<tr><td align="center"><img src="<%=uri %>"/><br><br><br>
���񸸵� : <b>${user.bmi}</b>%<br>
<%=info %>
</td>
<td>
<center>
<h2>� ���� ����</h2>

<br><br></center>
<form name="f" method="post" >
<input type="hidden" name="command" value="exerlistinsert">
<input type="hidden" name="userid" value=<%=session.getAttribute("userid") %>>
<table border="0" align="center" bgcolor="white" width="580">
<tr>
 <th>� �̸�</th>
	 
 <td colspan="2">
 <select name="exename">
 <option value="userChoice">�����Է�</option>
	<c:forEach var="exe" items="${exeList}">
			<option value="${exe.exename}">${exe.exename} (30�� ��  ${exe.exekcal}kcal)</option>		
	</c:forEach> 
 </select>
 <input type="text" name="exename2"  maxlength="20" size="10"></td>
	 

</tr>
<tr>
 <th>�Һ�Į�θ�</th>
 <td><input type="text" name="usekcal" maxlength="20" size="10">kcal

</td>
</tr>
<tr>
 <th>��¥/�ð�</th>
 <td><input type="text" name="exedate" maxlength="20" size="10">(��:20141114)</td>
 <td><select name="exetime">
 <option value="30��">30��</option>
 <option value="1�ð�">1�ð�</option>
 <option value="1�ð� 30��">1�ð� 30��</option>
 <option value="2�ð�">2�ð�</option>
 <option value="2�ð� 30��">2�ð� 30��</option>
 <option value="3�ð�">3�ð�</option>
 <option value="3�ð� 30��">3�ð� 30��</option>
 <option value="4�ð�">4�ð�</option>
 <option value="4�ð� 30��">4�ð� 30��</option>
 <option value="5�ð�">5�ð�</option>
 </select></td>
</tr>	  	
<tr align="center">
 <td colspan="3"><input type="image" src="image/register.jpg" width=80 value="����ϱ�" onClick="userExerCreate()"></td></tr>
</table>
</form>
<hr>
<center>
<h2>���� �Һ��� Į�θ�</h2>

<table border="0" align="center" bgcolor="white" width="580">
<tr>
 <th>��ȣ</th>
 <th>�</th>
 <th>�ð�</th>
 <th>Į�θ�</th>
</tr>

<%int i = 1; %>
<c:forEach var="exer" items="${userExerList}">
 <tr align=center>
 <td><%=i %></td>
 <td>${exer.exeName}</td>
 <td>${exer.exeTime}</td>
 <td>${exer.useKcal}</td>
 </tr>
 <%i++; %>
</c:forEach>


</table>
���� �Һ��� �� Į�θ� :<%=totalKcal %>Kcal</center>

</td>
</tr>
<tr>
<td></td>
<td align="center"><input type="image" src="image/undo.jpg" width=80 value="�ڷΰ���" onClick="back()"></td>
</tr>
</table>


</body>
</html>
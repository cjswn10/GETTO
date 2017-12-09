<%@page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="food.*" %>
<%@page import="dietList.*" %>
<%@page import="user.*" %>
<%@ include file="loginCheck.jsp" %>
<%
	List<FoodDTO> foodList = (List<FoodDTO>)request.getAttribute("foodList");
	List<dietListDTO> userDietList = (List<dietListDTO>)request.getAttribute("userDietList");
	UserDTO user = (UserDTO)request.getAttribute("user");
	String check = (String)request.getAttribute("foodCheck");
%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/getto.css" type="text/css">

<script>
function back() {
	f.target="main";
	f.command.value="mainSearch";
	f.action = "main.m2";
	f.submit();
}

function userDietCreate() {
	if(f.foodkcal.value == ""){
		alert("������ Į�θ��� �Է��ϼ���.");
		f.foodkcal.focus();
		return false;
	}
	if(f.eatdate.value == ""){
		alert("������ ��¥�� �Է��ϼ���.");
		f.eatdate.focus();
		return false;
	}
	if(f.eattime.value == ""){
		alert("������ �ð��� �Է��ϼ���.");
		f.eattime.focus();
		return false;
	}
	
	if(f.foodname.value == "userChoice" && f.foodname2.value == ""){
		alert("�����Է��� ��� ���� �̸��� �Է����ּ���.");
		f.foodname2.focus();
		return false;
	}
	
	if(f.eatdate.value.length != 8){
		alert("������ ��¥�� ���Ŀ� �°� ���ּ���.");
		f.eatdate.focus();
		return false;
	}
	
	if(f.foodname.value != "userChoice" && f.foodname2.value != ""){
		alert("�����Է� �ÿ��� ���� �Է¶��� �̿����ּ���.");
		f.foodname2.focus();
		return false;
	}
	f.command.value = "dietlistinsert";
	f.action = "DietCheck.m2";
	f.submit();
}

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
<%int totalKcal = 0;
for(dietListDTO list : userDietList)
	totalKcal += list.getKcal();
if("����".equals(check)){
%>
<script>alert("�����ִ� ���� ���������Դϴ� �Ф� \n �����ϼ���!")</script>
<%} %>
<%
int dailyKcal = 2500; //���� �Ϸ� ���� ���뷮
if(user.getGender().equals("women")) //���� �Ϸ� ���� ���뷮
	dailyKcal = 2000;

if(check!=null&&dailyKcal<totalKcal){ //���ĵ���� �� �����̰�, ����Į�θ��� ���差�� �ʰ��������!
%>
<script>alert("���� �Ϸ� ���� ���뷮�� �ʰ��߽��ϴ�!! \n �񸸿� �����ϼ���!")</script>
<%} %>
<table>
<%
String uri = "";
String info = "";
if(user.getBmi() >= 25){ 
	uri = "image/fat.png";
	info ="��ü���Դϴ�. �Ľ����� �����ϼ���!";
}
else if (user.getBmi() >=20 && user.getBmi() < 24){
	uri = "image/average.png";
	info ="����ü���Դϴ�. �Ľ����� �����ϼ���!";
}
else{
	uri = "image/slim.png";
	info ="��ü���Դϴ�. �ǰ��� �����ϴ� �����ϼ���!";
}
%>


<tr><td align="center"><img src="<%=uri %>"/><br><br><br>
���񸸵� : <b>${user.bmi}</b>%<br>
<%=info %>

</td>
<td>
<center>
<h2>�Ľ��� ���� ����</h2>

<br><br></center>
<form name="f" method="post" >
<input type="hidden" name="command" value="dietlistinsert">
<input type="hidden" name="userid" value=<%=session.getAttribute("userid") %>>
<table border="0"  width="580">

<tr>
 <th>���� ���� ����</th>
 <td colspan="2"><select name="foodname">
 <option value="userChoice">�����Է�</option>
<c:forEach var="food" items="${foodList}">
		<option value="${food.foodname}">${food.foodname} (100g ��  ${food.foodkcal}kcal)</option>		
</c:forEach>
 </select> <input type="text" name="foodname2"  maxlength="20" size="10"></td>
 
</tr>
<tr>
 <th>Į�θ�</th>

 <td><input type="text" name="foodkcal" maxlength="20" size="10">kcal 
 
</td>

</tr>

<tr>
 <th>��¥/�ð�</th>
 <td colspan="2"><input type="text" name="eatdate" maxlength="20" size="10">(��:20141114) 
 <select name="eattime">
 <option value="��ħ">��ħ</option>
 <option value="����">����</option>
 <option value="����">����</option>
 <option value="����">����</option>
 </select></td>
</tr>	  	
<tr align="center">
 <td colspan="3"><input type="image" src="image/register.jpg" width=80 value="����ϱ�" onClick="userDietCreate()"></td></tr>
</table>

</form>
<hr>
<center>
<h2>���� ���� ����</h2>

<table border="0" align="center" bgcolor="white" width="580">
<tr>
 <th>��ȣ</th>
 <th>����</th>
 <th>�ð�</th>
 <th>Į�θ�</th>
</tr>
<%
	int i = 1;
%>
<!-- ȸ���� ������ �Ľ��� List ��� �ڵ� -->
<c:forEach var="dietInfo" items="${userDietList}">
		<tr align="center">
			<td><%=i%></td>
			<td>${dietInfo.foodname}</td>
			<td>${dietInfo.eattime}</td>
			<td>${dietInfo.kcal}</td>
		</tr>	
<%
	i++; 
%>
</c:forEach>


</table>
<br>
���� ������ �� Į�θ�  : <b><%=totalKcal%></b>Kcal</center>

</td>
</tr>
<tr>
<td></td>
<td align="center"><input type="image" src="image/undo.jpg" width=80 value="�ڷΰ���" onClick="back()"></td>
</tr>
</table>


</body>
</html>
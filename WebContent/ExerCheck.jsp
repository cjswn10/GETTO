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
		alert("소비한 칼로리를 입력하세요.");
		f.usekcal.focus();
		return false;
	}
	if(f.exedate.value == ""){
		alert("운동한 날짜를 입력하세요.");
		f.exedate.focus();
		return false;
	}
	if(f.exetime.value == ""){
		alert("운동한 시간을 입력하세요.");
		f.exetime.focus();
		return false;
	}
	if(f.exename.value == "userChoice" && f.exename2.value == ""){
		alert("직접입력의 경우 운동 이름을 입력해주세요.");
		f.exename2.focus();
		return false;
	}
	if(f.exedate.value.length != 8){
		alert("운동한 날짜를 형식에 맞게 써주세요.");
		f.eatdate.focus();
		return false;
	}
	if(f.exename.value != "userChoice" && f.exename2.value != ""){
		alert("직접입력 시에만 운동이름 입력란을 이용해주세요.");
		f.foodname2.focus();
		return false;
	}
	f.command.value = "exerlistinsert";
	f.action = "ExerCheck.m2";
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
<table>
<%

System.out.print(user.getBmi());

String uri = "";
String info = "";

if(user.getBmi() >= 25){ 
	uri = "image/fat.png";
	info ="과체중입니다. 운동을 열심히 하세요!";
}
else if (user.getBmi() >=20 && user.getBmi() < 24){
	uri = "image/average.png";
	info ="정상체중입니다. 적당한 운동으로 몸을 유지하세요!";
}
else{
	uri = "image/slim.png";
	info ="저체중입니다. 건강을 위해 운동을 열심히 하세요!";
}
%>


<%int totalKcal = 0;
for (ExerListDTO list : userExerList) {
	totalKcal += list.getUseKcal();
}
%>
<tr><td align="center"><img src="<%=uri %>"/><br><br><br>
내비만도 : <b>${user.bmi}</b>%<br>
<%=info %>
</td>
<td>
<center>
<h2>운동 내역 관리</h2>

<br><br></center>
<form name="f" method="post" >
<input type="hidden" name="command" value="exerlistinsert">
<input type="hidden" name="userid" value=<%=session.getAttribute("userid") %>>
<table border="0" align="center" bgcolor="white" width="580">
<tr>
 <th>운동 이름</th>
	 
 <td colspan="2">
 <select name="exename">
 <option value="userChoice">직접입력</option>
	<c:forEach var="exe" items="${exeList}">
			<option value="${exe.exename}">${exe.exename} (30분 당  ${exe.exekcal}kcal)</option>		
	</c:forEach> 
 </select>
 <input type="text" name="exename2"  maxlength="20" size="10"></td>
	 

</tr>
<tr>
 <th>소비칼로리</th>
 <td><input type="text" name="usekcal" maxlength="20" size="10">kcal

</td>
</tr>
<tr>
 <th>날짜/시간</th>
 <td><input type="text" name="exedate" maxlength="20" size="10">(예:20141114)</td>
 <td><select name="exetime">
 <option value="30분">30분</option>
 <option value="1시간">1시간</option>
 <option value="1시간 30분">1시간 30분</option>
 <option value="2시간">2시간</option>
 <option value="2시간 30분">2시간 30분</option>
 <option value="3시간">3시간</option>
 <option value="3시간 30분">3시간 30분</option>
 <option value="4시간">4시간</option>
 <option value="4시간 30분">4시간 30분</option>
 <option value="5시간">5시간</option>
 </select></td>
</tr>	  	
<tr align="center">
 <td colspan="3"><input type="image" src="image/register.jpg" width=80 value="등록하기" onClick="userExerCreate()"></td></tr>
</table>
</form>
<hr>
<center>
<h2>오늘 소비한 칼로리</h2>

<table border="0" align="center" bgcolor="white" width="580">
<tr>
 <th>번호</th>
 <th>운동</th>
 <th>시간</th>
 <th>칼로리</th>
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
오늘 소비한 총 칼로리 :<%=totalKcal %>Kcal</center>

</td>
</tr>
<tr>
<td></td>
<td align="center"><input type="image" src="image/undo.jpg" width=80 value="뒤로가기" onClick="back()"></td>
</tr>
</table>


</body>
</html>
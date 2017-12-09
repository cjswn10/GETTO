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
		alert("섭취한 칼로리를 입력하세요.");
		f.foodkcal.focus();
		return false;
	}
	if(f.eatdate.value == ""){
		alert("섭취한 날짜를 입력하세요.");
		f.eatdate.focus();
		return false;
	}
	if(f.eattime.value == ""){
		alert("섭취한 시간을 입력하세요.");
		f.eattime.focus();
		return false;
	}
	
	if(f.foodname.value == "userChoice" && f.foodname2.value == ""){
		alert("직접입력의 경우 음식 이름을 입력해주세요.");
		f.foodname2.focus();
		return false;
	}
	
	if(f.eatdate.value.length != 8){
		alert("섭취한 날짜를 형식에 맞게 써주세요.");
		f.eatdate.focus();
		return false;
	}
	
	if(f.foodname.value != "userChoice" && f.foodname2.value != ""){
		alert("직접입력 시에만 음식 입력란을 이용해주세요.");
		f.foodname2.focus();
		return false;
	}
	f.command.value = "dietlistinsert";
	f.action = "DietCheck.m2";
	f.submit();
}

</script>

<style>
body { font-size: 10pt; font-family: AvantGarde-BookOblique, 맑은 고딕; background:url(../images/bg3.png); }
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
if("나쁨".equals(check)){
%>
<script>alert("관심있는 병에 나쁜음식입니다 ㅠㅠ \n 유의하세요!")</script>
<%} %>
<%
int dailyKcal = 2500; //남성 하루 권장 섭취량
if(user.getGender().equals("women")) //여성 하루 권장 섭취량
	dailyKcal = 2000;

if(check!=null&&dailyKcal<totalKcal){ //음식등록을 한 이후이고, 오늘칼로리가 권장량을 초과했을경우!
%>
<script>alert("오늘 하루 권장 섭취량을 초과했습니다!! \n 비만에 유의하세요!")</script>
<%} %>
<table>
<%
String uri = "";
String info = "";
if(user.getBmi() >= 25){ 
	uri = "image/fat.png";
	info ="과체중입니다. 식습관을 조절하세요!";
}
else if (user.getBmi() >=20 && user.getBmi() < 24){
	uri = "image/average.png";
	info ="정상체중입니다. 식습관을 유지하세요!";
}
else{
	uri = "image/slim.png";
	info ="저체중입니다. 건강에 위험하니 주의하세요!";
}
%>


<tr><td align="center"><img src="<%=uri %>"/><br><br><br>
내비만도 : <b>${user.bmi}</b>%<br>
<%=info %>

</td>
<td>
<center>
<h2>식습관 내역 관리</h2>

<br><br></center>
<form name="f" method="post" >
<input type="hidden" name="command" value="dietlistinsert">
<input type="hidden" name="userid" value=<%=session.getAttribute("userid") %>>
<table border="0"  width="580">

<tr>
 <th>오늘 먹은 음식</th>
 <td colspan="2"><select name="foodname">
 <option value="userChoice">직접입력</option>
<c:forEach var="food" items="${foodList}">
		<option value="${food.foodname}">${food.foodname} (100g 당  ${food.foodkcal}kcal)</option>		
</c:forEach>
 </select> <input type="text" name="foodname2"  maxlength="20" size="10"></td>
 
</tr>
<tr>
 <th>칼로리</th>

 <td><input type="text" name="foodkcal" maxlength="20" size="10">kcal 
 
</td>

</tr>

<tr>
 <th>날짜/시간</th>
 <td colspan="2"><input type="text" name="eatdate" maxlength="20" size="10">(예:20141114) 
 <select name="eattime">
 <option value="아침">아침</option>
 <option value="점심">점심</option>
 <option value="저녁">저녁</option>
 <option value="간식">간식</option>
 </select></td>
</tr>	  	
<tr align="center">
 <td colspan="3"><input type="image" src="image/register.jpg" width=80 value="등록하기" onClick="userDietCreate()"></td></tr>
</table>

</form>
<hr>
<center>
<h2>오늘 먹은 음식</h2>

<table border="0" align="center" bgcolor="white" width="580">
<tr>
 <th>번호</th>
 <th>음식</th>
 <th>시간</th>
 <th>칼로리</th>
</tr>
<%
	int i = 1;
%>
<!-- 회원의 오늘자 식습관 List 출력 코드 -->
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
오늘 섭취한 총 칼로리  : <b><%=totalKcal%></b>Kcal</center>

</td>
</tr>
<tr>
<td></td>
<td align="center"><input type="image" src="image/undo.jpg" width=80 value="뒤로가기" onClick="back()"></td>
</tr>
</table>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="css/getto.css" type="text/css">
<script>
function exerciseRecommend() {
	f.command.value = "bmiSearch";
	f.target="main";
	f.action = "ExerciseRecommend.m2";
	f.submit();
}

function recFood() {
	f.command.value = "recFood";
	f.target="main";
	f.action="foodrecommend.m2";
	f.submit();	
}
function hospitalSearch() {
	f.command.value = "hospitalSearch";
	f.target = "main";
	f.action = "HospitalSearch.m2";
	f.submit();
}
function statsCheck(){
	f.command.value = "statsCheck";
	f.target="main";
	f.action="stats.m2";
	f.submit();
}
</script>
</head>
<style type="text/css">
body{
	background-image:url("image/bg.png");
	background-repeat:repeat;
	background-attachment:scroll;
   }
img {border:none}
a {color:black;
font-size:12px;
text-decoration:none;
font-weight:bold;
}
a:link {color:black;
font-size:12px;
text-decoration:none;
font-weight:bold;
}
</style>
<body>
<CENTER>
<form name="f" method="post">
<input type="hidden" name="command" value="bmiSearch">
<input type="hidden" name="userid" value="<%session.getAttribute("userId"); %>">

<table border=0 cellpadding=0 align="center">
<tr align="center">
<td>
<img src="image/getto_logo.png" width="200"></td>
</tr>
</table>
<hr style="color:#999999;border-style:dotted">
<table  width=1000>
<tr align="center"><td>
<td><input type="image" src="image/recfood.jpg" width=120 value="음식 추천" onClick="recFood()"></td>
<td><input type="image" src="image/recexer.jpg" width=120 value="운동추천" onClick="exerciseRecommend()"></td>
<td><input type="image" src="image/searchhos.jpg" width=120 value="병원 검색" onClick="hospitalSearch()"></td> 
<td><input type="image" src="image/stats.jpg" width=120 value="통계조회" onClick="statsCheck()"></td></font>
</tr>
</form>
</CENTER>
</body>
</html>
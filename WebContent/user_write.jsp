<%@page contentType="text/html; charset=euc-kr" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>사용자 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="css/getto.css" type="text/css">
<script>
function userCreate() {
	if ( f.userid.value == "" ) {
		alert("사용자 아이디를 입력하십시요.");
		f.userid.focus();
		return false;
	} 
	if ( f.password.value == "" ) {
		alert("비밀번호를 입력하십시요.");
		f.password.focus();
		return false;
	}
	if ( f.name.value == "" ) {
		alert("이름을 입력하십시요.");
		f.name.focus();
		return false;
	}
	
	f.command.value = "insert";
	f.action="user_write.m2";
	f.submit();
}

</script>
</head>

<style>
body {background:url(image/bg3.png); }
h2 {
font-size:60px;
}
</style>

<body>
<center>
 <table>
		  <tr>
			<td align="center"><img src="image/user_write.png"></td>
		  </tr>
 </table> 
</center>	   	  
	  <c:if test="${not empty exception}">
	  	<c:out value="${exception.getMessage()}" />
	  </c:if>
	  
	  <!-- write Form  -->
	  <form name="f" method="post">
	  <input type="hidden" name="command"/>
	  <table border="0" cellpadding="0" cellspacing="1" align="center" bgcolor="white" width="420">
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">사용자 아이디</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:150" name="userid">
			</td>
		  </tr>
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">비밀번호</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="password" style="width:150" name="password">
			</td>
		  </tr>
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">비밀번호 확인</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="password" style="width:150" name="password2">
			</td>
		  </tr>
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">이름</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:150" name="name">
			</td>
		  </tr>
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">주소</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:150" name="address1"> 예)서울시 성북구
			</td>
		  </tr>	
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">상세주소</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:240" name="address2">
			</td>
		  </tr>		  
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">연락처</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:150" name="phone">
			</td>
		  </tr>	
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">성별</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="radio"name="gender" value="men" > 남
				<input type="radio"name="gender" value="women"> 여
			</td>
		  </tr>	
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">생년월일</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:150" name="birthday"> 예)19930303
			</td>
		  </tr>	
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">키</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:100" name="height"> cm
			</td>
		  </tr>	
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">몸무게</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:100" name="weight"> kg
			</td>
		  </tr>	
		  <tr height="35">
		  <td width=120 align=center bgcolor="d8d4d4">관심있는 병</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<select name="userDisease">
				<c:forEach var="disease" items="${diseaseList}">
				<option value="${disease.disid}">${disease.disname}</option>
				</c:forEach></select>
			</td>
		  </tr>
	  </table>
	  </form>
	  <br>
	  
	  <table width=590 border=0 cellpadding=0 cellspacing=0 align="center">
		  <tr>
			<td align=center>
			<input type="image" src="./image/join.jpg" width=60 value="회원 가입" onClick="userCreate()"> &nbsp;
			<input type="image" src="./image/reset.jpg" width=60 value="취소" onClick="f.reset()">
			</td>
		  </tr>
	  </table>

	  </td>
	</tr>
</table>  

</body>
</html>
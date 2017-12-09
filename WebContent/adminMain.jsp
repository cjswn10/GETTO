<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<script>
function foodCreate() {
	f.targat = "main";
	f.command.value="insertFood";
	f.action="adminMain.m2";
	f.submit();
}

function searchUserList(){
	f.target="main";
	f.command.value="searchUserList";
	f.action="admin_userList.m2";
	f.submit();
}
</script>
</head>

<body>
<center>
<%
String check = (String)request.getAttribute("check");
if("ok".equals(check)){
%>
<script>alert("등록되었습니다");</script>
<%} %>
<form name="f" method="post" >
<input type="hidden" name="command" value="dietlistinsert">
<input type="hidden" name="userid" value=<%=session.getAttribute("userid") %>>
<table>
<tr>
<td>
<table>
<tr><th colspan="2">음식등록</th></tr>
<tr>
 <th>음식이름</th>

 <td><input type="text" name="foodname" maxlength="20" size="10">
 
</td>

</tr>
<tr>
 <th>100g당 칼로리</th>
 <td><input type="text" name="foodkcal" maxlength="20" size="10">kcal
 
</td>

</tr>
<tr>
 <th>음식설명</th>
 <td colspan="2">
 <textarea name="foodinfo" maxlength="300" size="10"></textarea>
 </td>
</tr>	  	
<tr align="center">
 <td colspan="2"><input type="image" src="image/register.jpg" width=80 value="등록하기" onClick="foodCreate()"></td></tr>
 
</table>
</td>
<td align="center" width="250">
<input type="image" src="image/user_view.jpg" width=80 value="회원 보기" onClick="searchUserList()">
</td>
</tr>
</table>
</form>
</center>
</body>
</html>
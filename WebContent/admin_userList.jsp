<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form name="f" method="get" action="user_write.m2">

<table width=780 border=0 cellpadding=0 cellspacing=0>

<tr>
	<td width="20"></td>
	<td>
	  	<table align="center" width=590 border=0 cellpadding=0 cellspacing=0>
		  	<tr>
				<td bgcolor="lightgrey" height="22">&nbsp;&nbsp;<b>사용자 관리 - 리스트</b></td>
		  	</tr>
	  	</table>  
	  	<br>
	  
	  	<table border="0" cellpadding="0" cellspacing="1" width="800" bgcolor="BBBBBB">
		  	<tr align = center bgcolor="ffe322">
				<td >사용자 아이디</td>
				<td>이름</td>
				<td>주소</td>
				<td>bmi</td>
				<td>회원정보수정</td>
		  	</tr>

		<c:forEach var="user" items="${userList}">  			  	
		  	<tr align = center>
				<td align=center bgcolor="ffffff" height="20">
					${user.userId}
				</td>
				<td bgcolor="ffffff" style="padding-left:10">					
						${user.name}
				
				</td>
				<td align=center bgcolor="ffffff">
					${user.address1}
				</td>
				<td align=center bgcolor="ffffff">
					${user.bmi}
				</td>
				<td align=center bgcolor="ffffff"><a href="user_view.m2?userId=${user.userId}&command=view" class="user">
						▶
					</a></td>
		  	</tr>
		</c:forEach>  			  	
  	
	  	</table>
	
		
	</td>
</tr>
</table>  
</form>
</body>
</html>
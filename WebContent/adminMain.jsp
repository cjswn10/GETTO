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
<script>alert("��ϵǾ����ϴ�");</script>
<%} %>
<form name="f" method="post" >
<input type="hidden" name="command" value="dietlistinsert">
<input type="hidden" name="userid" value=<%=session.getAttribute("userid") %>>
<table>
<tr>
<td>
<table>
<tr><th colspan="2">���ĵ��</th></tr>
<tr>
 <th>�����̸�</th>

 <td><input type="text" name="foodname" maxlength="20" size="10">
 
</td>

</tr>
<tr>
 <th>100g�� Į�θ�</th>
 <td><input type="text" name="foodkcal" maxlength="20" size="10">kcal
 
</td>

</tr>
<tr>
 <th>���ļ���</th>
 <td colspan="2">
 <textarea name="foodinfo" maxlength="300" size="10"></textarea>
 </td>
</tr>	  	
<tr align="center">
 <td colspan="2"><input type="image" src="image/register.jpg" width=80 value="����ϱ�" onClick="foodCreate()"></td></tr>
 
</table>
</td>
<td align="center" width="250">
<input type="image" src="image/user_view.jpg" width=80 value="ȸ�� ����" onClick="searchUserList()">
</td>
</tr>
</table>
</form>
</center>
</body>
</html>
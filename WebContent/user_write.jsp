<%@page contentType="text/html; charset=euc-kr" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>����� ����</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="css/getto.css" type="text/css">
<script>
function userCreate() {
	if ( f.userid.value == "" ) {
		alert("����� ���̵� �Է��Ͻʽÿ�.");
		f.userid.focus();
		return false;
	} 
	if ( f.password.value == "" ) {
		alert("��й�ȣ�� �Է��Ͻʽÿ�.");
		f.password.focus();
		return false;
	}
	if ( f.name.value == "" ) {
		alert("�̸��� �Է��Ͻʽÿ�.");
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
			<td width=120 align=center bgcolor="d8d4d4">����� ���̵�</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:150" name="userid">
			</td>
		  </tr>
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">��й�ȣ</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="password" style="width:150" name="password">
			</td>
		  </tr>
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">��й�ȣ Ȯ��</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="password" style="width:150" name="password2">
			</td>
		  </tr>
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">�̸�</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:150" name="name">
			</td>
		  </tr>
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">�ּ�</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:150" name="address1"> ��)����� ���ϱ�
			</td>
		  </tr>	
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">���ּ�</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:240" name="address2">
			</td>
		  </tr>		  
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">����ó</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:150" name="phone">
			</td>
		  </tr>	
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">����</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="radio"name="gender" value="men" > ��
				<input type="radio"name="gender" value="women"> ��
			</td>
		  </tr>	
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">�������</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:150" name="birthday"> ��)19930303
			</td>
		  </tr>	
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">Ű</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:100" name="height"> cm
			</td>
		  </tr>	
		  <tr height="35">
			<td width=120 align=center bgcolor="d8d4d4">������</td>
			<td width=300 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:100" name="weight"> kg
			</td>
		  </tr>	
		  <tr height="35">
		  <td width=120 align=center bgcolor="d8d4d4">�����ִ� ��</td>
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
			<input type="image" src="./image/join.jpg" width=60 value="ȸ�� ����" onClick="userCreate()"> &nbsp;
			<input type="image" src="./image/reset.jpg" width=60 value="���" onClick="f.reset()">
			</td>
		  </tr>
	  </table>

	  </td>
	</tr>
</table>  

</body>
</html>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 --%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
	<br><br>
	<p align="center">用户登录</p>
	
	<hr width="230">
	<form action="login.do"  method="post">
		<table align="center" width="270" border="0"> 
			<tr height="30">
				<td>账号：</td>
				<td>
					<input type="text" name="firstName" >
				</td>
			</tr>
			<tr height="30">
				<td></td>
				<td>
					<%-- ${loginError} --%>
				</td>
			</tr>
			<!-- <tr height="30" align="center">
				<td colspan="2">
					<input type="checkbox" name="choose" value="true">自动登录
				</td>
			</tr> -->
			
			<tr height="40">
				<td colspan="2" align="center"> 
					<input type="submit" name="submit" value="登录">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
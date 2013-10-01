<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1>Please Log In:</h1>
<form action="LoginServlet" method="get">
	Username: <input type="text" name="unm"> </br>
    Password: <input type="password" name="pwd"> </br>
	<input type="submit" value="Submit"/>
	</form>
	
	<c:if test="${not empty message}">
    <h1>${message}</h1>
	</c:if>
</body>
</html>
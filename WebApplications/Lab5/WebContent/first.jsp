<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My First JSP</title>
<style>

body {
	font-family: sans-serif;
	background-color: silver;
}

</style>

</head>
<body>

<%@ page import="java.util.*" %>

<h1>My First JSP</h1>
<h2>Small Change</h2>
<p>
The time is now <%= new Date() %>
</p>

<% java.util.Date date = new Date(); %> 
<p>The time is now <%= date %></p>

<p>
<%=date.getHours() > 3 && date.getHours() < 12 ? "Good Morning" : date.getHours() >= 12 && date.getHours() < 17 ? "Good Afternoon" : "Good Night!"%>
</p>

</body>
</html>
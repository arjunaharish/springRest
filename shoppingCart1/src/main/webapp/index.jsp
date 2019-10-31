<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@page session="true"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Users using ajax</title>
<link rel="stylesheet" type="text/css" href="resources/css/loginTheme.css" media="all" />
<script src="${pageContext.request.contextPath}/resources/js/login.js" ></script>
</head>

<body>
      <div class="body"></div>
<div class="grad"></div>
<div class="header">
	<div>Site<span>Random</span></div>
</div>
<br>
<form id="loginForm"  action="login"method="post" name="submitForm">
<div class="login">
<div id="sloginFromResponse"></div>
<input id="userName"type="text" placeholder="userName"name="userName" ><br>
<input id="password" type="password" placeholder="password" name="password"><br>
	    <button class="registerbtn" type="submit">Register</button>
	    
</div>
<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
</form>



</body>
</html>




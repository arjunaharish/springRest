<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/js/app.js"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Users using ajax</title>
<%-- <script src="<c:url value="/js/app.js"/>"type="text/javascript"></script> --%>
<link rel="stylesheet" type="text/css" href="resources/css/viewemp.css" media="screen" />


</head>

<body>
      <div class="body"></div>
<div class="grad"></div>
<div class="header">
	<div>Site<span>Random</span></div>
</div>
<br>
<form id="loginForm" action="login" method="get" name="submitForm">
<div class="login">
<div id="sloginFromResponse"></div>
<input type="text" placeholder="userName"name="userName" ><br>
<input type="password" placeholder="password" name="password"><br>
	    <button type="submit" class="registerbtn">Register</button>
</div>
</form>
</body>
</html>




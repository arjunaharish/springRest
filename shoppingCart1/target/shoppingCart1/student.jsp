<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<html>
<body>
	<h2>How to fetch image from database using Spring MVC</h2>


	<table border="1">
		<tr>
			<th>Photo</th>
		</tr>
		<c:forEach var="student" items="${imagesIs}">
			<tr>
				
				
				
				
				<%-- <td><img width="100" height="100" src="${pageContext.request.contextPath}/resources/images/tennis.png"<c:out value='${student.name}'/>"></td> --%>
			</tr>
		</c:forEach>
	</table>

</body>
</html>
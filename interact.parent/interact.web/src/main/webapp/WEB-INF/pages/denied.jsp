<%@ page isELIgnored="false" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>

<head>

<title>Access Denied - Interact Web App</title>

<style TYPE="text/css">
.errormessage {
	color: red;
}
</style>

</head>

<body onload='document.loginForm.j_username.focus();'>

	
<h1>HTTP Status 403 - Access is denied</h1>
<a href="<%=request.getContextPath() %>">click here home page</a>
 	<c:choose>
		<c:when test="${empty j_username}">
			<h2>You do not have permission to access this page!</h2>
		</c:when>
		<c:otherwise>
			<h2>Username : ${j_username} <br/>You do not have permission to access this page!</h2>
		</c:otherwise>
	</c:choose> 
	

</body>

</html>

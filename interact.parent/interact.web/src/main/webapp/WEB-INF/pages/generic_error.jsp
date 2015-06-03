<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
h2 {
    color:red;
    padding-top: 4cm;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generic Error Page</title>
</head>
<body>
<div class="row">
		<div class="app_logo_error  columns">
			<img src="${pageContext.request.contextPath}/img/mail_logo.jpg" style="width:16%; title="Carecent" />
		</div>
		</div>
<h2 align="center" >Unknown Error Occured, please contact support team.</h2>
 </body>
</html>
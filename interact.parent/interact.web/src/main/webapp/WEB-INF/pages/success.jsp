<center>
<h2> successfully submitted.....</h2>
<a href="<%=request.getContextPath() %>">home</a>
</center>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/WEB-INF/fragments/admission.jsp" />
<html>
<head>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/admission.js"></script>
  <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>
  <style type="text/css">
  .ui-widget {
    font-family: Verdana,Arial,sans-serif;
    font-size: 10px;
}
 select{
    width: 142px;
    }
  </style>
</head>
</head>
<body>
	<form:form>
		<table>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td>First Name : ${registration.firstName}</td>
			</tr>
			<tr>
				<td>Middle Name : ${registration.middleName}</td>
			</tr>
			<tr>
				<td>Last Name: ${registration.lastName}</td>
			</tr>
			<tr>
				<td>User Name: ${registration.userName}</td>
			</tr>
			<tr>
				<td>Date of Birth: ${registration.dob} </td>
			</tr>
			<tr>
				<td>Joining Date: ${registration.joiningDate}</td>
			</tr>
			<tr>
				<td>Relieving Date: ${registration.relievingDate} </td>
			</tr>
			<tr>
				<td>Contact :${registration.contact} </td>
			</tr>
			<tr>
				<td>Gender : ${registration.gender} </td>
			</tr>
			<tr>
				<td>Role : ${registration.fStaffrole}</td>
			</tr>
			<tr>
				<td>Contact Type : ${registration.contactType} </td>
			</tr>
			<tr>
				<td>Address : ${registration.address} </td>
			</tr>
			<tr>
				<td>Street : ${registration.street} </td>
			</tr>
			<tr>
				<td>City : ${registration.city} </td>
			</tr>
			<tr>
				<td>State : ${registration.state} </td>
			</tr>
			<tr>
				<td>Zip : ${registration.zipcode} </td>
			</tr>
		</table>
	</form:form>
</body>
</html>
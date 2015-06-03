<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<title>Facility Management</title>
</head>
<body>
	<div id="facilityModel" class="reveal-modal tiny raleway" data-reveal>
		<h4 class="modal-title" style="text-align: center;">Facility Form</h4>
		<a class="close-reveal-modal">×</a>
			<form:form method="get" id="facilityForm" modelAttribute="facilityModel" action="/">
			<div id="facilityStatus" style="text-align: center;color: green;"></div>
			<div class="row">
				<div class="row">
					    <div class="large-8 columns small-centered">
					      <label><i class="fi-star small"></i>Facility Name: <form:input path="facilityName"/></label><br>
					  	  <label>Standing Orders: <form:checkbox id="standingOrders" path="standingOrders" value="1"/></label>
<!-- 					       <label>Contact Type: -->
<%-- 					       <form:select path="fcontactType"> --%>
<%-- 					       <form:option value="Mobile/Phone No">Mobile/Phone No</form:option> --%>
<%-- 					       <form:option value="Email">Email</form:option> --%>
<%-- 					    	</form:select> --%>
<!-- 					       </label> -->
					      <label id="fcontactInput"><i class="fi-star small"></i>Mobile/Phone No: <form:input path="fcontact" onblur="checkContact()"/></label>
					    </div>
					    
				</div>
				<div class="row">
					    <div class="large-8 columns small-centered">
					      <label>Address: <form:input path="faddress"/></label>
					      <label>Street: <form:input path="fstreet"/></label>
					      <label>City: <form:input path="fcity"/></label>
					      <label>State: <form:input path="fstate"/></label>
					      <label>ZipCode: <form:input path="fzipcode"/></label>
					    </div>
			   </div>
			   <br>
			   <div class="row">
				<div class="small-1 columns">
					<input id="facilitySubmit" type="button" class="button small" value="Create"/>
				</div>
				<div class="small-3 columns">
					<input type="button" class="button small" value="Close" id="facilityCancel" />
				</div>
			</div>
		</div>
			</form:form>
		</div>
</body>
</html>
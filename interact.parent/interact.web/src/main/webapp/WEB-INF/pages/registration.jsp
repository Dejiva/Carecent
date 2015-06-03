<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html id="myhtml">
<head>
<script src="${pageContext.request.contextPath}/js/vendor/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/jquery-ui.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/vendor/jquery.easydropdown.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/vendor/jquery.slimscroller.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/app.js"></script>
<script src="${pageContext.request.contextPath}/js/foundation.min.js"></script>
<script src="https://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/foundation.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font/foundation-icons.css" />
<link
	href="https://fonts.googleapis.com/css?family=Raleway:100,200,300,400,500,600,700,800,900"
	rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/js/vendor/modernizr.js"></script>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
<script>
	var flag = "true";
	$(function() {
		alert("${requestScope.errors}");
		if("${requestScope.errors}" == 'true'){
			$('#myModal').foundation('reveal', 'open');
		}
		$("#openregform").click(function() {
			//alert("hi");
			$('#myModal').foundation('reveal', 'open');
		});
		
		$("#lastName,#firstName").blur(
				function() {
					val1 = $("#firstName").val();
					val2 = $("#lastName").val();
					$.ajax({
						url : contextPath+'/registration/generateUser?firstName='
								+ val1 + "&lastName=" + val2,
						type : 'get',
						success : function(data) {
							$("#userName").val(data);
						}
					});
				});

	});
</script>
<script>
	$(function() {
		$(".datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : "-100:+0",
			dateFormat : "D M d yy"
		});
	});
</script>
</head>
<body>

	<button id="openregform">click me</button>

		<div id="myModal" class="reveal-modal large raleway"
			data-reveal>
			
			<p class="lead" style="text-align: center;">Carecent Staff
				Registratrion Form</p>
			<a class="close-reveal-modal">&#215;</a>
			<form:form method="post" commandName="staffRegistrationForm"  action="${pageContext.request.contextPath}/registration">
				<form:errors path="*"></form:errors>
				<div class="row">
					<div class="large-6 columns move_form_left">
						<div class="row">
							<div class="small-5 columns">
								<label for="firstName" class="right inline">First Name:</label>
							</div>
							<div class="small-5 columns">
								<form:errors path="firstName"  cssClass="error"></form:errors>
							</div>
							<div class="small-7 columns">
								<form:input path="firstName" />
							</div>
						</div>
						<div class="row">
							<div class="small-5 columns">
								<label for="middleName" class="right inline">Middle
									Name:</label>
							</div>
							<div class="small-7 columns">
								<form:input path="middleName"/>
							</div>
						</div>
						<div class="row">
							<div class="small-5 columns">
								<label for="lastName" class="right inline">LastName:</label>
							</div>
							<div class="small-7 columns">
								<form:input path="lastName"/>
							</div>
						</div>
						<!-- div class="row">
							<div class="small-5 columns">
								<label for="userName" class="right inline">UserName:</label>
							</div>
							<div class="small-7 columns">
								<input name="userName" readonly id="userName" type="text"
									required />
							</div>
						</div>
						<div class="row">
							<div class="small-5 columns">
								<label for="empId" class="right inline">Emp Id:</label>
							</div>
							<div class="small-7 columns">
								<input name="empId" id="empId" type="text" required />
							</div>
						</div>
						<div class="row">
							<div class="small-5 columns">
								<label for="dateOfHire" class="right inline">Date of
									Hire:</label>
							</div>
							<div class="small-7 columns">
								<input name="dateOfHire" id="dateOfHire" class="datepicker"
									type="text" required />
							</div>
						</div>
						<div class="row">
							<div class="small-5 columns">
								<label for="contactType" class="right inline">Contact
									Type:</label>
							</div>
							<div class="small-7 columns">
								<select name="contactType" id='contactType' required>
									<option value="mobile">Mobile</option>
									<option value="email">Email</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="small-5 columns">
								<label for="mobileNo" class="right inline">Mobile No:</label>
							</div>
							<div class="small-7 columns">
								<input name="mobileNo" id="mobileNo" type="text" required
									pattern="[0-9]{10,14}" />
							</div>
						</div>
						<div class="row">
							<div class="small-5 columns">
								<label for="workNo" class="right inline">Work Mobile No:</label>
							</div>
							<div class="small-7 columns">
								<input name="workNo" id="workNo" type="text" required
									pattern="[0-9]{10,14}" />
							</div>
						</div>
					</div>
					<div class="columns large-6 move_form_left">
						<div class="row">
							<div class="small-5 columns">
								<label for="email" class="right inline">Email:</label>
							</div>
							<div class="small-7 columns">
								<input name="email" id="email" type="email" required />
							</div>
						</div>
						<div class="row">
							<div class="small-5 columns">
								<label for="workEmail" class="right inline">Work Email:</label>
							</div>
							<div class="small-7 columns">
								<input name="workEmail" id="workEmail" type="email" required />
							</div>
						</div>
						<div class="row">
							<div class="small-5 columns">
								<label for="gender" class="right inline">Gender:</label>
							</div>
							<div class="small-7 columns">
								<select name="gender" id="gender" required>
									<option value="male">Male</option>
									<option value="female">Female</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="small-5 columns">
								<label for="fStaffrole" class="right inline">Role:</label>
							</div>
							<div class="small-7 columns">
								<select name="fStaffrole" id="fStaffrole" required>
									<option value="ROLE_CNA">ROLE_CNA</option>
									<option value="ROLE_LPN">ROLE_LPN</option>
									<option value="ROLE_RN">ROLE_RN</option>
									<option value="ROLE_GUEST">ROLE_GUEST</option>
									<option value="ROLE_PA">ROLE_PA</option>
									<option value="ROLE_MD">ROLE_MD</option>
									<option value="ROLE_ADMIN">ROLE_ADMIN</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="small-5 columns">
								<label for="address" class="right inline">Address:</label>
							</div>
							<div class="small-7 columns">
								<input name="address" id="address" type="text" required />
							</div>
						</div>
						<div class="row">
							<div class="small-5 columns">
								<label for="street" class="right inline">Street:</label>
							</div>
							<div class="small-7 columns">
								<input name="street" id="street" type="text" required
									pattern="[a-zA-Z0-9]+" />
							</div>
						</div>
						<div class="row">
							<div class="small-5 columns">
								<label for="city" class="right inline">City:</label>
							</div>
							<div class="small-7 columns">
								<input name="city" id="city" type="text" required
									pattern="[a-zA-Z0-9]+" />
							</div>
						</div>
						<div class="row">
							<div class="small-5 columns">
								<label for="state" class="right inline">State:</label>
							</div>
							<div class="small-7 columns">
								<input name="state" id="state" type="text" required
									pattern="[a-zA-Z]+" />
							</div>
						</div>
						<div class="row">
							<div class="small-5 columns">
								<label for="zipcode" class="right inline">Zip:</label>
							</div>
							<div class="small-7 columns">
								<input name="zipcode" id="zipcode" type="text" required
									pattern="[0-9]{5}" />
							</div>
						</div-->
					</div>
					<div class="row">
						<div class="small-1 large-centered columns">
							<input type="submit" class="button small" value="Register"
								tabindex="3" />
						</div>
					</div>
				</div>
			</form:form>
		</div>
</body>
</html>
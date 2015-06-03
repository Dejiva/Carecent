<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
</head>
<form:form method="POST" commandName="admissionAttributes" action="<%=request.getContextPath()%>/attributes">
	<div style="width:1000px;">
		<div style="width:100px;float:left">
			Mental Status
		</div>
		<div style="width:100px;float:left">
			<form:radiobutton path="KCIMS_ALRT" value="Alert"/>Alert
		</div>
		<div style="width:100px;float:left">
			<form:radiobutton path="KCIMS_ALRT" value="Disoriented,Follows Commands"/>Disoriented,Follows Commands
		</div>
		<div style="width:100px;float:left">
			<form:radiobutton path="KCIMS_ALRT" value="Disoriented,Cannot follow Commands"/>Disoriented,Cannot follow Commands
		</div>
		<div style="width:100px;float:left">
			<form:radiobutton path="KCIMS_ALRT" value="Not Alert"/>Not Alert
		</div>
	</div>
	<div style="width:1000px;">
		<input type="submit" value="Submit"/>
	</div>
</form:form>

</html>
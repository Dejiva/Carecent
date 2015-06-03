$(function(){
	$("#forgotPassword").click(function(){
		clearAll();
		$('#ForgotPasswordModal').foundation('reveal', 'open');
	});

	$("#forgotPassCancel").click(function(){
		$('#ForgotPasswordModal').foundation('reveal', 'close');
	});

	$("#passwordResetSubmit").click(function(){
		if($("#workMail").val()){
			$("#passwordResetMessage").html("Please wait while resetting password.......");
		}
	});
	if(status == "success"){
		$('#ForgotPasswordModal').foundation('reveal', 'open');
		$("#passwordResetMessage").html("<div style='color: green;'>Password Reseting success</div>");
		setTimeout(function(){
			$("#ForgotPasswordModal").foundation('reveal', 'close');
		}, 3000);
		window.history.pushState({"html":"","pageTitle":"CARECENT"},"", contextPath+"/");
	}else if(status == "failed"){
		$('#ForgotPasswordModal').foundation('reveal', 'open');
		$("#passwordResetMessage").html("<div style='color: red;'>Password Reseting failed please recheck your email once</div>");
		setTimeout(function(){
			$("#ForgotPasswordModal").foundation('reveal', 'close');
		}, 3000);
		window.history.pushState({"html":"","pageTitle":"CARECENT"},"", contextPath+"/");

	}
});
function clearAll(){
	$('input[type=text]').val("");
	$('#passwordResetMessage').empty();
}
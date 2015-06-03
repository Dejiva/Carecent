//show admission details to the doctor
function functionAdmissionEyeSpan(e) {
//	$('#admissionAttributesForm').trigger('reset');
	clearAll();
	$patientId = $(e).attr('patient-id').split('=')[0];
	$("#admistionPatientName").text($(e).attr('patient-name')+"-");
	$("#admissionForm").attr('action',path+"/getPatient?patientId="+$patientId+'&ssnCheck='+false+'&propStatus='+true);
	var x = document.getElementsByClassName("admissionModel_class");
	x[0].submit(); 
}

if(propStatus == "disbleProp"){
	$('input, textarea').prop('readonly', 'readonly');
	$('select').prop('disabled', 'disabled');
	$('input[type=radio], input[type=checkbox]').prop('disabled', 'disabled');
	$("#admission").hide();
	$("#cancelAdmission").hide();
	$("#medReconPart1Add").hide();
	$("#medReconPart2Add").hide();
}

function functionClearAdmissionView(e){
	if(propStatus != "disbleProp"){
		genericConfirm("Unsaved data might be lost, have you saved your changes?").then(function (answer){
			if(answer){
				clearAll();
				$('#NewPatientModal').foundation('reveal', 'close');
				window.history.pushState({"html":"","pageTitle":"CARECENT"},"", path+"/");
				$('input, textarea').prop('readonly',false);
				$('select').prop('disabled', false);
				$('input[type=radio], input[type=checkbox]').prop('disabled', false);
				$("#admission").show();
				$("#cancelAdmission").show();
				$("#medReconPart1Add").show();
				$("#medReconPart2Add").show();
				if(!jQuery.isEmptyObject(admissionStatus)){
					location.reload();
				}
			}
		});
	}else{
		clearAll();
		$('#NewPatientModal').foundation('reveal', 'close');
		window.history.pushState({"html":"","pageTitle":"CARECENT"},"", path+"/");
		$('input, textarea').prop('readonly',false);
		$('select').prop('disabled', false);
		$('input[type=radio], input[type=checkbox]').prop('disabled', false);
		$("#admission").show();
		$("#cancelAdmission").show();
		$("#medReconPart1Add").show();
		$("#medReconPart2Add").show();
		if(!jQuery.isEmptyObject(admissionStatus)){
			location.reload();
		}
	}
}


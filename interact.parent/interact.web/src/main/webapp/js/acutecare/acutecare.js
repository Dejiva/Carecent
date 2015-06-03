//*******************    Start changed document.on into custom functions..		By Ramesh *************************

function functionInAcutenhContactPerson(e){
	$("#inAcutenhContactPTel").val($('option:selected', e).attr('work-Num')).attr('readonly','true');
}

function functionInAcutekciPrimaryReasonTransfer2(e) {
	if ($(e).is(':checked')) {
		$('.kciiy').show();
	}
}

function functionInAcutekciPrimaryReasonTransfer1(e) {
	if ($(e).is(':checked')) {
		$("#inAcutekciTests").val('');
		$('.kciiy').hide();
	}
}

function functionInAcutekciRdOther(e) {
	if ($(e).is(':checked')) {
		$('.kciro').show();
	} else if ($(e).is(':not(:checked)')) {
		$("#inAcutekciRdOtherTxt").val('');
		$('.kciro').hide();
	}
}

function functionInAcutedtOther(e) {
	if ($(e).is(':checked')) {
		$('.dto').show();
	} else if ($(e).is(':not(:checked)')) {
		$("#inAcutedtOtherTxt").val('');
		$('.dto').hide();
	}
}

function functionInAcuteipO(e) {
	if ($(e).is(':checked')) {
		$('.ipo').show();
	} else if ($(e).is(':not(:checked)')) {
		$("#inAcuteipOTxt").val('');
		$('.ipo').hide();
	}
}

function functionInAcuteriskAlerts(e) {
	if ($("#inAcuteriskAlerts").val() == 'RA_OT') {
		$(".rao").show();
	} else if ($("#inAcuteriskAlerts").val() != 'RA_OT') {
		$("#inAcuteraOtTxt").val('');
		$(".rao").hide();
	}
}


function functionInOutcomeOfTransfer(e) {
	if ($("#inAcuteOutComeTransfers").val() == 'OCT_OT') {
		$(".oct1").show();
	} else if ($("#inAcuteOutComeTransfers").val() != 'OCT_OT') {
		$("#inAcuteOutComeTransfersTxt").val('');
		$(".oct1").hide();
	}
}

function functionInAcutepbswResident(e) {
	if ($("#inAcutepbswResident").val() == 'PBSR_OT') {
		$(".pbsr").show();
	} else if ($("#inAcutepbswResident").val() != 'PBSR_OT') {
		$("#inAcutepbsrOtTxt").val('');
		$(".pbsr").hide();
	}
}

function functionInAcutenharufconditions(e) {
	if ($("#inAcutenharufconditions").val() == 'NHARUFC_OT') {
		$(".nharbc").show();
	} else if ($("#inAcutenharufconditions").val() != 'NHARUFC_OT') {
		$("#inAcutenharufcOtTxt").val('');
		$(".nharbc").hide();
	}
}

function functionInAcutepgctOfTransfer(e) {
	if ($("#inAcutepgctOfTransfer").val() == 'PGCTT_O') {
		$(".pgoc").show();
	} else if ($("#inAcutepgctOfTransfer").val() != 'PGCTT_O') {
		$("#inAcutepgcttOtTxt").val('');
		$(".pgoc").hide();
	}
}

function functionInAcutedsc2(e) {
	if ($(e).is(':checked')) {
		$('.dsc').show();
	}
}

function functionInAcutedsc1(e) {
	if ($(e).is(':checked')) {
		$("#inAcutedscTxt").val('');
		$('.dsc').hide();
	}
}

function functionInAcutedetf2(e) {
	if ($(e).is(':checked')) {
		$('.etffr').show();
	}
}

function functionInAcutedetf1(e) {
	if ($(e).is(':checked')) {
		$("#inAcutedetfFrTxt").val('');
		$('.etffr').hide();
	}
}

function functionInAcuteprhPTherapy2(e) {
	if ($(e).is(':checked')) {
		$('.prtp').show();
	}
}

function functionInAcuteprhPTherapy1(e) {
	if ($(e).is(':checked')) {
		$("#inAcuteprhPtIt").val('');
		$('.prtp').hide();
	}
}

function functionInAcuteprhOt2(e) {
	if ($(e).is(':checked')) {
		$('.prto').show();
	}
}

function functionInAcuteprhOt1(e) {
	if ($(e).is(':checked')) {
		$("#inAcuteprhOtIt").val('');
		$('.prto').hide();
	}
}

function functionInAcuteprhSt2(e) {
	if ($(e).is(':checked')) {
		$('.prts').show();
	}
}

function functionInAcuteprhSt1(e) {
	if ($(e).is(':checked')) {
		$("#inAcuteprhStIt").val('');
		$('.prts').hide();
	}
}

function functionInAcuteadlsCai(e) {
	if ($(e).is(':checked')) {
		$('.adlcai').show();
	} else if ($(e).is(':not(:checked)')) {
		$("#inAcuteadlsCaiTxt").val('');
		$('.adlcai').hide();
	}
}

function functionInAcuteadlsAd(e) {
	if ($(e).is(':checked')) {
		$('.adlad').show();
	} else if ($(e).is(':not(:checked)')) {
		$("#inAcuteadlsAdTxt").val('');
		$('.adlad').hide();
	}
}

function functionInAcuteadlsHaa(e) {
	if ($(e).is(':checked')) {
		$('.adlnha').show();
	} else if ($(e).is(':not(:checked)')) {
		$("#inAcuteadlsHaaTxt").val('');
		$('.adlnha').hide();
	}
}

function functionInAcuteigeneral(e) {
	if ($("#inAcuteigeneral").val() == 'IG_OT') {
		$(".igo").show();
	} else if ($("#inAcuteigeneral").val() != 'IG_OT') {
		$("#inAcuteigOtTxt").val('');
		$(".igo").hide();
	}
}

function functionInAcuteimusculoskeletal(e) {
	if ($("#inAcuteimusculoskeletal").val() == 'IM_OT') {
		$(".imgo").show();
	} else if ($("#inAcuteimusculoskeletal").val() != 'IM_OT') {
		$("#inAcuteimOtTxt").val('');
		$(".imgo").hide();
	}
}

function functionAcuteCarePencilSpan(e){
	clearAcuteCareForm();
	var patientId = $(e).attr('ac-patient-id');
	$("#inAcutePatinetId").val(patientId);
	$("#openMode").val("Edit");
	$("#acuteCareModel").attr('action',path+"/acuteCare/openAcuteCare");
	var x = document.getElementsByClassName("acuteCareModel_class");
	x[0].submit(); 
}

//*******************   End  changed document.on into custom functions..		*************************
$(function() {

	$("#acuteCareFormButtonSave").click(function(){
		$("#patientStatusInAcuteCare").val("");
		if(jQuery.isEmptyObject($("#inAcutedot").val())){
			$("#inAcutedot").val(0);
		}
		if(jQuery.isEmptyObject($("#inAcuteOutComeTransfers").val())){
			$("#inAcuteOutComeTransfers option:eq(0)").val(0);
		}
	});
	
	if (acuteCareObj == "true") {
		$.ajax({
			url : path+'/sbar/getActivePatientEpisodeId?patientId='
				+ $("#inAcutePatinetId").val(),
				type : 'get',
				global : false,
				success : function(data) {
					$patientInfo = data.split('=');
					$('#acuteCarePatientEpisodeId').val($patientInfo[0]);
					$("#inAcutesendFrom").val(currentFacility).attr('readonly',
					'true');
					if($("#inAcutedot").val() == 0){
						$("#inAcutedot").val('');
					}
					$("#openMode").val("Edit");
					$("#acuteCareFormButtonSave").val("Update");
					$("#acuteCareModalMessage").html(acuteCareStatus);
					
				}
		});

		$('#AcuteCareRevialModal').foundation('reveal', 'open');
		$("#acuteCareModalMessage").html(acuteCareStatus);
		$("#acuteCarePatientName").text($(this).attr('patient-name'));
		$("#stayInFacility").attr('checked', false);
		$("#sendToHospital").attr('checked', false);
	} else if (acuteCareObj == "false" && acuteCareStatus != "openPatient") {
		$("#acuteCareModalMessage").html(acuteCareStatus);
		window.history.pushState({
			
			"html" : "",
			"pageTitle" : "CARECENT"
		}, "", path+"/");
		location.reload();
	}else if(acuteCareObj == "false" && acuteCareStatus == "openPatient"){
		clearAll();
		$("#SSNNumberStatus").text("");
		$("#AddNewPatient").attr('click-attr',1);
		$("#NewPatientModal dl dd:first-child").addClass('active');
		$("#NewPatientModal dl dd:first-child >a").trigger('click');
		$("#patientId").val(patientId);
		$("#admissionForm").attr('action',path+"/getPatient?patientId="+patientId+'&ssnCheck='+false+'&reloadDashBoard='+true);
		var x = document.getElementsByClassName("admissionModel_class");
		x[0].submit(); 
	}
	
	$(".inAcuteDateTimePicker").datepicker(
			{
				timeFormat : 'hh:mm:ss tt',
				defaultDate : new Date(),
				changeMonth : true,
				changeYear : true,
				maxDate : new Date(),
				yearRange : "-100:+0",
				dateFormat : "mm/dd/yy",
				onSelect : function(datetext) {
					var d = new Date(); // for now
					datetext = datetext + " " + d.getHours() + ": "
							+ d.getMinutes() + ": " + d.getSeconds();
					$('.inAcuteDateTimePicker').val(datetext);
				}
			});
});




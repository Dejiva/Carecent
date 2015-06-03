// *******************    Start changed document.on into custom functions..		By Ramesh *************************

// Auto populate selected (MD/NP/PA) phone numbers...
function populatepcpAtSiteWorkNumber(e) {
	$("#pcpWorkNumber").val($('option:selected', e).attr('work-Num')).attr('readonly', 'true');
}

function functionHideAllPatients(e) {
	$('#patientSearch').val('');
	$('.all_patients').show();
	if (staffRole == "MD" || staffRole == "NP" || staffRole == "PA") {
		assigned_patients();
	} else {
		allPatients();
	}
}

function functionAdmissionPencilSpan(e) {
	clearAll();
	$("#SSNNumberStatus").text("");
	$("#AddNewPatient").attr('click-attr',1);
	$("#NewPatientModal dl dd:first-child").addClass('active');
	$("#NewPatientModal dl dd:first-child >a").trigger('click');
	$patientId = $(e).attr('patient-id');
	$("#admistionPatientName").text($(e).attr('patient-name') + "-");
	$("#patientId").val($patientId);
	$("#admissionForm").attr('action',path+"/getPatient?patientId="+$patientId+'&ssnCheck='+false);
	var x = document.getElementsByClassName("admissionModel_class");
	x[0].submit(); 
}

function functionSSNumber(e) {
	$("#admission").show();
	$("#SSNNumberStatus").text('');
	$checking = $("#AddNewPatient").attr('click-attr');
	var patient = $("#patientId").val();
	var ssn ={
		ssn:$(e).val()
	};
	$.ajax({
		url : contextPath+'/checkSSN',
		type : 'post',
		contentType:'application/json',
		data: JSON.stringify(ssn),
		global: false,
		success : function(data) {
			if(data.status == "ok"){

				if(jQuery.isEmptyObject(patient)){
					if(jQuery.isEmptyObject(data.fdischrgeDt)){
						$("#admissionForm").attr('action',path+"/getPatient?patientId="+data.patientId+'&ssnCheck='+false);
						var x = document.getElementsByClassName("admissionModel_class");
						x[0].submit(); 
					}else{
						genericConfirm("Patient information exists,\n re-admitting now ?").then(function (answer){
							 if(answer){
									$("#admissionForm").attr('action',path+"/getPatient?patientId="+data.patientId+'&ssnCheck='+false);
									var x = document.getElementsByClassName("admissionModel_class");
									x[0].submit(); 
								}
						});
					}
				}
				if(!jQuery.isEmptyObject(patient)){
					if(patient != data.patientId){
						$("#SSNNumberStatus").text("Duplicate SSN Number");
					}
				}
			}else if(data.status == "duplicate"){

				if(data.fdischrgeDt == null && jQuery.isEmptyObject(patient)){
					$("#SSNNumberStatus").text("existed in other facility");
				}else if(data.fdischrgeDt != null && jQuery.isEmptyObject(patient)){
					$("#SSNNumberStatus").text("Patient was been in other facility,\n admitting into this facility now ?");
					genericConfirm("Patient was been in other facility,\n admitting into this facility now ?").then(function (answer){
						 if(answer){
							 $("#admissionForm").attr('action',path+"/getPatient?patientId="+data.patientId+'&ssnCheck='+true);
								var x = document.getElementsByClassName("admissionModel_class");
								x[0].submit(); 
							}
					});
				}else{
					$("#SSNNumberStatus").text("Duplicate SSN Number");
				}
			}else if(data.status == "New"){
				var ssn = $(e).val();
				clearAll();
				$("#sSNumber").val(ssn);
			}else if(data.status == "Failed"){
			}
		}
		});
}

function functionMedReconEdit(e) {
	$mdeals=$(e).attr('data-id');
	$('#medicalReconcilationRowId').val($mdeals);
	$medicationDetails=$('#medicationDetails'+$mdeals+' input').val();
	$clarificationNeeded=$('#clarificationNeeded'+$mdeals+' input').val();
	$comments=$('#comments'+$mdeals+' input').val();
	$resolutionId=$('#resolutionId'+$mdeals+' input').val();

	$('#admissionMedication').val($medicationDetails);
	$('#admissionClarification').val($clarificationNeeded);
	$('#resolutions').val($resolutionId);
	$('#medicalReconcilationRowId').val($mdeals);

	if($('#TABLE'+$mdeals).val() == 1){
		$('#comments').html('Clarification Needed*: <textarea id="admissionClarification" name="column2" cols="100" rows="2"></textarea>');
		$('#medicalReconciliationPopup').show();
		$("#medicalReconciliationPopup").dialog(opt).dialog("open");
		$("#medicalReconciliationPopup").dialog('option', 'title', ' Hospital Recommended Medications Needing Clarification');
		$('#admissionClarification').val($clarificationNeeded);
		$("#medications").html('Recommended by Hospital at Discharge:');
	}
	if($('#TABLE'+$mdeals).val() == 2){
		$('#comments').html('Comments: <textarea id="comnts" name="column2" cols="100" rows="2"></textarea>');
		$('#medicalReconciliationPopup').show();
		$("#medicalReconciliationPopup").dialog(opt).dialog("open");
		$("#medicalReconciliationPopup").dialog('option', 'title', 'Medications Prior to Hospitalization Needing Clarification');
		$('#comnts').val($comments);
		$("#medications").html('Before Hospitalization Not Recommended by Hospital:');
	}

}

function functionMedReconDel(e){
	$mdeals=$(e).attr('dataDel-id');
	 genericConfirm("Do You Want Delete The Record ?").then(function (answer){
		 if(answer){
			 if(answer && !$mdeals){
					$(e).parent().parent().remove();
				}
				if(answer){
					$('#medicalReconciliationId'+$mdeals).append('<input type="hidden" name="medicalReconciliationDto['+$mdeals+'].tobeDeleted" value="true">');
					$('#medicalReconciliationId'+$mdeals).parent().hide();
				}
			}
	});
}

// *******************   End  changed document.on into custom functions..		*************************
$(function() {
	if(status == "success"){
		$("#admission").val("Update Patient");
		$('#NewPatientModal').foundation('reveal', 'open',{
			close_on_background_click : false,
			close_on_esc : false
		});
		if(admissionStatus != "reloadDashBoard"){
		$("#NewPatientModalMessage").html(admissionStatus);
		}else if(admissionStatus == "reloadDashBoard"){
			$("#cancelAdmission").hide();
			$(".admission-close-reveal-modal").html("");
		}
		$("#transferredToPostAcuteCare").val(currentFacility).attr('readonly','true');
		$("#postAcuteCareTelephoneNumber").val(currentFacilityCont).attr('readonly','true');
		if(!jQuery.isEmptyObject(patientIdndEId)){
			 $patient = patientIdndEId.split('|');
			$("#patientId").val($patient[0]);
			$("#patientEpisodeId").val($patient[1]);
			
		}
		window.history.pushState({"html":"","pageTitle":"CARECENT"},"", path+"/");
		if(ssnCheck == "true"){
			$("#patientEpisodeId").val('');
		}
	}
	if(status == "FailedAdmission"){
		$("#admission").val("Update Patient");
		$('#NewPatientModal').foundation('reveal', 'open',{
			close_on_background_click : false,
			close_on_esc : false
		});
		$("#transferredToPostAcuteCare").val(currentFacility).attr('readonly','true');
		$("#postAcuteCareTelephoneNumber").val(currentFacilityCont).attr('readonly','true');
		if(admissionStatus != "reloadDashBoard"){
		$("#NewPatientModalMessage").html(admissionStatus);
		}else if(admissionStatus == "reloadDashBoard"){
			$("#cancelAdmission").hide();
			$(".admission-close-reveal-modal").html("");
		}
		window.history.pushState({"html":"","pageTitle":"CARECENT"},"", path+"/");
		if(ssnCheck == "true"){
			$("#patientEpisodeId").val('');
		}
	}

	var searchVal=$("#patientSearch").val();
	if(searchVal=='')
	{
		$('#hideAllPatients').hide();
	}
	else
	{
		$('#hideAllPatients').show();
	}
	 
	function assigned_patients()
	{
		
		var facilityId=$("#sfacilityId").val();
		$.ajax({
	 	         url :contextPath+'/findAssignPatients',
			     type:'get',
                 success : function(result) {
	        	    location.reload();
	     	     }
	    });

	};
	function allPatients()
	{
		var Id="";
		$.ajax({
			     url :contextPath+'/getSearchNames?searchId='+ Id,
				 type:'get',
                 success : function(result) {
					location.reload();
				 }
		});
	};
	$("span.add_patient").click(function(e) {
		$("#NewPatientModal dl dd:first-child").addClass('active');
		$("#NewPatientModal dl dd:first-child >a").trigger('click');
		statusFlag ="";
		$("#admission").show();
		$("#SSNNumberStatus").text("");
		$("#pcpAtSite").val("");
		$("#AddNewPatient").attr('click-attr',0);
		$("#admistionPatientName").text("");
		
		$("#admission").val("Add Patient");
		$('#NewPatientModal').foundation('reveal', 'open',{
			close_on_background_click : false,
			close_on_esc : false
		});
		clearAll();
		$("#patientEpisodeId").val('');
		$("#patientId").val('');
		$("#transferredToPostAcuteCare").val(currentFacility).attr('readonly','true');
		$("#postAcuteCareTelephoneNumber").val(currentFacilityCont).attr('readonly','true');
	});
	$("span.all_patients").click(function(e) {
		allPatients();
	});
    $("span.assigned_patients").click(function(e) {
    	
	   assigned_patients();
	});
	$("#specialist1Speciality").focus(function(){
		var specialities = [];
		$.ajax({
			url:contextPath+'/getAllSpecialities',
			type:'get',
			success:function(data){
				$.each(data,function(key,value){

					var label = {"label":value.specialityName, "value":value.specialityName, 
							"id":value.id};
					specialities.push(label);
				});
			}
		});
		$( "#specialist1Speciality" ).autocomplete({
			source: specialities,
			select: function(event,ui){
				$("#specialist1Speciality").val(ui.item.specialityName);
				$("#specialist1SpecialityId").val(ui.item.id);
			},
			 change:function(event,ui){
				 if(ui.item == null){
					 $("#specialist1Speciality").val('');
					 $("#specialist1SpecialityId").val('');
				 }
				   return false;
				 }
		});
	});

	$("#specialist2Speciality").focus(function(){
		var specialities = [];
		$.ajax({
			url:contextPath+'/getAllSpecialities',
			type:'get',
			success:function(data){
				$.each(data,function(key,value){

					var label = {"label":value.specialityName, "value":value.specialityName, 
							"id":value.id};
					specialities.push(label);
				});
			}
		});
		$( "#specialist2Speciality" ).autocomplete({
			source: specialities,
			select: function(event,ui){
				$("#specialist2Speciality").val(ui.item.specialityName);
				$("#specialist2SpecialityId").val(ui.item.id);
			},
			 change:function(event,ui){
				 if(ui.item == null){
					 $("#specialist2Speciality").val('');
					 $("#specialist2SpecialityId").val('');
				 }
				   return false;
				 }
		});
	});
	
 if(snwsuccess=='stopandwatchFailed'){
		
		try{
	    	 $('#message').text("Stop and Watch Not created").css("color", "red");
				 $('#messageModel').foundation('reveal', 'open');
		
			     setTimeout(function(){$("#messageModel").foundation('reveal', 'close');}, 5000);
		    
			   window.history.pushState({"html":"","pageTitle":"CARECENT"},"", path+"/");
         }catch(e){};
	}
	if(snwsuccess=='stopandwatchsuccess'){
		
		try{
	    	 $('#message').text("Stop and Watch created Successfully").css("color", "green");
				 $('#messageModel').foundation('reveal', 'open');
		
			     setTimeout(function(){$("#messageModel").foundation('reveal', 'close');}, 5000);
		    
			   window.history.pushState({"html":"","pageTitle":"CARECENT"},"", path+"/");
         }catch(e){};
	}
   if(snwsuccess=='stopandwatchsuccessupdate'){
		
		try{
	    	 $('#message').text("Stop and Watch updated Successfully").css("color", "green");
				 $('#messageModel').foundation('reveal', 'open');
		
			     setTimeout(function(){$("#messageModel").foundation('reveal', 'close');}, 5000);
		    
			   window.history.pushState({"html":"","pageTitle":"CARECENT"},"", path+"/");
        }catch(e){};
	}


if(errorIn == 'admissionModalError'){
		if(patientId){
			$("#admission").val("Update Patient");	
		}
		$('#NewPatientModal').foundation('reveal', 'open',{
			close_on_background_click : false,
			close_on_esc : false
		});
	} 
});

function clearAll(){
	var facilityId = $("#sfacilityId").val();
	$('input[type=text]').val("");
	$('input[type=radio]').prop("checked", false);
	$('input[type=checkbox]').prop("checked", false);
	$('select').val('');
	$('tbody').not($('.mail-table-body')).empty();
	$('form.error').empty();
	$('#NewPatientModalMessage').empty();
	$('#admissionFormErrors').empty();
	$("#sfacilityId").val(facilityId);
	$("#patientId").val('');
	$("#patientEpisodeId").val('');
}

function clearAcuteCareForm(){
	$('input[type=text]').val("");
	$('input[type=radio]').prop("checked", false);
	$('input[type=checkbox]').prop("checked", false);
	$('select').val('');
	$('tbody').not($('.mail-table-body')).empty();
	$('form.error').empty();
	$('#NewPatientModalMessage').empty();
	$('#admissionFormErrors').empty();
}
var opt = {
		autoOpen: true,
		modal: true,
		closeText: 'x',
		resizable: false,
		width: '60%',
		closeOnEscape: true
};

$("#medReconPart1Add").click(function(){
	$("#medications").html('Recommended by Hospital at Discharge:');
	$('#comments').html('Clarification Needed*: <textarea id="admissionClarification" name="column2" cols="100" rows="2"></textarea>');
	$('#medicalReconciliationPopup').show();
	$('#medicalReconciliationPopup').dialog(opt).dialog("open");
	$("#medicalReconciliationPopup").dialog('option', 'title', 'Hospital Recommended Medications Needing Clarification');
	$("#admissionMedication").val("");
});


function openJueryMedicalReconciliationPopupClose(eleId){
	$('.ui-dialog-titlebar-close').trigger('click');
	$('#medicalReconcilationRowId').val("");
}

function medicalReconciliationRecordSave(){
	var id = $("#medicalReconcilationRowId").val();
	var col1=$('#admissionMedication').val();
	var col3=$('#resolutions').val();
	
	$('#admissionClarification').val() ?  col2=$('#admissionClarification').val() : col2=$('#comnts').val();
	
	if(col1 == ""){
		$("#medications").css({color:"red"});
	}else{
		$("#medications").css({color:"black"});
	}
	if(col2 == "" || col2 == undefined){
		$("#comments").css({color:"red"});
	}else{
		$("#comments").css({color:"black"});
	}
	
	if(col1 !== ""  && col2 !== "" && col2 !== undefined){
	$('#medicationDetails'+id).val('<input type="hidden" value="'+col1+'" name="medicalReconciliationDto[0].medicationDetails">'+col1);
	$('#clarificationNeeded'+id).val('<input type="hidden" value="'+col2+'" name="medicalReconciliationDto[0].medicationDetails">'+col2);
	$('#resolutionId'+id).val('<input type="hidden" value="'+col3+'" name="medicalReconciliationDto[0].medicationDetails">'+$("#resolutions option:selected").text());
	openJueryMedicalReconciliationPopupClose();
	getMedicalReconciliationRecords(col1, col2, col3, id);
	}
}

function getMedicalReconciliationRecords(col1,col2,col3, id){

	if(id!=""){
		$('#medicationDetails'+id).html('<input type="hidden" name="medicalReconciliationDto['+id+'].medicationDetails"  value="'+col1+'">'+col1);
		if($('#TABLE'+id).val() == 1){
			$('#clarificationNeeded'+id).html('<input type="hidden" name="medicalReconciliationDto['+id+'].clarificationNeeded" value="'+col2+'">'+col2);
		}else if($('#TABLE'+id).val() == 2){
			$('#comments'+id).html('<input type="hidden" name="medicalReconciliationDto['+id+'].comments" value="'+col2+'" >'+col2);
		}
		$('#resolutionId'+id).html('<input type="hidden" name="medicalReconciliationDto['+id+'].resolutionId" value="'+$("#resolutions").val()+'">'+$("#resolutions option:selected").text());

	} else{
		var count=rowNum++;
		var str = $('#comments').text();
		if(str.search("Comments") == 0){
			$('#medicalReconciliationTable2 tbody')
			.append('<tr><td id="medicationDetails'+count+'"><input type="hidden" name="medicalReconciliationDto['+count+'].medicationDetails"  value="'+col1+'">'+col1+
					'</td><td id="comments'+count+'"><input type="hidden" name="medicalReconciliationDto['+count+'].comments" value="'+col2+'" >'+col2+
					'</td><td id="resolutionId'+count+'"><input type="hidden" name="medicalReconciliationDto['+count+'].resolutionId" value="'+$("#resolutions").val()+'">'+$("#resolutions option:selected").text()+
					'</td><td id="clarificationNeeded'+count+'"><input type="hidden" name="medicalReconciliationDto['+count+'].clarificationNeeded" value="">'+
					'<input type="hidden" id="TABLE'+count+'" value="2"><input type="hidden" id="rowNum" val='+count+'><input type="hidden" name="medicalReconciliationDto['+count+'].recommendation" value="PRIOR_TO_HOSPITALIZATION">'+
					'<span onclick="functionMedReconDel(this)" id="medReconDel" class="fi-x right" style="cursor: pointer;" title="Remove" data-tooltip>'+
					'</span><span onclick="functionMedReconEdit(this)" id="medReconEdit" data-id="'+count+'" class="fi-pencil" style=" float: left;  cursor: pointer; position: static;" title="Edit" data-tooltip></span></td></tr>');

		}else{
			$('#medicalReconciliationTable1 tbody')
			.append('<tr><td id="medicationDetails'+count+'"><input type="hidden" name="medicalReconciliationDto['+count+'].medicationDetails"  value="'+col1+'">'+col1+
					'</td><td id="clarificationNeeded'+count+'"><input type="hidden" name="medicalReconciliationDto['+count+'].clarificationNeeded" value="'+col2+'">'+col2+
					'</td><td id="resolutionId'+count+'"><input type="hidden" name="medicalReconciliationDto['+count+'].resolutionId" value="'+$("#resolutions").val()+'">'+$("#resolutions option:selected").text()+
					'</td><td id="comments'+count+'"><input type="hidden" name="medicalReconciliationDto['+count+'].comments" value="" >'+
					'<input type="hidden" id="TABLE'+count+'" value="1"><input type="hidden" id="rowNum" val='+count+'><input type="hidden" name="medicalReconciliationDto['+count+'].recommendation" value="HOSPITAL_RECOMMENDED">'+
					'<span onclick="functionMedReconDel(this)" id="medReconDel" class="fi-x right" style="cursor: pointer;" title="Remove" data-tooltip>'+
					'</span><span onclick="functionMedReconEdit(this)" id="medReconEdit" data-id="'+count+'" class="fi-pencil" style=" float: left;  cursor: pointer; position: static;" title="Edit" data-tooltip></span></td></tr>');
		}

	}

	$('#medicalReconcilationRowId').val("");
}

function setMedicalReconciliationRecords(medicalReconciliationId,resolutionId,recommendation,medicationDetails,clarificationNeeded,comments){
	var count = rowNum++;
	if(recommendation == "HOSPITAL_RECOMMENDED" &&  !comments){
		$('#medicalReconciliationTable1 tbody')
		.append('<tr id="medicRow'+count+'"><td id="medicationDetails'+count+'"><input type="hidden" name="medicalReconciliationDto['+count+'].medicationDetails" value="'+medicationDetails+'">'+medicationDetails+	
				'</td><td id="clarificationNeeded'+count+'"><input type="hidden" name="medicalReconciliationDto['+count+'].clarificationNeeded" value="'+clarificationNeeded+'">'+clarificationNeeded+
				'</td><td id="resolutionId'+count+'"><input type="hidden" name="medicalReconciliationDto['+count+'].resolutionId" value="'+resolutionId+'">'+$("#resolutions option[value='"+resolutionId+"']").text()+ //resolutionId+
				'</td><td id="medicalReconciliationId'+count+'"><input type="hidden" name="medicalReconciliationDto['+count+'].medicalReconciliationId" value="'+medicalReconciliationId+'">'+
				'<input type="hidden" id="TABLE'+count+'" value="1"><input type="hidden" name="medicalReconciliationDto['+count+'].recommendation" value="HOSPITAL_RECOMMENDED">'+
				'<span onclick="functionMedReconDel(this)" id="medReconDel" dataDel-id="'+count+'" class="fi-x right" style="cursor: pointer;" title="Remove" data-tooltip>'+
				'</span><span onclick="functionMedReconEdit(this)" id="medReconEdit" data-id="'+count+'" class="fi-pencil" style=" float: left;  cursor: pointer; position: static;" title="Edit" data-tooltip></span></td></tr>');
	}else if(recommendation == "PRIOR_TO_HOSPITALIZATION"){
		$('#medicalReconciliationTable2 tbody')
		.append('<tr id="medicRow'+count+'"><td id="medicationDetails'+count+'"><input type="hidden" name="medicalReconciliationDto['+count+'].medicationDetails" value="'+medicationDetails+'">'+medicationDetails+	
				'</td><td id="comments'+count+'"><input type="hidden" name="medicalReconciliationDto['+count+'].comments" value="'+comments+'">'+comments+
				'</td><td id="resolutionId'+count+'"><input type="hidden" name="medicalReconciliationDto['+count+'].resolutionId" value="'+resolutionId+'">'+$("#resolutions option[value='"+resolutionId+"']").text()+	//resolutionId+
				'</td><td id="medicalReconciliationId'+count+'"><input type="hidden" name="medicalReconciliationDto['+count+'].medicalReconciliationId" value="'+medicalReconciliationId+'">'+
				'<input type="hidden" id="TABLE'+count+'" value="2"><input type="hidden" name="medicalReconciliationDto['+count+'].recommendation" value="PRIOR_TO_HOSPITALIZATION">'+
				'<span onclick="functionMedReconDel(this)" id="medReconDel" dataDel-id="'+count+'" class="fi-x right" style="cursor: pointer;" title="Remove" data-tooltip>'+
				'</span><span onclick="functionMedReconEdit(this)" id="medReconEdit" data-id="'+count+'" class="fi-pencil" style=" float: left;  cursor: pointer; position: static;" title="Edit" data-tooltip></span></td></tr>');
	}
}

$(function(){

	$("#medReconPart2Add").click(function(){
		$("#medications").html('Before Hospitalization Not Recommended by Hospital:');
		$('#comments').html('Comments: <textarea id="comnts" name="column2" cols="100" rows="2"></textarea>');
		$('#medicalReconciliationPopup').show();
		$("#medicalReconciliationPopup").dialog(opt).dialog("open");
		$("#medicalReconciliationPopup").dialog('option', 'title', 'Medications Prior to Hospitalization Needing Clarification');
		$('#admissionMedication').val('');
	});

	$("#admission").click(function(){
		$("#admissionForm").trigger('submit');
	});
	$("#AcuteCareRevialModal >dl>dd").click(function(){
		$(".content").scrollTop(0);
		});

		$("#NewPatientModal > dl>dd ").click(function(){

		$(".NewPatientModalDiv").scrollTop(0);
		});
 
});

// ********************************** show Hide Function start ******************************************* 

function otherLanguageShowHide(){
	if ($('#canSpeakEnglish1').is(':checked')) {
		$("#otherLanguage").val('');
		$('#otherLanguageShowHide').hide();
	} 
	if ($('#canSpeakEnglish2').is(':checked')) {
		$('#otherLanguageShowHide').show();
	}
}

function otherEthnicityTypeShowHide(){
	if ($('#ethnicityType4').is(':not(:checked)')) {
		$("#otherEthnicityType").val('');
		$('#otherEthnicityTypeShowHide').hide();
	} 
	if ($('#ethnicityType4').is(':checked')) {
		$('#otherEthnicityTypeShowHide').show();
	}
}

function otherAdvanceDirectivesShowHide(e){
	if ($(e).is(':not(:checked)')) {
		$("#otherAdvanceDirectives").val('');
		$('#otherAdvanceDirectivesShowHide').hide();
	} 
	if ($(e).is(':checked')) {
		$('#otherAdvanceDirectivesShowHide').show();
	}
}

function specifyGoalsOfCareDiscussedShowHide(){
	if ($("#goalsOfCareDiscussed2").is(':not(:checked)')) {
		$("#specifyGoalsOfCareDiscussed").val('');
		$('#specifyGoalsOfCareDiscussedShowHide').hide();
	} 
	if ($("#goalsOfCareDiscussed2").is(':checked')) {
		$('#specifyGoalsOfCareDiscussedShowHide').show();
	}
}

function nurseToNurseVerbalReportTextShowHide(){
	if ($("#nurseToNurseVerbalReport2").is(':not(:checked)')) {
		$("#nurseToNurseVerbalReportText").val('');
		$('#nurseToNurseVerbalReportTextShowHide').hide();
	} 
	if ($("#nurseToNurseVerbalReport2").is(':checked')) {
		$('#nurseToNurseVerbalReportTextShowHide').show();
	}
}

function inAdmissionKcivsPainRatingShowHide(e){
	if ($(e).is(':not(:checked)')) {
		$("#inAdmissionKcivsPainRating").val('');
		$('#inAdmissionKcivsPainRatingShowHide').hide();
	} 
	if ($(e).is(':checked')) {
		$('#inAdmissionKcivsPainRatingShowHide').show();
	}
}

function inAdmissionHrcOrTiPrecShowHide(e){
	if ($(e).is(':not(:checked)')) {
		$("#inAdmissionHrcOrTiPrec").val('');
		$('#inAdmissionHrcOrTiPrecShowHide').hide();
	} 
	if ($(e).is(':checked')) {
		$('#inAdmissionHrcOrTiPrecShowHide').show();
	}
}

function HeartFailureShowHide(e){
	if ($(e).is(':not(:checked)')) {
		$("#inAdmissionHrcOrTiHfEfDw, #inAdmissionHrcOrTiHfEfp, #inAdmissionHrcOrTiHfExthadmssionLstdte").val('');
		$("#inAdmissionHrcOrTiHfNewdiag, #inAdmissionHrcOrTiHfExthadmssion, #inAdmissionHrcOrTiHfEf").prop("checked", false);
		$('#HeartFailureShowHide').hide();
	} 
	if ($(e).is(':checked')) {
		$('#HeartFailureShowHide').show();
	}
}

function AnticoagulatedShowHide(e){
	if ($(e).is(':not(:checked)')) {
		$("#inAdmissionhrcOrTiAcReasons").val('');
		$("#inAdmissionHrcOrTicROthrText").val('');
		$('#AnticoagulatedShowHide').hide();
	} 
	if ($(e).is(':checked')) {
		$('#AnticoagulatedShowHide').show();
	}
}

function inAdmissionHrcOrTicROthrTextShowHide(){
	if($("#inAdmissionhrcOrTiAcReasons").val() == 'HRC_OR_TI_AC_R_OTHR'){
		$("#inAdmissionHrcOrTicROthrTextShowHide").show();
	}else{
		$("#inAdmissionHrcOrTicROthrTextShowHide").hide();
		$("#inAdmissionHrcOrTicROthrText").val('');
	}
}

function inAdmissionHrcOrTiGiOthrTextShowHide(){
	if($("#inAdmissionGoalInr").val() == 'HRC_OR_TI_GI_OTHR'){
		$("#inAdmissionHrcOrTiGiOthrTextShowHide").show();
	}else{
		$("#inAdmissionHrcOrTiGiOthrTextShowHide").hide();
		$("#inAdmissionHrcOrTiGiOthrText").val('');
	}
}

function inAdmissionHrcOrTiOpIndicationsTextShowHide(){
	if($("#inAdmissionHrcOrTiOpIndications").val() == 'HRC_OR_TI_OP_SD'){
		$("#inAdmissionHrcOrTiOpIndicationsTextShowHide").show();
	}else{
		$("#inAdmissionHrcOrTiOpIndicationsTextShowHide").hide();
		$("#inAdmissionHrcOrTiOpIndicationsText").val('');
	}
}

function OnPPIShowHide(e){
	if ($(e).is(':not(:checked)')) {
		$("#inAdmissionHrcOrTiOpIndications").val('');
		$("#inAdmissionHrcOrTiOpIndicationsText").val('');
		$('#OnPPIShowHide').hide();
	} 
	if ($(e).is(':checked')) {
		$('#OnPPIShowHide').show();
	}
}

function OnAntibioticsShowHide(e){
	if ($(e).is(':not(:checked)')) {
		$("#inAdmissionHrcOrTiOabIndctn").val('');
		$("#inAdmissionHrcOrTiOabTtcd").val('');
		$("#inAdmissionHrcOrTiOabDs").val('');
		$('#OnAntibioticsShowHide').hide();
	} 
	if ($(e).is(':checked')) {
		$('#OnAntibioticsShowHide').show();
	}
}

function DiabeticShowHide(e){
	if ($(e).is(':not(:checked)')) {
		$("#inAdmissionHrcOrTiDMrgd").val('');
		$("#inAdmissionHrcOrTiDMrgt").val('');
		$('#DiabeticShowHide').hide();
	} 
	if ($(e).is(':checked')) {
		$('#DiabeticShowHide').show();
	}
}
function inAdmissionMAndAAllrgsYSpcfyShowHide(e){
	if($(e).val() == 'M_AND_A_ALLRGS_Y'){
		$("#inAdmissionMAndAAllrgsYSpcfyShowHide").show();
	}else{
		$("#inAdmissionMAndAAllrgsYSpcfyShowHide").hide();
		$("#inAdmissionMAndAAllrgsYSpcfy").val('');
	}
}

function inAdmissionMAndAPmTextShowHide(e){
	if($(e).val() == 'M_AND_A_PM_Y'){
		$("#inAdmissionMAndAPmTextShowHide").show();
	}else{
		$("#inAdmissionMAndAPmTextShowHide").hide();
		$("#inAdmissionMAndAPmText").val('');
	}
}

function inAdmissionContinenceTextShowHide(e){
	if($(e).val() == 'NCPSF_C_CATHTR'){
		$("#inAdmissionContinenceTextShowHide").show();
	}else{
		$("#inAdmissionContinenceTextShowHide").hide();
		$("#inAdmissionContinenceText").val('');
	}
}

//function inAdmissionReasonforcatheterTextShowHide(e){
//	if($(e).val() == 'NCPSF_C_RFC_OTHR'){
//		$("#inAdmissionReasonforcatheterTextShowHide").show();
//	}else{
//		$("#inAdmissionReasonforcatheterTextShowHide").hide();
//		$("#inAdmissionReasonforcatheterText").val('');
//	}
//}

function inAdmissionReasonforcatheterTextShowHide(e){
	if($("#inAdmissionReasonforcatheter3").is(':checked'))
	{
		$(".catheterothertext").show();
	}
	else if($("#inAdmissionReasonforcatheter3").not(':checked'))
	{
		$(".catheterothertext").hide();
	}
}

function inAdmissionNcpsfCTextShowHide(e){
	if($(e).val() == 'NCPSF_C_DOLBM'){
		$("#inAdmissionNcpsfCTextShowHide").show();
	}else{
		$("#inAdmissionNcpsfCTextShowHide").hide();
		$("#inAdmissionNcpsfCText").val('');
	}
}

function inAdmissionNcpsfNAndHTfDiTextShowHide(e){
	if($(e).val() == 'NCPSF_N_AND_H_TF_DI'){
		$("#inAdmissionNcpsfNAndHTfDiTextShowHide").show();
	}else{
		$("#inAdmissionNcpsfNAndHTfDiTextShowHide").hide();
		$("#inAdmissionNcpsfNAndHTfDiText").val('');
	}
}

function TubeFeedProductShowHideClass(e){
	if ($(e).is(':not(:checked)')) {
		$("#inAdmissionNcpsfNAndHTfTfpt").val('');
		$("#inAdmissionncpsfNAndHTfTfpRteCcPerH").val('');
		$("#inAdmissionncpsfNAndHTfTfpEveryHOrDay").val('');
		$('.TubeFeedProductShowHideClass').hide();
	} 
	if ($(e).is(':checked')) {
		$('.TubeFeedProductShowHideClass').show();
	}
}

function inAdmissionNcpsfTtdPiccTextShowHide(e){
	if($(e).val() == 'NCPSF_TTD_PRTCTH'){
		$("#inAdmissionNcpsfTtdPiccTextShowHide").show();
	}else{
		$("#inAdmissionNcpsfTtdPiccTextShowHide").hide();
		$("#inAdmissionNcpsfTtdPiccText").val('');
	}
}

function inAdmissionCardiacTextShowHide(e){
	if($(e).val() == 'NCPSF_TTD_C_OTHR'){
		$("#inAdmissionCardiacTextShowHide").show();
	}else{
		$("#inAdmissionCardiacTextShowHide").hide();
		$("#inAdmissionCardiacText").val('');
	}
}

function inAdmissionRespiratoryO2TextShowHide(e){
	if($(e).val() == 'NCPSF_TTD_R_O2'){
		$("#inAdmissionRespiratoryO2TextShowHide").show();
	}else{
		$("#inAdmissionRespiratoryO2TextShowHide").hide();
		$("#inAdmissionRespiratoryO2Text").val('');
	}
	if($(e).val() == 'NCPSF_TTD_R_TS'){
		$("#inAdmissionRespiratoryTsTextShowHide").show();
	}else{
		$("#inAdmissionRespiratoryTsTextShowHide").hide();
		$("#inAdmissionRespiratoryTsText").val('');
	}
}

function Pressureulcer1ShowHide(e){
	if ($(e).is(':not(:checked)')) {
		$("#inAdmissionNcpsfScPuStage").val('');
		$("#inAdmissionNcpsfScPuLocation").val('');
		$('.PressureulcerShowHideClass1').hide();
	} 
	if ($(e).is(':checked')) {
		$('.PressureulcerShowHideClass1').show();
	}
}

function Pressureulcer2ShowHide(e){
	if ($(e).is(':not(:checked)')) {
		$("#inAdmissionNcpsfScSpuStage").val('');
		$("#inAdmissionNcpsfScSpuLocation").val('');
		$('.PressureulcerShowHideClass2').hide();
	} 
	if ($(e).is(':checked')) {
		$('.PressureulcerShowHideClass2').show();
	}
}

function inAdmissionNcpsfScOthrSpcfyShowHide(e){
	if ($(e).is(':not(:checked)')) {
		$("#inAdmissionNcpsfScOthrSpcfy").val('');
		$('#inAdmissionNcpsfScOthrSpcfyShowHide').hide();
	} 
	if ($(e).is(':checked')) {
		$('#inAdmissionNcpsfScOthrSpcfyShowHide').show();
	}
}

function inAdmissionRisksandPrecautionsTextShowHide(e){
	if($(e).val() == 'NCPSF_R_AND_P_OTHR'){
		$("#inAdmissionRisksandPrecautionsTextShowHide").show();
	}else{
		$("#inAdmissionRisksandPrecautionsTextShowHide").hide();
		$("#inAdmissionRisksandPrecautionsText").val('');
	}
	
}

function inAdmissionNcpsfIciITextShowHide(e){
	if($(e).val() == 'NCPSF_ICI_I_Y'){
		$("#inAdmissionNcpsfIciITextShowHide").show();
	}else{
		$("#inAdmissionNcpsfIciITextShowHide").hide();
		$("#inAdmissionNcpsfIciIText").val('');
	}
	
}

function inAdmissionNcpsfIciIPTextShowHide(e){
	if($(e).val() == 'NCPSF_ICI_I_P_YES'){
		$("#inAdmissionNcpsfIciIPTextShowHide").show();
	}else{
		$("#inAdmissionNcpsfIciIPTextShowHide").hide();
		$("#inAdmissionNcpsfIciIPText").val('');
	}
}

function InfluenzaShowHide(e){
	if ($(e).is(':not(:checked)')) {
		$("#inAdmissionNcpsfIciI").val('');
		$("#inAdmissionNcpsfIciIText").val('');
		$('#InfluenzaShowHide').hide();
	} 
	if ($(e).is(':checked')) {
		$('#InfluenzaShowHide').show();
	}
}

function PneumococcalShowHide(e){
	if ($(e).is(':not(:checked)')) {
		$("#inAdmissionNcpsfIciIP").val('');
		$("#inAdmissionNcpsfIciIPText").val('');
		$('#PneumococcalShowHide').hide();
	} 
	if ($(e).is(':checked')) {
		$('#PneumococcalShowHide').show();
	}
}

function inAdmissionHrcOrTiHfEfpShowHide(e){
	if ($(e).is(':not(:checked)')) {
		$("#inAdmissionHrcOrTiHfEfp").val('');
		$('#inAdmissionHrcOrTiHfEfpShowHide').hide();
	} 
	if ($(e).is(':checked')) {
		$('#inAdmissionHrcOrTiHfEfpShowHide').show();
	}
}

function genericConfirm(status) {
	 var defer = $.Deferred();
	$("#dialog-confirm").html(
					'<center><p  class="confirm-dialog" style="font-size:22px !important">'+status+'</center>');
	$("#dialog-confirm").dialog({
		resizable : false,
		modal : true,
		height : 250,
		width : 380,
		buttons : {
			"Yes" : function() {
				defer.resolve(true);
				$(this).dialog('close');

			},
			"No" : function() {
				defer.resolve(false);
				$(this).dialog('close');

			}
		}

	});
	return defer.promise();
};

//********************************** show Hide Function end **********************************************





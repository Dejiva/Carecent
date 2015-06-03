$(document).ready(function(){

//	setting input valitations
	$("#careGiverTelephoneNumber").mask("(999) 999-9999");
	$("#guardianTelephoneNumber").mask("(999) 999-9999");
	$("#sSNumber").mask("999-99-9999");
	$("#dishcargingRNTelephoneNumber").mask("(999) 999-9999 ? ext:99999");
	$("#dishcargingMDTelephoneNumber").mask("(999) 999-9999 ? ext:99999");
	$("#primaryCarePhysicianTelephoneNumber").mask("(999) 999-9999 ? ext:99999");
	$("#postAcuteCareTelephoneNumber").mask("(999) 999-9999 ? ext:99999");
	$("#specialist1TelephoneNumber").mask("(999) 999-9999 ? ext:99999");
	$("#specialist2TelephoneNumber").mask("(999) 999-9999 ? ext:99999");
	
	$("#inAdmissionHrcOrTiHfEfp").inputmask({alias:"Regex",regex:"^([1-9]{1}|[1-9][0-9]|100)$"});
	$("#dob").inputmask("mm/dd/yyyy",{ showMaskOnHover: false });
	$("#inAdmissionHrcOrTiHfExthadmssionLstdte").inputmask("mm/dd/yyyy");
	$("#hospitalAdmissionDate").inputmask("mm/dd/yyyy");
	$("#inAdmissionHrcOrTiOabDs").inputmask("mm/dd/yyyy");
	$("#inAdmissionHrcOrTiDMrgd").inputmask("mm/dd/yyyy");
//	$("#M_AND_A_PM_LD").inputmask({ "placeholder": "mm/dd/yyyy hh:mm am/pm"});	
//	$("#M_AND_A_PM_LD").inputmask("datetime12",{"mask": "mm/dd/yyyy",showMaskOnHover: false });		// need to confirm from Mr.Raju sir
	$("#inAdmissionNcpsfCText").inputmask("mm/dd/yyyy");
	$("#inAdmissionContinenceText").inputmask("mm/dd/yyyy");
	$("#inAdmissionNcpsfNAndHTfDiText").inputmask("mm/dd/yyyy");
	$("#inAdmissionNcpsfIciIText").inputmask("mm/dd/yyyy");
	$("#inAdmissionNcpsfIciIPText").inputmask("mm/dd/yyyy");
	$("#inAdmissionNcpsfTtdPiccText").inputmask("mm/dd/yyyy");
	$("#inAdmissionKcivsTimeTaken").inputmask('h:s t',{ "placeholder": "hh:mm am/pm" });
	$("#inAdmissionHrcOrTiDMrgt").inputmask('h:s t',{ "placeholder": "hh:mm am/pm" });
//	$("#KCIVS_PAIN_RATING").mask("9?99999");
	$("#inAdmissionKcivsPainRating").inputmask({ alias:"Regex", regex:"^([0-9]|10)$" }); //.inputmask("decimal", { allowMinus: false, allowPlus: false});
	$("#inAdmissionHrcOrTiAcRDurtn").inputmask("decimal", { allowMinus: false, allowPlus: false});
	$("#inAdmissionKcivsTemp").inputmask("decimal", { allowMinus: false, allowPlus: false});
	
	// need clarification
	$("#inAdmissionRespiratoryO2Text").inputmask({alias:"Regex",regex:"^([1-9]{1}|[1-9][0-9]|100)$"});
	$("#inAdmissionRespiratoryTsText").inputmask("decimal", { allowMinus: false, allowPlus: false});
	$("#inAdmissionNcpsfNAndHTfDiFwbCc").inputmask("decimal", { allowMinus: false, allowPlus: false});
	$("#inAdmissionNcpsfNAndHTfDiFwbHrs").inputmask("decimal", { allowMinus: false, allowPlus: false});
	$("#inAdmissionncpsfNAndHTfTfpRteCcPerH").inputmask("decimal", { allowMinus: false, allowPlus: false});
	$("#inAdmissionncpsfNAndHTfTfpEveryHOrDay").inputmask("h:s",{ "placeholder": "hh/mm" });
	// end clarification
	$("#inAdmissionKcivsBp").inputmask("999/99", { allowMinus: false, allowPlus: false});
	$("#inAdmissionKcivsHr").inputmask("decimal", { allowMinus: false, allowPlus: false});
	$("#inAdmissionKcivsRr").inputmask("decimal", { allowMinus: false, allowPlus: false});
	$("#inAdmissionKcivsWght").inputmask("decimal", { allowMinus: false, allowPlus: false});
	//$("#KCIVS_02SAT").inputmask("decimal", { allowMinus: false, allowPlus: false});
	$("#inAdmissionKcivs02sat").inputmask({alias:"Regex",regex:"^([1-9]{1}|[1-9][0-9]|100)$"});
	$("#inAdmissionHrcOrTiHfEfDw").inputmask("decimal", { allowMinus: false, allowPlus: false});
	$("#inAdmissionHrcOrTiGiOthrText").inputmask("decimal", { allowMinus: false, allowPlus: false});
	$("#inAdmissionHrcOrTiOabTtcd").inputmask("decimal", { allowMinus: false, allowPlus: false});
	// S-bar/Observation validation start....
	$("#oximetryOnO2").inputmask("decimal", { allowMinus: false, allowPlus: false});
	
	// AcuteCare form Validations.......
	
	$("#inAcutenhContactPTel").mask("(999) 999-9999 ? ext:99999");
	$("#inAcutekciVsTt").inputmask('h:s t',{ "placeholder": "hh:mm am/pm" });
	$("#inAcutekciMrpmTime").inputmask('h:s t',{ "placeholder": "hh:mm am/pm" });
	$("#inAcutekciVsBp").inputmask("999/99", { allowMinus: false, allowPlus: false});
	$("#inAcutekciVsHr").inputmask("decimal", { allowMinus: false, allowPlus: false});
	$("#inAcutekciVsRr").inputmask("decimal", { allowMinus: false, allowPlus: false});
	$("#inAcutekciVsTemp").inputmask("decimal", { allowMinus: false, allowPlus: false});
	$("#inAcutekciVsO2sat").inputmask("decimal", { allowMinus: false, allowPlus: false});
	$("#inAcutedtO2Lm").inputmask("decimal", { allowMinus: false, allowPlus: false});
	$("#fromHospitalDischargeDate").inputmask("mm/dd/yyyy");
	
	
	
	// End - AcuteCare form Validations
	// Validating current or feature dates......
	$("#dob,#hospitalAdmissionDate").datepicker({
		changeMonth : true,
		changeYear : true,
		yearRange : "-125:+0",
		dateFormat : "mm/dd/yy",
		maxDate : new Date(),
	    onSelect: function(selected) {
	      $("#doj,#fromHospitalDischargeDate").datepicker("option","minDate", selected);
	    }
	});

	$("#doj,#fromHospitalDischargeDate").datepicker({ 
		changeMonth : true,
		changeYear : true,
		yearRange : "-125:+0",
		dateFormat : "mm/dd/yy",
		maxDate : new Date(),
	      onSelect: function(selected) {
	       $("#dob,#hospitalAdmissionDate").datepicker("option","maxDate", selected);
	    }
	}); 
	
	
});

	// disallow the future dates for date input fields....
function validateDate(e){
	var today = $.datepicker.formatDate("mm/dd/yy", new Date());
	var currentdateTime = today+" 12:00:00 am"; 
	try{
		var inputValue = $(e).val().split(" ")[0];
		if(Date.parse(inputValue) <= Date.parse(today)){
			return true;
		}else{
			$(e).val(currentdateTime);
			return false;
		}
	}catch(ex){}
}

// Allow only letters from a to z and A to Z
$('.alphaonly').bind('keyup blur',function(){ 
    var node = $(this);
    node.val(node.val().replace(/[^a-zA-Z]/g,'') ); }
);

// Validate whether AssignMD is required or not......
//$(document).on('change', '#staffRoleId', function() {
 function functionStaffRoleId(e) {
	var selectedRole = $("#staffRoleId option:selected").text();
	var selectedAsignDoc = $("#docRoleId option:selected").val();
	if (selectedRole == "NP" || selectedRole == "PA") {
		if(selectedAsignDoc == 0){
			$('#docRoleId option:eq(0)').val('');
		}
		$("#Asigndoc").show();
	} else {
		$('#docRoleId option:eq(0)').val('0');
		$("#Asigndoc").hide();
	}
}




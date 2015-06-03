<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- New Patient Block  -->
<style>
	input[type="text"]{
		margin: 0 0 0 0rem !important;
	}
	form .row .row {
	    margin: 0 0rem !important;
	}
	.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-draggable.ui-resizable {
	    left: 25% !important;
	}
</style>
<div id="medicalReconciliationPopup" style="display: none;">
	<input type="hidden" id="medicalReconcilationRowId" name="medicalReconcilationRowId" value=""/>
	<div class='row'>
	  <div class="large-12 columns padding-lr">
		<div class="large-7 column">
			<label id="medications">Recommended by Hospital at Discharge:</label><input type="text" id="admissionMedication" name="column1">
		</div>
		<div class="large-2 column">
			<label id="resolution">Resolution:</label>
			<select id="resolutions" style="font-family: Raleway; font-size: 15px;">
			<option value="">--select--</option>
						<c:forEach var="resolution" items="${sessionScope.resolutionsMap}">
						<option value="${resolution.key}">${resolution.value}</option>
						</c:forEach>
			</select>
		</div>
	 </div>
	</div>
	<div class='row'>
	 <div class="large-12 columns padding-lr">
		<label id="comments">Clarification Needed*: <textarea id="admissionClarification" name="column2" cols="100" rows="2"></textarea></label>
	 </div>
	</div>
	<br/>
	<div class='row'>
		<button class='button small right' style='padding: 5px 15px; margin: 0;' name='Add' onclick="medicalReconciliationRecordSave()">Save</button>
		<button class='button right small' style='padding: 5px 15px; margin: 0 15px 0 0;' name='Remove' onclick="openJueryMedicalReconciliationPopupClose('#medicalReconciliationPopup')">Cancel</button>
	</div>
</div>
<script type="text/javascript">
var rowNum = 0;
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
</script>

<div id="NewPatientModal" class="reveal-modal raleway" data-reveal style="top: 10px !important;">
	<p class="lead" style="text-align: center;"><span id="admistionPatientName" class="patientTitle" ></span>Hospital to Post-Acute Care Transfer Form</p>
	<a class="admission-close-reveal-modal" onclick="functionClearAdmissionView(this)">&#215;</a>
	<dl class="tabs" data-tab style=" bottom: 10px; position: relative;">
	  <dd class="active" id="AdmissionfirstTab"><a href="#panel1" style="font-family: Raleway;">Patient Registration</a></dd>
	  <dd><a href="#panel2" style="font-family: Raleway;">Clinical Information</a></dd>
	  <dd><a href="#panel3" style="font-family: Raleway;">Care Directives</a></dd>
	  <dd><a href="#panel4" style="font-family: Raleway;">Medication Reconciliation</a></dd>
	</dl>
	
<%@page import="com.codecoop.interact.core.util.Constants"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- New Patient Block  -->
<head>
<title>CARECENT</title>
<link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png" type="image/x-icon">
</head>
<!-- ********************************************ACUTE CARE FORM************************************************************** -->
<div id="AcuteCareRevialModal" class="reveal-modal raleway" data-reveal style="top: 10px !important; height:625px;">
	<p class="lead" style="text-align: center;"><span id="acuteCarePatientName" class="patientTitle" ></span> Nursing Home to Hospital Transfer Form</p>
	<input type="hidden" id="acuteCarePatientEpisodeId" val="" />
	<a class="close-reveal-modal">&#215;</a>
	<dl class="tabs" data-tab style=" bottom: 10px; position: relative;">
	  <dd class="active"><a href="#panel1" style="font-family: Raleway;">Resident Information</a></dd>
	  <dd><a href="#panel2" style="font-family: Raleway;">Clinical Information</a></dd>
	  <dd><a href="#panel3" style="font-family: Raleway;">Additional Information</a></dd>
	</dl>
	
		<form:form method="post"  commandName="acuteCareModel" class="acuteCareModel_class" action="${pageContext.request.contextPath}/acuteCare/saveOrUpdate">
			<div id="acuteCareFormErrors">
			<form:errors path="*" cssClass="error"/>
			</div>
			<div id="acuteCareModalMessage" style="color: green; font-size: large;text-align: center;"></div>
			<div class="content" style="height:360px; overflow: auto; ">
			<div class="tabs-content">
				<div class="content active" id='panel1'  >
					<div class='panel col_border'>
					<p class="lead p_heading">Resident Information</p>
					<div class="row">
					      <div class="large-12 columns padding-lr">
							<div class="large-2 column padding-lr">
								<label>Resident First Name:  <form:input readonly="true" path="inAcutefirstName"/></label>
							</div>
							<div class="large-2 column padding-lr">
								<label>Resident Last Name:  <form:input  readonly="true" path="inAcutelastName"/></label>
							</div>
							<div class="large-4 column padding-lr">
							 <label>Language:</label>
							   <div class="small-12 columns">
							     <form:radiobutton disabled="true"  path="inAcutelanguage" value="English" label="English"/>
								 <form:radiobutton disabled="true"   path="inAcutelanguage" value="Other" label="Other"/>
									${acuteCareModel.inAcutelanguage == 'Other'  ? "<span class='small-5 columns inline right'>" : "<span class='small-5 columns inline right' hidden='hidden'>"}
								 <form:input readonly="true"  path="inAcutelanguageText"/>
								</span>
							   </div>
							</div>
							<div class="large-3 column padding-lr end">
								<label>Resident is:</label>
								<form:radiobutton   path="inAcuteresidentIs"  value="SNF/rehab" label="SNF/rehab"/>
								<form:radiobutton   path="inAcuteresidentIs"  value="Long-term" label="Long-term"/>
							</div>
						 </div>
						 <div class="large-12 columns padding-lr">
							<div class="large-2 column padding-lr">
								<label>Date Admitted: <form:input readonly="true" path="inAcutedateAdmited"/></label>
							</div>
							<div class="large-2 column padding-lr">
								<label>DOB: <form:input readonly="true" path="inAcutedob"/></label>
							</div>
							<div class="large-4 column end padding-lr">
								<label>Primary diagnosis(es) for admission: <form:input readonly="true"  path="inAcutepdsforAdmission"/></label>
							</div>
							<div class="large-3 column end padding-lr">
							<label>Outcome of Transfer:</label>
							<form:select   onclick="functionInOutcomeOfTransfer(this)" path="inAcuteOutComeTransfers">
							<form:option value="">-- select --</form:option>
							<form:option value="OCT_EDVO">ED Visit Only</form:option>
							<form:option value="OCT_AIP">Admitted Inpatient</form:option>
							<form:option value="OCT_AOB">Admitted Observation</form:option>
							<form:option value="OCT_ASU">AdmittedStatusUncertain</form:option>
							<form:option value="OCT_OT">Other</form:option>
							</form:select>
							${acuteCareModel.inAcuteOutComeTransfers == 'OCT_OT' ? "<span class='oct1' style='display:block;'>" : "<span class='oct1' style='display:none;'>" }
						
							<label><form:input  path="inAcuteOutComeTransfersTxt" /> </label>
							</span>
						    </div>
						</div>
						</div>
					</div>
					<div class='panel col_border'>
					<div class="row">
					  <div class="large-12 columns padding-lr">
							<div class="large-3 column padding-lr">
								<label>Sent To (name of hospital):  <form:input path="inAcutesentTo"/></label>
							</div>
							<div class="large-2 column padding-lr">
								<label>Date of transfer:  
									<c:choose>
									<c:when test="${acuteCareModel.inAcuteSnd2HsptlFlag}">
										<form:input  readonly="true"  path="inAcutedot"/>
									</c:when>
									<c:otherwise>
										<form:input onchange="return validateDate(this)" cssClass="inAcuteDateTimePicker " path="inAcutedot"/>
									</c:otherwise>
									</c:choose>
								</label>
							</div>
							<div class="large-3 column padding-lr">
								<label>Sent From (name of nursing home):  <form:input readonly="true" path="inAcutesendFrom"/></label>
							</div>
							<div class="large-2 column padding-lr end">
								<label>Unit:  <form:input path="inAcuteunit"/></label>
							</div>
							</div>
						</div>
					</div>
					<div class='panel col_border'>
					<div class="row">
					   <div class="large-12 columns padding-lr">
							<div class="large-2 column padding-lr">
								<label>Contact Person:  <form:input readonly="true" path="inAcutecntPerson"/></label>
							</div>
							<div class="large-2 column padding-lr end">
								<label>Tel:<form:input disabled="true" path="inAcutecntPsnTel"/></label>
							</div>
							<div class="large-6 column padding-lr end">
								<label>Relationship (check all that apply):</label>
								<div class="small-12">
							    	<form:radiobutton disabled="true" path="inAcutecntPsnRelationShip" value="Relative" label="Relative"/>
							    	<form:radiobutton disabled="true" path="inAcutecntPsnRelationShip" value=" Healthcareproxy " label=" Health care proxy"/>
							    	<form:radiobutton disabled="true" path="inAcutecntPsnRelationShip" value="Guardian" label="Guardian"/>
								    <form:radiobutton disabled="true" path="inAcutecntPsnRelationShip" value="Other" label="Other"/>
								</div>
							</div>
						  </div>
						  <div class="large-12 columns padding-lr">
							<div class="large-4 column padding-lr">
								<label>Notified of transfer?:</label> 
								<form:radiobutton path="inAcutenotifiedOfTransfer" value="Yes" label="Yes"/>
								<form:radiobutton path="inAcutenotifiedOfTransfer" value="No" label="No"/>
								
							</div>
							<div class="large-4 column left padding-lr">
								<label>Aware of clinical situation?</label> 
								<form:radiobutton path="inAcuteawarOfClinicalSituation" value="Yes" label="Yes"/>
								<form:radiobutton path="inAcuteawarOfClinicalSituation" value="No" label="No"/>
								
							</div>
							</div>
						</div>
					</div>
					<div class='panel col_border'>
					<div class="row">
					<p>Who to Call at the Nursing Home to Get Questions Answered</p>
							<div class="large-2 column padding-lr">
<%-- 								<label>Name/Title: <form:input path="inAcutenhContactPName"/></label> --%>
								<label>Name/Title:
								<form:select onchange="functionInAcutenhContactPerson(this)" path="inAcutenhContactPerson">
								<c:forEach items="${sessionScope.nursesIncurrentFacility}" var="nurse">
								<form:option work-Num="${nurse.value.workNumber}" value="${nurse.value.id}">${nurse.value.name}</form:option>
								</c:forEach> 
								</form:select>
								</label>
							</div>
							<div class="large-2 column padding-lr end">
								<label>Tel:  <form:input readonly="true" path="inAcutenhContactPTel"/></label>
							</div>
						</div>
					</div>
					<div class='panel col_border'>
					<div class="row">
					    <div class="large-12 columns">
							<div class="large-4 column padding-lr">
								<label>Primary Care Clinician in Nursing Home:</label>
								 <form:radiobutton disabled="true" path="inAcutepccInNh" value="MD" label="MD"/>
								 <form:radiobutton disabled="true" path="inAcutepccInNh" value="NP" label="NP"/>
								 <form:radiobutton disabled="true" path="inAcutepccInNh" value="PA" label="PA"/>
							</div>
							<div class="large-2 column padding-lr">
								<label>Name:  <form:input readonly="true" path="inAcutepccInNhName"/></label>
							</div>
							<div class="large-2 column padding-lr end">
								<label>Tel:  <form:input readonly="true" path="inAcutepccInNhTel"/></label>
							</div>
							</div>
						</div>
					</div>
					<div class='panel col_border'>
					<div class="row">
							<div class="large-12 columns padding-lr">
								<label>Code Status:</label>
								 <form:checkbox path="inAcutecodeStatus" value="FullCode" label="Full Code"/>
								 <form:checkbox path="inAcutecodeStatus" value="DNR" label="DNR"/>
								 <form:checkbox path="inAcutecodeStatus" value="DNI" label="DNI"/>
								 <form:checkbox path="inAcutecodeStatus" value="DNH" label="DNH"/>
								 <form:checkbox path="inAcutecodeStatus" value="ComfortCareOnly" label="Comfort Care Only"/>
								 <form:checkbox path="inAcutecodeStatus" value="Uncertain" label="Uncertain"/>
								 <form:hidden path="openMode"/>
							</div>
						</div>
					</div>
				</div>
				<div class="content " id='panel2'>
					<div class='panel col_border'>
					<div class="row">
							<p class="lead p_heading">Key Clinical Information</p>
								<div class="large-6 column padding-lr">
								<label>Reason(s) for transfer:  <form:input path="inAcutekciReasonTransfer"/></label>
								</div>
								<div class="large-6 column padding-lr">
								<label>Is the primary reason for transfer for diagnostic testing, not admission?:</label>
								 <div class="small-12 columns">
								 <div class="small-12 columns">
								  <form:radiobutton onclick="functionInAcutekciPrimaryReasonTransfer1(this)" path="inAcutekciPrimaryReasonTransfer" value="KCI_PRIMARY_REASON_TRANSFER_NO" label="No"/>
								  <form:radiobutton onclick="functionInAcutekciPrimaryReasonTransfer2(this)" path="inAcutekciPrimaryReasonTransfer" value="KCI_PRIMARY_REASON_TRANSFER_YES" label="Yes"/>
								  
								  	${acuteCareModel.inAcutekciPrimaryReasonTransfer == 'KCI_PRIMARY_REASON_TRANSFER_YES' ? "<span class='kciiy' style='display:block;'>" : "<span class='kciiy' style='display:none;'>" }
								   <div class="small-9 columns inline right" hidden="hidden" style="display:inline-flex">
								    <div class="small-2 columns">
								     <label>Tests:</label>		
								    </div>
								    <div class="small-12 columns padding-lr">						   
								     <form:input path="inAcutekciTests"/>	
								    </div>							   
								   </div>
								   </span>
								   </div>
								   </div>
								</div>
								<div class="large-12 columns padding-lr">
								<label>Relevant diagnoses: </label>
								 <div class="large-12 columns">
							 	  <div class="large-1 columns"><form:checkbox path="inAcuterelevantDiagnoses" value="KCI_RD_CHF" label="CHF"/></div>
								  <div class="large-1 columns"><form:checkbox path="inAcuterelevantDiagnoses" value="KCI_RD_COPD" label="COPD"/></div>
								  <div class="large-1 columns"><form:checkbox path="inAcuterelevantDiagnoses" value="KCI_RD_CRF" label="CRF"/></div>
								  <div class="large-1 columns"><form:checkbox path="inAcuterelevantDiagnoses" value="KCI_RD_DM" label="DM"/></div>
								  <div class="large-2 columns"><form:checkbox path="inAcuterelevantDiagnoses" value="KCI_RD_Ca" label="Ca(active treatment)"/></div>
								  <div class="large-2 columns"><form:checkbox path="inAcuterelevantDiagnoses" value="KCI_RD_DEMENTIA" label="Dementia"/></div>
								  <div class="large-1 columns"><form:checkbox onclick="functionInAcutekciRdOther(this)" path="inAcutekciRdOther" value="KCI_RD_OTHER" label="Other"/></div>
								  	${acuteCareModel.inAcutekciRdOther == 'KCI_RD_OTHER' ? "<span class='large-3 right columns kciro padding-lr' style='display:block;'>":"<span class='large-3 right columns kciro padding-lr' style='display:none;'>"}<form:input path="inAcutekciRdOtherTxt"/></div>								 
								 </span>
								</div>
								<div class="large-12 columns padding-lr">
								<label>Vital Signs:</label>
								<div class="small-12 columns">
								  <div class="large-1 column padding-lr">
								   <label>BP<form:input path="inAcutekciVsBp"/></label>								   
								  </div>
								  <div class="large-1 column padding-lr">
								    <label>HR<form:input path="inAcutekciVsHr"/></label>
								  </div>
								  <div class="large-1 column padding-lr">
								   <label>RR<form:input path="inAcutekciVsRr"/></label>
								  </div>
								  <div class="large-1 column padding-lr">
								   <label>Temp<form:input path="inAcutekciVsTemp"/></label>
								  </div>
								  <div class="large-1 column padding-lr">
								   <label>O2 Sat<form:input path="inAcutekciVsO2sat"/></label>
								  </div>
								  <div class="large-2 column end padding-lr">
								   <label>Time taken (am/pm)<form:input path="inAcutekciVsTt"/></label>
								  </div>
								 </div>
								</div>
								<div class="row">
								 <div class="large-3 column padding-lr">
								  <label>Most recent pain level:</label>
								     <form:input path="inAcutekciMrpl"/>
								 </div> 
								 <div class="large-1 column padding-lr">	
								  <label>&nbsp;&nbsp;&nbsp;&nbsp;</label>						  
								  <form:checkbox path="inAcutekciMrplNa" value="" label="(N/A):"/>								  
							     </div> 
							     <div class="large-2 end column padding-lr">
								  <label>Pain location:  <form:input path="inAcutekciMrplPl"/></label>
								 </div>
								</div>
								<div class="row">
								 <div class="large-2 column padding-lr">
								   <label>Most recent pain med:  <form:input path="inAcutekciMrpm"/></label>
								 </div>
								 <div class="large-2 column padding-lr">
								   <label>Date given:  <form:input onchange="return validateDate(this)"  path="inAcutekciMrpmDg" cssClass="datepicker"/></label>
								 </div>
								 <div class="large-2 end column padding-lr">
								   <label>Time (am/pm):  <form:input path="inAcutekciMrpmTime"/></label>
								 </div>
								</div>
						</div>
					</div>
				<div class='panel col_border'>
					<div class="row">
						<div class="large-6 column padding-lr">
								<label>Usual Mental Status:</label>
								<div class="padding-lr">
								 <form:select path="inAcuteumsAlert"  multiple="true" cssStyle="height: 84px !important;">
								  <form:option value="">---select---</form:option>
								 <form:option value="UMS_ALERT_ORIENTED">Alert, oriented, follows instructions</form:option>
								 <form:option value="UMS_ALERT_DISORIENTED">Alert, disoriented, but can follow simple instructions</form:option>
								 <form:option value="UMS_ALERT_DISORIENTED_NOT_FOLLOW">Alert, disoriented, but cannot follow simple instructions</form:option>
								 <form:option value="UMS_NOT_ALERT">Not Alert</form:option>
								 </form:select>
								 </div>
						</div>
						<div class="large-6 column padding-lr">
								<label>Usual Functional Status:</label>
								<div class="padding-lr">
								 <form:select path="inAcuteufsAmbulates"  multiple="true" cssStyle="height: 84px !important;">
								  <form:option value="">---select---</form:option>
								 <form:option value="UFS_AD">Ambulates independently</form:option>
								 <form:option value="UFS_AAD">Ambulates with assistive device</form:option>
								 <form:option value="UFS_AHA">Ambulates only with human assistance</form:option>
								 <form:option value="UFS_NA">Not ambulatory</form:option>
								 </form:select>
								 </div>
						</div>
<!-- 					</div> -->
<!-- 					<div class="large-12 columns padding-lr"> -->
						<div class="large-6 column padding-lr">
								<label>Additional Clinical Information:</label>
								<div class="padding-lr">
								 <form:select path="inAcuteaciIncluded"  multiple="true" cssStyle="height: 84px !important;">
								 <form:option value="">---select---</form:option>
								 <form:option value="ACI_SBARACNI">SBAR Acute Change in Condition Note included</form:option>
								 <form:option value="ACI_OCNI">Other clinical notes included</form:option>
								 </form:select>
								 </div>
						</div>
						<div class="large-6 column padding-lr">
						    <div class="large-12">
						    <div class="padding-lr">
								<label>Date of last tetanus vaccination (if known):</label>
								<form:input onchange="return validateDate(this)"  path="inAcuteaciDtv" cssClass="datepicker"/>
						    </div>
						    </div>
						</div>
					</div>
				</div>
				<div class='panel col_border'>
					<p class="lead p_heading">Devices and Treatments</p>
					<div class="row">
						<div class="large-3 columns padding-lr" style="display:flex">
						  <label><form:checkbox path="inAcutedtO2" value="DT_O2"/>O2 at</label>
						  <div class="small-6 columns padding-lr">
						    <form:input path="inAcutedtO2Lm"/>
						  </div><label>L/min by</label>
						</div>
						<div class="large-2 columns padding-lr">
						</div>
						<div class="large-2 columns padding-lr">
						<form:checkbox path="inAcutedtOlNc" value="DT_OL_NC" label="Nasal canula"/>
						</div>
						<div class="large-2 columns padding-lr">
						<form:checkbox path="inAcutedtOlM" value="DT_OL_M" label="Mask"/>
						</div>
						<div class="large-2 columns padding-lr">
						<form:radiobutton path="inAcutedtOlMCN" value="DT_OL_M_C" label="Chronic"/>
						</div>
						<div class="large-2 columns end padding-lr">
						<form:radiobutton path="inAcutedtOlMCN" value="DT_OL_M_N" label="New"/>
						</div>
					</div>
					<div class="row">
						<div class="large-2 columns padding-lr">
						<form:checkbox path="inAcutedtNt" value="DT_OL_NC" label="Nebulizer therapy;"/>
						</div>
						<div class="large-2 columns padding-lr">
						<form:radiobutton path="inAcutedtNtCN" value="DT_OL_M_C" label="Chronic"/>
						</div>
						<div class="large-2 columns end padding-lr">
						<form:radiobutton path="inAcutedtNtCN" value="DT_OL_M_N" label="New"/>
						</div>
					</div>
					<div class="row">
						<div class="large-2 columns padding-lr">
						<form:checkbox path="inAcutedtCpap" value="DT_CPAP" label="CPAP"/>
						</div>
						<div class="large-2 columns padding-lr">
						<form:checkbox path="inAcutedtBipap" value="DT_BIPAP" label="BiPAP"/>
						</div>
						<div class="large-2 columns padding-lr">
						<form:checkbox path="inAcutedtP" value="DT_P" label="Pacemaker"/>
						</div>
						<div class="large-2 columns padding-lr">
						<form:checkbox path="inAcutedtIv" value="DT_IV" label="IV"/>
						</div>
						<div class="large-2 columns end padding-lr">
						<form:checkbox path="inAcutedtPiccl" value="DT_PICCL" label="PICC line"/>
						</div>
					</div>
					<div class="row">
						<div class="large-3 columns padding-lr">
						<form:checkbox path="inAcutedtBc" value="DT_BC" label="Bladder (Foley) Catheter"/>
						</div>
						<div class="large-2 columns padding-lr">
						<form:radiobutton path="inAcutedtBcCN" value="DT_BC_C" label="Chronic"/>
						</div>
						<div class="large-2 columns padding-lr">
						<form:radiobutton path="inAcutedtBcCN" value="DT_BC_N" label="New"/>
						</div>
						<div class="large-2 columns end padding-lr">
						<form:checkbox path="inAcutedtId" value="DT_ID" label="Internal Defibrillator"/>
						</div>
					</div>
					<div class="row">
						<div class="large-2 columns padding-lr">
						<form:checkbox path="inAcutedtEf" value="DT_EF" label="Enteral Feeding"/>
						</div>
						<div class="large-2 columns padding-lr">
						<form:checkbox path="inAcutedtTpn" value="DT_TPN" label="TPN"/>
						</div>
						<div class="large-1 columns padding-lr">
						<form:checkbox onclick="functionInAcutedtOther(this)" path="inAcutedtOther" value="DT_OTHER" label="Other"/>
						</div>
							${acuteCareModel.inAcutedtOther == 'DT_OTHER' ? "<span class='large-2 end columns padding-lr dto' style='display:block;'>":"<span class='large-2 end columns padding-lr dto' style='display:none;'>"}
						<form:input path="inAcutedtOtherTxt"/>
						</span>
					</div>
				</div>
				<div class='panel col_border'>
					<p class="lead p_heading">Isolation Precautions</p>
				<div class="row">
					<div class="large-1 columns padding-lr">
					<form:checkbox path="inAcuteipMrsa" value="IP_MRSA" label="MRSA"/>
					</div>
					<div class="large-1 columns padding-lr">
					<form:checkbox path="inAcuteipVre" value="IP_VRE" label="VRE"/>
					</div>
					<div class="large-2 columns padding-lr" style="display:inline-flex;">
					  <label>Site</label>
					 <div class="small-8 padding-lr">
					   <form:input path="inAcuteipSite"/>
				   	 </div>
					</div>
					<div class="large-2 columns padding-lr">
					<form:checkbox path="inAcuteipDfl" value="IP_DFL" label="C.difficile"/>
					</div>
					<div class="large-2 columns padding-lr">
					<form:checkbox path="inAcuteipNv" value="IP_NV" label="Norovirus"/>
					</div>
					<div class="large-3 columns padding-lr">
					<form:checkbox path="inAcuteipRvf" value="IP_RVF" label="Respiratory virus or flu"/>
					</div>
					<div class="large-2 columns end padding-lr" style="display:flex;">
					<form:checkbox onclick="functionInAcuteipO(this)" path="inAcuteipO" value="IP_O" label="Other"/>
					  	${acuteCareModel.inAcuteipO == 'IP_O'? "<span class='small-8 ipo' style='display:block;'>":"<span class='small-8 ipo' style='display:none;'>"}
						<form:input path="inAcuteipOTxt"/> 					
					  </span>
					</div>
<!-- 					<div class="large-2 columns padding-lr"> -->
<%-- 					<form:input path="inAcuteipOTxt"/> --%>
<!-- 					</div>	 -->
				</div>
				</div>
				<div class='panel col_border'>
					<div class="row">
						<label>Allergies <form:input path="inAcutealrg"/></label>
						<form:hidden path="inAcutePatinetId"/>
						<form:hidden path="inAcutePatientEpisodeId"/>
						<form:hidden path="patientStatusInAcuteCare"/>
						<form:hidden path="inAcuteSnd2HsptlFlag" value='${acuteCareModel.inAcuteSnd2HsptlFlag}'/>
						<form:hidden path="stayInAcutecareFlag" value='${acuteCareModel.stayInAcutecareFlag}'/>
						Status of(stayInAcutecareFlag) patient :${acuteCareModel.stayInAcutecareFlag}
						Status of(inAcuteSnd2HsptlFlag) patient :${acuteCareModel.inAcuteSnd2HsptlFlag}
					</div>
				</div>
				<div class='panel col_border'>
					<div class="row">
						<div class="large-6 columns">
								<label>Risk Alerts:</label>
								<div class="padding-lr">
								 <form:select onclick="functionInAcuteriskAlerts(this)" path="inAcuteriskAlerts" multiple="true" cssStyle="height: 84px !important;">
								 <form:option value="RA_AC">Anticoagulation </form:option>
								 <form:option value="RA_F">Falls</form:option>
								 <form:option value="RA_PU">Pressure ulcer(s)</form:option>
								 <form:option value="RA_A">Aspiration </form:option>
								 <form:option value="RA_SZ">Seizures</form:option>
								 <form:option value="RA_HSO">Harm to self or others</form:option>
								 <form:option value="RA_RS">Restraints</form:option>
								 <form:option value="RA_LNWB_L">Limited/non-weight bearing Left</form:option>
								 <form:option value="RA_LNWB_R">Limited/non-weight bearing Right</form:option>
								 <form:option value="RA_MAE">May attempt to exit</form:option>
								 <form:option value="RA_SPS">Swallowing precautions</form:option>
								 <form:option value="RA_NMC">Needs meds crushed</form:option>
								 <form:option value="RA_OT">Other</form:option>
								 </form:select>
						         	${acuteCareModel.inAcuteriskAlerts[0] == 'RA_OT' || acuteCareModel.inAcuteriskAlerts[1] == 'RA_OT' || acuteCareModel.inAcuteriskAlerts[2] == 'RA_OT' || acuteCareModel.inAcuteriskAlerts[3] == 'RA_OT' ||acuteCareModel.inAcuteriskAlerts[4] == 'RA_OT' ||acuteCareModel.inAcuteriskAlerts[5] == 'RA_OT' ||acuteCareModel.inAcuteriskAlerts[6] == 'RA_OT' || acuteCareModel.inAcuteriskAlerts[7] == 'RA_OT' ||acuteCareModel.inAcuteriskAlerts[8] == 'RA_OT' ||acuteCareModel.inAcuteriskAlerts[9] == 'RA_OT' ||acuteCareModel.inAcuteriskAlerts[10] == 'RA_OT' ||acuteCareModel.inAcuteriskAlerts[11] == 'RA_OT' ||acuteCareModel.inAcuteriskAlerts[12] == 'RA_OT' || acuteCareModel.inAcuteriskAlerts[13] == 'RA_OT' ? "<span class='rao' style='display:block;'>":"<span class='rao' style='display:none;'>"}
						         	<label style="margin-top:-15px; margin-bottom:18px"><i class="fi-star other small"></i>other
						         	<form:input path="inAcuteraOtTxt"/></label></span>	
						       </div>							 
						</div>
						<div class="large-6 columns">
								<label>Personal Belongings Sent with Resident:</label>
								<div class="padding-lr">
								 <form:select onclick="functionInAcutepbswResident(this)" path="inAcutepbswResident" multiple="true" cssStyle="height: 84px !important;">
								 <form:option value="PBSR_EG">Eyeglasses</form:option>
								 <form:option value="PBSR_HA">Hearing Aid</form:option>
								 <form:option value="PBSR_DA">Dental Appliance</form:option>
								 <form:option value="PBSR_J">Jewelry</form:option>
								 <form:option value="PBSR_OT">Other</form:option>
								 </form:select>
								 	${acuteCareModel.inAcutepbswResident[0] == 'PBSR_OT' || acuteCareModel.inAcutepbswResident[1] == 'PBSR_OT' || acuteCareModel.inAcutepbswResident[2] == 'PBSR_OT' || acuteCareModel.inAcutepbswResident[3] == 'PBSR_OT' || acuteCareModel.inAcutepbswResident[4] == 'PBSR_OT'? "<span class='pbsr' style='display:block;'>":"<span class='pbsr' style='display:none;'>"}
								 	  <label style="margin-top:-15px; margin-bottom:18px"><i class="fi-star other small"></i>other
								 	  <form:input path="inAcutepbsrOtTxt"/></label>
								 	</span>		
								</div>						 
						</div>
						</div>
						<div class="row">
						<div class="large-6 columns">
								<label>Nursing Home Would be able to Accept Resident Back Under the Following Conditions:</label>
								<div class="padding-lr">
								 <form:select onclick="functionInAcutenharufconditions(this)" path="inAcutenharufconditions" multiple="true" cssStyle="height: 84px !important;">
								 <form:option value="NHARUFC_ER">ER determines diagnoses, and treatment can be done in NH</form:option>
								 <form:option value="NHARUFC_VS">VS stabilized and follow up plan can be done in NH</form:option>
								 <form:option value="NHARUFC_OT">Other</form:option>
								 </form:select>
								 	${acuteCareModel.inAcutenharufconditions[0] == 'NHARUFC_OT' || acuteCareModel.inAcutenharufconditions[1] == 'NHARUFC_OT' || acuteCareModel.inAcutenharufconditions[2] == 'NHARUFC_OT' ? "<span class='nharbc' style='display:block;'>":"<span class='nharbc' style='display:none;'>"}
								 	<label style="margin-top:-15px; margin-bottom:18px"><i class="fi-star other small"></i>other
								 	<form:input path="inAcutenharufcOtTxt"/></label></span>	
								</div>							 
						</div>
						<div class="large-6 columns">
								<label>Additional Transfer Information  on a Second Page:</label>
								<label>&nbsp;&nbsp;&nbsp;&nbsp;</label>
								<div class="padding-lr">
								 <form:select path="inAcuteatisPage">
								  <form:option value="">---select---</form:option>
								 <form:option value="ATISP_I">Included</form:option>
								 <form:option value="ATISP_WSL">Will be sent later</form:option>
								 </form:select>
								</div>
						</div>
					</div>
				</div>
				</div>
				<div class="content" id='panel3'>
				<div class='panel col_border'>
					<div class="row">
						<div class="large-6 column">
								<label>Primary Goals of Care at Time of Transfer:</label>
								 <form:select onclick="functionInAcutepgctOfTransfer(this)" path="inAcutepgctOfTransfer" multiple="true" cssStyle="height: 84px !important;">
								 <form:option value="PGCTT_RMT">Rehabilitation and/or Medical Therapy with intent of returning home</form:option>
								 <form:option value="PGCTT_CLTC">Chronic long-term care</form:option>
								 <form:option value="PGCTT_P">Palliative or end-of-life care</form:option>
								 <form:option value="PGCTT_RHC">Receiving hospice care</form:option>
								 <form:option value="PGCTT_O">Other</form:option>
								 </form:select>
						       		${acuteCareModel.inAcutepgctOfTransfer[0] == 'PGCTT_O' || acuteCareModel.inAcutepgctOfTransfer[1] == 'PGCTT_O' || acuteCareModel.inAcutepgctOfTransfer[2] == 'PGCTT_O'|| acuteCareModel.inAcutepgctOfTransfer[3] == 'PGCTT_O'|| acuteCareModel.inAcutepgctOfTransfer[4] == 'PGCTT_O' ? "<span class='pgoc' style='display:block;'>":"<span class='pgoc' style='display:none;'>"}
						       		 <label style="margin-top:-15px; margin-bottom:18px"><i class="fi-star other small"></i>other
						       		 <form:input path="inAcutepgcttOtTxt"/></label>
						       		</span>						 
						</div>
					</div>
				</div>
				<div class='panel col_border'>
					<div class="row">
						<div class="large-12 columns padding-lr">
							<label>Treatments and Frequency (include special treatments such as dialysis, chemotherapy, transfusions, radiation, TPN)</label>
						   <form:input path="inAcutetreatMentsNdFr"/>
						</div>
					</div>
				</div>
				<div class='panel col_border'>
				<p class="lead p_heading">Diet</p>
					<div class="row">
						<div class="large-3 columns">
							<label>Needs assistance with feeding?</label><form:radiobutton value="D_NAF_NO" path="inAcutednaf" label="No"/>
							<form:radiobutton value="D_NAF_YES" path="inAcutednaf" label="Yes"/>
						</div>
						<div class="large-2 columns">
							<label>Trouble swallowing?</label><form:radiobutton value="D_TS_NO" path="inAcutedts" label="No"/>
							<form:radiobutton value="D_TS_YES" path="inAcutedts" label="Yes"/>
						</div>
						<div class="large-6 columns">
							<label>Special consistency (thickened liquids, crush meds, et.,)?</label>
							<div class="small-12 padding-lr" style="display:flex;">							
						    	<form:radiobutton onclick="functionInAcutedsc1(this)" value="D_SC_NO" path="inAcutedsc" label="No"/>
							    <form:radiobutton onclick="functionInAcutedsc2(this)" value="D_SC_YES" path="inAcutedsc" label="Yes"/>
							    	${acuteCareModel.inAcutedsc == 'D_SC_YES'?"<span class='dsc small-12' style='display:block;'>":"<span class='dsc small-12' style='display:none;'>"}
							    	<form:input path="inAcutedscTxt"/></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="large-12 columns">
							<label>Enteral tube feeding?</label>
							<div class="large-2 columns">
							 <form:radiobutton onclick="functionInAcutedetf1(this)" value="D_ETF_NO" path="inAcutedetf" label="No"/>
							 <form:radiobutton onclick="functionInAcutedetf2(this)" value="D_ETF_YES" path="inAcutedetf" label="Yes"/>
						    </div>
						    <div class="large-4 columns end">
						     	${acuteCareModel.inAcutedetf == 'D_ETF_YES'?"<span class='etffr' style='display:none;'>":"<span class='etffr' style='display:none;'>"}
						        <label> (formula/rate):<form:input path="inAcutedetfFrTxt"/></label></span>	
						     </div>						
						</div>
					</div>
				</div>
				<div class='panel col_border'>
				<p class="lead p_heading">Skin/Wound Care</p>
					<div class="row">
						<div class="large-12 columns padding-lr">
						<label>Pressure Ulcers (stage, location,  appearance, treatments)<form:input path="inAcuteswCarePU"/></label>
						</div>
					</div>
				</div>
				<div class='panel col_border'>
				<p class="lead p_heading">Immunizations</p>
					<div class="row">
						<div class="large-3 columns">
						<div class="padding-lr">
						<label>Influenza<form:input path="inAcuteiinfluenza"/></label>
						</div>
						</div>
						<div class="large-3 columns">
						<div class="padding-lr">
						<label>Date<form:input onchange="return validateDate(this)" path="inAcuteiinfDate" cssClass="datepicker"/></label>
						</div>
						</div>
						<div class="large-3 columns">
						<div class="padding-lr">
						<label>Pneumococcal<form:input path="inAcuteipneumococcal"/></label>
						</div>
						</div>
						<div class="large-3 columns">
						<div class="padding-lr">
						<label>Date<form:input onchange="return validateDate(this)"  path="inAcuteipnDate" cssClass="datepicker"/></label>
						</div>
						</div>
					</div>
				</div>
				<div class='panel col_border'>
				<p class="lead p_heading">Physical Rehabilitation Therapy</p>
					<div class="row">
						<div class="large-6 columns">
							<label>Resident is receiving therapy with goal of returning home?</label><form:radiobutton value="PRH_RRTRH_NO" path="inAcuteprhRrtrh" label="No"/>
							<form:radiobutton value="PRH_RRTRH_YES" path="inAcuteprhRrtrh" label="Yes"/>
						</div>
						<div class="large-6 columns">
							<label>Physical Therapy:</label>
							<div class="small-12" style="display:flex;">
							<div class="small-4">
							    <form:radiobutton onclick="functionInAcuteprhPTherapy1(this)" value="PRH_PT_NO" path="inAcuteprhPTherapy" label="No"/>
							    <form:radiobutton onclick="functionInAcuteprhPTherapy2(this)" value="PRH_PT_YES" path="inAcuteprhPTherapy" label="Yes"/>														 	
							</div>
								${acuteCareModel.inAcuteprhPTherapy == 'PRH_PT_YES' ?"<span class='prtp' style='display:block;'>":"<span class='prtp' style='display:none;'>"}
							 <div class="small-12" style="display:inline-flex;">
							  <div class="small-5">
							   <label>Interventions</label>
							  </div>
							  <div  class="small-5">
							    <form:input path="inAcuteprhPtIt"/>
							  </div>	 
							 </div>
							</span> 
						   </div>
						</div>
						<div class="large-6 columns">
							<label>Occupational Therapy:</label>
							<div class="small-12" style="display:flex;">
							<div class="small-4">
							    <form:radiobutton onclick="functionInAcuteprhOt1(this)" value="PRH_OT_NO" path="inAcuteprhOt" label="No"/>
							    <form:radiobutton onclick="functionInAcuteprhOt2(this)" value="PRH_OT_YES" path="inAcuteprhOt" label="Yes"/>														 	
							</div>
								${acuteCareModel.inAcuteprhOt == 'PRH_OT_YES' ?"<span class='prto end' style='display:block;'>":"<span class='prto end' style='display:none;'>"}
							<div class="small-12" style="display:flex;">
							 <div class="small-5">
							  <label>Interventions</label>
							 </div>
							 <div  class="small-5">
							   <form:input path="inAcuteprhOtIt"/>
							 </div>	 
							</div>
							</span>
							</div>
						</div>
						<div class="large-6 columns">
							<label>Speech Therapy:</label>
							<div class="small-12" style="display:flex;">
							<div class="small-4">
							    <form:radiobutton onclick="functionInAcuteprhSt1(this)" value="PRH_ST_NO" path="inAcuteprhSt" label="No"/>
							    <form:radiobutton onclick="functionInAcuteprhSt2(this)" value="PRH_ST_YES" path="inAcuteprhSt" label="Yes"/>														 	
							</div>
								${acuteCareModel.inAcuteprhSt == 'PRH_ST_YES' ?"<span class='prts end' style='display:block;'>":"<span class='prts' style='display:none;'>"}
							<div class="small-12" style="display:flex;">
							 <div class="small-5">
							  <label>Interventions</label>
							 </div>
							 <div  class="small-5">
							   <form:input path="inAcuteprhStIt"/>
							 </div>	 
							</div></span></div>
						</div>
					</div>
				</div>
				<div class='panel col_border'>
				 <p class="lead p_heading">ADLs  Mark   I = Independent   D = Dependent   A = Needs Assistance</p>
					<div class="row">
						<div class="large-2 columns">
						<div class="padding-lr">
						<label>Bathing <form:input path="inAcuteadlsB"/></label>
						</div>
						</div>
						<div class="large-2 columns padding-lr">
						<div class="padding-lr">
						<label>Dressing <form:input path="inAcuteadlsD"/></label>
						</div>
						</div>
						<div class="large-2 columns padding-lr">
						<div class="padding-lr">
						<label>Transfers <form:input path="inAcuteadlsT"/></label>
						</div>
						</div>
						<div class="large-2 columns padding-lr">
						<div class="padding-lr">
						<label>Toileting <form:input path="inAcuteadlsTl"/></label>
						</div>
						</div>
						<div class="large-2 columns end padding-lr">
						<div class="padding-lr">
						<label>Eating  <form:input path="inAcuteadlsE"/></label>
						</div>
						</div>
					</div>
					<div class="row">
					 <label>&nbsp;&nbsp;&nbsp;&nbsp;</label>	
					</div>
					<div class="row">
					 <div class="large-12 columns padding-lr">
						<div class="large-6 columns">
						<div class="padding-lr">
						 <form:checkbox onclick="functionInAcuteadlsCai(this)" path="inAcuteadlsCai" value="ADLS_CAI" label="Can ambulate independently"/>
						 	${acuteCareModel.inAcuteadlsCai == 'ADLS_CAI'?"<span class='large-4 adlcai  padding-lr' style='display:block;'>":"<span class='large-4 adlcai  padding-lr' style='display:none;'>"}
						 	<form:input path="inAcuteadlsCaiTxt"/></span>
						</div>
						</div>
						<div class="large-6 columns">
						<div class="padding-lr">
						 <form:checkbox onclick="functionInAcuteadlsAd(this)" path="inAcuteadlsAd" value="ADLS_AD" label="Assistive device (if applicable)"/>
						 	${acuteCareModel.inAcuteadlsAd == 'ADLS_AD' ?"<span class='large-4 adlad padding-lr' style='display:block;'>":"<span class='large-4 adlad padding-lr' style='display:none;'>"}
						 	<form:input path="inAcuteadlsAdTxt"/></span>
						</div>
						</div>
						</div>
					</div>
					<div class="row">
						<div class="large-12 columns padding-lr">
						 <div class="large-6 columns ">
						 <div class="padding-lr">
						  <form:checkbox onclick="functionInAcuteadlsHaa(this)" path="inAcuteadlsHaa" value="ADLS_HAA" label="Needs human assistance to ambulate"/>
						  	${acuteCareModel.inAcuteadlsHaa == 'ADLS_HAA' ?"<span class='large-4 adlnha padding-lr' style='display:block;'> ":"<span class='large-4 adlnha padding-lr' style='display:none;'> "}
						  	<form:input path="inAcuteadlsHaaTxt"/></span>
						</div>
						</div>
						</div>
					</div>
				</div>
				<div class='panel col_border'>
					<div class="row">
						<div class="large-5 column padding-lr">
								<label>Impairments - General:</label>
								 <div class="padding-lr">
								 <form:select onclick="functionInAcuteigeneral(this)" path="inAcuteigeneral" multiple="true" cssStyle="height: 84px !important;">
								 <form:option value="IG_C">Cognitive</form:option>
								 <form:option value="IG_S">Speech</form:option>
								 <form:option value="IG_H">Hearing</form:option>
								 <form:option value="IG_V">Vision</form:option>
								 <form:option value="IG_ST">Sensation</form:option>
								 <form:option value="IG_OT">Other</form:option>
								 </form:select>
								 	${acuteCareModel.inAcuteigeneral[0] == 'IG_OT' || acuteCareModel.inAcuteigeneral[1] == 'IG_OT' || acuteCareModel.inAcuteigeneral[2] == 'IG_OT' || acuteCareModel.inAcuteigeneral[3] == 'IG_OT' || acuteCareModel.inAcuteigeneral[4] == 'IG_OT' || acuteCareModel.inAcuteigeneral[5] == 'IG_OT'?"<span class='igo' style='display:block;'>":"<span class='igo' style='display:none;'>"}
								 	 <label style="margin-top:-15px; margin-bottom:18px"><i class="fi-star other small"></i>other
								 	 <form:input path="inAcuteigOtTxt"/></label>
								 	</span>
						    </div>
						</div>
						<div class="large-5 column padding-lr">
								<label>Impairments - General:</label>
								<div class="padding-lr">
								 <form:select onclick="functionInAcuteimusculoskeletal(this)" path="inAcuteimusculoskeletal" multiple="true" cssStyle="height: 84px !important;">
								 <form:option value="IM_AT">Amputation</form:option>
								 <form:option value="IM_PL">Paralysis</form:option>
								 <form:option value="IM_CTS">Contractures</form:option>
								 <form:option value="IM_OT">Other</form:option>
								 </form:select>
								 	${acuteCareModel.inAcuteimusculoskeletal[0] == 'IM_OT' || acuteCareModel.inAcuteimusculoskeletal[1] == 'IM_OT' || acuteCareModel.inAcuteimusculoskeletal[2] == 'IM_OT' || acuteCareModel.inAcuteimusculoskeletal[3] == 'IM_OT' ?"<span class='imgo'  style='display:block;'>":"<span class='imgo'  style='display:none;'>"}
								 	 <label style="margin-top:-15px; margin-bottom:18px"><i class="fi-star other small"></i>other
								 	 <form:input path="inAcuteimOtTxt"/></label>
								 	</span>
						    </div>
						</div>
						<div class="large-2 column padding-lr">
							<label>Continence:</label>
							<div class="padding-lr">
							<form:radiobutton path="inAcutectBlOrBr" value="CT_BL" label="Bowel"/>
							<form:radiobutton path="inAcutectBlOrBr" value="CT_BR" label="Bladder"/>
							</div>
						</div>
						<div class="large-2 column end padding-lr">
						<div class="padding-lr">
							<label>Date of last BM <form:input onchange="return validateDate(this)"  path="inAcutectDlbm" cssClass="datepicker"/></label>
						</div>
						</div>
					</div>
				</div>
				</div></div>
			</div>
			<br>
			<input id="acuteCareFormButtonSave" class="button small right" type="submit" value="Save">
			<c:choose>
			<c:when  test='${ sessionScope.role== "LPN"|| sessionScope.role== "RN"}'>
		    <div class="content">
			   <div class="large-10 columns padding-lr">
							${acuteCareModel.inAcuteSnd2HsptlFlag ? "<div class='large-3 columns padding-lr' style='display:flex;'>":"<div class='large-3 columns padding-lr' style='display:none;'>"}
							<input type="button" class="button small right" id="backInFacility" value="Back In Facility"  onclick="backInFacilityConfirm(${acuteCareModel.inAcuteSnd2HsptlFlag})" />
							</div>
							${acuteCareModel.inAcuteSnd2HsptlFlag ?"<div class='large-3 columns padding-lr end' style='display:none;'>":"<div class='large-3 columns padding-lr end' style='display:flex;'>"}
							<input type="button" class="button small right" id="transferedToHospital" value="Transfered To Hospital" onclick="transferedToHospitalconfirm()"/>
							</div>
							${acuteCareModel.inAcuteSnd2HsptlFlag ? "<div class='large-3 columns padding-lr end' style='display:flex;'>":"<div class='large-3 columns padding-lr end' style='display:none;'>"}
							<input type="button" class="button small right" id="closePatientEpisode" value="Stayed in Hospital" onclick="movedFromFacfirm()"/>
							</div>
			  </div>	</div>	
			</c:when>
			</c:choose>
			</form:form>
</div>
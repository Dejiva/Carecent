<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<div class="NewPatientModalDiv" style="max-height: 430px; overflow-y: auto;">
		<form:form method="post" cssClass="admissionModel_class" name="admissionForm" commandName="admissionForm" action="${pageContext.request.contextPath}/admission">
			<div id="NewPatientModalMessage" style="color: green; font-size: large;text-align: center;"></div>
			<div id="admissionFormErrors">
				<form:errors path="*" cssClass="error"/>
			</div>
			
<!--                       _________TABS-CONTENT OPEN__________                              -->

			<div class="tabs-content">
				<div class="content active" id='panel1'>
					<div class='panel col_border'>
							<p class="lead p_heading"><i class="fi-star small"></i> A. Patient Information</p>
							<div class="row">
							<div class="large-12 columns padding-lr">
							<div class="large-2 column padding-lr">
								<label>SSN Number:<span id="SSNNumberStatus" style="color: red;"></span>  <form:input path="sSNumber" onchange="functionSSNumber(this)" maxlength="30"/></label>
							</div>
							<div class="large-2 column padding-lr">
								<label>Patient First Name:  <form:input path="patientFirstName" maxlength="30"/></label>
							</div>
							<div class="large-2 column padding-lr">
								<label>Patient Last Name:  <form:input path="patientLastName" maxlength="30"/></label>
							</div>
							
							<div class="large-2 column padding-lr">
								<label>DOB: <form:input  path="dob" onchange="return validateDate(this)"/></label>
							</div>
							<div class="large-2 column padding-lr">
								<label>Room No:  <form:input path="roomNumber" maxlength="30"/></label></label>
							</div>
                            <div class="large-2 column padding-lr end">
								<label>Date of Admission:  <form:input path="doj"  onchange="return validateDate(this)" /></label>
							</div>
							</div>
							</div>
							<div class="row">
							<div class="large-12 columns padding-lr">
							<div class="large-2 column padding-lr">
								<label>Gender:</label>
								<form:radiobutton id="patientGender1" path="patientGender" value="M" label="M"/>
								<form:radiobutton id="patientGender2" path="patientGender" value="F" label="F"/>
							</div>
							<div class="large-4 column padding-lr">
								<label>Language:</label>
								<form:radiobutton onclick="otherLanguageShowHide()"  id="canSpeakEnglish1"  path="canSpeakEnglish" value="English" label="English"/>
								<form:radiobutton onclick="otherLanguageShowHide()"  id="canSpeakEnglish2"  path="canSpeakEnglish" value="Other" label="Other"/>
								${admissionForm.canSpeakEnglish == 'Other'   ? "<span id='otherLanguageShowHide'>":"<span id='otherLanguageShowHide' style='display: none;'>"}
									<div class="small-6 columns inline right padding-lr"><form:input path='otherLanguage' maxlength='15' cssClass='right inline'/></div>
								</span>
							</div>
							<div class="large-6 column padding-lr end">
								<label>Race/Ethnicity:</label>
								<form:radiobutton onclick="otherEthnicityTypeShowHide()" id="ethnicityType1" path="ethnicityType" value="White" label="White"/>
								<form:radiobutton onclick="otherEthnicityTypeShowHide()" id="ethnicityType2" path="ethnicityType" value="Black" label="Black"/>
								<form:radiobutton onclick="otherEthnicityTypeShowHide()" id="ethnicityType3" path="ethnicityType" value="Hispanic" label="Hispanic"/>
								<form:radiobutton onclick="otherEthnicityTypeShowHide()" id="ethnicityType4" path="ethnicityType" value="Other" label="Other"/>
								${admissionForm.ethnicityType == 'Other'  ? "<span id='otherEthnicityTypeShowHide'>":"<span id='otherEthnicityTypeShowHide' style='display: none;'>"}
									<div class="small-5 columns inline right padding-lr"><form:input path="otherEthnicityType" maxlength="15" cssClass="inline right"/></div>
								</span>
							</div>
							</div>
							</div>
						  <div class="row">
							<div class="large-12 columns padding-lr">
							<div class="large-2 column padding-lr">
							<label>PCP at site (MD)</label> 
									<form:select onchange="populatepcpAtSiteWorkNumber(this)" path="pcpAtSite">
									<form:option  value="" label="-- Select PCP --"/>
									<c:forEach items="${sessionScope.mdInFacilityMap}" var="item">
									<form:option work-Num="${item.value.workNumber}"  value="${item.key}">${item.value.name}</form:option>
									</c:forEach>
									</form:select>
							</div>
							<div class="large-2 column padding-lr">
								<label>Work Number:  <form:input path="pcpWorkNumber" maxlength="30" readonly="true"/></label>
							</div>
<%-- 							<div class="large-2 column padding-lr">
                            <label>Patient  Care Types</label>
                            <form:select path="patientCareTypes">
                            <form:option value="">-- select --</form:option>
                            <form:option value="PAC">Post-Acute Care</form:option>
                            <form:option value="CLTC">Chronic Long Term Care</form:option>
                            </form:select>
                            </div>
                            <div class="large-3 column padding-lr end">
                            <label>Medicare Insurance Plan</label>
                            <form:select path="patientInsurencePlan">
                            <form:option value="">-- select --</form:option>
                            <form:option value="RMFFS">Regular Medicare Fee-for-Service</form:option>
                            <form:option value="BCBSTX">BCBSTX</form:option>
                            <form:option value="AUHG">Aetna UHG</form:option>
                            <form:option value="CIGNA">Cigna</form:option>
                            </form:select>
                            </div> --%>
                            </div>
						</div>
					</div>
					<div class='panel col_border'>
						<div class="row">
							<p class="lead p_heading"><i class="fi-star small"></i> B. Family/Caregiver/Proxy Contact</p>
							<div class="large-4 column padding-lr">
								<label>Family/Caregiver Name: <form:input path="caregiverName" maxlength="30"/>
								<form:hidden path="gCareGiverId"/>
								</label>
							</div>
							<div class="large-2 column padding-lr end">
								<label>Tel: <form:input path="careGiverTelephoneNumber"/></label>
							</div>
							<div class="large-4 column padding-lr">
								<label>Healthcare Proxy/Guardian Name (if different): 
								<form:input path="guardianName"  maxlength="30"/>
								<form:hidden path="gProxyId"/>
								</label>
							</div>
							<div class="large-2 column padding-lr end">
								<label>Tel: 
								<form:input path="guardianTelephoneNumber"/>
								</label>
							</div>
						</div>
					</div>
					<div class='panel col_border col_border_blue'>
						<div class="row">
							<div class="large-12 columns padding-lr">
								<p class="lead p_heading"><i class="fi-star small greenStar"></i> C. Advance Directives/Goals of Care</p>
								<div class="large-2 columns padding-lr"><form:checkbox id="fullCode" path="fullCode" label="Full Code"/></div>
								<div class="large-1 columns padding-lr"><form:checkbox id="dnr" path="dnr" label="DNR"/></div>
								<div class="large-1 columns padding-lr"><form:checkbox id="dni" path="dni" label="DNI"/></div>
								<div class="large-1 columns padding-lr"><form:checkbox id="dnh" path="dnh" label="DNH"/></div>
								<div class="large-2 columns padding-lr"><form:checkbox id="noArtificialFeeding" path="noArtificialFeeding" label="No Artificial Feeding"/></div>
								<div class="large-2 columns padding-lr"><form:checkbox id="comfortCare" path="comfortCare" label="Comfort Care"/></div>
								<div class="large-2 columns padding-lr"><form:checkbox id="hospiceCare" path="hospiceCare" label="Hospice Care"/></div>
								<div class="large-3 columns padding-lr end"><form:checkbox id="otherDirectiveCheckBox" onclick="otherAdvanceDirectivesShowHide(this)" path="otherDirectiveCheckBox" label="Other"/>
								${admissionForm.otherDirectiveCheckBox ? "<span id='otherAdvanceDirectivesShowHide'>":"<span  id='otherAdvanceDirectivesShowHide' style='display: none;'>"}
									<div class="small-8 columns inline right"><form:input path="otherAdvanceDirectives" cssClass="other-input"/></div>
								</span></div>
							</div>
						</div>
						<div class="row">
							<div class="large-5 column padding-lr">
								<label><b>Were goals of care discussed during this hospitalization?:</b></label>
								<form:radiobutton id="goalsOfCareDiscussed1" onclick="specifyGoalsOfCareDiscussedShowHide()" path="goalsOfCareDiscussed" value="No" label="No"/>
								<form:radiobutton id="goalsOfCareDiscussed2" onclick="specifyGoalsOfCareDiscussedShowHide()" path="goalsOfCareDiscussed" value="Yes" label="Yes (specify)"/>
								${admissionForm.goalsOfCareDiscussed == 'Yes'   ? "<span id='specifyGoalsOfCareDiscussedShowHide'>":"<span id='specifyGoalsOfCareDiscussedShowHide' style='display: none;'>"}
									<div class="small-5 columns inline right"><form:input path="specifyGoalsOfCareDiscussed" cssClass="other-input" cssStyle="top: 0 !important;"/></div>
								</span>
							</div>
							<div class="large-6 column padding-lr end">
								<label><b>Patient decision making capacity?:</b></label>
								<form:radiobutton data-radio="patientDecision" id="canTakeDecision" value="Yes" path="canTakeDecision" label="Capable of making decisions"/>
								<form:radiobutton data-radio="patientDecision" id="reqProxy" value="Yes" path="reqProxy" label="Requires proxy"/>
							</div>
						</div>
					</div>
					<div class='panel col_border col_border_blue'>
						<p class="lead p_heading"><i class="fi-star small greenStar"></i> D. Transferring Hospital Information</p>
						<div class="row">
							<div class="large-2 column padding-lr">
								<label>Hospital: <form:input path="hospitalName"/><form:hidden path="hospitalId"/></label>
							</div>
							<div class="large-2 column padding-lr">
								<label>Unit: <form:input path="hospitalUnit"/></label>
							</div>
							<div class="large-2 column padding-lr">
								<label>Discharging RN: <form:input path="dischargingRN"/><form:hidden path="dischargingRNId"/></label>
							</div>
							<div class="large-2 column padding-lr end">
								<label>Tel: <form:input path="dishcargingRNTelephoneNumber"/></label>
							</div>
						</div>
						<div class="row">
							<div class="large-2 column padding-lr">
								<label>Discharging MD: <form:input path="dischargingMD"/><form:hidden path="dischargingMDId"/></label>
							</div>
							<div class="large-2 column padding-lr">
								<label>Tel /Page: <form:input path="dishcargingMDTelephoneNumber"/></label>
							</div>
							<div class="large-2 column padding-lr">
								<label>Hospital Admission Date: <form:input path="hospitalAdmissionDate"   onchange="return validateDate(this)"/></label>
							</div>
							<div class="large-2 column padding-lr end">
								<label>Hospital Discharge Date: <form:input path="fromHospitalDischargeDate"  onchange="return validateDate(this)"/></label>
							</div>
						</div>
					</div>
					<div class='panel col_border col_border_blue'>
						<p class="lead p_heading"><i class="fi-star small greenStar"></i> E. Post-Acute Care Information</p>
						<div class="row">
							<div class="large-3 column padding-lr">
								<label>Transferred to: 
								<form:input path="transferredToPostAcuteCare" readonly="true"/>
								<form:hidden path="facilityId" value='${sessionScope.facilityId}'/>
								<form:hidden path="patientId"/>
								<form:hidden path="patientEpisodeId"/>
								<form:hidden path="admittedByStaffId" value='${sessionScope.staffId }'/>
								<form:hidden path="admittedByStaffName" value='${pageContext.request.userPrincipal.name}'/>
								<form:hidden path="admittedByStaffType" value='${sessionScope.user.authorities}'/>
								<form:hidden path="modeOpen" id="modeOpen"/>
								</label>
							</div>
							<div class="large-2 column padding-lr">
								<label>Tel: <form:input readonly="true" path="postAcuteCareTelephoneNumber"/></label>
							</div>
							<div class="large-7 columns padding-lr">
								<label><b>Nurse to Nurse verbal report?:</b></label>
								<div class="large-7 column">
								<form:radiobutton  id="nurseToNurseVerbalReport1" onclick="nurseToNurseVerbalReportTextShowHide()" path="nurseToNurseVerbalReport" value="No" label="No"/>
								<form:radiobutton  id="nurseToNurseVerbalReport2" onclick="nurseToNurseVerbalReportTextShowHide()" path="nurseToNurseVerbalReport" value="Yes" label="Yes(specify to whom)"/>
								</div>
								${admissionForm.nurseToNurseVerbalReport == 'Yes'  ? "<span id='nurseToNurseVerbalReportTextShowHide'  class='large-5  columns inline right'>":"<span id='nurseToNurseVerbalReportTextShowHide'  class='large-5  columns inline right' style='display: none;'>"}
									<form:input path="nurseToNurseVerbalReportText"/>
								</span>
							</div>
						</div>
					</div> 
					<div class='panel col_border col_border_blue'>
						<p class="lead p_heading"><i class="fi-star small greenStar"></i> F. Hospital Physician Care Team Information</p>
						<div class="row">
							<div class="large-9 column padding-lr">
								<label>Primary Care Physician (or Hospitalist): <form:input path="primaryCarePhysician"/>
								<form:hidden path="primaryCarePhysicianId"/>
								</label>
							</div>
							<div class="large-3 column padding-lr end">
								<label>Tel: <form:input path="primaryCarePhysicianTelephoneNumber"/></label>
							</div>
						</div>
						<div class="row">
							<div class="large-6 column padding-lr">
								<label>Specialist: <form:input path="specialist1"/><form:hidden path="specialist1Id"/></label>
							</div>
							<div class="large-3 column padding-lr">
								<label>Specialty: <form:input path="specialist1Speciality"/>
								<form:hidden path="specialist1SpecialityId"/>
								</label>
							</div>
							<div class="large-3 column padding-lr end">
								<label>Tel: <form:input path="specialist1TelephoneNumber"/></label>
							</div>
						</div>
						<div class="row">
							<div class="large-6 column padding-lr">
								<label>Specialist: <form:input path="specialist2"/>
								<form:hidden path="specialist2Id"/>
								</label>
							</div>
							<div class="large-3 column padding-lr">
								<label>Specialty: <form:input path="specialist2Speciality"/>
								<form:hidden path="specialist2SpecialityId"/>
								</label>
							</div>
							<div class="large-3 column padding-lr end">
								<label>Tel: <form:input path="specialist2TelephoneNumber"/>
								<form:hidden path="hospitalCareTeamId"/>
								</label>
							</div>
						</div>
					</div>
			</div>
			
<!--                       _________PANEL-2 OPEN__________                              -->
			
			<div class="content" id='panel2'>
				
<!-- 				    panel col_border col_border_blue   "OPEN"                      	 -->
					<div class='panel col_border col_border_blue'>
						<p class="lead p_heading">G. Key Clinical Information</p>
						<div class="row">
						 <div class="large-12 columns padding-lr">
							<label class='left'><b>Vital Signs: </b></label>
						 </div>
						 <div class="large-12 columns padding-lr">
							<div class="large-2 column padding-lr">
								<label class="padding-lr">Time Taken: <form:input path="inAdmissionKcivsTimeTaken"/></label>
							</div>
							<div class="large-10 columns padding-lr">
<!-- 								<label>&nbsp;</label> -->
								<div class="large-1 column padding-lr">
								<form:checkbox  onclick="inAdmissionKcivsPainRatingShowHide(this)" value="KCIVS_NOT_APPLICABLE" path="inAdmissionKcivsNotApplicable" label="N/A"/>
							    </div>
							${admissionForm.inAdmissionKcivsNotApplicable == 'KCIVS_NOT_APPLICABLE'   ? "<span id='inAdmissionKcivsPainRatingShowHide' class='large-2 column padding-lr'>":"<span id='inAdmissionKcivsPainRatingShowHide' class='large-2 column padding-lr' style='display: none;'>"}
								<label>Pain Rating: <form:input path="inAdmissionKcivsPainRating" maxlength="6"/></label>
							</span>
							<div class="large-1 column padding-lr">
								<label>Pain Site: <form:input path="inAdmissionKcivsPainSite"/></label>
							</div>
							<div class="large-1 column padding-lr">
								<label>Temp:(°F) <form:input path="inAdmissionKcivsTemp" maxlength="6"/></label>
							</div>
							<div class="large-1 column padding-lr">
								<label>BP: <form:input path="inAdmissionKcivsBp" maxlength="6"/></label>
							</div>
							<div class="large-1 column padding-lr">
								<label>HR:   <form:input path="inAdmissionKcivsHr" maxlength="6"/></label>
							</div>
							<div class="large-1 column padding-lr">
								<label>RR: <form:input path="inAdmissionKcivsRr" maxlength="6"/></label>
							</div>
							<div class="large-1 column padding-lr">
								<label><i class="fi-star small greenStar"></i> 02 Sat: <form:input path="inAdmissionKcivs02sat" maxlength="6"/></label>
							</div>
							<div class="large-2 column padding-lr end">
								<label>Weight:(Ibs) <form:input path="inAdmissionKcivsWght" maxlength="6"/></label>
							</div>
							</div>
							</div>
						</div>
						<div class='row'> 
					    	<div class="large-12 columns padding-lr">
							  <label class='left'><b>Mental Status:</b></label>
							</div>
							<div class="large-3 column padding-lr end">
							 <form:select path="inAdmissionMentalStatus">
							  <form:option value="">-- select --</form:option>
							  <form:option value="KCIMS_ALRT">Alert</form:option>
							  <form:option value="KCIMS_N_ALRT">Not Alert</form:option>
							  <form:option value="KCIMS_DFC">Disoriented, follows commands</form:option>
							  <form:option value="KCIMS_DCFC">Disoriented, cannot follow commands</form:option>
							 </form:select>
							<form:hidden path="specialist2Id"/>
							</div>
						</div>
						<div class='row'>
						  <div class="large-12 columns padding-lr">
							<label class='left'><b>Diagnoses:</b></label>
						  </div>
							<div class="large-3 column padding-lr">
								<label class="padding-lr"><i class="fi-star small greenStar"></i>Primary Discharge Diagnosis: <form:input path="inAdmissionKcidPdd"/></label>
							</div>
							<div class="large-4 column padding-lr">
								<label class="padding-lr">Other Medical Diagnoses: <form:input path="inAdmissionKcidOmd"/></label>
							</div>
							<div class="large-4 column padding-lr end">
								<label class="padding-lr">Mental Health Diagnoses: <form:input path="inAdmissionKcidMhd"/></label>
							</div>
						</div>
					</div>					
<!-- 				    panel col_border col_border_blue   "CLOSE"                        -->

<!-- 				    panel col_border col_border_blue   "OPEN"                         -->
					<div class='panel col_border col_border_blue'>
						<p class="lead p_heading">H. High Risk Conditions/Treatment Information (check all that apply)</p>
						
						<div class="row">
							<div class="large-2 columns padding-lr">
								<label><form:checkbox value="HRC_OR_TI_FALRSK" onclick="inAdmissionHrcOrTiPrecShowHide(this)" path="inAdmissionhrcOrTiFalrsk"/><b>&nbsp;&nbsp;Fall Risk:</b></label>
							</div>
							${admissionForm.inAdmissionhrcOrTiFalrsk == 'HRC_OR_TI_FALRSK'  ? "<span id='inAdmissionHrcOrTiPrecShowHide' class='large-12 columns padding-lr' style='display: block;'>":"<span id='inAdmissionHrcOrTiPrecShowHide' class='large-12 columns padding-lr' style='display: none;'>"}
								<label style="padding-bottom:10px">Precautions:<form:input path="inAdmissionHrcOrTiPrec"/></label>
							</span>
						</div>
						
						<div class="row">
							<div class="large-2 columns padding-lr">
								<label><form:checkbox value="HRC_OR_TI_HF" onclick="HeartFailureShowHide(this)" path="inAdmissionHrcOrTiHf"/><b>&nbsp;&nbsp;Heart Failure:</b></label>
							</div>
							${admissionForm.inAdmissionHrcOrTiHf == 'HRC_OR_TI_HF' ? "<span id='HeartFailureShowHide'  class='padding-lr' style='display: block;'>":"<span id='HeartFailureShowHide'  class='padding-lr' style='display: none;'>"}
							<div class='large-12 columns padding-lr'>
							 <div class="large-2 columns padding-lr">
								<form:checkbox value="HRC_OR_TI_HF_NEWDIAG" id="inAdmissionHrcOrTiHfNewdiag" path="inAdmissionHrcOrTiHfNewdiag" label="New diagonsis?:"/>
							 </div>
							 <div class="large-3 columns padding-lr">
								<form:checkbox value="HRC_OR_TI_HF_EXTHADMSSION" id="inAdmissionHrcOrTiHfExthadmssion" path="inAdmissionHrcOrTiHfExthadmssion" label="Exacerbation this admission?"/>
							 </div>
							 <div class="large-2 columns padding-lr">
								<label>Date of last echo: <form:input onchange="return validateDate(this)" id="inAdmissionHrcOrTiHfExthadmssionLstdte"  path="inAdmissionHrcOrTiHfExthadmssionLstdte" cssClass="datepicker"/></label>
							 </div>
							 <div class="large-1 columns padding-lr">
								<form:checkbox value="HRC_OR_TI_HF_EF" id="inAdmissionHrcOrTiHfEf" onclick="inAdmissionHrcOrTiHfEfpShowHide(this)" path="inAdmissionHrcOrTiHfEf" label="EF(%)" style="margin:0px"/>							
								${admissionForm.inAdmissionHrcOrTiHfEf == 'HRC_OR_TI_HF_EF'  ? "<span id='inAdmissionHrcOrTiHfEfpShowHide'>":"<span  id='inAdmissionHrcOrTiHfEfpShowHide' style='display: none !important;'>"}
									<form:input path="inAdmissionHrcOrTiHfEfp" id="inAdmissionHrcOrTiHfEfp" maxlength="6"/></span>
							 </div>
							 <div class="large-2 columns padding-lr end">
								<label>Dry Weight (if known): <form:input id="inAdmissionHrcOrTiHfEfDw" path="inAdmissionHrcOrTiHfEfDw" maxlength="6"/></label>
							 </div>
							</div>
						 </span>
						</div>
						
						<div class="row">
							<div class="large-12 columns padding-lr">
							   <div class="large-12 columns">
							    <span style=" font-size: 13px; !important">
								<form:checkbox value="HRC_OR_TI_AC" onclick="AnticoagulatedShowHide(this)" path="inAdmissionhrcOrTiAc"/>&nbsp;&nbsp;<i class="fi-star small greenStar"></i><b>Anticoagulated:&nbsp;&nbsp;&nbsp;&nbsp;</b></span>
							   </div>
								${admissionForm.inAdmissionhrcOrTiAc == 'HRC_OR_TI_AC'   ? "<span id='AnticoagulatedShowHide'>":"<span id='AnticoagulatedShowHide' style='display: none;'>"}
								<div class='large-4 column padding-lr'>
								<label>Reason:&nbsp;&nbsp;</label>
								<form:select path="inAdmissionhrcOrTiAcReasons" multiple="true" cssStyle=" height: 100px !important;">
								<form:option value="">-- select --</form:option> 
								<form:option value="HRC_OR_TI_AC_R_AFIB" onclick="inAdmissionHrcOrTicROthrTextShowHide()">Afib</form:option>
								<form:option value="HRC_OR_TI_AC_R_DVT_OR_PE" onclick="inAdmissionHrcOrTicROthrTextShowHide()">DVT/PE</form:option>
								<form:option value="HRC_OR_TI_AC_R_MECVAL" onclick="inAdmissionHrcOrTicROthrTextShowHide()">Mech.Valve</form:option>
								<form:option value="HRC_OR_TI_AC_R_POP" onclick="inAdmissionHrcOrTicROthrTextShowHide()">Post-OP</form:option>
								<form:option value="HRC_OR_TI_AC_R_LOWEF" onclick="inAdmissionHrcOrTicROthrTextShowHide()">Low EF</form:option>
								<form:option value="HRC_OR_TI_AC_R_OTHR" onclick="inAdmissionHrcOrTicROthrTextShowHide()">Other</form:option>
								</form:select>
								${admissionForm.inAdmissionhrcOrTiAcReasons[0] == 'HRC_OR_TI_AC_R_OTHR' || admissionForm.inAdmissionhrcOrTiAcReasons[1] == 'HRC_OR_TI_AC_R_OTHR' || admissionForm.inAdmissionhrcOrTiAcReasons[2] == 'HRC_OR_TI_AC_R_OTHR' || admissionForm.inAdmissionhrcOrTiAcReasons[3] == 'HRC_OR_TI_AC_R_OTHR' || admissionForm.inAdmissionhrcOrTiAcReasons[4] == 'HRC_OR_TI_AC_R_OTHR' || admissionForm.inAdmissionhrcOrTiAcReasons[5] == 'HRC_OR_TI_AC_R_OTHR'   ? "<span id='inAdmissionHrcOrTicROthrTextShowHide' class='large-3'>":"<span id='inAdmissionHrcOrTicROthrTextShowHide' class='large-3' style='display: none !important;'>"}
									<label style="margin-top:-15px; margin-bottom:18px"><i class="fi-star other small"></i>other
									<form:input path="inAdmissionHrcOrTicROthrText"/></label>
								</span>
								</div>
								<div class='large-4 column padding-lr' ><label>Duration:
									 <form:input path="inAdmissionHrcOrTiAcRDurtn" maxlength="6"/></label>
								</div>
								<div class='large-4 column padding-lr'>
										<label>Goal INR:</label>
										<form:select path="inAdmissionGoalInr" multiple="true" cssStyle=" height: 50px !important;">
										<form:option value="HRC_OR_TI_GI_1_5_2_5" onclick="inAdmissionHrcOrTiGiOthrTextShowHide()">1.5-2.5</form:option>
										<form:option value="HRC_OR_TI_GI_2_3" onclick="inAdmissionHrcOrTiGiOthrTextShowHide()">2-3</form:option>
										<form:option value="HRC_OR_TI_GI_OTHR" onclick="inAdmissionHrcOrTiGiOthrTextShowHide()">Other</form:option>
										</form:select>
									  ${admissionForm.inAdmissionGoalInr[0] == 'HRC_OR_TI_GI_OTHR' || admissionForm.inAdmissionGoalInr[1] == 'HRC_OR_TI_GI_OTHR' || admissionForm.inAdmissionGoalInr[2] == 'HRC_OR_TI_GI_OTHR'  ? "<span id='inAdmissionHrcOrTiGiOthrTextShowHide'>":"<span id='inAdmissionHrcOrTiGiOthrTextShowHide'  style='display: none !important;'>"}
										<label style="margin-top:-15px; margin-bottom:18px"><i class="fi-star other small"></i>other
										<form:input path="inAdmissionHrcOrTiGiOthrText"/></label>
										</span>
								</div>
								<div style="clear:both;"></div>
								
								</span>
							</div>
						</div>
						
						<div class="row">
							<div class="large-12 columns padding-lr">
						      <form:checkbox value="HRC_OR_TI_OP" id="inAdmissionHrcOrTiOp" onclick="OnPPIShowHide(this)" path="inAdmissionHrcOrTiOp"/><span style=" font-size: 13px; !important;"><b>&nbsp;&nbsp;On PPI:</b></span>
							</div>
							<div class="large-12 columns padding-lr">
								<div class="large-4 column padding-lr">
								${admissionForm.inAdmissionHrcOrTiOp == 'HRC_OR_TI_OP' ? "<span  id='OnPPIShowHide'>":"<span id='OnPPIShowHide' style='display: none;'>"}
								<label>Indication(s):</label>
								<form:select path="inAdmissionHrcOrTiOpIndications" multiple="true" cssStyle=" height: 50px !important;">
								<form:option value="HRC_OR_TI_OP__I_IHP_AND_CBD" onclick="inAdmissionHrcOrTiOpIndicationsTextShowHide()">In-hospital prophylaxis and can be d/c</form:option>
								<form:option value="HRC_OR_TI_OP_SD" onclick="inAdmissionHrcOrTiOpIndicationsTextShowHide()">Specific Dx</form:option>
								</form:select>
								${admissionForm.inAdmissionHrcOrTiOpIndications[0] || admissionForm.inAdmissionHrcOrTiOpIndications[1] == 'HRC_OR_TI_OP_SD'   ? "<span id='inAdmissionHrcOrTiOpIndicationsTextShowHide' class='large-12'>":"<span id='inAdmissionHrcOrTiOpIndicationsTextShowHide' class='large-12' style='display: none;'>"}
									<label style="margin-top:-15px; margin-bottom:18px"><i class="fi-star other small"></i>Specific Dx:
									<form:input path="inAdmissionHrcOrTiOpIndicationsText"/></label> 
								</span>
								</span>
							</div>
						   </div>
						</div>
						
						<div class="row">
							<div class="large-12 columns padding-lr">
								<form:checkbox value="HRC_OR_TI_OAB" id="inAdmissionhrcOrTiOab" onclick="OnAntibioticsShowHide(this)" path="inAdmissionhrcOrTiOab"/>&nbsp;&nbsp;<span style=" font-size: 13px; !important;"><i class="fi-star small greenStar"></i><b>On Antibiotics:&nbsp;&nbsp;</b></span>
							
							${admissionForm.inAdmissionhrcOrTiOab == 'HRC_OR_TI_OAB' ? "<span id='OnAntibioticsShowHide'  class='padding-lr'>":"<span id='OnAntibioticsShowHide' style='display: none;'  class='padding-lr'>"}
							<div class="large-12 columns" style="padding-bottom:10px">
								<div class='large-4 column padding-lr' ><label>Indication(s):<form:input path="inAdmissionHrcOrTiOabIndctn"/></label></div>
								<div class='large-4 column padding-lr' ><label style='color: #4d4d4d; font-size: 13px;'>Total Treatment Course In Days</label><form:input path="inAdmissionHrcOrTiOabTtcd" maxlength="6"/></div>
								<div class='large-3 column padding-lr end' ><label>Date Started: <form:input onchange="return validateDate(this)"  path="inAdmissionHrcOrTiOabDs" class="datepicker"/></label></div>								
							</div>
							</span>
						   </div>
						</div>
						
						<div class="row">
						 
							<div class="large-12 columns  padding-lr">
								<form:checkbox value="HRC_OR_TI_D" id="inAdmissionHrcOrTid" onclick="DiabeticShowHide(this)" path="inAdmissionHrcOrTid"/>&nbsp;&nbsp;<span style=" font-size: 13px; !important;"><b>Diabetic:</b></span>
							</div>
							${admissionForm.inAdmissionHrcOrTid == 'HRC_OR_TI_D' ? "<span id='DiabeticShowHide'>":"<span id='DiabeticShowHide' style='display: none;'>"}
							<div class="large-3 column padding-lr">
								<label>Most recent glucose Date:
								<form:input onchange="return validateDate(this)"  path="inAdmissionHrcOrTiDMrgd" class="datepicker"/></label>
							</div>
							<div class="large-2 column padding-lr end">
								<label>Time (am/pm): <form:input path="inAdmissionHrcOrTiDMrgt"/></label>
							</div>
							</span>
					   </div>
					 </div>
					 
<!-- 				    panel col_border col_border_blue   "CLOSE"                         -->


<!-- 				    panel col_border col_border_blue   "OPEN"                         -->
					<div class='panel col_border col_border_blue'>
						<p class="lead p_heading">I. Procedures & Key Findings (during this hospitalization) * Please Attach Reports *</p>
						<div class="row">
							<div class="large-6 column padding-lr">
								<label>List Procedures (surgeries, imaging): <form:input path="listProcedures"/></label>
							</div>
							<div class="large-6 column padding-lr end">
								<label>Key findings: <form:input path="keyFindings"/></label>
							</div>
						</div>
					</div>
					
<!-- 				    panel col_border col_border_blue   "CLOSE"                        -->


<!-- 				    panel col_border col_border_blue   "OPEN"                        -->
					<div class='panel col_border col_border_blue'>
						<p class="lead p_heading">J. Medications and Allergies</p>
						<div class="row">
							<div class="large-12 columns padding-lr">
								<label><form:checkbox value="M_AND_A_MLA" path="inAdmissionMAndAMla"/><b>  Medication List Attached</b></label>
								<label><b>Please provide a HARD COPY PRESCRIPTION FOR CONTROLLED SUBSTANCES</b></label>
								<div class="large-6 column padding-lr">
								  <div class="small-12 columns padding-lr">
								   <label>Allergies:</label>
								  </div>
								  <form:radiobutton  value="M_AND_A_ALLRGS_NK" onclick="inAdmissionMAndAAllrgsYSpcfyShowHide(this)"  path="inAdmissionMAndAAllrgs" label="None known"/>
								  <form:radiobutton  value="M_AND_A_ALLRGS_Y"  onclick="inAdmissionMAndAAllrgsYSpcfyShowHide(this)" path="inAdmissionMAndAAllrgs" label="Yes (specify)"/>
								
								 ${admissionForm.inAdmissionMAndAAllrgs == 'M_AND_A_ALLRGS_Y'   ? "<span id='inAdmissionMAndAAllrgsYSpcfyShowHide'  class='large-6 columns inline right'>":"<span id='inAdmissionMAndAAllrgsYSpcfyShowHide' class='large-6 columns inline right' style='display: none;'>"}
								  <form:input path="inAdmissionMAndAAllrgsYSpcfy"/>
								  </span>
								  </div>
							    
							    <div class="large-6 column padding-lr">
									<label>Pain med:</label>
									<form:radiobutton  value="M_AND_A_PM_N"   onclick="inAdmissionMAndAPmTextShowHide(this)" path="inAdmissionMAndAPm" label="No"/>
									<form:radiobutton  value="M_AND_A_PM_Y"   onclick="inAdmissionMAndAPmTextShowHide(this)" path="inAdmissionMAndAPm" label="Yes (specify)"/>
									${admissionForm.inAdmissionMAndAPm == 'M_AND_A_PM_Y'  ? "<span id='inAdmissionMAndAPmTextShowHide'  class='inline right large-8'>":"<span id='inAdmissionMAndAPmTextShowHide'  class='inline right large-8' style='display: none;'>"}
										<form:input path="inAdmissionMAndAPmText" cssClass="other-input"/>
									</span>
							    </div>
							</div>
						</div>
						
						<div class='row'>
							
							<div class="large-3 column padding-lr">
									<label>Dose: <form:input path="inAdmissionMAndAPmD"/></label>
								</div>
							<div class="large-3 column padding-lr end">
									<label>Last Dose (am/pm): <form:input path="inAdmissionMAndAPmLd" readonly="true" onchange="return validateDate(this)" cssClass="dateAndTimePicker" placeholder="mm/dd/yyy hh:mm:ss am/pm" cssStyle="cursor: pointer;"/></label>
								</div>

						</div>
				   </div>
				   
<!-- 				    panel col_border col_border_blue   "CLOSE"                        -->
              </div>
<!--                       _________PANEL-2 CLOSED__________                              -->


<!--                       _________PANEL-3 OPEN__________                              -->

				<div class="content" id='panel3'>
				
<!-- 				    panel col_border col_border_blue   "OPEN"                       -->
					<div class='panel col_border col_border_blue'>
						<p class="lead p_heading">K. Nursing Care</p>
						
						 <div class="row">
						  <div class="large-12 columns padding-lr">
							<label><b>Physical and Sensory Function</b></label>
							 <div class="large-3 column padding-lr">
							  <span style='color: #4d4d4d;font-size: 13px;'><b>Ambulation</b></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  <form:select  path="inAdmissionAmbulation">
							   <form:option value="">-- select --</form:option>
							   <form:option value="NCPSF_AMBLTN_INDPNDNT">Independent</form:option>
							   <form:option value="NCPSF_AMBLTN_WA">With Assistance</form:option>
							   <form:option value="NCPSF_AMBLTN_WAD">With Assistive Device</form:option>
						 	   <form:option value="NCPSF_AMBLTN_NTA">Not Ambulatory</form:option>
							  </form:select>
							 </div>
                             <div class="large-3 column padding-lr">
							  <span style='color: #4d4d4d;font-size: 13px;'><b>Weight Bearing</b></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  <form:select path="inAdmissionWeightBearing">
							    <form:option value="">-- select --</form:option>
							    <form:option value="NCPSF_WB_FULL">Full</form:option>
							    <form:option value="NCPSF_WB_PARTL_L_OR_R">Partial L / R</form:option>
							    <form:option value="NCPSF_WB_NONE">None L / R</form:option>
							  </form:select>
							 </div>
							 <div class="large-3 column padding-lr end">
							  <span style='color: #4d4d4d;font-size: 13px;'><b>Transfer</b></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  <form:select path="inAdmissionTransfer">
							   <form:option value="">-- select --</form:option>
							   <form:option value="NCPSF_TRNSFR_SLF">Self</form:option>
							   <form:option value="NCPSF_TRNSFR_OPA">1-Person Assist</form:option>
							   <form:option value="NCPSF_TRNSFR_TPA">2-Person Assist</form:option>
							  </form:select>
							  </div>
                             </div> 
                            </div>
                            
							<div class='row'>
								<div class="large-12 columns padding-lr">
								  <div class="large-12">
									<span style='color: #4d4d4d;font-size: 13px;'><b>Sensory Function</b></span>
								  </div>
									<div class="large-3 column padding-lr">
									<span class="springLabel"><b>Sight:</b></span>
									<form:select path="inAdmissionSensoryFunctionSight">
									<form:option value="">-- select --</form:option>
									<form:option value="NCPSF_SF_S_N">Normal</form:option>
									<form:option value="NCPSF_SF_S_IMPRD">Impaired</form:option>
									<form:option value="NCPSF_SF_S_BLND">Blind</form:option>
									</form:select>
									</div>
									<div class="large-3 column padding-lr end">
									<span class="springLabel"><b>Hearing:</b></span>
									<form:select path="inAdmissionSensoryFunctionHearing">
									<form:option value="">-- select --</form:option>
									<form:option value="NCPSF_SF_H_N">Normal</form:option>
									<form:option value="NCPSF_SF_H_IMPRD">Impaired</form:option>
									<form:option value="NCPSF_SF_H_DF">Deaf</form:option>
									</form:select>
									</div>
								</div>
							</div>
							
							<div class='row'>
								<div class="large-12 columns padding-lr">
								 <div class="large-3 column padding-lr">
									<span style='color: #4d4d4d;font-size: 13px;'><b>Devices</b></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<form:select path="inAdmissionDevices" multiple="true" cssStyle=" height: 100px !important;">
									<form:option value="NCPSF_DEV_WC">Wheelchair</form:option>
									<form:option value="NCPSF_DEV_WLKR">Walker</form:option>
									<form:option value="NCPSF_DEV_C">Cane</form:option>
									<form:option value="NCPSF_DEV_CRTCHS">Crutches</form:option>
									<form:option value="NCPSF_DEV_PROS">Prosthesis</form:option>
									<form:option value="NCPSF_DEV_GLSES">Glasses</form:option>
									<form:option value="NCPSF_DEV_CNTCTS">Contacts</form:option>
									<form:option value="NCPSF_DEV_D">Dentures</form:option>
									<form:option value="NCPSF_DEV_HAL_OR_R">Hearing Aid L / R</form:option>
									</form:select>
								 </div>
								 
								</div>
							</div>
							
							<div class='row'>
								<div class="large-12 columns padding-lr">
									<div class="large-3 column padding-lr">
									 <span style='color: #4d4d4d;font-size: 13px;'><b>Continence</b></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 <form:select path="inAdmissionContinence" multiple="true" cssStyle=" height: 60px !important;">
									  <form:option value="NCPSF_C_CONTNENT" onclick="inAdmissionContinenceTextShowHide(this)">Continent</form:option>
									  <form:option value="NCPSF_C_INCONTNENT" onclick="inAdmissionContinenceTextShowHide(this)">Bladder Incontinent</form:option>
									  <form:option value="NCPSF_C_OSTMY" onclick="inAdmissionContinenceTextShowHide(this)">Ostomy</form:option>
									 </form:select>
									 </div>
								</div>
								<div class="padding-lr">
								<div class="large-12 columns padding-lr">
								    <div class="large-12 columns padding-lr"><label class="padding-lr"><b><i>catheter</i></b></label></div>
								     <div class="large-12 columns padding-lr">
									 <div class="large-3 column padding-lr">
									   <label>Date inserted<form:input  onchange="return validateDate(this)" path="inAdmissionContinenceText" cssClass="datepicker"/></label>
									 </div>			    
									 <div class="large-8 column padding-lr end">
									  <div class="large-12 columns padding-lr">
									   <span style='color: #4d4d4d;font-size: 13px;'>Reason for catheter:</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;								
									  </div>
									  <div class="large-12 columns padding-lr">
									   <div class="large-2 columns">
									    <form:checkbox value="NCPSF_C_RFC_R" path="inAdmissionReasonforcatheter" label="Retention"/>
									   </div>
									   <div class="large-3 columns">
									    <form:checkbox value="NCPSF_C_RFC_SP" path="inAdmissionReasonforcatheter" label="Skin protection"/>
									   </div>
									   <div class="large-2 columns"> 
									    <form:checkbox value="NCPSF_C_RFC_OTHR" path="inAdmissionReasonforcatheter" onclick="inAdmissionReasonforcatheterTextShowHide(this)" label="Other"/>
                                       </div>
                                       <div class="large-3 columns end catheterothertext" hidden="hidden">  
										<form:input path="inAdmissionReasonforcatheterText"/>
									   </div>
                                      </div>
									</div>
									</div>			    
								</div>
								</div>
								<div class="padding-lr">
								<div class="large-12 columns padding-lr">
								  <div class="padding-lr">
								    <div class="large-12 columns padding-lr"><label class="padding-lr"><b><i>Bowel Incontinent</i></b></label></div>
								    <div class="large-3 columns padding-lr end">
								     <label class="padding-lr">Date of last BM</label><form:input  onchange="return validateDate(this)" path="inAdmissionNcpsfCText" cssClass="other-input datepicker"/>
								    </div>
								  </div>
								</div>
								</div>
							</div>
							
							<div class='row'>
								<div class="large-12 columns padding-lr">
									<label class="padding-lr"><b>Nutrition and Hydration</b></label>
									<div class="large-3 column padding-lr">
										<label>Diet: <form:input path="inAdmissionNcpsfNAndHD"/></label>
									</div>
									<div class="large-3 column padding-lr">
										<label>Consistency: <form:input path="inAdmissionNcpsfNAndHC"/></label>
									</div>
									<div class="large-3 column padding-lr end">
										<label>Free Water Restriction:<form:input path="inAdmissionNcpsfNAndHFwr"/></label>
									</div>
								</div>
							</div>
							
							<div class='row'>
								<div class="large-12 columns padding-lr">
									<div class="large-6 column padding-lr">
									<span style='color: #4d4d4d;font-size: 13px;'><b>Eating Instructions</b></span>&nbsp;&nbsp;&nbsp;
									<form:select path="inAdmissionEatingInstructions" multiple="true" cssStyle=" height: 100px !important;">
									<form:option value="NCPSF_N_AND_H_EI_SLF">Self</form:option>
									<form:option value="NCPSF_N_AND_H_EI_WA">With Assistance</form:option>
									<form:option value="NCPSF_N_AND_H_EI_DS">Difficulty Swallowing</form:option>
									<form:option value="NCPSF_N_AND_H_EI_DS_ASTRIA">Attach speech therapy recommendations if available</form:option>
									</form:select>
									</div>
								</div>
							</div>
 
							<div class='row'>
								<div class="large-12 columns">
									<label style='color: #4d4d4d;font-size: 13px;' class="padding-lr"><b class="padding-lr">Tube Feeding</b></label>
									<div class="large-12 padding-lr">
									<div class="large-6 column padding-lr">
									<form:select path="inAdmissionTubeFeeding" onchange="inAdmissionNcpsfNAndHTfDiTextShowHide(this)">
									<form:option value="">-- select --</form:option>
									<form:option value="NCPSF_N_AND_H_TF_GT">G-tube</form:option>
									<form:option value="NCPSF_N_AND_H_TF_JT">J-tube</form:option>
									<form:option value="NCPSF_N_AND_H_TF_DI">Date inserted</form:option>
									</form:select>
									</div>
									</div>
									${admissionForm.inAdmissionTubeFeeding == 'NCPSF_N_AND_H_TF_DI'   ? "<span id='inAdmissionNcpsfNAndHTfDiTextShowHide' class='large-3 columns'>":"<span id='inAdmissionNcpsfNAndHTfDiTextShowHide' style='display: none;' class='large-3 columns'>"}
										<form:input onchange="return validateDate(this)" placeholder="Date Inserted"  path="inAdmissionNcpsfNAndHTfDiText" cssClass="datepicker"/>
									</span>
									<div style="clear:both;"></div>
									<div class='large-2 column padding-lr'>
										<label class="padding-lr">Free Water Bolus(in cc): <form:input path="inAdmissionNcpsfNAndHTfDiFwbCc" maxlength="6"/>
										</label>
									</div>
									<div class='large-2 column padding-lr'>
										<label class="padding-lr">every(in hrs): <form:input path="inAdmissionNcpsfNAndHTfDiFwbHrs" maxlength="6"/>
										</label>
									</div>
									<div class='large-4 column padding-lr'>
										<form:checkbox value="NCPSF_N_AND_H_TF_TFP" id="inAdmissionNcpsfNAndHTfTfp" onclick="TubeFeedProductShowHideClass(this)" path="inAdmissionNcpsfNAndHTfTfp" label="Tube feed product"/>
										${admissionForm.inAdmissionNcpsfNAndHTfTfp == 'NCPSF_N_AND_H_TF_TFP' ? "<span class='TubeFeedProductShowHideClass'>":"<span class='TubeFeedProductShowHideClass' style='display:none;!important;'>"}
										<form:input path="inAdmissionNcpsfNAndHTfTfpt" cssStyle="position: relative;display:block;!important;"/>
										</span>
									</div>
									${admissionForm.inAdmissionNcpsfNAndHTfTfp == 'NCPSF_N_AND_H_TF_TFP' ? "<span class='TubeFeedProductShowHideClass'>":"<span class='TubeFeedProductShowHideClass' style='display:none;!important;'>"}
									<div class='large-2 column padding-lr'>
										<label class="padding-lr">Rate(in cc/h): <form:input path="inAdmissionncpsfNAndHTfTfpRteCcPerH" maxlength="6" cssStyle="display:block;!important;"/>
										</label>
									</div>
									<div class='large-2 column padding-lr end'>
										<label class="padding-lr">Duration(in h/day): <form:input path="inAdmissionncpsfNAndHTfTfpEveryHOrDay"  maxlength="6" cssStyle="display:block;!important;"/>
										</label>
									</div>
									</span>
								</div>
							</div>
							
							<div class='row'>
								<div class="large-12 columns padding-lr">
										<div class="large-3 column padding-lr">
											<label><b>Treatments and Therapeutic Devices</b></label>
											<form:select path="inAdmissionNcpsfTtdPicc" multiple="true" cssStyle=" height: 60px !important;">
											 <form:option value="NCPSF_TTD_PICC" onclick="inAdmissionNcpsfTtdPiccTextShowHide(this)">PICC</form:option>
											 <form:option value="NCPSF_TTD_PRTCTH" onclick="inAdmissionNcpsfTtdPiccTextShowHide(this)">Portacath Date inserted</form:option>
											</form:select>
											${admissionForm.inAdmissionNcpsfTtdPicc[0] == 'NCPSF_TTD_PRTCTH' || admissionForm.inAdmissionNcpsfTtdPicc[1] == 'NCPSF_TTD_PRTCTH'   ? "<span class='small-12 columns' id='inAdmissionNcpsfTtdPiccTextShowHide'>":"<span class='small-12 columns' id='inAdmissionNcpsfTtdPiccTextShowHide' style='display: none;'>"}
												<label style="margin-top:-15px; margin-bottom:18px"><i class="fi-star other small"></i>Date inserted:
												<form:input onchange="return validateDate(this)"  path="inAdmissionNcpsfTtdPiccText" class="datepicker"/></label>
										    </span>
										</div>
										<div class="large-3 column padding-lr end">
											<label><b>Cardiac</b></label>
											<form:select path="inAdmissionCardiac" multiple="true" cssStyle=" height: 60px !important;">
											<form:option value="NCPSF_TTD_C_PM" onclick="inAdmissionCardiacTextShowHide(this)">Pacemaker</form:option>
											<form:option value="NCPSF_TTD_C_ICD" onclick="inAdmissionCardiacTextShowHide(this)">ICD</form:option>
											<form:option value="NCPSF_TTD_C_OTHR" onclick="inAdmissionCardiacTextShowHide(this)">Other (specify)</form:option>
											</form:select>
											<div class='small-12 columns'>
												${admissionForm.inAdmissionCardiac[0] == 'NCPSF_TTD_C_OTHR' || admissionForm.inAdmissionCardiac[1] == 'NCPSF_TTD_C_OTHR' || admissionForm.inAdmissionCardiac[2] == 'NCPSF_TTD_C_OTHR'  ? "<span class='small-12 columns' id='inAdmissionCardiacTextShowHide'>":"<span class='small-12 columns' id='inAdmissionCardiacTextShowHide' style='display: none;'>"}
													<label style="margin-top:-15px; margin-bottom:18px"><i class="fi-star other small"></i>other(specify):
													<form:input path="inAdmissionCardiacText"/></label>
												</span>
											</div>
										</div>
								</div>
							</div>
							
							<div class='row' style="margin: 0;">
								
								<div class="large-12 columns padding-lr">
								 <div class="padding-lr">
									<div class='large-12 columns'>
									
								 	<label><b>Respiratory</b></label>
								 	<div class="small-6 columns">
										<form:select path="inAdmissionRespiratory" multiple="true" cssStyle=" height: 100px !important;">
											<form:option value="NCPSF_TTD_R_CPAP" onclick="inAdmissionRespiratoryO2TextShowHide(this)">CPAP</form:option>
											<form:option value="NCPSF_TTD_R_BIPAP" onclick="inAdmissionRespiratoryO2TextShowHide(this)">BiPAP</form:option>
											<form:option value="NCPSF_TTD_R_PRN" onclick="inAdmissionRespiratoryO2TextShowHide(this)">prn</form:option>
											<form:option value="NCPSF_TTD_R_CNTNOUS" onclick="inAdmissionRespiratoryO2TextShowHide(this)">continuous</form:option>
											<form:option value="NCPSF_TTD_R_SCTN" onclick="inAdmissionRespiratoryO2TextShowHide(this)">Suction</form:option>
<%-- 											<form:option value="NCPSF_TTD_R_O2" onclick="inAdmissionRespiratoryO2TextShowHide(this)">O2(In L)</form:option> --%>
<%-- 											<form:option value="NCPSF_TTD_R_TS" onclick="inAdmissionRespiratoryO2TextShowHide(this)">Trach size</form:option> --%>
										</form:select>
									</div>
									</div>
									<div class='large-12 columns padding-lr'>
									 <div class="large-2 columns padding-lr">
									    <label class="padding-lr"><b><i>O2(In L)</i></b><form:input  path="inAdmissionRespiratoryO2Text"/></label>
									    
									 </div>
									 <div class="large-2 columns padding-lr end">
									    <label class="padding-lr"><b><i>Trach size</i></b><form:input  path="inAdmissionRespiratoryTsText"/></label>
									    
									 </div>
									</div>
									</div>
								</div>
								<div class="large-6 column padding-lr end">
									<div class="small-12 columns padding-lr">
									<label><b>Therapies (please attach assessment/recommendations)</b></label>
									</div>
									<div class="small-12 columns padding-lr">
									  <form:select path="inAdmissionTherapies" multiple="true" cssStyle=" height: 100px !important;">
										 <form:option value="NCPSF_THRPS_PT">PT</form:option>
										 <form:option value="NCPSF_THRPS_OT">OT</form:option>
										 <form:option value="NCPSF_THRPS_S">Speech</form:option>
										 <form:option value="NCPSF_THRPS_R">Respiratory</form:option>
										 <form:option value="NCPSF_THRPS_D">Dialysis</form:option>
									  </form:select>
									 </div>
									</div>
							 </div>
							 
							 <div class='row'  style="margin: 0;">
								<label><b><i class="fi-star small greenStar"></i>Skin Care</b></label>
								    <div class="large-12  padding-lr columns">
										<form:checkbox value="NCPSF_SC_NSB" path="inAdmissionNcpsfScNsb" label="No skin breakdown"/>
									</div>
									<div class='large-12  padding-lr columns'>
										<form:checkbox value="NCPSF_SC_PU" onclick="Pressureulcer1ShowHide(this)" path="inAdmissionNcpsfScpU" label="Pressure ulcer"/>
											${admissionForm.inAdmissionNcpsfScpU == 'NCPSF_SC_PU' ? "<span class='small-12 columns PressureulcerShowHideClass1'>":"<span class='small-12 columns PressureulcerShowHideClass1' style='display: none;'>"}
											<div class='large-3 column padding-lr'>
													<label>Stage: <form:input path="inAdmissionNcpsfScPuStage"/></label>
											</div>
											<div class='large-3 column padding-lr end'>
													<label>Location: <form:input path="inAdmissionNcpsfScPuLocation"/></label>
											</div>
										 </span>
									 </div>
 									 <div class='large-12  padding-lr columns'>	
										 
											<form:checkbox value="NCPSF_SC_SPU" onclick="Pressureulcer2ShowHide(this)" path="inAdmissionNcpsfScSpu" label="2nd Pressure ulcer:"/>
											 <div class='large-12 columns'>
											 ${admissionForm.inAdmissionNcpsfScSpu == 'NCPSF_SC_SPU' ? "<span class='PressureulcerShowHideClass2'>":"<span class='PressureulcerShowHideClass2' style='display: none;'>"}
											 <div class='large-3 column padding-lr padding-lr'>
												<label>Stage: <form:input path="inAdmissionNcpsfScSpuStage" cssStyle="display: block;"/></label>
											 </div>
											 <div class='large-3 column padding-lr end'>
													<label>Location: <form:input path="inAdmissionNcpsfScSpuLocation" cssStyle="display: block;"/></label>
											 </div>
										    </span>
										    </div>
									</div>
									<div class='large-12 columns end'>
									   <div class='large-6 column padding-lr'>
										  <form:checkbox value="NCPSF_SC_OTHR" onclick="inAdmissionNcpsfScOthrSpcfyShowHide(this)" path="inAdmissionNcpsfScOthr" label="Other wounds (specify)"/>
										  ${admissionForm.inAdmissionNcpsfScOthr == 'NCPSF_SC_OTHR'  ? "<span id='inAdmissionNcpsfScOthrSpcfyShowHide' class='padding-lr'>":"<span  id='inAdmissionNcpsfScOthrSpcfyShowHide'  class='padding-lr' style='display: none;'>"}
										  	<form:input path="inAdmissionNcpsfScOthrSpcfy"/>
										  </span>
										  </div>
								   </div>
								 </div>
							 
							 <div class='row' style="margin: 0;">
								<label class="padding-lr"><b>Risks and Precautions (check all that apply)</b></label>
								<div class='large-12 columns padding-lr'>
									<div class='large-6 column padding-lr'>
									  <form:select path="inAdmissionRisksandPrecautions" multiple="true" cssStyle=" height: 100px !important;">
										<form:option value="NCPSF_R_AND_P_FALL" onclick="inAdmissionRisksandPrecautionsTextShowHide(this)">Fall</form:option>
										<form:option value="NCPSF_R_AND_P_DELRUM" onclick="inAdmissionRisksandPrecautionsTextShowHide(this)">Delirium</form:option>
										<form:option value="NCPSF_R_AND_P_AGTATN" onclick="inAdmissionRisksandPrecautionsTextShowHide(this)">Agitation</form:option>
										<form:option value="NCPSF_R_AND_P_AGGRSN" onclick="inAdmissionRisksandPrecautionsTextShowHide(this)">Aggression</form:option>
										<form:option value="NCPSF_R_AND_P_UEE" onclick="inAdmissionRisksandPrecautionsTextShowHide(this)">Unescorted exiting</form:option>
										<form:option value="NCPSF_R_AND_P_ASPRTN" onclick="inAdmissionRisksandPrecautionsTextShowHide(this)">Aspiration</form:option>
										<form:option value="NCPSF_R_AND_P_OTHR" onclick="inAdmissionRisksandPrecautionsTextShowHide(this)">Other</form:option>
										</form:select>
										${admissionForm.inAdmissionRisksandPrecautions == 'NCPSF_R_AND_P_OTHR'   ? "<span id='inAdmissionRisksandPrecautionsTextShowHide' class='other-date'>":"<span  id='inAdmissionRisksandPrecautionsTextShowHide' class='other-date' style='display: none;'>"}
											<label style="margin-top:-15px; margin-bottom:18px"><i class="fi-star other small"></i>other
											<form:input path="inAdmissionRisksandPrecautionsText" cssClass="other-input"/>
										    </label>
										</span>
									</div>
									<div class='large-3 column padding-lr end'>
										<label>Precautions: <form:input path="inAdmissionNcpsfRAndPOtherPrecautions"/></label>
									</div>
								 </div>
							 </div>
							  
							 <div class='row' style="margin: 0;">
								<label><b>Infection Control Issues</b></label>
								<div class='large-12 columns padding-lr'>
								  <div class='large-6 columns'>
									<label>Infection/Colonization</label>
									<form:select path="inAdmissionInfectionRColonization" multiple="true" cssStyle=" height: 100px !important;">
										<form:option value="NCPSF_ICI_I_OR_C_MRSA">MRSA</form:option>
										<form:option value="NCPSF_ICI_I_OR_C_VRE">VRE</form:option>
										<form:option value="NCPSF_ICI_I_OR_C_CDI">C.difficle</form:option>
										<form:option value="NCPSF_ICI_I_OR_C_ESBL">ESBL</form:option>
										<form:option value="NCPSF_ICI_I_OR_C_NV">Norovirus</form:option>
										<form:option value="NCPSF_ICI_I_OR_C_F_OR_R">Flu/respiratory</form:option>
									</form:select>
								  </div>
								</div>
								<div class='large-6 columns padding-lr'>
								   <div>
										<label>Isolation Precautions</label>
								     <form:select path="inAdmissionIsolationPrecautions">
										<form:option value="">-- select --</form:option>
										<form:option value="NCPSF_ICI_IP_NONE">None</form:option>
										<form:option value="NCPSF_ICI_IP_CNTCT">Contact</form:option>
										<form:option value="NCPSF_ICI_IP_CNTCTPLUS">Contact-Plus</form:option>
										<form:option value="NCPSF_ICI_IP_DRPLT">Droplet</form:option>
										<form:option value="NCPSF_ICI_IP_ARBRNE">Airborne</form:option>
									 </form:select>
									</div>
								</div>
								<div class='large-12 columns padding-lr'>
									<div class='large-12 columns padding-lr'>
										<label class="padding-lr">Immunizations (in hospital)</label>
									</div>
									<div class='large-12 columns padding-lr'>
										<div class='large-6 column padding-lr'>
											<form:checkbox value="NCPSF_ICI_I_INFLUNZA" onclick="InfluenzaShowHide(this)" path="inAdmissionNcpsfIciIInflunza" label="Influenza"/>
											${fn:length(admissionForm.inAdmissionNcpsfIciI) > 0  ? "<span id='InfluenzaShowHide'>":"<span id='InfluenzaShowHide' style='display: none;'>"}
											<form:select path="inAdmissionNcpsfIciI" onchange="inAdmissionNcpsfIciITextShowHide(this)" >
												<form:option value="">-- select --</form:option>
												<form:option value="NCPSF_ICI_I_NO">No</form:option>
												<form:option value="NCPSF_ICI_I_Y">Yes (date)</form:option>
											</form:select>
											${admissionForm.inAdmissionNcpsfIciI == 'NCPSF_ICI_I_Y'   ? "<span id='inAdmissionNcpsfIciITextShowHide' class='small-12 columns'>":"<span id='inAdmissionNcpsfIciITextShowHide' class='small-12 columns' style='display: none;'>"}
												<form:input onchange="return validateDate(this)"  path="inAdmissionNcpsfIciIText" cssClass="datepicker" />
											</span>
											</span>
										</div>
										<div class='large-6 column padding-lr end'>
											<form:checkbox value="NCPSF_ICI_I_P" onclick="PneumococcalShowHide(this)" path="inAdmissionNcpsfIciIPneumococcal" label="Pneumococcal"/>
											${fn:length(admissionForm.inAdmissionNcpsfIciIP) > 0  ? "<span id='PneumococcalShowHide'>":"<span id='PneumococcalShowHide' style='display: none;'>"}
											  <form:select path="inAdmissionNcpsfIciIP" onchange="inAdmissionNcpsfIciIPTextShowHide(this)">
												<form:option value="">-- select --</form:option>
												<form:option value="NCPSF_ICI_I_P_NO">No</form:option>
												<form:option value="NCPSF_ICI_I_P_YES">Yes (date)</form:option>
											  </form:select>
											  ${admissionForm.inAdmissionNcpsfIciIP == 'NCPSF_ICI_I_P_YES'   ? "<span id='inAdmissionNcpsfIciIPTextShowHide'>":"<span id='inAdmissionNcpsfIciIPTextShowHide' style='display: none;'>"}
												<form:input onchange="return validateDate(this)"  path="inAdmissionNcpsfIciIPText" cssClass="datepicker" />
											  </span>
											</span>
										</div>
									</div>
							   </div>
							 </div>
						</div>
<!-- 				    panel col_border col_border_blue   "CLOSED"                      -->
						
						
<!-- 				    panel col_border col_border_blue   "OPEN"                          -->

					<div class='panel col_border col_border_blue'>
						<p class="lead p_heading">L. Critical Transitional Care Information: Pending Tests and Follow-Up</p>
						<div class="row">
							<div class="large-12 columns padding-lr">
								<label>Summarize high-priority care needs for next 24-48 hrs (including essential medications, pain control, tests needed, follow-up):
								<form:input path="summarizeHighPriorityNeeds"/></label>
							</div>
							<div class="large-12 columns padding-lr">
								<label>Pending Lab and Test Results:
								<form:input path="pendingLabAndTestResults"/></label>
							</div>
							<div class="large-12 columns padding-lr">
								<label>Recommended Follow-Up Tests, Procedures, Appointments:
								<form:input path="recommendedFollowupTests"/></label>
							</div>
						</div>
					</div>
					
<!-- 				    panel col_border col_border_blue   "CLOSE"                       -->

	</div>
				
<!--                       _________PANEL-3 CLOSED__________                              -->

<!--                       _________PANEL-4 OPEN__________                              -->
                
				<div class="content" id='panel4'>
					<div class='panel col_border col_border_blue' id="medicalReconciliationModel">
						 part 1
						<div class='row'>
							<p style="margin-bottom: 0;"><b>Part 1:</b> Hospital Recommended Medications Needing Clarification</p>
							<table id="medicalReconciliationTable1">
								<thead>
									<tr>
										<th width="30%">Recommended by Hospital at Discharge</th>
										<th width="50%">Clarification Needed*</th>
										<th>Resolution</th>
										<th width='4%'><button id="medReconPart1Add" class='button small right' style='padding: 5px 15px; margin: 0;' name='Add' type="button">Add</button></th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
						</div>
						part 2
						<div class='panel col_border col_border_blue' id="medicalReconciliationModel">
							<div class='row'>
								<p style="margin-bottom: 0;"><b>Part 2:</b> Medications Prior to Hospitalization Needing Clarification</p>
								<table id="medicalReconciliationTable2">
								<thead>
									<tr>
										<th width="30%">Before Hospitalization Not Recommended by Hospital</th>
										<th width="50%" data-tooltip title='e.g. reason for the medication before hospitalization,  and reason it was stopped in the hospital, if known'>Comments</th>
										<th>Resolution</th>
										<th width='4%'><button id="medReconPart2Add" class='button small right' style='padding: 5px 15px; margin: 0;' name='Add' type="button">Add</button></th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
				
<!--                       _________PANEL-4 CLOSED__________                              -->

	</div>
<!--                       _________TABS-CONTENT OPEN__________                              -->

		<c:forEach items="${admissionForm.medicalReconciliationDto}" var="medRecon">
		<script>
		setMedicalReconciliationRecords(
						'${medRecon.medicalReconciliationId}',
						'${medRecon.resolutionId}',
						'${medRecon.recommendation}',
						'${medRecon.medicationDetails}',
						'${medRecon.clarificationNeeded}',
						'${medRecon.comments}'
				);
		</script>
		</c:forEach>
		</form:form>
	</div>
	<div class="row" style="position: relative; top: 15px;">
		<div class="large-12 columns">
			<input type="button" class="button small" value="Close" tabindex="3" onclick="functionClearAdmissionView(this)" id="cancelAdmission" />
			<input type="button" class="button small right"  value="Add Patient" tabindex="3" id="admission" />
		</div>
	</div>
</div>

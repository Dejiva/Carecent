<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="reveal-modal xlarge" id="optionsMenuModal" data-reveal>
    <input type="hidden" id="sbarPrvTabId"  />
	<div class='row optionsMenuTopBar'>
		<div class='large-10 small-10 columns padding-lr' style="left: 11px;">
		       <span id="SbarTitle"  style="color:sandybrown;font-size:25px;"></span>
		       <span style="color:cornsilk;">- Clinical Assessment</span>
        </div>
<!-- 		<div class='large-2 small-2 columns padding-lr'></div> -->
<!-- 		<div class='large-2 small-2 columns padding-lr'></div> -->
<!-- 		<div class='large-2 small-2 columns padding-lr ' style="text-align: right; padding-right: 10px;"></div> -->
	   <div style="margin-left:100px" class="large-1 small-1 columns padding-lr end right"> <a style="top:-8px;" class="sbar-close-reveal-modal">×</a></div>
	   </div>
		<dl class="tabs vertical" id="observation" data-tab>
		    <dd class="active" id='sbar_SNWSymptomsTab'><a href="#panel0">S&W</a></dd>
			<dd  id='sbar_SymtomsTab'><a href="#panel1">Signs & Symptoms</a></dd>
			<dd id="sbar_VitalSignsTab"><a href="#panel2">Vital Signs</a></dd>
			<dd id="sbar_LabWorksTab"><a href="#panel3">Tests/Results</a></dd>
			<dd id="sbar_CarePaths"><a href="#panel4">CarePaths</a></dd>
			<dd id="sbar_NurseNotesTab"><a href="#panel5">Nurse Notes</a></dd>
			<dd id="sbar_PractitionerTab"><a href="#panel6">Practitioner</a></dd>
			<dd id="sbar_BackgroundTab"><a href="#panel7">BackGround</a></dd>
     </dl>

	<div class="tabs-content vertical">
	      <div class="content active" id="panel0">
	         <div class="panel col_border_blue sbar_content ">
					<div  id="stopAndWatchSymptomsDataTable" > </div>
	         </div>
	      </div>
	    <div class="content active" id="panel1">
	    <div class="content">
	    <div class="panel col_border_blue sbar_content ">
	    
	       <c:choose>
		    <c:when  test='${ sessionScope.role== "LPN"|| sessionScope.role== "RN"}'>
<!--          	    <div class="panel col_border_blue sbar_content "> -->
				<div class="medium-12 columns">
		        <div class="medium-8 columns">
				<div>
					<div class='row' style="margin: 0;">
						<p class="s_header left">Symptoms</P>
						<input type="button" id="addSymptoms" value="Add Symptom"
							onclick="addSymptoms()"
							class='button small right'
							style='padding: 5px 15px; margin: 0;' />
					</div>
					<div class='row' style="margin: 0; max-height: 100%; overflow:auto; height:240px;">
					<dl class="accordion" id="symptomsDataTable" data-accordion></dl>
					</div>
				</div>
				</div>
				<div class="medium-4 columns">
				  <div  id="medicineviewOnlytxable" ></div>      
				</div>
				</div>
<!-- 			      </div> -->

          
		   </c:when>
		   <c:when  test='${sessionScope.role=="PA" ||  sessionScope.role== "MD" ||  sessionScope.role== "NP"}'>
		       <div class="medium-12 columns">
		        <div class="medium-8 columns">
				   <div>
						<div class='row' style="margin: 0;">
							<p class="s_header left">Symptoms</P>
						</div>
						<div class='row' style="margin:0;  overflow:auto; width:655px; height:250px;">
							<dl class="accordion" id="symptomsViewDataTable" data-accordion></dl>
						</div>
				   </div>
                </div>
		        <div class="medium-4 columns">
		        <div class="row">
  				  <input class="button small right" id="addMedicine" onclick="addPrescribe()" type="button" value="Add Medicine" style="padding: 5px 15px; margin: 0px 8px;">		        
				  <input class="button small right" id="addLabWorkForSymptom"  onclick="addLabWorkForSymptom()" type="button" value="Add Tests" style="padding: 5px 15px; color: margin: 0;">				       
				</div>
				  <div  style="overflow:auto; max-height:240px;"  id="medicineviewtable" style="margin-top:20%" ></div>     
                </div>	
		      </div>
		   </c:when>
		   <c:otherwise>		   
<!-- 		       <div class="panel col_border_blue sbar_content"> -->
				   <div>
						<div class='row' style="margin: 0;">
							<p class="s_header left">Symptoms</P>
						</div>
						<div class='row' style="margin: 0;  overflow:scroll; height:250px;">
							<dl class="accordion" id="symptomsViewDataTable" data-accordion></dl>
						</div>
				   </div>
<!-- 			     </div> -->              
		   </c:otherwise>
			</c:choose>
		
		</div>
		</div>
		  <div class="content">
		       <div  id='EvaluationMessagePanel1' class="EvaluationMessagePanel"></div>
		   </div>
	    </div>
	    <div class="content" id="panel2">
	                <div class="content">
	                  <div class="panel col_border_blue sbar_content vitalSignsDataTableOnPatient">
							<div>
								<div class='row' style="margin: 0;">
									<p class="s_header left">VITAL SIGNS</P>
									
								</div>
								<c:choose>
		                          <c:when  test='${ sessionScope.role== "LPN"|| sessionScope.role== "RN"}'>
								       <div  id="vitalSignsDataTableOnPatient" ></div>
								     
							      </c:when>
			                      <c:otherwise>
			                          <div  id="vitalSignsDataTableForView" ></div>
			                      </c:otherwise>
		                        </c:choose>		
							</div>
						</div>
						</div>
						<div class="content">
						  <div  id='EvaluationMessagePanel1' class="EvaluationMessagePanel"></div>
			    	    </div>
			 
	    </div>
	    <div class="content" id="panel3">
	          <div class="content">
	             	<div class="panel col_border_blue sbar_content">
							<div  style="max-height: 314px; overflow:auto;">
							<c:choose>
								<c:when test='${sessionScope.loggedUser.responsibilityName == "DOCTOR" || sessionScope.loggedUser.responsibilityName == "NURSE" }'>
							<input type="button" id="addlabWork" value="Add Tests"
										onclick="addLaborders()"
										class='button small right'
										style='padding: 5px 15px; margin: 0;' />
								</c:when>
							</c:choose>
							<div class='row' style="margin: 0;"> 
									<h3 class="s_header left">Tests/Results Entry</h3>
									<hr>
							</div>
								<div class='row' style="margin: 0;">
									<p class="s_header left">Waiting for approval</P>
								</div>
								<div class='row' style="margin: 0;">
									<dl class="accordion" id="labworkDataTable" data-accordion></dl>
								</div>
								<br>
								<p class="s_header left">Approved</P>
								<hr>
								<div class='row' style="margin: 0;">
									<dl class="accordion" id="labworkDocApprovedTable" data-accordion></dl>
								</div>
<!-- 								<p class="s_header left">Nurse Approved</P> -->
<!-- 								<hr> -->
<!-- 								<div class='row' style="margin: 0;"> -->
<!-- 									<dl class="accordion" id="labworkNurApprovedTable" data-accordion></dl> -->
<!-- 								</div> -->
								<p class="s_header left">Rejected</P>
								<hr>
								<div class='row' style="margin: 0;">
									<dl class="accordion" id="labworkRejectedTable" data-accordion></dl>
								</div>
							</div>
						</div>
						</div>
						<div class="content">
						<div  id='EvaluationMessagePanel1' class="EvaluationMessagePanel"></div>
						</div>
	    </div>
	    <div class="content" id="panel4">
	    <c:choose>
		  <c:when  test='${ sessionScope.role== "LPN"|| sessionScope.role== "RN"}'>
		  <div class="content">
	       <div class="panel col_border_blue sbar_content " id="dynamicCarePathsElementOnPatient"></div>
	       </div>
	       <div class="content">
	       <div  id='EvaluationMessagePanel1' class="EvaluationMessagePanel"></div>
	       </div>
	       </c:when>
	       <c:otherwise>
	       <div class="dynamicCarePathsElement" ></div>
	       </c:otherwise>
	       </c:choose>
	    </div>
	    <div class="content" id="panel5">
	       	<div class="panel col_border_blue ">
							<div>
								<div class='row' style="margin: 0;">
									<p class="s_header left">Nurse Notes</P>
									<c:choose>
										<c:when
											test='${ sessionScope.role== "LPN"|| sessionScope.role== "RN"}'>
											<%-- 								<c:when test='${ sessionScope.role== lpn_role|| sessionScope.role== rn_role}'> --%>
											<input type="button" id="addNurseNotes"
												value="Add Nurse Notes"
												onclick="addNurseNotes();"
												class='button small right'
												style='padding: 5px 15px; margin: 0;' />
										</c:when>
									</c:choose>
								</div>
								<div class='row' style="margin: 0;">
									<dl class="accordion" id="nurseNotesDataTable" data-accordion></dl>
								</div>
							</div>
						</div>
									<div class="panel col_border_blue" id='systemNotesPanel'>
							<div class="row custom_right_panel ">
								<div class='row' style="margin: 0;">
									<dl class="accordion" id="systemNotesDataTable" data-accordion></dl>
								</div>
							</div>
						</div>
						<div  id='EvaluationMessagePanel1' class="EvaluationMessagePanel"></div>
						
			
	    </div>
	    <div class="content" id="panel6">
	       	<div class="panel col_border_blue ">
							<div>
								<div class='row' style="margin: 0;">
									<p class="s_header left">Practitioner Notes</P>
									<c:choose>
										<c:when
											test='${ sessionScope.role== "MD"|| sessionScope.role== "PA"||sessionScope.role== "NP"}'>
											<input type="button" id="addPractitionerNotes"
												value="Add Notes"
												onclick="addPractitionerNotes()"
												class='button small right'
												style='padding: 5px 15px; margin: 0;' />
										</c:when>
									</c:choose>
								</div>
								<div class='row' style="margin: 0;">
									<dl class="accordion" id="PractitionerNotesDataTable"
										data-accordion></dl>
								</div>
							</div>
						</div>
						<br>
					<c:choose>
					 <c:when test='${ sessionScope.role== "MD"|| sessionScope.role== "PA"||sessionScope.role== "NP"||sessionScope.role== "LPN"||sessionScope.role== "RN"}'>
						<div class="row">
							<div class='large-2 columns'>
								<input type="radio" class="inline-label" data-radio='pationMove'
									id="manageInFacilityFlag" name="manageInFacilityFlag"  onclick="manageInFacilityConfirm(this)"/> <label
									for="manageInFacilityFlag"> Manage In Facility</label>
							</div>
							<!-- <div class='large-10 columns'>
								<input type="radio" class="inline-label" data-radio='pationMove'
									id="transferToHospitalFlag" name="transferToHospitalFlag"  onclick="transferToHospitalConfirm(this)"/> <label
									for="transferToTheHospistal"> Transfer to the hospital</label>
							</div> -->
						</div>
					 </c:when>
					</c:choose>
	    </div>
	    <div class="content" id="panel7">
	    <div style="overflow:auto; max-height:263px; max-width:63em;">
	       <form:form commandName="sbarForm" method="GET" action="${pageContext.request.contextPath}/sbar/submit" id="SBARModalForm">
		       <input type="hidden" id="sbarPatientEpisodeId" name="sbarPatientEpisodeId" />
		       
	           <div class="panel col_border_blue ">
							<p class="s_header">BACKGROUND</P>
							<label><b>Resident Description</b></label>
							<div class='row'>
								<div class='large-6 column padding-lr'>
									<label for="primaryDiagnoses">Primary diagnoses:</label> <input
										type="text" id="primaryDiagnoses" name="primaryDiagnoses" />
								</div>
								<div class='large-6 column padding-lr end'>
									<label for="otherPertinentHistory">Other pertinent
										history (e.g. medical diagnosis of CHF, DM, COPD):</label> <input
										type="text" id="otherPertinentHistory"
										name="otherPertinentHistory" />
								</div>
							</div>
							<label><b>Medication Alerts</b></label>
							<div class='row'>
								<div class='large-6 column padding-lr'>
                                    <input type="checkbox" class="inline-label"
										id="medAlertChangeLastWeek" name="medAlertChangeLastWeek" />
									<label for="changeInLastWeek">Changes in the last week
										(describe below):</label>
								</div>
								<div class='large-6 column padding-lr end'>
                                    <input type="checkbox"
										id="medAlertInWarfarin" name="medAlertInWarfarin"> <label
										for="warfarinOrCoumadin">Resident is on
										warfarin/coumadin:</label>
								</div>
							</div>
							<div class='row'>
								<div class='large-2 column padding-lr'>
									<label for="resultOfLastINR">Result of last INR:</label> <input
										type="text" id="resultOfLastInr" name="resultOfLastInr" />
								</div>
								<div class='large-2 column padding-lr'>
									<label for="latestInrDate">Date:</label> <input type="text"
										name="latestInrDate" id="latestInrDate" class="datepicker" />
								</div>
								<div class='large-8 column padding-lr'>
									<label for="sbarAllergies">Allergies:</label> <input
										type="text" id="allergies" name="allergies" />
								</div>
							</div>
							<div class='row'>
								<label><b>Oximetry %</b></label>
								<div class='large-6 column padding-lr'>
									<input type="radio"
										data-radio="oximetryOnRoomairRadio" id="oximetryOnRoomair"
										name="oximetryOnRoomair" /> <label for="oximetryOnRoomAir">on
										room air :</label>
								</div>
								<div class='large-6 column padding-lr'>
									<input type="radio"
										data-radio="oximetryOnRoomairRadio" id="oximetryonO2Check"
										name="oximetryonO2Check"> <label
										for="oximetryonO2Check">on O2 ( liters/minute ) :</label> <input
										type="text" id="oximetryOnO2" name="oximetryOnO2"/>
								</div>
							</div>
						

							<div class="content custom_right_panel">
								<p class="s_header">Advance Care Planning Information (the
									resident has orders for the following advance directives)</p>
								<div class='row'>
									<div class='large-2 column'>
										<input type="checkbox"
											id="advCarePlanDNRFlag" name="advCarePlanDNRFlag" /> <label
											for="dnr">DNR</label>
									</div>
									<div class='large-3 column'>
										<input type="checkbox"
											id="advCarePlanDNIFlag" name="advCarePlanDNIFlag" /> <label
											for="dni"> DNI (Do Not Intubate) </label>
									</div>
									<div class='large-3 column'>
										<input type="checkbox"
											id="advCarePlanDNHflag" name="advCarePlanDNHflag" /> <label
											for="dnh">DNH (Do Not Hospitalize) </label>
									</div>
									<div class='large-3 column'>
										<input type="checkbox"
											id="advCarePlanNoEtrnalFeedFlag"
											name="advCarePlanNoEtrnalFeedFlag" /> <label
											for="noExternalFeeding">No Enteral Feeding</label>
									</div>
								</div>
								<div class='row'>
								 <div class='large-4 column'>
									<input type="checkbox"
										id="otherOrderOrWillcheckBox" name="otherOrderOrWillcheckBox" />
									<label for="otherOrder">Other Order or Living Will
										(specify) 
										</label> 
								</div>
								<div class='large-4 column end'>
								  <input type="text" id="otherOrderOrWill"
										name="otherOrderOrWill"/>
								</div>
								</div>
							</div>
							<div class="row">
								<div class='large-8 column'>
									<label for="otherOrderResident">Other Resident or
										Family preferences for care <input type="text"
										id="otherResidentCareId" name="otherResidentCare"/>
									</label>
								</div>
							</div>
							<br>
							 <div class='row'>	
					   			 <div class='large-4 column'>
					    				<label for="">Name(Family/Health Care Agent)</label> <input type="text" name="gaurdianName" id=gaurdianName />
					             </div>
					             <div class='large-4 column reportedToFamily end'>
						             <label>Date</label> 
						          	<input id="residentRepotedDateId" class="residentDateClass" type="text" onchange="return validateDate(this)" name="repotedToFamily">
					            </div>
					
				             </div>
						</div>
						     <div class="panel col_border_blue ">
							<div>
								<div class='row'>
									<p class="s_header left">For the next 5 items, complete
										only those relevant to the change in condition. If the item is
										not relevant, check "N/A" for not applicable.</P>
									
								</div>
								<div class='row'>
									<dl class="accordion" id="sbarReleventSymptoms" data-accordion></dl>
								</div>
							</div>
			      </div>
						
			         
						
               </form:form> 
                
               </div>   
               <div style="position: relative; " class="row ">
		                 <div class="small-12 columns right">
					           <input type="submit" tabindex="2" value="Save" class="button small right" id="sbarSub" onclick="sbarSave()">
		                </div>
                </div>
	    </div>
	    <div class="content" id="panel8">
	           
	    </div>
	    <div class="content" id="panel9">
	        <p>This is the fourth panel of the basic tab example. This is the fourth panel of the basic tab example.</p>
	    </div>
	</div>
	
</div>
<div id="SBARIntialModal" class="reveal-modal large raleway"
	style="top: 20%; margin-left: -40%; width: 50%;"
	data-reveal>
	<p  class="h_header" style="text-align: center;">
		Do you want to observe the patient</p>
		
		<a class="close-reveal-modal">&#215;</a>
		<div class="row" style="position: relative; top: 15px;">
			<div class="row">
				
				<div class="small-1 columns right ">
					<input type="button" id="intialSbar_Yes" class="button small " value="Yes"
						tabindex="3">
				</div>
				<div class="small-1 columns ">
					<input type="button" id="intialSbar_No" class="button small "
						value="No" tabindex="3" />
				</div>

			</div>
	   </div>
		
</div>
<div id="notifyDoctorModel" style="display: none;">
	<div id="notifyContent" ></div>
	
</div>
<form:form action="" commandName="">
	<div id="conditionPopup"></div>
	<div id="conditionPopup1"></div>
</form:form>
<div id="SNWSymptomDataTable" style=" display:none"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/carepath.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/vendor/jquery.tooltip.js"></script>
<script type="text/javascript"> 
$('.fi-first-aid').tooltip({ 
	tooltipSourceID:'#SNWSymptomDataTable', 
	loader:1, 
	loaderHeight : 16,
	loaderWidth : 17, 
	positionLeft: -100,
	width : '500px',
	height : '200px',
	tooltipSource : 'inline',
	borderSize : '0',
	tooltipBGColor : '#fff'
});
</script>


<%@ page import="com.codecoop.interact.core.util.Constants"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page session="true"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>CARECENT</title>
<link rel="icon" href="img/favicon.png" type="image/x-icon">

<link rel="stylesheet" href="https://cdn.datatables.net/1.10.4/css/jquery.dataTables.css">
<script src="https://jquery-datatables-column-filter.googlecode.com/svn/trunk/media/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="https://jquery-datatables-column-filter.googlecode.com/svn/trunk/media/js/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dataTable/jquery.dataTables.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/foundation.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/device.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font/foundation-icons.css" />
<link type="stylesheet" href="${pageContext.request.contextPath}/css/jqplot/jquery.jqplot.min.css" />

<!-- <link rel='stylesheet' type='text/css' href='https://cdnjs.cloudflare.com/ajax/libs/jquery-impromptu/5.2.3/jquery-impromptu.min.css' /> -->
<link rel="stylesheet" href="https://datatables.github.com/Plugins/integration/foundation/dataTables.foundation.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/responsive/1.0.1/css/dataTables.responsive.css">
<link href="https://fonts.googleapis.com/css?family=Raleway:100,200,300,400,500,600,700,800,900"
	rel="stylesheet" type="text/css">
<!-- 	<script src="${pageContext.request.contextPath}/js/vendor/jquery.bpopup.min.js"></script> -->

<script src="${pageContext.request.contextPath}/js/vendor/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/foundation/foundation.js"></script>
<script src="${pageContext.request.contextPath}/js/foundation/foundation.orbit.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/modernizr.js"></script>
<!-- <script src="${pageContext.request.contextPath}/js/vendor/jquery.validate.min.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-impromptu/5.2.3/jquery-impromptu.min.js"></script> -->
<script>
	var firstTimeUserFlag = '${firstTimeUser}';
</script>

<script>
    var contextPath='<%=request.getContextPath()%>';
	var statusFlag = '${param.status}';
	var status = '${status}';
	var propStatus = '${propStatus}';
	var errorIn = '${errorPage}';
	var acuteCareStatus='${acuteCareStatus}';
	var admissionStatus = '${admissionStatus}';
	var ssnCheck = '${param.ssnCheck}';
	var acuteCareObj = '${openAcuteCare}';
	var constants_reportImmidate = '<%=Constants.CIC_NOTIFY_IMMEDIATE%>';
	var constants_reportNextDay = '<%=Constants.CIC_NOTIFY_NEXT_DAY%>';
	var np_role='<%=Constants.NP_ROLE%>';
	var lpn_role='<%=Constants.LPN_ROLE%>';
	var pa_role='<%=Constants.PA_ROLE%>';
	var md_role='<%=Constants.MD_ROLE%>';
	var cna_role='<%=Constants.CNA_ROLE%>';
	var rn_role='<%=Constants.RN_ROLE%>';
	var snwsuccess='${param.snwsuccess}';
    if(snwsuccess==null){
    	 snwsuccess="";
     }
	var patientId = '${patientId}';
	var patientIdndEId = '${patientIdndEId}';
	var showFacilitySelect ='${showFacilitySelect}';
	var fromPage = '${param.fromPage}';
	var firstTimeUserFlag = '${firstTimeUser}';
	var formFlag = '${formName}';
	var fromPage = '${param.fromPage}';
	var isRoleAdmin ='${param.isRoleAdmin}';
	var isRoleSuperAdmin = '${param.isRoleSuperAdmin}';
	var	admissionOperation = '${param.admissionOperation}';
    var staffId='${sessionScope.staffId}';
	var medReconcilationList = '${medReconcilation}';
	var path = '<%=request.getContextPath()%>';
	var newAdmissionForm = '${param.newAmission}';
	var staffRole='${sessionScope.role}';
	var staffName='${sessionScope.staffName}';
    var staffDes=staffRole.split('_')[1];
	var currentPage='${sessionScope.currentPage}';
	var previousPage='${sessionScope.currentPage-1}';
	var responsibility = '${sessionScope.loggedUser.responsibilityName}';
	var currentFacility = '${sessionScope.loggedUser.facilityName}';
	var currentFacilityCont = '${sessionScope.loggedUser.fcontact}';
	var nextPage='${sessionScope.currentPage+1}';
	var showPages='${sessionScope.showPages}';
	var isAllPatientsSearchFlag='${sessionScope.isAllPatientsSearch}';
	var specificPatientsFlag='${sessionScope.specificPatientsFlag}';
	var admissionSubj='<%=Constants.ADMISSION_SUBJECT%>';
	var snwSubj='<%=Constants.SNW_SUBJECT%>';
	var observation_Subj='<%=Constants.OBSERVATION_SUBJECT%>';
	var cpSubj='<%=Constants.CP_SUBJECT%>';
	var acuteCareSubj='<%=Constants.ACUTECARE_SUBJECT%>';	
	var mang_FacilitySubj='<%=Constants.MANG_FACILITY_SUBJECT%>';
	var patient_Recoverd='<%=Constants.PATIENT_RECOVERD_SUBJECT%>';
	var  backin_facility_subj='<%=Constants.BACKIN_FACILITY_SUBJ%>';
	var transfer_hospital_subj='<%=Constants.TRANSFER_HOSPITAL_SUBJ%>';
	var immediate='<%=Constants.CIC_NOTIFY_IMMEDIATE%>';
	var nonimmediate='<%=Constants.CIC_NOTIFY_NEXT_DAY%>';
	var message='<%=Constants.MESSAGE%>';
	var alert='<%=Constants.ALERT%>';
	
</script>
</head>

<jsp:include page="/WEB-INF/fragments/admission1.jsp"/>
<jsp:include page="/WEB-INF/fragments/admission.jsp"/>
<jsp:include page="/WEB-INF/fragments/acuteCare.jsp"/>
<jsp:include page="/WEB-INF/fragments/stopAndWatch.jsp"/>
<%-- <jsp:include page="/WEB-INF/fragments/sbar.jsp"/> --%>
<body>
	<div class="row">
	 <div class="large-12 columns padding-lr">
		<div class="app_logo large-2 columns">
			<img src="${pageContext.request.contextPath}/img/mail_logo.jpg" title="Carecent" />
		</div>
		<div class="large-5 columns user_info">
          <nav class="top-bar" data-topbar data-options="is_hover:true">
<!-- 				<ul class="title-area"> -->
<!-- 					<li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li> -->
<!-- 				</ul> -->
				<section class="top-bar-section">
					<div id="ShowFacilitySelectModal_topSection"
						class="small-6 columns user_info">
						<form:form method="get" commandName="dashboardForm"
							action="${pageContext.request.contextPath}/">
							<div class="small-10 columns user_info">
								<form:select path="facilityId" id="sfacilityId">
									<sec:authorize access="!hasRole('ROLE_SUPER_ADMIN')"> 
									<form:options items="${sessionScope.loggedInUserFacilityMap}" />
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_SUPER_ADMIN')"> 
									<form:options items="${sessionScope.allFacilityMap}" />
									</sec:authorize>
								</form:select>
							</div>
						</form:form>
					</div>
					<ul class="right">
						<li class="has-dropdown"><a href="#" id="li_color_change">
								<i class="fi-torso-business user_icon"></i> <c:if
									test="${pageContext.request.userPrincipal.name != null}">
								${pageContext.request.userPrincipal.name == null? "Hello" : pageContext.request.userPrincipal.name}(${sessionScope.role})
							</c:if>
						</a>
							<ul class="dropdown right">
								<sec:authorize access="hasRole('ROLE_SUPER_ADMIN')"> 
								<li id='li_hover'>
									<a href="#" data-reveal-ajax="true" id="createFacility" >Create Facility</a>
								</li>
							</sec:authorize>
								<li id='li_hover'><a href="#" id="passchangelink">Change
										Password</a></li>
								<c:choose>
								<c:when test="${sessionScope.loggedUser.roleValue == 'ROLE_ADMIN'  ||  sessionScope.loggedUser.roleValue == 'ROLE_SUPER_ADMIN'}">
								<li id='li_hover'><a href="#" id="userSettrings"
										onclick="loadManageUsersDialog('');">Manage Users</a></li>
								<li id='li_hover'><a href="#" id="manageAnnouncement">Manage Announcement</a></li>
								<li id='li_hover'><a href="${pageContext.request.contextPath}/qiGraphs/qiPage" id="qiGraphs">Quality Improvement</a></li>
								</c:when>
								<c:otherwise>
								<li id='li_hover'><a href="#" id="userSettrings"
										onclick="editMyProfile();">My Profile</a></li>
								</c:otherwise>
								</c:choose>
								<li id='li_hover'><a
									href="<%=request.getContextPath()%>/j_spring_security_logout">Logout</a>
								</li>
							</ul></li>
					</ul>
				</section>
			</nav>
		</div>
		</div>
	</div>
	<div class="row">
	 <div class="large-9 columns top_info" >
           	<c:choose>
			   <c:when test="${sessionScope.role!='MD' && sessionScope.role!='PA' && sessionScope.role!='NP'}">    
           	       <div class="small-3 columns top_info  padding-lr" id="addnewpatientbtn">
					<span id="AddNewPatient" click-attr="0" class="add_patient button tiny">Add New Resident</span>
				  </div>
			   </c:when>
			</c:choose>
			<div class="small-5 columns top_info right">
	    	<c:choose>
<%-- 	    	 <c:when test="${sessionScope.role!='ROLE_ADMIN' && sessionScope.role!='ROLE_CNA' &&(sessionScope.isAllPatientsSearch)}"> --%>
				 <c:when test="${sessionScope.role!='ADMIN' && sessionScope.role!='CNA'&& sessionScope.role!='RN'&& sessionScope.role!='LPN' }">
			
				<div id="myPatientsDiv" class="small-4 columns top_info right  padding-lr">
					<span class="assigned_patients button  right">My Residents</span>
				</div>
		   	</c:when>
		  </c:choose>
		  <c:choose>
<%-- 	    	 <c:when test="${sessionScope.role!='ROLE_MD' && sessionScope.role!='ROLE_PA'&&(!sessionScope.isAllPatientsSearch||sessionScope.specificPatientsFlag)}"> --%>
			<c:when test="${sessionScope.role!='MD' && sessionScope.role!='PA'&& sessionScope.role!='NP'}">
				
		     <div id="AllPatientsDiv" class="small-4 columns top_info right  padding-lr">
					<span class="all_patients button tiny right" id="allpatientsspan">All Residents</span>
					
		    </div>
		 </c:when>
		  </c:choose>
		 </div>
		</div>
		
		<div class="large-3 columns top_info" >
					<!-- 	<input type="text" id="searchId" class="input_round" placeholder="Search" /> --> 
			<div>
				<div class="small-12 column">
					<input type="text" placeholder="Search The Patient"  style="border-radius: 5px;" id='patientSearch'>
					<a class="alert button expand search-x" href="#" onclick="functionHideAllPatients(this)" id='hideAllPatients'>x</a>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="large-9 app_panel columns">
			<div class="panel">
				<div class="large-3 columns">
					<div class="panel col_even">
						<div class="col_head col_blue0">
							<span class="data_vertical">...</span> <span
								class="data_col_head">Residents</span><span class="patient_group"> </span> <span
								class="data_number">${sessionScope.patientsInCurrentFacility.admissionQueueSize}</span>
						</div>
						<div class="col_body">
							<ul id="one">
								<c:forEach items="${sessionScope.patientsInCurrentFacility.patientDetailsModelMap}"
									var="patient">
									  <c:choose>
									 	<c:when test="${fn:length(patient.value.fullName)>10}">
									    <c:set var="patientName" value="${fn:substring(patient.value.fullName, 0, 10)}..." /></c:when>
									    <c:otherwise>
									      <c:set var="patientName" value="${patient.value.fullName}"/>
									    </c:otherwise>
								     </c:choose> 
								     <c:choose>
								     	<c:when test="${patient.value.stopAndWatchEligible}">
								     		<c:choose>
								     			<c:when test="${patient.value.stayedInHosp}">
								     				<li class='bottom_border_yellow'  data-target='one' data-patient='${patient.key}=${patient.value.fullName}'>
								     			</c:when>
								     			<c:otherwise>
								     				<li class='${patient.value.observationQueue ? "bottom_border_green sbarRised" :"bottom_border_green sbarNotRised" } ${patient.value.acutecareQueue ? "acuteCareRaised":"acuteNotCareRaised" }' data-target="one"  data-patient='${patient.key}=${patient.value.fullName}'>
								     			</c:otherwise>
								     		</c:choose>
							     	</c:when>
								     	<c:otherwise>
								     	${patient.value.stayedInHosp ? "<li class='bottom_border_yellow'  data-target='one' data-patient='${patient.key}=${patient.value.fullName}'>" :"<li class='bottom_border_red'  data-target='one' data-patient='${patient.key}=${patient.value.fullName}'>"}
								     	</c:otherwise>
								     </c:choose>
								   <span  class="fi-name  " title='${patient.value.fullName}' data-tooltip>${patientName}</span>
												<c:choose>
												<c:when test="${sessionScope.loggedUser.roleValue == 'ROLE_NP' || sessionScope.loggedUser.roleValue == 'ROLE_MD' || sessionScope.loggedUser.roleValue == 'ROLE_PA'}">
												<span id='admissionEyeSpan' onclick="functionAdmissionEyeSpan(this)"
															class='${patient.value.stayedInHosp ? "":"fi-eye tip-bottom noradius"}'
															patient-id='${patient.key}' patient-episodeId='${patient.value.patientEpisodeId}' patient-name='${patientName}' title="View" data-tooltip></span>
												</c:when>
												<c:otherwise>
												<span id='admissionPencilSpan' onclick="functionAdmissionPencilSpan(this)"
															class='${patient.value.stayedInHosp ?"":"fi-pencil tip-bottom noradius"}'
															patient-id='${patient.key}' patient-episodeId='${patient.value.patientEpisodeId}' patient-name='${patientName}' title="Edit" data-tooltip></span>
												</c:otherwise>
												</c:choose>
												<span id="admissionPdf" class="fi-page-export-pdf has-tip tip-bottom noradius" title="PDF Export" data-tooltip data-patient='${patient.key}'></span>
							   
								 </li>
								</c:forEach>
								
							</ul>
							<div class="break"></div>
						</div>
					</div>
				</div>
				<div class="large-3 columns">
					<div class="panel col_odd">
						<div class="col_head col_blue1">
							<span class="data_vertical">...</span> <span
								class="data_col_head">Stop &amp; Watch</span><span class="snw_queue" >
								</span><span class="data_number">${sessionScope.patientsInCurrentFacility.stopNWatchQueueSize}</span>
						</div>
						<div class="col_body">
							<ul id="two">
								<c:forEach items="${sessionScope.patientsInCurrentFacility.patientDetailsModelMap}"
									var="stopAndWatchPatients">
									<c:choose>
									<c:when test="${stopAndWatchPatients.value.stopAndWathQueue}">
                                  <c:choose>
									 <c:when test="${fn:length(stopAndWatchPatients.value.fullName)>10}">
									   <c:set var="snwpatientName" value="${fn:substring(stopAndWatchPatients.value.fullName, 0, 10)}..." /></c:when>
									   <c:otherwise>
									     <c:set var="snwpatientName" value="${stopAndWatchPatients.value.fullName}"/>
									   </c:otherwise>
									   </c:choose>
									   			<li class='${stopAndWatchPatients.value.hasResponse?  "bottom_border_green" :"bottom_border_red" }'   id="stopAndWatchPatient"
													data-target="two" data-patient='${stopAndWatchPatients.key}=${stopAndWatchPatients.value.fullName}' data-patient-name='${stopAndWatchPatients.value.fullName}'><span  class="fi-name" title='${stopAndWatchPatients.value.fullName}' data-tooltip>${snwpatientName}</span>
													<span class="li_doc"></span> <!-- <span class="li_time">35m</span> -->
													<span class="fi-eye has-tip tip-bottom noradius" id="stop-watch" data-tooltip title="View" onclick="stopAndWatchViewHistory(this)"></span> 
														<span id="snwhistorycount${stopAndWatchPatients.key}" class="stop-and-watch-count" data-tooltip title="Count">${stopAndWatchPatients.value.stpwCount}</span>
													
                                     	<span class="fi-plus has-tip tip-bottom noradius"
										id="stop-watch-add" onclick="addStopAndWactch(this)" title="Add" data-tooltip ></span>
                                     </li>
                                     </c:when>
                                    </c:choose>
								</c:forEach>
							</ul>
							<div class="break"></div>
						</div>
					</div>
				</div>
				<div class="large-3 columns">
					<div class="panel col_even">
						<div class="col_head col_blue2">
							<span class="data_vertical">...</span> <span
								class="data_col_head">Clinical Assessment</span> <span class="patient_group"></span> <span class="data_number">${sessionScope.patientsInCurrentFacility.observationQueueSize}</span>
						</div>
						<div class="col_body">
							<ul id="three">
								<c:forEach items="${sessionScope.patientsInCurrentFacility.patientDetailsModelMap}" var="sbarPatients">
									<c:choose>
									<c:when test="${sbarPatients.value.observationQueue}">
									 <c:choose>
									   <c:when test="${fn:length(sbarPatients.value.fullName)>10}">
									   	    <c:set var="sPatients" value="${fn:substring(sbarPatients.value.fullName, 0, 10)}..." />
									   </c:when>
									   <c:otherwise>
									       <c:set var="sPatients" value="${sbarPatients.value.fullName}"/>
									   </c:otherwise>
									   </c:choose>
									<li class="bottom_border_green" data-target="three" data-patient='${sbarPatients.key}' data-episodeId='${sbarPatients.value.patientEpisodeId}'  data-patient-name='${sbarPatients.value.fullName}'  patient-episodeId='${sbarPatients.value.patientEpisodeId}'>
									<span class="fi-name" title='${sbarPatients.value.fullName}' data-tooltip>${sPatients}</span>
<%-- 									<span class="fi-page-export-pdf has-tip tip-bottom noradius" title="PDF Export" data-tooltip data-patient='${sbarPatients}'></span> --%>
									<span class="fi-list has-tip tip-bottom noradius" title="Menu" data-tooltip data-patient='${sbarPatients.key}'
										  data-dropdown="hover1" data-options="align:left; is_hover:false;"></span>
								    <ul id="hover1" class="f-dropdown menu-items-list" data-dropdown-content>
								        <li><a href="#" tab_id="sbar_SNWSymptomsTab">S&W</a></li>
									    <li><a href="#" tab_id="sbar_SymtomsTab">Signs & Symptoms</a></li>
									   	<li><a href="#" tab_id="sbar_VitalSignsTab">Vital Signs</a></li>
									   	<li><a href="#" tab_id="sbar_LabWorksTab">Tests/Results</a></li>
									   	<li><a href="#" tab_id="sbar_CarePaths">CarePaths</a></li>
									   	<li><a href="#" tab_id="sbar_NurseNotesTab">Nurse Notes</a></li>
									   	<li><a href="#" tab_id="sbar_PractitionerTab">Practitioner</a></li>
									   	<li><a href="#" tab_id="sbar_BackgroundTab">Background</a></li>
									</ul>
									<c:choose>
                                       <c:when test='${sessionScope.sbarPatientStatusMap.get(sbarPatients.key) == "NONE" }'>			
										    <span class="li_doc"></span> <span id='sbarPatientStatus${sbarPatients.key}' class="immediateSbar"></span>
									  </c:when>
									  <c:otherwise>
							             <c:set var="CIC_NOTIFY_IMMEDIATE" value="<%=Constants.CIC_NOTIFY_IMMEDIATE%>" />
								         <c:choose>
	                                         <c:when test="${sessionScope.sbarPatientStatusMap.get(sbarPatients.key)==CIC_NOTIFY_IMMEDIATE}">			
   									            <span class="li_doc"></span> <span id='sbarPatientStatus${sbarPatients.key}' class="immediateSbar">Immediate</span>
										     </c:when>
										     <c:otherwise>
										         <span class="li_doc"></span> <span id='sbarPatientStatus${sbarPatients.key}' class="no immediateSbar">Non Immediate</span>
										    </c:otherwise>
									     </c:choose>
									</c:otherwise>
								</c:choose>
								<span class="fi-page-export-pdf has-tip tip-bottom noradius" title="PDF Export" data-tooltip data-patient='${sbarPatients.key}'></span>
							     </li>
							     </c:when>
									</c:choose>
								</c:forEach>
							</ul>
							<div class="break"></div>
						</div>
					</div>
				</div>
				<div class="large-3 columns">
					<div class="panel col_odd">
						<div class="col_head col_brown">
							<span class="data_vertical">...</span> <span
								class="data_col_head">Acute Care Transfer</span><span class="patient_group"></span>  <span
								class="data_number" id="openAcuteCare">${sessionScope.patientsInCurrentFacility.acuteCareQueueSize}</span>
						</div>
						<div class="col_body">
						<ul id="four">
						<c:forEach items="${sessionScope.patientsInCurrentFacility.patientDetailsModelMap}" var="patient">
						<c:choose>
							<c:when test="${patient.value.acutecareQueue}">
							<c:choose>
									 	<c:when test="${fn:length(patient.value.fullName)>10}">
									    <c:set var="patientName" value="${fn:substring(patient.value.fullName, 0, 10)}..." /></c:when>
									    <c:otherwise>
									      <c:set var="patientName" value="${patient.value.fullName}"/>
									    </c:otherwise>
					       </c:choose>
						<li class='${patient.value.stayedInHosp ? "bottom_border_yellow" :"bottom_border_green"}'  data-target="four" data-patient="${patient.key}"><span class="li_doc"></span>
						<span  class="fi-name" title='${patient.value.fullName}' data-tooltip>${patientName}</span> 
						<span id='acuteCarePencilSpan' onclick="functionAcuteCarePencilSpan(this)" class="fi-pencil tip-bottom noradius" ac-patient-id='${patient.key}'  patient-name ="${patient.value.fullName}" title="Edit" data-tooltip></span>
						</li>
							</c:when>
						</c:choose>
						</c:forEach>
							</ul>
							<div class="break"></div>
						</div>
					</div>
				</div>
			</div>
		 <table class="mail_table" style="margin: 0 !important;">
			<thead>
				<tr style="border-left: 8px solid rgb(171, 170, 135);">
					<th><input type="checkbox" name='checkAll' style="top: 0px !important; left: -3px !important;" id='checkAll'/></th>
					<th>
				        <div class='left left-message padding-lr'>
				            <b style='color:"black"' class="padding-lr">Messages</b>
				        </div>
			        </th>
			        <th colspan="3">
						<input id="msgesSearchId"  type="text" name='Search' placeholder="Search" style="float: right; width: 250px;"/>
						<div id="paginationId" class="right"></div>
					</th>
			        <th>
						<span  id="pageRefreshId" class='fi-refresh' style="float: left;" onclick="pageRefresh()"></span>
						<span  id='deleteTrashId' class='fi-trash right' onclick="deleteSelectedMessages()"></span>
					</th>
				</tr>
			</thead>
			<tbody id="messagesId" class="mail-table-body"></tbody>
		</table> 
	</div> 
		<div class="large-3 columns">
			<div class="panel col_last">
				<section style="background:white;">
					<div class="col_head col_violet1 col_last_head">
						<span class="data_col_head alert-header">Alerts</span>
					</div>
					<div  id="alertsId" class="alertsClass padding-lr">
					
				   </div> 
				</section>
				<section style="background:white; margin-top:5px;">
					<div class="col_head col_violet2 col_last_head">
						<span class="data_col_head alert-header">Anouncements</span>
						<span class="fi-refresh right re-ann" style="font-size:15px;margin-right:10px;"></span>
						</div>
						<div  class="anouncements"></div>
				</section>
			</div>
		</div>
	</div>
	<!-- Change Password Code Block -->
	<div id="ChangePasswordModal" class="reveal-modal medium raleway"
		data-reveal>
		<p class="lead" style="text-align: center;">New Password Confirmation</p>
		<div id="ChangePasswordModalMessage"></div>
		<form action='${pageContext.request.contextPath}/resetPassword' method='post' data-abide
			id='ChangePasswordForm'>
			<div class="password-field">
				<label>Current password: <input type="password" name="currentPassword" id="currentPassword">
				</label>
			</div>
			<div class="password-field">
				<label>New password: <input type="password" name="password"
					id="newpassword"  required pattern="passWordPattern">
				</label> <small class="error">password should contain at least 8 chars, two digits, one capital</small>
			</div>
			<div class="password-confirmation-field">
				<label>Confirm Password: <input type="password"
					name="confirmPassword" id="confirmPassword" 
					data-equalto="newpassword" required>
				</label> <small class="error">The password did not match</small>
			</div>
			<div class="row">
				<div class="small-1 columns">
					<input type="submit" class="button small" value="Submit"
						tabindex="3" />
				</div>
				<div class="small-3 columns">
					<input type="button" class="button small" value="Close"
						tabindex="3" id="passCancel" />
				</div>
			</div>
		</form>
	</div>
	<!-- Show facility select Code Block -->
	<div id="ShowFacilitySelectModal" class="reveal-modal tiny raleway">
		<p class="lead" style="text-align: center;">Select A Facility</p>
		<form:form method="get" commandName="dashboardForm"
			action="${pageContext.request.contextPath}/">
			<div class="row">
				<div style="text-align: center;">
					<form:select path="facilityId" >
						<form:options items="${sessionScope.loggedInUserFacilityMap}" />
					</form:select>
				</div>
			</div>
			<div class="row">
				<div style="text-align: center;">
					<input type="submit" class="button small" value="Submit"
						tabindex="3" />
				</div>
			</div>
		</form:form>
	</div>
	<!-- Registration Code Block -->
	<div id="RegistrationModal" class="reveal-modal large raleway"
		data-reveal>
		<p class="lead" style="text-align: center;" id="careCentStaffReg">Staff
			Registration Form</p>
		<c:choose>
		<c:when test="${sessionScope.loggedUser.roleValue == 'ROLE_ADMIN' || sessionScope.loggedUser.roleValue == 'ROLE_SUPER_ADMIN'}">
		<a class="close-reveal-modal" onclick="loadManageUsersDialog('');">&#215;</a>
		</c:when>
		</c:choose>
		<c:choose>
		<c:when test="${sessionScope.loggedUser.roleValue != 'ROLE_ADMIN'}">
		<a class="close-reveal-modal">&#215;</a>
		</c:when>
		</c:choose>
		<form:form method="post" commandName="staffRegistrationForm"
			action="${pageContext.request.contextPath}/mangeUser/registration">
			<div id="registrationErrors">
				<form:errors path="*" cssClass="error"></form:errors>
			</div>
			<div class="row">
				<div class="large-6 columns move_form_left">
					<div class="row">
						<div class="small-5 columns">
							<label for="firstName" class="right inline"><i
								class="fi-star small"></i> First Name:</label>
						</div>
						<div class="small-7 columns">
							<form:input path="firstName" cssClass="alphaonly"/>
							<%-- 							<small class="small-7 columns"><form:errors path="firstName" cssClass="error"></form:errors></small> --%>
						</div>
					</div>
					<div class="row">
						<div class="small-5 columns">
							<label for="middleName" class="right inline">Middle Name:</label>
						</div>
						<div class="small-7 columns">
							<form:input path="middleName" cssClass="alphaonly" />
						</div>
					</div>
					<div class="row">
						<div class="small-5 columns">
							<label for="lastName" class="right inline"><i
								class="fi-star small"></i> Last Name:</label>
						</div>
						<div class="small-7 columns">
							<form:input path="lastName" cssClass="alphaonly"/>
						</div>
					</div>
					<div class="row">
						<div class="small-5 columns">
							<label for="userName" class="right inline">User Name:</label>
						</div>
						<div class="small-7 columns">
							<form:input path="userName" readonly="true" />
						</div>
					</div>
					<div class="row">
						<div class="small-5 columns">
							<label for="gender" class="right inline"><i class="fi-star small"></i> Gender:</label>
						</div>
						<div class="small-7 columns">
							<form:select path="gender">
								<form:option value="" label="-Select Gender-" />
								<form:option value="male" label="Male" />
								<form:option value="female" label="Female" />
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="small-5 columns">
							<label for="mobileNo" class="right inline">Mobile Number:</label>
						</div>
						<div class="small-7 columns">
							<form:input path="mobileNo" />
						</div>
					</div>
					<div class="row">
						<div class="small-5 columns">
							<label for="email" class="right inline">Email:</label>
						</div>
						<div class="small-7 columns">
							<form:input path="email" />
						</div>
					</div>
					<div class="row">
						<div class="small-5 columns">
							<label for="workNumber" class="right inline"><i class="fi-star small"></i> Work Number:</label>
						</div>
						<div class="small-7 columns">
							<form:input path="workNumber" />
						</div>
					</div> 
				</div>
				<div class="columns large-6 move_form_left">
					<div class="row">
						<div class="small-5 columns">
							<label for="workEmail" class="right inline"><i
								class="fi-star small"></i>Work Email:</label>
						</div>
						<div class="small-7 columns">
							<form:input path="workEmail" />
						</div>
					</div>
					<div class="row">
						<div class="small-5 columns">
							<label for="staffRoleId" class="right inline"><i
								class="fi-star small"></i> Role:</label>
						</div>
						
						<div class="small-7 columns">
							<form:select id="staffRoleId" onchange="functionStaffRoleId(this)" path="staffRoleId">
								<form:option value="0" label="-Select Role-" />
								<form:options items="${sessionScope.rolesMap}" />
							</form:select>
							</div>
							</div>
							<div id="assignDocForNpPA"></div>
<%-- 							<c:choose>	 --%>
<%-- 							<c:when test="${sessionScope.role =='ADMIN'|| sessionScope.role.replace(' ','_') =='SUPER_ADMIN' || sessionScope.role =='PA' || sessionScope.role =='NP'}">  				 --%>
							<c:choose>	 
							<c:when test="${ sessionScope.role =='PA' || sessionScope.role =='NP'}">
							<div class="row" id=Asigndoc style="display: block;">
							</c:when>
							<c:otherwise>
							<div class="row" id=Asigndoc style="display: none;">
							</c:otherwise>
							</c:choose>
								<div class="small-5 columns">
									<label for="docRoleId" class="right inline">Assign to Doctor:</label>
								</div>
								<div class="small-7 columns">
									<form:select id="docRoleId"  path="docRoleId" >
									<form:option  value="0" label="-- Select MD --"/>
									<c:forEach items="${sessionScope.mdInFacilityMap}" var="item">
									<form:option value="${item.key}">${item.value.name}</form:option>
									</c:forEach>
									</form:select>
								</div> 
							</div> 
<%-- 						 </c:when> --%>
<%-- 						</c:choose> --%>
					<div class="row">
						<div class="small-5 columns">
							<label for="address" class="right inline">Street1:</label>
						</div>
						<div class="small-7 columns">
							<form:input path="address" />
						</div>
					</div>
					<div class="row">
						<div class="small-5 columns">
							<label for="street" class="right inline">Street2:</label>
						</div>
						<div class="small-7 columns">
							<form:input path="street" />
						</div>
					</div>
					<div class="row">
						<div class="small-5 columns">
							<label for="city" class="right inline">City:</label>
						</div>
						<div class="small-7 columns">
							<form:input path="city" />
						</div>
					</div>
					<div class="row">
						<div class="small-5 columns">
							<label for="state" class="right inline">State:</label>
						</div>
						<div class="small-7 columns">
							<form:input path="state" class="search"  onfocus="populateStates()"/>
						</div>
					</div>
					<div id="stateResult"></div>
					<span class="name"></span>
					<div class="row">
						<div class="small-5 columns">
							<label for="zipcode" class="right inline">Zip:</label>
						</div>
						<div class="small-7 columns">
							<form:input path="zipcode" />
						</div>
					</div>
					<div class="row" id="activateDeactivateId">
						<div class="small-5 columns">
							<label for="Activate/Deactivate" class="right inline"
								id="deactiveLabel">Deactivate:</label>
						</div>
						<div class="small-7 columns">
							<form:checkbox path="deactive" />
							<form:input path="relievingDate" placeholder="Relieving Date"
								class="relievingdatepicker alignWithCheckBox" />
						</div>
					</div>
				</div>
				<form:hidden path="facilityId" id="facilityId" />
				<form:hidden path="operation" id="operation" />
				<form:hidden path="staffId" id="staffId" />
				<div class="row">
					<div class="small-1 large-centered columns">
					<c:choose>
					<c:when test="${sessionScope.loggedUser.roleValue == 'ROLE_ADMIN' || sessionScope.loggedUser.roleValue == 'ROLE_SUPER_ADMIN'}">
					<input type="submit" class="button small right" value="Register"
								tabindex="3" id="regSubmit"
								onsubmit="loadManageUsersDialog('');" style='padding: 5px 15px; margin: 0;'/>
							<input  id="resett" type="reset"   class="button right small" value="Reset" style='padding: 5px 15px; margin: 0 -197px 0 0;'>
					</c:when>
					<c:otherwise>
					<input type="submit" class="button small" value="Register"
								tabindex="3" id="regSubmit" />
					</c:otherwise>
					</c:choose>
					</div>
				</div>
			</div>
		</form:form>
	</div>
	<!-- User Setting Block  -->
	<div id="userSettingsModal" class="reveal-modal large raleway"
		data-reveal>
		<p class="lead" style="text-align: center;">Users Accounts</p>
		<p id="userSettingsModalMessage"></p>
		<div id="userSettingsModalMessage" style="width: 30px;"></div>
		<a class="close-reveal-modal">&#215;</a>
		<table id="userDataGrid" class="display" width="100%" cellspacing="0">
			<thead>
				<tr>
					<th>Name</th>
					<th>Username</th>
					<th class="userDataGridWorkMail">Work Email</th>
					<th class="userDataGridWorkNumber">Work Number</th>
					<th class="userDataGridRole">Role</th>
					<th class="userDataGridFacility">Facility Name</th>
					<th></th>
					<th></th>
					<th></th>
					<!-- <th>Facility</th> -->
				</tr>
			</thead>
			<tbody></tbody>
		</table>
					<c:choose>
					<c:when test="${sessionScope.loggedUser.roleValue == 'ROLE_ADMIN' || sessionScope.loggedUser.roleValue == 'ROLE_SUPER_ADMIN'}">
					<br/><br/>
					<a href="#" id='newRegistrationModal'
					class="center button [radius tiny round] button_position">New
					User Account</a>
					</c:when>
					</c:choose>
	</div>
	<script src="${pageContext.request.contextPath}/js/vendor/jquery-1.10.2.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/vendor/jquery.easydropdown.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/vendor/jquery.slimscroller.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/app.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/admission/admission.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/admission/admission-view.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/acutecare/acutecare.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/facility.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/admission/admissionFieldValidations.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/carepath.js"></script>

	<script src="${pageContext.request.contextPath}/js/vendor/jquery.inputmask.bundle.js"></script>
	<script src="https://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
	<script src="https://cdn.datatables.net/1.10.5/js/jquery.dataTables.min.js"></script>
<!-- 	<script src="${pageContext.request.contextPath}/js/foundation/jquery.dataTables.min.js"></script> -->
	<script src="${pageContext.request.contextPath}/js/foundation/foundation.dataTables.js"></script>
	<script src="${pageContext.request.contextPath}/js/vendor/jquery.maskedinput.min.js"
		type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/vendor/jquery-ui-timepicker.js"></script>
	<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
	<script src="${pageContext.request.contextPath}/js/foundation.min.js"></script>
	<script>
		$(document).foundation();
		var checkVal = '';
		function editStaffUser(ele) {
			// $('#RegistrationModal').foundation('reveal', 'open');
			$("#careCentStaffReg").text("Facility staff profile");
			var aPos = $('#userDataGrid').dataTable().fnGetPosition(
					ele.parentNode);
			var aData = $('#userDataGrid').dataTable().fnGetData(aPos[0]);
			$
					.ajax({
						type : "GET",
						url : contextPath+"/mangeUser/getStaffDetails",
						data : 'staffId=' + aData[7] + '&operation=edit',
						dataType : 'json',
						success : function(data) {
							$flagArray = aData[4].split('|');
							var adminFlag = $flagArray[0];
							//				alert(adminFlag);
							$('#regSubmit').val("Update");
							$("#firstName").val(data.firstName).attr(
									"readonly", true);
							$("#lastName").val(data.lastName).attr("readonly",
									true);
							$("#middleName").val(data.middleName).attr(
									"readonly", true);
							$("#userName").val(data.userName).attr("readonly",
									true);
							if (staffRole != "ADMIN") {
								$("#workNumber").val(data.workNumber).attr(
										"readonly", true).css('pointer-events', 'none');
								$("#workEmail").val(data.workEmail).attr(
										"readonly", true);
							} else {
								$("#workNumber").val(data.workNumber).css('pointer-events', '');
								$("#workEmail").val(data.workEmail);
							}
							if(aData[7] == '${sessionScope.staffId}'){
								$("#staffRoleId").val(data.staffRoleId).css('pointer-events', 'none');	
							}else{
								$("#staffRoleId").val(data.staffRoleId).css('pointer-events', '');
							}
							if( data.staffRoleId==8||data.staffRoleId==5){
								  
								$("#Asigndoc").show();
								$("#docRoleId").val(data.docRoleId);
							}
							else
								{
								$("#Asigndoc").hide();
								$("#docRoleId").val('0').css('pointer-events', '');
								}
							
							
							if (($("#staffRoleId").find('option:selected')
									.text() == "ADMIN")
									|| ($flagArray[2] == '${sessionScope.staffId}')) {
								$("#activateDeactivateId").hide();
							} else {
								if (data.relievingDate !== null) {
									$("#activateDeactivateId").show();
									$("#deactiveLabel").text("Activate");
									$("#relievingDate").val('');
									$("#relievingDate").hide();
								} else {
									$("#activateDeactivateId").show();
									$("#deactiveLabel").text("Deactive");
									$("#relievingDate").show();
									$("#relievingDate").val(data.relievingDate);
									$("#regSubmit")
											.click(
													function() {
														if ($("#relievingDate")
																.val() == ''
																&& checkVal == "true") {
															confirmforInvalidate();
															$("#dialog-confirm").html('<center>please provide relieving date</center>');
															return false;
														}
													});
								}
							}
							$("#gender").val(data.gender);
							$("#mobileNo").val(data.mobileNo);
							$("#email").val(data.email);
							$("#address").val(data.address);
							$("#street").val(data.street);
							$("#city").val(data.city);
							$("#state").val(data.state);
							$("#zipcode").val(data.zipcode);
							$("#operation").val("edit");
							$("#staffId").val(aData[7]);
						},
						error : AjaxFetchDataFailed
					});

			//Data will be available @ aData
			/* row data setting mapping to form fields  */

			/* ending mapping to form fields....  */
			/* $.each( aData, function( key, val ){
			 console.log(key,val);
			}); */
			rowData = aData;
			$("#registrationErrors .error").html('');
			$("#registrationErrors span").css({
				background : '#fff',
				margin : 0
			});
			$('#userSettingsModal').foundation('reveal', 'close');
			$('#RegistrationModal').foundation('reveal', 'open');
		}

		function addStaffUser(ele) {
			var aPos = $('#userDataGrid').dataTable().fnGetPosition(
					ele.parentNode);
			var aData = $('#userDataGrid').dataTable().fnGetData(aPos[0]);
			$.ajax({ 
				type : "GET",
				url : contextPath+"/mangeUser/getStaffDetails",
				data : 'staffId=' + aData[7] + '&operation=edit',
				dataType : 'json',
				success : function(data) {
					$("#regSubmit").val("Add To Facility");
					$("#firstName").val(data.firstName).attr("readonly", true);
					$("#lastName").val(data.lastName).attr("readonly", true);
					$("#middleName").val(data.middleName)
							.attr("readonly", true);
					$("#userName").val(data.userName).attr("readonly", true);
/* 					$("#empId").val(data.empId); */
					$("#workNumber").val(data.workNumber);
					$("#workEmail").val(data.workEmail);
/* 					$("#dateOfHire").val(data.dateOfHire); */
					$("#staffRoleId").val(0).css('pointer-events','');
					$("#gender").val(data.gender).attr("readonly", true);
					$("#mobileNo").val(data.mobileNo).attr("readonly", true);
					$("#email").val(data.email).attr("readonly", true);
					$("#address").val(data.address).attr("readonly", true);
					$("#street").val(data.street).attr("readonly", true);
					$("#city").val(data.city).attr("readonly", true);
					$("#state").val(data.state).attr("readonly", true);
					$("#zipcode").val(data.zipcode).attr("readonly", true);
					$("#operation").val("add");
					$("#staffId").val(aData[7]);
					$("#activateDeactivateId").hide();

				},
				error : AjaxFetchDataFailed
			});

			//Data will be available @ aData
			/* row data setting mapping to form fields  */

			/* ending mapping to form fields....  */
			/* $.each( aData, function( key, val ){
			 console.log(key,val);
			}); */
			rowData = aData;
			$("#registrationErrors .error").html('');
			$("#registrationErrors span").css({
				background : '#fff',
				margin : 0
			});
			$('#userSettingsModal').foundation('reveal', 'close');
			$('#RegistrationModal').foundation('reveal', 'open');
		}

	</script>
	<script>
		/* to read activate deacivate user by check box click   */
		$(function() {

			$('#staffRoleId option').map(function() {
				if ($(this).text() == "GUEST") {
					$(this).remove();
				}
			}); 
			$("#newRegistrationModal").click(function() {
				$("#activateDeactivateId").hide();
				$('#staffRegistrationForm').trigger('reset');
				cleanNewRegistrationModal();
				$("#Asigndoc").hide();
				
			});
			$("#deactive1").change(function() {
				if (this.checked) {
					checkVal = "true";
				} else {
					checkVal = "false";
				}
			});
			//Field Masking using Jquery Field masking plugin	
			$("#mobileNo").mask("(999) 999-9999");
			$("#workNumber").mask("(999) 999-9999 ? ext:99999");
			$("#zipcode").mask("99999- ? 9999");
/* 			$("#dateOfHire").inputmask("mm/dd/yyyy"); */
		});
	</script>
	<script>
		var content="<table>";
		$.ajax({
			url : contextPath+'/getAllActiveAnnouncement',
			type : 'get',
			success : function(data) {
				$.each(data, function(index, value) {
					content+="<tr><td width='318px'>"+value.announcementText+"</td></tr>";
				});
				content+="</table>";
				$('div.anouncements').html('');
				$('div.anouncements').append(content); 
			}
		});
	</script>
	<div id="dialog-confirm" ></div>
	<div id="messageModel" class="reveal-modal small raleway" data-reveal>
	<a class="close-reveal-modal">&#215;</a>
	<p class="msg_header" style="text-align: center;" id="message">Message</p>

	<!-- 		<div id="message"></div> -->
	</div>

<jsp:include page="/WEB-INF/fragments/carePath.jsp"/>
<jsp:include page="/WEB-INF/fragments/facility.jsp"/>
<jsp:include page="/WEB-INF/fragments/options.jsp"/>
<jsp:include page="/WEB-INF/fragments/announcement.jsp"/>

<!-- <script src='${pageContext.request.contextPath}/js/pdfmake/html2canvas.js'></script> -->
<!-- <script src='${pageContext.request.contextPath}/js/pdfmake/jspdf.debug.js'></script> -->
<!-- <script src="${pageContext.request.contextPath}/js/pdf/export_jsPdf.js"></script> -->
<script src='${pageContext.request.contextPath}/js/pdf/pdfmake.js'></script>
<script src='${pageContext.request.contextPath}/js/pdfmake/vfs_fonts.js'></script>
<script src="${pageContext.request.contextPath}/js/export_pdfMake.js"></script>

</body>
</html>

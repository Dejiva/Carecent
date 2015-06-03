
	
function showLabvwarksValuse(signsSymptomsLabworkId,idx){
	$("#appendlabValues_"+idx).html('');
	var episodeId =  $('#sbarPatientEpisodeId').val();
	$.ajax({
		type : "GET",
		url : contextPath+'/sbar/getlabworkValuesFromCIC?patientEpisodeId='+episodeId+'&signSymptomLabworkId='+signsSymptomsLabworkId,
		success : function(data) {
			if(data.status == "notOk"){
				$("#appendlabValues_"+idx).append("<span>Not Available</span>");
			}else if(data.status == "ok"){
			$.each(data.list, function(idxx, obj){
				$("#appendlabValues_"+idx).append("<div><i class='fi-checkbox left'></i>&nbsp;&nbsp;&nbsp;"+obj.sslattrName+"&nbsp;&nbsp;&nbsp;"+(obj.cicValue == 'true' ?"":':&nbsp;'+obj.cicValue)+"</div>");
			});
			}
		}
	});
}
function showChangeInConditionValuesInputPageForLabwork(signSymptomLabworkId) {
	$("#conditionPopup").empty();
	openJueryPopup('#conditionPopup');
	$("#conditionPopup").dialog('option', 'title', 'Tests');
	$("#conditionPopup").dialog('option', 'resizable', false);
	$("#conditionPopup").dialog('option', 'width', '50%');
	$('#conditionPopup').append("<div id='labworkinputBlock'></div>");
	$(".symptomAdded").hide();
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	$.ajax({
			url : contextPath+'/sbar/getChangeInConditionSymptomValues?id='+signSymptomLabworkId+"&patientEpisodeId="+patientEpisodeId,
			type : 'get',
			success : function(data) {
					var innerHtmlTxt = '<div id="symptomAttrValueId" style="max-height: 350px; overflow-y: auto;" >';
					var len = data.length;
					for (var i = 0; i < len; i++) {
						if (data[i].datatype == 'BOOLEAN') {
							innerHtmlTxt += '<div class="row"><input type="checkbox" '+(data[i].attrValue?"checked":"")+' name="symptomValues'+ data[i].id+ '" value="'+ data[i].id+ '"><label for="symptomValues'+ data[i].id+ '">'+ data[i].attrName+ '</label></div>';
						} else {
							var datatype = data[i].datatype;
							innerHtmlTxt += '<div class="row"><label for="symptomValues'+ data[i].id	+ '">'+ data[i].attrName+ '</label>'+ '<div class="large-3 columns"><input onkeypress="return isIntFolatKey(event, this)" data-datatype="'+ datatype+ '" type="text" id="numbersOnly" '+(!jQuery.isEmptyObject(data[i].attrValue)?'value="'+data[i].attrValue+'"':"")+'  name="symptomValues'+ data[i].id+ '"></div>'+ '<div class="small-1 columns end"><label for="symptomValues'+ data[i].id	+ '">'+ (data[i].units == null|| data[i].units == "" ? "" : "("+ data[i].units + ")")+ '</label></div></div>';
						}
					}
					innerHtmlTxt += '</div><div class="row" style="padding-top:10px;"><button id="changeConditionOk" value="Save" name="Save" onclick="submitCICLabworkFormValues('+signSymptomLabworkId+');" class="small right" style="padding:10px 20px;">Save</button><button id="changeConditionCancel" value="Cancel" name="Cancel" onclick="openJueryPopupClose('
							+ "'#coditionPopup'"
							+ ')" class="small"  style="padding:10px 20px;">Cancel</button></div>';
					$("#labworkinputBlock").html(innerHtmlTxt);
				}
			});
};

function submitCICLabworkFormValues(symptom) {
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	var ajaxData = {
		patientEpisodeId : patientEpisodeId,
		symptom : symptom
	};
	$("#symptomAttrValueId").find(
			'input:radio:checked, input:checkbox:checked, input:text').each(
			function() {
				ajaxData[$(this).attr("name")] = "" + $(this).val();
			});

	$.ajax({
		url : contextPath+'/sbar/saveChangeInConditionsSymptoms',
		data : ajaxData,
		type : 'post',
		success : function(data) {
			if (data == "SUCCESS") {
				openJueryPopupClose("#conditionPopup");
				//getConditionsLabworkData(patientEpisodeId);
				changeSbarPatientStatus(patientEpisodeId);
			} else {
				alert(data);
			}
		}
	});

};

function approveLabwork(ele){
	var signSymptomLabworkId = $(ele).val();
	$("#addLabworkasApproved").html("<input type='button' class='button small right end' onclick='saveApprovedLabworkIncLabworks("+signSymptomLabworkId+")' value='Add' style='margin: 0px; padding: 5px;margin-right:8px'>");	
	
};

function saveApprovedLabworkIncLabworks(signSymptomLabworkId){
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	$.ajax({
		
		url : contextPath+"/sbar/saveApprovedLabworkIncLabworks?patientEpisodeId="+patientEpisodeId+"&signSymptomLabworkId="+signSymptomLabworkId,
		type : "get",
		success : function(data) {
			populateCarePathSuggestedLabworks(patientEpisodeId);
		}
	});
}
function populateCarePathSuggestedLabworks(patientEpisodeId){
    $.ajax({
		url : contextPath+"/sbar/getCarePathLabworkEntries?patientEpisodeId="+patientEpisodeId,
		type : "get",
		success : function(data) {
			var divApprovalsElement = "";
			var divApprovedElement = "";
			var divNurseApprovedElement = "";
			var divRejectedElement = "";
			var divAppUser = "";
			var divRejUser = "";
			$("#labworkDataTable").empty();
			$("#labworkDocApprovedTable").empty();
			$("#labworkNurApprovedTable").empty();
			$("#labworkRejectedTable").empty();
			$.each(data.labworks, function(idx, ele){
				if(!ele.approved && !ele.rejected){
				divApprovalsElement += "<dd class='accordion-navigation'><a onclick='patientToggle(this)' href='#panelaa"+idx+"'><div class='fi-play'></div>&nbsp;&nbsp;&nbsp;"+ele.signSymtomLabworkName+( responsibility == 'NURSE' || responsibility == 'DOCTOR' ?  "<input  signsSymptomsLabwork-sbarId='"+ele.sbarId+"'  signsSymptomsLabwork-carePathCode='"+ele.carePathCode+"' accLabwork-rejId='"+ele.id+"' signsSymptomsLabwork-rejId='"+ele.signsSymptomsLabworkId+"' id='labWorkReject' onclick='labWorkReject(this);' class='button small right' type='button' value='Reject' style='margin: 0px; padding: 3px;'><input id='labWorkGetItDone' onclick='labWorkGetItDone(this);' accLabwork-apprId='"+ele.id+"' signsSymptomsLabwork-sbarId='"+ele.sbarId+"'  signsSymptomsLabwork-carePathCode='"+ele.carePathCode+"'  signsSymptomsLabwork-apprId='"+ele.signsSymptomsLabworkId+"' class='button small right end' type='button' value='Approve' style='margin: 0px; padding:3px;margin-right:8px'>":"");
				divApprovalsElement += "</a><div id='panelaa"+idx+"' class='content'>";
					$.each(ele.signsSymptomsLabworkAttrs,function(idx2,ele2){
						divApprovalsElement +="<i class='fi-arrow-right left'></i>&nbsp;&nbsp;&nbsp;"+ele2.attrName+"<br>";
					});
				divApprovalsElement	+= "</div></dd>";
				}
				$.each(ele.carePathLabworkAppRej,function(idx3,ele3){
					if(ele3.approvedFlag){
						divAppUser = ele3.userName+"("+ele3.role+")";
					}
					if(ele3.rejectedFlag){
						divRejUser = ele3.userName+"("+ele3.role+")";
					}
				});
				if(ele.approved){
					divApprovedElement += "<dd class='accordion-navigation' onclick='showLabvwarksValuse("+ele.signsSymptomsLabworkId+","+idx+")'><a onclick='patientToggle(this)' href='#panelbb"+idx+"'><div class='fi-play'></div>&nbsp;&nbsp;&nbsp;"+ele.signSymtomLabworkName+"&nbsp;&nbsp;&nbsp;By:&nbsp;&nbsp;&nbsp;<span>"+divAppUser+"<span>"+ ((responsibility == 'NURSE' || responsibility == 'DOCTOR') ? "<input  signsSymptomsLabwork-sbarId='"+ele.sbarId+"'  signsSymptomsLabwork-carePathCode='"+ele.carePathCode+"' accLabwork-rejId='"+ele.id+"' signsSymptomsLabwork-rejId='"+ele.signsSymptomsLabworkId+"' id='labWorkReject' onclick='labWorkReject(this);' class='button small right' type='button' value='Reject' style='margin: 0px; padding: 3px;'>":"");
					divApprovedElement += ""+((responsibility == 'NURSE') ?"<input id='takeLabReading'   signsSymptomsLabwork-sbarId='"+ele.sbarId+"'  signsSymptomsLabwork-carePathCode='"+ele.carePathCode+"'  labwork-radingId='"+ele.signsSymptomsLabworkId+"' onclick='showChangeInConditionValuesInputPageForLabwork("+ele.signsSymptomsLabworkId+")' class='button small right end' type='button' value='Edit' style='margin: 0px; padding: 3px;margin-right:8px'>":"");
					divApprovedElement += "</a><div id='panelbb"+idx+"' class='content'><div id='appendlabValues_"+idx+"'></div>";
				}
				divApprovedElement += "</div></dd>";
				if(ele.rejected){
					divRejectedElement +="<div class='large-12 columns'>"+ele.signSymtomLabworkName+"&nbsp;&nbsp;&nbsp;By:&nbsp;&nbsp;&nbsp;<span>"+divRejUser+"<div class='large-2 columns right'><span>"+((responsibility == 'NURSE' || responsibility == 'DOCTOR') ? "<input  signsSymptomsLabwork-sbarId='"+ele.sbarId+"'  signsSymptomsLabwork-carePathCode='"+ele.carePathCode+"'  id='labWorkGetItDone' onclick ='labWorkGetItDone(this)' accLabwork-apprId='"+ele.id+"' signsSymptomsLabwork-apprId='"+ele.signsSymptomsLabworkId+"' class='button small right end' type='button' value='Approve' style='margin: 0px; padding: 3px;margin-right:8px'></div></div>":"")+"</div><br>";
				}
			});
			$("#labworkDataTable").html(divApprovalsElement);
			$("#labworkDocApprovedTable").html(divApprovedElement).css('color','green');
			$("#labworkNurApprovedTable").html(divNurseApprovedElement).css('color','green');
			$("#labworkRejectedTable").html(divRejectedElement).css('color','red');
			
		}			
	});
};
function resetSNWBorder($patientId) {
	$.ajax({
		url : contextPath+'/stopAndWatch/hasResponse?patientId=' + $patientId,
		type : 'get',
		success : function(data) {

			$snwpatientInfo = data.patientInfo;
			if (data.hasRespnse) {
				if ($('#stopAndWatchPatient[data-patient="' + $snwpatientInfo+ '"]').hasClass("bottom_border_red")) {
					$('#stopAndWatchPatient[data-patient="'	+ $snwpatientInfo + '"]').removeClass("bottom_border_red");
				}
				$('#stopAndWatchPatient[data-patient="' + $snwpatientInfo+ '"]').addClass("bottom_border_green");
			} else {
				if ($('#stopAndWatchPatient[data-patient="' + $snwpatientInfo+ '"]').hasClass("bottom_border_green")) {
					$('#stopAndWatchPatient[data-patient="'	+ $snwpatientInfo + '"]').removeClass("bottom_border_green");
				}
				$('#stopAndWatchPatient[data-patient="' + $snwpatientInfo+ '"]').addClass("bottom_border_red");
			}
		}

	});
};

function editStopAndWatchHistory(stopAndWatchHistoryId) {
	var operation = "snwedit";
	$.ajax({
				type : "get",
				global : false,
				url : contextPath+'/stopAndWatch/getStopAndWatchHistoryData?stopAndWatchHistoryId='
						+ stopAndWatchHistoryId + '&operation=' + operation,
				success : function(data) {
					$('#seemsDifferentThanUsual' + 1).prop('checked',
							data.seemsDifferentThanUsual).attr('disabled',
							'disabled');
					$('#communicatesLess' + 1).prop('checked',
							data.communicatesLess).attr('disabled', 'disabled');
					$('#needsMoreHelp' + 1).prop('checked', data.needsMoreHelp)
							.attr('disabled', 'disabled');
					$('#pain' + 1).prop('checked', data.pain).attr('disabled',
							'disabled');
					$('#participatedLessInActivities' + 1).prop('checked',
							data.participatedLessInActivities).attr('disabled',
							'disabled');
					$('#ateLess' + 1).prop('checked', data.ateLess).attr(
							'disabled', 'disabled');
					$('#diarrhea' + 1).prop('checked', data.diarrhea).attr(
							'disabled', 'disabled');
					$('#noBowelMovement' + 1).prop('checked',
							data.noBowelMovement).attr('disabled', 'disabled');
					$('#drankLess' + 1).prop('checked', data.drankLess).attr(
							'disabled', 'disabled');
					$('#weightChange' + 1).prop('checked', data.weightChange)
							.attr('disabled', 'disabled');
					$('#agitated' + 1).prop('checked', data.agitated).attr(
							'disabled', 'disabled');
					$('#tired' + 1).prop('checked', data.tired).attr(
							'disabled', 'disabled');
					$('#weak' + 1).prop('checked', data.weak).attr('disabled',
							'disabled');
					$('#confused' + 1).prop('checked', data.confused).attr(
							'disabled', 'disabled');
					$('#drowsy' + 1).prop('checked', data.drowsy).attr(
							'disabled', 'disabled');
					$('#changeInSkinCondition' + 1).prop('checked',
							data.changeInSkinCondition).attr('disabled',
							'disabled');
					$('#helpWithWalking' + 1).prop('checked',
							data.helpWithWalking).attr('disabled', 'disabled');
					$('#transferring' + 1).prop('checked', data.transferring)
							.attr('disabled', 'disabled');
					$('#toiletingMoreThanUsual' + 1).prop('checked',
							data.toiletingMoreThanUsual).attr('disabled',
							'disabled');
					$('#snwOther'+ 1).prop('checked', data.snwOther).attr(
							'disabled', 'disabled').show();
					if (data.snwOther) {
						$('#snwOtherDiv').show();
						
						$('#snwOtherSymptom').val(data.snwOtherSymptom).show()
								.attr("readonly", true);
						
					} else {
						//$('#snwOther' + 1).prop('checked', data.snwOther);
						$('#snwOtherSymptom').val("").hide();
					}

					$('#residentId').val(data.residentId);
					$('#residentName').val(data.residentName).attr("readonly",
							true);
					$('#snwadd').hide();
					$('#snwedit').show();
					$("#reportedToFacilityStaffId").prop('disabled', true);
					$('#reportedDateAndTime').val(data.reportedDateAndTime);
							if (staffRole == "ADMIN" || staffRole == "CNA" ) {
								var reportedToFacilityStaffId = data.reportedToFacilityStaffId;
								$('#reportedToFacilityStaffId').val(reportedToFacilityStaffId);
								$('#reportedDateAndTime').datepicker("option", "disabled",false);
								$('#InvalidateStopAndWatch').hide();
								$('#facilityStafResponse').attr("readonly", true);
								$('#reportedDateAndTime').val(data.reportedDateAndTime);
								$("#responseDateAndTime").datepicker("option","disabled", false);
								$("#reportedToFacilityStaffId").prop('disabled', false);
								$("#nurseId").prop('disabled', 'disabled');
					} else if (staffRole == "RN" || staffRole == "LPN") {
								if (data.reportedToFacilityStaffId != null) {
									var reportedToFacilityStaffId = data.reportedToFacilityStaffId;
									$('#reportedToFacilityStaffId').val(reportedToFacilityStaffId);
								}
							else{
									$('#InvalidateStopAndWatch').hide();
									$('#facilityStafResponse').attr("readonly", true);	
								}
									$('#reportedDateAndTime').datepicker("option", "disabled",true);
									$('#reportedDateAndTime').val(data.reportedDateAndTime);
									$("#responseDateAndTime").datepicker("option",	"disabled", false);
									$('#reportedToFacilityStaffId').val(data.reportedToFacilityStaffId);
									$('#facilityStafResponse').attr("readonly", false);
									$('#nurseId').val(staffId);
							}
								$('#StopAndWatchModalSub').val("Update");
								$('#stopAndWatchHistoryId').val(stopAndWatchHistoryId);
								$('#stopAndWatchHistroyInvalidateId').val(stopAndWatchHistoryId);
								$("#stopAndWatchErrors span").hide();
								if (data.guestName != null) {
									$('#repotedBy').val(data.guestName).attr("readonly",true);
								} else {
									$('#repotedBy').val(data.yourName).attr("readonly",true);
								}
					$('#stopAndWatchForm1').show();
					$('#StopAndWatchModal').foundation('reveal', 'open');
					
				}
			});
};

function getPatientHistory(patientId) {
	$("#stopAndWatchErrors span").hide();
	$("#stopAndWatchSuccess").hide();
	$.ajax({
				url : contextPath+'/stopAndWatch/getPatientHistory?currPatientId='	+ patientId,
				type : 'get',
				success : function(data) {
					var content = "";
					$.each(	data,function(idx, obj) {
						var list = "", by = "", to = "", items = "";
						$.each(	obj.symtomsList,function(index, item) {
							items += "<li class='fi-check'>&nbsp;&nbsp;"+ item+ "</li>";});
							list = "<div id='panel"+ idx+ ''+ idx+ "' class='content row symptom_row' style='width:95%'>"+ "<div class='medium-6 columns'>"
												+ "<ul class='symptom_ul'><li><B>Symptoms:</B></li>"+ items+ "</ul>"+ "</div>"
												+ "<div class='medium-5 columns end'>"+ "<div style='left: 18px; position: relative;'>"
												+ "<span><B>Nurse Name:</B> "+ obj.nurseName+ "</span><br/>"+ "<span><B>Response: </B>"+ obj.response + "</span><br/>"
												+ "<span><B>DateAndTime:</B> "+ obj.responseDateString+ "</span>" + "</div>"+ "</div>" + "</div>";
										repotedByName=obj.repotedBy;
										repotedToName=obj.repotedTo;
										by = (obj.repotedBy == null ? "": (obj.repotedBy.length>8?obj.repotedBy.substring(0, 8)+"...":obj.repotedBy))
										to = (obj.repotedTo == null ? "":(obj.repotedTo.length>8?obj.repotedTo.substring(0, 8)+"...":obj.repotedTo))
										if (obj.response == ""|| obj.response == null) {
											if (staffRole == "ADMIN") {
												content += ('<dd class="accordion-navigation" ><a onclick="patientToggle(this)" href="#panel'+ idx+ ''+ idx	+ '"><div class="fi-play fi-play_down_nodata"></div>&nbsp;&nbsp;&nbsp;<B>Reported by:</B><span title="'+obj.repotedBy+'" data-tooltip>'
														+ by+ '</span>&nbsp;&nbsp;&nbsp;&nbsp;<B>Reported to:</B><span title="'+obj.repotedTo+'" data-tooltip>'	+ to+ '</span>&nbsp;&nbsp;&nbsp;&nbsp;<B>Date And Time:</B>'
														+ obj.repotedDateString	+ '</a><button class="history_edit tiny edit" id="stop-watch-edit" onclick="editStopAndWatchHistory('+ obj.stopAndWatchHistoryId+ ')" title="Edit" >EDIT</button><button class="history_edit tiny edit" id="stop-watch-delete" onclick="deleteStopAndWatchHistory('+ obj.stopAndWatchHistoryId+ ')" title="Delete" >DELETE</button>'
														+ list + '</dd>');
											} else if (staffRole == "RN"|| staffRole == "LPN") {
												content += ('<dd class="accordion-navigation" ><a onclick="patientToggle(this)" href="#panel'+ idx+ ''+ idx+ '"><div class="fi-play fi-play_down_nodata"></div>&nbsp;&nbsp;&nbsp;<B>Reported by:</B><span title="'+obj.repotedBy+'" data-tooltip>'
														+ by+ '</span>&nbsp;&nbsp;&nbsp;&nbsp;<B>Reported to:</B><span title="'+obj.repotedTo+'" data-tooltip>'	+ to+ '</span>&nbsp;&nbsp;&nbsp;&nbsp;<B>Date And Time:</B>'	+ obj.repotedDateString	+ '</a>'+(obj.repotedTo !=null ?'<button class="history_edit tiny edit" id="stop-watch-edit" onclick="editStopAndWatchHistory('+ obj.stopAndWatchHistoryId+ ')" title="Edit" >EDIT</button>':'')
														+ list + '</dd>');
											}else if (staffRole == "CNA") {
													if (obj.role == "ROLE_ADMIN"|| obj.role == "ROLE_RN"|| obj.role == "ROLE_LPN") {
												            content += ('<dd class="accordion-navigation" ><a onclick="patientToggle(this)" href="#panel'+ idx+ ''+ idx+ '"><div class="fi-play fi-play_down_nodata"></div>&nbsp;&nbsp;&nbsp;<B>Reported by:</B><span title="'+obj.repotedBy+'" data-tooltip>'+ by+ '</span>&nbsp;&nbsp;&nbsp;&nbsp;<B>Reported to:</B><span title="'+obj.repotedTo+'" data-tooltip>'+ to+ '</span>&nbsp;&nbsp;&nbsp;&nbsp;<B>Date And Time:</B>'+ obj.repotedDateString + '</a></dd>');
												  	} else {
															content += ('<dd class="accordion-navigation" ><a onclick="patientToggle(this)" href="#panel'+ idx+ ''+ idx	+ '"><div class="fi-play fi-play_down_nodata"></div>&nbsp;&nbsp;&nbsp;<B>Reported by:</B><span title="'+obj.repotedBy+'" data-tooltip>'+ by	+ '</span>&nbsp;&nbsp;&nbsp;&nbsp;<B>Reported to:</B><span title="'+obj.repotedTo+'" data-tooltip>'	+ to+ '</span>&nbsp;&nbsp;&nbsp;&nbsp;<B>DateAndTime:</B>'+ obj.repotedDateString+ '</a><button class="history_edit tiny edit" id="stop-watch-edit" onclick="editStopAndWatchHistory('+ obj.stopAndWatchHistoryId+ ')" title="Edit" >EDIT</button>'+ list + '</dd>');
											      }
										    }
											else {
												content += ('<dd class="accordion-navigation" ><a onclick="patientToggle(this)" href="#panel'+ idx+ ''+ idx	+ '"><div class="fi-play fi-play_down_nodata"></div>&nbsp;&nbsp;&nbsp;<B>Reported by:</B><span title="'+obj.repotedBy+'" data-tooltip>'+ by+ '</span>&nbsp;&nbsp;&nbsp;&nbsp;<B>Reported to:</B><span title="'+obj.repotedTo+'" data-tooltip>'+ to+ '</span>&nbsp;&nbsp;&nbsp;&nbsp;<B>Date And Time:</B>'	+ obj.repotedDateString+ '</a>' + list + '</dd>');
											}
										} else {

											if (staffRole == "ADMIN") {
												content += ('<dd class="accordion-navigation" ><a onclick="patientToggle(this)" href="#panel'+ idx+ ''+ idx+ '"><div class="fi-play"></div>&nbsp;&nbsp;&nbsp;<B>Reported by:</B><span title="'+obj.repotedBy+'" data-tooltip>'+ by+ '</span>&nbsp;&nbsp;&nbsp;<B>Reported to:</B><span title="'+obj.repotedTo+'" data-tooltip>'+ to+ '</span>&nbsp;&nbsp;&nbsp;&nbsp;<B>Date And Time:</B>'+ obj.repotedDateString	+ '</a><button class="history_edit tiny edit" id="stop-watch-delete" onclick="deleteStopAndWatchHistory('	+ obj.stopAndWatchHistoryId + ')" title="Delete" >DELETE</button>'+ list+ '</dd>');
											} else{
												content += ('<dd class="accordion-navigation" ><a onclick="patientToggle(this)" href="#panel'+ idx+ ''+ idx	+ '"><div class="fi-play"></div>&nbsp;&nbsp;&nbsp;<B>Reported by:</B><span title="'+obj.repotedBy+'" data-tooltip>'+ by+ '</span>&nbsp;&nbsp;&nbsp;<B>Reported to:</B><span title="'+obj.repotedTo+'" data-tooltip>'	+ to+ '</span>&nbsp;&nbsp;&nbsp;&nbsp;<B>Date And Time:</B>'	+ obj.repotedDateString	+ '</a>' + list + '</dd>');

											}

										}
									});

					$("#pHistList").html(content);
				}
			});
};

function deleteStopAndWatchHistory(stopAndWatchHistoryId) {
	var pagefirstId = $("#pagefirstId").text();
	$('<div></div>').appendTo('body').dialog(
					{
						modal : true,
						title : 'Delete Record?',
						zIndex : 10000,
						autoOpen : true,
						width : '300px',
						height : '30%',
						resizable : false,
						buttons : {
							Ok : function() {
								$.ajax({
											type : "get",
											url : contextPath+'/stopAndWatch/deleteStopAndWatchHistoryData?stopAndWatchHistoryId='
													+ stopAndWatchHistoryId,
											success : function(data) {
												resetSNWBorder(data);
												getPatientHistory(data);
												populateMessagesList(pagefirstId,$("#msgesSearchId").val());
												populateAlertsList(alert);
												$("#snwhistorycount" + data).text(($("#snwhistorycount"+ data).text()) - 1);
											}
										});
								$(this).dialog("close");
							},
							Cancel : function() {
								$(this).dialog("close");
								return false;
							}
						},
						close : function(event, ui) {
							$(this).remove();
						}
					});
};

function stopAndWatchViewHistory(e){
//$(document).on('click', "span#stop-watch", function(e) {
	$('#stopAndWatchForm1').hide();
	$('#stopAndWatchForm2').css("max-height","500px");
	if (staffRole == "ADMIN" || staffRole == "CNA") {
		$('#InvalidateStopAndWatch').hide();
	} else {
		$('#InvalidateStopAndWatch').show();
	}
    //var value1 = $(this).parent().attr("data-patient");
    var value1=e.parentNode.getAttribute("data-patient");
	$patientInfoArr = value1.split('=');
	$('#StopAndWatchModal').foundation('reveal', 'open');
	getPatientHistory($patientInfoArr[0]);
};
function addStopAndWactch(e){
	$('#InvalidateStopAndWatch').hide();
	$('#stopAndWatchSuccess').hide();
	var value1 = e.parentNode.getAttribute("data-patient");
	$patientInfoArr = value1.split('=');
	$('#residentName').val($patientInfoArr[1]);
	$('#residentId').val($patientInfoArr[0]).attr("hidden", true);
	$('#residentName').attr("readonly", true);
	$("#stopAndWatchForm1 input:checkbox").removeAttr('disabled');
	$("#stopAndWatchForm1 input:checkbox").prop('checked', false);
	$("#other").prop('checked', false);
	$("#otherSymtom").val('').hide();
	$("#snwOtherSymptom").val('').hide();
	$('#operation').val("snwadd");
	$('#yourName').attr("readonly", true);
	$('#yourName').val(staffName);
	$('#snwadd').show();
	$('#snwedit').hide();
	$('#reportedToFacilityStaffId').val("");
	$("#nurseId").val("");
	$('#StopAndWatchModalSub').val("Submit");
	$('#stopAndWatchForm1').show();
	$('#StopAndWatchModal').foundation('reveal', 'open');
	$('#stopAndWatchHistoryId').val("");
	$("#responseDateAndTime").val("");
	$("#reportedDateAndTime").val("").attr("hidden", false);
	$("#stopAndWatchErrors span").hide();
	getPatientHistory($patientInfoArr[0]);
	if (staffRole == "ADMIN" || staffRole == "CNA") {
		$('#reportedDateAndTime').datepicker("option", "disabled",false);
		$("#nurseId").prop('disabled', 'disabled');
		$('#facilityStafResponse').attr("readonly", true);
		$("#reportedToFacilityStaffId").prop('enabled', 'enabled');
		$("#responseDateAndTime").datepicker("option", "disabled",true);
		$("#snwOtherDiv").hide();
	} else if (staffRole == "RN" || staffRole == "LPN") {
		var dNow = new Date();
		var utc = new Date(dNow.getTime()
				+ dNow.getTimezoneOffset());
		var utcdate = (utc.getMonth() + 1) + '/' + utc.getDate()
				+ '/' + utc.getFullYear() + ' ' + utc.getHours()
				+ ':' + utc.getMinutes() + ':' + utc.getSeconds();
		$('#reportedDateAndTime').val(utcdate);
		$("#responseDateAndTime").datepicker("option", "disabled",
				false);
		$('#reportedToFacilityStaffId').val(staffId);
		$('#reportedDateAndTime').datepicker("option", "disabled",false);
		$('#nurseId').val(staffId);
	} else if (staffRole == "MD" || staffRole == "NP"|| staffRole == "PA") {
		$("#snwOtherDiv").hide();
		$("#nurseId").prop('disabled', 'disabled');
		$('#facilityStafResponse').attr("readonly", true);
		$("#reportedToFacilityStaffId").prop('enabled', 'enabled');
		$("#responseDateAndTime").datepicker("option", "disabled",
				true);
	}
};
function addSymptoms(){
	var ele = $("#conditionPopup");
	openJueryPopup('#conditionPopup');
	ele.empty();
	$.ajax({
				url : contextPath+'/sbar/getChangeInConditionSymptoms',
				type : 'get',
				success : function(data) {
					var innerHtmlText = '<div id="sbar-symtomErrors">';
					innerHtmlText += '</div>';
					innerHtmlText += '<div class="row symptomAdded"style="overflow-x:hidden"><div class="large-12 columns"><label class="left">The change in condition, symptoms, or signs I am calling about is/are: </label></div><div class="large-12 columns"><div class="large-8 column"><select id="symptom" name="symptom" onChange="getChangeInConditionValues(this)"><option value="0">-- Select --</option>';
					var len = data.length;
					for (i = 0; i < len; i++) {
						innerHtmlText += '<option value="'+ data[i].id+ '">'+ data[i].signsSymptomsLabworkName+ '</option>';
					}
					innerHtmlText += '</select></div></div><div id="symptomsValueBlock"></div>';
					ele.html(innerHtmlText);
				}
			});
};

function getChangeInConditionValues(ele) {
	var selectedId = $(ele).val();
	$.ajax({
				url : contextPath+'/sbar/getChangeInConditionSymptomValues',
				data : {
					id : selectedId
				},
				type : 'get',
				success : function(data) {
					var innerHtmlTxt = '<div id="symptomAttrValueId">';
					var len = data.length;
					if (len == 1) {
						innerHtmlTxt += '<div class="row padding-lr"><div class="large-12 columns padding-lr"  style="padding-left:11px"><input type="checkbox"  name="symptomValues'+ data[0].id+ '" value="'	+ data[0].id+ '"><label for="symptomValues'+ data[0].id+ '">'+ data[0].attrName+ '</label></div></div>';
					} else {

						for (var i = 0; i < len; i++) {
							if (data[i].datatype == 'BOOLEAN') {
								// Two Check Boxes
								innerHtmlTxt += '<div class="row padding-lr"><div class="large-12 columns padding-lr" style="padding-left:11px"><input type="checkbox" class="left"  name="symptomValues'+ data[i].id+ '" value="'	+ data[i].id+ '"><label for="symptomValues'+ data[i].id+ '" class="small-10 columns">'	+ data[i].attrName+ '</label></div></div>';
							}
						}
					}
					innerHtmlTxt += '</div>';
					innerHtmlTxt += '<div class="row padding-lr"><div class="large-12 columns padding-lr"><div class="large-9 columns padding-lr"><label>Since this started has it gotten:</label><div class="small-12 columns"><div class="large-3 columns"><input type="radio" value="WORSE" name="symptomsStatus"><label>Worse&nbsp;&nbsp;&nbsp;&nbsp;</label></div><div class="large-3 columns"><input type="radio" value="BETTER" name="symptomsStatus"><label>Better&nbsp;&nbsp;&nbsp;&nbsp;</label></div><div class="large-4 columns end"><input type="radio" value="SAME" name="symptomsStatus"><label>Stayed the same</label></div></div></div><div class="large-3 columns padding-lr end"><label>This started on: <input type="text" class="symptomsDateClass" readonly="true" name="symptomsDate" onchange="return validateDate(this)"></label></div></div></div> <div class="row padding-lr"><div class="large-12 columns padding-lr"><div class="large-6 column padding-lr"><label>Things that make the condition or symptom <b>worse</b> are: <input type="text" name="thingsMadeSymptomWorse"></label></div><div class="large-6 column padding-lr"><label>Things that make the condition or symptom <b>better</b> are: <input type="text" name="thinsMadeSymptomBetter"></label></div></div></div><div class="row padding-lr"><div class="large-12 columns padding-lr"><div class="large-12 column padding-lr"><label>This condition, symptom, or sign has occurred before:</label></div><div class="large-6 column padding-lr end"><input type="radio" value="YES" name="symptomAppearedBefore"><label>Yes&nbsp;&nbsp;</label><input type="radio" value="NO" name="symptomAppearedBefore"><label>No&nbsp;&nbsp;</label></div></div></div><div class="row padding-lr"><div class="padding-lr"><div class="large-12 columns padding-lr"><label>Treatment for last episode (if applicable) <input type="text" name="treatmentForLastEpisode"></label></div></div></div><div class="row  padding-lr"><div class="padding-lr"><div class="large-12 columns padding-lr"><label>Other relevant information <input type="text"  name="comments"></label></div></div></div>';
					innerHtmlTxt += '<div class="row padding-lr" style="margin-top: 10px;"><div class="large-12 columns"><button id="changeConditionOk" value="Save" name="Save" onclick="submitCICForm();" class="small right">Save</button><button id="changeConditionCancel" value="Cancel" name="Cancel" onclick="openJueryPopupClose('+ "'#coditionPopup'"+ ')" class="small">Cancel</button></div></div>';
					$("#symptomsValueBlock").html(innerHtmlTxt);
				}
			});
};
function getChangeInConditionValuesForLabwork(ele) {
	var selectedId = $(ele).val();
	$.ajax({
				url : contextPath+'/sbar/getChangeInConditionSymptomValues',
				data : {
					id : selectedId
				},
				type : 'get',
				success : function(data) {
					var innerHtmlTxt = '<div id="symptomAttrValueId" style="max-height: 350px; overflow-y: auto;" >';
					var len = data.length;
					for (i = 0; i < len; i++) {
						if (data[i].datatype == 'BOOLEAN') {
							innerHtmlTxt += '<div class="row padding-lr"><input type="checkbox" name="symptomValues'
									+ data[i].id
									+ '" value="'
									+ data[i].id
									+ '" style="margin: 0.3rem 0 1rem  !important;"><label for="symptomValues'
									+ data[i].id
									+ '">'
									+ data[i].attrName
									+ '</label></div>';
						} else {
							var datatype = data[i].datatype;
							innerHtmlTxt += '<div class="row padding-lr"><label for="symptomValues'
									+ data[i].id
									+ '">'
									+ data[i].attrName
									+ '</label>'
									+ '<div style="margin-left:5px" class="row small-6"><div class="medium-2 columns left"><input onkeypress="return isIntFolatKey(event, this)" data-datatype="'
									+ datatype
									+ '" type="text" id="numbersOnly"  name="symptomValues'
									+ data[i].id
									+ '" style="margin: 0.3rem 0 1rem  !important; "></div>'
									+ '<div class="medium-2 columns end"><label for="symptomValues'
									+ data[i].id
									+ '">'
									+ (data[i].units == null
											|| data[i].units == "" ? "" : "("
											+ data[i].units + ")")
									+ '</label></div></div></div>';
						}
					}
					innerHtmlTxt += '<div class="row right" style="margin-top: 10px;"><button id="changeConditionOk" value="Save" name="Save" onclick="submitCICLabworkForm();"  style="padding: 5px 15px;margin-bottom: 0 !important;" class="small right">Save</button><button id="changeConditionCancel" value="Cancel" name="Cancel" onclick="openJueryPopupClose('
							+ "'#coditionPopup'"
							+ ')" style="padding: 5px 15px;margin-right: 20px;margin-bottom: 0 !important;" class="small right">Cancel</button></div>';
					$("#symptomsValueBlock").html(innerHtmlTxt);
				}
			});
};

function submitCICForm() {

	var error = false;
	var errorMsg = "";
	var checkStatus = false;
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	var symptom = "";
	if ($("#symptom option:selected").val() != null) {
		symptom = $("#symptom option:selected").val();

	} else {
		symptom = $("#symptomHidden").val();
	}
	var symptomsDate = $("input[name=symptomsDate]").val();
	var symptomsStatus = $("input:radio[name=symptomsStatus]:checked").val();
	var thingsMadeSymptomWorse = $("input[name=thingsMadeSymptomWorse]").val();
	var thinsMadeSymptomBetter = $("input[name=thinsMadeSymptomBetter]").val();
	var symptomAppearedBefore = $(
			"input:radio[name=symptomAppearedBefore]:checked").val();
	var treatmentForLastEpisode = $("input[name=treatmentForLastEpisode]")
			.val();
	var comments = $("input[name=comments]").val();
	var ajaxData = {
		patientEpisodeId : patientEpisodeId,
		symptom : symptom,
		symptomsDate : symptomsDate,
		symptomsStatus : symptomsStatus,
		thingsMadeSymptomWorse : thingsMadeSymptomWorse,
		thinsMadeSymptomBetter : thinsMadeSymptomBetter,
		symptomAppearedBefore : symptomAppearedBefore,
		treatmentForLastEpisode : treatmentForLastEpisode,
		comments : comments
	};
	$("#symptomAttrValueId").find(
			'input:radio:checked, input:checkbox:checked, input:text').each(
			function() {
				ajaxData[$(this).attr("name")] = "" + $(this).val();
			});

	$("#symptomAttrValueId input:checkbox").each(function(c) {
		var isChecked = $(this).is(":checked");
		if (isChecked) {
			checkStatus = isChecked;

		}
	});
	if (!checkStatus) {
		errorMsg += "Select Atleast One Symptom<Br>";
		error = true;
	}
	if (error) {
		$("#sbar-symtomErrors").show().html(errorMsg);
		return false;
	} else {
		$.ajax({
			url : contextPath+'/sbar/saveChangeInConditionsSymptoms',
			data : ajaxData,
			type : 'post',
			success : function(data) {
				if (data == "SUCCESS") {
					openJueryPopupClose("#conditionPopup");
					getConditionsSymptomsData(patientEpisodeId);
					changeSbarPatientStatus(patientEpisodeId);
				} else {
				}
			}
		});
	}
};

function submitCICLabworkForm() {
	// var patientEpisodeId =40;
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	var symptom = "";
	if ($("#symptom option:selected").val() != null) {
		symptom = $("#symptom option:selected").val();
	} else {
		symptom = $("#symptomHidden").val();
	}

	var ajaxData = {
		patientEpisodeId : patientEpisodeId,
		symptom : symptom
	};
	$("#symptomAttrValueId").find(
			'input:radio:checked, input:checkbox:checked, input:text').each(
			function() {
				ajaxData[$(this).attr("name")] = "" + $(this).val();
			});

	$.ajax({
		url : contextPath+'/sbar/saveChangeInConditionsSymptoms',
		data : ajaxData,
		type : 'post',
		success : function(data) {
			if (data == "SUCCESS") {
				openJueryPopupClose("#conditionPopup");
				getConditionsLabworkData(patientEpisodeId);
				changeSbarPatientStatus(patientEpisodeId);
			} else {
				
			}
		}
	});

};


function changeSbarPatientStatus(patientEpisodeId) {
	$.ajax({
		url : contextPath+'/sbar/changeSbarPatientStatus?patientEpisodeId='
				+ patientEpisodeId,
		type : 'get',
		success : function(data) {
			if (data.changeInConditionMessageList.length != 0) {
				var notifyImediate = false;
				$.each(data.changeInConditionMessageList, function(key, ele) {
					if (constants_reportImmidate == ele.message) {
						notifyImediate = true;
					}
				});
				if (notifyImediate == true) {
					$("#sbarPatientStatus" + data.patientId).removeClass('no')
							.text("Immediate");
				} else {
					$("#sbarPatientStatus" + data.patientId).addClass('no')
							.text("Non Immediate");
				}

			} else {
				$("#sbarPatientStatus" + data.patientId).text("");
			}
		}
	});

}
function saveNurseNotes() {
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	var nurseNotes = $("#nurseNotes").val();
	$.ajax({
		url : contextPath+'/sbar/saveNotes?patientEpisodeId=' + patientEpisodeId
				+ '&notes=' + nurseNotes,
		type : 'get',
		success : function(data) {
			openJueryPopupClose("#conditionPopup");
			getNurseNotes(patientEpisodeId);
		}
	});

};
function savePractitionerNotes() {
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	var practitionerNotes = $("#practitionerNotes").val();
	$.ajax({
		url : contextPath+'/sbar/saveNotes?patientEpisodeId=' + patientEpisodeId
				+ '&notes=' + practitionerNotes,
		type : 'get',
		success : function(data) {
			openJueryPopupClose("#conditionPopup");
			getPractitionerNotes(patientEpisodeId);
		}
	});

}

function saveMedicine() {
	var error = false;
	var errorMsg = "";
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	var medicineName = $("#medicine").val();
	if(medicineName == ''){
		errorMsg += "please enter medicineName<BR>";
		error=true;
	}
	if(error)
		{
		$("#medicine-Errors").show().html(errorMsg);
		return false;
		}
	else{
	$.ajax({
		url : contextPath+'/sbar/saveMedicine?patientEpisodeId='
				+ patientEpisodeId + '&medicineName=' + medicineName,
		type : 'get',
		success : function(data) {
			openJueryPopupClose("#conditionPopup");
			getMedicine(patientEpisodeId);
		}
	});
	}
}
$('#changeConditionCancel').on("click", function() {
	openJueryPopupClose("#conditionPopup");
});

function removeSymptomRecord(sid, patientEpisodeId) {
	$.ajax({
		url : contextPath+'/sbar/removeChangeInConditionRecord?patientEpisodeId='+ patientEpisodeId + '&symptomId=' + sid,
		type : 'get',
		success : function(data) {
			getConditionsSymptomsData(patientEpisodeId);
			changeSbarPatientStatus(patientEpisodeId);
		}
	});
};

function removeLabworkRecord(sid, patientEpisodeId) {
	$.ajax({
		url : contextPath+'/sbar/removeChangeInConditionRecord?patientEpisodeId='
				+ patientEpisodeId + '&symptomId=' + sid,
		type : 'get',
		success : function(data) {
			changeSbarPatientStatus(patientEpisodeId);
			getConditionsLabworkData(patientEpisodeId);
		}
	});
};
function editSymptomRecord(sid, peid) {

	$.ajax({
				url : contextPath+'/sbar/getSymptomsChangeInConditionsByPatientEpisodeId?patientEpisodeId='+ peid + '&symptomId=' + sid,
				type : 'GET',
				success : function(data) {
					$('#conditionPopup').empty();
					$('#conditionPopup').append(
							'<div id="symptomsValueBlockEdit"></div>');
					var innerHtmlTxt = '<div id="sbar-symtomErrors">';
					innerHtmlTxt += '</div>';
					innerHtmlTxt += '<div id="symptomAttrValueId">';
					innerHtmlTxt += '<div class="row padding-lr"><p class="s_header">'+ data[0].symptomName + '</p></div>';
					$
							.each(
									data[0].attributes,
									function(idx, val) {
										if (val.dataType == 'BOOLEAN') {
											innerHtmlTxt += '<div class="row padding-lr"><div class="large-12 columns"><input type="checkbox" class="left" name="symptomValues'
													+ val.attributeId
													+ '" value="'
													+ val.attributeId
													+ '" '
													+ (val.attributeValue == "true" ? 'checked="checked"'
															: "")
													+ '><label  class="small-10 columns" for="symptomValues'
													+ val.attributeId
													+ '">'
													+ val.attributeName
													+ '</label></div></div>';
										} else {
											innerHtmlTxt += '<div class="row padding-lr"><div class="large-12 columns"><label for="symptomValues'
													+ val.attributeId
													+ '">'
													+ val.attributeName
													+ '<input id="numbersOnly" onkeypress="return isNumberKey(event)" type="text" name="symptomValues'
													+ val.attributeId
													+ '" value="'
													+ (val.attributeValue != null ? val.attributeValue
															: "")
													+ '" style="margin: 0.3rem 0 1rem !important;width: 20%;"></label></div></div>';
										}
									});
					innerHtmlTxt += '<input type="hidden" id="symptomHidden" name="symptom" value="'
							+ data[0].symptomId + '">';
					innerHtmlTxt += '<input type="hidden" name="patientEpisodeId" value="'
							+ peid + '">';
					innerHtmlTxt += '<div class="row padding-lr"><div class="large-9 columns padding-lr"><label>Since this started has it gotten:</label><div class="large-12 columns"><div class="large-3 columns"><input type="radio" value="WORSE" '	+ (data[0].symptomStatus == "WORSE" ? 'checked="checked"': "")+ ' name="symptomsStatus"><label>Worse&nbsp;&nbsp;&nbsp;&nbsp;</label></div><div class="large-3 columns"><input type="radio" value="BETTER" '+ (data[0].symptomStatus == "BETTER" ? 'checked="checked"': "")	+ ' name="symptomsStatus"><label>Better&nbsp;&nbsp;&nbsp;&nbsp;</label></div><div class="large-4 columns end"><input type="radio" value="SAME" '+ (data[0].symptomStatus == "SAME" ? 'checked="checked"': "")+ ' name="symptomsStatus"><label>Stayed the same</label></div></div></div><div class="large-3 columns padding-lr end"><label>This started on: <input type="text" class="symptomsDateClass" readonly="true" name="symptomsDate" onchange="return validateDate(this)" value="'+ (data[0].startedOnDate != null ? data[0].startedOnDate: "")+ '"></label></div></div><div class="row padding-lr"><div class="large-6 column padding-lr"><label>Things that make the condition or symptom <b>worse</b> are: <input type="text" name="thingsMadeSymptomWorse" value="'	+ (data[0].thingsMadeSymptomWorse != null ? data[0].thingsMadeSymptomWorse: "")	+ '"></label></div><div class="large-6 column padding-lr"><label>Things that make the condition or symptom <b>better</b> are: <input type="text" name="thinsMadeSymptomBetter" value="'
							+ (data[0].thinsMadeSymptomBetter != null ? data[0].thinsMadeSymptomBetter
									: "")
							+ '"></label></div></div><div class="row padding-lr"><div class="large-12 columns padding-lr"><label>This condition, symptom, or sign has occurred before:</label></div><div class="large-12 columns padding-lr"><input type="radio" value="YES" name="symptomAppearedBefore" '
							+ (data[0].occurredBefore == false ? 'checked="checked"'
									: "")
							+ '><label>Yes&nbsp;&nbsp;</label><input type="radio" value="NO" name="symptomAppearedBefore" '
							+ (data[0].occurredBefore == true ? 'checked="checked"'
									: "")
							+ '><label>No&nbsp;&nbsp;</label></div></div><div class="row  padding-lr"><div class="large-12 columns padding-lr"><label>Treatment for last episode (if applicable) <input type="text" name="treatmentForLastEpisode" value="'
							+ (data[0].treatmentForLastEpisode != null ? data[0].treatmentForLastEpisode
									: "")
							+ '"></label></div></div><div class="row padding-lr"><div class="large-12 columns padding-lr"><label>Other relevant information <input type="text" name="comments" value="'
							+ (data[0].comments != "" ? data[0].comments : "")
							+ '"></label></div></div>';
					innerHtmlTxt += '<div class="row right" style="margin-top: 10px;"><button id="changeConditionOk" value="Save" name="Save" onclick="submitCICForm();"  style="padding: 5px 15px;margin-bottom: 0 !important;" class="small right">Save</button><button id="changeConditionCancel" value="Cancel" name="Cancel" onclick="openJueryPopupClose('
							+ "'#coditionPopup'"
							+ ')" style="padding: 5px 15px;margin-right: 20px;margin-bottom: 0 !important;" class="small right">Cancel</button></div>';
					$("#symptomsValueBlockEdit").html(innerHtmlTxt);
					openJueryPopupWithTitle("#conditionPopup");
				}
			});
};

function editLabworkRecord(sid, peid) {
	$.ajax({
				url : contextPath+'/sbar/getSymptomsChangeInConditionsByPatientEpisodeId?patientEpisodeId='
						+ peid + '&symptomId=' + sid,
				type : 'GET',
				success : function(data) {
					$('#conditionPopup').empty();
					$('#conditionPopup').append(
							'<div id="symptomsValueBlockEdit"></div>');
					var innerHtmlTxt = '<div id="symptomAttrValueId">';
					innerHtmlTxt += '<div class="row padding-lr"><p class="s_header">'
							+ data[0].symptomName + '</p></div>';
					$.each(
									data[0].attributes,
									function(idx, val) {
										if (val.dataType == 'BOOLEAN') {
											innerHtmlTxt += '<div class="row padding-lr"><input type="checkbox" name="symptomValues'
													+ val.attributeId
													+ '" value="'
													+ val.attributeId
													+ '" '
													+ (val.attributeValue == "true" ? 'checked="checked"'
															: "")
													+ ' style="margin: 0.3rem 0 1rem !important;"><label for="symptomValues'
													+ val.attributeId
													+ '">'
													+ val.attributeName
													+ '</label></div>';
										} else {
											innerHtmlTxt += '<div class="row padding-lr"><label for="symptomValues'
													+ val.attributeId
													+ '">'
													+ val.attributeName
													+ '<input  onkeypress="return isNumberKey(event)" type="text" name="symptomValues'
													+ val.attributeId
													+ '" value="'
													+ (val.attributeValue != null ? val.attributeValue
															: "")
													+ '" style="margin: 0.3rem 0 1rem !important;width: 20%;"></label></div>';
										}
									});
					innerHtmlTxt += '<input type="hidden" id="symptomHidden" name="symptom" value="'
							+ data[0].symptomId + '">';
					innerHtmlTxt += '<input type="hidden" name="patientEpisodeId" value="'
							+ peid + '">';
					innerHtmlTxt += '<div class="row right" style="margin-top: 10px;"><button id="changeConditionOk" value="Save" name="Save" onclick="submitCICLabworkForm();"  style="padding: 5px 15px;margin-bottom: 0 !important;" class="small right">Save</button><button id="changeConditionCancel" value="Cancel" name="Cancel" onclick="openJueryPopupClose('
							+ "'#coditionPopup'"
							+ ')" style="padding: 5px 15px;margin-right: 20px;margin-bottom: 0 !important;" class="small right">Cancel</button></div>';
					$("#symptomsValueBlockEdit").html(innerHtmlTxt);
					openJueryPopup("#conditionPopup");
					$("#conditionPopup").dialog('option', 'title',
							'Lab Work Edit');
				}
			});
};


function editSbarNotes(notesId) {
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	var notes = $('#editNotes').val();
	$.ajax({
		url : contextPath+'/sbar/editSbarNotesByNotesId?notesId=' + notesId
				+ '&notes=' + notes,
		type : 'get',
		success : function(data) {

			getNurseNotes(patientEpisodeId);
			getPractitionerNotes(patientEpisodeId);
			openJueryPopupClose('#coditionPopup');
		}
	});
};
function sbarNotesDel(e){
var notesId = e.getAttribute("data-id");
var patientEpisodeId = $('#sbarPatientEpisodeId').val();
$.ajax({
	url : contextPath+'/sbar/deleteSbarNotes?notesId=' + notesId,
	type : 'get',
	success : function(data) {
		getNurseNotes(patientEpisodeId);
		getPractitionerNotes(patientEpisodeId);
	}
});

};
function getConditionsSymptomsData(patientEpisodeId) {
	$.ajax({
				type : "GET",
				url : contextPath+"/sbar/getSymptomsChangeInConditionsByPatientEpisodeId?patientEpisodeId="
						+ patientEpisodeId,
				dataType : 'json',
				success : function(data) {
					
					var content = "";
					$.each(
									data,
									function(idx, ele) {

										var sym = "<label><b>Condition:</b></label>", contentEnd = "</div></dd>";
										$.each(	ele.attributes,	function(index, item) {
											sym += '<div class="content"><div class="medium-12 columns padding-lr"><i class="'
																	+ (item.attributeValue == "true" ? "fi-checkbox left"
																			: "fi-x left")
																	+ '"></i><label style="margin-top: 3px !important;">&nbsp;&nbsp;&nbsp;&nbsp;'
																	+ item.attributeName
										});
										if (ele.startedOnDate != null) {
											sym += '<hr style="margin: 5px !important;"/><div class="content  padding-lr"><label>&nbsp;<i class="fi-arrow-right"></i>&nbsp;&nbsp;&nbsp;This started on <b>'
													+ ele.startedOnDate
													+ '</b>,&nbsp;&nbsp;<i class="fi-flag"></i>&nbsp;&nbsp; since this started, it gotten <b>'
													+ (ele.symptomStatus != "null" ? ele.symptomStatus
															: "UNKNOWN")
													+ '</b>.&nbsp;&nbsp;<i class="fi-flag"></i>&nbsp;&nbsp;';
										}
										if (ele.thingsMadeSymptomWorse != "") {
											sym += ' <b>'
													+ ele.thingsMadeSymptomWorse
													+ '</b> made the condition or symptom worse, &nbsp;&nbsp;<i class="fi-flag"></i>&nbsp;&nbsp;';
										}
										if (ele.thinsMadeSymptomBetter != "") {
											sym += '<b>'
													+ ele.thinsMadeSymptomBetter
													+ '</b> made the condition or symptom better.&nbsp;&nbsp;<i class="fi-flag"></i>&nbsp;&nbsp;';
										}
										if (ele.occurredBefore != false) {
											sym += 'This condition, symptom, or sign <b>has</b> occurred before. ';
										} else {
											sym += 'This condition, symptom, or sign <b>has not</b> occurred before. ';
										}
										if (ele.treatmentForLastEpisode != "") {
											sym += '<br/>&nbsp;<i class="fi-arrow-right"></i>&nbsp;&nbsp;&nbsp;Treatment for last episode (if applicable)   :  <b>'
													+ ele.treatmentForLastEpisode
													+ '</b>';
										}
										if (ele.comments != "") {
											sym += '<br/>&nbsp;<i class="fi-arrow-right"></i>&nbsp;&nbsp;&nbsp;Other relevant information   :  <b>'
													+ ele.comments + '</b>';
										}
										
										content += '<dd class="accordion-navigation"><a href="#symptom'
												+ idx
												+ '" onclick="patientToggle(this)"><div class="fi-play"></div>&nbsp;&nbsp;&nbsp;<b>Symptom:&nbsp;&nbsp;&nbsp;</b>'
												+ ele.symptomName
												+ '<input type="button" onclick="removeSymptomRecord('
												+ ele.symptomId
												+ ','
												+ patientEpisodeId
												+ ')" value="Remove" class="button small right" style="margin: 0px; padding: 5px;"><input type="button" id="symptomEdit" onclick="editSymptomRecord('
												+ ele.symptomId
												+ ','
												+ patientEpisodeId
												+ ')" value="Edit" class="button small right" style="margin: 0px; padding: 5px;margin-right:8px "></a><div class="content row symptom_row" id="symptom'
												+ idx + '">';
										content += sym + "</label></div>"
												+ contentEnd;
									});
					$("#symptomsDataTable").html(content);
				}
			});
};

function getConditionsSymptomsDataForView(patientEpisodeId) {
	 $('#addMedicine').show();
	 $('#addLabWorkForSymptom').show();
 	 $.ajax({
				type : "GET",
				url : contextPath+"/sbar/getSymptomsChangeInConditionsByPatientEpisodeId?patientEpisodeId="
						+ patientEpisodeId,
				dataType : 'json',
			success : function(data) {
				
				if(data==''){
						$('#addMedicine').hide();
						$('#addLabWorkForSymptom').hide();
						var innerHtmlTxt ='<div class="row"><div class="small-12 column noDataMsg"><center>So far no Signs&Symptoms recorded</cemter></div></div>';
						$("#symptomsViewDataTable").html(innerHtmlTxt);
					  }else{
			
						var content = "";
						$.each(data,function(idx, ele) {
										
										var sym = "", contentEnd = "</div>";
										$.each(
														ele.attributes,
														function(index, item) {
															if (item.attributeValue == "true") {
																sym += '<div class="content"><div class=" columns "><i class="'
																		+ (item.attributeValue == "true" ? "fi-checkbox left"
																				: "fi-x left")
																		+ '"></i><label style="margin-top: 3px !important;">&nbsp;&nbsp;&nbsp;&nbsp;'
																		+ item.attributeName
																		+ '<span class="cic_sym_status">'
																		+ item.doctorNotification
																		+ '</span></label></div></div>';
																}
														});
										if (ele.startedOnDate != null) {
											sym += '<hr style="margin: 5px !important;"/><div class="content padding-lr"><label>&nbsp;<i class="fi-arrow-right"></i>&nbsp;&nbsp;&nbsp;This started on <b>'
													+ ele.startedOnDate
													+ '</b>,&nbsp;&nbsp;<i class="fi-flag"></i>&nbsp;&nbsp; since this started, it gotten <b>'
													+ (ele.symptomStatus != "null" ? ele.symptomStatus
															: "UNKNOWN")
													+ '</b>.&nbsp;&nbsp;<i class="fi-flag"></i>&nbsp;&nbsp;';
										}
										if (ele.thingsMadeSymptomWorse != "") {
											sym += ' <b>'
													+ ele.thingsMadeSymptomWorse
													+ '</b> made the condition or symptom worse, &nbsp;&nbsp;<i class="fi-flag"></i>&nbsp;&nbsp;';
										}
										if (ele.thinsMadeSymptomBetter != "") {
											sym += '<b>'
													+ ele.thinsMadeSymptomBetter
													+ '</b> made the condition or symptom better.&nbsp;&nbsp;<i class="fi-flag"></i>&nbsp;&nbsp;';
										}
										if (ele.occurredBefore != false) {
											sym += 'This condition, symptom, or sign <b>has</b> occurred before. ';
										} else {
											sym += 'This condition, symptom, or sign <b>has not</b> occurred before. ';
										}
										if (ele.treatmentForLastEpisode != "") {
											sym += '<br/>&nbsp;<i class="fi-arrow-right"></i>&nbsp;&nbsp;&nbsp;Treatment for last episode (if applicable)   :  <b>'
													+ ele.treatmentForLastEpisode
													+ '</b>';
										}
										if (ele.comments != "") {
											sym += '<br/>&nbsp;<i class="fi-arrow-right"></i>&nbsp;&nbsp;&nbsp;Other relevant information   :  <b>'
													+ ele.comments + '</b>';
										}
										
										content += '<div class="content"><div class="sysmtom_header"><b>'
												+ ele.symptomName
												+ ',</b></div></div><div class="content" id="symptom'
												+ idx + '">';
										content += sym + "</label></div><br>"
												+ contentEnd;
									});
		
					$("#symptomsViewDataTable").html(content);
				}
	           }
			});
};

function labWorkGetItDone(e){
					var carepathApprLabworkId = e.getAttribute('accLabwork-apprId');
					var sbarId = e.getAttribute('signssymptomslabwork-sbarId');
					var sslId =  e.getAttribute('signssymptomslabwork-apprid');
					var cpCode = e.getAttribute('signssymptomslabwork-carepathcode');
					var queryParms = "carepathApprLabworkId="+carepathApprLabworkId+"&sbarId="+sbarId+"&sslId="+sslId+"&cpCode="+cpCode;
					$.ajax({
								type : "GET",
								url : contextPath+"/sbar/updateCarePathLabworkEntries?"+queryParms,
								success : function(data) {
									populateCarePathSuggestedLabworks($('#sbarPatientEpisodeId').val());
								}
});
					
};

function labWorkReject(e){
					var carepathRejLabworkId = e.getAttribute('accLabwork-rejId');
					var sbarId = e.getAttribute('signssymptomslabwork-sbarId');
					var sslId =  e.getAttribute('signssymptomslabwork-rejid');
					var cpCode = e.getAttribute('signssymptomslabwork-carepathcode');
					var queryParms = "carepathRejLabworkId="+carepathRejLabworkId+"&sbarId="+sbarId+"&sslId="+sslId+"&cpCode="+cpCode;
					$.ajax({
								type : "GET",
								url : contextPath+"/sbar/updateCarePathLabworkEntries?"+queryParms,
								success : function(data) {
									populateCarePathSuggestedLabworks($('#sbarPatientEpisodeId').val());
								}
							});
					
};

function getConditionsLabworkData(patientEpisodeId) {
	$.ajax({
				type : "GET",
				url : contextPath+"/sbar/getLabworkChangeInConditionsByPatientEpisodeId?patientEpisodeId="+ patientEpisodeId,
				success : function(data) {
					var content = "";
					$
							.each(
									data,
									function(idx, ele) {
										var sym = "<label><b>Labwork:</b></label>", contentEnd = "</div></dd>";
										$
												.each(
														ele.attributes,
														function(index, item) {
															sym += '<div class="row"><div class="medium-12 columns padding-lr"><i class="'
																	+ (item.attributeValue != null ? "fi-checkbox left"
																			: "fi-x left")
																	+ '"></i><label style="margin-top: 3px !important;">&nbsp;&nbsp;&nbsp;&nbsp;'
																	+ item.attributeName
																	+ ' : '
																	+ item.attributeValue
																	+ '</label></div></div>';
														});

										content += '<dd class="accordion-navigation"><a href="#labwork'
												+ idx
												+ '" onclick="patientToggle(this)"><div class="fi-play"></div>&nbsp;&nbsp;&nbsp;<b>Lab Work:&nbsp;&nbsp;&nbsp;</b>'
												+ ele.symptomName
												+ '<input type="button" onclick="removeLabworkRecord('
												+ ele.symptomId
												+ ','
												+ patientEpisodeId
												+ ')" value="Remove" class="button small right" style="margin: 0px; padding: 5px;"><input type="button" id="labworkEdit" onclick="editLabworkRecord('
												+ ele.symptomId
												+ ','
												+ patientEpisodeId
												+ ')" value="Edit" class="button small right" style="margin: 0px; padding: 5px;margin-right:8px "></a><div class="content row symptom_row" id="labwork'
												+ idx + '">';
										content += sym + "</label></div>"
												+ contentEnd;
									});
					$("#labworkDataTable").html(content);
				}
			});
};

function getSbarReleventSymtoms(patientEpisodeId) {
	$.ajax({
				type : "GET",
				url : contextPath+"/sbar/getSbarReleventSymtoms?patientEpisodeId="
						+ patientEpisodeId,
				
				success : function(data) {
					var content = "";
					var item = data[0];
					var i = 0;
					var hasCPData = "false";
					$
							.each(
									data.section,
									function(idx, ele) {
										hasCPData = "false";
										var sym = "", contentEnd = "</div></dd>";
										$
												.each(
														ele.symptoms.symptom,
														function(index, item) {
															var code;

															switch (item.datatype) {
															case "BOOLEAN":
																if (item.carePathAttrCode != null) {
																	if (item.value == "true") {
																		hasCPData = "true";
																		sym += '<div class="row"><input id="'
																				+ item.sbarAttrCode
																				+ '" data-id="'
																				+ item.carePathAttrCode
																				+ '" type="checkbox" disabled checked><label for="'
																				+ item.sbarAttrCode
																				+ '">'
																				+ item.name
																				+ '</label></div>';
																	} else {
																		sym += '<div class="row"><input id="'
																				+ item.sbarAttrCode
																				+ '" data-id="'
																				+ item.carePathAttrCode
																				+ '" type="checkbox" disabled><label for="'
																				+ item.sbarAttrCode
																				+ '">'
																				+ item.name
																				+ '</label></div>';

																	}
																} else {

																	if (item.value == "true") {
																		sym += '<div class="row"><input type="hidden" id="sbarSymtomList['
																				+ i
																				+ '].code" name="sbarSymtomList['
																				+ i
																				+ '].code" value="'
																				+ item.sbarAttrCode
																				+ '"><input id="sbarSymtomList['
																				+ i
																				+ '].boolValue" name="sbarSymtomList['
																				+ i
																				+ '].boolValue" data-id="'
																				+ item.carePathAttrCode
																				+ '" type="checkbox" checked><label for="'
																				+ item.sbarAttrCode
																				+ '">'
																				+ item.name
																				+ '</label></div>';
																	} else {
																		sym += '<div class="row"><input type="hidden" id="sbarSymtomList['
																				+ i
																				+ '].code" name="sbarSymtomList['
																				+ i
																				+ '].code" value="'
																				+ item.sbarAttrCode
																				+ '"><input id="sbarSymtomList['
																				+ i
																				+ '].boolValue" name="sbarSymtomList['
																				+ i
																				+ '].boolValue" data-id="'
																				+ item.carePathAttrCode
																				+ '" type="checkbox" ><label for="'
																				+ item.sbarAttrCode
																				+ '">'
																				+ item.name
																				+ '</label></div>';

																	}
																	i++;
																}
																break;
															case "VARCHAR":
																if (item.value == null) {
																	item.value = "";
																}
																sym += '<div class="row"><label>'
																		+ item.name
																		+ ':<input type="text" name="sbarSymtomList['
																		+ i
																		+ '].textValue" id="sbarSymtomList['
																		+ i
																		+ '].textValue" data-id="'
																		+ item.carePathAttrCode
																		+ '" value="'
																		+ item.value
																		+ '"/><input type="hidden" id="sbarSymtomList['
																		+ i
																		+ '].code" name="sbarSymtomList['
																		+ i
																		+ '].code" value="'
																		+ item.sbarAttrCode
																		+ '"></label></div>'
																i++;
																break;
															case "DATE":
																if (item.value == null) {
																	item.value = "";
																}
																sym += '<div class="row"> <div class="small-2 column inline "><label>'
																		+ item.name
																		+ ':</label></div><div class="small-3 column left" style="margin-left:-5%"><input class="snwrrepoteddatepicker" type="text" name="sbarSymtomList['
																		+ i
																		+ '].dateValue" id="sbarSymtomList['
																		+ i
																		+ '].dateValue" data-id="'
																		+ item.carePathAttrCode
																		+ '" value="'
																		+ item.value
																		+ '"/><input type="hidden" id="sbarSymtomList['
																		+ i
																		+ '].code" name="sbarSymtomList['
																		+ i
																		+ '].code" value="'
																		+ item.sbarAttrCode
																		+ '"></div></div>'
																i++;
																break;
															default:
															}

														});

										content += '<dd class="accordion-navigation"><a class="releventSysmtom" href="#releventSysmtom'
												+ idx
												+ '" onclick="patientToggle(this)" disabled="disabled"><div class='
												+ (hasCPData == "true" ? "fi-play"
														: "fi-lock")
												+ ' id="releventSysmtom'
												+ idx
												+ '-Lock" style="top: -2px;"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
												+ ele.name
												+ '</a>'
												+ (hasCPData == "true" ? ''
														: '<div class="notApplicable right"><input id="releventSysmtom'
																+ idx
																+ '" onchange="toggleAccordianAndLock(this)" style="margin-right: 5px;" type="checkbox" name="releventSysmtomCheckbox'
																+ idx
																+ '" '
																+ (hasCPData == "true" ? ""
																		: "checked='checked'")
																+ '>N/A</div>')
												+ '<div class="content row" id="releventSysmtom'
												+ idx
												+ '" style='
												+ (hasCPData == "true" ? ""
														: "display:none") + '>';

										content += sym + " " + contentEnd;
									});
					$("#sbarReleventSymptoms").html(content);
				}
			});
};
function getNurseNotes(patientEpisodeId) {
	$.ajax({
				type : "GET",
				url : contextPath+"/sbar/getSbarNotesByPatientEpisodeId?patientEpisodeId="
						+ patientEpisodeId + "&nursingStaffType=NURSE",
				//dataType : 'json',
				success : function(data) {
					var content = "";
					$.each(	data,function(idx, ele) {
						var sym = '<div class="row"><label><b>Notes:</b></label></div>', contentEnd = "</div></div></dd>";
							$.each(ele.nurseNotesList,function(index, item) {
								if (item.notes != "") {
										if (staffId == ele.staffId) {
												sym += '<div class="row"><div class="medium-8 columns padding-lr">'	+ item.notes+ '</div><div class="medium-3 columns padding-lr">'	+ item.createdDate+ '</div><div class="medium-1 columns padding-lr end" style="width:4%;"><span id="sbarNotesDel" onclick="sbarNotesDel(this)" class="fi-x right has-tip tip-bottom noradius" data-id='
																			+ item.sbarNotesId
																			+ ' title="Remove" style="cursor: pointer; border: medium none;" data-tooltip></span><span id="sbarNotesEdit" onclick="sbarNotesEdit(this)" class="fi-pencil open has-tip tip-bottom noradius" data-id='
																			+ item.sbarNotesId
																			+ ' style=" float: left; cursor: pointer; position: static; margin-top: 1px;" title="Edit" data-tooltip></span></div></div>';
										 } else {
																	sym += '<div class="row"><div class="medium-8 columns padding-lr">'
																			+ item.notes
																			+ '</div><div class="medium-3 columns padding-lr">'
																			+ item.createdDate
																			+ '</div></div>';
										 }

								 }
							});
						content += '<dd class="accordion-navigation"><a href="#notes'+ idx+ '" onclick="patientToggle(this)"><div class="fi-play" style="top: -2px;"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
												+ ele.staffName	+ '</a><div class="content symptom_row" id="notes'+ idx + '">';
										content += sym + contentEnd;
									});

					$("#nurseNotesDataTable").html(content);
				}
			});
};
function getPractitionerNotes(patientEpisodeId) {
	$.ajax({
				type : "GET",
				url : contextPath+"/sbar/getSbarNotesByPatientEpisodeId?patientEpisodeId="
						+ patientEpisodeId + "&nursingStaffType=DOCTOR",
				dataType : 'json',
				success : function(data) {
					var content = "";
					$.each(data,function(idx, ele) {
										var sym = '<div class="row"><label><b>Notes:</b></label></div>', contentEnd = "</div></dd>";
										$.each(
														ele.nurseNotesList,
														function(index, item) {
															if (item.notes != "")
																if (staffId == ele.staffId) {
																	sym += '<div class="row"><div class="medium-8 columns padding-lr">'
																			+ item.notes
																			+ '</div><div class="medium-3 columns padding-lr ">'
																			+ item.createdDate
																			+ '</div><div class="medium-1 columns padding-lr end" style="width:4%;"><span id="sbarNotesDel" onclick="sbarNotesDel(this)" class="fi-x right" data-id='
																			+ item.sbarNotesId
																			+ ' title="Remove" style="cursor: pointer;" data-tooltip></span><span id="sbarNotesEdit" onclick="sbarNotesEdit(this)" class="fi-pencil open" data-id='
																			+ item.sbarNotesId
																			+ ' title="Edit" style=" float: left; cursor: pointer; position: static;" data-tooltip></span></div></div>';
																} else {
																	sym += '<div class="row"><div class="medium-8 columns padding-lr">'
																			+ item.notes
																			+ '</div><div class="medium-3 columns padding-lr">'
																			+ item.createdDate
																			+ '</div></div>';
																}
														});
										content += '<dd class="accordion-navigation"><a href="#pnotes'
												+ idx
												+ '" onclick="patientToggle(this)"><div class="fi-play" style="top: -2px;"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
												+ ele.staffName
												+ '</a><div class="content providerVisitFlag" id="pnotes'
												+ idx + '">';
										content += sym + " " + contentEnd;
									});
					$("#PractitionerNotesDataTable").html(content);
				}
			});
};
function getMedicine(patientEpisodeId) {
	
	$.ajax({
		type : "GET",
		url : contextPath+"/sbar/getMedicine?patientEpisodeId=" + patientEpisodeId,
		dataType : 'json',
		success : function(data) {
			if(data.length!=0){
			var content = "<div style='margin-left:10%;'><label style='color:orange; font-size:18px;'>Prescribed Medicine</label>";
			
			$.each(data,function(idx, ele) {
			content += '<div class="row"><div class="medium-8 columns" style="left:10%"><div class="medium-1 columns"><img src="'+contextPath+'/img/13032.png"/></div>'+ ele.medicineName+ '</div><div class="medium-2 columns"><span id="prescribePencilSpan" class="fi-pencil right" onclick=\'editMedicine('+ ele.id+ ',"'+ ele.medicineName+'")\'  style="top:4px  ;left:-5px" title="edit" ></span></div><div class="medium-2 columns"><span id="deletetrashId" class="fi-trash small"  style="top: 4px; margin-right: 11px;" title="edit" onclick="removeMedicine('+ ele.id+')"></span></div></div>';
		});
			$("#medicineviewtable").html(content);
		}}
	});
};


function getMedicineNames(){
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	$.ajax({
		type : "GET",
		url : contextPath+"/sbar/getMedicine?patientEpisodeId="
				+ patientEpisodeId,
		dataType : 'json',
		success : function(data) {
			if(data.length!=0){
			var content = "<div style='margin-left:10%;'><label style='color:orange; font-size:18px;'>Prescribed Medicine</label></div><div  style='overflow:auto;  margin-top:5%;  max-height:247px;'>";
			$.each(data,function(idx, ele) {

					/*	content +='<div class="medium-4 columns"></div><div class="content"><div class="medium-8 columns padding-lr">'+ ele.medicineName+'</div></div>';*/
						content +='<div class="row"><div class="medium-8 columns padding-lr" ><div class="medium-1 columns" style="left:20%"><img src="'+contextPath+'/img/13032.png"/></div><div class="medium-7 columns">'+ ele.medicineName+'</div></div></div>';

							});
			$("#medicineviewOnlytable").html(content+ '</div>');
		}
		}	
	});
};
	

function editMedicine(prescribeMedicineId, medicine) {
	$('#conditionPopup').empty();
	$('#conditionPopup').append('<div id="medicineValueBlockEdit"></div>');
	
	var content = '<div id="update-medicine-Errors">';
	content += '</div>';
	content += '<div class="medicineAdded">MedicineName:<input type="text" id="medicine"  name="medicineName" value='+ medicine+ '></div><div class="row right" style="margin-top: 10px;"><button id="updateMedicine" value="Save"  onclick="updateMedicine('+ prescribeMedicineId+ ')"  style="padding: 5px 15px;margin-bottom: 0 !important;" class="small right">Update</button><button id="medicineCancel" value="Cancel" name="Cancel" onclick="openJueryPopupClose('
			+ "'#coditionPopup'"
			+ ')" style="padding: 5px 15px;margin-right: 20px;margin-bottom: 0 !important;" class="small right">Cancel</button></div>';
	$("#medicineValueBlockEdit").html(content);
	openJueryPopup("#conditionPopup");
	$("#conditionPopup").dialog('option', 'title', 'PrescribeMedicineEdit');
	$("#conditionPopup").dialog('option', 'resizable', false);
	$("#conditionPopup").dialog('option', 'width', '50%');

};

function updateMedicine(prescribeMedicineId) {
	var error = false;
	var errorMsg = "";
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	var name = $('#medicine').val();
	if(name == ''){
		errorMsg += "please enter medicineName<BR>";
		error=true;
	}
	if(error)
		{
		$("#update-medicine-Errors").show().html(errorMsg);
		return false;
		}
	else{
	$.ajax({
		type : "GET",
		url : contextPath+'/sbar/updateMedicine?prescribeMedicineId='
				+ prescribeMedicineId + '&medicineName=' + name,
		
		success : function(data) {
			openJueryPopupClose("#conditionPopup");
			getMedicine(patientEpisodeId);

		}
	});
	}
};

function removeMedicine(prescribeMedicineId) {
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	$.ajax({
		type : "GET",
		url : contextPath+'/sbar/removeMedicine?prescribeMedicineId='
				+ prescribeMedicineId,
		
		success : function(data) {
			getMedicine(patientEpisodeId);
		}
	});
};
function addLabWorkForSymptom(){
	   addLaborders();
		$($('.tabs.vertical > dd#sbar_LabWorksTab').find('a')).click();

};
function addLaborders(){
	var ele = $("#conditionPopup");
	openJueryPopup('#conditionPopup');
	$("#conditionPopup").dialog('option', 'title', 'Tests');
	$("#conditionPopup").dialog('option', 'resizable', false);
	$("#conditionPopup").dialog('option', 'width', '50%');
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	ele.empty();
	$.ajax({
				url : contextPath+'/sbar/showLabworksNotInCarePathLabworks?patientEpisodeId='+patientEpisodeId,
				type : 'get',
				success : function(data) {
					if(data.status == "success"){
					var innerHtmlText = '<div class="symptomAdded"><label class="left">The Lab work record is/are: &nbsp;&nbsp;&nbsp;&nbsp;</label><select id="symptom" name="symptom" onChange="approveLabwork(this)"><option value="0">-- Select --</option>';
					var len = data.list.length;
					for (var i = 0; i < len; i++) {
						innerHtmlText += '<option value="'+ data.list[i].id	+ '">'+ data.list[i].signsSymptomsLabworkName+ '</option>';
					}
					innerHtmlText += '</select><span id="addLabworkasApproved"></span>';
					ele.html(innerHtmlText);
				}else{
					ele.html(data.status);
				}
			}
			});
};
function addNurseNotes(){
	var ele = $("#conditionPopup");
	openJueryPopup('#conditionPopup');
	$("#conditionPopup").dialog('option', 'title', 'Nurse Notes');
	ele.empty();
	var innerHtmlText = '<div class="symptomAdded"><textarea id="nurseNotes" name="textarea" rows="4" cols="50"></textarea></div><div class="row" style="margin-top: 10px;"><button id="saveNourseNotes" value="Save"  onclick="saveNurseNotes()"  style="padding: 5px 15px;margin-bottom: 0 !important;" class="small right">Save</button><button id="nourseNotesCancel" value="Cancel" name="Cancel" onclick="openJueryPopupClose('
			+ "'#coditionPopup'"
			+ ')" style="padding: 5px 15px;margin-right: 20px;margin-bottom: 0 !important;" class="small right">Cancel</button></div>';
	ele.html(innerHtmlText);

};
function addPractitionerNotes() {
			var ele = $("#conditionPopup");
			openJueryPopup('#conditionPopup');
			$("#conditionPopup").dialog('option', 'title', 'Practitioner Notes');
			ele.empty();
			var innerHtmlText = '<div class="symptomAdded"><textarea id="practitionerNotes" name="textarea" rows="4" cols="50"></textarea></div><div class="row right" style="margin-top: 10px;"><button id="savePractitionerNotes" value="Save"  onclick="savePractitionerNotes()"  style="padding: 5px 15px;margin-bottom: 0 !important;" class="small right">Save</button><button id="practitionerNotesCancel" value="Cancel" name="Cancel" onclick="openJueryPopupClose('
					+ "'#coditionPopup'"
					+ ')" style="padding: 5px 15px;margin-right: 20px;margin-bottom: 0 !important;" class="small right">Cancel</button></div>';
			ele.html(innerHtmlText);

};
function sbarNotesEdit(e){
   var notesId = e.getAttribute("data-id");
	$.ajax({
				url : contextPath+'/sbar/getSbarNotesByNotesId?notesId='+ notesId,
				type : 'get',
				success : function(data) {
					$('#conditionPopup').empty();
					$('#conditionPopup').html('<div class="symptomAdded"><textarea id="editNotes"  name="textarea" rows="4" cols="50" z-index:1000></textarea></div><div class="row right" style="margin-top: 10px;"><button id="editNourseNotes" value="Save"  onclick="editSbarNotes('
											+ notesId+ ')"  style="padding: 5px 15px;margin-bottom: 0 !important;" class="small right">Save</button><button id="notesCancel" value="Cancel" name="Cancel" onclick="openJueryPopupClose('+ "'#coditionPopup'"+ ')" style="padding: 5px 15px;margin-right: 20px;margin-bottom: 0 !important;" class="small right">Cancel</button></div>');
					$('#editNotes').text(data);
					openJueryPopup("#conditionPopup");
					$("#conditionPopup").dialog('option','title', 'Edit Notes');
				}
			});
};


var searchNames = [];
var residentNames = [];
var searchMsges = [];
$(function() { 
	$(document).foundation({
		abide : {
			patterns : {
				passWordPattern : /^(?=.*?[0-9].*?[0-9])(?=.*?[A-Z]).{8,}$/
			}
		}
	});
	$(document).on({
		ajaxStart : function() {
			$(".loading").show();
		},
		ajaxStop : function() {
			$(".loading").hide();
		}
	});
 	$('body').append('<div class="loading" style="display: none"><img src="'+contextPath+'/img/loader.gif" class="ajax-loader"/></div>').trigger('create');

	$.ajax({
		url : contextPath+'/stopAndWatch/getPatientNames?residentName=',
		type : 'get',
		success : function(data) {
			$.each(data, function(key, value) {
				var label = {
					"label" : value,
					"value" : value,
					"id" : key
				};
				searchNames.push(label);
			});
		}

	});
	$("#patientSearch").autocomplete({
		source : searchNames,
		autoSelectFirst: true,
		select : function(event, ui) {
			minLength: 0;
			var pName= ui.item.value;
			
			$.ajax({
				url : contextPath+'/getSearchNames?searchId=' + pName,
				type : 'get',
				success : function(result) {
					location.reload();
					// $("#searchId").val('');
				}
			});
		}
	})
	.keydown(function(e){
		if(e.keyCode == 13){
		$.ajax({
				url : contextPath+'/getSearchNames?searchId=' + $("#patientSearch").val(),
			type : 'get',
				success : function(result) {
					location.reload();
			}
		});
		}
	});
	
	//function ValidateStopAndWatch(){
	$("#StopAndWatchModalSub").click(function() {
		$("#stopAndWatchErrors span").hide();
		var checkStatus = false;
		var errorMsg = "";
		var error = false;
		var isExists = false;
		var isNotInSbar = false;
		var isresident = $("#residentName").val();
		var snwOtherSymptom = $("#snwOtherSymptom").val();
		$.each(searchNames, function(key, value) {
			if (isresident.toLowerCase().trim()== value.value.toLowerCase().trim()) {
				isExists = true;
				var temp = value.value;
				$.each(residentNames, function(key, value) {
					if (temp.toLowerCase() == value.value.toLowerCase()) {

						isNotInSbar = true;
					}

				});

			}

		});

		$("#stopAndWatchForm1 input:checkbox").each(function(c) {
			var isChecked = $(this).is(":checked");
			if (isChecked) {
				checkStatus = isChecked;
			}
		});

		if (!checkStatus) {
			errorMsg += "Select Atleast One Symptom<Br>";
			error = true;
		} else if ($("#snwOther1").is(':checked')) {
			if (snwOtherSymptom == '')

			{
				errorMsg += "please enter OtherSymptom <BR>";
				error = true;
			}
		}
		if (isresident == '') {
			errorMsg += "Resident Name<BR>";
			error = true;
		} else if (!isExists) {

			errorMsg += "Invalid Resident<BR>";
			error = true;
		} else if (!isNotInSbar) {
			errorMsg += "Resident in sbar<BR>";
			error = true;
		}
		if ($("#guestName").length) {
			if ($("#guestName").val() == '') {
				errorMsg += "Guest Name<BR>";
				error = true;
			}
		} else {
			if ($("#yourName").val() == '') {
				errorMsg += "Your Name<BR>";
				error = true;
			}
		}
		if($(this).val()=="Update"&&responsibility == "NURSE" && $("#facilityStafResponse").val().length == 0){
			errorMsg += "Nurse Response should be described <BR>";
			error = true;
		}
		if (error) {

			$("#stopAndWatchErrors span").show().html(errorMsg);
			return false;
		}

	});
	
	$("#generate").click(function() {
		
		$("#snwOtherDiv").hide();
		$("#snwOtherSymptom").hide();
		$('#InvalidateStopAndWatch').hide();
		if (fromGuest != "noFacilityId") {
			$('#InvalidateStopAndWatch').hide();
			$("#residentId").val("");
			$("#guestName").val("");
			$("#stopAndWatchForm1 input:checkbox").prop('checked', false);
			$("#reportedToFacilityStaffId").val("");
			$("#reportedDateAndTime").val("");
			$("#stopAndWatchErrors span").hide();
			$("#StopAndWatchModal").foundation('reveal', 'open');
			
		}
	});
	
	$.ajax({
		url : contextPath+'/stopAndWatch/getPatientNamesForGuest?residentName=',
		type : 'get',

		success : function(data) {

			$.each(data, function(key, value) {
				var label = {
					"label" : value,
					"value" : value,
					"id" : key
				};
				residentNames.push(label);
			});
		}
	});
	$("#residentName").autocomplete({
		source : searchNames,
		select : function(event, ui) {
			minLength: 3, $("#residentId").val(ui.item.id);
		}
	});

	
	$(".symptomsDateClass").datetimepicker({
		controlType : 'select',
		clickInput : false,
		showOn : "both",
		buttonImage : "img/calendar_icon.jpg",
		buttonImageOnly : true,
		timeFormat : 'hh:mm:ss tt',
		changeMonth : true,
		changeYear : true,
		yearRange : "-100:+0",
		maxDate : new Date('mm/dd/yy')
	});
	

	$('.sbar-close-reveal-modal').click(function() {
	  $("#optionsMenuModal").foundation('reveal','close');
	});
	$('#carePathModal').bind('dialogclose', function(event) {
		dynamicCarePathOnPatient();
	});

	$("#forgotPassword").click(function() {
		$('#currentPassword').val('');
		$('#newpassword').val('');
		$('#confirmPassword').val('');
		$('#ChangePasswordModal').foundation('reveal', 'open');
	});
		
	$('.ul_alerts').slimscroll({
		color : 'rgb(130, 107, 133)',
		size : '5px',
		opacity : '1',
		height : 'auto'
	});
	$('.ul_anno').slimscroll({
		color : 'rgb(183, 136, 141)',
		size : '5px',
		opacity : '1',
		height : 'auto'
	});

	$("ul#one").sortable({
		revert : true,
		opacity : 0.6,
		cursor : 'move',
		scrollSensitivity : 40,
		cursor : 'crosshair',
		connectWith : 'ul#two ,ul#three',
		// items: '.bottom_border_green',
		placeholder : "ui-state-highlight",
		helper : function(e, li) {
			this.copyHelper = li.clone().insertAfter(li);
			$(this).data('copied', false);
			return li.clone();
		},
		stop : function(e, ui) {
			var copied = $(this).data('copied');
			if (!copied) {
				this.copyHelper.remove();
			}
			this.copyHelper = null;
		}
	});

	$("ul#two")	.sortable(
	{
						revert : true,
						opacity : 0.6,
						cursor : 'move',
						cursor : 'crosshair',
						placeholder : "ui-state-highlight",
						scrollSensitivity : 40,
						connectWith : 'ul#three',
						// items: '.bottom_border_green',
						update : function(event, ui) {
							if (!$(ui.item).hasClass("sbarRised")&&!$(ui.item).hasClass("acuteCareRaised")) {
								var target = "", patient = "";
								target = ui.item[0].dataset.target; // $(this).data().uiSortable.items[0].item[0].dataset.target;
								patient = ui.item[0].dataset.patient; // $(this).data().uiSortable.items[0].item[0].dataset.patient;
								$patientInfoArr = patient.split('=');
								$('#InvalidateStopAndWatch').hide();
								if (target == "one") {
									var movingPatinetItem = $(this).find("li[id!=stopAndWatchPatient]");
									$(movingPatinetItem).attr("data-target","two");
									var movingPatient = $patientInfoArr[1]+ "<span class='li_doc'>RN Ms.Sally</span><span class='li_time'>35m</span><span id='stop-watch' class='fi-eye has-tip tip-bottom noradius' data-tooltip title='View'></span><span id='stop-watch-add' class='fi-plus has-tip tip-bottom noradius' data-tooltip title='Add'></span>"
									$(movingPatinetItem).html(movingPatient);
									getPatientHistory($patientInfoArr[0]);
									$("#stopAndWatchForm1 input:checkbox").removeAttr('disabled');
									$("#stopAndWatchForm1 input:checkbox").prop('checked', false);
									$("#snwOther").prop('checked', false);
									$("#snwOtherSymptom").val('').hide();
									$('#residentName').val($patientInfoArr[1]);
									$('#residentId').val($patientInfoArr[0]);
									$('#residentName').attr("readonly", true);
									$('#yourName').val(staffName);
									$('#yourName').attr("readonly", true);
									$('#snwOtherSymptom').attr("hidden", true);
									$('#snwadd').show();
									$('#snwedit').hide();
									$('#StopAndWatchModalSub').val("Submit");
									$('#reportedToFacilityStaffId').val("");
									// Rameshwari
									if (staffRole == "ADMIN") {
										$("#snwOtherDiv").hide();
										$("#nurseId").prop('disabled','disabled');
										$('#facilityStafResponse').attr("readonly", true);
										$("#reportedToFacilityStaffId").prop('enabled', 'enabled');
										$("#responseDateAndTime").datepicker("option", "disabled", true);
										$('#nurseId').val(staffId);
									} else if (staffRole == "RN"
											|| staffRole == "LPN") {
										$("#reportedToFacilityStaffId").prop('enabled', 'enabled');
										$("#responseDateAndTime").datepicker("option", "disabled", false);
										$("#reportedDateAndTime").datepicker("option", "enabled", true);
										$('#reportedToFacilityStaffId').val(staffId);
										var dNow = new Date();
										var utc = new Date(dNow.getTime()+ dNow.getTimezoneOffset());
										var utcdate = (utc.getMonth() + 1)
												+ '/' + utc.getDate() + '/'
												+ utc.getFullYear() + ' '
												+ utc.getHours() + ':'
												+ utc.getMinutes() + ':'
												+ utc.getSeconds();
										$('#reportedDateAndTime').val(utcdate);
										$('#nurseId').val(staffId);
										$("#nurseId").prop('disabled', false);
									} else {
										$("#snwOtherDiv").hide();
										$("#nurseId").val('');
										$("#nurseId").prop('disabled','disabled');
										$('#facilityStafResponse').attr("readonly", true);
										$("#reportedToFacilityStaffId").prop('enabled', 'enabled');
										$("#responseDateAndTime").datepicker("option", "disabled", true);

									}
									$("#responseDateAndTime").val("");
									$("#reportedDateAndTime").attr("hidden",false);
									$('#stopAndWatchHistoryId').val("");
									$('#stopAndWatchForm1').show();
									if ($(ui.item).hasClass("sbarNotRised")	&& !$(ui.item).hasClass("bottom_border_red")) {
										$('#StopAndWatchModal').foundation('reveal', 'open');
									}
									$('#StopAndWatchModal').bind('closed',function() {
												$(ui.item).remove();
												createDropZone();
											});
								}
							}
						},
						receive : function(e, ui) {
							if (ui.item[0].className
									.indexOf("bottom_border_red") == 0) {
								$('#message').text("Admission Incomplete, Please fill mandatory fields").css("color", "orange");
								$('#messageModel').foundation('reveal', 'open');
								$(ui.item).remove();
							}
							if (ui.item[0].className
									.indexOf("bottom_border_yellow") == 0) {
								$('#message').text("Resident sent to Hospital, waiting for response").css("color", "orange");
								$('#messageModel').foundation('reveal', 'open');
								$(ui.item).remove();
							}
							if ($(ui.item).hasClass("sbarRised")) {
								$('#message').text("Clinical Assessment in-progress, can not create STOP AND WATCH").css("color", "orange");
								$('#messageModel').foundation('reveal', 'open');
								$(ui.item).remove();
							} else 
							if ($(ui.item).hasClass("acuteCareRaised")) {
								$('#message').text(	"Acute Care Transfer in-progress, can not create STOP AND WATCH")
										.css("color", "orange");
								$('#messageModel').foundation('reveal', 'open');
								$(ui.item).remove();
							}
							ui.sender.data('copied', true);
							createDropZone();
						},
						stop : function(e, ui) {
						}
					});

	$("ul#three")
			.sortable(
					{
						revert : true,
						opacity : 0.6,
						cursor : 'move',
						scrollSensitivity : 40,
						placeholder : "ui-state-highlight",
						cursor : 'crosshair',
						connectWith : 'ul#four',
						items : '.bottom_border_green',

						update : function(event, ui) {
							var target = "", patient = "";
							target = ui.item[0].dataset.target;
							patient = ui.item[0].dataset.patient;
							$patientInfoArr = patient.split('=');
							var noResponse=false;
						
							if (target == "two"|| target == "one") {
								var patientEpisodeId, currentHtml = ui.item
										.html();
								var movingPatinetItem = $(this).find(
										"li[data-target=two]");
								$(movingPatinetItem).attr("id", "");
								$(movingPatinetItem).attr("data-patient",
										patient);
								$(movingPatinetItem).attr("data-target",
										"three");
								//
								   $.ajax({
									  url : contextPath+'/stopAndWatch/hasResponse?patientId=' + $patientInfoArr[0],
									  type : 'get',
									
									  success : function(data) {
										  
										  if(target=="one"&&staffRole != "LPN" && staffRole != "RN"){
                                              $('#message').text(
                                              "No privilege to start Clinical Assessment").css(
                                              "color", "orange");
                                              $('#messageModel').foundation('reveal', 'open');
                                              $(ui.item).remove();
                                          }else
									 
										if(target == "one"&&data.stopAndWatchFlag&&!data.hasRespnse){
											
											$('#message').text("The assigned nurse must respond in the S&W request before  patient move to Clinical Assessment").css("color", "orange");
											$('#messageModel').foundation('reveal', 'open');
											$(ui.item).remove();
										}else  if (target == "one"&&$(ui.item).hasClass("sbarRised")) {
											$('#message').text("Clinical Assessment  in-progress").css("color", "orange");
											$('#messageModel').foundation('reveal', 'open');
											$(ui.item).remove();
										} else
										if (target == "one"&&$(ui.item).hasClass("acuteCareRaised")) {
											$('#message').text(	"Acute Care Transfer in-progress, can not start Clinical Assessment")
													.css("color", "orange");
											$('#messageModel').foundation('reveal', 'open');
											$(ui.item).remove();
										}
								       else{
							     	      $.ajax({
											url : contextPath+'/sbar/getActivePatientEpisodeId?patientId='
													+ $patientInfoArr[0],
											type : 'get',
											global : false,
											success : function(data) {
												$patientInfo = data.split('=');
												patientEpisodeId = $patientInfo[0];
												$('#sbarPatientEpisodeId').val(
														patientEpisodeId);
												$('#sbarTitle').text(
														$patientInfo[1]
																+ "-Clinical Assessment");
												var movingPatient = $patientInfoArr[1]
														+ "<span id='sbar-add' class='fi-pencil has-tip tip-bottom noradius' data-tooltip title='Edit'></span>"
												$(movingPatinetItem).html(
														movingPatient);
												if (!$(ui.item).hasClass(
														"bottom_border_red") 
														&& staffRole != "CNA") {
													if($(ui.item).hasClass("bottom_border_yellow")){
														$('#message').text("Resident sent to Hospital, waiting for response").css("color", "orange");
														$('#messageModel').foundation('reveal', 'open');
														$(ui.item).remove();
													}else{
													$("#dialog-confirm").html('<center><p  class="confirm-dialog">Would you like to move patient to Clinical Assessment ?</p></center>');

													$("#dialog-confirm").dialog({
														resizable : false,
														modal : true,
														height : 250,
														width : 500,
														close: function( ) {
															if (target == "two"){
															ui.item[0].dataset.target = target;
															ui.item.html(currentHtml);
															$(ui.sender).sortable('cancel');
															createDropZone();
															
															}else{
																$(ui.item).remove();
															}
															$(this).dialog("close");
														},
														buttons : {
															"Yes" : function() {
            													$.ajax({
																	url : contextPath+'/sbar/createIntialSbar?patientEpisodeId='+ patientEpisodeId,
																	type : 'get',
																	success : function(	data) {
																		location.reload();
																	}
																});

															},
															"No" : function() {
																if (target == "two"){
																ui.item[0].dataset.target = target;
																ui.item.html(currentHtml);
																$(ui.sender).sortable('cancel');
																createDropZone();
																$(this).dialog("close");
																}else{
																	$(ui.item).remove();
																	
																	$(this).dialog("close");
																}
															}
														}
														
														
													//    	  
													}).dialog("open");
												}
													
//													

												} else {
													ui.item[0].dataset.target = target;
													ui.item.html(currentHtml);
													$(ui.sender).sortable(
															'cancel');
													createDropZone();
												}
												
											}
										});
							       }
							    }//sucess colse
							  });//ajax(1) closing
							}
						},
						receive : function(e, ui) {
							target = ui.item[0].dataset.target;
							var currentHtml = ui.item.html();
							if (ui.item[0].className
									.indexOf("bottom_border_red") == 0) {
								$('#message')
										.text(
												"The assigned nurse must respond in the S&W request before  patient move to Clinical Assessment ")
										.css("color", "orange");
								$('#messageModel').foundation('reveal', 'open');
							}
							ui.sender.data('copied', true);
							createDropZone();
						},
					});

	$("ul#four")
			.sortable(
					{
						placeholder : "ui-state-highlight",
						update : function(event, ui) {
							var target = "", patient = ""; patientEpisodeId = "";
							target = ui.item[0].dataset.target; // $(this).data().uiSortable.items[0].item[0].dataset.target;
							patient = ui.item[0].dataset.patient; // $(this).data().uiSortable.items[0].item[0].dataset.patient;
							patientEpisodeId = ui.item[0].dataset.episodeid;
							console.log("patientEpisodeId"+patientEpisodeId);
							$patientId = patient.split('=')[0];
							if (target == "three"&&staffRole != "ADMIN" && staffRole != "CNA" &&staffRole != "SUPER_ADMIN") {
								$("#dialog-confirm").html('<center><p  class="confirm-dialog" style="font-size:20px !important">Would you like to move the patient to Hospitlal ?</p></center><div class="large-12 columns"><center><div class="large-12 columns emptyselectvalidation" hidden="hidden"><label></label></div></center><div class="large-4 columns"><span id="transferTypeLable"><i class="fi-star small"></i>Transfer type</span></div><div class="large-4 columns end"><select class="plannedselect"><option value="0">--select--</option><option value="planned">Planned</option><option value="unplanned">Unplanned</option></select></div></div>');
								$("#dialog-confirm").dialog({
									resizable : false,
									modal : true,
									height : 250,
									width : 500,
									buttons : {
										"Yes" : function() {
											moveToAcuteCare(patientEpisodeId);

										},
										"No" : function() {
											if (target == "three"){
												ui.item[0].dataset.target = target;
												$(ui.sender).sortable('cancel');
												createDropZone();
												$(this).dialog("close");
												}
											}
									}
								});
							}
						},
						receive : function(e, ui) {
							ui.sender.data('copied', true);
							createDropZone();
						},
					});

	$(".col_body ul li").disableSelection();

	function createDropZone() {
		$('.col_body ul').each(function(i) {
			if (i > 0) {
				if ($(this).find('li').length == 0) {
					$(this).parent('div').addClass('ui-sortable_parent');
					$(this).addClass('ui-sortable_empty');
				} else {
					$(this).parent('div').removeClass('ui-sortable_parent');
					$(this).removeClass('ui-sortable_empty');
				}
			}
		});
	}
	;

	$(document).on('click', '#snwOther1', function() {
		if ($("#snwOther1").is(':checked')) {
			$("#snwOtherSymptom").show();
		} else {
			$("#snwOtherSymptom").hide();
		}
	});

	if (firstTimeUserFlag == 'true') {
		$('#currentPassword').val('');
		$('#newpassword').val('');
		$('#confirmPassword').val('');
		$("#passCancel").hide();
		$('#ChangePasswordModal').foundation('reveal', 'open', {
			close_on_background_click : false,
			close_on_esc : false
		});
	}

	if (showFacilitySelect == 'true') {

		$('#ShowFacilitySelectModal').foundation('reveal', 'open', {
			close_on_background_click : false,
			close_on_esc : false
		});
	}

	$("a#passchangelink").click(function() {
		$('#ChangePasswordModal a.close-reveal-modal').text('');
		$('#currentPassword').val('');
		$('#newpassword').val('');
		$('#confirmPassword').val('');
		$('#ChangePasswordModal').foundation('reveal', 'open');
	});

	$("#passCancel").click(function() {
		$('#ChangePasswordModal').foundation('reveal', 'close');
	});

	$('#ChangePasswordForm').on('invalid.fndtn.abide', function() {
		var invalid_fields = $(this).find('[data-invalid]');

	}).on('valid.fndtn.abide', function() {
		$('#ChangePasswordModal').foundation('reveal', 'close');
	});

	$("a#registrationlink").click(function(e) {
		$('#RegistrationModal').foundation('reveal', 'open');
	});

	if (fromPage == 'registration'
			&& (isRoleAdmin == 'true' || isRoleSuperAdmin == 'true')) {
		loadManageUsersDialog('');
	}

	$("#StopAndWatchModalCancel ,#snw-close-reveal-modal").click(
			function closeSnwmodel() {
		$("#stopAndWatchForm1 input:checkbox").removeAttr('disabled');
		$("#stopAndWatchForm1 input:checkbox").prop('checked', false);
		$("#reportedToFacilityStaffId").removeAttr('disabled');
		$("#reportedToFacilityStaffId").val("");
		$("#nurseId").val("");
		$("#facilityStafResponse").val("");
		$("#yourName").attr("readonly", false);
		$("#responseDateAndTime").val('');
		$("#StopAndWatchModal").foundation('reveal', 'close');
	});

	$('#RegistrationForm').on('invalid.fndtn.abide', function() {
		var invalid_fields = $(this).find('[data-invalid]');
	}).on('valid.fndtn.abide', function() {
		// $('#RegistrationModal').foundation('reveal', 'close');
	});


	if (formFlag == 'stopAndWatch') {
		$('#StopAndWatchModal').foundation('reveal', 'open');
	}

	$("#newRegistrationModal").click(function(e) {
		cleanNewRegistrationModal();
	});

	/** * POP UP STUFF - END** */

	/** * DATE PICKER** */
	$(".snwrepoteddatepicker").datepicker(
			{
				showOn : "both",
				buttonImage : "img/calendar_icon.jpg",
				buttonImageOnly : true,
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
					$('.snwrepoteddatepicker').val(datetext);
				}
			});

	$(".sbarrepoteddatepicker").datepicker({
		showOn : "both",
		buttonImage : "img/calendar_icon.jpg",
		buttonImageOnly : true,
		timeFormat : 'hh:mm:ss tt',
		defaultDate : new Date(),
		changeMonth : true,
		changeYear : true,
		yearRange : "-100:+0",
		dateFormat : "mm/dd/yy",

	});

	$(".snwresponsedatepicker").datepicker(
			{
				showOn : "both",
				buttonImage : "img/calendar_icon.jpg",
				buttonImageOnly : true,

				changeMonth : true,
				changeYear : true,
				yearRange : "-100:+0",
				dateFormat : "mm/dd/yy",
				maxDate : new Date(),
				onSelect : function(datetext) {
					var d = new Date(); // for now
					datetext = datetext + " " + d.getHours() + ": "
							+ d.getMinutes() + ": " + d.getSeconds();
					$('.snwresponsedatepicker').val(datetext);
				}
			});

	/** * AJAX CALL** */
	$("#reportedToFacilityStaffId").blur(
			function() {

				var repotedFacilityId = $("#reportedToFacilityStaffId").val();
				if (repotedFacilityId != '') {
					var dNow = new Date();
					var utc = new Date(dNow.getTime()
							+ dNow.getTimezoneOffset());
					var utcdate = (utc.getMonth() + 1) + '/' + utc.getDate()
							+ '/' + utc.getFullYear() + ' ' + utc.getHours()
							+ ':' + utc.getMinutes() + ':' + utc.getSeconds();
					$('#reportedDateAndTime').val(utcdate);
				} else {
					$("#reportedDateAndTime").val('');

				}

			});
	// Modified
	$("#facilityStafResponse").blur(
			function() {
				var response = $("#facilityStafResponse").val();
				var facilityStafResponse = $("#facilityStafResponse").val();
				if (facilityStafResponse != '') {
					var dNow = new Date();
					var utc = new Date(dNow.getTime()
							+ dNow.getTimezoneOffset());
					var utcdate = (utc.getMonth() + 1) + '/' + utc.getDate()
							+ '/' + utc.getFullYear() + ' ' + utc.getHours()
							+ ':' + utc.getMinutes() + ':' + utc.getSeconds();
					$('#responseDateAndTime').val(utcdate);

				} else {
					$("#responseDateAndTime").val('');

				}

			});

	$("#reportedToFacilityStaffId").change(
			function() {

				var reportedToFacilityStaffId = $(
						"#reportedToFacilityStaffId option:selected").text();

				if (reportedToFacilityStaffId != '') {

					var dNow = new Date();
					var utc = new Date(dNow.getTime()
							+ dNow.getTimezoneOffset());
					var utcdate = (utc.getMonth() + 1) + '/' + utc.getDate()
							+ '/' + utc.getFullYear() + ' ' + utc.getHours()
							+ ':' + utc.getMinutes() + ':' + utc.getSeconds();
					$('#reportedDateAndTime').val(utcdate);

				} else {
					$('#reportedDateAndTime').val('');
				}
			});


	$("#lastName, #firstName").blur(
			function() {
				val1 = $("#firstName").val();
				val2 = $("#lastName").val();
				val3 = $("#operation").val();
				if (val1 != '' && val2 != '' && val3 == 'new') {
					$.ajax({
						url : contextPath+'/mangeUser/generateUser?firstName='
								+ val1 + "&lastName=" + val2,
						type : 'get',
						success : function(data) {
							$("#userName").val(data);
						}
					});
				}
			});

	$('.col_body ul').each(function(i) {
		if (i > 0) {
			if ($(this).find('li').length == 0) {
				$(this).parent('div').addClass('ui-sortable_parent');
				$(this).addClass('ui-sortable_empty');
			}
		}
	});

	$('.col_body ul').click(function() {
		if ($(this).find('li').length != 0) {
			$(this).parent('div').removeClass('ui-sortable_parent');
			$(this).removeClass('ui-sortable_empty');
		}
	});

	$('input[type="radio"]').bind(
			'click',
			function() {
				$('input[data-radio="' + $(this).attr('data-radio') + '"]')
						.not($(this)).prop('checked', false);
			});
	

	if (errorIn == 'RegistrationModalError') {
		var selectedRole = $("#staffRoleId option:selected").text();
		var selectedAsignDoc = $("#Asigndoc option:selected").val();
		if (selectedRole == "NP" || selectedRole == "PA") {
			$("#Asigndoc").show();
				if(selectedAsignDoc != 0){
				$('#docRoleId option:eq("'+selectedAsignDoc+'")').val(selectedAsignDoc);
				}else if(selectedAsignDoc != 0){
					$('#docRoleId option:eq(0)').val('');
				}
		} else {
			$("#Asigndoc").hide();
			$('#docRoleId option:eq(0)').val('0');
		}
		var operation = $("#operation").val();
		if (operation == 'new') {
			$("#regSubmit").val("Register");
			$("#activateDeactivateId").hide();
		} else if (operation == 'edit') {
			if (staffRole != "ADMIN" && staffRole.replace(" ","_") != "SUPER_ADMIN") {
				$("#firstName").attr("readonly", true);
				$("#lastName").attr("readonly", true);
				$("#workNumber").attr("readonly", true).css('pointer-events', 'none');
				$("#workEmail").attr("readonly", true);
				$("#staffRoleId").css('pointer-events', 'none');
				$("#docRoleId").css('pointer-events', 'none');
				$("#activateDeactivateId").hide();
			}
			$("#regSubmit").val("Update");
		} else if (operation == 'add') {
			$("#regSubmit").val("Add To Facility");
		}
		$("#mobileNo").mask("(999) 999-9999");
		$("#workNumber").mask("(999) 999-9999 ? ext:999");
		$("#zipcode").mask("99999 - ? 0000");
		$('#RegistrationModal').foundation('reveal', 'open');
		//$("#resett").hide();
	}
	
		populateMessagesList(currentPage, $("#msgesSearchId").val());
	
         var i=0;
	     if(i==0){
		      populateAlertsList(alert);
		 }
		setInterval(function(){
		     i=1;
		     populateAlertsList(alert);
		}, 20000);
	
	$("#checkAll").change(function() {
		if (this.checked) {
			$('.mail-table-body tr td input[type=checkbox]').each(function() {
				this.checked = true;
			});
		} else {
			$('.mail-table-body tr td input[type=checkbox]').each(function() {
				this.checked = false;
			});
		}
	});
	$('#messageAlertDetailsId').click(function() {
		if (!$(this).is(":checked")) {
			$("#checkAll").prop("checked", false);
		} else {
			var flag = 0;
			$('.mail-table-body tr td input[type=checkbox]').each(function() {
				if (!this.checked)
					flag = 1;
			});
			if (flag == 0) {
				$("#checkAll").prop("checked", true);
			}
		}
	});
	$.ajax({
		url : contextPath+'/message/getMsgSubj?&msgSubj=',
		type : 'get',
		success : function(data) {
			$.each(data, function(index, item) {
				searchMsges.push(item);
			});
		}

	});
	$("#msgesSearchId").autocomplete(
	{
						source : searchMsges,
						select : function(event, ui) {
							minLength: 0;
							var msgSubject = ui.item.value;
							populateMessagesList(currentPage, msgSubject);
						}
					}).keydown(function(e){
						if(e.keyCode == 13){
         					populateMessagesList(currentPage, $("#msgesSearchId").val());
						}
					});
	


	$(".reportedDateClass").datetimepicker({
		showOn : "both",
		buttonImage : "img/calendar_icon.jpg",
		buttonImageOnly : true,
		controlType : 'select',
		clickInput : false,
		timeFormat : 'hh:mm:ss tt',
		changeMonth : true,
		changeYear : true,
		yearRange : "-100:+0",
		maxDate : new Date('mm/dd/yy')
	});

	$(".residentDateClass").datetimepicker({
		showOn : "both",
		buttonImage : "img/calendar_icon.jpg",
		buttonImageOnly : true,
		controlType : 'select',
		clickInput : false,
		timeFormat : 'hh:mm:ss tt',
		changeMonth : true,
		changeYear : true,
		yearRange : "-100:+0",
		maxDate : new Date('mm/dd/yy')
	});

	// date Picker
	$(".datepicker").datepicker({

		changeMonth : true,
		changeYear : true,
		yearRange : "-125:+0",
		dateFormat : "mm/dd/yy",
		ampm : true,
		maxDate : new Date()
	});
	$(".relievingdatepicker").datepicker({

		changeMonth : true,
		changeYear : true,
		yearRange : "-125:+0",
		dateFormat : "mm/dd/yy",
	//	ampm : true
		
	});
	$(".dateAndTimePicker").datetimepicker({
		controlType : 'select',
		clickInput : false,
		timeFormat : 'hh:mm:ss tt',
		changeMonth : true,
		changeYear : true,
		yearRange : "-100:+0",
		maxDate : new Date('mm/dd/yy')
	});

	// Password Resetting Message................................

	if (statusFlag == "passChangedSuccess") {
		$("#message")
				.html(
						"<div style='color: green;text-align: center;'>Password Changed successfully....</div>");
		$('#messageModel').foundation('reveal', 'open');
		setTimeout(function() {
			$("#messageModel").foundation('reveal', 'close');
			$("#message").empty();
		}, 3000);
		window.history.pushState({
			"html" : "",
			"pageTitle" : "CARECENT"
		}, "", contextPath+"/");

	} else if (statusFlag == "passChangedFailed") {
		$('#currentPassword').val('');
		$('#newpassword').val('');
		$('#confirmPassword').val('');
		$("#ChangePasswordModalMessage").html(
				"<div style='color: red;'>Change password failed</div>");
		$('#ChangePasswordModal').foundation('reveal', 'open');
		// setTimeout(function(){
		// $("#ChangePasswordModal").foundation('reveal', 'close');
		// $("#ChangePasswordModalMessage").empty();
		// }, 3000);
		window.history.pushState({
			"html" : "",
			"pageTitle" : "CARECENT"
		}, "", contextPath+"/");
	} else if (statusFlag == "Invalid") {
		$('#currentPassword').val('');
		$('#newpassword').val('');
		$('#confirmPassword').val('');
		$("#ChangePasswordModalMessage").html(
				"<div style='color: red;'>Invalid Current Password</div>");
		$('#ChangePasswordModal').foundation('reveal', 'open');
		
		window.history.pushState({
			"html" : "",
			"pageTitle" : "CARECENT"
		}, "", contextPath+"/");
	}
	// Registration Message................................

	if (statusFlag != "") {
		$("#userSettingsModalMessage")
				.html(
						("<div style='color: green;text-align: center;'>"
								+ statusFlag + "</div>"));
		window.history.pushState({
			"html" : "",
			"pageTitle" : "CARECENT"
		}, "", contextPath+"/");
	}
	if (statusFlag != "" && fromPage == 'registration'
			&& isRoleAdmin == 'false' && isRoleSuperAdmin == 'false') {
		$('#messageModel').foundation('reveal', 'open');
		$("#message").html("<div style='color: green;text-align: center;'>"+ statusFlag + "</div>");
		setTimeout(function() {
			$("#messageModel").foundation('reveal', 'close');
			$("#message").empty();
		}, 3000);
		
		window.history.pushState({
			"html" : "",
			"pageTitle" : "CARECENT"
		}, "", contextPath+"/");

	}

	// close the foundation reveal ....................................

	$(".close-reveal-modal").click(function() {
		window.history.pushState({
			"html" : "",
			"pageTitle" : "CARECENT"
		}, "", contextPath+"/");
		$("#userSettingsModalMessage").empty();
	});

	// Resetting data grid message.......................
	$("#userSettrings").click(function() {
		$("#userSettingsModalMessage").empty();
	});

	// change query string to dash board.......................

	$("#regSubmit").click(function() {
		window.history.pushState({
			"html" : "",
			"pageTitle" : "CARECENT"
		}, "", contextPath+"/");
	});

});
function transferToHospitalConfirm() {
	//if ($(this).is(':checked')) {
		$("#dialog-confirm").html('<center><p  class="confirm-dialog" style="font-size:20px !important">Would you like to move the patient to Hospitlal ?</p></center><div class="large-12 columns"><center><div class="large-12 columns emptyselectvalidation" hidden="hidden"><label></label></div></center><div class="large-4 columns"><span id="transferTypeLable"><i class="fi-star small"></i>Transfer type</span></div><div class="large-4 columns end"><select class="plannedselect"><option value="0">--select--</option><option value="planned">Planned</option><option value="unplanned">Unplanned</option></select></div></div>');
		$("#dialog-confirm").dialog({
			resizable : false,
			modal : true,
			height : 250,
			width : 500,
			buttons : {
				"Yes" : function() {
					moveToAcuteCare();

				},
				"No" : function() {
					$(this).dialog('close');

				}
			}
		});
}; 
function moveToAcuteCare(patientEpisodeId) {
	var plannyedDetails= $('.plannedselect option:selected').val();
	if(plannyedDetails=='0'){
		//$(".emptyselectvalidation").show();
		//$(".emptyselectvalidation").text("Please select transfer type");
		$("#transferTypeLable").text('Please select transfer type').css("color","red").parent().removeClass("large-4").addClass("large-5");
	}
	else if(plannyedDetails!='0'){
	//	$(".emptyselectvalidation").text("");
		$("#transferTypeLable").text("Transfer type");
	$.ajax({
		url : contextPath+'/sbar/moveToAcuteCare?patientEpisodeId='+patientEpisodeId+'&plannedDetails='+plannyedDetails,
		type : 'get',
		success : function(data) {
				location.reload();
		}

	});
	}
};

function manageInFacilityConfirm(e) {
	console.log("mangeIn...");
	//if (e.is(':checked')) {
		$("#dialog-confirm").html(
				'<center><p  class="confirm-dialog">Manage the patient in facility</p> (if click yes patient moves to admission)</center>');
		$("#dialog-confirm").dialog({
			resizable : false,
			modal : true,
			height : 250,
			width : 500,
			buttons : {
				"Yes" : function() {
				//	$('#gender-dialog s').text($(this).val());
					manageInFacility();
					$(this).dialog('close');

				},
				"No" : function() {
					$(this).dialog('close');

				}
			}
		});

	//}
};
function manageInFacility() {
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	$.ajax({
		url : contextPath+'/sbar/moveToManagingFacility?patientEpisodeId='
				+ patientEpisodeId,
		type : 'get',
		success : function(data) {
			if (data == "SUCESS") {
				location.reload();
			}
		}

	});

};
function backInFacilityConfirm(data) {
	$("#dialog-confirm").html('<center><p  class="confirm-dialog">Did resident come back from Hospital ?</p>(if click yes, resident moves to admission queue)<br><br>'+(data?'Note : In Admission Form Hospital Related Informatation Need to be filled':'')+'</center>');
	$("#patientStatusInAcuteCare").val("stay");
	$("#dialog-confirm").dialog({
		resizable : false,
		modal : true,
		height : 250,
		width : 500,
		buttons : {
			"Yes" : function() {
				$("#acuteCareModel").trigger('submit');
				$(this).dialog('close');

			},
			"No" : function() {
				$(this).dialog('close');

			}
		}
	});
};
function transferedToHospitalconfirm() {
	$("#dialog-confirm").html(
					'<center><p  class="confirm-dialog" style="font-size:22px !important">Is resident stayed in Hostpital for Long Tearm Care ?</p>(if click yes, patient will get discharged from the facility)<br><br>Note : resident transfer status will be on hold in 48 hrs untill confirm patient stayed in Hospital </center>');
	$("#patientStatusInAcuteCare").val("transfer");
	$("#dialog-confirm").dialog({
		resizable : false,
		modal : true,
		height : 250,
		width : 550,
		buttons : {
			"Yes" : function() {
				$("#acuteCareModel").trigger('submit');	
				$(this).dialog('close');

			},
			"No" : function() {
				$(this).dialog('close');

			}
		}

	});

};
function movedFromFacfirm() {
	$("#dialog-confirm").html(
	'<center><p  class="confirm-dialog" style="font-size:22px !important">would you like move resident permanently from this facility ?</p>(if click yes, resident will get discharged from the facility)</center>');
	$("#patientStatusInAcuteCare").val("movedFromFac");
	$("#dialog-confirm").dialog({
		resizable : false,
		modal : true,
		height : 250,
		width : 550,
		buttons : {
			"Yes" : function() {
				$("#acuteCareModel").trigger('submit');	
				$(this).dialog('close');
				
			},
			"No" : function() {
				$(this).dialog('close');
				
			}
		}
	
	});
	
};
function pupulateSystemNotes(patientEpisodeId) {
	$.ajax({
				url : contextPath+'/sbar/pupulateSystemNotes?patientEpisodeId='+ patientEpisodeId,
				type : 'get',
				success : function(data) {
					if (data.length == 0) {
						$('#systemNotesPanel').hide();
					} else {
						var content = "";
						var sym = '<div class="row">';
						$.each(data,function(idx, ele) {
							 var contentEnd = "</div></dd></div>";
							 $.each(ele.instuctionsList,function(index,	item) {
							    sym += '<div class="medium-12 columns padding-lr left" style="padding: 2px"><label class="fi-info">'+ item	+ '</label></div>';});
								content += '<dd class="accordion-navigation"><a href="#systemNotes'	+ idx+ '" onclick="patientToggle(this)"><div class="fi-play" style="top: -2px;"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+ ele.stepType+ '</a><div class="content" id="systemNotes'+ idx + '">';
								content += sym + " " + contentEnd;
							});
						$("#systemNotesDataTable").html(content);
						$('#systemNotesPanel').show();
					}
				}
			});
};

function populateSbardata(patientEpisodeId, isFormEdit) {
	$('#SBARModalForm').each(function() {
		this.reset();
	});
	$.ajax({
				url : contextPath+'/sbar/populateSbarData?patientEpisodeId='
						+ patientEpisodeId + '&isFormEdit=' + isFormEdit,
				type : 'get',
				success : function(data) {

					$("#otherResidentCareId").val(data.otherResidentCare);
					if (data.advCarePlanDNHflag != null)
						$('#advCarePlanDNHflag').prop('checked',data.advCarePlanDNHflag);
					if (data.advCarePlanDNRFlag != null)
						$('#advCarePlanDNRFlag').prop('checked',data.advCarePlanDNRFlag);
					if (data.advCarePlanDNIFlag != null)
						$('#advCarePlanDNIFlag').prop('checked',data.advCarePlanDNIFlag);
					if (data.advCarePlanNoEtrnalFeedFlag != null)
						$('#advCarePlanNoEtrnalFeedFlag').prop('checked',data.advCarePlanNoEtrnalFeedFlag);
					if (data.otherOrderOrWill.length > 0) {
						$('#otherOrderOrWillcheckBox').prop('checked', true);
						$("#otherOrderOrWill").val(data.otherOrderOrWill);
					} else {
						$('#otherOrderOrWillcheckBox').prop('checked', false);
						$("#otherOrderOrWill").hide();
					}
					if (data.postAcuteCareFlag != null)
						$('#postAcuteCareFlag').prop('checked',
								data.postAcuteCareFlag);
					if (data.longTermCareFlag != null)
						$('#longTermCareFlag').prop('checked',
								data.longTermCareFlag);
					if (data.primaryDiagnoses != null)
						$('#primaryDiagnoses').val(data.primaryDiagnoses);
					if (data.otherPertinentHistory != null)
						$('#otherPertinentHistory').val(
								data.otherPertinentHistory);
					if (data.rnComment != null)
						$('#rnComment').val(data.rnComment);
					if (data.lpnComment != null)
						$('#lpnComment').val(data.lpnComment);
					if (data.latestInrDate != null)
						$('#latestInrDate').val(data.latestInrDate);
					if (data.medAlertChangeLastWeek != null)
						$('#medAlertChangeLastWeek').prop('checked',
								data.medAlertChangeLastWeek);
					if (data.allergies != null)
						$('#allergies').val(data.allergies);
					if (data.medAlertInWarfarin != null)
						$('#medAlertInWarfarin').prop('checked',
								data.medAlertInWarfarin);
					if (data.resultOfLastInr != null)
						$('#resultOfLastInr').val(data.resultOfLastInr);
					if (data.repotedToDate != null)
						$('#repotedToDate').val(data.repotedToDate);
					if (data.gaurdianName != null)
						$("#gaurdianName").val(data.gaurdianName);
					if (data.transferToHospitalFlag != null)
						$('#transferToHospitalFlag').prop('checked',data.transferToHospitalFlag);
					if (data.oximetryOnO2 != null) {
						$("#oximetryOnO2").val(data.oximetryOnO2.length > 0 ? data.oximetryOnO2	: "");
						if (data.oximetryOnRoomair) {
							$("#oximetryOnRoomair").prop('checked', true);
						} else {
							$("#oximetryonO2Check").prop('checked', true);
						}
					}
					if (data.providerVisitFlag != null) {
						$('#providerVisitFlag').prop('checked',
								data.providerVisitFlag);
						$('#repotedToDoctor').val(data.doctorId);
						$(".reportedTo").show();
					}

				}
			});
};

function patientToggle(ele) {
	if ($(ele).find('div.fi-play').hasClass('fi-play_down')) {
		$('div.fi-play').removeClass('fi-play_down');
	} else {
		$('div.fi-play').removeClass('fi-play_down');
		$(ele).find('div.fi-play').addClass('fi-play_down');
	}
};

function toggleAccordianAndLock(ele) {
	$("#" + $(ele).attr('id') + "-Lock").toggleClass('fi-play').toggleClass('fi-lock');
	$("div#" + $(ele).attr('id')).attr('style', '');
};

function patientNewToggle(ele) {
	$('.accordion-navigation').removeClass('active');
	$('.accordion-navigation  div ').removeClass('active');
	if ($(ele).find('div.fi-play').hasClass('fi-play_down')) {
		$('div.fi-play').removeClass('fi-play_down');
		$(ele).find('div.symptom_row active').addClass('active');
	} else {
		$('div.fi-play').removeClass('fi-play_down');
		$(ele).find('div.fi-play').addClass('fi-play_down');
		$(ele).find('div.symptom_row active').addClass('active');
	}
	if ($(this).find('div.symptom_row active').hasClass("active")) {
		$('div.symptom_row active').removeClass('active');
	} 

};

function sbarEvaluate(sbartabId) {
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	$('.EvaluationMessagePanel').empty();
	$.ajax({
				url : contextPath+'/sbar/sbarEvaluate?patientEpisodeId='+ patientEpisodeId,
				type : 'get',
				success : function(data) {
					if (data.length == 0) {
						$('.EvaluationMessagePanel').hide();
					} else {
						
						var content = '<div class="panel col_border_blue">';
						var header = '<div class="row custom_right_panel "><p class="s_header left">Carecent latest suggestions</P>';
						var headerEnd = '</div>';
						var hasSuggestion = false;
						if ((staffRole == "RN" || staffRole == "LPN")&&sbartabId=='sbar_NurseNotesTab' ) {
							
						header += '<input id="notify" class="button small right" type="button" style="padding: 5px 15px; margin: 0;" onclick=notifyDoctor() value="Notify Doctor"/>';
						}
						content += header + headerEnd;
						var patientId = data.patientId;
						if (data.changeInConditionMessageList.length != 0) {
							hasSuggestion = true;
							var notifyImediate = false;
							$.each(data.changeInConditionMessageList, function(key, ele) {
								if (constants_reportImmidate == ele.message) {
									notifyImediate = true;
								}
							});
							if (notifyImediate == true) {
								content += '<div class="row" style="padding: 3px;"><b>Change In Condtion : </b><font color="red">'
										+ constants_reportImmidate
										+ '</font></div>';
								$("#sbarPatientStatus" + patientId)
										.removeClass('no').text("Immediate");
							} else {

								content += '<div class="row" style="padding: 3px;"><b>Change In Condtion : </b><font color="red">'+ constants_reportNextDay+ '</font></div>';
								$("#sbarPatientStatus" + patientId).addClass('no').text("Non Immediate");
							}

						} else {
							$("#sbarPatientStatus" + patientId).addClass('no').text("");
						}
						if (data.carePathNotifyDoctorMessageList.length != 0) {
							hasSuggestion = true;
							content += '<div class="row" style="padding: 3px;"><b>Care Path : </b><font color="red">Notify Doctor!</font></div>';
						}

						if (hasSuggestion == true) {
							$(".EvaluationMessagePanel").html(content + '</div></div>');
							$('.EvaluationMessagePanel').show();
						}
					}
				}
			});
};

$("#sfacilityId").change(function() {
	$("#message").html("<div style='color: green;text-align: center;'>please wait untill reload the dashboard....</div>");
	$('#messageModel').foundation('reveal', 'open');
	var facilityId = $("#sfacilityId").val();
	$.ajax({
		url : contextPath+'/findPatientsFromFacility?facilityId=' + facilityId,
		type : 'get',
		success : function(result) {
			location.reload();
		}
	});
});

function isIntFolatKey(evt, ele) {
	var datatype = $(ele).attr('data-datatype');
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if (charCode > 31 && (charCode < 46 || charCode > 57)) {
		return false;
	}
	if (datatype == "INT"&& (charCode == 46 || (ele.value.length > 4 && charCode != 8))) {
		return false;
	}
	var parts = ele.value.split('.');
    if(parts.length > 1 && charCode==46){
        return false;
    }
	return true;
}

function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if (charCode > 31 && (charCode < 46 || charCode > 57)) {
		return false;
	}
	return true;
}

$(document).on('click', '#SBARModalForm', function() {
	if ($("#otherOrderOrWillcheckBox").is(':checked')) {
		$("#otherOrderOrWill").show();
	} else {
		$("#otherOrderOrWill").hide();
	}

});

function populateMessagesList(pageNo, msgSubject) {
	$.ajax({
				url : contextPath+'/message/getMessages?message=' + message
						+ '&pageNo=' + pageNo + '&msgSubject=' + msgSubject,
				type : 'get',
				success : function(data) {
					//if(data.length>0){
					var content = "";
					var paginationContent = "";
					if(data.messageDtoList!=null){
					$.each(
									data.messageDtoList,
									function(index, item) {
										var date = new Date(item.sentDate);
										date.setHours(date.getHours());
										var dateString = date.toLocaleString();

										if (item.msgSubj == admissionSubj) {
											content += '<tr><td class="mail_leftborder_green">';

										} else if (item.msgSubj == "STOPANDWATCH"|| item.msgSubj == snwSubj) {
											content += '<tr><td class="mail_leftborder_red">';
										} else if (item.msgSubj == observation_Subj) {
											content += '<tr><td class="mail_leftborder_orange">';
										} else if (item.msgSubj == cpSubj) {
											content += '<tr><td class="mail_leftborder_blue">';
										} else if (item.msgSubj == acuteCareSubj) {
											content += '<tr><td class="mail_leftborder_yellow">';
										} else if (item.msgSubj == mang_FacilitySubj) {
											content += '<tr><td class="mail_leftborder_ash">';
										}

										else if(item.msgSubj == transfer_hospital_subj) {
											content += '<tr><td class="mail_leftborder_red">';
										}
										else if(item.msgSubj == backin_facility_subj) {
											content += '<tr><td class="mail_leftborder_green">';
										}
										content += '<input type="checkbox" name="checkClass"  id="messageAlertDetailsId"  value='
												+ item.messageAlertDetailsId
												+ '></input><!--<span class="mail_trdots">.......</span>--></td><td class="col_subject padding-lr"><span style="left: -15px;position: relative;">'
												+ item.msgSubj
												+ '</span><!--<span class="mail_trdots">.......</span>--></td><td class="col_content"><span id="msgcountid" style="float: left;" class="msg-count">'
												+ item.msgCount
												+ '</span><span>'
												+ item.msgBody
												+ '</span></td><td class="col_date" colspan="3">'
												+ dateString + '</td></tr>';
									});
					paginationContent += '<ul class="pagination">';
					if (data.currentPage != 1) {
						paginationContent += '<li class="arrow"><a id="previousId" prevPage='+ (data.currentPage - 1)+ ' href="#" onclick="previousPage(this)">&laquo;</a></li>';
					}
					if (data.noOfpages != 0) {
						paginationContent += '<li class="current"><a href="#" class="pageClass" onclick="currentMsgPage(this)">'
								+ data.currentPage + '</a></li>';
					}
					if (data.currentPage < data.noOfpages - 3) {
						paginationContent += '<li><a href="#" class="pageClass" onclick="currentMsgPage(this)">'
								+ (data.currentPage + 1) + '</a></li>';
						paginationContent += '<li><a href="#" class="pageClass" onclick="currentMsgPage(this)">'
								+ (data.currentPage + 2) + '</a></li>';
					}
					if (data.currentPage < data.noOfpages - 2) {
						paginationContent += '<li class="unavailable"><a href="#">&hellip;</a></li>';
						paginationContent += '<li><a href="#" class="pageClass" onclick="currentMsgPage(this)">'
								+ (data.noOfpages - 2) + '</a></li>';
					}
					if (data.currentPage < data.noOfpages - 1) {
						paginationContent += '<li><a href="#" class="pageClass" onclick="currentMsgPage(this)">'
								+ (data.noOfpages - 1) + '</a></li>';
					}
					if (data.currentPage != data.noOfpages) {
						// paginationContent+='<li><a href="#"
						// class="pageClass">'+data.noOfpages+'</a></li>';
					}
					if (data.currentPage < data.noOfpages) {
						paginationContent += '<li class="arrow"><a id="nextId" href="#" nextpage='+ (data.currentPage + 1) + ' onclick="nextPage(this)">&raquo;</a></li>';
					}
					paginationContent += '</ul>';
					$('#paginationId').html(paginationContent);
					$("#messagesId").html(content);
				}
				}
			});
	
}

function populateAlertsList(alert) {
	$
			.ajax({
				url : contextPath+'/message/getAlerts?alert=' + alert,
				type : 'get',
				global : false,
				success : function(data) {
					
					var content = "<table class='alerts-table'><tbody>";
					if(data.length>0){
					$
							.each(
									data,
									function(idx, obj) {
										content += '<tr><td><span class="fi-flag"></span><span id=""></span></td><td width="100%">'
												+ obj.msgBody
												+ '</td><td width="50%"><span id="alertcountid" class="alert-count">'
												+ obj.alertCount
												+ '</span></td><td><span id="deleteAlertId" class="fi-trash small" onclick="deleteAlerts('
												+ obj.messageAlertDetailsId
												+ ')"></span></td></tr>';
									});
				}
					$("#alertsId").html(content + '</tobdy></table>');
				}
			});
}
function nextPage(e) {
	populateMessagesList(e.getAttribute('nextpage'), $("#msgesSearchId").val());
	$("#checkAll").prop("checked", false);
	return false;
};

function previousPage(e) {
	populateMessagesList(e.getAttribute('prevPage'), $("#msgesSearchId").val());
	$("#checkAll").prop("checked", false);
	return false;
};
function currentMsgPage(e) {
	//e.preventDefault();
	populateMessagesList(e.text, $("#msgesSearchId").val());
	$("#checkAll").prop("checked", false);
	return false;
};

function pageRefresh() {
	var pagefirstId = $("#pagefirstId").text();
	populateMessagesList(pagefirstId, $("#msgesSearchId").val());
	$('#msgesSearchId').val('');
	$("#checkAll").prop("checked", false);
};

function deleteSelectedMessages() {
	var messageAlertDetailsId = [];
	$('.mail-table-body tr td input[type=checkbox]').each(function(c) {
		if ($(this).is(":checked")) {
			deleteMessages($(this).val());
		}

	});

};

$(".ui-datepicker-today span").addClass("ui-state-hover");
function populateStates(){
	var states = [];
	$.ajax({
		type : "GET",
		url : contextPath+"/mangeUser/findStates",
		success : function(data) {
			$.each(data, function(idx, obj) {
				states.push(obj);
			});
		}
	});

	$("#state").autocomplete({
		source : states
	});
};
function getStopAndWacthSymtoms(patientEpisodeId) {
	$.ajax({
				url : contextPath+'/sbar/getStopAndWacthSymtoms?patientEpisodeId='
						+ patientEpisodeId,
				type : 'get',
				success : function(data) {
					var content = "";
					var sym = "", contentEnd = "</div>";
					$.each(data, function(idx, ele) {

						sym += '<div class="fi-check" style="padding:3px">'
								+ ele + '</div>';

					});
					content += '</div ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<p class="s_header"><U>STOP AND WATCH SYMPTOMS</u></p><div class="content row" id="snwsymtom">';
					content += sym + " " + contentEnd;

					$("#SNWSymptomDataTable").html(content);
					$("#stopAndWatchSymptomsDataTable").html(content);
				}

			});
}

function deleteMessages(messageAlertDetailsId) {
	$.ajax({
		url : contextPath+'/message/deleteMessages?messageAlertDetailsId='
				+ messageAlertDetailsId,
		type : 'get',
		success : function(data) {
			populateMessagesList(currentPage, $("#msgesSearchId").val());
			$("#checkAll").prop("checked", false);
			$('#msgesSearchId').val('');
		}
	});
}

function deleteAlerts(messageAlertDetailsId) {
	$.ajax({
		url : contextPath+'/message/deleteAlerts?messageAlertDetailsId='
				+ messageAlertDetailsId,
		type : 'get',
		success : function(data) {
			populateAlertsList(alert);
		}
	});

}

function getChangeInCanditionVitalSignBlack() {
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	$.ajax({
				url : contextPath+'/sbar/getChangeInConditionVitalSignsAttrOnPatient?patientEpisodeId='+patientEpisodeId,
				type : 'get',
				success : function(data) {
					var innerHtmlTxt = '<div id="symptomAttrValueOnPatientId"><div class="row padding-lr"><div style="max-height:200px; overflow:auto;" class="row">';
					var len = data.length;
					var innerHtmlTxt1 = '';
					for (i = 0; i < len; i++) {
						if (data[i].datatype == 'BOOLEAN') {
							
					innerHtmlTxt1 += '<div class="large-3 column"><label for="symptomValues'+ data[i].id+ '"></label><input type="checkbox" id="symptomValues'+ data[i].id+ '" name="symptomValues'+ data[i].id+ '" value="'+ data[i].id+ '" style="margin: 0.3rem 0 1rem  !important;"'+ (data[i].attrValue?"checked":"")+'><label for="symptomValues'+ data[i].id+ '">'	+ data[i].attrName+ '</label></div>';
					} else {
							var datatype = data[i].datatype;
							innerHtmlTxt += '<div class="large-3 column"><label for="symptomValues'+ data[i].id+ '">'+ data[i].attrName+ '</label>'
									+ '<div class="small-6 columns "><input onkeypress="return isIntFolatKey(event, this)" data-datatype="'+ datatype+ '" type="text" id="symptomValues'+ data[i].id+ '" value="'+(data[i].attrValue!=null?data[i].attrValue:"" )+'"  name="symptomValues'+ data[i].id+ '" style="margin: 0.3rem 0 1rem  !important;"></div>'
									+ '<div class="small-3 columns end"><label for="symptomValues'	+ data[i].id+ '">'
									+ (data[i].units == null|| data[i].units == "" ? "" : "("+ data[i].units + ")")
									+ '</label></div></div>';
						}
					}
					innerHtmlTxt += innerHtmlTxt1;

					innerHtmlTxt += '<div class="medium-3 columns end "></div></div>';
					innerHtmlTxt += '<div class="row right small-12" >  <div class="column small-6 right"><button id="changeConditionOkOnPatient" value="Save" name="Save" onclick="submitCICVitalSignsFormOnPatient(true);" style="padding: 8px 25px;" class="small right">Save</button> </div>'
							+ '<div class="column small-6"><button id="changeConditionCancelOnPatient" value="Cancel" name="Cancel" onclick="getChangeInCanditionVitalSignBlack('
							+ patientEpisodeId
							+ ');" style="padding: 8px 21px;" class="small right">Cancel</button></div></div>';
					$("#vitalSignsDataTableOnPatient").html(innerHtmlTxt);
				}

			});
};
function submitCICVitalSignsFormOnPatient(doEval) {
	// var patientEpisodeId =40;
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	var symptom = "";
	if ($("#symptom option:selected").val() != null) {
		symptom = $("#symptom option:selected").val();
	} else {
		symptom = $("#symptomHidden").val();
	}

	var ajaxData = {
		patientEpisodeId : patientEpisodeId,
		symptom : symptom
	};
	$("#symptomAttrValueOnPatientId").find(
			'input:radio:checked, input:checkbox:checked, input:text').each(
			function() {
				ajaxData[$(this).attr("name")] = "" + $(this).val();
			});

	$.ajax({
		url : contextPath+'/sbar/saveChangeInConditionVitalSignData',
		data : ajaxData,
		type : 'post',
		success : function(data) {
			if (data == "SUCCESS") {
				openJueryPopupClose("#conditionPopup");
				//getConditionsVitalSignsDataOnPatient(patientEpisodeId);
				getChangeInCanditionVitalSignBlack(patientEpisodeId)
				changeSbarPatientStatus(patientEpisodeId);
				if(doEval){
					sbarEvaluate("");
				}
			} else {

			}
		}
	});

};

function getConditionsVitalSignsDataOnPatient(patientEpisodeId) {
	$('#vitalSignsDataTableOnPatient input').each(function() {
		this.value = '';
	});
	$.ajax({
				type : "GET",
				url : contextPath+"/sbar/getVitalSignsChangeInConditionsByPatientEpisodeId?patientEpisodeId="+ patientEpisodeId,
				dataType : 'json',
				success : function(data) {
					$.each(data, function(idx, ele) {
						$.each(ele.attributes, function(index, item) {
							if (item.dataType == 'BOOLEAN') {
								$("#symptomValues" + item.attributeId).prop('checked', item.attributeValue);
							} else {
								if (item.attributeValue != null) {
									$("#symptomValues" + item.attributeId).val(item.attributeValue);
								} else {
									$("#symptomValues" + item.attributeId).val("");
								}
							}
						});

					});

				}
			});
};
function vitalSignsView(patientEpisodeId) {
	var innerHtmlTxt = '<div ><div class="row padding-lr"><div style="margin-left:5px" class="row">';
	var innerHtmlTxt1 = '';
	$.ajax({
				type : "GET",
				url : contextPath+"/sbar/getVitalSignsChangeInConditionsByPatientEpisodeId?patientEpisodeId="+ patientEpisodeId,
				dataType : 'json',
				success : function(data) {
					if(data.length!=0){
					$.each(	data,function(idx, ele) {
						$.each(ele.attributes,function(index, item) {
								if (item.datatype == 'BOOLEAN') {
									innerHtmlTxt1 += '<div class="medium-3 columns " style="margin-bottom:2%;margin-top:2%"><label for="symptomValues'+ item.attributeId+ '"></label><div class="medium-1 columns left "><input type="checkbox" id="symptomValues'+ item.id	+ '" name="symptomValues'+ item.id+ '" value="'	+ item.id+ '" style="margin: 0.3rem 0 1rem  !important;"></div><div class="medium-8 columns end "><label >'	+ item.attrName	+ '</label></div></div>';
								} else if (item.attributeValue != ''&& item.attributeValue != null) {
                                    var datatype = item.datatype;
									innerHtmlTxt += '<div class="medium-6 columns"><div class="medium-4 columns "><label for="symptomValues'+ item.attributeId+ '"><b>'	+ item.attributeName+ ':</b></label></div>'	+ '<div class="medium-8 columns end "><span  type="text"  style="margin: 0.3rem 0 1rem  !important;">'+ item.attributeValue+ ' '+ (item.units == null|| item.units == "" ? "": "("+ item.units+ ")")+ '</div></div>';
								}
						});

					});
					innerHtmlTxt += innerHtmlTxt1;
					innerHtmlTxt += '<div class="medium-6 columns end "></div></div>';
					$("#vitalSignsDataTableForView").html(innerHtmlTxt);
				}else{
					innerHtmlTxt +='<div class="small-12 column noDataMsg"><center>So far no Vital Signs recorded</cemter></div></div>';
					$("#vitalSignsDataTableForView").html(innerHtmlTxt);
				}
				}
			});
};

// --- dashboard js start ---
$(document).on('click',	'.fi-list',	function() {

			$(this).parent().find("ul.f-dropdown.menu-items-list li").show();
			var patientEpisodeId = $(this).parent().attr("patient-episodeid");
			$('#sbarPatientEpisodeId').val(patientEpisodeId);
});
$(document).on('click', '.tabs.vertical > dd >a,.sbar-close-reveal-modal', function() {
	
	var sbartabId = $(this).parent().attr("id");
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	var sbarPrvId=$('#sbarPrvTabId').val();
		if(sbarPrvId != sbartabId){
			if((staffRole == "RN" || staffRole == "LPN") && sbarPrvId == "sbar_BackgroundTab"){
				sbarSave();
			}
			if((staffRole == "RN" || staffRole == "LPN") && sbarPrvId == "sbar_VitalSignsTab"){
				 submitCICVitalSignsFormOnPatient(false);
				 
			}
	}
	$('#sbarPrvTabId').val(sbartabId);
	switch (sbartabId) {
	
	case 'sbar_VitalSignsTab':
		sbarEvaluate(sbartabId);
		//getConditionsVitalSignsDataOnPatient(patientEpisodeId);
		break;
	case 'sbar_SymtomsTab':
	if (staffRole == "RN" || staffRole == "LPN") {
		getConditionsSymptomsData(patientEpisodeId);
		//getConditionsSymptomsDataForView(patientEpisodeId);
		getMedicineNames(patientEpisodeId);
		sbarEvaluate(sbartabId);
		
	 }
		else{
		//	getConditionsSymptomsData(patientEpisodeId);
			getConditionsSymptomsDataForView(patientEpisodeId);
			getMedicine(patientEpisodeId);
		}
	
	break;
	case 'sbar_LabWorksTab':
		sbarEvaluate(sbartabId);
		populateCarePathSuggestedLabworks(patientEpisodeId);
		break;
	case 'sbar_CarePaths':
		if (staffRole == "RN" || staffRole == "LPN") {
			dynamicCarePathOnPatient();
			sbarEvaluate(sbartabId);
		} else {
			dynamicCarePath();
		}
		break;
	case 'sbar_NurseNotesTab':
		sbarEvaluate(sbartabId);
		pupulateSystemNotes(patientEpisodeId);
		getNurseNotes(patientEpisodeId);
		break;
	case 'sbar_PractitionerTab':
		getPractitionerNotes(patientEpisodeId);
		break;
//	case 'sbar_BackgroundTab':
//		break;

	}
});

function invalidateStopAndWatch(){
					var pagefirstId = $("#pagefirstId").text();
					var stopAndWatchHistoryId = $("#stopAndWatchHistroyInvalidateId").val();
					$("#stopAndWatchErrors span").hide();
					var errorMsg = "";
					var error = false;
					var facilityStafResponse = $("#facilityStafResponse").val();
					if (facilityStafResponse == '') {
						errorMsg += "Nurse Response is Required<BR>";
						error = true;
					}
					if (error) {
						$("#stopAndWatchErrors span").show().html(errorMsg);
						return false;
					}
					$.ajax({
								url : contextPath+'/stopAndWatch/invalidateStopAndWatchHistoryData?stopAndWatchHistoryId='
										+ stopAndWatchHistoryId,
								type : 'get',
								success : function(data) {
									$('#StopAndWatchModal').foundation(	'reveal', 'close');
									resetSNWBorder(data);
									getPatientHistory(data);
									populateMessagesList(pagefirstId, $("#msgesSearchId").val());
									populateAlertsList(alert);
									$("#snwhistorycount" + data).text(($("#snwhistorycount" + data).text()) - 1);
									if($("#snwhistorycount" + data).text()<1){
										location.reload();
									}
								}
							});
};


function addPrescribe() {
	$("#conditionPopup").dialog('option', 'title', 'Prescribe Medicine');
	$("#conditionPopup").dialog('option', 'resizable', false);
	$("#conditionPopup").dialog('option', 'width', '50%');
	var ele = $("#conditionPopup");
	openJueryPopup('#conditionPopup')
	ele.empty();
	var innerHtmlText = '<div id="medicine-Errors">';
	innerHtmlText += '</div>';
	innerHtmlText += '<div class="medicineAdded">MedicineName:<input type="text" id="medicine"  name="medicineName" /></div><div class="row right" style="margin-top: 10px;"><button id="saveMedicine" value="Save"  onclick="saveMedicine()"  style="padding: 5px 15px;margin-bottom: 0 !important;" class="small right">Save</button><button id="medicineCancel" value="Cancel" name="Cancel" onclick="openJueryPopupClose('
			+ "'#coditionPopup'"
			+ ')" style="padding: 5px 15px;margin-right: 20px;margin-bottom: 0 !important;" class="small right">Cancel</button></div>';
	ele.html(innerHtmlText);

};

$(document).on('click',	'.f-dropdown.menu-items-list li',	function() {
			if (staffRole != "CNA") {
				var tabId = $(this).find('a').attr("tab_id");
				$('#sbarPrvTabId').val(tabId);
				$('.tabs.vertical > dd').removeClass('active');
				$('.tabs.vertical > dd#' + $(this).find('a').attr("tab_id"))
						.addClass("active");
				$($('.tabs.vertical > dd#'+ $(this).find('a').attr("tab_id")).find('a')).click();
				var patientEpisodeId = $('#sbarPatientEpisodeId').val();
				var value1 = $(this).parent().parent().attr("data-patient");
				$patientInfoArr = value1.split('=');
				$('#SbarTitle').text($(this).parent().parent().attr("data-patient-name"));
				getStopAndWacthSymtoms(patientEpisodeId);
				populateSbardata(patientEpisodeId, true);
				getSbarReleventSymtoms(patientEpisodeId);
				if (staffRole == "RN" || staffRole == "LPN") {
				getChangeInCanditionVitalSignBlack();
			//	getChangeInCanditionLabWorkBlack();
				}else{
				vitalSignsView(patientEpisodeId)
				}
				$('#optionsMenuModal').foundation('reveal', 'open');

			} else {
				$('#message').text("No privilege to access SBAR").css("color",
						"orange");
				$('#messageModel').foundation('reveal', 'open');
			}
		});

$('.re-ann').click(
		function() {

			var content = "<table>";

			$.ajax({
				url : contextPath+'/getAllActiveAnnouncement',
				type : 'get',
				success : function(data) {
					$.each(data, function(index, value) {
						content += "<tr><td width='318px'>"
								+ value.announcementText + "</td></tr>";
					});

					content += "</table>";
					$('div.anouncements').html('');
					$('div.anouncements').append(content);
				}
			});

		});

$(document).on('ready focus', ".symptomsDateClass", function() {
	$(".symptomsDateClass").datetimepicker({
		controlType : 'select',
		clickInput : false,
		showOn : "both",
		buttonImage : "img/calendar_icon.jpg",
		buttonImageOnly : true,
		timeFormat : 'hh:mm:ss tt',
		changeMonth : true,
		changeYear : true,
		yearRange : "-100:+0",
		maxDate : new Date('mm/dd/yy')
	});
});
// --- dashboard js end =======

function editMyProfile() {
	$("#careCentStaffReg").text(currentFacility+" staff profile");
	$.ajax({
		type : "GET",
		url : contextPath+"/mangeUser/getStaffDetails",
		data : 'staffId=' + staffId + '&operation=edit',
		dataType : 'json',
		success : function(data) {
			$('#regSubmit').val("Update");
			$("#activateDeactivateId").hide();
			$("#firstName").val(data.firstName).attr("readonly", true);
			$("#lastName").val(data.lastName).attr("readonly", true);
			$("#middleName").val(data.middleName).attr("readonly", true);
			$("#userName").val(data.userName).attr("readonly", true);
			$("#workNumber").val(data.workNumber).attr("readonly", true).css('pointer-events', 'none');
			$("#workEmail").val(data.workEmail).attr("readonly", true);
			$("#staffRoleId").val(data.staffRoleId).css('pointer-events',
					'none'); /* need to set property TODO: */
			$("#gender").val(data.gender);
			$("#docRoleId").val(data.docRoleId == null ?0:data.docRoleId).css('pointer-events','none');
			$("#mobileNo").val(data.mobileNo);
			$("#email").val(data.email);
			$("#address").val(data.address);
			$("#street").val(data.street);
			$("#city").val(data.city);
			$("#state").val(data.state);
			$("#zipcode").val(data.zipcode);
			$("#operation").val("edit");
			$("#staffId").val(staffId);
		}
	});

	$("#registrationErrors .error").html('');
	$("#registrationErrors span").css({
		background : '#fff',
		margin : 0
	});
	$('#RegistrationModal').foundation('reveal', 'open');
}

// --- dashboard js end ==============
function loadManageUsersDialog(searchKey) {
	$.ajax({
		type : "GET",
		url : contextPath+"/mangeUser/facilityStaff",
		data : 'searchUserName=' + searchKey,
		dataType : 'json',
		success : AjaxFetchDataSucceeded,
		error : AjaxFetchDataFailed
	});
	$("#resett").show();
	$('#userSettingsModal').foundation('reveal', 'open');
}

function AjaxFetchDataSucceeded(result) {
	$('#userDataGrid')
			.dataTable(
					{
						"iDisplayLength" : 5,
						"aLengthMenu" : [ 5, 10, 25, 50 ],
						"scrollY": "200px",
				        "scrollCollapse": true,
				        //"paging": false,
						"aaData" : result.data,
						"bDestroy" : true,
						"sZeroRecords" : "No records matched your criteria",
						"oLanguage" : {
							"sEmptyTable" : "Matching data is not available"
						},
						"aoColumnDefs" : [
								{
									"aTargets" : [ 2 ],
									"searchable" : false,
									"sClass" : "userDataGridWorkMail",
								},
								{
									"aTargets" : [ 3 ],
									"sClass" : "userDataGridWorkNumber",
									"searchable" : false
								},
								{
									"aTargets" : [ 4 ],
									"sClass" : "userDataGridRole",
									"searchable" : false,
								},
								{
									"aTargets" : [ 5 ],
									"sClass" : "userDataGridFacility",
									"searchable" : false,
								},
								{
									"aTargets" : [ 6 ],
									"mRender" : function(data, type, ful) {
										$result = data;
										$flagArray = $result.split('|');
										if ($flagArray[0] == "true") {

											if ($flagArray[1] == "true") {
												return '<a href="#" id="edit_staff" class="center button tiny edit" onclick="editStaffUser(this)">Edit</a>';
											} else {
												return '<a href="#" id="add_staff" class="center button tiny add" onclick="addStaffUser(this)">Add</a>';
											}
										} else if ($flagArray[2] == '${sessionScope.staffId}') {
											return '<a href="#" id="edit_staff" class="center button tiny edit" onclick="editStaffUser(this)">Edit</a>';
										} else {
											return ' ';
										}
									}
								}, {
									"aTargets" : [ 7 ],
									"bVisible" : false,
									"searchable" : false
								}, {
									"aTargets" : [ 8 ],
									"bVisible" : false,
									"mRender" : function(data, type, ful) {
										return '';
									}
								} ],

					});
	$('#userDataGrid_filter input').attr("placeHolder", "By Username");
	$('.dataTables_filter input').unbind();
	$('.dataTables_filter input').bind("<input type='text'/>");
	$('.dataTables_filter input').bind('keyup', function(e) {
		if (e.keyCode == 13) {
			loadManageUsersDialog(this.value);
		}
	});
}

function AjaxFetchDataFailed(result) {
	//alert(result.status + ' ' + result.statusText);
}

function cleanNewRegistrationModal() {
	$("#careCentStaffReg").text(currentFacility+' Staff Registration Form');
	$("#regSubmit").val("Register");
	$("#firstName").val('').attr("readonly", false);
	$("#lastName").val('').attr("readonly", false);
	$("#middleName").val('').attr("readonly", false);
	$("#userName").val('');
	$("#gender").val("");
	$("#staffRoleId").val('0').css('pointer-events', '');
	$("#empId").val('').attr("readonly", false);
	$("#isAdminId").val('0').attr("readonly", false);
	$("#workNumber").val('').attr("readonly", false).css('pointer-events', '');
	$("#docRoleId").val('0').css('pointer-events', '');
	$("#workEmail").val('').attr("readonly", false);
	$("#mobileNo").val('').attr("readonly", false);
	$("#email").val('').attr("readonly", false);
	$("#address").val('').attr("readonly", false);
	$("#street").val('').attr("readonly", false);
	$("#city").val('').attr("readonly", false);
	$("#state").val('').attr("readonly", false);
	$("#zipcode").val('').attr("readonly", false);
	$("#operation").val("new");
	$("#staffId").val('').attr("readonly", false);
	$("#registrationErrors .error").html('');
	$("#registrationErrors span").css({
		background : '#fff',
		margin : 0
	});
	$('#RegistrationModal').foundation('reveal', 'open');

}

function notifyDoctor() {
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	$("#notifyContent").empty();
	var content = '<div class="row"><div class="small-12 columns"><span id="notifyDocResultMsg" ></span></div></div><br> <div class="row><div class="small-12 columns end" style="display:flex;"><div class="small-2 columns"><label>Report To :</label></div><div class="small-4 columns"><span id="repotedToDoctorName"></span><select class="notifyoptions end" id="repotedToDoctor" name="repotedToDoctor" hidden> </select></div></div><div class="small-12 columns end"><label>Notes:</label><textarea rows="2" cols="30" id="userNotesArea"></textarea></div></div><div class="row"><input  class="button small right" type="button" style="padding: 5px 15px; margin: 0;" onclick="saveReportToDocInfo()" value="Report"/></div>';
	$('#notifyDoctorModel').dialog(notfyDoctorPopUp).dialog("open");
	$.ajax({
		url : contextPath+'/mangeUser/getPatientAsigndDoc?patientEpisodeId='+ patientEpisodeId,
		type : 'get',
		success : function(data) {
			if(data.length != 0) {
				if(data.relievingDate==null || data.relievingDate> new Date() ){
				
				$("#notifyContent").html(content);
				$("#repotedToDoctor").append(
						'<option value=' + data.id + ' selected="selected">'
								+ data.name + '</option>');
				$("#repotedToDoctorName").text(data.name);
				}
		        else{
					var innerHtmlTxt ='<div class="row"><div class="small-12 column noDataMsg"><center>Assigned Doctor ('+data.name+')is deactivated form facility please contact Admin </cemter></div></div>';
					$("#notifyContent").html(innerHtmlTxt);
					$("reportToDoctorButton").hide();
				}
				
			} else {
				var innerHtmlTxt ='<div class="row"><div class="small-12 column noDataMsg"><center>Doctor is not asigned to patient </cemter></div></div>';
				$("#notifyContent").html(innerHtmlTxt);
		    	$("reportToDoctorButton").hide();
			}
			$('#notifyDocResultMsg').hide();
		}
	});

};
function saveReportToDocInfo() {
	var patientEpisodeId = $('#sbarPatientEpisodeId').val();
	var doctorId = $('#repotedToDoctor').val();
	var notes = $('#userNotesArea').val();
	if (notes != '' && notes != null) {
		$.ajax({
			url : contextPath+'/sbar/saveRepotedDoctor?patientEpisodeId='
					+ patientEpisodeId + '&doctorId=' + doctorId + '&notes='
					+ notes,
			type : 'get',
			success : function(data) {

				if (data == "SUCESS") {
					if ($('#notifyDocResultMsg').hasClass("error")) {
						$('#notifyDocResultMsg').removeClass("error")
					}
					$('#notifyDocResultMsg').text("Reported Sucessfully").show()
							.addClass("sucess");
					getNurseNotes(patientEpisodeId);
				} else {
					if ($('#notifyDocResultMsg').hasClass("sucess")) {
						$('#notifyDocResultMsg').removeClass("sucess")
					}
					$('#notifyDocResultMsg').text("Reported failed").show()
							.addClass("error");

				}
				setTimeout(function() {
					$('#notifyDoctorModel').dialog("close");
				}, 10000);

			}
		});
	} else {
		if ($('#notifyDocResultMsg').hasClass("sucess")) {
			$('#notifyDocResultMsg').removeClass("sucess")
		}
		$('#notifyDocResultMsg').text("Please provide Notes to Report").show()
				.addClass("error");

	}
};


function confirmforInvalidate() {
	$("#dialog-confirm").dialog({
		resizable : false,
		modal : true,
		height : 250,
		width : 500,
		buttons : {
			"Ok" : function() {
				$(this).dialog('close');

			}
		}
	});
}

function stayInNursingHome() {
	var patientId = $("#inAcutePatinetId").val();
	$.ajax({
		url : contextPath+'/acuteCare/stayInNursingHome?patientId=' + patientId,
		type : 'get',
		success : function(data) {
			if (data == "SUCESS") {
				console.log("Stay in nursing home");
				window.history.pushState({"html" : "","pageTitle" : "CARECENT"}, "", contextPath+"/");
				location.reload();
			}
		}

	});
}

function sbarSave(){
	var frm=$("#SBARModalForm");
    var sbarData=$("#SBARModalForm").serialize();
    $.ajax({
         contentType : 'application/json; charset=utf-8',
         type: frm.attr('method'),
         url: frm.attr('action'),
         dataType : 'json',
         data : sbarData,
         success : function(data){
         },
         error : function(){
         }
   });
   
};

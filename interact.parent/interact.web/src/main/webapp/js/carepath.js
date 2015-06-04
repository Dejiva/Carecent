
var carePathFieldArr = [], popUpTitle="";

var SbarIntialPopUpOption={
		 modal: true,
		 title:'Do you want to Observe the patient?',
		 autoOpen: true,
	      width:'40%',
	      height:'30%', 
	      resizable:false,
	   
		buttons: {
			Yes:{
				text: 'Yes',
				click : function (){
					//SBARIntialModalForm.submit();	
				}
			},
			   No: function (event, ui) {
				    
				 
				  
			      }
			}
		
};
		
var carePathViewPopUpOption={
		autoOpen: true,
		modal: true,
		closeText: 'x',
		closeOnEscape: true,
		width: 100,
		minHeight: 'auto',
		height:'auto',
		maxHeight: 400
};

var carePathPopUpOption={
		autoOpen: true,
		modal: true,
		closeText: 'x',
		closeOnEscape: true,
		width: 550,
		minHeight: 'auto',
		height:'auto',
		maxHeight: 400,
		buttons: {
			PreviousStep:{
				'class': 'CarePathPreviousStep',
				id:"previousStep",
				text: 'Previous Step',
				click : function (){
					gotoPreviousStep();
				}
			},
			NextStep:{
				'class': 'CarePathNextStep',
				id:"nextStep", 
				text: 'Next Step',
				click : function (){
					gotoNextStep();
				}
			}

		}
};
var carePathPopUpMsgOption={
		autoOpen: true,
		modal: true,
		closeText: 'x',
		closeOnEscape: true,
		width: '50%',
		maxWidth: '50%',
		height:200,
		resizable: false, 
		title: "Care Path Concluded",
		create: function(event, ui) {}, 
		buttons: {
			Back:{
				'class': 'CareBack',
				id:'Back',
				text:'Back',
				click : function(){
					gotoPreviousStep();
				}
			},
			Close:{
				'class': 'CareClose',
				id:'close', 
				text:'Close',
				click : function(){
					closeCarePath();
				}
			}

		}
};
var notfyDoctorPopUp={
		autoOpen: true,
		modal: true,
		closeText: 'x',
		closeOnEscape: true,
		aspectRatio: true,
		width: '45%',
		maxWidth: '45%',		
		height:300,
		resizable: false, 
		title: "Notify To Doctor",

};
function generateCarePathPage(data){
	
	$("#content").empty();
	$.each(data, function(key, fields) {
	
		$.each(fields, function(fieldRowIdx, fieldRow) {
		
			var htmlRow = $('<div style="padding: 2px"/>').addClass("row");
			
			$("#content").append(htmlRow);
			
			$.each(fieldRow, function(fieldIdx, field) {
				var ctrl = '';
				if (field.filedId != null) {
					carePathFieldArr.push(field.filedId);
				}
				
				switch (field.fieldType) {
				case 'title':
					ctrl=$('<p id='+field.filedId+'/>').text(field.title).attr("class","h_header");
					popUpTitle = field.title;	
					//htmlRow.append(ctrl);
					break;
				case 'error':
					
					if(field.text==''){
						ctrl=$('<p id='+field.filedId+'/>').text(field.text).attr({ 'class': 'error', hidden: 'hidden'});
					}else{
						ctrl=$('<p id='+field.filedId+'/>').text(field.text).attr({	'class': 'error'});
						
					}
					
                     htmlRow.append(ctrl);
					break;

				case 'checkbox':
					
					ctrl = $('<input/>', {"class": "left"}).attr({
						type : field.fieldType,
						id : field.filedId,
						value : field.value
					});
					if (field.property != null) {
						var property = field.property;
						$.each(property, function(propIdx, prop) {
							ctrl.prop(propIdx, prop);
						});
					}
					
					htmlRow.append(ctrl);
					ctrl = $('<label for='+ field.filedId +'/>').attr({style: "display: initial !important;"}).text(field.label);
					htmlRow.append(ctrl);
					break;
				case 'label':
					if(field.listItems.length > 0){
						var ulmain = $('<ul/>', {"class": "square"}).append($('<li />', {text: field.label}));
						var ulchild = $('<ul/>', {"class": "square"});
						$.each(field.listItems, function(id, data){
							ulchild.append($('<li />', {text: data}));
						});
						ulmain.append(ulchild);
						htmlRow.html(ulmain);
					}else{
						ctrl = $('<label/>', {"class": "fi-info"}).text(field.label);
						htmlRow.append(ctrl);
					}
					break;
				case 'inputlabel':
					ctrl = $('<label class="left small-2" />').text(field.label);
					htmlRow.append(ctrl);
					break;
				case 'hidden':
					ctrl = $('<input/>').attr({
						type : field.fieldType,
						id : field.filedId,
						value : field.value
					});
					htmlRow.append(ctrl);
					break;
				case 'heading':
					ctrl = $('<h5/>').text(field.heading);
					htmlRow.append(ctrl);
					break;
				default:

					ctrl = $('<input style="width:20%; " onkeypress="return isNumberKey(event)"/>').attr({
						type : field.fieldType,
						id : field.filedId,
						value : field.value,



					})
					if (field.property != null) {
						var property = field.property;
						$.each(property, function(propIdx, prop) {
							ctrl.prop(propIdx, prop);
						});
					}
				htmlRow.append(ctrl);
				if (field.label != null) {
					ctrl = $('<label for='+ field.filedId +' class="left"/>').text(field.label);
					htmlRow.append(ctrl);
				}
				};
			});
		});
	});
}

function closeCarePath(){
	//$('#carePathModalMessage').foundation('reveal', 'close');
	openJueryPopupClose('#carePathModalMessage');
	dynamicCarePathOnPatient( );
}



function gotoNextStep() {
	
	var queryString = '?';
	for (var i = 0; i < carePathFieldArr.length; i++) {
		if ($('#' + carePathFieldArr[i]).is(':hidden')) {
			if ($('#' + carePathFieldArr[i]).val() != '') {
				queryString = queryString
				+ $('#' + carePathFieldArr[i]).attr('id') + '='
				+ $('#' + carePathFieldArr[i]).val() + '&';
			}
		}
		if ($('#' + carePathFieldArr[i]).is(':text')) {
			if ($('#' + carePathFieldArr[i]).val() != '') {
				queryString = queryString
				+ $('#' + carePathFieldArr[i]).attr('id') + '='
				+ $('#' + carePathFieldArr[i]).val() + '&';
			}
		}

		if ($('#' + carePathFieldArr[i]).is(':checkbox')) {
			if ($('#' + carePathFieldArr[i]).is(":checked")) {
				queryString = queryString
				+ $('#' + carePathFieldArr[i]).attr('id') + '='
				+ $('#' + carePathFieldArr[i]).val() + '&';
			}
		}


	}
	
	$.ajax({
		url : contextPath+'/carepath/invoke' + queryString,
		type : 'get',
		dataType : 'json',
		success : function(data) {
			if(data == 'require_approve_from_Doctor'){
				$('#cpMessage').text('Require approval from Doctor').css('font-size', '28px');
				openJueryPopupClose('#carePathModel');
				$('#carePathModalMessage').dialog(carePathPopUpMsgOption).dialog("open");
				$(".ui-dialog-title").text('');
			}
			else if(data == 'NOTIFY_MD_NP_PA'){
				$('#cpMessage').text('Notify MD or NP or PA');
				openJueryPopupClose('#carePathModel');
				//$('#carePathModalMessage').foundation('reveal', 'open');
				$('#carePathModalMessage').dialog(carePathPopUpMsgOption).dialog("open");	
				/*}else if(data == 'DO_NOTHING'){
				openJueryPopupClose('#carePathModel');
				$('#cpMessage').text('Care Path ended with no conclusion');
			//	$('#carePathModalMessage').foundation('reveal', 'open');
				$('#carePathModalMessage').dialog(carePathPopUpMsgOption).dialog("open");	*/
			}else{
				generateCarePathPage(data);
				$('#carePathModal').dialog(carePathPopUpOption).dialog("open");
				$step_id=$("#stepId").val();
				if($step_id > 1){
//					$('#previousStep').show();
					$("#carePathModal").dialog('option', 'title', popUpTitle);
				} else{
					$("carePathError").text("Please select aleast one symtom");
					$("#carePathModal").dialog('option','buttons', [{ text:'Next Step',click : function (){gotoNextStep();}}],'title', popUpTitle);
					//	$('#message').dialog(messageOption).dialog("open");
				}
			}
		}
	  
	});
};	
function dynamicCarePathOnPatient() {
	patientEpisodeId = $('#sbarPatientEpisodeId').val()
	$
			.ajax({
				url : contextPath+'/carepath/getCarePathNamesWithStatus/'
						+ patientEpisodeId,
				type : 'get',
				success : function(data) {
					var header = "<p class='s_header'> CarePath ", content = "";
					if (staffRole != "MD" && staffRole != "NP"
							&& staffRole != "PA") {
						header += "<span class='fi-stop' style='color:green; float: right; margin-left: 50px; font-size: 15px; text-transform: capitalize;'>&nbsp;&nbsp;Running/Completed</span><span class='fi-stop' style='color:gray; float: right; margin-left: 50px; font-size: 15px; text-transform: capitalize;'>&nbsp;&nbsp;Other</span><span class='fi-stop' style='color: #008cba;float: right; margin-left: 50px;font-size: 15px; text-transform: capitalize;'>&nbsp;&nbsp;Suggesting</span>";
					}
					header += "</p>";
					$
							.each(
									data,
									function(idx, name) {
										var color = "", sugg = "Suggested By:\n";
										if (name.status == "RUNNING_OR_COMPLETED") {
											color = "green";
										} else if (name.status == "OTHERS") {
											color = "gray";
										} else {
											color = "#008cba";
										}
										var disableButtonColor = 'gray';

										$
												.each(
														name.suggestedBy,
														function(id, sugest) {
															sugg += ((name.suggestedBy.length > 1 && name.suggestedBy.length - 1 != id) ? sugest
																	.toString()
																	.split(':')[1]
																	+ ","
																	: sugest
																			.toString()
																			.split(
																					':')[1])
														});
										if (name.status == "RUNNING_OR_COMPLETED") {
											content += '<div class="columns large-6 '
													+ (data.length - 1 == idx ? "end"
															: "")
													+ '"><div class="large-7 columns"><span class="left '
													+ (name.suggestedBy.length > 0 ? "fi-clipboard-notes has-tip tip-bottom noradius"
															: "")
													+ '" data-tooltip title="'
													+ sugg
													+ '" style="border: medium none;">&nbsp;&nbsp;<label style="float: right;">'
													+ name.carePathName
													+ '</label></span></div><div class="large-5 columns">';
											content += '<input type="button" value="Invoke" class="dynamicCarePathHit button small radius" onclick="initiateCarePath(this)" title="'
													+ sugg
													+ '" data-suggestedby="'
													+ sugg
													+ '" data-status="'
													+ name.status
													+ '" data-code="'
													+ name.carePathCode
													+ '" style="padding: 5px 10px; background-color: '
													+ color
													+ '" data-patientEpisodeId="'
													+ patientEpisodeId + '"/>';
											content += '</div></div>'

										} else {
											content += '<div class="columns large-6 '
													+ (data.length - 1 == idx ? "end"
															: "")
													+ '"><div class="large-7 columns"><span class="left '
													+ (name.suggestedBy.length > 0 ? "fi-clipboard-notes has-tip tip-bottom noradius"
															: "")
													+ '" data-tooltip title="'
													+ sugg
													+ '" style="border: medium none;">&nbsp;&nbsp;<label style="float: right;">'
													+ name.carePathName
													+ '</label></span></div><div class="large-5 columns">';

											content += '<input type="button" value="Invoke" class="dynamicCarePathHit button small radius"  onclick="initiateCarePath(this)" title="'
													+ sugg
													+ '" data-suggestedby="'
													+ sugg
													+ '" data-status="'
													+ name.status
													+ '" data-code="'
													+ name.carePathCode
													+ '" style="padding: 5px 10px; background-color: '
													+ color
													+ '" data-patientEpisodeId="'
													+ patientEpisodeId
													+ '"/></div></div>';
											// content +='</div></div>';

										}

									});
					content = "<div class='row' style='max-height:219px; overflow:auto;'>" + content + "</div></div>"
					$('#dynamicCarePathsElementOnPatient').html(
							header + content);
					$("#dynamicCarePathsElementOnPatient").show();
				}
			});
};
function dynamicCarePath() {
	 $.ajax({
					url : contextPath+'/carepath/getRunningCarePath/'
							+ $('#sbarPatientEpisodeId').val(),
					type : 'get',
					success : function(data) {
						var count = 0;

						var header = '<div class="panel col_border_blue sbar_content"><lable class="s_header">Select a CarePath </lable>', content = "", end = "</div>";
						content += '<select class="SelectedCarePathView" onchange="viewCarePath()" style="width:auto"><option value="">-----Select-----</option>';
						$.each(data, function(idx, name) {

							count = 1;
							content += '<option value="' + name.carePathCode + '">'
									+ name.carePathName + '</option>';

						});
						content += '</select>';
						content = content + '<div id="viewContent"></div>';
						if (count == 0) {
							var header1 ='<div class="row"><div class="small-12 column noDataMsg"><center>So far no carepaths executed</cemter></div></div>';
							
							//var header1 = '<div class="panel col_border_blue sbar_content" style=""><center><lable class="s_header" style="margin: auto;" >No runing carepaths </lable></div>'
							$('.dynamicCarePathsElement').html(header1);
						} else {
							$('.dynamicCarePathsElement').html(
									header + content + end);
						}
						$(".dynamicCarePathsElement").show();
					}

				});
	}

function viewCarePath() {
	$carePathCode=$(".SelectedCarePathView").val();
	$("#viewContent").empty();
	var ele=""
	$("#viewContent").append(ele);
	var patientEpisodeId=$('#sbarPatientEpisodeId').val();
	if($carePathCode!=""){
	$.ajax({
	type : "GET",
		url : contextPath+"/carepath/viewCarePath?CARE_PATH_CODE="+$carePathCode+"&patientEpisodeId="+patientEpisodeId,
		dataType : 'json',
		success : function(data) {
			var content = "";
			var isNotify="false";
			var i=0;
			var notifyBox='';
			$.each(data, function(idx, ele){
				//$('#carePathViewModal').dialog('option', 'title', ele.carePathName+" - View");
    			var sym = "", contentEnd="</div></div>";
            	var sym = "<table style='width: 100%;'><tbody>", contentEnd="</tbody></table></div>";
            	if(ele.attrValues.length==0){
            		sym +='<tr><td>No Symptoms were found</td></tr>';
            	}
            	$.each(ele.attrValues, function(index, attr){
            		sym += '<tr>';
            		var symEndTag='</tr>';
            		if(attr.notifyDoctorFlag){
            			if(attr.dataType != null){
            				sym +='<td class="notifyDocSymtom">';
            			}
            			else{
            				sym+='<td class="fi-check notifyDocSymtom">';
            			}
            			
            			 isNotify="true";
            			 notifyBox='<div style="text-align:center">&dArr;</div><div class="panel callout radius " style="width: 50% !important;"> <center><p style="font-size:20px;color:red;margin-bottom:-3px;" > Notify MD or NP or PA</p></center></div>';
            			
            		}else{
            			if(attr.dataType != null){
            				sym +='<td>';
            			}
            			else{
            				sym+='<td class="fi-check">';
            			}
            		}
            			sym += attr.carePathAttrName+'</td>';
					if(attr.dataType != null){
						sym += '<td>'+attr.value+'</td>'
	             	}
					sym+=symEndTag;

				});
            	if(i>0){
            		content += '<div style="text-align:center">&dArr;</div>';
            	}
        	content += '<div class="panel callout radius"  style="width: 50% !important;"><h6>'+ele.stepName+'</h6>';
			content += sym+" "+contentEnd;
			i++;
			});
			content +=notifyBox;
			$("#viewContent").html(content)
		}
	});
	}
};

 function initiateCarePath(e){
	var patientEpisodeId=$('#sbarPatientEpisodeId').val();	
	$carePathCode=e.getAttribute("data-code");
	$.ajax({
		type : "GET",
		url : contextPath+"/carepath/invoke?CARE_PATH_CODE="+$carePathCode+"&patientEpisodeId="+patientEpisodeId,
		//  data : 'CARE_PATH_CODE=' + carePath,
		dataType : 'json',
		success : function(data) {
			if(data == 'require_approve_from_Doctor'){
				$('#cpMessage').text('Require approval from Doctor');
				openJueryPopupClose('#carePathModel');
				$('#carePathModalMessage').dialog(carePathPopUpMsgOption).dialog("open");
			}
			else if(data == 'NOTIFY_MD_NP_PA'){
				$('#previousStep').show();
				openJueryPopupClose('#carePathModel');
				$('#cpMessage').text('Notify MD or NP or PA');
				//$('#carePathModalMessage').foundation('reveal', 'open');
				$('#carePathModalMessage').dialog(carePathPopUpMsgOption).dialog("open");	
				/*}else if(data == 'DO_NOTHING'){
				openJueryPopupClose('#carePathModel');
				$('#cpMessage').text('Care Path ended with no conclusion');
				//$('#carePathModalMessage').foundation('reveal', 'open');
				$('#carePathModalMessage').dialog(carePathPopUpMsgOption).dialog("open");	*/
			}else{

				generateCarePathPage(data);
				$('#carePathModal').show();
				$('#carePathModal').dialog(carePathPopUpOption).dialog("open");
				$step_id=$("#stepId").val();

				if($step_id==1){
					$("#carePathModal").dialog('option','buttons', [{ text:'Next Step',click : function (){gotoNextStep();}}],'title', popUpTitle);
					$("#carePathModal").dialog('option', 'title', popUpTitle);
				}
				$("#carePathModal").dialog('option', 'title', popUpTitle);
			}
			//$('#loader_'+carePath).hide();
		}			
	});
//	$("#carePathModal").dialog('option', 'title', popUpTitle);
};

function gotoPreviousStep(){
	$.ajax({
		type : "GET",
		url : contextPath+"/carepath/getPreviousStep",
		dataType : 'json',
		success : function(data) {
			generateCarePathPage(data);
			$step_id=$("#stepId").val();
			openJueryPopupClose('#carePathModelMessage');
			$('#carePathModal').dialog(carePathPopUpOption).dialog("open");
			$("carePathError").hide();
			if($step_id==1){
				
				$("#carePathModal").dialog('option','buttons', [{ text:'Next Step',click : function (){gotoNextStep();}}],'title', popUpTitle);
			}else{
				$("#carePathModal").dialog('option', 'title', popUpTitle);
			}
		}			
	});
};

var options = {
		autoOpen: true,
		modal: true,
 	    closeOnEscape: true,
		title: 'Symptom'
};

var messageOption= {
		autoOpen: true,
		modal: true,
		closeText: 'x',
		closeOnEscape: true,
		/*Close: function () {
            $(this).dialog('close');
        }*/
		width: 150,
		height: 50,

};
function openJueryPopupWithTitle(eleId){
	$(eleId).dialog({
		autoOpen: true,
		modal: true,
		closeText: 'x',
		closeOnEscape: true,
		title: "SITUATION"}).dialog("open");
}

function openJueryPopup(eleId){
	$(eleId).dialog(options).dialog("open");
	/*var els = $('#symptomsAddBlock').children("div:last").find('select').value;
	if(els!=0){
		$(eleId).show();
	}*/
}
function openJueryPopupClose(eleId){
	$('.ui-dialog-titlebar-close').trigger('click');
	//$(eleId).dialog("close");
};





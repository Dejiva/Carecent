<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<div id="StopAndWatchModal" class="reveal-modal large raleway" data-reveal>
	<p class="lead" style="text-align: center;">Stop and Watch Form</p>

	<a id="snw-close-reveal-modal" class="close-reveal-modal" >&#215;</a>
<div style="max-height: 530px; overflow-y: auto;">

	<form:form method="post" commandName="stopAndWatchForm"
		action="${pageContext.request.contextPath}/stopAndWatch/submit">
		
			<div id="deletePopup" ></div>
		<div id="stopAndWatchErrors" >
	         	<span class="error"> </span>
		</div>
			<div id="stopAndWatchSuccess" style="color: green; font-size: large;text-align: center;"></div>
	
		<div class="row" id="stopAndWatchForm1">
		  <div class="row" style="padding-left:10px;">
			<div>
				<div class="large-6 check_data columns padding-lr">
					<div class="small-12 columns check_box">
						<form:checkbox path="seemsDifferentThanUsual" class="left inline" ></form:checkbox>
						<label for="seemsDifferentThanUsual">Seems
							different than usual</label>
					</div>
					<div class="small-12 columns check_box">
						<form:checkbox path="communicatesLess"  class="left inline "/>
						<label for="communicatesLess">Talks or
							communicates less</label>
					</div>
					<div class="small-12 columns check_box">
						<form:checkbox path="needsMoreHelp" class="left inline "></form:checkbox>
						<label for="needsMoreHelp">Overall needs more help</label>
					</div>
					<div class="small-12 columns check_box">
						<form:checkbox path="pain" class="left inline"/>
						<label for="pain">Pain - new or worsening;Participated less in activities</label>
					</div>

                    <div class="small-12 columns check_box">
						<form:checkbox path="ateLess" class="left inline"/>
						<label for="ateLess">Ate less</label>
					</div>
					<div class="small-12 columns check_box">
						<form:checkbox path="noBowelMovement" class="left inline"/>
						<label for="noBowelMovement">No bowel movement in 3 days;or Diarrhea</label>
					</div>
					<div class="small-12 columns check_box">
						<form:checkbox path="drankLess" class="left inline"/>
						<label for="drankLess">Drank less</label>
					</div>
				</div>
				<div class="large-6 check_data columns padding-lr">
					
					
					<div class="small-12 columns check_box">
						<form:checkbox path="weightChange" class="left inline"/>
						<label for="weightChange">Weight change</label>
					</div>
					<div class="small-12 columns check_box">
						<form:checkbox path="agitated" class="left inline"/>
						<label for="agitated">Agitated or nervous more than usual</label>
					</div>
					<div class="small-12 columns check_box">
						<form:checkbox path="tired" class="left inline"/>
						<label for="tired">Tired, weak, confused, or drowsy</label>
					</div>
				

					<div class="small-12 columns check_box">
						<form:checkbox path="changeInSkinCondition" class="left inline"/>
						<label for="changeInSkinCondition">Change in skin color or
							condition</label>
					</div>
					<div class="small-12 inline columns check_box">
						<form:checkbox path="helpWithWalking" class="left inline"/>
						<label for="helpWithWalking">Help with walking, transferring, toileting more than usual</label>
					</div>
					   <div class="small-12 columns check_box "  id="snwOtherDiv">
					  
						<form:checkbox path="snwOther" class="left inline"/>
						<label for="snwOther">other</label>
						
					 </div>
					
					<div class="small-12 columns check_box">
					  
					<form:input path="snwOtherSymptom" maxlength="100" class="left inline"/>
					</div>

				</div>
			</div>
			</div>
			<div class="row">
				<div style='clear: both;'></div>
				 <br>
			   
<!-- 				    <div class="large-10 check_data"> -->
					<div class="row">
					 <div class="large-12 columns">
					 <div class="large-10 columns">
					    <div class="large-2 columns">&emsp;&emsp;&emsp;&emsp;
					    </div>
						<div class="large-3 columns">
							<label for="residentName"><i class="fi-star small"></i>Name Of Resident</label>
						</div>
						<div class="large-7 columns">
							 <c:choose>
								<c:when test='${ fromPage == "guest"}'>
									<%-- <form:select path="residentId"> 
										<form:option value="" label="--- Select ---" />
										<form:options items="${sessionScope.facilityPatientsMap}" />
									</form:select> --%>
									
							  	 <form:hidden   path="residentId" /> 
								<form:input  path="residentName" />
								</c:when>
								<c:otherwise>
									 <form:hidden  path="residentId" /> 
									<form:input  path="residentName" />
								</c:otherwise>
				    		</c:choose> 
                      
						</div>
					 </div>
					</div>
					</div>
					<div class="row">
                      <c:choose>
							<c:when test='${ fromPage == "guest"}'>
                             <div class="large-12 columns">
					           <div class="large-10 columns">
					           <div class="large-2 columns">&emsp;&emsp;&emsp;&emsp;
					           </div>
							 	<div class="large-3 columns">
									<label for="guestName"><i class="fi-star small"></i>Guest Name</label>
								</div>
								<div class="large-7 columns end">
									<form:input path="guestName" />
								</div> 
                               </div>
                              </div>
							</c:when>
							<c:otherwise>
	                      	<br>
								<form:hidden path="stopAndWatchHistoryId" />
								<div id="snwedit">
								 <div class="large-12 columns">
								  <div class="large-10 columns">
								   <div class="large-2 columns">&emsp;&emsp;&emsp;&emsp;
					               </div>
									<div class="large-3 columns">
										<label for="repotedBy">Reported By</label>
									</div>

									<div class="large-7 columns end">
										<form:input path="repotedBy" />
									</div>
								  </div>
								  </div>
								</div>
								<div id="snwadd">
								<div class="large-12 columns">
								 <div class="large-10 columns">
								    <div class="large-2 columns">&emsp;&emsp;&emsp;&emsp;
					                </div>
									<div class="large-3 columns">
										<label for="yourName"><i class="fi-star small"></i>Your Name</label>
									</div>
									<div class="large-7 columns end">
										<form:input path="yourName" value="${sessionScope.staffName}" />
									</div>
								  </div>
								 </div>
								</div>
							</c:otherwise>
						</c:choose>

					</div>
					<c:choose>
						<c:when test='${ fromPage != "guest"}'>
<!-- 					<div class="row"> -->
					<br>
					<div class="large-12 columns">
					<div class="large-10 columns">
					    <div class="large-2 columns">&emsp;&emsp;&emsp;&emsp;
					    </div>
					    <div class="large-3 columns">
						  <label for="right-label">Reported to</label>
						</div> 
						<div class="large-4 columns end">
							<form:select path="reportedToFacilityStaffId">
						
								 <form:option value="" label="----select---" /> 
								<form:options items="${sessionScope.activefacilityStaffMap}" />
							</form:select>
						</div>
						<div class="large-3 columns ">
							 <form:input  path="reportedDateAndTime" class="snwrepoteddatepicker"/>
						</div>
					 </div>
					 </div>
<!-- 					</div> -->
<!-- 					<div class="row "> -->
					<div class="large-12 columns">
					      <div class="large-10 columns">
					            <div class="large-2 columns">&emsp;&emsp;&emsp;&emsp;
					            </div>         
								<div class="large-3 columns">
									<label for="right-label">Nurse Response</label>
								</div>
								<div class="large-7 columns end">
									<form:textarea path="facilityStafResponse" />
								</div>
								</div>
								</div>
<!-- 					</div> -->
<!-- 					<div class="row"> -->
					  <div class="large-12 columns">
					       <div class="large-10 columns">
					            <div class="large-2 columns">&emsp;&emsp;&emsp;&emsp;
					            </div>
								<div class="large-3 columns">
									<label for="right-label">Nurse's Name</label>
								</div>
								<div class="large-4 columns">
								  <form:select path="nurseId">
									<form:options items="${sessionScope.activefacilityStaffMap}" />
								  </form:select>
								 </div>
								 <div class="large-3 columns end">
									<form:input path="responseDateAndTime" class="snwresponsedatepicker"/>
								 </div>
								 </div>
								 </div>
<!-- 					 </div> -->
					</c:when>
					</c:choose>
					<div class="row">
					<div class="large-12 columns padding-lr" style="padding-top:10px">
						<div class="small-6 columns">
					         <input type="button" id="StopAndWatchModalCancel"
								class="button small revertBackListElement left" value="Close"
								tabindex="3" />
						</div>
						<div class="small-4 columns right">
						<input type="hidden" id="stopAndWatchHistroyInvalidateId">
							<input type="button" id="InvalidateStopAndWatch" name="Invalidate"
								class="button small" onclick="invalidateStopAndWatch()" value="Invalid" tabindex="3"  >
						    <input type="submit" id="StopAndWatchModalSub" name="ButtonSubmit"
								class="button small right" value="Submit" tabindex="3" >
						</div>
<!-- 						<div class="small-4 columns"> -->
					       
<!-- 						</div> -->
						
					 </div>
					</div>
			</div>
			</div>
	</form:form>
	
		<c:choose>
			<c:when test='${ fromPage != "guest"}'>
				<div class="row small-11 " id="stopAndWatchForm2" style="max-height:400px;min-height:400px ;overflow-y: auto;">

					<div class="col_head col_violet1 col_last_head padding-lr">
						<span class="data_col_head align-data padding-lr">Patient History</span>
					</div>
					<!-- Patient History List -->
					<dl class="accordion" id="pHistList" data-accordion></dl>
				</div>
			</c:when>
		</c:choose>
	</div>
<!-- FacilityId alert popup -->
<div id="GuestFacilityIdAlertModal" class="reveal-modal tiny raleway">
	<p class="lead" style="text-align: center;">FacilityId alert</p>

	URL must have have facilityId parameter:
	http://domain${pageContext.request.contextPath}/guest?facilityId=value

</div>
</div>



<%@page import="javax.jms.Session"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/fragments/stopAndWatch.jsp"/>
<!doctype html>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>CARECENT</title>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/foundation.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font/foundation-icons.css" />
<link rel="stylesheet"
	href="https://datatables.github.com/Plugins/integration/foundation/dataTables.foundation.css">
<!--     <link rel="stylesheet" href="//cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css"> -->
<!--     <link rel="stylesheet" href="//cdn.datatables.net/responsive/1.0.1/css/dataTables.responsive.css"> -->
<link
	href="https://fonts.googleapis.com/css?family=Raleway:100,200,300,400,500,600,700,800,900"
	rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/js/vendor/modernizr.js"></script>
<script>
	var firstTimeUserFlag = '${firstTimeUser}';
	
</script>
<script>
	var formFlag = '${formName}';
	
	var errorIn	= '${errorPage}';
</script>
<script>
	var showFacilitySelect = '${showFacilitySelect}';
</script>
<script>
	var fromPage = '${param.fromPage}';
	
	var fromGuest = '${fromPage}';
	var currentPage='${sessionScope.currentPage}';
	var errorIn='${errorPage}';
	var facilityId='$.param(facilityId)';
	/* var snwsuccess='${sessionScope.snwsuccess}';
	
    if(snwsuccess==null){
    	 snwsuccess="";
     }
 */
</script>
</head>
<body>
<div id="fname" style="color:blue; font-size:large;text-align: center;">${sessionScope.FacilityName}</div>

	<div class="row raleway">
		<div class="small-3 small-centered columns">
			<img src="${pageContext.request.contextPath}/img/mail_logo.jpg" title="Carecent" /><br /> <br />
			<p class="lead" style="text-align: center;">Generate Stop and
				Watch Form</p>
				
		</div>
	</div>
	<div class="row login-top raleway">
		<div class="small-6 small-centered columns">
			<!--<div class="row">
                  <div class="small-4 columns">
                          <label for="right-label" class="right inline">Bed Number:</label>
                  </div>
                  <div class="small-7 columns">
                          <input type="text" id="right-label" placeholder="">
                  </div>
              </div>-->
			<div class="row center">
				<div class="small-2 small-centered columns">
					<input type="button" id="generate"
						class="button small success center launcher" value="Open Form"
						tabindex="4"  />
				</div>
			</div>
		</div>
	</div>
	<div id="myModal">
		<jsp:include page="/WEB-INF/fragments/stopAndWatch.jsp"/>
	</div>
	<div id="messageModel" class="reveal-modal small raleway" data-reveal>
	<a class="close-reveal-modal">&#215;</a>
	<p class="msg_header" style="text-align: center;" id="message">Message</p>
	<!-- 		<div id="message"></div> -->
</div>
</body>
<script src="${pageContext.request.contextPath}/js/vendor/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/jquery-ui.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/vendor/jquery.easydropdown.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/vendor/jquery.slimscroller.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/app.js"></script>
<script src="${pageContext.request.contextPath}/js/foundation.min.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/foundation/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/foundation/foundation.dataTables.js"></script>
<!-- <script src="${pageContext.request.contextPath}/js/ajaxDataTable.js"></script> -->
<!-- <script src="//cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script> -->
<!-- <script src="//cdn.datatables.net/responsive/1.0.1/js/dataTables.responsive.min.js"></script> -->
<script>
/* if(snwsuccess=='stopandwatchsuccess'){

		try{
		    	 $('#message').text("Stop and Watch created Successfully").css("color", "green");
				 $('#messageModel').foundation('reveal', 'open');
				 
//		         $("#stopAndWatchSuccess").html("Data is Saved Successfully......");
//				 editStopAndWatchHistory(statusFlag.split('|')[1]);
			     setTimeout(function(){$("#messageModel").foundation('reveal', 'close');}, 5000);
			    
			     window.history.pushState({"html":"","pageTitle":"INTERACT"},"", path+"/");
			   
		      
		  }catch(e){};
	} */
	$(function() {
		$.ajax({
			type : "POST",
			url : contextPath+"/j_spring_security_check",
			data : "j_username=guest&j_password=guest",
			success : function(data) {
			}
		});
	});
	if (fromGuest == "noFacilityId") {
		$("#GuestFacilityIdAlertModal").foundation('reveal', 'open');
	} 
</script>
</html>


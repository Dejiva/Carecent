<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<title>INTERAT</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/foundation.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font/foundation-icons.css"/>
<link href="https://fonts.googleapis.com/css?family=Raleway:100,200,300,400,500,600,700,800,900" rel="stylesheet" type="text/css">
<script src="js/vendor/modernizr.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var check = '<%=request.getAttribute("valid")%>';
	$("#submit").click(function(){
		
		if($("#password").val() ==''){
			alert("pass wont be null");
			return false;
		}
		
		if($("#password").val() != $("#confirmPassword").val()){
			alert("passwords not matched");
			$("#password").val("");
			$("#confirmPassword").val("");
			//document.myform.reset();
			return false;
		}
	});
});
</script>
</head>
<body>
<div class="row raleway">
	<div class="small-3 small-centered columns">
    </div>
    <br/><br/>
    <div class="row login-top raleway">
	    <form action="${pageContext.request.contextPath}/firstlogin/user" method="post" name="myform">
	       <div class="small-10">
	       <div class="row">
	            <div class="small-5 columns">
	                <label for="right-label" class="right inline">Password :</label>
	            </div>
	            <div class="small-7 columns">
	                <input type="password" id="password" name="password"/>
	            </div>
	       </div>
	       <div class="row">
	            <div class="small-5 columns">
	                <label for="right-label" class="right inline">Conform Password :</label>
	            </div>
	            <div class="small-7 columns">
	                <input type="password" id="confirmPassword" name="confirmPassword"/>
	            </div>
	       </div>
	       <div class="row">
	            <div class="small-1 columns">
	                <input type="submit" data-reveal-id="myModal"
	                        data-reveal-ajax="true" class="button small" value="Submit"
	                        tabindex="3"  id="submit"/>
	            </div>
	            <div class="small-1 columns">
	                <input type="button" data-reveal-id="myModal"
	                        data-reveal-ajax="true" class="button small" value="Cancel"
	                        tabindex="3" />
	            </div>
			</div>
	   		</div>
		</form>
    </div>
</body>
</html>
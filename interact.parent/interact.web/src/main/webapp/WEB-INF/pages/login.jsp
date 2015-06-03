<%@ page isELIgnored="false" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>CARECENT</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/foundation.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css"/>
    <link href="https://fonts.googleapis.com/css?family=Raleway:100,200,300,400,500,600,700,800,900" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/js/vendor/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/vendor/jquery.easydropdown.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/vendor/jquery.slimscroller.js"></script>
<script src="${pageContext.request.contextPath}/js/foundation.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
<script>
var status='${status}';
</script>
</head>
<body>
    <div class="row raleway">
        <div class="small-3 small-centered columns">
            <img src="${pageContext.request.contextPath}/img/mail_logo.jpg" title="Carecent"/>
        </div>
    </div>
    <br/><br/>
    <div class="row login-top raleway">
        <div class="small-3 small-centered columns">
           <form id="loginForm" name="loginForm" action="j_spring_security_check" method="post">
              <c:if test="${not empty param.authfailed}">
				  <span id="infomessage" class="error">Login failed due to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /></span>
			  </c:if>
			  <c:if test="${not empty param.newpassword}">
				  <span id="infomessage" class="error">Login failed due to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /></span>
			 </c:if>
			  <c:if test="${not empty param.acclocked}">
				  <span id="infomessage" class="error">Login failed due to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /></span>
			  </c:if>
			  <c:if test="${not empty param.accdisabled}">
				  <span id="infomessage" class="error">Login failed due to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /></span>
			  </c:if>
			  <c:if test="${not empty param.loggedout}">
				  <span id="infomessage" class="error success">You have been successfully logged out</span>
			  </c:if>
			  <div class="row">
                  <label>Username</label>
                  <input id="usernameField" type="text" name="j_username" required pattern="[a-zA-Z0-9]+"
                  		 placeholder="Username" tabindex="1" value="<c:out value="${SPRING_SECURITY_LAST_USERNAME}"/>"/> 
              </div>
              <div class="row">
                  <label>Password</label>
                  <input id="passwordField" type="password" name="j_password"  tabindex="2"/>
              </div>
              <div class="row">
                  <input type="submit" class="button small success" value="Login" tabindex="3"/>
                  <button id="forgotPassword" type="button" style="float: right; font-size: 14px; margin: 20px 0 0; padding: 0; position: relative;">Forgot your password?</button>
              </div>
          </form>
        </div>
    </div>
    <!-- forgot Password Code Block -->
	<div id="ForgotPasswordModal" class="reveal-modal small raleway"
		data-reveal>
		<p class="lead" style="text-align: center;">Reset Password</p>
		<form action='${pageContext.request.contextPath}/recover/resetPassword' method='get' data-abide
			id='ForgotPassworForm'>
			<div id="passwordResetMessage"></div>
			<div>
				<label><b>Please provide your work email:</b><span><img src="${pageContext.request.contextPath}/img/postcover.gif" title="resetPassword"/></span></label>
				<input type="text" name="workMail" id="workMail" style="width:459px;!important" required>
			</div>
			<div class="row">
				<div class="small-1 columns">
					<input id="passwordResetSubmit" type="submit" class="button small" value="Submit"
						tabindex="3"/>
				</div>
				<div class="small-3 columns">
					<input type="button" class="button small" value="Close"
						tabindex="3" id="forgotPassCancel" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>
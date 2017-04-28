<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery-2.0.3.js"/>"></script>
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap/bootstrap.css" />"></link>
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap/bootstrap-theme.css" />"></link>
<link rel="stylesheet"
	href="<c:url value="/resources/css/common/airline.css" />"></link>
<script type="text/javascript"
	src="<c:url value="/resources/js/register/register.js"/>"></script>
<script type="text/javascript">

$(document).ready(function(){
	$('#login').hide();
})
</script>

</head>

<body class="body loginbody">
	<div class="login-header"></div>
	<div class="airline-container">
		<div class="login-container">
			<div class="login-logo">
				<img src="resources/images/airplane-logo.png">
			</div>
			<div class="form">

				<ul class="tab-group">
					<li class="tab active"><a href="#signup">Sign Up</a></li>
					<li class="tab"><a href="#login">Log In</a></li>
				</ul>

				<div class="tab-content">
					<div id="signup">
						<h1>Sign Up for Free</h1>
						<div class="alert alert-danger" id="errorSection"
							style="display: none;"></div>
						<div class="alert alert-success" id="successSection"
							style="display: none;"></div>
						<form:form action="signUp.do" method="POST" modelAttribute="user"
							id="signUpform" name="form">

							<div class="top-row">
								<div class="field-wrap">
									<label> First Name<span class="req">*</span>
									</label>
									<form:input type="text" class="form-control"
										placeholder="First-Name" path="firstName" id="firstName"
										name="firstName" ></form:input>
									<form:errors path="firstName" cssStyle='color:red'/>
								</div>

								<div class="field-wrap">
									<label> Last Name<span class="req">*</span>
									</label>
									<form:input type="text" class="form-control"
										placeholder="Last-Name" path="lastName" id="lastname"
										name="lastName" ></form:input>
									<form:errors path="lastName" cssStyle='color:red'/>
								</div>
							</div>

							<div class="field-wrap">
								<label>Choose Role<span class="req">*</span>
								</label>
								<form:select id="role" class="form-control" style="" size="1"
									path="roleType">
									<option>CUSTOMER</option>
									<option>AGENT</option>
									<option>SUPPORT</option>
									
								</form:select>
								<form:errors path="roleType" />
							</div>

							<div class="field-wrap">
								<label>Email<span class="req">*</span>
								</label>
								<form:input type="text" class="form-control" placeholder="Email"
									path="emailID" id="email" name="email" required="true" value='${cookie.userName.value}' ></form:input>
								<form:errors path="emailID" cssStyle='color:red'/>
							</div>

							<div class="field-wrap">
								<label>Password<span class="req">*</span>
								</label>
								<form:input type="text" class="form-control"
									placeholder="Password" path="password" id="password"
									name="password" required="true" value='${cookie.password.value}' ></form:input>
								<form:errors path="password" cssStyle='color:red'/>
							</div>
 
							<button type="submit" id="saveUser" class="button button-block">SAVE</button>

						</form:form>







					</div>

					<div id="login">
						<h1>Welcome Back!</h1>

						<form:form action="home.do" method="POST"
							 id="loginBean" modelAttribute="loginBean" >

							<div class="field-wrap">
								<label> Email Address<span class="req">*</span>
								</label>
								<input type="text" name="username"/>
								
							</div>

							<div class="field-wrap">
								<label> Password<span class="req">*</span>
								</label>
								<input type="text" name="pass"/>
								
							</div>

							<p class="forgot">
								<a href="#">Forgot Password?</a>
							</p>
							<div class="field-wrap">
                             <b style="color:red">Remember Me</b><input type="checkbox" name="rememberMe"
                              value="true" style="display: block;background: white;width: 20px;height: 20px;
                              float: left;margin-right: 5px;"/>
                             </div>
							<button class="button button-block" />Log In</button>

						</form:form>

					</div>

				</div>
				<!-- tab-content -->

			</div>
			<!-- /form -->
		</div>
	</div>
</body>
</html>
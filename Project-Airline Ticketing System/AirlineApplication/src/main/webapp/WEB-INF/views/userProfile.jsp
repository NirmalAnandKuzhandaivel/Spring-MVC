<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	 src="<c:url value="/resources/js/jquery/jquery-2.0.3.js"/>"></script>
<script type="text/javascript"
   src="<c:url value="/resources/js/bootstrap/bootstrap.min.js"/>"></script>
<script type="text/javascript"
	 src="<c:url value="/resources/js/register/register.js"/>"></script>
<script type="text/javascript"
   src="<c:url value="/resources/js/bootstrap/bootstrap-datepicker.js"/>"></script>
<script type="text/javascript" 
   src="<c:url value="/resources/js/jquery/jquery.timepicker.min.js"/>"></script>
 <script type="text/javascript"
	src="<c:url value="/resources/js/jquery-ui/jquery-ui-custom.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jqgrid/jquery.jqGrid.min.js"/>"></script>
	<script type="text/javascript"
	src="<c:url value="/resources/js/admin/userProfile.js"/>"></script>
<script type="text/javascript"
			src="<c:url value="/resources/js/jqgrid/i18n/grid.locale-en.js"/>"></script>
	
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/css/jqgrid/ui.jqgrid.css"/>"></link>
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/css/jqgrid/jqGridCustom.css"/>"></link>
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/css/jqueryui/jquery-ui.css"/>"></link>
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap/bootstrap.css" />"></link>
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap/bootstrap-theme.css" />"></link>
<link rel="stylesheet" 
    href="<c:url value="/resources/css/common/airline.css" />"></link>
</head>
<body>
<div class="airline-container">
	<div class="profile-template">
		
			
				<br> <br>
			<form action="updateProfile.do" method="POST"  enctype="multipart/form-data">
				<div class="tab-content">
					  <div class="top-row">
							<div class="field-wrap">
								<input type="text" class="form-control" 
								value="${sessionScope.user.getFirstName()}"
										 id="firstName"
										name="firstName" />
									
							</div>
							<div class="field-wrap">
								<button type="button" class="btn btn-success" value="EDIT"
									id="firstNameEdit">EDIT</button>
							</div>
						</div>
						
						<div class="top-row">
							<div class="field-wrap">
								<input type="text" class="form-control" 
								value="${sessionScope.user.getLastName()}"
									 id="lastName"
										name="lastName" />
									
							</div>
							<div class="field-wrap">
								<button type="button" class="btn btn-success" value="EDIT"
									id="lastNameEdit">EDIT</button>
							</div>
						</div>
						
						<div class="top-row">
							<div class="field-wrap">
								<input type="text" class="form-control" 
								value="${sessionScope.user.getEmailID()}"
									 id="emailID"
										name="emailID" />
									
							</div>
							<div class="field-wrap">
								<button type="button" class="btn btn-success" value="EDIT"
									id="emailIDedit">EDIT</button>
							</div>
						</div>
						
						<div class="field-wrap">
							<div class="fileUpload btn btn-primary">
	   						 <span>Upload</span>
	    						Select photo : <input  class="upload button-block"  name="uploadFile" type="file"/>
							</div>
						</div>
						
						
						<button type="submit" id="UpdateUser" class="button button-block">UPDATE</button>
						
				 </div>
				
			
			</form>	
			</div>
				
				 </div>
				
		
	



</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery-2.0.3.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap/button.js"/>"></script>
<script type="text/javascript"
   src="<c:url value="/resources/js/bootstrap/bootstrap.min.js"/>"></script>

<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap/bootstrap.css" />"></link>
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap/bootstrap-theme.css" />"></link>
<link rel="stylesheet"
	href="<c:url value="/resources/css/common/airline.css" />"></link>
<script type="text/javascript" 
    src="<c:url value="/resources/js/bootstrap/bootstrap-datepicker.js"/>"></script>
    <script type="text/javascript" 
    src="<c:url value="/resources/js/admin/contactMailSupport.js"/>"></script>
</head>
<body>
<div id="header"><tiles:insertAttribute name="header"/></div>
<div id="body"><tiles:insertAttribute name="body"/></div>
</body>
</html>
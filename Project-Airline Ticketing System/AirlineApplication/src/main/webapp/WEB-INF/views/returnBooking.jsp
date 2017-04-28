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
	src="<c:url value="/resources/js/admin/returnSchedule.js"/>"></script>
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
    
    
 <script type="text/javascript">
var strings = ["","Flight ID","Flight Company","From","To","Start Date","Start Time","Reach Date","Reach Time" ,"Price Each","Tickets Available"            
             ];
        
        </script>
        
</head>
<body>
<div class="airline-container">
<div id="alert"></div>
<div class="alert alert-danger" id="errorSection" style="display: none;"></div>
    <input type=hidden name="fromPlace" id="fromPlace" value="${sessionScope.fromPlace}" />
    <input type=hidden name="toPlace" id="toPlace" value="${sessionScope.toPlace}" />
    
    <input type=hidden name="returnDate" id="returnDate" value="${sessionScope.returnDate}" />
    <input type=hidden name="noofPeople" id="noofPeople" value="${sessionScope.noofPeople}" />
    <input type=hidden name="trip" id="trip" value="${sessionScope.trip}" />
	<table id="returnScheduleGrid"></table>
	<div id="returnScheduleGrid" class="jqgtabs"></div>
	<div id="returnScheduleGrid" class="jqgtabs"></div>
	<br>
	
		<form:form action="CustomerDetails.do" method="POST">
			     <button type="submit" id="submitPage" class="btn btn-danger"
						title="Next Page">	PROCEED BOOKING	
				</button>
	   </form:form>
		
</div>

</body>
</html>
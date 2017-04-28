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
	src="<c:url value="/resources/js/admin/departSchedule.js"/>"></script>
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
<h4>TICKET DETAILS</h4>
		<table
			class="table table-striped table-bordered table-condensed table-hover table-custom">
			<thead>
				<tr>
					<th>FlightID</th>
					<th>Company</th>
					<th>From</th>
					<th>To</th>
					<th>Start Date</th>
					<th>Start Time</th>
					<th>End Date</th>
					<th>End Time</th>
					<th>Price</th>
				</tr>
			</thead>
			<tbody>

				<tr>
					<td>${sessionScope.departureTicket.getFlightID()}</td>
					<td>${sessionScope.departureTicket.getFlightCompany()}</td>
					<td>${sessionScope.departureTicket.getFromPlace()}</td>
					<td>${sessionScope.departureTicket.getToPlace()}</td>
					<td>${sessionScope.departureTicket.getStartDate()}</td>
					<td>${sessionScope.departureTicket.getStartTime()}</td>
					<td>${sessionScope.departureTicket.getEndDate()}</td>
					<td>${sessionScope.departureTicket.getEndTime()}</td>
					<td>${sessionScope.departureTicket.getPrice()}</td>

				</tr>
				
				<tr>
					<td>${sessionScope.returnTicket.getFlightID()}</td>
					<td>${sessionScope.returnTicket.getFlightCompany()}</td>
					<td>${sessionScope.returnTicket.getFromPlace()}</td>
					<td>${sessionScope.returnTicket.getToPlace()}</td>
					<td>${sessionScope.returnTicket.getStartDate()}</td>
					<td>${sessionScope.returnTicket.getStartTime()}</td>
					<td>${sessionScope.returnTicket.getEndDate()}</td>
					<td>${sessionScope.returnTicket.getEndTime()}</td>
					<td>${sessionScope.returnTicket.getPrice()}</td>

				</tr>

			</tbody>
		</table>
		
<h4>PASSENGER DETAILS</h4>
   <table
			class="table table-striped table-bordered table-condensed table-hover table-custom">
			<thead>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Age</th>
					<th>ID Proof</th>
				</tr>
			</thead>
			<tbody>
             <c:forEach var="passenger" items="${sessionScope.passengerList}">
				<tr>
					<td>${passenger.getFirstName()}</td>
					<td>${passenger.getLastName()}</td>
					<td>${passenger.getAge()}</td>
					<td>${passenger.getIdProof()}</td>
				</tr>
			</c:forEach>

			</tbody>
		</table>
		
		<form:form  action="FinalPage.do" method="POST"> 
		
		<button type="submit" class="button button-block" />SUBMIT ORDER</button>   
		</form:form>
	</div>

</body>
</html>
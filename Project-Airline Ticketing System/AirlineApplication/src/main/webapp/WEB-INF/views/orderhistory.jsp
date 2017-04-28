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
	src="<c:url value="/resources/js/admin/orderHistory.js"/>"></script>
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
	var ticketHeaders = ["Ticket ID", "Flight ID", "Flight Company", "From", "To",
			"Start Date", "Start Time", "Reach Date", "Reach Time",
			"Price", "Ticket Type" ];
	
	var passengerHeaders = ["Passenger ID", "First Name", "Last Name", "Age", "ID Proof"];
</script>
</head>
<body>
<div class="order-container">
<h4> ${sessionScope.user.getFirstName()} ORDER HISTORY </h4>
<table class="table table-striped table-bordered table-condensed table-hover table-custom">
  <thead class="thead-inverse">
    <tr>
      <th>Order ID</th>
      <th>Pricing</th>
      <th>Passengers</th>
      <th></th>
      <th></th>
      <th></th>
    </tr>
  </thead>
  <tbody>
        <c:forEach var="order" items="${requestScope.orderList}">
				<tr>
				<td id="col1-${order.getOrderID()}">  ${order.getOrderID()} </td>
				<td id="col2-${order.getOrderID()}">  ${order.getPrice()} </td>
				<td id="col3-${order.getOrderID()}"> ${order.getNoOfPassengers()} </td>
				<td><button type="button" class="btn btn-warning" id="viewTicket" onclick="viewTicket('${order.getOrderID()}')">VIEW TICKET</button></td>
				<td><button type="button" class="btn btn-info" id="viewPassenger" onclick="viewPassenger('${order.getOrderID()}')">VIEW PASSENGERS</button></td>
				<td><a href="../airline/pdfTicketView.do?orderid=${order.getOrderID()}" target="_blank"><button type="button" class="btn btn-danger" id="pdfTicket">Generate Ticket</button></a></td>
				
				</tr>
		</c:forEach>
</tbody>
  </table>
  
  
  <div class="modal fade" id="ticketsubgridModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog " style="max-width: 80% !important; width: auto !important;">
    <div class="modal-content modal-content-width" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Ticket Details</h4>
      </div>
      <div id="modalBody" class="modal-body modal-body-height" > 
         <table id="ticketGrid"></table>
	     <div id="ticketGrid" class="jqgtabs"></div>
     </div>
   </div>
  </div>
</div>

 <div class="modal fade" id="passengersubgridModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog " style="max-width: 80% !important; width: auto !important;">
    <div class="modal-content modal-content-width" >
      <div class="modal-header">
       <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Passenger Details</h4>
      </div>
      <div id="modalBody" class="modal-body modal-body-height" > 
         <table id="passengerGrid"></table>
	     <div id="passengerGrid" class="jqgtabs"></div>
     </div>
   </div>
  </div>
</div>
  
</div>
</body>
</html>
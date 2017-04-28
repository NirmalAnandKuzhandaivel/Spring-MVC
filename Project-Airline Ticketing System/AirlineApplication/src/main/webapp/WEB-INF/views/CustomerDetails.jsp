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
<h2>PASSENGER DETAILS</h2>
<div class=row>
   <div class=" table-responsive"> 
   <form:form  action="PaymentDetails.do" method="POST">      
   <table class="table table-striped table-bordered table-condensed table-hover table-custo">
    <thead>
      <tr>
        <th>Passenger</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Age</th>
        <th>Proof Type</th>
        <th>Proof Details</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach begin="1" end="${sessionScope.noofPeople}" var="val">
                <tr>
                    <td> <input class="form-control" name="passenger${val}" value="${val}"/></td>
                    <td><input class="form-control" name="firstName${val}"/></td>
                    <td> <input class="form-control" name="lastName${val}"/></td>
                    <td> <input class="form-control" name="age${val}"/></td>
                    <td>
                    <select id="role" class="form-control" style="" size="1">									
									<option>PASSPORT</option>
									<option>LICENSE</option>
								</select>
					</td>
                    <td><input class="form-control" name="proofDetails${val}"/> </td>
                </tr>
         </c:forEach>
    </tbody>
  </table>
  <button type="submit" class="button button-block" />PAYMENT</button>   
  
 </form:form>
  </div>
 
  </div>
</div>
</body>
</html>
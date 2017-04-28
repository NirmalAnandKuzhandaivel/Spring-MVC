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
	src="<c:url value="/resources/js/admin/flightDetails.js"/>"></script>
<script type="text/javascript"
			src="<c:url value="/resources/js/jqgrid/i18n/grid.locale-en.js"/>"></script>
 <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
 <script src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/additional-methods.min.js"></script>
	
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
var strings = ["Flight ID","Flight Company","From","To","Start Date","Start Time","Reach Date","Reach Time" ,"Price Each","Tickets Available"            
             ];
            $(function () {
               /*  $('#startDatePicker').datepicker();
                $('#endDatePicker').datepicker(); */
                $('#startTimePicker').timepicker();
                $('#endTimePicker').timepicker();
                $('#addFlightModal').hide();
                
                
            });
        </script>
        
        
 <script type="text/javascript">
(function($,W,D)
{
    var JQUERY4U = {};

    JQUERY4U.UTIL =
    {
        setupFormValidation: function()
        {
            //form validation rules
            $("#addFlightForm").validate({
                rules: {
                	flightID: {
                		required:true,
                		alphanumeric: true
                	},
                	flightCompany: {
                		required:true,
                		lettersonly: true                 		
                    } ,
                    fromPlace: {
                		required:true,
                		lettersonly: true
                	},
                	toPlace: {
                		required:true,
                		lettersonly: true
                	},
                	  startDate: {
                		required:true,
                		//dateITA: true                 		
                    },
                    endDate: {
                		required:true,
                		//dateITA: true                 		
                    },
                    startTime: {
                		required:true,
                		//dateITA: true                 		
                    },
                    endTime: {
                		required:true,
                		//dateITA: true                 		
                    }, 
                    price: {
                		required:true,
                		integer: true                 		
                    },
                    ticketAvailability: {
                		required:true,
                		integer: true                 		
                    }
                },
                messages: {
                	flightID:"Alphanumeric",
                	flightCompany: "Please only letters",
                	fromPlace:"Please only letters",
                	toPlace:"Please only letters",
                	 startDate:"Please choose date",
                		endDate: "Please choose date",
                		startTime: "Please Enter start Time",
                		endTime:"Please Enter end Time", 
                		price:"Enter only Integer",
                		ticketAvailability:"Enter only Integer"
                    
                },
                submitHandler: function(form) {
                    //form.submit();
                	 $("#addFlightForm").submit(function(e){
                  	   e.preventDefault();
  						 $.ajax({
  			                    url: "saveFlightDetails.do",
  			                    type: "POST",
  			                    data: $(this).serialize(),
  			                    
  			    				success : function(response) {
  			    					var html = "";
  			    					if (response[0].errorMessage != undefined && response[0].errorMessage != "") {
  			    						alertHandling($("#errorSection"), response[0].errorMessage);
  			    						$('#successSection').html("");
  			    						$('#successSection').hide();
  			    					} else {
  			    						if (response[0].successMsg != null && response[0].successMsg != "") {
  			    							$('#addFlightModal').modal("hide");
  			    							alertHandling($("#successSection"), "Record Updated");
  			    							
  			    							$('#errorSection').hide();
  			    							$("#errorSection").html('');
  			    							$("#flightDetailsGrid").jqGrid('setGridParam', {
  			    								datatype : "json",
  			    								page : 1
  			    							}).trigger("reloadGrid");
  			    						} else {
  			    						}
  			    					}
  			    				},
  			    				error : function(jqXHR, error, errorThrown) {
  			    					error_alert_handling(jqXHR.status, "#errorSection",
  			    							"Record Not Saved");
  			    				}
  			                });
  					});
                }
            });
        }
    }

    //when the dom has loaded setup form validation rules
    $(D).ready(function($) {
        JQUERY4U.UTIL.setupFormValidation();
    });

})(jQuery, window, document);
</script>
        

</head>
<body>
        <script>
$(document).ready(function () {

  $("#startDatePicker").datepicker({
      //dateFormat: "dd/MM/yy",
      minDate: 0,
      onSelect: function (date) {
          var date2 = $('#startDatePicker').datepicker('getDate');
          date2.setDate(date2.getDate() + 1);
          $('#endDatePicker').datepicker('setDate', date2);
          //sets minDate to dt1 date + 1
          $('#endDatePicker').datepicker('option', 'minDate', date2);
      }
  });
  $('#endDatePicker').datepicker({
     // dateFormat: "dd/MM/yy",
      onClose: function () {
          var dt1 = $('#startDatePicker').datepicker('getDate');
          var dt2 = $('#endDatePicker').datepicker('getDate');
          //check to prevent a user from entering a date below date of dt1
          if (dt2 <= dt1) {
              var minDate = $('#endDatePicker').datepicker('option', 'minDate');
              $('#endDatePicker').datepicker('setDate', minDate);
          }
      }
  });
});
</script>
<div class="airline-container">
   <div class="alert alert-danger" id="errorSection" style="display: none;"></div>
	<div class="alert alert-success" id="successSection" style="display: none;"></div>
	<table id="flightDetailsGrid"></table>
	<div id="flightDetails-pager" class="jqgtabs"></div>
	<div class="row ticketFieldRow adminticketField">
	<button type="button" id="addFlight" class="btn btn-success"
			title="Add new Flight">	ADD		
	</button>
	<button type="button" id="updateFlight" class="btn btn-primary"
			title="Update Flight"> DELETE
	</button>
	</div>

		<div class="modal fade" id="addFlightModal"
			class="modal-body modal-body-height">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					area-hidden="true">&times;</button>
				<h4 class="modal-title" id="mufgModalLabel">
					<span class="label label-info">Add Flight Details</span>
				</h4>
			</div>
			<div id="mufgModalBody" class="modal-body modal-body-height">
			<div id="statusMessage"	class="alert" style="display:none">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
					</div>
			<div class="starter-template">
				<form:form action="saveFlightDetails.do" method="POST"
					modelAttribute="flightDetails" id="addFlightForm" novalidate="novalidate" class="form">

					<div class="field-wrap">
						<form:input type="text" class="form-control"
							placeholder="Flight ID" path="flightID" id="flightID"
							name="flightID" ></form:input>
						<form:errors path="flightID" />
					</div>

					<div class="field-wrap">
						<form:input type="text" class="form-control"
							placeholder="Flight Company" path="flightCompany"
							id="flightCompany" name="flightCompany" ></form:input>
						<form:errors path="flightCompany" />
					</div>

					<div class="top-row">
						<div class="field-wrap">
							<form:input type="text" class="form-control"
								placeholder="From Place" path="fromPlace" id="fromPlace"
								name="fromPlace" ></form:input>
							<form:errors path="fromPlace" />
						</div>
						<div class="field-wrap">
							<form:input type="text" class="form-control"
								placeholder="To Place" path="toPlace" id="toPlace"
								name="toPlace" ></form:input>
							<form:errors path="toPlace" />
						</div>
					</div>

					<div class="top-row">
						<div class="field-wrap">
							<form:input class="form-control" 
								placeholder="Start Date" path="startDate" id="startDatePicker"
								name="startDate" readonly="true" ></form:input>
							<form:errors path="startDate" />
						</div>

						<div class="field-wrap">
							<form:input class="form-control"
								placeholder="Start Time" path="startTime" id="startTimePicker"
								name="startTime" ></form:input>
							<form:errors path="startDate" />
						</div>
					</div>
					
					<div class="top-row">
						<div class="field-wrap">
							<form:input type="text" class="form-control"
								placeholder="End Date" path="endDate" id="endDatePicker"
								name="endDate" readonly="true" ></form:input>
							<form:errors path="endDate" />
						</div>
						<div class="field-wrap">
							<form:input type="text" class="form-control"
								placeholder="Start Time" path="endTime" id="endTimePicker"
								name="endTime" ></form:input>
							<form:errors path="startDate" />
						</div>
					</div>

					<div class="top-row">
						<div class="field-wrap">
							<form:input type="text" class="form-control" placeholder="Price"
								path="price" id="price" name="price" ></form:input>
							<form:errors path="price" />
						</div>

						<div class="field-wrap">
							<form:input type="text" class="form-control"
								placeholder="No of Tickets" path="ticketAvailability"
								id="ticketAvailability" name="ticketAvailability"
								></form:input>
							<form:errors path="ticketAvailability" />
							
						<div class="row ticketFieldRow adminticketField">
						<button type="submit" id="submitBtn" class="btn btn-primary">Save</button>
						</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	</div>

</body>
</html>
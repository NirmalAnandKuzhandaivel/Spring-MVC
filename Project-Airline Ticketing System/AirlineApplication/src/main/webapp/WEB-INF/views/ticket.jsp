
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <script type="text/javascript"
	 src="<c:url value="/resources/js/jquery/jquery-2.0.3.js"/>"></script>
<script type="text/javascript"
   src="<c:url value="/resources/js/bootstrap/bootstrap.min.js"/>"></script>
<script type="text/javascript"
	 src="<c:url value="/resources/js/register/register.js"/>"></script>
<script type="text/javascript"
   src="<c:url value="/resources/js/bootstrap/bootstrap-datepicker.js"/>"></script>
 <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
 <script src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/additional-methods.min.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
     $('#roud').click(function () {
    $("#ret").show();//
    $("#trip").val("RoundTrip");
  });
    $('#one_wy').click(function () {
    $("#ret").hide();// button text will be "finished!"
    $("#trip").val("Oneway");
  })
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
            $("#airline-form").validate({
                rules: {
                	fly_from: {
                		required:true,
                		lettersonly: true
                	},
                	fly_to: {
                		required:true,
                		lettersonly: true                 		
                    } ,
                    /*  departDate: {
                		required:true,
                		dateITA: true
                	}, 
                	returnDate: {
                		required:true,
                		dateITA: true                 		
                    } */
                },
                messages: {
                	fly_from:"Please enter To Place",
                	fly_to: "Please enter To Place",
                	/* departDate:"Please choose Departure Date",
                	returnDate:"Please choose Return Date" */
                    
                },
                submitHandler: function(form) {
                    form.submit();
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
<body>

        <script>
$(document).ready(function () {

  $("#departDatePicker").datepicker({
      //dateFormat: "dd/MM/yy",
      minDate: 0,
      onSelect: function (date) {
          var date2 = $('#departDatePicker').datepicker('getDate');
          date2.setDate(date2.getDate() + 1);
          $('#returnDatePicker').datepicker('setDate', date2);
          //sets minDate to dt1 date + 1
          $('#returnDatePicker').datepicker('option', 'minDate', date2);
      }
  });
  $('#returnDatePicker').datepicker({
     // dateFormat: "dd/MM/yy",
      onClose: function () {
          var dt1 = $('#departDatePicker').datepicker('getDate');
          var dt2 = $('#returnDatePicker').datepicker('getDate');
          //check to prevent a user from entering a date below date of dt1
          if (dt2 <= dt1) {
              var minDate = $('#returnDatePicker').datepicker('option', 'minDate');
              $('#returnDatePicker').datepicker('setDate', minDate);
          }
      }
  });
});
</script>



<div class="starter-template">
	
	<div class="btn-group" data-toggle="buttons">
		<button type="button" id="one_wy" class="btn btn-primary">One way</button>
		<button type="button" id="roud" class="btn btn-primary " style="">Two way</button>
	</div>
	<form:form class="form" action="ViewSchedules.do"  id="airline-form" novalidate="novalidate">
	    <div class="form-group">
	        Flying From:
	        <input class="form-control" name="fly_from" id="fly_from" placeholder="City or Airport">
	    </div>
	    <div class="form-group">
	     Flying to:
	      <input class="form-control" name="fly_to" id="fly_to" placeholder="City or Airport">
	    </div>
	    <div class="form-group" >
	     Departing:
	     <input  class="form-control" name="departDate" id="departDatePicker" placeholder="mm/dd/yyyy" readonly="true">
	    </div>
	    <div class="form-group" style="display:none;" id="ret">
	      Returning:
	      <input class="form-control" name="returnDate" id="returnDatePicker" placeholder="mm/dd/yyyy" readonly="true">
	    </div>
	    <div class="dropdown">
	     No of people:
		  <select class="form-control" id="no_people" name="noofPeople">
		    <option value="1">1</option>
		    <option value="2">2</option>
		    <option value="3">3</option>
		    <option value="4">4</option>
		    <option value="5">5</option>
		</select>
	     
       </div>
         <input class="hidden" name="trip" id="trip"/>
       <div class="field-wrap">
       <button type="submit" class="button button-block">SEARCH</button>    
      </div>   
 </form:form>

</div>
</body>

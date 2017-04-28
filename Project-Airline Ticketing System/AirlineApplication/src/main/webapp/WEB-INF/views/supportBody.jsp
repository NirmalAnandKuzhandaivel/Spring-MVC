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
	src="<c:url value="/resources/js/admin/message.js"/>"></script>
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
var messagesHeader = ["ID","Reference ID","User email","Subject","Body" ,"Message Type" ,"Status"," "       
             ];
        
        </script>
    
</head>
<body>
<div class="airline-container">
	<table id="messagesGrid"></table>
	<div id="messagesGrid" class="jqgtabs"></div>
	
	
	
<div class="modal fade" id="notes-modal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header chartsModalHeader">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h2 class="modal-title">REPLY MODAL</h2>
      </div>
      <div class="modal-body">
         <div class="alert alert-info" id="reponse_alert" style="display: none;"></div>
        <!-- Division for text-box -->
        <div> 
          <h5>ADD REPLY</h5>
          <textarea id="note_txt_box" rows="7" cols="70" 
                     data-output="note_txt_box_footer"
                    style="resize:none;background-color:#d9edf7">
          </textarea>          
        </div>        
      </div>
      <div class="modal-footer">       
        <button id="note_md_save_btn" type="button" class="btn btn-success" onClick="note_md_save_note()">SEND</button>
      </div>
    </div>
  </div>
</div>


</div>
</body>
</html>
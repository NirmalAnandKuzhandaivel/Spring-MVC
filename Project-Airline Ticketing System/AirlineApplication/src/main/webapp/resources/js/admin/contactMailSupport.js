function mailPopup(){
	$("#mailSubject").val("");
	$("#mailBody").val("");
	$("#mailSubject").attr('disabled',false);
	$("#mailBody").attr('disabled',false);
	$("#sendMail").attr('disabled',false);
	$('#contactSupportModal').modal('show');
	$("#contactSupportModalErrorSection").html("");
	$("#contactSupportModalErrorSection").hide();
	$("#mailStatus").hide();
}

function sendMail(){
	var currentUrl = document.URL;
	sendSupportMail("contactSupportMail.do?currentUrl="+currentUrl+ "&mailSubject="+ $('#mailSubject').val() + "&mailBody="+ $('#mailBody').val() +"&r="+Math.random());
	
}

function closeMailPopup(){
	$("#mailSubject").val("");
	$("#mailBody").val("");
	$("#mailSubject").attr('disabled',false);
	$("#mailBody").attr('disabled',false);
	$("#sendMail").attr('disabled',false);
	$("#contactSupportModalErrorSection").html("");
	$("#contactSupportModalErrorSection").hide();
	$("#mailStatus").hide();
}

function sendSupportMail(uri){
	$("#mailStatus").show();
	$.ajax({
        type: "GET",
        url: uri,
        accepts : "json",
        mimeType: 'application/json',
        beforeSend: function(xhr) {  
            xhr.setRequestHeader("Accept", "application/json");  
            xhr.setRequestHeader("Content-Type", "application/json");  
        },
        success: function( response ){
        	if(response[0].errorMessage != undefined && response[0].errorMessage != ""){
    	    	$("#contactSupportModalErrorSection").html(response[0].errorMessage);  
    	    	$("#contactSupportModalErrorSection").show();    
    	    	error_alert_handling(-1, "#contactSupportModalErrorSection", response[0].errorMessage);
	    	}else{
	    		$("#contactSupportModalErrorSection").html("<strong>Info!</strong> Your Message has been sent to our support team. They will get back to you as soon as possible.");
	    		$("#contactSupportModalErrorSection").show();
	    		$("#mailStatus").hide();
          		$('#contactSupportModal').modal('hide');
          		$("#messagesGrid").jqGrid('setGridParam', {
					datatype : "json",
					page : 1
				}).trigger("reloadGrid");
	    		$("#mailSubject").attr('disabled',true);
	    		$("#mailBody").attr('disabled',true);
	    		$("#sendMail").attr('disabled',true);
	    	}
        },
        error: function(jqXHR,error, errorThrown) {  
        	error_alert_handling(jqXHR.status, "#contactSupportModalErrorSection", "Unable to send mail.");
        }
	});
}

function error_alert_handling(status_code, alert_id, error_msg) {
	$(alert_id).text('');
	if (status_code == 0) {
		error_msg = "Server not connected. " + error_msg;
	} else if (status_code == 404) {
		error_msg = "Requested page not found. " + error_msg;
	} else if (status_code == 500) {
		error_msg = "Internal server error. " + error_msg;
	} 
	$(alert_id).text(error_msg);
	$(alert_id).css("display", "inherit");
	$(alert_id).fadeIn().fadeOut(5000);
}
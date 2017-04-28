$(document).ready(function(){
	
	 $("#messagesGrid")
		.jqGrid( {
			url:'viewMessageAsJson.do',
			datatype : "json",
			mtype : "POST",
			cache: false,
			autowidth : true,
			loadonce : true,
			sortable : true,
			height : 300,
			// Change
			colNames : messagesHeader,
			colModel : [ {
				name : 'messageID',
				index : 'messageID',
			    autowidth : true
			},{
				name : 'referenceID',
				index : 'referenceID',
			    autowidth : true
			},{
				name : 'userEmail',
				index : 'userEmail',
			    autowidth : true
			},{
				name : 'messageHeader',
				index : 'messageHeader',
			    autowidth : true
			},{
				name : 'messageText',
				index : 'messageText',
			    autowidth : true
			},{
				name : 'messageType',
				index : 'messageType',
			    autowidth : true
			},{
				name : 'status',
				index : 'status',
			    autowidth : true,
			    hidden : true
			},
			{
				name : 'ReplyIcon', index : 'ReplyIcon', width:28, search:false,hidden:false,
				formatter: function (cellvalue, options, rowObject) {
					var message_id = rowObject["messageID"];
					var messageType = rowObject["messageType"];
					var status=rowObject["status"];
					
					var btn_id = "message_ack_" + message_id;
					var classVal = 'btn-add';
					if(status!=2){
					return "<input id=" + btn_id + " name='btnManuAck' " +
					       "class='" + classVal + "' " + "type='button' title='Response' " +
						   "onClick='sendReply(" + message_id + ")'/>";
					}else{
						return "";
					}
				},
			}],
			onSelectRow: function(rowId){
				
			},
		
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#message-pager',
		caption : "MESSAGE DATA",
		// Change
		multipleSearch : true,
		toolbar : [ false, "top" ],
		hidegrid: false
		});
	
});

function sendReply(obj){
	$("#notes-modal").modal('show');
	$("#note_txt_box").val("");
	$("#note_md_save_btn").attr('onClick', 'note_md_save_note(' + obj + ')');
}

function note_md_save_note(obj) {
	
	var msg_text = $("#note_txt_box").val().trim();
	if (msg_text.length <= 0) {
		$("#note_alert").fadeIn().fadeOut(5000);
		return;
	}
	var uri="replyRequest.do?message_id="+obj+"&msg_txt="+msg_text;
	$.ajax({
        type: "GET",
        url: uri,
        accepts : "json",
        mimeType: 'application/json',
        beforeSend: function(xhr) {  
            xhr.setRequestHeader("Accept", "application/json");  
            xhr.setRequestHeader("Content-Type", "application/json");  
        },
		success: function(response) {
			if(response[0].errorMessage != undefined && response[0].errorMessage != ""){
    	    	$("#reponse_alert").html(response[0].errorMessage);  
    	    	$("#reponse_alert").show();    
    	    	error_alert_handling(-1, "#reponse_alert", response[0].errorMessage);
	    	}else{
	    		$("#reponse_alert").html("<strong>Your response to customer has beens sent</strong> ");
	    		$("#reponse_alert").show();
	    		//$("#mailStatus").hide();
	    		$("#note_txt_box").attr('disabled',true);
	    		$("#note_md_save_btn").attr('disabled',true);
	    		//$("#notes-modal").modal('hide');
	    	}
		},
		error: function(jqXHR,error, errorThrown) {
			error_alert_handling(jqXHR.status, "#reponse_alert", "Unable to send mail.");
			
		}   				
});
}

$(document).ready(function(){
	
	$("#deleteCustomerBtn").prop("disabled",true);
	$("#updateCustomerBtn").prop("disabled",true);
	
	 $("#usersGrid")
		.jqGrid( {
			url:'viewUsersJson.do',
			datatype : "json",
			mtype : "POST",
			cache: false,
			autowidth : true,
			loadonce : true,
			sortable : true,
			height : 300,
			// Change
			colNames : customerHeader,
			colModel : [ {
				name : 'id',
				index : 'id',
			    autowidth : true
			},{
				name : 'firstName',
				index : 'firstName',
			    autowidth : true
			},{
				name : 'lastName',
				index : 'lastName',
			    autowidth : true
			},{
				name : 'emailID',
				index : 'emailID',
			    autowidth : true
			},{
				name : 'roleType',
				index : 'roleType',
			    autowidth : true
			},{
				name : 'password',
				index : 'password',
			    autowidth : true
			}],
			onSelectRow: function(rowId){
				$("#deleteCustomerBtn").prop("disabled",false);
				$("#updateCustomerBtn").prop("disabled",false);
			},
		
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#users-pager',
		caption : "USERS DATA",
		// Change
		multipleSearch : true,
		toolbar : [ false, "top" ],
		hidegrid: false
		});
	 
	 function alertHandling(alert, msg) {
			alert.text(msg);
			alert.css("display", "inherit").fadeOut(2000);
		}
	 
	 $("#deleteCustomerBtn").click(function() {
			var rowId = $("#usersGrid").jqGrid('getGridParam', 'selrow');
			if (rowId == null) {
				$('#successSection').html("");
				$('#successSection').hide();
				alertHandling($("#errorSection"),"Select Row to Delete Record");
			} else {
				var rowData = $("#usersGrid").jqGrid('getRowData', rowId);
				var uri = "deleteRecord.do?id=" + rowData.id;
				console.log(uri);
				callAjaxToDeleteCustomer(uri);
			}
    });
	 
	 function callAjaxToDeleteCustomer(uri){
		 $.ajax({
				type : "GET",
				url : uri,
				accepts : "json",
				cache : false,
				mimeType : 'application/json',
				beforeSend : function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success : function(response) {
					var html = "";
					if (response[0].errorMessage != undefined && response[0].errorMessage != "") {
						alertHandling($("#errorSection"), response[0].errorMessage);
						$('#successSection').html("");
						$('#successSection').hide();
					} else {
						if (response[0].successMsg != null && response[0].successMsg != "") {
							alertHandling($("#successSection"), "Record deleted");
							$('#errorSection').hide();
							$("#errorSection").html('');
							$("#usersGrid").jqGrid('setGridParam', {
								datatype : "json",
								page : 1
							}).trigger("reloadGrid");
						} else {
						}
					}
				},
				error : function(jqXHR, error, errorThrown) {
					error_alert_handling(jqXHR.status, "#errorSection",
							"Unable to delete Record.");
				}
			});
		}
	 
	
});


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
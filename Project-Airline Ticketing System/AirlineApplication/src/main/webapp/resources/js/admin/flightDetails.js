$(document).ready(function() {
                   $("#flightDetailsGrid")
							.jqGrid( {
								url:'FlightsJson.do',
								datatype : "json",
								mtype : "POST",
								cache: false,
								autowidth : true,
								loadonce : true,
								sortable : true,
								height : 300,
								// Change
								colNames : strings,
								colModel : [ {
									name : 'flightID',
									index : 'flightID',
								    autowidth : true
								},{
									name : 'flightCompany',
									index : 'flightCompany',
								    autowidth : true
								},{
									name : 'fromPlace',
									index : 'fromPlace',
								    autowidth : true
								},{
									name : 'toPlace',
									index : 'toPlace',
								    autowidth : true
								},{
									name : 'startDate',
									index : 'startDate',
								    autowidth : true
								},{
									name : 'startTime',
									index : 'startTime',
								    autowidth : true
								},{
									name : 'endDate',
									index : 'endDate',
								    autowidth : true
								},{
									name : 'endTime',
									index : 'endTime',
								    autowidth : true
								},{
									name : 'price',
									index : 'price',
								    autowidth : true
								},{
									name : 'ticketAvailability',
									index : 'ticketAvailability',
								    autowidth : true
								}],
								onSelectRow: function(rowId){
									$("#updateFlight").prop("disabled",false);
								},
							
							viewrecords : true,
							rowNum : 10,
							rowList : [ 10, 20, 30 ],
							pager : '#flightDetails-pager',
							caption : "FLIGHT DETAILS",
							// Change
							multipleSearch : true,
							toolbar : [ false, "top" ],
							hidegrid: false
							});	
                   
                   jQuery("#flightDetailsGrid").jqGrid('navGrid',
							'#flightDetails-pager', {
								edit : false,
								add : false,
								del : false
							});
                   
                   
                   $('#addFlight').click(function(){
                  	 $('#addFlightModal').modal("show");
                  	$('#flightID').val("");
                  	$('#flightCompany').val("");
                  	$('#fromPlace').val("");
                  	$('#toPlace').val("");
                  	$('#startDate').val("");
                  	$('#endDate').val("");
                  	$('#startTime').val("");
                  	$('#endTime').val("");
                  	$('#price').val("");
                	$('#ticketAvailability').val("");
                  	 
                  });
                   
                   
                   
                   
                /*  $("#addFlightForm").submit(function(e){
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
			    						$('#addFlightModal').modal("hide");
			    					} else {
			    						if (response[0].successMsg != null && response[0].successMsg != "") {
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
					});*/
                   
                   
                   $('#addFlightModal').on('hidden.bs.modal', function () {
						console.log("HERERE");
						$("#flightDetailsGrid").jqGrid('setGridParam', {
								   datatype:"json",
								   page:1
								  }).trigger("reloadGrid");
					});
                   
                   $("#updateFlight").click(function() {
           			var rowId = $("#flightDetailsGrid").jqGrid('getGridParam', 'selrow');
           			if (rowId == null) {
           				$('#successSection').html("");
           				$('#successSection').hide();
           				alertHandling($("#errorSection"),"Select Row to Delete Record");
           			} else {
           				var rowData = $("#flightDetailsGrid").jqGrid('getRowData', rowId);
           				var uri = "deleteFlightRecord.do?flightID=" + rowData.flightID;
           				console.log(uri);
           				callAjaxToDeleteFlight(uri);
           			}
               });
           	 
           	 function callAjaxToDeleteFlight(uri){
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

function alertHandling(alert, msg) {
	alert.text(msg);
	alert.css("display", "inherit").fadeOut(2000);
}


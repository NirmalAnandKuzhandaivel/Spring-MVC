$(document).ready(function() {
	var from_place= $("#toPlace").val();
	var to_place= $("#fromPlace").val();
    var return_date= $("#returnDate").val();
	var trip= $("#trip").val();
	$("#next-page").prop("disabled",true);
	$("#submitPage").prop("disabled",true);
	 $("#returnScheduleGrid")
		.jqGrid( {
			url:'returnFlightsJson.do?from_place='+from_place+'&to_place='+to_place+'&return_date='+return_date,
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
				name:"check_box",
				index:"check_box",
				search: false,
				width:25,
				formatter: function (cellValue, options) {
				    return '<input type="radio" name="radioSelect" id="airline_radio_' + options.rowId + '" onclick="radioCheck(this)" />';
				}},{	
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
				radioCheckreturn(rowId);
				$("#next-page").prop("disabled",false);
				$("#submitPage").prop("disabled",false);
			},
			gridComplete: function () {
				
				//alert("hello");
			},
			loadComplete: function(data) {
				//alert("Diana");
				var $this = $(this);
				var datatype = $this.getGridParam('datatype');
			    if (datatype === "xml" || datatype === "json") {
			    	if(data[0].errorCode != undefined && data[0].errorCode != ""){
			    		$('#errorSection').html(data[0].errorMessage);
			    		$('#errorSection').show();
					}else{
			    		$('#errorSection').html("");
			    		$('#errorSection').hide();
			    	}
			    }
			},
		
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#flightDetails-pager',
		caption : from_place.toUpperCase() + " TO " + to_place.toUpperCase() + " FLIGHT DETAILS",
		// Change
		multipleSearch : true,
		toolbar : [ false, "top" ],
		hidegrid: false
		});
	
	
	 
});

function radioCheckreturn(rowID){
	
	var myGrid = $('#returnScheduleGrid');
	
    var selRowId = myGrid.jqGrid ('getGridParam', 'selrow');
    var flightID = myGrid.jqGrid ('getCell', selRowId, 'flightID');
   
    var uri="saveReturnSession.do" + "?flightID="+flightID;
	//alert(flightID);
    $.ajax({
		type: "GET",
		cache:false,
		url: uri,		
		success:function(){

       	},
        error:function(){
        	alert("Error has been encountered while processing request, please contact system administrator!");
        }   				
	});
 }
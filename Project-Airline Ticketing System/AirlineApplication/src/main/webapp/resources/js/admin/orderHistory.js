$(document).ready(function() {
	
	 
});

function viewTicket(obj){
	//alert(obj);
	var uri="getTicketsForOrder.do?orderID="+obj;
	$('#ticketsubgridModal').modal('show');
	getTicketDetails(uri);
}

function viewPassenger(obj){
	var uri="getPassengerForOrder.do?orderID="+obj;
	$('#passengersubgridModal').modal('show');
	console.log(uri);
	getPassengerDetails(uri);
}

function getTicketDetails(uri){
		jQuery("#ticketGrid").jqGrid({
		url:uri,
		datatype: "json",
		loadonce: true,
		colNames: ticketHeaders,
		colModel: [
				{name:"ticketID",index:"ticketID", autowidth:true},
				{name:"flightID",index:"flightID",autowidth:true},
				{name:"flightCompany",index:"flightCompany",autowidth:true},
				{name:"fromPlace",index:"fromPlace",autowidth:true},
				{name:"toPlace",index:"toPlace",autowidth:true},
				{name:"startDate",index:"startDate",autowidth:true},
				{name:"startTime",index:"startTime",autowidth:true, align:'right'},
				{name:"endDate",index:"endDate",autowidth:true},
	            {name:"endTime",index:"endTime",autowidth:true},
	            {name:"price",index:"price",autowidth:true},
				{name:"ticketType",index:"ticketType",autowidth:true}
		],
		sortable:false,
	    height: '100%',
	    width:'100%',
	    viewrecords : true,
	    rowNum : 20,
		rowList : [  20, 30, 40 ],
		pager : "#"+"Ticket" ,
	    caption: "TICKET DETAILS",
	    	
	});
}
		
		
function getPassengerDetails(uri){
			jQuery("#passengerGrid").jqGrid({
			url:uri,
			datatype: "json",
			loadonce: true,
			colNames: passengerHeaders,
			colModel: [
					{name:"passengerID",index:"passengerID", autowidth:true},
					{name:"firstName",index:"firstName",autowidth:true},
					{name:"lastName",index:"lastName",autowidth:true},
					{name:"age",index:"age",autowidth:true},
					{name:"idProof",index:"idProof",autowidth:true},
					
			],
			sortable:false,
		    height: '100%',
		    width:'100%',
		    viewrecords : true,
		    rowNum : 20,
			rowList : [  20, 30, 40 ],
			pager : "#"+"Passenger" ,
		    caption: "Passenger DETAILS",
		    	
		});
	
}
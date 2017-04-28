<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-default navbar-fixed-top bs-docs-nav" role="navigation">
 <a href="#" class="navbar-brand logo-align"><img src="/airline/resources/images/logo.png"></a>
 
 <ul class="nav navbar-nav" id="Iconsnav"> 
      <li class="menuhighlight ticket-icon " id="TICKET"><a class="" href="TICKET.do"> <span class="menutexalign">Ticket</span></a></li>
      <li class="menuhighlight history-icon" id="HISTORY"><a href="TICKET_HISTORY.do"><span class="menutexalign">History</span></a></li>
      <li class="menuhighlight profile-icon" id="PROFILE"><a href="PROFILE_EXT.do"><span class="menutexalign">Profile</span></a></li>

     
      <c:choose>
		<c:when test="${IsAdmin == true}">
			<li class="menuhighlight admin-icon" id="ADMIN"><a href="../airline/admin/addFlightDetails.do" target="_blank"><span class="menutexalign">Admin</span></a></li>
		 </c:when>
		
	  </c:choose>
	    <c:choose>
		<c:when test="${IsSupport == true}">
			<li class="menuhighlight support-icon" id="SUPPORT"><a href="../airline/support/SupportDetails.do" target="_blank"><span class="menutexalign">Support</span></a></li>
		 </c:when>
		
	  </c:choose>
	
     
    </ul>

	<ul class="nav navbar-nav navbar-right">

		<li class="dropdown airline-dropdown">
			<div class="dropdown-toggle airline-dropdown-toggle"
				data-toggle="dropdown">
				<c:choose>
					<c:when test="${not empty sessionScope.user}">
						<img class="img-circle"
							src="${sessionScope.user.getPhotoPathName()}" />
					</c:when>
					<c:otherwise>
						<img class="img-circle" src="/airline/resources/images/photo.jpg" />
					</c:otherwise>
				</c:choose>
				<br> <a href="#" id="active_user">${sessionScope.name}</a>
			</div> 
		<li class="divider"></li>

		<c:choose>
			<c:when test="${IsCustomer == true}">
				<li onclick="mailPopup()"><a href="javascript:void(0);"
					id="contactSupport"><button type="button"
							class="btn btn-danger">Contact Support</button></a></li>
			</c:when>

		</c:choose>

		<li><a href="../airline/logout.do"><button type="button"
					class="btn btn-danger">Logout</button></a></li>
		

	</ul>

</nav>

<div class="modal fade" id="contactSupportModal" tabindex="-1" role="dialog" aria-labelledby="securityModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="max-width: 50% !important; width: auto !important;">
    <div class="modal-content modal-content-width">
      <div class="modal-header chartsModalHeader">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="closeMailPopup()">&times;</button>
        <%-- <h4 class="modal-title" id="securityModalLabel"><span class="label label-info"><spring:message code='security.Modal.mappingDetails'/></span></h4> --%>
        <h4 class="modal-title" id="securityModalLabel">Expedia Contact Support</h4>
      </div>
      
      <div id="contactSupportModalBody" class="modal-body modal-body-height">
      	<div class="alert alert-info" id="contactSupportModalErrorSection" style="display: none;"></div>
      	<table>
	      	<tr>
	      		<td><b>Subject</b></td>
	      	</tr>
	      	<tr>
	      		<td><input type="text" id="mailSubject" style="width:300px;background-color:#d9edf7"/></td>
	      	</tr>
      		<tr>
      			<td><b>Body</b></td>
			</tr>
	      	<tr>
	      		<td><textarea rows="10" cols="60" id="mailBody" draggable="false" style="resize:none;background-color:#d9edf7"></textarea></td>
			</tr>
			<tr>
      			<td><button type="button" class="btn btn-danger" id="sendMail" value="Send Mail" onclick="sendMail()">SEND</button><div id="mailStatus">Please wait ...</div></td>
      		</tr>
      	</table>
      </div>
    </div>
  </div>
</div>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="cust" uri="/WEB-INF/tlds/custom"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>A sample custom tag</title>
  </head>
  <body>
    <c:choose>
      
        <c:when test="${requestScope.custom == 'tagging'}">
            <cust:Message message="${param['message']}"/>
        </c:when>
        <c:otherwise>
            <form method="POST" enctype="multipart/form-data" action="custom.htm?action=upload">
                Enter the File Name: <input type="text" name="message"><br />  
                <input type="hidden" name="filePresent" value="name"/>
                <input type="submit" value="Upload"> Press here to upload the file!
            </form>
        </c:otherwise>
            
    </c:choose>
  </body>
</html>

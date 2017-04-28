<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <title>File Upload Example</title>
  <script language="JavaScript" type="text/javascript">
  function ButtonClick()
  {
    document.csvFileUpload.csvFile.click();
    document.csvFileUpload.csvPath.value = document.csvFileUpload.csvFile.value;
  }
  </script>
</head>
<body>
    
   
  <form name="csvFileUpload" action="home.htm">   
    <input type="file" name="csvFile" style="display:none;">  
    <input type="text" name="csvPath"/> 
    <input type="hidden" value="one" name="navigate"/>
    <input type="button" onclick="ButtonClick();" 
         value="Choose File" style="background: lightcoral;">
    <input type="SUBMIT" value="SUBMIT"/>
  </form>
    
    
    <br>
        <br>
        <form method="POST" action="home.htm">
            <input type="hidden" value="two" name="navigate"/>
        <table border="2">
            <tr>
                <td><input type="SUBMIT" value="SUBMIT"/></td>
                <td colspan="8"></td>
            </tr>
            <tr>
                <th>Sales Order ID</th>
                <th>Revision No</th>
                <th>Order Date</th>
                 <th>Due Date</th>
                <th>Ship Date</th>
                <th>Status</th>
                 <th>Order Online Flag</th>
                <th>Sales Order No</th>
                <th>Purchase Order No</th>
                
                
            </tr>
        <c:forEach var="salesOrder" items="${requestScope.salesOrderList}">
            <tr>
                
                <td> <input type="text" value=${salesOrder.salesOrderID} /></td>
                <td> <input type="text" value=${salesOrder.revisionNumber} /> </td>
                <td> <input type="text" value=${salesOrder.orderDate} /></td>
                 <td> <input type="text" value=${salesOrder.dueDate} /></td>
                <td> <input type="text" value=${salesOrder.shipDate} /> </td>
                <td> <input type="text" value=${salesOrder.status} /></td>
                 <td> <input type="text" value=${salesOrder.orderOnlineFlag} /></td>
                <td> <input type="text" value=${salesOrder.salesOrderNumber} /> </td>
                <td> <input type="text" value=${salesOrder.purchaseOrderNumber} /></td>
                 
            </tr>
            
        </c:forEach>
        </table>
        </form>
                    
</body>
</html>

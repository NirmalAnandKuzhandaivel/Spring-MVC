<%-- 
    Document   : CartItemsView
    Created on : Feb 3, 2016, 4:22:27 PM
    Author     : Nimi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashSet"%>
<%@page import="com.neu.webtools.bean.CartItem"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
table, th, td {
    border: 1px solid black;
}
</style>
    </head>
    <body>
        <table>
  <tr>
    <th>Item Name</th>
    <th>Item Price</th>
     <th>Link to Remove</th>
  </tr>
        <%
           HashSet<CartItem> cartItemSet = new HashSet<>();
           cartItemSet = (HashSet<CartItem>) session.getAttribute("cartItemList");
           Iterator iterator = cartItemSet.iterator();
           float totalPrice=0;
           int temp=cartItemSet.size();
           while (iterator.hasNext()){
               
               CartItem c = (CartItem) iterator.next();
               String itemName = c.getItemName();
               int itemPrice = c.getItemPrice();
               totalPrice=totalPrice + itemPrice;
               %>
               <tr>
                   <td><jsp:expression>itemName</jsp:expression></td>
               <td><jsp:expression>itemPrice</jsp:expression></td>
               <td><a href='removeItem.do?action=remove&itemName=<jsp:expression>itemName</jsp:expression>'>Remove Item</a></td>
        </tr>
               <jsp:scriptlet> 
           } </jsp:scriptlet>
        <tr>
            <td>Total Price</td>
            <td></td>
            <td><jsp:expression>totalPrice</jsp:expression></td>
        </tr>
        </table>
        <br>
                  <a target = "rightframe" href="books.do"> Return to Books</a>
                 <a target = "rightframe" href="music.do"> Return to Musics</a>
                 <a target = "rightframe" href="laptops.do"> Return to Laptops</a>
    </body>
</html>

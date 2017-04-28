<%-- 
    Document   : index
    Created on : Feb 7, 2016, 3:05:50 PM
    Author     : Nimi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-color: lightgoldenrodyellow">
        <h1>How many books you want to add</h1>
        <br>
        <br>
        <form method='POST' action='addBook.do'>
        <input type="text" name="booksNo"/>
       
        <input type="SUBMIT" value="SUBMIT">
        </form>
    </body>
</html>

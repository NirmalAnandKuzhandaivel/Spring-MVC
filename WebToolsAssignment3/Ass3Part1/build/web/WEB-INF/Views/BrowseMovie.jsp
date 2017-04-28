<%-- 
    Document   : BrowseMovie
    Created on : Feb 7, 2016, 10:25:57 AM
    Author     : Nimi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-color:lightgreen">
        <h1>Searching Movie</h1>
        <br>
        <br>
        <form method='POST' action='Search.do'>
        Keyword  <input type="text" name="keyword"/>
        <br>
        <br>
        <input type="radio" name="searchRadio" value="title">Search by Title
        <br>
        <br>
        <input type="radio" name="searchRadio" value="actor">Search by Actor
        <br>
        <br>
        <input type="radio" name="searchRadio" value="actress">Search by Actress
        <br>
        <br>
        <br>
        <input type='submit' value="SEARCH"/>
        </form>
        
         
    </body>
</html>

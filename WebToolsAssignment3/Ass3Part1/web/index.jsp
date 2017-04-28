<%-- 
    Document   : index
    Created on : Feb 7, 2016, 10:07:11 AM
    Author     : Nimi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-color:lightcoral">
        <h1>Welcome to our movie store</h1>
        <br>
        <h2>Please make your selection below</h2>
        <br>
        <form action='movie.do' method="POST">
        <select name="selectOption">
        <option value="browseMovie">Browse Movie</option>
       <option value="addMovie">Add New Movies in database</option>
        </select>
       <input type='submit' value='Submit'/>
        </form>
       
        </body>
</html>

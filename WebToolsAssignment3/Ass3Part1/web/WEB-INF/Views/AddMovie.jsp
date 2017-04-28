<%-- 
    Document   : AddMovie
    Created on : Feb 7, 2016, 10:25:47 AM
    Author     : Nimi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-color:lightsalmon">
        <h1>Add Movie</h1>
        <br>
        <h2>Please enter the details below</h2>
        <form method="POST" action="addMovie.do">
            Movie Title  <input type="text" name="movieTitle"/>
            <br> <br>
            Lead Actor   <input type="text" name="actor"/>
            <br> <br>
            Lead Actress <input type="text" name="actress"/>
            <br> <br>
            Genre        <input type="text" name="genre"/>
            <br> <br>
            Year         <input type="text" name="year"/>
            <br> <br>
            <input type="submit" value="ADD MOVIE"/>
        </form>
    </body>
</html>

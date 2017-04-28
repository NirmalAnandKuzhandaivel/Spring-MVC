<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-color: lightgoldenrodyellow">
        <h1>How many books you want to add</h1>
        <br>
        <br>
        <form method='POST' action='addBook.htm'>
        <input type="text" name="booksNo"/>
        <input type="hidden" value="one" name="page"/>
       
        <input type="SUBMIT" value="SUBMIT">
        </form>
    </body>
</html>

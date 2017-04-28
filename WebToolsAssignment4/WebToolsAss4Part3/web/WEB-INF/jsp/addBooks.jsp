<%-- 
    Document   : addBooks
    Created on : Feb 19, 2016, 3:40:14 PM
    Author     : Nimi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-color: lightsalmon">
        <form method="POST" action="addBook.htm">
        <table border="1">
            <tr>
                <th>ISBN</th>
                <th>Book Title</th>
                <th>Authors</th>
                <th>Price</th>
            </tr>
            <c:forEach begin="1" end="${requestScope.bookCount}" var="val">
                <tr>
                    <td> <input type="text" name="isbN${val}"/></td>
                    <td><input type="text" name="title${val}"/></td>
                    <td> <input type="text" name="author${val}"/></td>
                    <td><input type="text" name="price${val}"/> </td>
                </tr>
            </c:forEach>
        </table>
            </br>
            </br>
             <input type="hidden" value="second" name="page"/>
             <input type="hidden" value="${requestScope.bookCount}" name="count"/>
            <input type="submit" value="SUBMIT"/>
        </form>
    </body>
</html>

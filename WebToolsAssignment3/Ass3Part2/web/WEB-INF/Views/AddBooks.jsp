<%-- 
    Document   : AddBooks
    Created on : Feb 8, 2016, 11:24:57 AM
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
    <body style="background-color: lightsalmon">
        <form method="POST" action="addToDB.do">
        <table border="1">
            <tr>
                <th>ISBN</th>
                <th>Book Title</th>
                <th>Authors</th>
                <th>Price</th>
            </tr>
            <c:forEach begin="1" end="${sessionScope.noOfBooks}" var="val">
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
            <input type="submit" value="SUBMIT"/>
        </form>
    </body>
</html>

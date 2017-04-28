<%-- 
    Document   : ViewMovies
    Created on : Feb 7, 2016, 2:02:18 PM
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
    <body style="background-color:lightseagreen">
        <h1> You searched for  <c:out value="${sessionScope.keyword}"></c:out></h1>
        <h2>Here are the Search Results</h2>
        <br>
        <br>
        <table border="2">
            <tr>
                <th>Title</th>
                <th>Actor</th>
                <th>Actress</th>
                <th>Genre</th>
                <th>Year</th>
            </tr>
        <c:forEach var="movie" items="${sessionScope.movieList}">
            <tr>
                
                <td><c:out value="${movie.title}"></c:out></td>
                 <td><c:out value="${movie.actor}"></c:out></td>
                  <td><c:out value="${movie.actress}"></c:out></td>
                   <td><c:out value="${movie.genre}"></c:out></td>
                    <td><c:out value="${movie.year}"></c:out></td>
                 
            </tr>
            
        </c:forEach>
        </table>
         <a href="index.jsp">Click here to go back to main Page</a>
    </body>
</html>

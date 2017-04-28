/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webtools.assignment3.controller;

import com.webtools.assignment3.bean.Movie;
import com.webtools.assignment3.dao.FetchConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nimi
 */
public class SearchMovieController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           String keyword=request.getParameter("keyword");
           String columnName=request.getParameter("searchRadio");
                       
            String dbuser=getServletContext().getInitParameter("dbuser");
            String driver=getServletContext().getInitParameter("driver");
            String dburl=getServletContext().getInitParameter("dburl");
            String pass=getServletContext().getInitParameter("password");
                       

            Connection conn=FetchConnection.establishConnectionJDBC(dbuser,pass,driver,dburl);
             PreparedStatement ps = null;
             
        String query="select * from movieTable where "+columnName+" like '%"+keyword+"%'";
            ps = conn.prepareStatement(query);
            // ps.setString(1, keyword);
          //   ps.setString(2, keyword);
            HttpSession session=request.getSession();
             
            ResultSet resultSet=ps.executeQuery();
             ArrayList<Movie> movieList=new ArrayList<>();
                    while(resultSet.next()){
                        Movie movie=new Movie();
                        movie.setTitle(resultSet.getString("Title"));
                        movie.setActor(resultSet.getString("Actor"));
                        movie.setActress(resultSet.getString("Actress"));
                        movie.setGenre(resultSet.getString("Genre"));
                        movie.setYear(resultSet.getString("year"));
                        movieList.add(movie);
                    }
            ps.close();  
            if(movieList.size()>0){
            session.setAttribute("keyword", keyword);
            session.setAttribute("size", movieList.size());
                     session.setAttribute("movieList", movieList);
                     
                     RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/Views/ViewMovies.jsp");
                    rd.forward(request, response);
            }
            else{
                 RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/Views/MovieNotFound.jsp");
                    rd.forward(request, response);
            }
          
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SearchMovieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SearchMovieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webtools.assignment3.controller;

import com.webtools.assignment3.dao.FetchConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nimi
 */
@WebServlet(name = "AddMovieController", urlPatterns = {"/addMovie.do"})
public class AddMovieController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException ,SQLException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            String title=request.getParameter("movieTitle");
            String actor=request.getParameter("actor");
            String actress=request.getParameter("actress");
            String genre=request.getParameter("genre");
            int  year=Integer.parseInt(request.getParameter("year"));
            
            String dbuser=getServletContext().getInitParameter("dbuser");
            String driver=getServletContext().getInitParameter("driver");
            String dburl=getServletContext().getInitParameter("dburl");
            String pass=getServletContext().getInitParameter("password");
                    
            Connection conn=FetchConnection.establishConnectionJDBC(dbuser,pass,driver,dburl);
             PreparedStatement ps = null;
        
            ps = conn.prepareStatement("insert into movieTable(Title,Actor,Actress, genre,year) values (?,?,?,?,?)");
            ps.setString(1, title);
            ps.setString(2, actor);
            ps.setString(3, actress);
            ps.setString(4, genre);
             ps.setInt(5, year);
             
            ps.execute();
            ps.close();
       
             RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/Views/AddMovieConfirmation.jsp");
            rd.forward(request, response);
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
            Logger.getLogger(AddMovieController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddMovieController.class.getName()).log(Level.SEVERE, null, ex);
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

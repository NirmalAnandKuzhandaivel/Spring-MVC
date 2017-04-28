/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webtools.assignment3.controller;

import com.webtools.assignment3.bean.Book;
import com.webtools.assignment3.dao.FetchConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
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
public class AddBookController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session=request.getSession();
            String value=(String) session.getAttribute("noOfBooks");
            int countValue=Integer.parseInt(value);
            
            ArrayList<Book> bookList=new ArrayList<>();
            for(int i=1;i<=countValue;i++){
                String isBN=null,title=null,author=null;
                float price=0;
                Enumeration<String> bookNames=request.getParameterNames();
                while(bookNames.hasMoreElements()){
                    String paramName=bookNames.nextElement();
                    if(paramName.contains(String.valueOf(i))){
                        if(paramName.contains("isbN"))
                            isBN=request.getParameter(paramName);
                        if(paramName.contains("title"))
                            title=request.getParameter(paramName);
                        if(paramName.contains("author"))
                            author=request.getParameter(paramName);
                        if(paramName.contains("price"))
                            price=Float.parseFloat(request.getParameter(paramName));   
                    }
                    
                }                
                Book b=new Book();
                b.setAuthor(author);
                b.setPrice(price);
                b.setiSBN(isBN);
                b.setTitle(title);
                bookList.add(b);  
              }
            String dbuser=getServletContext().getInitParameter("dbuser");
            String driver=getServletContext().getInitParameter("driver");
            String dburl=getServletContext().getInitParameter("dburl");
            String pass=getServletContext().getInitParameter("password");
                    
            Connection conn=FetchConnection.establishConnectionJDBC(dbuser,pass,driver,dburl);
             PreparedStatement ps = null;
             
             for(Book book:bookList){
        
            ps = conn.prepareStatement("insert into booksTable(isbn,title,author,price) values (?,?,?,?)");
            ps.setString(1, book.getiSBN());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setFloat(4, book.getPrice());
             ps.execute();
             }
             
           
            ps.close();
            
             RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/Views/AddBookConfirmation.jsp");
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
            Logger.getLogger(AddBookController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddBookController.class.getName()).log(Level.SEVERE, null, ex);
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

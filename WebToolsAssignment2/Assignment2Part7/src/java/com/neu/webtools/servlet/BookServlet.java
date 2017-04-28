/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.webtools.servlet;

import com.neu.webtools.bean.CartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nimi
 */
public class BookServlet extends HttpServlet {

   
    public static List<CartItem> setBooks(){
        
        List<CartItem> booksList=new ArrayList<>();
        CartItem c1=new CartItem();
        c1.setItemName("Object Oriented Programming");
        c1.setItemPrice(200);
        c1.setItemType("Book");
        
        CartItem c2=new CartItem();
        c2.setItemName("Head First Servlets and Jsp");
        c2.setItemPrice(200);
        c2.setItemType("Book");
                
        booksList.add(c1);
        booksList.add(c2);
        
        return booksList;
        
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<CartItem> cartBooksList=new ArrayList<>();
        cartBooksList=setBooks();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BookServlet</title>");            
            out.println("</head>");
            out.println("<body><form submit='post' action='AddandViewCart.do'>");
            out.println("<h2>Books </h2>");
            
            for(CartItem c:cartBooksList){
                 String itemName=c.getItemName();
                 int itemPrice=c.getItemPrice();
                 String itemType=c.getItemType();
                 String checkboxItem=itemName+'['+itemPrice+']';
                 
                 out.println("<input type='checkbox' name='item' value='"+itemName+"'/>" + checkboxItem + "<br>");
             }
            
            out.println("<input type='hidden'  value='add' name='action'/>");
            out.println("<input type='submit'  value='Add to Cart'/>");
            out.println("<a target = 'rightframe' href='CartItemsView.jsp'> View Cart</a>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.webtools.servlet;

import com.neu.webtools.bean.CartItem;
import static com.neu.webtools.servlet.BookServlet.setBooks;
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
public class LaptopServlet extends HttpServlet {

    public static List<CartItem> setLaptops(){
        
        List<CartItem> laptopList=new ArrayList<>();
        CartItem c1=new CartItem();
        c1.setItemName("Dell");
        c1.setItemPrice(500);
        c1.setItemType("Laptop");
        
        CartItem c2=new CartItem();
        c2.setItemName("Acer");
        c2.setItemPrice(600);
        c2.setItemType("Laptop");
                
        laptopList.add(c1);
        laptopList.add(c2);
        
        return laptopList;
        
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         List<CartItem> cartLaptopList=new ArrayList<>();
        cartLaptopList=setLaptops();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BookServlet</title>");            
            out.println("</head>");
            out.println("<body><form submit='post' action='AddandViewCart.do'>");
            out.println("<h2>Books </h2>");
            
            for(CartItem c:cartLaptopList){
                 String itemName=c.getItemName();
                 int itemPrice=c.getItemPrice();
                 String itemType=c.getItemType();
                 String checkboxItem=itemName+'['+itemPrice+']';
                 
                 out.println("<input type='checkbox' name='item' value='"+itemName+"'/>" + checkboxItem + "<br>");
             }
            
            out.println("<input type='hidden'  value='add' name='action'/>");
            out.println("<input type='submit'  value='Add to Cart'/>");
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

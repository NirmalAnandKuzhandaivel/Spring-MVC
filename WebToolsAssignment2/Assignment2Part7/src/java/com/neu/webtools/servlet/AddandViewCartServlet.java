/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.webtools.servlet;

import com.neu.webtools.bean.CartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nimi
 */
public class AddandViewCartServlet extends HttpServlet {

   private List<CartItem> bookCart;
   private List<CartItem> musicCart;
   private List<CartItem> laptopCart;
   private Set<CartItem> cartItemSet;

    public AddandViewCartServlet() {
        bookCart = BookServlet.setBooks();
        musicCart = MusicServlet.setMusic();
        laptopCart = LaptopServlet.setLaptops();
    }
    

   
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         HttpSession session = request.getSession();
        if (session.getAttribute("cartItemList") != null) {
            cartItemSet = (Set<CartItem>) session.getAttribute("cartItemList");
        } else {
            cartItemSet = new HashSet<CartItem>();
        }
        try{
            String action=request.getParameter("action");
            System.out.println(action);
            if(action.equalsIgnoreCase("add")){
                String[] selectedItems = request.getParameterValues("item");
                for (int i = 0; i < selectedItems.length; i++) {
                    for (CartItem item : bookCart) {
                        if (item.getItemName().equalsIgnoreCase(selectedItems[i])) {
                            cartItemSet.add(item);

                        }
                    }
                    for (CartItem item : musicCart) {
                        if (item.getItemName().equalsIgnoreCase(selectedItems[i])) {
                            cartItemSet.add(item);

                        }
                    }
                    for (CartItem item : laptopCart) {
                        if (item.getItemName().equalsIgnoreCase(selectedItems[i])) {
                            cartItemSet.add(item);

                        }
                    }
                }
            }else if(action.equalsIgnoreCase("remove")){
               boolean check=false;
               String removeItem=request.getParameter("itemName");
               Iterator iterator=cartItemSet.iterator();
               while(iterator.hasNext())
               {
                   if(check)break;
                   CartItem cart=(CartItem)iterator.next();
                   if(cart.getItemName().equals(removeItem)){
                       cartItemSet.remove(cart);
                       check=true;
                   }
               }
           }
           session.setAttribute("cartItemList", cartItemSet);
           response.sendRedirect("CartItemsView.jsp");
            
        }catch(Exception e){
            e.printStackTrace();
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

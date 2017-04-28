/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webTools.controller;

import com.webTools.Bean.Book;
import com.webTools.DAO.BookDAO;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Nimi
 */
public class BookController implements Controller {
    
    private BookDAO bookDAO;
    public BookController(BookDAO bookDAO) {
        this.bookDAO=bookDAO;
    }
    
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
    
        
        ModelAndView mv=new ModelAndView();
        
       
        String page=hsr.getParameter("page");
        if(page.equalsIgnoreCase("one")){
            int bookCount=Integer.parseInt(hsr.getParameter("booksNo"));
            mv.addObject("bookCount", bookCount);
            mv.setViewName("addBooks");
        }else if(page.equalsIgnoreCase("second")){
            String value=hsr.getParameter("count");
            int countValue=Integer.parseInt(value);
            
            ArrayList<Book> bookList=new ArrayList<>();
            for(int i=1;i<=countValue;i++){
                String isBN=null,title=null,author=null;
                float price=0;
                Enumeration<String> bookNames=hsr.getParameterNames();
                while(bookNames.hasMoreElements()){
                    String paramName=bookNames.nextElement();
                    if(paramName.contains(String.valueOf(i))){
                        if(paramName.contains("isbN"))
                            isBN=hsr.getParameter(paramName);
                        if(paramName.contains("title"))
                            title=hsr.getParameter(paramName);
                        if(paramName.contains("author"))
                            author=hsr.getParameter(paramName);
                        if(paramName.contains("price"))
                            price=Float.parseFloat(hsr.getParameter(paramName));   
                    }
                    
                }                
                Book b=new Book();
                b.setAuthor(author);
                b.setPrice(price);
                b.setiSBN(isBN);
                b.setTitle(title);
                bookList.add(b);  
              }
            
            bookDAO.addtoDB(bookList);
            mv.setViewName("addBooksConfirmation");
        }
        return mv;
    }
    
}

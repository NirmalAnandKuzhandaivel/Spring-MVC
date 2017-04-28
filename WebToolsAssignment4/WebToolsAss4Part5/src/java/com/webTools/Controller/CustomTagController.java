/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webTools.Controller;

import com.webTools.Bean.SalesOrder;
import com.webTools.dao.AddtoDB;
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
public class CustomTagController implements Controller {
    
    private AddtoDB csvDatatoDB;
    public CustomTagController(AddtoDB csvDatatoDB) {
        this.csvDatatoDB=csvDatatoDB;
    }
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ArrayList<SalesOrder> salesOrderList = new ArrayList<>();
        ModelAndView mv=new ModelAndView();
        String action=hsr.getParameter("action");
        if(action.equalsIgnoreCase("upload")){
          mv.setViewName("index");
          mv.addObject("custom", "tagging");
        }else if(action.equals("savetodb")){
           String s=hsr.getParameter("size");
           int countValue=Integer.parseInt(s);
           for(int i=1;i<=countValue;i++){
                String orderDate=null,duedate=null,shipDate=null,status=null,salesOrderNo=null,purchaseOrderNo=null;
                int salesOrderID=0,revisionNo=0,onlineFlag=0;
                Enumeration<String> bookNames=hsr.getParameterNames();
                
                while(bookNames.hasMoreElements()){
                    String paramName=bookNames.nextElement();
                    if(paramName.contains(String.valueOf(i))){
//                        if(paramName.contains("salesOrderNo"))
//                            salesOrderID=Integer.parseInt(hsr.getParameter(paramName));
                        if(paramName.contains("revisiobNo"))
                            revisionNo=Integer.parseInt(hsr.getParameter(paramName));
                        if(paramName.contains("orderDate"))
                            orderDate=hsr.getParameter(paramName);
                        if(paramName.contains("dueDate"))
                            duedate=hsr.getParameter(paramName); 
                        if(paramName.contains("shipDate"))
                            shipDate=hsr.getParameter(paramName);
                        if(paramName.contains("status"))
                            status=hsr.getParameter(paramName);
                        if(paramName.contains("orderFlag"))
                            onlineFlag=Integer.parseInt(hsr.getParameter(paramName));
                        if(paramName.contains("salesOrderNo"))
                            salesOrderNo=hsr.getParameter(paramName);
                        if(paramName.contains("purchaseorderNo"))
                            purchaseOrderNo=hsr.getParameter(paramName);
                    }
                    
                }                
                SalesOrder sales=new SalesOrder();
                sales.setSalesOrderID(salesOrderID);
                sales.setRevisionNumber(revisionNo);
                sales.setOrderDate(orderDate);
                sales.setShipDate(shipDate);
                sales.setDueDate(duedate);
                sales.setSalesOrderNumber(salesOrderNo);
                sales.setOrderOnlineFlag(onlineFlag);
                sales.setPurchaseOrderNumber(purchaseOrderNo);
                sales.setStatus(status);
                salesOrderList.add(sales);
                int n = csvDatatoDB.csvDataAddToDB(salesOrderList);
                System.out.println(n);
                mv.setViewName("index");
              }
        }
        return mv;
        
    }
    
}

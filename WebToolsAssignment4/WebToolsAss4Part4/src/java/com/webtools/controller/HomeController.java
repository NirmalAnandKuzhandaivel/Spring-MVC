/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webtools.controller;

import com.webtools.bean.SalesOrder;
import com.webtools.dao.CsvDataDAO;
import com.webtools.dao.CsvDatatoDB;
import com.webtools.dao.FetchCsvConnectionDAO;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.relique.jdbc.csv.CsvDriver;

import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Nimi
 */
public class HomeController implements Controller {
    
    private CsvDataDAO csvDataDAO;
    private CsvDatatoDB csvdbDAO;
    public HomeController(CsvDataDAO csvDataDAO,CsvDatatoDB csvdbDAO) {
        this.csvDataDAO=csvDataDAO;
        this.csvdbDAO=csvdbDAO;
    }
    
    

    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ArrayList<SalesOrder> salesOrderList = new ArrayList<>();
        String action = hsr.getParameter("csvPath");
        String navigate=hsr.getParameter("navigate");
        ModelAndView mv = new ModelAndView();
        HttpSession session = hsr.getSession();
        System.out.println(action);
        if(navigate.equalsIgnoreCase("one")){
        salesOrderList = CsvDataDAO.getSalesOrderList();
        mv.addObject("salesOrderList", salesOrderList);
        session.setAttribute("salesOrderList", salesOrderList);
        mv.setViewName("index");
        }else if(navigate.equalsIgnoreCase("two")){
            int no=csvdbDAO.csvDataAddToDB((ArrayList<SalesOrder>) session.getAttribute("salesOrderList"));
            System.out.println(no);
            mv.setViewName("index");
        }
        return mv;
    }
    
    
}

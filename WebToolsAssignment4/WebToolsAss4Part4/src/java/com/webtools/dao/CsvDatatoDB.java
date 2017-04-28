/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webtools.dao;

import com.webtools.bean.SalesOrder;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nimi
 */
public class CsvDatatoDB {
    
    public int csvDataAddToDB(ArrayList<SalesOrder> salesOrderList) throws IOException, SQLException{
        
        Connection con=FetchConnection.establishConnectionJDBC();
        PreparedStatement ps = null;
        String query="insert into salesOrder(SALESORDERID,REVISONNO,ORDER_DATE,DUE_DATE,SHIP_DATE"
                +",STATUS,ORDER_ONLINE_FLAG,SALES_ORDER_NO,PURCHASE_ORDER_NO)values(?,?,?,?,?,?,?,?,?)";
        ps = con.prepareStatement(query);
       // System.out.println(salesOrderList.size());
        for(SalesOrder s:salesOrderList){
           ps.setInt(1, s.getSalesOrderID());
           ps.setInt(2, s.getRevisionNumber());
           ps.setString(3, s.getOrderDate());
           ps.setString(4, s.getDueDate());
           ps.setString(5, s.getShipDate());
           ps.setString(6, s.getStatus());
           ps.setInt(7, s.getOrderOnlineFlag());
           ps.setString(8, s.getSalesOrderNumber());
           ps.setString(9, s.getPurchaseOrderNumber());
           ps.addBatch();
        }
        ps.executeBatch();
        return salesOrderList.size();
    }
}

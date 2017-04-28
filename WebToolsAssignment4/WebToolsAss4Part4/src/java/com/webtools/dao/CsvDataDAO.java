/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webtools.dao;

import com.webtools.bean.SalesOrder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nimi
 */
public class CsvDataDAO {
    
    public static ArrayList<SalesOrder> getSalesOrderList() throws SQLException{
        
         ArrayList<SalesOrder> salesOrderList=new ArrayList<>();
        Connection conn = FetchCsvConnectionDAO.FetchConnection();
        java.sql.Statement stmt = conn.createStatement();
        
        String query=" SELECT SalesOrderID,RevisionNumber,OrderDate,DueDate,ShipDate,Status,OnlineOrderFlag,"
                + "SalesOrderNumber,PurchaseOrderNumber,AccountNumber,CustomerID,SalesPersonID,TerritoryID,"
                + "BillToAddressID,ShipToAddressID,ShipMethodID,CreditCardID,CreditCardApprovalCode,CurrencyRateID,SubTotal,"
                + "TaxAmt,Freight,TotalDue,Comment,ModifiedDate FROM SalesOrder";

        ResultSet resultSet = stmt.executeQuery(query);
        StringBuffer buffer=new StringBuffer();
        while(resultSet.next()){
            SalesOrder s=new SalesOrder();
            s.setSalesOrderID(resultSet.getInt(1));
            s.setRevisionNumber(resultSet.getInt(2));
            s.setOrderDate(resultSet.getString(3));
            s.setDueDate(resultSet.getString(4));
            s.setShipDate(resultSet.getString(5));
            s.setStatus(resultSet.getString(6));
            s.setOrderOnlineFlag(resultSet.getInt(7));
            s.setSalesOrderNumber(resultSet.getString(8));
            s.setPurchaseOrderNumber(resultSet.getString(9));
            salesOrderList.add(s);
            
            
        }
        // Clean up
        conn.close();
        
        return salesOrderList;
    }
    
    
}

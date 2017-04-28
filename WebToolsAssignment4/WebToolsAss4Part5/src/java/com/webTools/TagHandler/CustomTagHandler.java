package com.webTools.TagHandler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.webTools.Bean.SalesOrder;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Nimi
 */
public class CustomTagHandler extends SimpleTagSupport {

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    //private CsvDataDAO csvDataDAO=new CsvDataDAO();
    
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
      //  StringWriter sw = new StringWriter();
       ArrayList<SalesOrder> salesOrderList = new ArrayList<>();
      
        try {
            
          
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
             out.println("<strong>" + "nirmal"+ "</strong>");
             out.println("    <blockquote>");
             
             Connection conn = null;
        try {

            Class.forName("org.relique.jdbc.csv.CsvDriver");
            conn = DriverManager.getConnection("jdbc:relique:csv:" + "F:\\Excel");
            java.sql.Statement stmt = conn.createStatement();
        
        String query=" SELECT SalesOrderID,RevisionNumber,OrderDate,DueDate,ShipDate,Status,OnlineOrderFlag,"
                + "SalesOrderNumber,PurchaseOrderNumber,AccountNumber,CustomerID,SalesPersonID,TerritoryID,"
                + "BillToAddressID,ShipToAddressID,ShipMethodID,CreditCardID,CreditCardApprovalCode,CurrencyRateID,SubTotal,"
                + "TaxAmt,Freight,TotalDue,Comment,ModifiedDate FROM SalesOrder";

        ResultSet resultSet = stmt.executeQuery(query);
       // StringBuffer buffer=new StringBuffer();
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
        conn.close();
         out.println("<form method='POST'  action='custom.htm?action=savetodb'>");
         out.println("<input type=hidden value='"+salesOrderList.size()+"' name='size'/>");
                out.println("<table border='2'>");
                out.println("<tr>");
                out.println("<td><input type='SUBMIT' value='SUBMIT'/></td>");
                out.println(" <td colspan='8'></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th>Sales Order ID</th>");
                out.println("<th>Revision No</th>");
                out.println("<th>Order Date</th>");
                out.println("<th>Due Date</th>");
                out.println("<th>Ship Date</th>");
                out.println("<th>Status</th>");
                out.println(" <th>Order Online Flag</th>");
                out.println("<th>Sales Order No</th>");
                out.println("<th>Purchase Order No</th>");             
                out.println("</tr>");
	int i=1;	
        for(SalesOrder s:salesOrderList){
                 out.println("<tr>");               
                out.println("<td> <input type='text' value='"+s.getSalesOrderNumber()+"' name=salesOrderNo"+i+"/></td>"); 
                out.println("<td> <input type='text' value='"+s.getRevisionNumber()+"' name=revisiobNo"+i+"/> </td>"); 
                out.println("<td> <input type='text' value='"+s.getOrderDate()+"' name=orderDate"+i+"/></td>"); 
                 out.println("<td> <input type='text' value='"+s.getDueDate()+"' name=dueDate"+i+"/></td>"); 
                out.println("<td> <input type='text' value='"+s.getShipDate()+"' name=shipDate"+i+"/> </td>"); 
                out.println("<td> <input type='text' value='"+s.getStatus()+"' name=status"+i+"/></td>"); 
                 out.println("<td> <input type='text' value='"+s.getOrderOnlineFlag()+"' name=orderFlag"+i+" /></td>"); 
                out.println("<td> <input type='text' value='"+s.getSalesOrderNumber()+"' name=salesOrderNo"+i+"/> </td>"); 
                out.println("<td> <input type='text' value='"+s.getPurchaseOrderNumber()+"' name=purchaseorderNo"+i+" /></td>");                  
              out.println("</tr>"); 
              i++;
            
        }
       
        out.println("</table>");
         out.println("</form>");
        } catch (Exception e) {
            e.printStackTrace();
        }

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }
            
      // getJspBody().invoke(sw);
      //getJspContext().getOut().println(sw.toString());
       
      

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in CustomTagHandler tag", ex);
        }
    }
    
}

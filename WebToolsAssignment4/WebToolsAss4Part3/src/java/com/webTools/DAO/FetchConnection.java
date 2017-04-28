/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webTools.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nimi
 */
public class FetchConnection {
    public  static Connection establishConnectionJDBC() throws IOException{
        
       
        
        
        Connection connection=null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(ClassNotFoundException e){
            System.out.println("Where is your MYSQL JDBC Driver");
            e.printStackTrace();
        }
        try{
            connection= DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=WEBTOOLS", "sa", "Summer@123");
            
        }catch(SQLException e){
            System.out.println("Connection Failed.Check output console");
            e.printStackTrace();
        }
         if(connection!=null){
             System.out.println("You made it");
         }
        return connection;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webtools.assignment3.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nimi
 */
public class FetchConnection {
    public  static Connection establishConnectionJDBC(String dbuser,String dbpassword,String dbdriver,String dbURL) throws IOException{
        
       
        
        
        Connection connection=null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(ClassNotFoundException e){
            System.out.println("Where is your MYSQL JDBC Driver");
            e.printStackTrace();
        }
        try{
            connection= DriverManager.getConnection(dbURL, dbuser, dbpassword);
            
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

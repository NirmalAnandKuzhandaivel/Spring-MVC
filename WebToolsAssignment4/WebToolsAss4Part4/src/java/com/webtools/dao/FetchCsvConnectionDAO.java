/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webtools.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Nimi
 */
public class FetchCsvConnectionDAO {
    
    public static Connection FetchConnection() {

        Connection conn = null;
        try {

            Class.forName("org.relique.jdbc.csv.CsvDriver");
            conn = DriverManager.getConnection("jdbc:relique:csv:" + "F:\\Excel");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webTools.DAO;

import com.webTools.Bean.Book;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nimi
 */
public class BookDAO {
    
    public void addtoDB(ArrayList<Book> bookList) throws IOException, SQLException{
         Connection conn=FetchConnection.establishConnectionJDBC();
             PreparedStatement ps = null;
             
             for(Book book:bookList){
        
            ps = conn.prepareStatement("insert into booksTable(isbn,title,author,price) values (?,?,?,?)");
            ps.setString(1, book.getiSBN());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setFloat(4, book.getPrice());
             ps.execute();
             }
             ps.close();
    }
}

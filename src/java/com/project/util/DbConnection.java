/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.util;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class DbConnection {
    
    Connection conn=null;
    DbConnection DbMgr=null;
    final Object lock= new Object();

    public DbConnection(String url, String user, String password, String className) throws SQLException, ClassNotFoundException {
        Class.forName(className);
        this.conn = DriverManager.getConnection(url,user,password);
    }
    
     public DbConnection getInstance(String url,String user,String password,String jdbcClass){
       synchronized(lock){
           if(DbMgr == null){
                try {
                    DbMgr = new DbConnection(url,user,password,jdbcClass);
                 } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
       }
       return DbMgr;
    }
    public Connection getConnection(){
        return this.conn;
    }
}

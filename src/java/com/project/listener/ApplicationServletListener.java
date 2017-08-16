/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.listener;

import com.project.util.DbConnection;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author hp
 */
public class ApplicationServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.print("context initialized");
    
        ServletContext contxt = sce.getServletContext();
        String user = (String) contxt.getInitParameter("user");
        String password = (String) contxt.getInitParameter("password");
        String  url = (String) contxt.getInitParameter("url");
        String className = (String) contxt.getInitParameter("className");
        DbConnection dbConn=null;
        try{
           dbConn = new DbConnection(url, user, password, className);
           contxt.setAttribute("DbConn", dbConn);
        }
        catch(Exception e){
        System.out.println("DB Failure");
         }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    System.out.print("context Destroyed...");
    }
}

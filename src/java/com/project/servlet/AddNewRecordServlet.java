/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.servlet;

import com.project.util.DbConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public class AddNewRecordServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String std_id = request.getParameter("std_id");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String contact_no = request.getParameter("contact_no");
        String program_id = request.getParameter("program_id");
        String admission_year = request.getParameter("admission_year");
        
        
        try{
        ServletContext context = getServletContext();
        DbConnection dbConn = (DbConnection) context.getAttribute("DbConn");
        Connection conn = dbConn.getConnection();
        
            System.out.println("connected to datbase...ready to execute query...");
        
        PreparedStatement stmnt  = 
                    conn.prepareStatement("insert into student_info( "
                            + "std_id,firstname,lastname,prog_id,birthday,email,gender,contact_no,admission_year)"
                            + " values (?,?,?,?,?,?,?,?,?)");

            stmnt.setString(1,std_id);
            stmnt.setString(2, firstname);
            stmnt.setString(3, lastname);
            stmnt.setString(4, program_id);
            stmnt.setString(5, birthday);
            stmnt.setString(6, email);
            stmnt.setString(7, gender);
            stmnt.setString(8,contact_no);
            stmnt.setString(9, admission_year);
            
            stmnt.execute();
            
            PrintWriter out = response.getWriter();
             out.println("<font color=green>Enrollment Successful! ");
            response.sendRedirect("HomePage.html");
             
              
        }
        catch(Exception e){
            String except = e.toString();
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, e);
        }

    
    
    
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

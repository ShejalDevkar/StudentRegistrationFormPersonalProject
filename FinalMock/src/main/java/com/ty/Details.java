//package com.ty;
//
//import java.io.IOException;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebServlet("/details")
//public class Details extends HttpServlet {
//    
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        
//        HttpSession session = req.getSession(false);
//        
//        if (session != null) {
//           
//            String username = (String) session.getAttribute("username");
//            String email = (String) session.getAttribute("email");
//            String phone = (String) session.getAttribute("phone");
//
//         
//            req.setAttribute("username", username);
//            req.setAttribute("email", email);
//            req.setAttribute("phone", phone);
//
//            
//            RequestDispatcher rd = req.getRequestDispatcher("details.jsp");
//            rd.forward(req, resp);
//        } else {
//           
//            req.setAttribute("message", "Please log in first.");
//            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
//            rd.forward(req, resp);
//        }
//    }
//}

package com.ty;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/details")
public class Details extends HttpServlet {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/finalmock";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        
        if (session != null) {
            String username = (String) session.getAttribute("username");
            System.out.println("Username from session: " + username);
            if (username != null) {
                Connection connection = null;
                PreparedStatement pstm = null;
                ResultSet rs = null;

                try {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    
                    pstm = connection.prepareStatement("SELECT email, phone, std FROM mock WHERE username = ?");
                    pstm.setString(1, username);
                    rs = pstm.executeQuery();

                    if (rs.next()) {
//                    	String username = rs.getString("username");
                        String email = rs.getString("email");
                        String phone = rs.getString("phone");
                        String std = rs.getString("std");

                        req.setAttribute("username", username);
                        req.setAttribute("email", email);
                        req.setAttribute("phone", phone);
                        req.setAttribute("std" , std);
                    } else {
                        req.setAttribute("message", "User not found.");
                    }
                } catch (SQLException e) {
                    req.setAttribute("message", "Database error: " + e.getMessage());
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (pstm != null) pstm.close();
                        if (connection != null) connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                RequestDispatcher rd = req.getRequestDispatcher("details.jsp");
                rd.forward(req, resp);
            } else {
                req.setAttribute("message", "Please log in first.");
                RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
                rd.forward(req, resp);
            }
        } else {
            req.setAttribute("message", "Please log in first.");
            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
            rd.forward(req, resp);
        }
    }
}

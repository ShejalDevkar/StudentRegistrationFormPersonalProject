package com.ty;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/register")
public class Register extends HttpServlet {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/finalmock";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String std = req.getParameter("std");

        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            
            Class.forName("org.postgresql.Driver");
            
            
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            
            
            pstm = connection.prepareStatement("INSERT INTO mock (username, email, phone, password, std) VALUES (?, ?, ?, ?, ?)");
            pstm.setString(1, username);
            pstm.setString(2, email);
            pstm.setString(3, phone);
            pstm.setString(4, password);
            pstm.setString(5, std);
            
            // Execute the update
            int insert = pstm.executeUpdate();
            
            if (insert > 0) {
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setAttribute("email", email);
                session.setAttribute("phone", phone);
                session.setAttribute("std" , std);
                req.setAttribute("message", "Registration Successful");
                RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
                rd.forward(req, resp);
            } else {
                req.setAttribute("message", "Something went wrong; please register again or try login");
                RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
                rd.forward(req, resp);
            }
            
        } catch (SQLException e) {
            req.setAttribute("message", "Email is already registered or another error occurred.");
            RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
            rd.forward(req, resp);
            e.printStackTrace(); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); 
        } finally {
            
            try {
                if (pstm != null) pstm.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace(); 
            }
        }
    }
}

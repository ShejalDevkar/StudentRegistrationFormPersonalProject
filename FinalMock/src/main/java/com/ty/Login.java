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

@WebServlet("/login")
public class Login extends HttpServlet {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/finalmock";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            
            Class.forName("org.postgresql.Driver");

           
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            
            pstm = connection.prepareStatement("SELECT username, phone, password, std FROM mock WHERE email=?");
            pstm.setString(1, email);

            
            rs = pstm.executeQuery();

            if (rs.next()) {
                
                String dbPassword = rs.getString("password");
                HttpSession session = req.getSession();
                session.setAttribute("email", email);
                session.setAttribute("username", rs.getString("username"));
                session.setAttribute("phone", rs.getString("phone"));
                session.setAttribute("std" , rs.getString("std"));

                
                if (dbPassword.equals(password)) {
                    RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
                    rd.forward(req, resp);
                } else {
                    req.setAttribute("message", "Invalid Password");
                    RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
                    rd.forward(req, resp);
                }
            } else {
                req.setAttribute("message", "User Not Registered");
                RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
                rd.forward(req, resp);
            }

        } catch (SQLException e) {
            req.setAttribute("message", "Something went wrong; please try again");
            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
            rd.forward(req, resp);
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
    }
}

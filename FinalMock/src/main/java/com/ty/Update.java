//package com.ty;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebServlet("/update")
//public class Update extends HttpServlet {
//
//    private static final String URL = "jdbc:postgresql://localhost:5432/finalmock";
//    private static final String USER = "postgres";
//    private static final String PASSWORD = "root";
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        String phone = req.getParameter("phone");
//        String std = req.getParameter("std");
//        String password = req.getParameter("password");
//
//        // Validate phone number and other inputs
//        if (std == null || phone == null || !phone.matches("\\d{10}")) {  // Assuming 10-digit phone number
//            req.setAttribute("message", "Invalid input. Please enter a valid 10-digit phone number.");
//            RequestDispatcher rd = req.getRequestDispatcher("update.jsp");
//            rd.forward(req, resp);
//            return;
//        }
//
//        HttpSession session = req.getSession();
//        String currentstd = (String) session.getAttribute("std");
//
//        Connection connection = null;
//        PreparedStatement pstm = null;
//
//        try {
//            // Load database driver
//            Class.forName("org.postgresql.Driver");
//
//            // Establish database connection
//            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//
//            // Prepare SQL query to update user details
//            String sql = "UPDATE mock SET username = ?, phone = ?, password = ?, std = ? WHERE email = ?";
//            pstm = connection.prepareStatement(sql);
//
//            // Set values in the prepared statement
//            pstm.setString(1, username);  // Set email
//            pstm.setString(2, phone);     // Set phone
//            pstm.setString(3, password);  // Set password
//            pstm.setString(4, std);       // Use std as the identifier for the record
//
//            // Execute the update query
//            int update = pstm.executeUpdate();
//
//            if (update > 0) {
//                // Update session attributes on success
//                session.setAttribute("username", username);
//                session.setAttribute("password", password);
//                session.setAttribute("phone", phone);
//                session.setAttribute("std", std);
//
//                req.setAttribute("message", "Update Successful");
//                RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
//                rd.forward(req, resp);
//            } else {
//                req.setAttribute("message", "Update failed. No matching record found.");
//                RequestDispatcher rd = req.getRequestDispatcher("update.jsp");
//                rd.forward(req, resp);
//            }
//
//        } catch (SQLException e) {
//            // Log detailed SQL error
//            e.printStackTrace();
//            req.setAttribute("message", "An error occurred during update: " + e.getMessage());
//            RequestDispatcher rd = req.getRequestDispatcher("update.jsp");
//            rd.forward(req, resp);
//        } catch (ClassNotFoundException e) {
//            // Handle class not found exception for PostgreSQL driver
//            e.printStackTrace();
//            req.setAttribute("message", "Database driver not found: " + e.getMessage());
//            RequestDispatcher rd = req.getRequestDispatcher("update.jsp");
//            rd.forward(req, resp);
//        } finally {
//            // Close resources in the finally block
//            try {
//                if (pstm != null) pstm.close();
//                if (connection != null) connection.close();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
//}

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

@WebServlet("/update")
public class Update extends HttpServlet {

    private static final String URL = "jdbc:postgresql://localhost:5432/finalmock";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String phone = req.getParameter("phone");
        String std = req.getParameter("std");
        String password = req.getParameter("password");

        // Validate phone number and other inputs
        if (std == null || phone == null || !phone.matches("\\d{10}")) {  // Assuming 10-digit phone number
            req.setAttribute("message", "Invalid input. Please enter a valid 10-digit phone number.");
            RequestDispatcher rd = req.getRequestDispatcher("update.jsp");
            rd.forward(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        String currentstd = (String) session.getAttribute("std");

        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            // Load database driver
            Class.forName("org.postgresql.Driver");

            // Establish database connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Prepare SQL query to update user details
            String sql = "UPDATE mock SET username = ?, phone = ?, password = ? WHERE std = ?";
            pstm = connection.prepareStatement(sql);

            // Set values in the prepared statement
            pstm.setString(1, username);  // Set email
            pstm.setString(2, phone);     // Set phone
            pstm.setString(3, password);  // Set password
            pstm.setString(4, std);       // Use std as the identifier for the record

            // Execute the update query
            int update = pstm.executeUpdate();

            if (update > 0) {
                // Update session attributes on success
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                session.setAttribute("phone", phone);
                session.setAttribute("std", std);

                req.setAttribute("message", "Update Successful");
                RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
                rd.forward(req, resp);
            } else {
                req.setAttribute("message", "Update failed. No matching record found.");
                RequestDispatcher rd = req.getRequestDispatcher("update.jsp");
                rd.forward(req, resp);
            }

        } catch (SQLException e) {
            // Log detailed SQL error
            e.printStackTrace();
            req.setAttribute("message", "An error occurred during update: " + e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("update.jsp");
            rd.forward(req, resp);
        } catch (ClassNotFoundException e) {
            // Handle class not found exception for PostgreSQL driver
            e.printStackTrace();
            req.setAttribute("message", "Database driver not found: " + e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("update.jsp");
            rd.forward(req, resp);
        } finally {
            // Close resources in the finally block
            try {
                if (pstm != null) pstm.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
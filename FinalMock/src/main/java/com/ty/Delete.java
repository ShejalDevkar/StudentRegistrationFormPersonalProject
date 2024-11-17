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

@WebServlet("/delete")
public class Delete extends HttpServlet {
    private static final String URL = "jdbc:postgresql://localhost:5432/finalmock";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String currentEmail = (String) session.getAttribute("email");

        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Prepare the SQL statement to delete the user
            pstm = connection.prepareStatement("DELETE FROM mock WHERE email=?");
            pstm.setString(1, currentEmail);

            // Execute the delete
            int delete = pstm.executeUpdate();

            if (delete > 0) {
                // Invalidate the session
                session.invalidate();
                req.setAttribute("message", "Account deleted successfully. Please register in again.");
                RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
                rd.forward(req, resp);
            } else {
                req.setAttribute("message", "Delete operation failed. Account not found.");
                RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
                rd.forward(req, resp);
            }

        } catch (SQLException e) {
            req.setAttribute("message", "An error occurred during deletion: " + e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
            rd.forward(req, resp);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle driver not found exception
        } finally {
            // Close resources
            try {
                if (pstm != null) pstm.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace(); // Handle close exceptions
            }
        }
    }
}

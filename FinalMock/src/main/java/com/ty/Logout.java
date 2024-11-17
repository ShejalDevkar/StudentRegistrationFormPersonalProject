package com.ty;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession(false); 
        
        if (session != null) {
            session.invalidate();
        }

        req.setAttribute("message", "You have been successfully logged out. ");
        RequestDispatcher rd = req.getRequestDispatcher("logout.jsp");
        rd.forward(req, resp);
    }
}

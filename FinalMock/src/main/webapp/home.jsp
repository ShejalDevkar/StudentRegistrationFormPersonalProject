<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%> --%>
<!--  <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href="all.css">
</head>
<body>
 <div class="home-container">
        <div class="home-box">
            <h2>Welcome, Student!</h2>
            <p>Choose an option below:</p>

            <div class="home-options">
                <!--<a href="details.jsp" class="home-link">View Details</a> 
                <a href="details.jsp" class=home-link>View Details</a>
                <a href="update.jsp" class="home-link">Update Profile</a>
                <a href="delete.jsp" class="home-link">Delete Account</a>
                <a href="logout.jsp" class="home-link">Logout</a>
                
            </div>
        </div>
    </div>

</body>
</html> -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="javax.servlet.http.HttpSession" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Home</title>
    <link rel="stylesheet" href="all.css">
</head>
<body>
    <div class="home-container">
        <div class="home-box">
            <% 
                String username = (session != null) ? (String) session.getAttribute("username") : null;
                String message = request.getParameter("message");

                if (message != null) {
            %>
                <p class="message" style="color:red;"><%= message %></p>
            <% 
                }

                if (username == null) {
            %>
                <h2>Please log in to access this page.</h2>
                <a href="login.jsp" class="home-link">Login</a>
            <% 
                } else { 
            %>
                <h2>Welcome, <%= username %>!</h2>
                <p>Choose an option below:</p>
                <div class="home-options">
                    <a href="details" class="home-link">View Details</a>
                    <a href="update.jsp" class="home-link">Update Profile</a>
                    <a href="delete.jsp" class="home-link">Delete Account</a>
                    <a href="logout" class="home-link">Logout</a>
                </div>
            <% 
                }
            %>
        </div>
    </div>
</body>
</html>




<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!--
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <title>User Details</title>
 <link rel="stylesheet" href="all.css">
</head>
<body>
<div class="details-container">
        <div class="details-box">
            <h2>User Details</h2>
            <p><strong>Username:</strong></p>
            <p><strong>Email:</strong></p>
            <p><strong>Phone:</strong></p>
            
        </div>
    </div>

</body>
</html> 

<!DOCTYPE html>
<html>
<head>
    <title>User Details</title>
    <link rel="stylesheet" href="all.css">
</head>
<body>
    <div class="details-container">
        <div class="details-box">
            <h1>User Details</h1>
            
            <%-- <% 
                String message = (String) request.getAttribute("message");
                if (message != null) {
            %>
                <p style="color:red;"><%= message %></p>
            <% 
                } else {
                    String username = (String) request.getAttribute("username");
                    String email = (String) request.getAttribute("email");
                    String phone = (String) request.getAttribute("phone");
            %>
                <p><strong>Username:</strong> <%= username %></p>
                <p><strong>Email:</strong> <%= email %></p>
                <p><strong>Phone:</strong> <%= phone %></p>
                <a href="home.jsp" class="home-link">Back to Home</a>
            <% 
                }
            %> --%>
            <a href="logout.jsp" class="home-link">Logout</a>
        </div>
    </div>
</body>
</html> -->

<!DOCTYPE html>
<html>
<head>
    <title>User Details</title>
    <link rel="stylesheet" href="all.css">
</head>
<body>
    <div class="details-container">
        <div class="details-box">
            <h1>User Details</h1>
            
            <% 
                String message = (String) request.getAttribute("message");
                if (message != null) {
            %>
                <p style="color:red;"><%= message %></p>
            <% 
                } else {
                    String username = (String) request.getAttribute("username");
                    String email = (String) request.getAttribute("email");
                    String phone = (String) request.getAttribute("phone");
                    String std = (String) request.getAttribute("std");
            %>
                <p><strong>Username:</strong>${username} </p>
                <p><strong>Email:</strong> ${email}</p>
                <p><strong>Phone:</strong>${phone} </p>
                <p><strong>Std:</strong>${std} </p>
                <a href="home.jsp" class="home-link">Back to Home</a>
            <% 
                }
            %>
           <!-- <a href="logout.jsp" class="home-link">Logout</a> -->
        </div>
    </div>
</body>
</html>



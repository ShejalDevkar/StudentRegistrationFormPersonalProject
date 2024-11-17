<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Update Profile</title>
    <link rel="stylesheet" href="all.css">
</head>
<body>
    <div class="update-container">
        <div class="update-box">
            <h2>Update Profile</h2>
           
            <%
                String message = (String) request.getAttribute("message");
                if (message != null) {
            %>
                <p style="color: red;"><%= message %></p>
                 <p style="color: <%= message.contains("successfully") ? "green" : "red" %>;">
                    <%= message %>
                </p>
            <%
                }
            %>

            <form action="update" method="post">
                <div class="update-input-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" value="<%= session.getAttribute("username") %>" required>
                </div>
                
                 <%-- <div class="update-input-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" value="<%= session.getAttribute("email") %>" required>
                </div --%>>
                
                <div class="update-input-group">
                    <label for="phone">Phone</label>
                    <input type="tel" id="phone" name="phone" value="<%= session.getAttribute("phone") %>" required>
                </div>
                
                <div class="update-input-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" value="<%= session.getAttribute("password") %>" required>
                </div>
                
                <div class="update-input-group">
                    <label for="std">Std</label>
                    <input type="text" id="std" name="std" value="<%= session.getAttribute("std") %>" required>
                </div>
                
                
                
                <input type="submit" class="update-button" value="Update">
            </form>
            <a href="home.jsp" class="home-link">Back to Home</a>
        </div>
    </div>
</body>
</html>
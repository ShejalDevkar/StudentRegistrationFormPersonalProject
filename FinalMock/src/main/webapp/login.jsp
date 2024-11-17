<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Login</title>
<link rel="stylesheet" href="login.css">
</head>
<body>
<div class="login-container">
        <div class="login-box">
        <h3>${message}</h3>
            <h2>Student Login</h2>
            <form action="login" method="post">
                <div class="login-input-group">
                    <label for="login-email">Email</label>
                    <input type="email" id="login-email" name="email" required placeholder="Enter your email">
                </div>
                <div class="login-input-group">
                    <label for="login-password">Password</label>
                    <input type="password" id="login-password" name="password" required placeholder="Enter your password">
                </div>
                <input type="submit" class="login-button" value="Login"> <br>
                <p class="login-register-text">Don't have an account? <a href="register.jsp">Register here</a>.</p>
            </form>
        </div>
    </div>

</body>
</html>
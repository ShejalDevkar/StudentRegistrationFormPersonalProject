<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Registration</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container">
        <div class="form-box">
        <h3>${message}</h3>
            <h2>Student Registration</h2>
            <form action="register" method="post">
                <div class="input-box">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required placeholder="Enter your username">
                </div>
                <div class="input-box">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required placeholder="Enter your email">
                </div>
                <div class="input-box">
                    <label for="phone">Phone</label>
                    <input type="tel" id="phone" name="phone" required placeholder="Enter your phone number">
                </div>
                <div class="input-box">
                    <label for="std">Std</label>
                    <input type="text" id="std" name="std" required placeholder="Enter your std">
                </div>
                <div class="input-box">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required placeholder="Create a password">
                </div>
                <input type="submit" value="Register" class="button"> <br>
                <p class="login-text">Already have an account? <a href="login.jsp">Login here</a>.</p>
            </form>
        </div>
    </div>

</body>
</html>
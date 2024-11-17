<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Delete Account</title>
    <link rel="stylesheet" href="all.css">
</head>
<body>
    <div class="delete-container">
        <div class="delete-box">
            <h2>Delete Account</h2>
            <p>Are you sure you want to delete your account?</p>
            <form action="delete" method="post">
                <input type="submit" class="delete-button" value="Delete Account">
            </form>
            <a href="home.jsp" class="home-link">Back to Home</a>
        </div>
    </div>
</body>
</html>

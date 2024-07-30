<%-- 
    Document   : index
    Created on : Jun 27, 2024, 7:10:39 PM
    Author     : duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="static/index.css?v=<%= System.currentTimeMillis() %>">
    </head>
    <body>
        <div class="container">
            <form action="dangnhap" method="post">
                <div class="image">
                    <img src="https://i.postimg.cc/J0QtwRjW/logo.png"/>
                </div>
              <h2>Đăng nhập</h2>
              <div class="formInput">
                <div class="input-group">
                  <label for="username">Username:</label>
                  <input type="text" id="username" name="username" required>
                </div>
                <div class="input-group">
                  <label for="password">Password:</label>
                  <input type="password" id="password" name="password" required>
                </div>
                <button type="submit">Đăng nhập</button>
              </div>
            </form>
        </div>
    </body>
</html>

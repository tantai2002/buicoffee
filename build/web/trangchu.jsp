<%-- 
    Document   : trangchu
    Created on : Jul 5, 2024, 12:11:17 AM
    Author     : duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="static/trangchu.css?v=<%= System.currentTimeMillis() %>">
    </head>
    <body>
        <div class="container">
            <div class="image"><img src="https://i.postimg.cc/J0QtwRjW/logo.png"/></div>
            <div class="options">
                <div class="option">
                    <p><a class="btnTaoBaoCao" id="btnTaoBaoCao">Tạo báo cáo hóa đơn cho năm</a></p>
                </div>
                <div class="option">
                    <p><a href="/duan1/nhaphoadon">Nhập hóa đơn thu chi</a></p>
                </div>
                <div class="option">
                    <p><a href="/duan1/xemchinhsuahd">Xem và chỉnh sửa hóa đơn</a></p>
                </div>
                <div class="option">
                    <p><a href="/duan1/thongkehoadon">Thống kê</a></p>
                </div>
            </div>
            
        </div>
        <script src="static/trangchu.js?v=<%= System.currentTimeMillis() %>"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    </body>
</html>

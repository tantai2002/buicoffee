<%-- 
    Document   : xemchinhsuahoadon
    Created on : Jul 19, 2024, 12:42:00 AM
    Author     : duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.model.hoadon"  %>
<%@page import="com.dao.xemchinhsuahdDao"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="static/xemchinhsuahoadon.css?v=<%= System.currentTimeMillis() %>">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <div class="container">
            <a class="backhome" href="trangchu"><i class="fa-solid fa-house"></i></a>
            <h1>Chỉnh sửa hóa đơn</h1>
            <div class="timkiem">
                <form action="xemchinhsuahd" method="post">
                    <label>Chọn ngày:</label>
                    <input type="date" id="ngaycantim" name="ngaycantim"/>
                    <button id="btnTim">Tìm</button>
                </form>
            </div>
            <div class="hoadontong">
                <h2>Danh sách sản phẩm ngày: <%= request.getParameter("ngaycantim") %></h2>
                <table>
                    <thead>
                        <tr>
                            <th>Mã sản phẩm</th>
                            <th>Tên sản phẩm</th>
                            <th style="display: none">Đơn vị tính</th>
                            <th style="display: none">Số lượng</th>
                            <th>Thành tiền</th>
                            <th style="display: none">Ghi chú</th>
                            <th>Chọn</th>
                        </tr>
                    </thead>
                    <tbody id="productTableBody">
                        <% 
                            if (request.getAttribute("danhSachSanPham") != null) {
                                List<hoadon> danhSachSanPham = (List<hoadon>) request.getAttribute("danhSachSanPham");
                                for (hoadon hd : danhSachSanPham) {
                        %>
                        <tr>    
                            <td><%= hd.getIdchitiethoadon() %></td>
                            <td><%= hd.getTenSanPham() %></td>
                            <td style="display: none"><%= hd.getDonViTinh() %></td>
                            <td style="display: none"><%= hd.getSoLuong() %></td>
                            <td><%= hd.getThanhTien() %></td>
                            <td style="display: none"><%= hd.getGhiChu() %></td>
                            <td><i class="btncheck fa-solid fa-check"></i></td>
                        </tr>
                        <% 
                                }
                            } else {
                        %>
                            <tr>
                                <td colspan="4">Không có dữ liệu</td>
                            </tr>
                        <% 
                            }
                        %>


                    </tbody>

                </table>
            </div>
                        
                        
            <div class="formNhapHoaDon">
                <form id="sanPham" class="sanPham" action="capnhatsanphamhd" method="post">
                    <div class="idsanpham">
                        <!--<label for="idsanpham">M sản phẩm:</label>-->
                        <input style="display: none;" type="text" id="idSanPham" name="idSanPham" required>
                    </div>
                    <div class="tenSanPham">
                        <label for="tenSanPham">Tên sản phẩm:</label>
                        <input type="text" id="tenSanPham" name="tenSanPham" required>
                    </div>
                    
                    <div class="soLuong">
                        <label for="soLuong">Số lượng:</label>
                        <input type="number" id="soLuong" name="soLuong" required>
                    </div>
                    
                    <div class="donViTinh">
                        <label for="donViTinh">Đơn vị tính:</label>
                        <input type="text "id="donViTinh" name="donViTinh" required>
                    </div>
                    
                    <div class="donGia">
                        <label for="thanhTien">Thành tiền:</label>
                        <input type="text" id="thanhTien" name="thanhTien" required><br/>
                    </div>
                    
                    <div class="ghiChu">
                        <label>Ghi chú (Nếu có):</label>
                        <textarea id="ghiChu" name="ghiChu"></textarea>
                    </div>
                    
                    <button type="submit" id="addProduct">Thêm sản phẩm</button><br><br>
                </form>
            </div>
        </div>
        <script src="static/chinhsuahoadon.js?v=<%= System.currentTimeMillis() %>"></script>
    </body>
</html>

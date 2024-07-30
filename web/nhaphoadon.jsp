<%-- 
    Document   : nhaphoadon
    Created on : Jul 7, 2024, 1:59:12 PM
    Author     : duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="static/nhaphoadon.css?v=<%= System.currentTimeMillis() %>">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <div class="container">
            <a class="backhome" href="trangchu"><i class="fa-solid fa-house"></i></a>
            <h1>Nhập hóa đơn sản phẩm</h1>
            
            <div class="formNhapHoaDon">
                <div id="sanPham" class="sanPham">
                    <div class="tenSanPham">
                        <label for="tenSanPham">Tên sản phẩm:</label>
                        <input type="text" id="tenSanPham" name="tenSanPham" required>
                    </div>
                    
                    <div class="soLuong">
                        <label for="soLuong">Số lượng:</label>
                        <input type="number" id="soLuong" name="soLuong" value="1" required>
                    </div>
                    
                    <div class="donViTinh">
                        <label for="donViTinh">Đơn vị tính:</label>
                        <select id="donViTinh">
                            <option value="Kg">Kg</option>
                            <option value="Cái">Cái</option>
                            <option value="Phần">Phần</option>
                            <option value="Thùng">Thùng</option>
                            <option value="Hộp">Hộp</option>
                            <option value="Khác" selected>Khác</option>
                        </select>
                    </div>
                    
                    <div class="donGia">
                        <label for="thanhTien">Thành tiền:</label>
                        <input type="text" id="thanhTien" name="thanhTien" required><br/>
                    </div>
                    
                    <div class="ghiChu">
                        <label>Ghi chú (Nếu có):</label>
                        <textarea id="ghiChu" name="ghiChu"></textarea>
                    </div>
                    
                    <button type="button" id="addProduct">Thêm sản phẩm</button><br><br>
                </div>
            </div>

            <form id="formNhapHoaDon">    
                <div class="tonghoadon">
                    <h4>Hóa đơn trong ngày: <input type="date" id="ngayHoaDon" class="ngayHoaDon"/></h4>
                    <table>
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên sản phẩm</th>
                                <th>Số lượng</th>
                                <th>Đơn vị tính</th>
                                <th>Thành tiền</th>
                                <th style="display: none;">Ghi chú</th>
                                <th>Xóa</th>
                            </tr>
                        </thead>
                        <tbody id="productTableBody">
                            
                            
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="8"><button type="submit" id="luuHoaDonBtn">Lưu hóa đơn</button></td>
                            </tr>
                        </tfoot>
                    </table>
                </div>

                
            </form>
        </div>
        
        <script src="static/nhaphoadon.js?v=<%= System.currentTimeMillis() %>"></script>
    </body>
</html>

<%-- 
    Document   : thongke
    Created on : Jul 26, 2024, 12:37:24 AM
    Author     : duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.model.hoadon" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="static/thongke.css?v=<%= System.currentTimeMillis() %>">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.5/xlsx.full.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <a class="backhome" href="trangchu"><i class="fa-solid fa-house"></i></a>
        <h1>Thống Kê Hóa Đơn</h1>
        <div class="btnluachon">
            <form action="thongkehoadon" method="post">
                <input type="text" name="thang" id="thang">
                <button type="submit">Chọn</button>
            </form>
            <button onclick="exportTableToExcel('myTable')">Xuất file excel</button>
            <!--            <script>
                            function formatCurrency(value) {
                                // Định dạng số với dấu phân cách hàng nghìn và đơn vị tiền tệ VND
                                return parseInt(value).toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
                            }
            
                            function exportTableToExcel(tableId) {
                                // Lấy bảng bằng ID
                                const table = document.getElementById(tableId);
            
                                // Định dạng cột "Đơn Giá" trong bảng HTML
                                const cells = table.querySelectorAll('td.dongia');
                                cells.forEach(cell => {
                                    cell.innerText = formatCurrency(cell.innerText);
                                });
            
                                // Tạo workbook và worksheet từ bảng HTML
                                const wb = XLSX.utils.table_to_book(table, {sheet: "Sheet1"});
                                const ws = wb.Sheets['Sheet1'];
            
                                // Định dạng cột "Đơn Giá" trong Excel
                                const range = XLSX.utils.decode_range(ws['!ref']);
                                for (let row = range.s.r; row <= range.e.r; row++) {
                                    const cell_address = {c: 4, r: row}; // Cột "Đơn Giá" là cột 4 (Bắt đầu từ 0)
                                    const cell_ref = XLSX.utils.encode_cell(cell_address);
                                    if (!ws[cell_ref])
                                        continue;
                                    ws[cell_ref].z = '#,##0'; // Định dạng tiền tệ với ký hiệu VND
                                }
            
                                // Tạo file Excel
                                const wbout = XLSX.write(wb, {bookType: "xlsx", type: "binary"});
            
                                // Chuyển đổi binary string thành ArrayBuffer
                                function s2ab(s) {
                                    const buf = new ArrayBuffer(s.length);
                                    const view = new Uint8Array(buf);
                                    for (let i = 0; i < s.length; i++) {
                                        view[i] = s.charCodeAt(i) & 0xFF;
                                    }
                                    return buf;
                                }
            
                                // Tạo đối tượng Blob và tải xuống file
                                const blob = new Blob([s2ab(wbout)], {type: "application/octet-stream"});
                                const link = document.createElement("a");
                                link.href = URL.createObjectURL(blob);
                                link.download = "table_data.xlsx";
                                document.body.appendChild(link);
                                link.click();
                                document.body.removeChild(link);
                            }
                        </script>-->
        </div>
        <table id="myTable">
            <thead>
                <tr>
                    <th>Ngày</th>
                    <th>Tên Sản Phẩm</th>
                    <th>Số Lượng</th>
                    <th>Đơn Vị Tính</th>
                    <th>Đơn Giá</th>
                    <th>Ghi Chú</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<hoadon> danhsachHD = (List<hoadon>) request.getAttribute("danhsachHD");
                    if (danhsachHD != null && !danhsachHD.isEmpty()) {
                        for (hoadon hoadon : danhsachHD) {
                %>
                <tr>
                    <td class="ngay"><%= hoadon.getNgayNhapHD() %></td>
                    <td><%= hoadon.getTenSanPham() %></td>
                    <td><%= hoadon.getSoLuong() %></td>
                    <td><%= hoadon.getDonViTinh() %></td>
                    <td><%= hoadon.getThanhTien() %></td>
                    <td><%= hoadon.getGhiChu() %></td>
                </tr>
                <%
            }
        } else {
                %>
                <tr>
                    <td colspan="8">Không có dữ liệu.</td>
                </tr>
                <%
            }
                %>
            </tbody>
        </table>
            
        <script src="static/thongke.js?v=<%= System.currentTimeMillis() %>"></script>
    </body>
</html>

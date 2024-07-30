/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlet;

import com.dao.capnhatsanphamhdDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duong
 */
@WebServlet(name = "capnhatsanphamhd", urlPatterns = {"/capnhatsanphamhd"})
public class capnhatsanphamhd extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Đọc dữ liệu từ form
        String maSanPham = request.getParameter("idSanPham");
        String tenSanPham = request.getParameter("tenSanPham");
        String soLuong = request.getParameter("soLuong");
        String donViTinh = request.getParameter("donViTinh");
        String thanhTien = request.getParameter("thanhTien");
        String ghiChu = request.getParameter("ghiChu");

        // In ra console để kiểm tra
        System.out.println("Tên sản phẩm: " + tenSanPham);
        System.out.println("Số lượng: " + soLuong);
        System.out.println("Đơn vị tính: " + donViTinh);
        System.out.println("Thành tiền: " + thanhTien);
        System.out.println("Ghi chú: " + ghiChu);
        boolean result;
        try {
            result = capnhatsanphamhdDao.ktSuahoaDon(maSanPham, tenSanPham, donViTinh, soLuong, thanhTien, ghiChu);
            if (result) {
                response.getWriter().write("Lưu hóa đơn thành công!");
            } else {
                response.getWriter().write("Lưu hóa đơn thất bại!");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(capnhatsanphamhd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(capnhatsanphamhd.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Đoạn code xử lý dữ liệu trong database có thể thêm ở đây
        // Chuyển hướng hoặc trả về response
//        response.sendRedirect("xemchinhsuahoadon.jsp");
        request.getRequestDispatcher("xemchinhsuahoadon.jsp").forward(request, response);
    }

}

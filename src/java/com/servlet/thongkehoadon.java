/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.dao.thongkeDao;
import com.model.hoadon;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author duong
 */
@WebServlet(name = "thongkehoadon", urlPatterns = {"/thongkehoadon"})
public class thongkehoadon extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("thongke.jsp");
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Đọc dữ liệu từ form
        String thangLuuHoaDon = request.getParameter("thang");

        // Tạo đối tượng thongkeDao
        thongkeDao dao = new thongkeDao();
        List<hoadon> danhsachHD = null;

        try {
            // Gọi phương thức thongKeHD để lấy danh sách hóa đơn
            danhsachHD = dao.thongKeHD(thangLuuHoaDon);
            System.out.println(danhsachHD);
            // Đưa danh sách hóa đơn vào thuộc tính của request
            request.setAttribute("danhsachHD", danhsachHD);

            // Chuyển hướng đến trang JSP để hiển thị kết quả
            request.getRequestDispatcher("thongke.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi và gửi thông báo lỗi về giao diện
            request.setAttribute("errorMessage", "Lỗi khi lấy dữ liệu hóa đơn. Vui lòng thử lại.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(thongkehoadon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

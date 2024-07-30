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
import com.dao.xemchinhsuahdDao;
import com.google.gson.Gson;
import com.model.hoadon;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duong
 */
@WebServlet(name = "xemchinhsuahd", urlPatterns = {"/xemchinhsuahd"})
public class xemchinhsuahd extends HttpServlet {
    
    private xemchinhsuahdDao thongtinHD = new xemchinhsuahdDao();
    
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException, ClassNotFoundException, SQLException {
//        
//        // Lấy ngày từ request
//        String ngayLuuhoadon = request.getParameter("ngaycantim");
//
//        // Gọi service để lấy danh sách sản phẩm
////        xemchinhsuahdDao thongtinHD = new xemchinhsuahdDao();
//        List<hoadon> danhSachSanPham = thongtinHD.layDanhSachSanPham(ngayLuuhoadon);
//
//        // Gửi danh sách sản phẩm lên giao diện
//        request.setAttribute("danhSachSanPham", danhSachSanPham);
//        request.getRequestDispatcher("xemchinhsuahoadon.jsp").forward(request, response);
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("xemchinhsuahoadon.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy ngày từ request
        String ngayLuuhoadon = request.getParameter("ngaycantim");

        List<hoadon> danhSachSanPham = null;
        try {
            danhSachSanPham = thongtinHD.layDanhSachSanPham(ngayLuuhoadon);
        } catch (SQLException ex) {
            Logger.getLogger(xemchinhsuahd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(xemchinhsuahd.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Gửi danh sách sản phẩm lên giao diện
        request.setAttribute("danhSachSanPham", danhSachSanPham);
        request.getRequestDispatcher("xemchinhsuahoadon.jsp").forward(request, response);


    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
/**
 *
 * @author duong
 */
public class capnhatsanphamhdDao {

    public static boolean ktSuahoaDon(String idchitiethoadon, String tensanpham,
            String donvitinh, String soluong, String thanhtien, String ghichu)
            throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/buicoffee",
                    "root", "1111"
            );
            String sql = "UPDATE chitiethoadon SET tensanpham = ?, soluong = ?, "
                    + "donvitinh = ?, dongia = ?, ghichu = ? WHERE idchitiethoadon = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, tensanpham);
            statement.setString(2, soluong);
            statement.setString(3, donvitinh);
            statement.setString(4, thanhtien);
            statement.setString(5, ghichu);
            statement.setString(6, idchitiethoadon);
            // Thực hiện câu lệnh UPDATE
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Dữ liệu đã được cập nhật thành công");
                return true;
            }

            // Đóng kết nối và chuyển hướng đến trang xemchinhsuahoadon.jsp
            statement.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
//    public static void main(String[] args) {
//        String idChiTietHoaDon = "2024070116"; // ID chi tiết hóa đơn cần chỉnh sửa
//        String tenSanPham = "Trái cây";
//        String donViTinh = "Khác";
//        int soLuong = 2;
//        String thanhTien = "280.000";
//        String ghiChu = "";
//
//        try {
//            boolean result = ktLuuhoaDon(idChiTietHoaDon, tenSanPham, donViTinh, soLuong, thanhTien, ghiChu);
//            if (result) {
//                System.out.println("Cập nhật thành công");
//            } else {
//                System.out.println("Cập nhật không thành công");
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }

}

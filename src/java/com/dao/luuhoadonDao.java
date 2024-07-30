/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.util.Random;

/**
 *
 * @author duong
 */
public class luuhoadonDao {

    public static boolean ktLuuhoaDon(String ngayNhapHD, String tensanpham,
            String donvitinh, int soluong, String thanhtien, String ghichu)
            throws ClassNotFoundException, SQLException {
        String dateString = getCurrentDateAsString(ngayNhapHD);
        String randomNumbers = generateRandomNumbers(2);

        // Tạo chuỗi kết hợp ngày tháng năm và số ngẫu nhiên
        String id = dateString + randomNumbers;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/buicoffee",
                    "root", "1111"
            );

            // Truy vấn SQL để lấy idhoadon từ bảng hoadon với ngayluuhoadon tương ứng
            String getIdHoaDonQuery = "SELECT idhoadon FROM hoadon WHERE ngayluuhoadon = ?";
            PreparedStatement stmtGetIdHoaDon = conn.prepareStatement(getIdHoaDonQuery);
            stmtGetIdHoaDon.setString(1, ngayNhapHD);
            ResultSet rs = stmtGetIdHoaDon.executeQuery();
            System.out.println(ngayNhapHD);
            String idhoadon = null;
            if (rs.next()) {
                idhoadon = rs.getString("idhoadon");
                System.out.println(id );    
            }
            
            if (idhoadon != null){
            String chitiethoadon = "INSERT INTO chitiethoadon (idchitiethoadon, tensanpham,"
                    + "donvitinh, soluong, dongia, ghichu, hoadon_chitiethoadon)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmtChiTietHD = conn.prepareStatement(chitiethoadon);
            stmtChiTietHD.setString(1, id);
            stmtChiTietHD.setString(2, tensanpham);
            stmtChiTietHD.setString(3, donvitinh);
            stmtChiTietHD.setInt(4, soluong);
            stmtChiTietHD.setString(5, thanhtien);
            stmtChiTietHD.setString(6, ghichu);
            stmtChiTietHD.setString(7, idhoadon);
            int rowsInsert1 = stmtChiTietHD.executeUpdate();
            System.out.println(id);
            if (rowsInsert1 > 0) {
                conn.close();
                System.out.println("hi");
                return true;

            }
            }else {
                System.out.println("Không tìm thấy ngày trong báo cáo");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    // Phương thức để lấy ngày tháng năm hiện tại dưới dạng chuỗi
    public static String getCurrentDateAsString(String ngaynhaphd) {
        String dateString = ngaynhaphd;
        StringBuilder sb = new StringBuilder(dateString);

        // Duyệt qua từng ký tự trong StringBuilder và xóa bỏ ký tự '/'
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '-') {
                sb.deleteCharAt(i);
                i--; // Giảm chỉ số để duyệt lại vị trí hiện tại sau khi xóa
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    // Phương thức để tạo chuỗi chứa số ngẫu nhiên
    public static String generateRandomNumbers(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10)); // Số ngẫu nhiên từ 0 đến 9
        }
        return sb.toString();
    }

//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        String ngayNhapHD = "2024-07-10";
//        String tensanpham = "Coffee";
//        String donvitinh = "Cup";
//        String soluong = "2";
//        String dongia = "5.5";
//        String ghichu = "test";
//
//        try {
//            boolean result = ktLuuhoaDon(ngayNhapHD, tensanpham, donvitinh, soluong, dongia, ghichu);
//            if (result) {
//                System.out.println("Đã lưu hóa đơn thành công!");
//            } else {
//                System.out.println("Lưu hóa đơn thất bại!");
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
}

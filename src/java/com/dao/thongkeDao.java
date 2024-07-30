/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.model.hoadon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author duong
 */
public class thongkeDao {
    public List<hoadon> thongKeHD(String thangLuuHoaDon) throws ClassNotFoundException, SQLException {
        List<hoadon> danhsachHD = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/buicoffee",
                    "root", "1111"
            );
            String sql = "SELECT h.idhoadon, h.ngayluuhoadon, c.idchitiethoadon, c.tensanpham, " +
                     "c.soluong, c.donvitinh, c.dongia, c.ghichu " +
                     "FROM hoadon h " +
                     "JOIN chitiethoadon c ON h.idhoadon = c.hoadon_chitiethoadon " +
                     "WHERE MONTH(h.ngayluuhoadon) = ? " +
                     "AND YEAR(h.ngayluuhoadon) = '2024'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, thangLuuHoaDon); // Tháng

            // Thực thi truy vấn
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String ngayluuhoadon = rs.getString("ngayluuhoadon");
                String idchitiethoadon = rs.getString("idchitiethoadon");
                String tensanpham = rs.getString("tensanpham");
                int soluong = rs.getInt("soluong");
                String donvitinh = rs.getString("donvitinh");
                String dongia = rs.getString("dongia");
                String ghichu = rs.getString("ghichu");

                // Tạo đối tượng Hoadon và thêm vào danh sách
                hoadon hoadon = new hoadon(ngayluuhoadon, idchitiethoadon, tensanpham,
                                           donvitinh, soluong, dongia, ghichu);
                danhsachHD.add(hoadon);
            }
            
            // Đóng kết nối và tài nguyên
            rs.close();
            stmt.close();
            conn.close();
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return danhsachHD;
    }

    
}



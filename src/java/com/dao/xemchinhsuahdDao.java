/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import com.model.hoadon;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duong
 */
public class xemchinhsuahdDao {
    public List<hoadon> layDanhSachSanPham(String ngayLuuhoadon) throws ClassNotFoundException, SQLException {
        List<hoadon> danhSachSanPham = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/buicoffee",
                    "root", "1111"
            );
            String sql = "SELECT chitiethoadon.idchitiethoadon,"
                    + "chitiethoadon.donvitinh, chitiethoadon.ghichu,"
                    + " chitiethoadon.tensanpham, chitiethoadon.soluong, chitiethoadon.dongia " +
                         "FROM hoadon " +
                         "JOIN chitiethoadon ON hoadon.idhoadon = chitiethoadon.hoadon_chitiethoadon " +
                         "WHERE hoadon.ngayluuhoadon = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ngayLuuhoadon);

            // Thực thi câu truy vấn
            ResultSet rs = pstmt.executeQuery();

            // Duyệt qua kết quả và thêm vào danh sách sản phẩm
            while (rs.next()) {
                String idchitiethoadon = rs.getString("idchitiethoadon");
                String tenSanPham = rs.getString("tensanpham");
                String donViTinh = rs.getString("donvitinh");
                int soLuong = rs.getInt("soluong");
                String donGia = rs.getString("dongia");
                String ghiChu = rs.getString("ghichu");

                hoadon chiTiet = new hoadon(idchitiethoadon, tenSanPham, donViTinh, soLuong, donGia, ghiChu);
                danhSachSanPham.add(chiTiet);
            }

        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return danhSachSanPham;
    }
}

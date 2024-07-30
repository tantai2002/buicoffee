/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;

/**
 *
 * @author duong
 */
public class taobaocaohdDao {

    public static boolean ktTaoBaoCaoHD(String startDate, String endDate)
            throws ClassNotFoundException, SQLException, ParseException {
        String startcd = chuyenDoiNgayThang(startDate);
        String endcd = chuyenDoiNgayThang(endDate);
        String start = startcd;
        String end = endcd;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/buicoffee",
                    "root", "1111"
            );

            String kiemTraEndDate = "SELECT COUNT(*) AS count FROM hoadon WHERE ngayluuhoadon = ?";
            PreparedStatement stmtKiemTra = conn.prepareStatement(kiemTraEndDate);
            stmtKiemTra.setString(1, end);
            ResultSet rs = stmtKiemTra.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    // Nếu end đã tồn tại trong cơ sở dữ liệu, trả về false
                    return false;
                }
            }

            String taoBaoCaoHD = "INSERT INTO hoadon (idhoadon, ngayluuhoadon)\n"
                    + "SELECT CONCAT(DATE_FORMAT(ngayluuhoadon, '%Y%m%d'), LPAD(FLOOR(RAND() * 10), 1, '0')) AS idhoadon,\n"
                    + "       ngayluuhoadon\n"
                    + "FROM\n"
                    + "    (SELECT DATE_ADD(?, INTERVAL (t4.i * 1000 + t3.i * 100 + t2.i * 10 + t1.i) DAY) AS ngayluuhoadon\n"
                    + "     FROM\n"
                    + "         (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION \n"
                    + "          SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) AS t1,\n"
                    + "         (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION \n"
                    + "          SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) AS t2,\n"
                    + "         (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION \n"
                    + "          SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) AS t3,\n"
                    + "         (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION \n"
                    + "          SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) AS t4\n"
                    + "     WHERE DATE_ADD(?, INTERVAL (t4.i * 1000 + t3.i * 100 + t2.i * 10 + t1.i) DAY) <= ?) AS dates;";
            PreparedStatement stmtBC = conn.prepareStatement(taoBaoCaoHD);

//            stmtBC.executeUpdate();
            stmtBC.setString(1, start);
            stmtBC.setString(2, start);
            stmtBC.setString(3, end);

            int rowsInsert = stmtBC.executeUpdate();
            if (rowsInsert > 0) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String chuyenDoiNgayThang(String ngayNhapHD) throws ParseException {
        // Định dạng cho chuỗi ngày tháng đầu vào (1/7/2024)
        DateFormat sdfInput = new SimpleDateFormat("d/M/yyyy");

        // Định dạng cho chuỗi ngày tháng đầu ra (2024/7/1)
        DateFormat sdfOutput = new SimpleDateFormat("yyyy/M/d");

        // Chuyển đổi từ chuỗi sang Date
        Date date = sdfInput.parse(ngayNhapHD);

        // Chuyển đổi từ Date sang chuỗi theo định dạng mới
        String ngayDaChinhSua = sdfOutput.format(date);

        return ngayDaChinhSua;
    }

//    public static void main(String[] args) throws ParseException {
//        String startDate = chuyenDoiNgayThang("2024/7/1"); // Ngày bắt đầu (format YYYY/MM/DD)
//        String endDate = chuyenDoiNgayThang("2024/7/31");   // Ngày kết thúc (format YYYY/MM/DD)
//
//        try {
//            boolean result = ktTaoBaoCaoHD(startDate, endDate);
//            if (result) {
//                System.out.println("Tạo báo cáo hóa đơn thành công.");
//            } else {
//                System.out.println("Không thành công khi tạo báo cáo hóa đơn.");
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
}

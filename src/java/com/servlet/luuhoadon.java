/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlet;

import com.dao.luuhoadonDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.sql.SQLException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.model.hoadon;
import java.text.DecimalFormat;
import java.util.List;

//import java.ObjectMapper;
/**
 *
 * @author duong
 */
@WebServlet(name = "luuhoadon", urlPatterns = {"/luuhoadon"})
public class luuhoadon extends HttpServlet {

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
////        hoadon hd = new hoadon();
//
//        // Đọc dữ liệu JSON từ request body
//        BufferedReader reader = request.getReader();
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            sb.append(line);
//        }
//        String jsonData = sb.toString();
//        System.out.println(jsonData);
//        Gson gson = new Gson();
//        Type ofjectType = new TypeToken<hoadon>(){}.getType();
//        hoadon hdOfject = gson.fromJson(jsonData, ofjectType);
//        
//        String ngayNhapHD = hdOfject.getNgayNhapHD();
//        String tensanpham = hdOfject.getTenSanPham();
//        String donvitinh = hdOfject.getDonViTinh();
//        String soluong = String.valueOf(hdOfject.getSoLuong());
//        String thanhtien = String.valueOf(hdOfject.getThanhTien());
//        String ghichu = hdOfject.getGhiChu();
//        
//        System.out.println(soluong);
//        
//
////        // Gọi phương thức ktLuuhoaDon để lưu hóa đơn vào CSDL
//        try {
//            if (ngayNhapHD != null && tensanpham != null) {
//                boolean result = luuhoadonDao.ktLuuhoaDon(ngayNhapHD, tensanpham, donvitinh, soluong, thanhtien, ghichu);
//                if (result) {
//                    response.getWriter().write("Lưu hóa đơn thành công!");
//                } else {
//                    response.getWriter().write("Lưu hóa đơn thất bại!");
//                }
//            } else {
//                response.getWriter().write("Dữ liệu không hợp lệ!"); // Xử lý khi dữ liệu không hợp lệ
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            response.getWriter().write("Lỗi khi lưu hóa đơn: " + e.getMessage());
//        }
//    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Đọc dữ liệu JSON từ request body
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonData = sb.toString();
        System.out.println("Received JSON data: " + jsonData);

        // Chuyển đổi JSON thành đối tượng hoadon và danh sách chitiethoadon
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);

        // Đối tượng hoadon
        String ngayHoaDon = jsonObject.get("ngayHoaDon").getAsString();
        // Các chi tiết hóa đơn
        JsonArray productsArray = jsonObject.getAsJsonArray("products");
        System.out.println(productsArray);
        Type productListType = new TypeToken<List<hoadon>>(){}.getType();
        List<hoadon> productList = gson.fromJson(productsArray, productListType);
        String tenSanPham = null;
        int soLuong = 0;
        String donViTinh = null;
        String thanhTien = null;
        String ghiChu = null;
        
//         Lặp qua từng sản phẩm và lấy thông tin
        for (hoadon product : productList) {
            tenSanPham = product.getTenSanPham();
            soLuong = product.getSoLuong();
            donViTinh = product.getDonViTinh();
            thanhTien = product.getThanhTien();
            ghiChu = product.getGhiChu();
//            
//             In thông tin của sản phẩm
            System.out.println("Ngày: " + ngayHoaDon);
            System.out.println("Tên sản phẩm: " + tenSanPham);
            System.out.println("Số lượng: " + soLuong);
            System.out.println("Đơn vị tính: " + donViTinh);
            System.out.println("Thành tiền: " + thanhTien);
            System.out.println("Ghi chú: " + ghiChu);
            System.out.println();


            try {
                if (ngayHoaDon != null && tenSanPham != null) {
                    boolean result = luuhoadonDao.ktLuuhoaDon(ngayHoaDon, tenSanPham, donViTinh, soLuong, thanhTien, ghiChu);
                    if (result) {
                        response.getWriter().write("Lưu hóa đơn thành công!");
                    } else {
                        response.getWriter().write("Lưu hóa đơn thất bại!");
                    }
                } else {
                    response.getWriter().write("Dữ liệu không hợp lệ!"); // Xử lý khi dữ liệu không hợp lệ
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                response.getWriter().write("Lỗi khi lưu hóa đơn: " + e.getMessage());
            }
            
        }

//    // Lưu hóa đơn chính vào CSDL
//    try {
//        boolean result = luuhoadonDao.ktLuuhoaDon(ngayNhapHD, tensanpham, donvitinh, soluong, thanhtien, ghichu);
//        if (!result) {
//            response.getWriter().write("Lỗi khi lưu hóa đơn chính");
//            return;
//        }
//    } catch (ClassNotFoundException | SQLException e) {
//        e.printStackTrace();
//        response.getWriter().write("Lỗi khi lưu hóa đơn chính: " + e.getMessage());
//        return;
//    }
//
//    // Lưu từng chi tiết hóa đơn vào CSDL
//    for (JsonElement element : productsArray) {
//        JsonObject productObject = element.getAsJsonObject();
//        String tenSanPham = productObject.get("tenSanPham").getAsString();
//        String soLuong = productObject.get("soLuong").getAsString();
//        String donViTinh = productObject.get("donViTinh").getAsString();
//        String thanhTien = productObject.get("thanhTien").getAsString();
//        String ghiChu = productObject.get("ghiChu").getAsString();
//
//        try {
//            boolean detailResult = luuhoadonDao.ktLuuChiTietHoaDon(tenSanPham, donViTinh, soLuong, thanhTien, ghiChu);
//            if (!detailResult) {
//                response.getWriter().write("Lỗi khi lưu chi tiết hóa đơn cho sản phẩm: " + tenSanPham);
//                return;
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            response.getWriter().write("Lỗi khi lưu chi tiết hóa đơn cho sản phẩm " + tenSanPham + ": " + e.getMessage());
//            return;
//        }
//    }
//
//    // Phản hồi thành công nếu không có lỗi
//    response.getWriter().write("Lưu hóa đơn thành công!");
    }

}

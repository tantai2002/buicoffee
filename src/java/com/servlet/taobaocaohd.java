/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.model.ngaynhapbaocao;
import java.io.BufferedReader;
import java.lang.reflect.Type;
import com.dao.taobaocaohdDao;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duong
 */
@WebServlet(name = "taobaocaohd", urlPatterns = {"/taobaocaohd"})
public class taobaocaohd extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        ngaynhapbaocao ngaynhap = new ngaynhapbaocao();

        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonData = sb.toString();
        System.out.println(jsonData);
        Gson gson = new Gson();
        Type ofjectType = new TypeToken<ngaynhapbaocao>() {
        }.getType();
        ngaynhapbaocao ngaybcOfject = gson.fromJson(jsonData, ofjectType);
        String start = ngaybcOfject.getStartDate();
        String end = ngaybcOfject.getEndDate();
        String message;
        try {
            if (start != null && end != null) {
                boolean result = taobaocaohdDao.ktTaoBaoCaoHD(start, end);
                JsonObject jsonResponse = new JsonObject();
                jsonResponse.addProperty("success", result);
                if (result) {
                    message = "Tạo báo cáo tháng thành công!";
                } else {
                    message = "Tạo báo cáo tháng thất bại!";
                }
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(new Gson().toJson(jsonResponse));
                out.flush();
            } else {
                response.getWriter().write("Dữ liệu không hợp lệ!"); // Xử lý khi dữ liệu không hợp lệ
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Lỗi khi lưu hóa đơn: " + e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(taobaocaohd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.dao.ktdangnhap;

/**
 *
 * @author duong
 */
@WebServlet(name = "dangnhap", urlPatterns = {"/dangnhap"})
public class dangnhap extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ktdangnhap kt = new ktdangnhap();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean isValid = kt.authLogin(username, password);
            if (isValid) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("trangchu.jsp");
            } else {
                System.out.println("fail");
                response.sendRedirect("login.jsp?error=true");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ktdangnhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

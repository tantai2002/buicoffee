/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author duong
 */
public class ktdangnhap {

    public boolean authLogin(String username, String password) throws
            ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/buicoffee",
                    "root", "1111"
                    
            );
            System.out.println(username);
            String query = "SELECT * FROM nguoidung WHERE tendangnhap = ?"
                    + " AND matkhau = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, hashPassword(password));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                conn.close();
                return true;
            } else {
                conn.close();
                return false;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ktdangnhap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ktdangnhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes()); 
            byte[] byteData = md.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : byteData) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            System.out.println(hexString.toString());
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ktdangnhap kt = new ktdangnhap();
        String testUsername = "thungan";
        String testPassword = "123";

        if (!kt.authLogin(testUsername, testPassword)) {
              System.out.println("Thông tin đăng nhập không hợp lệ!");
        } else {
            System.out.println("Đăng nhập thành công!");
        }
    }
}

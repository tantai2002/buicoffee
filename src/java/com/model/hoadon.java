/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author duong
 */
public class hoadon {
    private String ngayHoaDon;
    private String idchitiethoadon;
    private String tenSanPham;
    private String donViTinh;
    private int soLuong;
    private String thanhTien;
    private String ghiChu;

    public hoadon(String ngayHoaDon, String idchitiethoadon, String tenSanPham, String donViTinh, int soLuong, String thanhTien, String ghiChu) {
        this.ngayHoaDon = ngayHoaDon;
        this.idchitiethoadon = idchitiethoadon;
        this.tenSanPham = tenSanPham;
        this.donViTinh = donViTinh;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.ghiChu = ghiChu;
    }
    
    

    public hoadon(String idchitiethoadon,String tenSanPham, String donViTinh, int soLuong, String thanhTien, String ghiChu) {
        this.idchitiethoadon = idchitiethoadon;
        this.tenSanPham = tenSanPham;
        this.donViTinh = donViTinh;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.ghiChu = ghiChu;
    }
    
    

    // Các phương thức getter và setter
    public String getNgayNhapHD() {
        return ngayHoaDon;
    }

    public void setNgayNhapHD(String ngayHoaDon) {
        this.ngayHoaDon = ngayHoaDon;
    }
    
    public String getIdchitiethoadon() {
        return idchitiethoadon;
    }

    public void setIdchitiethoadon(String idchitiethoadon) {
        this.idchitiethoadon = idchitiethoadon;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
//    @Override
//    public String toString() {
//        return "Hoadon idchitiethoadon=" + idchitiethoadon + ", tensanpham=" + tenSanPham +
//               ", soluong=" + soLuong + ", donvitinh=" + donViTinh + ", dongia=" + thanhTien +
//               ", ghichu=" + ghiChu + "]";
//    }
}

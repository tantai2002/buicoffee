/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;


/**
 *
 * @author duong
 */
public class ngaynhapbaocao {
    String startDate;
    String endDate;
    
    public ngaynhapbaocao(){
    }

    public ngaynhapbaocao(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public String getEndDate() {
        return endDate;
    }

    public void getEndDate(String endDate) {
        this.endDate = endDate;
    }
}

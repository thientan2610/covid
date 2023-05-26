/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thientan.model;
import java.sql.*;
import java.util.List;
/**
 *
 * @author Admin
 */
public class Receipt {

    public Receipt() {
    }

    public Receipt(String id, String userID, Date orderDate, int totalAmount, Boolean status, List<ReceiptDetail> packList) {
        this.id = id;
        this.userID = userID;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.packList = packList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<ReceiptDetail> getPackList() {
        return packList;
    }

    public void setPackList(List<ReceiptDetail> packList) {
        this.packList = packList;
    }
    
    private String id;
    private String userID;
    private Date orderDate;
    private int totalAmount;
    private int remainAmount;

    public int getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(int remainAmount) {
        this.remainAmount = remainAmount;
    }
    private Boolean status;
    private List<ReceiptDetail> packList;
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thientan.model;

/**
 *
 * @author Admin
 */
public class ReceiptDetail {

    public ReceiptDetail() {
    }

    public ReceiptDetail(Pack pack, int quantity) {
        this.pack = pack;
        this.quantity = quantity;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    private Pack pack;
    private int quantity;
}

package com.thientan.model;

public class PackDetail {
    private String packId;
    private Product product;
    private int quantity;


    public PackDetail() {
    }

    public PackDetail(String packId, Product product, int quantity) {
        this.packId = packId;
        this.product = product;
        this.quantity = quantity;
    }


    public String getPackId() {
        return packId;
    }

    public void setPackId(String packId) {
        this.packId = packId;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" +
            " product='" + getProduct() + "'" +
            ", quantity='" + getQuantity() + "'" +
            "}";
    }

    public void getPackId(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

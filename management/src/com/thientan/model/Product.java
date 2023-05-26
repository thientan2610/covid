package com.thientan.model;

public class Product {
    private String id;
    private String name;
    private int price;
    private String unit;
    private String image;


    public Product() {
    }

    public Product(String id, String name, int price, String unit, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.image = image;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", price='" + getPrice() + "'" +
            ", unit='" + getUnit() + "'" +
            ", image='" + getImage() + "'" +
            "}";
    }
    
}

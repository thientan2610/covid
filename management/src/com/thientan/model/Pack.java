package com.thientan.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Pack {
    private String id;
    private String name;
    private int price;
    private int limitQuantity;
    private Date dateExp;
    private List<PackDetail> packList;

    public Pack(){
        packList = new ArrayList<PackDetail>();
    }

    public Pack(String id, String name, int price, int limitQuantity, Date dateExp, List<PackDetail> proList) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.limitQuantity = limitQuantity;
        this.dateExp = dateExp;
        this.packList = proList;
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

    public int getLimitQuantity() {
        return this.limitQuantity;
    }

    public void setLimitQuantity(int limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    public Date getDateExp() {
        return this.dateExp;
    }

    public void setDateExp(Date dateExp) {
        this.dateExp = dateExp;
    }

    public List<PackDetail> getProList() {
        return this.packList;
    }

    public void setPackList(List<PackDetail> proList) {
        this.packList = proList;
    }
    public void addPackage(PackDetail product){
        packList.add(product);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", price='" + getPrice() + "'" +
            ", limitQuantity='" + getLimitQuantity() + "'" +
            ", dateExp='" + getDateExp() + "'" +
            ", proList='" + getProList() + "'" +
            "}";
    }
}

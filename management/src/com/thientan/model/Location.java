package com.thientan.model;

public class Location {

    private String id;
    private String address;
    private String ward;
    private String district;
    private String city;

    public Location() {
    }

    public Location(String id, String address, String ward, String district, String city) {
        this.id = id;
        this.address = address;
        this.ward = ward;
        this.district = district;
        this.city = city;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWard() {
        return this.ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return (this.address + ", " + this.ward + ", " + this.district + ", " + this.city);
    }
}

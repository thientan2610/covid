package com.thientan.model;

public class TreatmentLocation {

    private String id;
    private String name;
    private Location address;
    private int capacity;
    private int occupancy;

    public TreatmentLocation() {
    }

    public TreatmentLocation(String id, String name, Location address, int capacity, int occupancy) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.occupancy = occupancy;
    }

    public TreatmentLocation(String id, String name, int occupancy, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.occupancy = occupancy;    }

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

    public Location getAddress() {
        return this.address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getOccupancy() {
        return this.occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public Boolean isFull() {
        if (occupancy >= capacity) {
            return true;
        }
        return false;
    }

    public void updateOccupancy(int num, Boolean isAdd) {
        if (isAdd) {
            occupancy += num;
        } else {
            occupancy -= num;
        }
    }

    @Override
    public String toString() {
        return "TreatmentLocation{" + "id=" + id + ", name=" + name + ", address=" + address + ", capacity=" + capacity + ", occupancy=" + occupancy + '}';
    }

   

}

package com.thientan.model;

import java.time.YearMonth;
import java.util.List;

public class User {
    private String id;
    private String name;
    private String noID;
    private int birthYear;
    private Location address;
    private TreatmentLocation trmtLoca;
    private int status;
    private int debit;
    private List<User> relatedList;
    private List<TreatmentRecord> trmtRec;
    public User(){
        birthYear=YearMonth.now().getYear();
        status=-1;
        debit=0;
    };

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

    public String getNoID() {
        return this.noID;
    }

    public void setNoID(String noID) {
        this.noID = noID;
    }

    public int getBirthYear() {
        return this.birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public Location getAddress() {
        return this.address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public TreatmentLocation getTrmtLoca() {
        return this.trmtLoca;
    }

    public void setTrmtLoca(TreatmentLocation trmtLoca) {
        this.trmtLoca = trmtLoca;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDebit() {
        return this.debit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public List<User> getRelatedList() {
        return this.relatedList;
    }

    public void setRelatedList(List<User> relatedList) {
        this.relatedList = relatedList;
    }

    public List<TreatmentRecord> getTrmtRec() {
        return this.trmtRec;
    }

    public void setTrmtRec(List<TreatmentRecord> trmtRec) {
        this.trmtRec = trmtRec;
    }
   
    public void updateDebit(int deb, Boolean isAdd){
        if(isAdd)
            debit += deb;
        else debit -= deb; 
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", noID='" + getNoID() + "'" +
            ", birthYear='" + getBirthYear() + "'" +
            ", address='" + getAddress() + "'" +
            ", trmtLoca='" + getTrmtLoca() + "'" +
            ", status='" + getStatus() + "'" +
            ", debit='" + getDebit() + "'" +
            ", relatedList='" + getRelatedList() + "'" +
            ", trmtRec='" + getTrmtRec() + "'" +
            "}";
    }
}

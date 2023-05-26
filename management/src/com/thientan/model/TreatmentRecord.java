package com.thientan.model;

import java.sql.Timestamp;

public class TreatmentRecord {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private TreatmentLocation trmtLoca;
    private int status;
    private Timestamp recTimestamp;

    public TreatmentRecord() {
    }

    public TreatmentRecord(TreatmentLocation trmtLoca, int status, Timestamp recTimestamp) {
        this.trmtLoca = trmtLoca;
        this.status = status;
        this.recTimestamp = recTimestamp;
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

    public Timestamp getRecTimestamp() {
        return this.recTimestamp;
    }

    public void setRecTimestamp(Timestamp recTimestamp) {
        this.recTimestamp = recTimestamp;
    }

    @Override
    public String toString() {
        return 
            "- " + getRecTimestamp() + ": " +
            "TreatmentLocation: " + getTrmtLoca() +
            ", Status: F" + getStatus();
    }

}

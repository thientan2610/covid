/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Timestamp;
/**
 *
 * @author Admin
 */
public class Record {
    private String recID;
    private String accID;
    private int money;
    private Timestamp recTimestamp;

    public Record() {
    }
    
    public Record(String accID, int money) {
        this.accID = accID;
        this.money = money;
    }

    public Record(String recID, String accID, int money, Timestamp recTimestamp) {
        this.recID = recID;
        this.accID = accID;
        this.money = money;
        this.recTimestamp = recTimestamp;
    }

    public String getRecID() {
        return recID;
    }

    public void setRecID(String recID) {
        this.recID = recID;
    }

    public String getAccID() {
        return accID;
    }

    public void setAccID(String accID) {
        this.accID = accID;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Timestamp getRecTimestamp() {
        return recTimestamp;
    }

    public void setRecTimestamp(Timestamp recTimestamp) {
        this.recTimestamp = recTimestamp;
    }

    @Override
    public String toString() {
        return "Record{" + "recID=" + recID + ", accID=" + accID + ", money=" + money + ", recTimestamp=" + recTimestamp + '}';
    }
    
}

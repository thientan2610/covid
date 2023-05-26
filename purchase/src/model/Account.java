/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

public class Account implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private int money;

    public Account() {
    }

    public Account(String id) {
        this.id = id;
    }
    
    public Account(String id, int money) {
        this.id = id;
        this.money = money;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "{" + "id='" + getId() + "'" + ", money='" + getMoney() + "'" +"}";
    }

}

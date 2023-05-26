/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import sql.JDBCConnection;

/**
 *
 * @author Admin
 */
public class AccountService {
    public static int getBalanceByID(String id){
        int res = -1;
        Connection Conn = new JDBCConnection().getConnection();
        PreparedStatement  ps = null;
        String sql = "SELECT AccBalance FROM ACCOUNT WHERE AccID = ?";
        try {
            ps = Conn.prepareStatement(sql);
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                res = rs.getInt("AccBalance");
        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    public static Account setAccount(String str){
        String[] arr = str.trim().split("@",2);
        Account acc = null;
        if(arr[0].length() != 6)
            return acc;
        if(arr[1].isEmpty())
            acc = new Account(arr[0]);
        else
            acc = new Account(arr[0],Integer.parseInt(arr[1]));
        return acc;        
    }
    public static Boolean insAccount(Account acc){
        Connection Conn = new JDBCConnection().getConnection();
        PreparedStatement  ps = null;
        String sql = "insert into ACCOUNT(AccID) values(?) ";
        try {
            ps = Conn.prepareStatement(sql);
            ps.setString(1,acc.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

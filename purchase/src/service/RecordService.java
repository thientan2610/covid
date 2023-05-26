/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import sql.JDBCConnection;
import model.Record;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
/**
 *
 * @author Admin
 */
public class RecordService {
    public static List<Record> getAll(){
        List<Record> res = new ArrayList<Record>();
        Connection Conn = new JDBCConnection().getConnection();
        PreparedStatement ps = null;
        String sql = "SELECT * FROM PAYMENT_REC";
        try {
            ps = Conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Record rec = new Record(rs.getString("RecID"),rs.getString("AccID"),rs.getInt("AmountOfMoney"), rs.getTimestamp("RecTimestamp"));
                res.add(rec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecordService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    public static Boolean insRecord(Record rec){
        Connection Conn = new JDBCConnection().getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO PAYMENT_REC (AccID, AmountofMoney) VALUES(?,?)";
        try {
            ps = Conn.prepareStatement(sql);
            ps.setString(1,rec.getAccID());
            ps.setInt(2, rec.getMoney());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RecordService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static Record Acc2Rec(Account acc){
        Record rec = new Record(acc.getId(),acc.getMoney());
        return rec;
    }
}

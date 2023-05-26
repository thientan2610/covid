package com.thientan.service;

import com.thientan.model.Location;
import com.thientan.model.TreatmentLocation;
import com.thientan.model.TreatmentRecord;
import com.thientan.sql.connectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TreatmentRecordService {
    
    public static List<TreatmentRecord> getAll(String id) {
        List<TreatmentRecord> res = new ArrayList<TreatmentRecord>();
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;
        
        try {
            connection = cn.getConnection();
            String sql = "SELECT * FROM TreatmentRecord where userid = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TreatmentRecord rec = new TreatmentRecord();
                rec.setStatus(rs.getInt("UserStatus"));
                rec.setRecTimestamp(rs.getTimestamp("RecordTimestamp"));
                TreatmentLocation location = new TreatmentLocation();
                location = TreatmentLocationService.getByID(rs.getString("TrmtLocaID"));
                rec.setTrmtLoca(location);
                res.add(rec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return res;
    }
    
    public static int countTreatmentRecord() {
        int res = 0;
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;
        
        try {
            connection = cn.getConnection();
            String sql = "SELECT COUNT(*) FROM TREATMENTRECORD";
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                res = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return res;
    }
}

package com.thientan.service;

import com.thientan.model.Location;
import com.thientan.model.TreatmentLocation;
import com.thientan.sql.connectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TreatmentLocationService {

    public static TreatmentLocation getByID(String id) {
        TreatmentLocation res = null;
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = cn.getConnection();
            String query = "SELECT * FROM TREATMENTLOCATION WHERE TrmtLocaID = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Location location = null;
                res
                        = new TreatmentLocation(
                                rs.getString("TrmtLocaID"),
                                rs.getString("TrmtLocaName"),
                                location,
                                rs.getInt("Capacity"),
                                rs.getInt("Occupancy")
                        );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
    public static TreatmentLocation getByName(String name) {
        TreatmentLocation res = null;
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = cn.getConnection();
            String query = "SELECT * FROM TREATMENTLOCATION WHERE TrmtLocaName = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Location location = null;
                res
                        = new TreatmentLocation(
                                rs.getString("TrmtLocaID"),
                                rs.getString("TrmtLocaName"),
                                location,
                                rs.getInt("Capacity"),
                                rs.getInt("Occupancy")
                        );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static List<TreatmentLocation> getAll() {
        List<TreatmentLocation> res = new ArrayList<TreatmentLocation>();
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = cn.getConnection();
            String sql = "SELECT * FROM TREATMENTLOCATION";
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TreatmentLocation rec = new TreatmentLocation();
//                Location location = LocationService.getByID(rs.getString("AddressID"));
                rec.setId(rs.getString("TrmtLocaID"));
                rec.setName(rs.getString("TrmtLocaName"));
//                rec.setAddress(location);
                rec.setCapacity(rs.getInt("Capacity"));
                rec.setOccupancy(rs.getInt("Occupancy"));
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

    public static Boolean addOne(TreatmentLocation ele) {
        connectDB cn = new connectDB();
        Connection connection = null;
        connection = cn.getConnection();
        PreparedStatement ps = null;
        
        try {
            String query = "insert into TREATMENTLOCATION values(?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, ele.getId());
            ps.setString(2, ele.getName());
//            ps.setString(3, ele.getAddress().getId());
            ps.setInt(3, ele.getCapacity());
            ps.setInt(4, ele.getOccupancy());
            
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean updOne(TreatmentLocation ele) {
        connectDB cn = new connectDB();
        Connection connection = null;
        connection = cn.getConnection();
        PreparedStatement ps = null;
        try {
            String query
                    = "update TREATMENTLOCATION set TrmtLocaName = ?, Capacity = ?, Occupancy = ? where TrmtLocaID = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, ele.getName());
//            ps.setString(2, ele.getAddress().getId());
            ps.setInt(2, ele.getCapacity());
            ps.setInt(3, ele.getOccupancy());
            ps.setString(4, ele.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean delOne(String id) {
       connectDB cn = new connectDB();
        Connection connection = null;
        connection = cn.getConnection();
        PreparedStatement ps = null;
        String query = "delete from TREATMENTLOCATION where TrmtLocaID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
}

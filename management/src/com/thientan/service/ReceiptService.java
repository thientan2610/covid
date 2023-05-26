/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thientan.service;

import com.thientan.model.Pack;
import com.thientan.model.PackDetail;
import com.thientan.model.Product;
import com.thientan.model.Receipt;
import com.thientan.model.ReceiptDetail;
import com.thientan.sql.connectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ReceiptService {

    public static int getTotalByID(String id) {
        int total = 0;
        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        PreparedStatement ps = null;
        String query = "EXEC usp_Total ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("tongtien");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReceiptService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public static int countRecepit() {
        int res = 0;
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = cn.getConnection();
            String sql = "SELECT COUNT(*) FROM RECEIPT";
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

    public static boolean addReceipt(Receipt receipt) {
        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        PreparedStatement ps = null;

        try {
            String query = "insert into RECEIPT values(?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, receipt.getId());
            ps.setString(2, receipt.getUserID());
            ps.setDate(3, receipt.getOrderDate());
            ps.setBoolean(4, receipt.getStatus());
            ps.setInt(5, receipt.getTotalAmount());

            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean updateReceipt(Receipt receipt) {
        connectDB cn = new connectDB();
        Connection connection = null;
        connection = cn.getConnection();
        PreparedStatement ps = null;
        try {
            String query
                    = "update RECEIPT set ReceiptStatus = ?, RemainAmount =? where ReceiptID = ?";
            ps = connection.prepareStatement(query);
            
            ps.setBoolean(1, receipt.getStatus());
            ps.setInt(2, receipt.getRemainAmount());
            ps.setString(3, receipt.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int countPayment() {
        int res = 0;
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = cn.getConnection();
            String sql = "SELECT COUNT(*) FROM PAYMENTRECORD";
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

    public static int countTotalPayment() {
        int res = 0;
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = cn.getConnection();
            String sql = "SELECT * FROM PAYMENTRECORD";
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                res += rs.getInt("AmountOfMoney");
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
    
     public static int countTotalDebit() {
        int res = 0;
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = cn.getConnection();
            String sql = "SELECT * FROM USERS";
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                res += rs.getInt("DebitBalance");
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
    
    public static boolean addPayment(String id, Receipt receipt) {
        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        PreparedStatement ps = null;

        try {
            String query = "insert into PAYMENTRECORD(UserID, ReceiptID, AmountOfMoney) values(?, ?, ?)";
            ps = connection.prepareStatement(query);
            
            ps.setString(1, receipt.getUserID());
            ps.setString(2, receipt.getId());
            ps.setInt(3, receipt.getTotalAmount());

            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addReceiptDetail(String id, ReceiptDetail rcDetail) {
        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        PreparedStatement ps = null;

        try {
            String query = "insert into RECEIPT_DETAIL values(?, ?, ?)";
            ps = connection.prepareStatement(query);

            ps.setString(1, id);
            ps.setString(2, rcDetail.getPack().getId());
            ps.setInt(3, rcDetail.getQuantity());

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Receipt> getAllReceipt(String userID) {
        List<Receipt> res = new ArrayList<Receipt>();
        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        PreparedStatement ps = null;
        String sql = "SELECT * FROM Receipt where userid = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Receipt rec = new Receipt();

                rec.setId(rs.getString("ReceiptID"));
                rec.setOrderDate(rs.getDate("OrderDate"));
                rec.setStatus(rs.getBoolean("ReceiptStatus"));
                rec.setRemainAmount(rs.getInt("RemainAmount"));

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
    
        public static List<Receipt> getAll() {
        List<Receipt> res = new ArrayList<Receipt>();
        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        PreparedStatement ps = null;
        String sql = "SELECT * FROM RECE";

        try {
            ps = connection.prepareStatement(sql);
          
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Receipt rec = new Receipt();

                rec.setId(rs.getString("ReceiptID"));
                rec.setOrderDate(rs.getDate("OrderDate"));
                rec.setStatus(rs.getBoolean("ReceiptStatus"));
                rec.setRemainAmount(rs.getInt("RemainAmount"));

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


    public static List<Pack> getAllPack(String id) {
        List<Pack> res = new ArrayList<Pack>();
        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        PreparedStatement ps = null;
        String sql = "SELECT * FROM Receipt_detail where receiptID = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pack rec = new Pack();
                rec = PackService.getPackById(rs.getString("PackID"));
                rec.setLimitQuantity(rs.getInt("Quantity"));
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
    
    public static int getAmount(String id) {
        int res = 0;
        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        PreparedStatement ps = null;

        try {
            String query = "SELECT * FROM RECEIPT WHERE ReceiptID = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                res = rs.getInt("RemainAmount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
        public static List<Receipt> getAllPaymentRecord(String id) {
        List<Receipt> res = new ArrayList<Receipt>();
        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        PreparedStatement ps = null;
        String sql = "SELECT * FROM PAYMENTRECORD where ReceiptID = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Receipt rec = new Receipt();

                rec.setId(rs.getString("ReceiptID"));
                rec.setOrderDate(rs.getDate("PayTimestamp"));
                rec.setRemainAmount(rs.getInt("AmountOfMoney"));

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


}

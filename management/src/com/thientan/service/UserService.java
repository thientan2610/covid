package com.thientan.service;

import com.thientan.model.Account;
import com.thientan.model.Location;
import com.thientan.model.TreatmentRecord;
import com.thientan.model.User;
import com.thientan.sql.connectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {

    public static Boolean UpdStatus(User user) {
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = cn.getConnection();
            String query = "update USERS set UserStatus = ? where UserID = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, user.getStatus());
            ps.setString(2, user.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
     public static Boolean UpdDebit(String id, int deb, Boolean paid){
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;
        connection = cn.getConnection();
        String pay = "UPDATE USERS SET DebitBalance = DebitBalance - ? WHERE UserId = ? ";
        String buy = "UPDATE USERS SET DebitBalance = DebitBalance + ? WHERE UserId = ?";
        try {
            if(paid){
                ps = connection.prepareStatement(pay);
            } else ps = connection.prepareStatement(buy);
            ps.setInt(1, deb);
            ps.setString(2, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return false;
    }
    public static Boolean UpdTreatmentLocation(User user) {
        connectDB cn = new connectDB();
        Connection connection = null;
        connection = cn.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps0 = null;
        PreparedStatement ps1 = null;
        User preUser = getUserById(user.getId());
        String query = "update USERS set TrmtLocaID = ?, UserStatus = ? where UserID = ?";
        String sql0 = "update TREATMENTLOCATION set Occupancy = Occupancy - 1 where TrmtLocaID = ? ";
        String sql1 = "update TREATMENTLOCATION set Occupancy = Occupancy + 1 where TrmtLocaID = ? ";
        try {
            connection.setAutoCommit(false);
            ps0 = connection.prepareStatement(sql0);
            ps0.setString(1, user.getTrmtLoca().getId());
            ps0.execute();
            System.out.println("hi");

            if (preUser.getTrmtLoca() != null) {
                ps1 = connection.prepareStatement(sql1);
                ps1.setString(1, preUser.getTrmtLoca().getId());
                ps1.execute();
                System.out.println("ha");
            }

            ps = connection.prepareStatement(query);
            ps.setString(1, user.getTrmtLoca().getId());
            ps.setInt(2, user.getStatus());
            ps.setString(3, user.getId());
            ps.execute();
            System.out.println("hu");
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                e.printStackTrace();
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public static Boolean UpRelated(String user1, String user2) {
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;
        String query = "insert into RELATED values(?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user1);
            ps.setString(2, user2);

            ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<TreatmentRecord> getTreatmentRecord(String id) {
        List<TreatmentRecord> res = new ArrayList<TreatmentRecord>();
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = cn.getConnection();
            String sql = "SELECT * FROM TREATMENTRECORD WHERE UserID = ?";

            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TreatmentRecord rec = new TreatmentRecord();
                rec.setTrmtLoca(TreatmentLocationService.getByID(rs.getString("TrmtLocaID")));
                rec.setStatus(rs.getInt("UserStatus"));
                rec.setRecTimestamp(rs.getTimestamp("RecordTimestamp"));
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

    public static List<User> getAllRelatedUser(String id) {
        List<User> res = new ArrayList<User>();
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = cn.getConnection();
            String sql = "SELECT * FROM RELATED WHERE UserID_1 = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User rec = new User();
                rec = getUserById(rs.getString("UserID_2"));
                res.add(rec);
            }
        } catch (SQLException e) {
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

    public static List<User> getAllUser() {
        List<User> res = new ArrayList<User>();
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = cn.getConnection();
            String sql = "SELECT * FROM USERS";
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User rec = new User();
                rec.setId(rs.getString("UserID"));
                rec.setName(rs.getString("Username"));
                rec.setNoID(rs.getString("NoID"));
                rec.setBirthYear(rs.getInt("BirthYear"));
                rec.setAddress(LocationService.getByID(rs.getString("AddressID")));
                rec.setTrmtLoca(TreatmentLocationService.getByID(rs.getString("TrmtLocaID")));
                rec.setDebit(rs.getInt("DebitBalance"));
                rec.setStatus(rs.getInt("UserStatus"));

                // list related List
                List<User> relatedUserList = getAllRelatedUser(rec.getId());
                rec.setRelatedList(relatedUserList);
                // list treatment record
                List<TreatmentRecord> trmRecList = getTreatmentRecord(rec.getId());
                rec.setTrmtRec(trmRecList);
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

    public static String getUserID(String id) {
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;
        String result = null;

        try {
            connection = cn.getConnection();
            String sql = "SELECT * FROM USERS where NoID = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString("UserID");
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
        return result;
    }

    public static String getName(String id) {
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;
        String result = null;

        try {
            connection = cn.getConnection();
            String sql = "SELECT * FROM USERS where NoID = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString("Username");
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
        return result;
    }

    public static Boolean addOne(User user) throws SQLException {
        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        Account acc = new Account(user.getNoID(), "123456", 3);
        PreparedStatement ps0 = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        PreparedStatement ps4 = null;
        String query0 = "INSERT INTO ACCOUNT VALUES(?,?,?)";
        String query2 = "insert into USERS values(?, ?, ?, ?, ?, ?, ?, ?)";
        String query1 = "insert into LOCATION values(?, ?, ?)";
        String query3 = "update TREATMENTLOCATION set Occupancy = Occupancy + 1 where TrmtLocaID = ? ";
        String query4 = "insert into RELATED(UserID_1, UserID_2) values(?, ?)";
        try {
            connection.setAutoCommit(false);
            ps0 = connection.prepareStatement(query0);
            ps0.setString(1, acc.getId());
            String hash = BCrypt.hashpw(acc.getPass(), BCrypt.gensalt(13));
            ps0.setString(2, hash);
            ps0.setInt(3, acc.getRoles());

            ps1 = connection.prepareStatement(query1);
            ps1.setString(1, user.getAddress().getId());
            ps1.setString(2, user.getAddress().getAddress());
            String wardID = LocationService.getWardID(user.getAddress().getWard());
            ps1.setString(3, wardID);

            ps2 = connection.prepareStatement(query2);

            if (user.getTrmtLoca() != null) {
                ps3 = connection.prepareStatement(query3);
                ps3.setString(1, user.getTrmtLoca().getId());
                ps3.execute();
                ps2.setString(6, user.getTrmtLoca().getId());
            } else {
                ps2.setString(6, null);
            }
            ps2.setString(1, user.getId());
            ps2.setString(2, user.getName());
            ps2.setString(3, user.getNoID());
            ps2.setInt(4, user.getBirthYear());
            ps2.setString(5, user.getAddress().getId());
            ps2.setInt(7, user.getStatus());
            ps2.setInt(8, user.getDebit());

            ps0.execute();
            ps1.execute();
            ps2.execute();

            ps4 = connection.prepareStatement(query4);
            List<User> relatedUser = user.getRelatedList();
            for (int i = 0; i < relatedUser.size(); i++) {
                if (relatedUser.get(i).getStatus() < user.getStatus()) {
                    ps4.setString(1, relatedUser.get(i).getId());
                    ps4.setString(2, user.getId());
                } else {
                    ps4.setString(2, relatedUser.get(i).getId());
                    ps4.setString(1, user.getId());
                }
                ps4.execute();
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
        return false;
    }

    public static User getUserById(String id) {
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;
        User rec = new User();

        try {
            connection = cn.getConnection();
            String sql = "SELECT * FROM USERS where UserID = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rec.setId(rs.getString("UserID"));
                rec.setName(rs.getString("Username"));
                rec.setNoID(rs.getString("NoID"));
                rec.setBirthYear(rs.getInt("BirthYear"));
                rec.setAddress(LocationService.getByID(rs.getString("AddressID")));
                rec.setTrmtLoca(TreatmentLocationService.getByID(rs.getString("TrmtLocaID")));
                rec.setDebit(rs.getInt("DebitBalance"));
                rec.setStatus(rs.getInt("UserStatus"));

                // list related List
                List<User> relatedUserList = getAllRelatedUser(rec.getId());
                rec.setRelatedList(relatedUserList);
                // list treatment record
                List<TreatmentRecord> trmRecList = getTreatmentRecord(rec.getId());
                rec.setTrmtRec(trmRecList);
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
        return rec;
    }

    public static User getUserByName(String name) {
        connectDB cn = new connectDB();
        Connection connection = null;
        PreparedStatement ps = null;
        User rec = new User();

        try {
            connection = cn.getConnection();
            String sql = "SELECT * FROM USERS where Username = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rec.setId(rs.getString("UserID"));
                rec.setName(rs.getString("Username"));
                rec.setNoID(rs.getString("NoID"));
                rec.setBirthYear(rs.getInt("BirthYear"));
                rec.setAddress(LocationService.getByID(rs.getString("AddressID")));
                rec.setTrmtLoca(TreatmentLocationService.getByID(rs.getString("TrmtLocaID")));
                rec.setDebit(rs.getInt("DebitBalance"));
                rec.setStatus(rs.getInt("UserStatus"));

                // list related List
                List<User> relatedUserList = getAllRelatedUser(rec.getId());
                rec.setRelatedList(relatedUserList);
                // list treatment record
                List<TreatmentRecord> trmRecList = getTreatmentRecord(rec.getId());
                rec.setTrmtRec(trmRecList);
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
        return rec;
    }
}

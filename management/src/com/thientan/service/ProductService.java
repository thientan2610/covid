package com.thientan.service;

import com.thientan.model.Product;
import com.thientan.sql.connectDB;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class ProductService {

    public static Product getByID(String id) {
        Product res = null;
        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        PreparedStatement ps = null;
        try {
            String query = "SELECT * FROM PRODUCT WHERE ProductID = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                res = new Product();
                res.setId(rs.getString("ProductID"));
                res.setName(rs.getString("ProductName"));
                res.setUnit(rs.getString("Unit"));
                res.setPrice(rs.getInt("Price"));
                res.setImage(rs.getString("ProductImage"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Product getProductByName(String name) {
        Product res = null;
        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        PreparedStatement ps = null;
        try {
            String query = "SELECT * FROM PRODUCT WHERE ProductName = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                res = new Product();
                res.setId(rs.getString("ProductID"));
                res.setName(rs.getString("ProductName"));
                res.setUnit(rs.getString("Unit"));
                res.setPrice(rs.getInt("Price"));
                res.setImage(rs.getString("ProductImage"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static List<Product> getAllProduct() {
        List<Product> res = new ArrayList<Product>();
        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        PreparedStatement ps = null;

        try {
            String sql = "SELECT * FROM PRODUCT";
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product rec = new Product();

                rec.setId(rs.getString("ProductID"));
                rec.setName(rs.getString("ProductName"));
                rec.setUnit(rs.getString("Unit"));
                rec.setPrice(rs.getInt("Price"));
                rec.setImage(rs.getString("ProductImage"));

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

    public static List<Product> getAllProductByPackId(String id) {
        List<Product> res = new ArrayList<Product>();
        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        PreparedStatement ps = null;

        try {
            String sql = "SELECT * FROM PACK_DETAIL WHERE PackID = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product rec = new Product();
                rec = getByID(rs.getString("ProductID"));
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

    public static Image getImage(String id) {
        Image res = null;

        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        PreparedStatement ps = null;
        try {
            String query = "SELECT ProductImage FROM PRODUCT WHERE ProductID = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                InputStream is = rs.getBinaryStream("ProductImage");
                BufferedImage imag = ImageIO.read(is);
                res = imag;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public static Boolean addOne(Product ele) {
        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        PreparedStatement ps = null;

        try {
            String query = "insert into Product values(?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, ele.getId());
            ps.setString(2, ele.getName());
            ps.setString(3, ele.getUnit());
            ps.setInt(4, ele.getPrice());
            // ps.setImage(5, );
            if (ele.getImage() != null) {

                FileInputStream fin = new FileInputStream(ele.getImage());
                ps.setBinaryStream(5, fin, fin.available());
            }

            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean updOneNoImg(Product ele) {
        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        PreparedStatement ps = null;
        try {
            String query = "update PRODUCT set ProductName = ?,Unit = ?, Price = ? where ProductID = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, ele.getName());
            ps.setString(2, ele.getUnit());
            ps.setInt(3, ele.getPrice());

            ps.setString(4, ele.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean updOne(Product ele) {
        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        PreparedStatement ps = null;

        try {
            String query = "update PRODUCT set ProductName = ?,Unit = ?, Price = ?, ProductImage = ? where ProductID = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, ele.getName());
            ps.setString(2, ele.getUnit());
            ps.setInt(3, ele.getPrice());
            // ps.setImage(5, );
//            ele.setImage("D:\\a.png");
            if (ele.getImage() != null) {

                FileInputStream fin = new FileInputStream(ele.getImage());
                ps.setBinaryStream(4, fin, fin.available());
            }
            ps.setString(5, ele.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean delOne(String id) {
        connectDB cn = new connectDB();
        Connection connection = cn.getConnection();
        PreparedStatement ps = null;
        String query = "delete from Product where ProductID = ?";
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

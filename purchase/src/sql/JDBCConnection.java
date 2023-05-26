/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class JDBCConnection {
    public Connection getConnection() {
        @SuppressWarnings("UnusedAssignment")
        Connection conn = null;
        String dbURL = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;databaseName=qlCovidPayment";
        String user = "sa";
        String pass = "123456";
        
        try {
            conn = DriverManager.getConnection(dbURL, user, pass);
//            if (conn != null) {
//                DatabaseMetaData dm = conn.getMetaData();
//                System.out.println("Driver name: " + dm.getDriverName());
//                System.out.println("Driver version: " + dm.getDriverVersion());
//                System.out.println("Product name: " + dm.getDatabaseProductName());
//                System.out.println("Product version: " + dm.getDatabaseProductVersion());
//            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return conn;
    }
}

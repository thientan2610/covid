/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package management.purchase;

import Manager.PaymentMain;
import Networking.TCPServer;

/**
 *
 * @author Admin
 */
public class ManagementPurchase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PaymentMain main = new PaymentMain();
                main.setVisible(true);
                TCPServer tcpServer = new TCPServer();
                tcpServer.setCheckBox(main.getjCheckBox1());
                tcpServer.open();
                tcpServer.start();
            }
        });
        
        
        
    }
    
}

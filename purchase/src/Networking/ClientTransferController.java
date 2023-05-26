/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Networking;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
/**
 *
 * @author Admin
 */
public class ClientTransferController implements ActionListener{
    private ClientTransferView view;
 
    public ClientTransferController(ClientTransferView view) {
        this.view = view;
        view.getBtnSendFile().addActionListener(this);
    }
 
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(view.getBtnSendFile().getText())) {
            String host = view.getTextFieldHost().getText().trim();
//            int port = Integer.parseInt(view.getTextFieldPort().getText().trim());
                       String port = view.getTextFieldPort().getText().trim();
            if (host != "") {
                TCPClient tcpClient;
                tcpClient = new TCPClient(view.getTextAreaResult(),host, port);
                tcpClient.connectServer();
                tcpClient.start();
            } else {
                JOptionPane.showMessageDialog(view, "Host, Port "
                    + "và FilePath phải khác rỗng.");
            }
        }
    }
}

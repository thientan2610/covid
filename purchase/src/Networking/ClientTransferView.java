/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Networking;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author Admin
 */
class ClientTransferView extends JFrame{
    private static final long serialVersionUID = 1L;
 
    private JLabel labelHost;
    private JTextField textFieldHost;
    private JLabel labelPort;
    private JTextField textFieldPort;
    private JButton btnSendFile;
    private JTextArea textAreaResult;
 
    public ClientTransferView() {
        setTitle("Client - truyền file bằng giao thức TCP/IP");
        labelHost = new JLabel("Host:");
        textFieldHost = new JTextField();
        labelPort = new JLabel("Port:");
        textFieldPort = new JTextField();
        labelHost.setBounds(20, 20, 50, 25);
        textFieldHost.setBounds(55, 20, 120, 25);
        labelPort.setBounds(190, 20, 50, 25);
        textFieldPort.setBounds(220, 20, 50, 25);
 
        btnSendFile = new JButton("Send File");
        btnSendFile.setBounds(20, 80, 80, 25);
        textAreaResult = new JTextArea();
        textAreaResult.setBounds(20, 110, 490, 150);
 
        add(labelHost);
        add(textFieldHost);
        add(labelPort);
        add(textFieldPort);
        add(btnSendFile);
        add(textAreaResult);
 
        setLayout(null);
        setSize(600, 350);
        setVisible(true);
        // thoát chương trình khi tắt window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
        public JLabel getLabelHost() {
        return labelHost;
    }
 
    public void setLabelHost(JLabel labelHost) {
        this.labelHost = labelHost;
    }
 
    public JTextField getTextFieldHost() {
        return textFieldHost;
    }
 
    public void setTextFieldHost(JTextField textFieldHost) {
        this.textFieldHost = textFieldHost;
    }
 
    public JLabel getLabelPort() {
        return labelPort;
    }
 
    public void setLabelPort(JLabel labelPort) {
        this.labelPort = labelPort;
    }
 
    public JTextField getTextFieldPort() {
        return textFieldPort;
    }
 
    public void setTextFieldPort(JTextField textFieldPort) {
        this.textFieldPort = textFieldPort;
    }
 
    public JButton getBtnSendFile() {
        return btnSendFile;
    }
 
    public void setBtnSendFile(JButton btnSendFile) {
        this.btnSendFile = btnSendFile;
    }
 
    public JTextArea getTextAreaResult() {
        return textAreaResult;
    }
 
    public void setTextAreaResult(JTextArea textAreaResult) {
        this.textAreaResult = textAreaResult;
    }
}

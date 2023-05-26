/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Networking;

import Crypto.AES;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.logging.*;
import javax.crypto.*;
import model.*;
import javax.swing.*;
import static service.AccountService.*;
import static service.RecordService.*;
/**
 *
 * @author Admin
 */
public class TCPServer extends Thread{
    private ServerSocket serverSocket;
    private final int port = 1234;
    private JCheckBox checkBox;

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(JCheckBox checkBox) {
        this.checkBox = checkBox;
    }
    
    public void open() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("server is open on port " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeSocket(Socket socket) {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeStream(InputStream inputStream) {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void closeStream(OutputStream outputStream) {
        try {
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public Boolean saveDB(String str){
        Account acc = setAccount(str);
        Boolean flag = false;
        if(acc.getMoney() == 0)
            flag = insAccount(acc);
        else
            flag = insRecord(Acc2Rec(acc));
        return flag;
    }
    public String encryptedStr(String orginal) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        String res = new AES().encryptedTxt(orginal);        
        return res;
    }
    public String decryptedStr(String orginal) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        String res = new AES().decryptedTxt(orginal);  
        return res;
    }
    @Override
    public void run() {
        while (true) {
            Socket server = null;
            InputStream inFromClient = null;
            OutputStream clientOut = null;
            PrintWriter pw = null;
            BufferedReader br = null;           
            try {
                // accept connect from client and create Socket object
                server = serverSocket.accept();
                System.out.println("connected to "+ server.getRemoteSocketAddress());
                inFromClient = new DataInputStream(server.getInputStream());
                br = new BufferedReader(new InputStreamReader(inFromClient)); 
                clientOut = server.getOutputStream();
                pw = new PrintWriter(clientOut, true);
                String strg = br.readLine();
                if(strg != null){
                    try {
                    Boolean opt = saveDB(decryptedStr(strg));
                    
                    if(opt)
                        pw.println("success");
                        checkBox.doClick();
                    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
                        pw.println("failed");
                        Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // close all stream
                closeStream(clientOut);
                closeStream(inFromClient);
                // close session
                closeSocket(server);
            }
        }
    }
}

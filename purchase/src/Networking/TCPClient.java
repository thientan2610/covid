/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Networking;
import Crypto.AES;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JTextArea;
/**
 *
 * @author Admin
 */
public class TCPClient extends Thread{
    private Socket client;
    private final int port = 1234;
    private final JTextArea textAreaLog;
    private String id;
    private String money;
    public TCPClient(JTextArea textAreaLog, String id, String money) {
        this.textAreaLog = textAreaLog;
        this.id = id;
        this.money = money;
    }
    public void connectServer() {
        try {
            client = new Socket(InetAddress.getLocalHost(), port);
            textAreaLog.append("connected to server.");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeSocket() {
        try {
            if (client != null) {
                client.close();
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
    public String encryptedStr(String orginal) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        String res = new AES().encryptedTxt(orginal);        
        return res;
    }
    public String decryptedStr(String orginal) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        String res = new AES().decryptedTxt(orginal);  
        return res;
    }

    /**
     *
     */
    @Override
    public void run() {
        DataOutputStream outToServer = null;
        InputStream clientIn = null;
        PrintWriter pw = null;
        BufferedReader br = null;       
        try {
            // make greeting
            outToServer = new DataOutputStream(client.getOutputStream());
            pw = new PrintWriter(outToServer, true);
            String str = id + "@" + money;
            try {
                pw.println(encryptedStr(str));
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
                Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            clientIn = client.getInputStream();
            br = new BufferedReader(new InputStreamReader(clientIn));
            str = br.readLine();    
            if(str != null){
                textAreaLog.append("send file to server "+ str);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            closeStream(clientIn);
            closeStream(outToServer);
            // close session
            closeSocket();
        }
    }
}

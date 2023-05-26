/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Crypto;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author Admin
 */
public class AES {
    protected static String SECRET_KEY = "-kina.kim.ngan-*";
    protected SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
    Cipher cipher;

    public AES() throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
    }
    public String encryptedTxt(String original) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        String encrypted = null;
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] byteEncrypted = cipher.doFinal(original.getBytes());
        encrypted =  Base64.getEncoder().encodeToString(byteEncrypted);
        return encrypted;
    }
    public String decryptedTxt(String s) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        String decrypted = null;
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] byteDecrypted = cipher.doFinal(Base64.getDecoder().decode(s));
        decrypted = new String(byteDecrypted);
        return decrypted;
    }
}

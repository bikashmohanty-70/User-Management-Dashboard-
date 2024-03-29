package com.bridgelabz.utility;
/**
 * 
 * @author Bikash Mohanty
 * @since 15th November 2017
 * @version 1.0
 * 
 * Purpose: Encrypting and decrypting Password
 *
 */

import java.security.NoSuchAlgorithmException;
import java.util.Base64;    
import javax.crypto.Cipher;  
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey; 

public class EncryptionDecryptionAES 
{
	static Cipher cipher;
	private EncryptionDecryptionAES() {
		
	}
	
	public static SecretKey secretKeyGenerator()
	{
		/* 
        create key 
        If we need to generate a new key use a KeyGenerator
        If we have existing plaintext key use a SecretKeyFactory
       */ 
		KeyGenerator keyGenerator = null;
		SecretKey secretKey = null;
		try 
		{
			keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			secretKey = keyGenerator.generateKey();
		} 
		catch (NoSuchAlgorithmException e) 
		{
			System.out.println("Generating Secret key is not Possible at this moment..");
			e.printStackTrace();
		}
		
		/*
        Cipher Info
        Algorithm : for the encryption of electronic data
        mode of operation : to avoid repeated blocks encrypt to the same values.
        padding: ensuring messages are the proper length necessary for certain ciphers 
        mode/padding are not used with stream cyphers.  
       */
		
		try 
		{
			cipher = Cipher.getInstance("AES");	//SunJCE provider AES algorithm, mode(optional) and padding schema(optional)
		} 
		catch (NoSuchAlgorithmException | NoSuchPaddingException e) 
		{
			e.printStackTrace();
		}
		return secretKey; 
		
	}
	
//    public static void main(String[] args) throws Exception {
//        /* 
//         create key 
//         If we need to generate a new key use a KeyGenerator
//         If we have existing plaintext key use a SecretKeyFactory
//        */ 
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(128); // block size is 128bits
//        SecretKey secretKey = keyGenerator.generateKey();
//
//        /*
//          Cipher Info
//          Algorithm : for the encryption of electronic data
//          mode of operation : to avoid repeated blocks encrypt to the same values.
//          padding: ensuring messages are the proper length necessary for certain ciphers 
//          mode/padding are not used with stream cyphers.  
//         */
//        cipher = Cipher.getInstance("AES"); //SunJCE provider AES algorithm, mode(optional) and padding schema(optional)  
//        
//
//        String plainText = "Biki12@3";
//        System.out.println("Plain Text Before Encryption: " + plainText);
//
//        String encryptedText = encrypt(plainText, secretKey);
//        System.out.println("Encrypted Text After Encryption: " + encryptedText);
//
//        String decryptedText = decrypt(encryptedText, secretKey);
//        System.out.println("Decrypted Text After Decryption: " + decryptedText);
//    }

    public static String encrypt(String plainText, SecretKey secretKey)
            throws Exception {
        byte[] plainTextByte = plainText.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedByte);
        return encryptedText;
    }

    public static String decrypt(String encryptedText, SecretKey secretKey)
            throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = decoder.decode(encryptedText);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        String decryptedText = new String(decryptedByte);
        return decryptedText;
    }
}

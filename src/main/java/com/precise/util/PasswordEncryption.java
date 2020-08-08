package com.precise.util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;
public class PasswordEncryption {
private static PasswordEncryption instance;
	
	
	private PasswordEncryption() { }

	public synchronized String encrypt(String plaintext) throws PasswordEncryptionException{
	//	log.info("inside encrypt password ");
		MessageDigest md = null;
		try{
			md = MessageDigest.getInstance("SHA"); //step 2
			 md.update(plaintext.getBytes("UTF-8"));
		}catch(NoSuchAlgorithmException e){ 			
			throw new PasswordEncryptionException();
		}catch(UnsupportedEncodingException e){
			
			throw new PasswordEncryptionException();
		}	

		byte raw[] = md.digest(); //step 4
		String hash = (new BASE64Encoder()).encode(raw); //step 5   
		return hash; //step 6
	}

	public static synchronized PasswordEncryption getInstance() {
		if(instance == null){
			instance = new PasswordEncryption();
		}
		return instance;
	}	
	
	
	public static void main(String[] args) {
		
		
	}
}

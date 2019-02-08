package org.ulearnuhelp.security.cryptography;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;


/**
 * 
 * This class is an example class to demonstrate usage of java.security.MessageDigest to create a Message Digest from a text file using 
 * SHA-256 algorithm.
 * 
 * This class has been created for demonstration purpose for the use of an Non-governmental organization named www.ulearnuhelp.org, the author of this 
 * code has given permission to use this class in any form its users feel like, total freedom of expression.
 * 
 * The author will like to thank https://www.baeldung.com/sha-256-hashing-java 
 * The users of this class are also encouraged to visit https://csrc.nist.gov/projects/hash-functions for a better understanding of Hash functions. 
 * 
 * @author swarajitroy (swarajit.roy@gmail.com)
 *
 */


public class MessageDigestExample {

	public static void main(String[] args) throws Exception{
		

		try {
			
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			System.out.println("Security Provider Name for MessageDigest = " + digest.getProvider().getName());
			System.out.println("Security Provider Version  = " + digest.getProvider().getVersion());
			System.out.println("Security Provider Info  = " + digest.getProvider().getInfo());
			System.out.println("Security Algorithm Name  = " + digest.getAlgorithm());
			System.out.println("Hash Length (Bytes)  = " + digest.getDigestLength());
			
			String fileToDigest = args[0] ;
			System.out.println("Path of the input file for which Message Digest will be calculated is = " + fileToDigest);
			File file = new File(fileToDigest) ;
			byte[] bytesArray = new byte[(int) file.length()]; 

			  FileInputStream fis = new FileInputStream(file);
			  fis.read(bytesArray); //read file into bytes[]
			  fis.close();

			
			System.out.println("Input File byte length = " + bytesArray.length);
			byte[] encodedhash = digest.digest(bytesArray);
			System.out.println("Digest/Hashed content byte length = " + encodedhash.length);
			
			String digestInHex = bytesToHex(encodedhash);
			System.out.println("Digest/Hashed content in HEX : " + digestInHex);

			
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		
		

	}
	
	
	private static String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}


}

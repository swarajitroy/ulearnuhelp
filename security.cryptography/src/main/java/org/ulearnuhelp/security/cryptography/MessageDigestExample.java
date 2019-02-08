package org.ulearnuhelp.security.cryptography;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.util.Enumeration;

// https://csrc.nist.gov/projects/hash-functions
// Some good libraries other than Java default API - A. Google Guava library, B. Apache Commons Codecs
// Some security provider other than Java packaged - Bounty Castle
// Java 9 is required for SHA 3 algorithms for default support




// https://www.baeldung.com/sha-256-hashing-java

public class MessageDigestExample {

	public static void main(String[] args) throws Exception{
		

		try {
			
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			System.out.println("Security Provider Name for MessageDigest = " + digest.getProvider().getName());
			System.out.println("Security Provider Version  = " + digest.getProvider().getVersion());
			System.out.println("Security Provider Info  = " + digest.getProvider().getInfo());
			System.out.println("Security Algorithm Name  = " + digest.getAlgorithm());
			System.out.println("Hash Length (Bytes)  = " + digest.getDigestLength());
			
			String fileToDigest = "C:\\swararoy-2016-l420-bkup\\swararoy\\swararoy-2019\\ulearnuhelp.org\\security\\cryptography\\security.cryptography\\src\\main\\java\\org\\"
					+ "ulearnuhelp\\security\\cryptography\\messageDigest.txt" ;
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

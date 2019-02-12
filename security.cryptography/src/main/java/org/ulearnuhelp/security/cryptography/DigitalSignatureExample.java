package org.ulearnuhelp.security.cryptography;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class DigitalSignatureExample {

	public static void main(String[] args) throws Exception {
		
		
		// Sender Side
		
		
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(2048);

		System.out.println("Security Provider Name for Asymmetric Key Generator = " + keyPairGen.getProvider().getName());
		System.out.println("Security Provider Version for Asymmetric Key Generator = " + keyPairGen.getProvider().getVersion());
		System.out.println("Security Provider Information for Asymmetric Key Generator = " + keyPairGen.getProvider().getInfo());

		KeyPair keyPair = keyPairGen.generateKeyPair();
		PublicKey publicKey = keyPair.getPublic();
		System.out.println("Public Key format =" + publicKey.getFormat());
		PrivateKey privateKey = keyPair.getPrivate();
		System.out.println("Private Key format =" +  privateKey.getFormat());
		
		Signature signature = Signature.getInstance("SHA1withRSA"); 
		signature.initSign(privateKey);
		
		FileInputStream fis = new FileInputStream(args[0]);
		BufferedInputStream bufin = new BufferedInputStream(fis);
		byte[] buffer = new byte[1024];
		int len;
		while ((len = bufin.read(buffer)) >= 0) {
			signature.update(buffer, 0, len);
		};
		bufin.close();

		byte[] realSig = signature.sign();
		String signatureHex = bytesToHex(realSig) ;
		
		System.out.println("Computed Public Key -> " + bytesToHex(publicKey.getEncoded()));
		System.out.println("Computed Digital Signature -> " + signatureHex);
		System.out.println("Input DataFile Path -> " + args[0]);
		

		// Receiver  Side 
		Signature recieverSidesignature = Signature.getInstance("SHA1withRSA"); 
		recieverSidesignature.initVerify(publicKey);
		
		fis = new FileInputStream(args[0]);
		bufin = new BufferedInputStream(fis);
		byte[] buffer2 = new byte[1024];
		int len2;
		while ((len2 = bufin.read(buffer2)) >= 0) {
			recieverSidesignature.update(buffer2, 0, len2);
		};
		bufin.close();
		
		boolean verifies = recieverSidesignature.verify(realSig);
        System.out.println("signature verifies: " + verifies);

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

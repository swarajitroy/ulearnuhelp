package org.ulearnuhelp.security.cryptography;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

/**
 *
 *
 * This class is an example class to demonstrate usage of javax.crypto.Cipher,
 * java.security.PublicKey, java.security.PrivateKey to demonstrate an
 * encryption and decryption using Asymmetric Key technologies.
 * 
 * 
 * Popular algorithm
 * 
 * RSA - 1024 to 4096 bit keys
 * 
 * However for knowledge purpose, one should look into history of asymmetric key
 * algorithms and take a look into algorithms like Diffie-Hellman Algorithm and
 * Elliptical Wave Theory Algorithm
 *
 * 
 * This class has been created for demonstration purpose for the use of an
 * Non-governmental organization (NGO) named www.ulearnuhelp.org, the author of
 * this code has given permission to use this class in any form its users feel
 * like, total freedom of expression.
 * 
 * https://www.tech-coffee.net/public-key-infrastructure-part-1-introduction-encryption-signature/
 * https://resources.infosecinstitute.com/mathematical-algorithms-asymmetric-cryptography-introduction-public-key-infrastructure/#grefß
 * https://www.mkyong.com/java/java-asymmetric-cryptography-example/
 * 
 * @author swarajitroy
 *
 */

public class ASymmetricKeyEncryptionExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String plainTextSender = "Hi Alice, I am Bob. Asymmetric Cryptography is interesting and used widely in industry. "
				+ "I have used your Public key to encrypt this text as it will be only decrypted by your private key";
		String plainTextReceiver = "Hi Bob, this is Alice here. Thanks for your message. Yes Asymmetric Cryptography is interesting and used widely in industry" ;
		

		try {

			KeyPairGenerator senderKPG = KeyPairGenerator.getInstance("RSA");
			senderKPG.initialize(2048);

			System.out.println("Security Provider Name for Asymmetric Key Generator = " + senderKPG.getProvider().getName());
			System.out.println("Security Provider Version for Asymmetric Key Generator = " + senderKPG.getProvider().getVersion());
			System.out.println("Security Provider Information for Asymmetric Key Generator = " + senderKPG.getProvider().getInfo());

			KeyPair senderKP = senderKPG.generateKeyPair();
			PublicKey senderPublicKey = senderKP.getPublic();
			System.out.println("Public Key format =" + senderPublicKey.getFormat());
			PrivateKey senderPrivateKey = senderKP.getPrivate();
			System.out.println("Private Key format =" +  senderPrivateKey.getFormat());

			KeyPairGenerator recieverKPG = KeyPairGenerator.getInstance("RSA");
			recieverKPG.initialize(2048);

			KeyPair receiverKP = recieverKPG.genKeyPair();
			PublicKey receiverPublicKey = receiverKP.getPublic();
			PrivateKey receiverPrivateKey = receiverKP.getPrivate();
		
			// Sender Side - encrypt with Receiver Public Key
			Cipher senderCipher = Cipher.getInstance("RSA");
			senderCipher.init(Cipher.ENCRYPT_MODE, receiverPublicKey);

			byte[] encrytedBytesSender = senderCipher.doFinal(plainTextSender.getBytes("UTF-8"));
			String encryptedHexSender = bytesToHex(encrytedBytesSender);
			System.out.println("[communication step #1] Sender Plain Text -> " + plainTextSender);
			System.out.println("[communication step #2] Sender Cipher Text -> " + encryptedHexSender);

			// Receiver Side - decrypt with Receiver Private Key
			Cipher receiverCipher = Cipher.getInstance("RSA");
			receiverCipher.init(Cipher.DECRYPT_MODE, receiverPrivateKey);
			byte[] decryptedByteReceiver = receiverCipher.doFinal(encrytedBytesSender);
			String decryptedTextReceiver = new String(decryptedByteReceiver, "UTF-8");
			System.out.println("[communication step #3] Reciever Decrypted Text -> " + decryptedTextReceiver);
			
			// Receiver Side - encrypt greetings back message
			
			receiverCipher.init(Cipher.ENCRYPT_MODE, senderPublicKey);
			byte[] encrytedBytesReceiver = receiverCipher.doFinal(plainTextReceiver.getBytes("UTF-8"));
			String encryptedHexReceiver = bytesToHex(encrytedBytesReceiver);
			System.out.println("[communication step #4] Receiver Plain Text -> " + plainTextReceiver);
			System.out.println("[communication step #5] Reciever Cipher Text -> " + encryptedHexReceiver);
			
			// Sender Side Decrypt 
			senderCipher.init(Cipher.DECRYPT_MODE, senderPrivateKey);
			byte[] decryptedByteSender = senderCipher.doFinal(encrytedBytesReceiver);
			String decryptedTextSender = new String(decryptedByteReceiver, "UTF-8");
			System.out.println("[communication step #6] Sender Decrypted Text -> " + decryptedTextSender);
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

}

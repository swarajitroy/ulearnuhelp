package org.ulearnuhelp.security.cryptography;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * 
 * This class is an example class to demonstrate usage of java.crypto.Cipher,
 * java.crypto.SecretKey to demonstrate an encryption and decryption using
 * Symmetric Key technologies.
 * 
 * Popular algorithm
 * 
 * Advanced Encryption Standard (AES) keys are symmetric keys that can be three
 * different key lengths (128, 192, or 256 bits). AES is the encryption standard
 * that is recognized and recommended by the US government.
 * 
 * However for knowledge purpose, one should look into history of symmetric key
 * algorithms and take a look into algorithms like Data Encryption Standard
 * (DES), triple-strength DES (3DES), Rivest Cipher 2 (RC2), Rivest Cipher 4
 * (RC4), Blowfish etc
 * 
 * 
 * 
 * This class has been created for demonstration purpose for the use of an
 * Non-governmental organization (NGO) named www.ulearnuhelp.org, the author of
 * this code has given permission to use this class in any form its users feel
 * like, total freedom of expression.
 * 
 * 
 *
 */

public class SymmetricKeyEncryptionExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SecretKey secretKey = null;
		String plainText = "Action:AllowResetPassword#User:swarajit";

		try {

			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			System.out.println(
					"Security Provider Name for Symmetric Key Generator = " + keyGenerator.getProvider().getName());
			System.out.println("Security Provider version for Symmetric Key Generator = "
					+ keyGenerator.getProvider().getVersion());
			System.out.println(
					"Security Provider info for Symmetric Key Generator = " + keyGenerator.getProvider().getInfo());
			secretKey = keyGenerator.generateKey();
			System.out.println("Symmetric Key Algorithm = " + secretKey.getAlgorithm());

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		try {
			Cipher cipher = Cipher.getInstance("AES");
			byte[] encodedPlainTextBytes = plainText.getBytes("UTF-8");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] encryptedBytes = cipher.doFinal(encodedPlainTextBytes);
			String cipherText = bytesToHex(encryptedBytes);

			System.out.println("Plain Text = " + plainText);
			System.out.println("Cipher Text = " + cipherText);

			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
			String decryptedText = new String(decryptedBytes, "UTF-8");
			System.out.println("Decrypted Text = " + decryptedText);

		} catch (Exception e) {

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

package com.srikanth.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * ** Public Key Cryptography **
 * 
 * In Public Key Cryptography, public and private keys are generated as a pair
 * and need longer lengths than the equivalent strength private key encryption keys.
 * 
 * Public Key encryption is slow (100 to 1000 times slower than private key encryption).
 * So a hybrid technique is used in practice: public key encryption is used to distribute
 * a private key, known as a session key. to other party, and then private key encryption
 * using that private session key is used for the bulk of the message encryption. 
 * 
 * The following two algorithms used in public key encryption.
 * RSA:- This algorithm is the most popular public key cipher, but it's not supported in JDK 1.4. 
 * You must use a third-party library like BouncyCastle to get this support.
 * Typical key lengths for the RSA algorithm is 1024 bits.
 * 
 * Diffie-Hellman:- This algorithm is technically known as a key-agreement algorithm. 
 * It cannot be used for encryption, but can be used to allow two parties to derive a secret 
 * key by sharing information over a public channel. This key can then be used for private key encryption.
 * 
 * The Cipher class manipulates public key algorithms using keys produced by the KeyPairGenerator class.
 * 
 * 
 * @author Srikanth
 *
 */
public class PublicKeyCryptDemo {

	static Logger logger = LoggerFactory.getLogger(PublicKeyCryptDemo.class);
	
	// Public Key Algorithms
	public static final String RSA = "RSA"; // supports 1024
	public static final String DIFFIE_HELLMAN= "DiffieHellman";
	
	// Cipher Modes
	public static final String MODE_NONE = "NONE"; // No mode
	public static final String ECB = "ECB"; // Electronic Code Block
	public static final String CBC = "CBC"; // Cipher Block Chaining
	public static final String CFB = "CFB"; // Cipher Feedback Mode
	public static final String OFB = "OFB"; // Output Feedback Mode
	public static final String PCBC = "PCBC"; // Propagating Cipher Block Chaining
	public static final String CTR = "CTR"; // Simplification of OFB and counter mode
	public static final String CTS = "CTS"; // Cipher Text Stealing
	
	// Padding Techniques
	public static final String NO_PADD = "NoPadding"; // No Padding. Waste of memory blocks
	public static final String ISO10126_PADD = "ISO10126Padding"; // It is for Block Ciphers
	public static final String PKCS1_PADD = "PKCS1Padding";
	public static final String PKCS5_PADD = "PKCS5Padding";
	public static final String SSL3_PADD = "SSL3Padding";

	public static void encryptAndDecrypt(String message, String algorithm, String cipherMode, String paddingType) {
		try {
			byte[] plainText = message.getBytes("UTF8");
			logger.info("Started Key Generation using " + algorithm + " Public Key Algorithm..");
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
			keyPairGenerator.initialize(findBitLength(algorithm));
			KeyPair key = keyPairGenerator.generateKeyPair();
			logger.info("Completed Key Pair Generation using " + algorithm + " Public Key Algorithm..");
			
			Cipher cipher = Cipher.getInstance(algorithm + "/" + cipherMode + "/" + paddingType);
			logger.info("Provider Info: " + cipher.getProvider().getInfo());
			logger.info("Starting Encryption..");
			cipher.init(Cipher.ENCRYPT_MODE, key.getPublic());
			byte[] cipherText = cipher.doFinal(plainText);
			logger.info("Completed Encryption..");
			logger.info("Encrypted Cipher Text: " + new String(cipherText, "UTF8"));
			
			logger.info("Starting Decryption..");
			cipher.init(Cipher.DECRYPT_MODE, key.getPrivate());
			byte[] decipherText = cipher.doFinal(cipherText);
			logger.info("Completed Decryption..");
			logger.info("Decrypted Plain Text: " + new String(decipherText, "UTF8"));
			
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException 
				| NoSuchPaddingException 
				| InvalidKeyException 
				| IllegalBlockSizeException 
				| BadPaddingException e) {
			logger.error("Error Occurred: ", e);
		}
	}
	
	private static int findBitLength(String algorithm) {
		
		switch(algorithm) {
			case RSA:
				return 1024;
			case DIFFIE_HELLMAN:
				return 1024;
		}
		return 32;
	}
}

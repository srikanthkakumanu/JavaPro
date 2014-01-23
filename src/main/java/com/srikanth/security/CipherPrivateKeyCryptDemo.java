package com.srikanth.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * ** Keeping a message Confidential ** 
 * Message digests may ensure integrity of a message, but they can't be 
 * used to ensure the confidentiality of a message.
 * For that, we need to use private key cryptography to exchange private
 * messages. 
 * But private key cryptography have one major drawback. how does
 * the private key get to other party in the first place? It is solved by public key
 * cryptography by encrypting messages between two parties without prior
 * agreement on the key.
 * 
 * In Cryptography, You can encrypt single bits or chunks of bits, called
 * blocks. The blocks, called cipher blocks, are typically 64 bits in size. If
 * the message is not a multiple of 64 bits, then the short block must be
 * padded. Single-bit encryption is more common in hardware implementations.
 * Single-bit ciphers are called stream ciphers . The strength of the private
 * key encryption is determined by the cryptography algorithm and the length of
 * the key. If the algorithm is sound, then the only way to attack it is with a
 * brute-force approach of trying every possible key, which will take an average
 * of (1/2)*2*n attempts, where n is the number of bits in the key.
 * 
 * Generally, 128-bit keys are preferred today. With them, if one million keys
 * could be tried every second, it would take an average of many times the age
 * of the universe to find a key!
 * 
 * Padding: if a block cipher is used and the message length is not a multiple
 * of the block length, the last block must be padded with bytes to yield a full
 * block size. There are many ways to pad a block, such as using all zeroes or
 * ones. With PKCS5, a short block is padded with a repeating byte whose value
 * represents the number of remaining bytes.
 * 
 * JDK7 supports the following padding options: No padding, PKCS1, PKCS5, OAEP,
 * SSL3
 * 
 * Encryption & Cipher A given cipher can be used in a variety of modes. Modes
 * allow you to specify how encryption will work.
 * 
 * For example, you can allow the encryption of one block to be dependent on the
 * encryption of the previous block, or you can make the encryption of one block
 * independent of any other blocks.
 * 
 * The mode you choose depends on your needs and you must consider the
 * trade-offs (security, ability to parallel process, and tolerance to errors in
 * both the plaintext and the ciphertext).
 * 
 * Cipher Modes are: ECB (Electronic Code Book) CBC (Cipher Block Chaining) CFB
 * (Cipher Feedback Mode) OFB (Output Feedback Mode) PCBC (Propagating Cipher
 * Block Chaining)
 * 
 * 
 * Private Key Algorithm Types:
 * Note:- AES is better. RC4 is faster than DES, RC2. Triple DES is most secure but performance cost is high.
 * DES:- (Data Encryption Standard) is official US Govt. standard and it is a 56
 * bit algorithm. 
 * TripleDES:- This algorithm is used to deal with the growing
 * weakness of a 56-bit key while leveraging DES technology by running plain text
 * through the DES algorithm three times, with two keys, giving an effective key
 * strength of 112 bits. TripleDES is sometimes known as DESede (for encrypt,
 * decrypt, and encrypt, which are the three phases). 
 * AES:- AES (Advanced Encryption Standard) replaces DES as the U.S. standard. It is
 * also known as the Rinjdael algorithm. It is a 128-bit block cipher with key lengths 
 * of 128, 192, or 256 bits. 
 * RC2, RC4 & RC5:- These are algorithms from a leading encryption security
 * company, RSA Security. 
 * Blowfish:- This algorithm is a block cipher with variable key lengths from 32 to 448 bits
 * (in multiples of 8), and was designed for efficient implementation in software for 
 * microprocessors. 
 * PBE:- PBE (Password Based Encryption) can be used in combination with a variety 
 * of message digest and private key algorithms.
 * 
 * @author Srikanth
 * 
 */

/**
 * The Cipher class manipulates private key algorithms using a key produced by
 * the KeyGenerator class.
 * 
 */
public class CipherPrivateKeyCryptDemo {
	static Logger logger = LoggerFactory.getLogger(CipherPrivateKeyCryptDemo.class);
	
	// Private Key Algorithms
	public static final String DES = "DES";
	public static final String TRIPLE_DES = "TripleDES";
	public static final String AES = "AES";		
	public static final String RC2 = "RC2";
	public static final String RC4 = "RC4"; // It supports 40-128
	public static final String RC5 = "RC5"; // It supports 32, 64, 128 bit
	public static final String BLOWFISH = "Blowfish";
	public static final String RSA = "RSA";
	
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
			logger.info("Started Key Generation using " + algorithm + " Private Key Algorithm..");
			KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
			keyGenerator.init(findBitLength(algorithm));
			Key key = keyGenerator.generateKey();
			logger.info("Completed Key Generation using " + algorithm + " Private Key Algorithm..");
			
			Cipher cipher = Cipher.getInstance(algorithm + "/" + cipherMode + "/" + paddingType);
			logger.info("Provider Info: " + cipher.getProvider().getInfo());
			logger.info("Starting Encryption..");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] cipherText = cipher.doFinal(plainText);
			logger.info("Completed Encryption..");
			logger.info("Encrypted Cipher Text: " + new String(cipherText, "UTF8"));
			
			logger.info("Starting Decryption..");
			cipher.init(Cipher.DECRYPT_MODE, key);
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
			case DES:
				return 56;
			case AES:
				return 128;
			case TRIPLE_DES:
				return 112;
			case BLOWFISH:
				return 32;
			case RC2:
				return 40;
			case RC4:
				return 128;
			case RC5:
				return 128;
	
		}
		return 32;
	}
}

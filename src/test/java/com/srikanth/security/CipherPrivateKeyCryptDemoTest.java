package com.srikanth.security;

import org.junit.Test;

public class CipherPrivateKeyCryptDemoTest {

	@Test
	public void testEncryptAndDecrypt() {
		 
//		CipherPrivateKeyCryptDemo.encryptAndDecrypt("Hello",
//				CipherPrivateKeyCryptDemo.DES, CipherPrivateKeyCryptDemo.ECB,
//				CipherPrivateKeyCryptDemo.PKCS5_PADD);

//		CipherPrivateKeyCryptDemo.encryptAndDecrypt("Hello",
//				CipherPrivateKeyCryptDemo.AES, CipherPrivateKeyCryptDemo.ECB,
//				CipherPrivateKeyCryptDemo.PKCS5_PADD);

//		CipherPrivateKeyCryptDemo.encryptAndDecrypt("Hello",
//				CipherPrivateKeyCryptDemo.TRIPLE_DES,
//				CipherPrivateKeyCryptDemo.ECB,
//				CipherPrivateKeyCryptDemo.PKCS5_PADD);

//		CipherPrivateKeyCryptDemo.encryptAndDecrypt("Hello",
//				CipherPrivateKeyCryptDemo.BLOWFISH,
//				CipherPrivateKeyCryptDemo.ECB,
//				CipherPrivateKeyCryptDemo.PKCS5_PADD);

//		CipherPrivateKeyCryptDemo.encryptAndDecrypt("Hello",
//				CipherPrivateKeyCryptDemo.RC2, CipherPrivateKeyCryptDemo.ECB,
//				CipherPrivateKeyCryptDemo.PKCS5_PADD);

//		CipherPrivateKeyCryptDemo.encryptAndDecrypt("Hello",
//				CipherPrivateKeyCryptDemo.RC4, CipherPrivateKeyCryptDemo.ECB,
//				CipherPrivateKeyCryptDemo.SSL3_PADD);

		CipherPrivateKeyCryptDemo.encryptAndDecrypt("Hello",
				CipherPrivateKeyCryptDemo.RC5, CipherPrivateKeyCryptDemo.ECB,
				CipherPrivateKeyCryptDemo.PKCS5_PADD);

	}
}

package com.srikanth.security;

import org.junit.Test;

public class MessageDigestDemoTest {

	@Test
	public void testCreateMessageDigest() {
//		MessageDigestDemo.createMessageDigest("Hello", MessageDigestDemo.MD2);
//		MessageDigestDemo.createMessageDigest("Hello", MessageDigestDemo.MD5);
//		MessageDigestDemo.createMessageDigest("Hello", MessageDigestDemo.SHA1);
//		MessageDigestDemo.createMessageDigest("Hello", MessageDigestDemo.SHA256);
//		MessageDigestDemo.createMessageDigest("Hello", MessageDigestDemo.SHA384);
		MessageDigestDemo.createMessageDigest("Hello", MessageDigestDemo.SHA512);
		
		MessageDigestDemo.createMessageAuthenticationCode("Hello", MessageDigestDemo.HMAC_SHA512);
	}

}

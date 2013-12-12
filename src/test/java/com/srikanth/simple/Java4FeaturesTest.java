package com.srikanth.simple;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Java4FeaturesTest {
	Logger logger = LoggerFactory.getLogger(Java4FeaturesTest.class);
	
	@Test(expected=ArithmeticException.class)
	public void testShowChainedExceptionsForException() {
		Java4Features obj = new Java4Features();
		try {
			obj.showChainedExceptions(230, 0);
		} catch(ArithmeticException ae) {
			logger.error("Exception: " + ae.getCause());
			throw ae;
		}
	}
	@Test
	public void testShowChainedExceptions() {
		Java4Features obj = new Java4Features();
		obj.showChainedExceptions(230, 4);
	}
	@Test
	public void testSetPreference() {
		//Run the program twice. The value of "ID1" should be still true as we delete it. 
		//The value of "id2" and "id2" should have changed after the first call.
		Java4Features obj = new Java4Features();
		obj.setPreference();
		obj.setPreference();
	}
}

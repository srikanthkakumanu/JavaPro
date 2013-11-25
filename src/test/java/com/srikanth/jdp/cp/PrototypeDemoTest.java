package com.srikanth.jdp.cp;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrototypeDemoTest {
	Logger logger = LoggerFactory.getLogger(PrototypeDemoTest.class);
	@Test
	public void testPrototypePattern() {
        ConcretePrototype obj1= new ConcretePrototype ();
        ConcretePrototype obj2;
		try {
			obj2 = (ConcretePrototype) obj1.clone();
			 obj2.getClass();
		} catch (CloneNotSupportedException e) {
			logger.info("Exception Occurred: " + e.getLocalizedMessage());
		}
	}
}

package com.srikanth.jdp.cp;

import org.junit.Test;

public class SingletonDemoTest {

	@Test
	public void testSingleton() {
        Singleton singleton = Singleton.getInstance();
        singleton.sayHello();
	}

}

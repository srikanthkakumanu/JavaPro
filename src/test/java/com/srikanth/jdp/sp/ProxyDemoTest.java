package com.srikanth.jdp.sp;

import org.junit.Test;

public class ProxyDemoTest {

	@Test
	public void testProxyPattern() {
        Proxy proxy = new Proxy();
        FastThing fastThing = new FastThing();
        fastThing.sayHello();
        proxy.sayHello();

	}

}

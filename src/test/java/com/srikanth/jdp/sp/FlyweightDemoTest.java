package com.srikanth.jdp.sp;

import org.junit.Test;

public class FlyweightDemoTest {

	@Test
	public void testFlyweightPattern() {
        FlyweightFactory flyweightFactory = FlyweightFactory.getInstance();
        for (int i = 0; i < 5; i++) {
            Flyweight flyweightAdder = flyweightFactory.getFlyweight("add");
            flyweightAdder.doMath(i, i);
            Flyweight flyweightMultiplier = flyweightFactory.getFlyweight("multiply");
            flyweightMultiplier.doMath(i, i);
        }
	}

}

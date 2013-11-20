package com.srikanth.jdp.sp;

import static org.junit.Assert.*;

import org.junit.Test;

public class FacadePatternDemoTest {

	@Test
	public void testCubeX() {
		ClassOne classOne = new ClassOne();
		assertFalse(classOne.doSomethingComplicated(3) == 0);
	}
	@Test
	public void testCubeXTimes2() {
		ClassOne classOne = new ClassOne();
		ClassTwo classTwo = new ClassTwo();
		assertFalse(classTwo.doAnotherThing(classOne,3) == 0);
	}
	@Test
	public void testXToSixthPowerTimes2() {
		ClassOne classOne = new ClassOne();
		ClassTwo classTwo = new ClassTwo();
		ClassThree classThree = new ClassThree();
		assertFalse(classThree.doMoreStuff(classOne, classTwo, 3) == 0);
	}

}

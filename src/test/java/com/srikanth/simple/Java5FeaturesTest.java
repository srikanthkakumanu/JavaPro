package com.srikanth.simple;

import org.junit.Test;

public class Java5FeaturesTest {

	@Test
	public void testShowEnumValues() {
		Java5Features.showEnumValues();
	}
	@Test
	public void testAutoboxingAndUnboxing() {
		Java5Features.autoBoxingAndUnboxingExample();
		BoxingOne.hitIt();
		BoxingTwo.hitIt();
		BoxingThree.hitIt();
	}
	@Test
	public void testCovariantReturnTypeExample() {
		Java5Features.covariantReturnTypeExample();
	}
}

package com.srikanth.jdp.sp;

 import org.junit.Test;

public class BridgePatternDemoTest {

	@Test
	public void testBridgePatternDemo() {
		Vehicle vehicle = new BigBus(new SmallEngine());
		vehicle.drive();
		vehicle.setEngine(new BigEngine());
		vehicle.drive();
		
		vehicle = new SmallCar(new SmallEngine());
		vehicle.drive();
		vehicle.setEngine(new BigEngine());
		vehicle.drive();
	}

}

package com.srikanth.jdp.sp;

import org.junit.Test;

public class AdapterDemoTest {

	@Test
	public void testAdapterPattern() {
       
        TemperatureInfo tempInfo = new TemperatureClassReporter();
        AdapterDemo.testTempInfo(tempInfo);
        tempInfo = new TemperatureObjectReporter();
        AdapterDemo.testTempInfo(tempInfo);

	}

}

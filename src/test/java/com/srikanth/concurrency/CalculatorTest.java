package com.srikanth.concurrency;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorTest {

	Logger logger = LoggerFactory.getLogger(CalculatorTest.class);
	
	@Test
	public void testCalculator() {
		for(int i = 1; i <=10; i++) {
			Calculator calculator = new Calculator(i);
			Thread thread = new Thread(calculator);
			thread.start();
		}
	}
	@Test
	public void testCalculatorWithStateAndPriority() {
		Thread[] threads = new Thread[10];
		Thread.State[] status = new Thread.State[10];
		
		for(int i = 0; i <10; i++) {
			threads[i] = new Thread(new Calculator(i));
			if(i%2 == 0) {
				threads[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
			threads[i].setName("Thread " + i);
		}
		for (int i=0; i<10; i++) {
			logger.info("Main : Status of Thread "+i+" : " + threads[i].getState());
			status[i]=threads[i].getState();
		}
		for (int i=0; i<10; i++) {
			threads[i].start();
		}
	}
}

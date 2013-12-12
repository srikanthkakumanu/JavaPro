package com.srikanth.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator implements Runnable {
	private int number;
	Logger logger = LoggerFactory.getLogger(Calculator.class);
	
	public Calculator(int number) {
		super();
		this.number = number;
	}
	@Override
	public void run() {
		for(int i=1; i <=10; i++) {
			System.out.printf("%s: %d * %d = %d\n", Thread.currentThread().getName(), number, i, i*number);
			logger.info("%s: %d * %d = %d\n" + Thread.currentThread().getName() + number + " " + i + " " + i*number );
		}
	}
}

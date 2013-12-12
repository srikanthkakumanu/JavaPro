package com.srikanth.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimeGenerator extends Thread {
	//static Logger logger = LoggerFactory.getLogger(PrimeGenerator.class);

	@Override
	public void run() {
		long number = 1L;
		while (true) {
			if (isPrime(number)) {
				System.out.println("Number %d is Prime.");
			}
			if (isInterrupted()) {
				System.out.println("The Prime Generator has been Interrupted.");
				return;
			}
			number++;
		}
	}
	private boolean isPrime(long number) {
		if (number <= 2) {
			return true;
		}
		for (long i = 2; i < number; i++) {
			if ((number % i) == 0) {
				return false;
			}
		}
		return true;
	}
	public static void main(String...strings) {
		
		try {
			Thread task = new PrimeGenerator();		
			task.start();
			Thread.sleep(50);
		} catch (InterruptedException ie) {
			System.out.println("Exception Occurred: " + ie.toString());
		}
	}
}

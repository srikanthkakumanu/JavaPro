package com.srikanth.concurrency;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo {

	/**
	 * One of the most critical aspects of a concurrent application is shared
	 * data. This has special importance in those objects that extend the Thread
	 * class or implement the Runnable interface. If you create an object of a
	 * class that implements the Runnable interface and then start various
	 * Thread objects using the same Runnable object, all the threads share the
	 * same attributes. This means that, if you change an attribute in a thread,
	 * all the threads will be affected by this change. Sometimes, you will be
	 * interested in having an attribute that won't be shared between all the
	 * threads that run the same object. The Java Concurrency API provides a
	 * clean mechanism called thread-local variables with a very good
	 * performance.
	 *  The Java Concurrency API also includes the InheritableThreadLocal
	 * class that provides inheritance of values for threads created from a
	 * thread. If a thread A has a value in a threadlocal variable and it
	 * creates another thread B, the thread B will have the same value as the
	 * thread A in the thread-local variable. You can override the childValue()
	 * method that is called to initialize the value of the child thread in the
	 * thread-local variable. It receives the value of the parent thread in the
	 * thread-local variable as a parameter.
	 */
	public static void main(String[] args) {
		SafeTask task = new SafeTask();
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(task);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
class SafeTask implements Runnable {

	private static ThreadLocal<Date> startDate = new ThreadLocal<Date>() {
		protected Date initialValue() {
			return new Date();
		}
	};
	@Override
	public void run() {
		System.out.printf("Starting Thread: %s : %s\n", Thread.currentThread().getId(), startDate.get());
		try {
			TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Thread Finished: %s : %s\n", Thread.currentThread().getId(), startDate.get());
	}
}
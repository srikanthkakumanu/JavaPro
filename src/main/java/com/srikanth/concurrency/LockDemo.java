package com.srikanth.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LockDemo {

	/**
	 * Java provides another mechanism for the synchronization of blocks of
	 * code. It's a more powerful and flexible mechanism than the synchronized
	 * keyword. It's based on the Lock interface and classes that implement it
	 * (as ReentrantLock). This mechanism presents some advantages, which are as
	 * follows: 
	 * 1. It allows the structuring of synchronized blocks in a more
	 * flexible way. With the synchronized keyword, you have to get and free the
	 * control over a synchronized block of code in a structured way. The Lock
	 * interfaces allow you to get more complex structures to implement your
	 * critical section. 
	 * 2. The Lock interfaces provide additional functionalities over the synchronized 
	 * keyword. One of the new functionalities is implemented by the tryLock() method. 
	 * This method tries to get the control of the lock and if it can't, because it's used by
	 * other thread, it returns the lock. With the synchronized keyword, when a
	 * thread (A) tries to execute a synchronized block of code, if there is
	 * another thread (B) executing it, the thread (A) is suspended until the
	 * thread (B) finishes the execution of the synchronized block. With locks,
	 * you can execute the tryLock() method. This method returns a Boolean value
	 * indicating if there is another thread running the code protected by this
	 * lock. 
	 * 3. The Lock interfaces allow a separation of read and write operations having 
	 * multiple readers and only one modifier. 
	 * 4. The Lock interfaces offer better performance than the synchronized keyword
	 */
	public static void main(String[] args) {
		PrintQueue printQueue = new PrintQueue();
		Thread printThread[] = new Thread[10];
		for(int i = 0; i < 10; i++) {
			printThread[i] = new Thread(new Job(printQueue), "Thread "+ i);
		}
		for(int i = 0; i < 10; i++) {
			printThread[i].start();
		}
	}
}
class PrintQueue {
	Logger logger = LoggerFactory.getLogger(PrintQueue.class);
	private final Lock queueLock = new ReentrantLock();
	
	public void printJob(Object document) {
		//get the control of the Lock object calling the lock() method
		queueLock.lock();
		/*
		 * The Lock interface (and the ReentrantLock class) includes another
		 * method to get the control of the lock. It's the tryLock() method. The
		 * biggest difference with the lock() method is that this method, if the
		 * thread that uses it can't get the control of the Lock interface,
		 * returns immediately and doesn't put the thread to sleep. This method
		 * returns a boolean value, true if the thread gets the control of the
		 * lock, and false if not.
		 */
		try {
			Long duration = (long) (Math.random() * 10000);
			logger.info(Thread.currentThread().getName() + ": PrintQueue : Printing a Job during " + (duration/1000) + " seconds");
			Thread.sleep(duration);
		} catch(InterruptedException ie) {
			logger.error(ie.toString(), ie);
		}
		finally {
			queueLock.unlock();
		}
	}
}
class Job implements Runnable {
	Logger logger = LoggerFactory.getLogger(Job.class);
	private PrintQueue printQueue;
	
	public Job(PrintQueue printQueue) {
		this.printQueue = printQueue;
	}
	@Override
	public void run() {
		logger.info(String.format("%s: Going to print a document\n", Thread.currentThread().getName()));
		printQueue.printJob(new Object());
		logger.info(String.format("%s: The document has been printed\n",	Thread.currentThread().getName()));
	}
}

package com.srikanth.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LockFairnessDemo {
	static Logger logger = LoggerFactory.getLogger(LockFairnessDemo.class);
	/**
	 * The constructor of the ReentrantLock and ReentrantReadWriteLock classes
	 * admits a boolean parameter named fair that allows you to control the
	 * behavior of both classes. The false value is the default value and it's
	 * called the non-fair mode. In this mode, when there are some threads
	 * waiting for a lock (ReentrantLock or ReentrantReadWriteLock) and the lock
	 * has to select one of them to get the access to the critical section, it
	 * selects one without any criteria. The true value is called the fair mode.
	 * In this mode, when there are some threads waiting for a lock
	 * (ReentrantLock or ReentrantReadWriteLock) and the lock has to select one
	 * to get access to a critical section, it selects the thread that has been
	 * waiting for the most time. Take into account that the behavior explained
	 * previously is only used with the lock() and unlock() methods. As the
	 * tryLock() method doesn't put the thread to sleep if the Lock interface is
	 * used, the fair attribute doesn't affect its functionality.
	 */
	public static void main(String[] args) {
		PrintQueue2 printQueue = new PrintQueue2();
		Thread printThread[] = new Thread[10];
		for(int i = 0; i < 10; i++) {
			printThread[i] = new Thread(new Job2(printQueue), "Thread "+ i);
		}
		for(int i = 0; i < 10; i++) {
			printThread[i].start();
			try {
				Thread.sleep(100);
			} catch(InterruptedException ie) {
				logger.error(ie.toString(), ie);
			}
		}
	}
}
class PrintQueue2 {
	Logger logger = LoggerFactory.getLogger(PrintQueue2.class);
	// enabling lock fairness
	private final Lock queueLock = new ReentrantLock(true);
	// disabling lock fairness or default
	// private final Lock queueLock = new ReentrantLock(false);
	
	public void printJob(Object document) {
		queueLock.lock();
		try {
			Long duration = (long) (Math.random() * 10000);
			logger.info(Thread.currentThread().getName() + ": PrintQueue2 : Printing a Job during " + (duration/1000) + " seconds");
			Thread.sleep(duration);
		} catch(InterruptedException ie) {
			logger.error(ie.toString(), ie);
		}
		finally {
			queueLock.unlock();
		}
		
		queueLock.lock();
		try {
			Long duration = (long) (Math.random() * 10000);
			logger.info(Thread.currentThread().getName() + ": PrintQueue2 : Printing a Job during " + (duration/1000) + " seconds");
			Thread.sleep(duration);
		} catch(InterruptedException ie) {
			logger.error(ie.toString(), ie);
		}
		finally {
			queueLock.unlock();
		}

	}
}
class Job2 implements Runnable {
	Logger logger = LoggerFactory.getLogger(Job2.class);
	private PrintQueue2 printQueue;
	
	public Job2(PrintQueue2 printQueue) {
		this.printQueue = printQueue;
	}
	@Override
	public void run() {
		logger.info(String.format("%s: Going to print a document\n", Thread.currentThread().getName()));
		printQueue.printJob(new Object());
		logger.info(String.format("%s: The document has been printed\n",	Thread.currentThread().getName()));
	}
}

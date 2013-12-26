package com.srikanth.concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SemaphoreDemo {

	/**
	 * A semaphore is a counter that controls the access to one or more shared
	 * resources. This mechanism is one of the basic tools of concurrent
	 * programming and is provided by most of the programming languages.
	 * Semaphores are a generic synchronization mechanism that you can use to
	 * protect any critical section in any problem. When a thread wants to
	 * access one of these shared resources, first, it must acquire the
	 * semaphore. If the internal counter of the semaphore is greater than 0,
	 * the semaphore decrements the counter and allows access to the shared
	 * resource. A counter bigger than 0 means there are free resources that can
	 * be used, so the thread can access and use one of them. Otherwise, if the
	 * counter of the semaphore is 0, the semaphore puts the thread to sleep
	 * until the counter is greater than 0. A value of 0 in the counter means
	 * all the shared resources are used by other threads, so the thread that
	 * wants to use one of them must wait until one is free. When the thread has
	 * finished the use of the shared resource, it must release the semaphore so
	 * that the other thread can access the shared resource. That operation
	 * increases the internal counter of the semaphore. Semaphore class to
	 * implement special kinds of semaphores called binary semaphores. These
	 * kinds of semaphores protect the access to a unique shared resource, so
	 * the internal counter of the semaphore can only take the values 1 or
	 * 0.This print queue will be protected by a binary semaphore, so only one
	 * thread can print at a time.
	 */
	public static void main(String[] args) {
		SPrintQueue printQueue = new SPrintQueue();
		Thread threads[] = new Thread[10];
		for(int i = 0; i < 10; i++) {
			threads[i] = new Thread(new SJob(printQueue), "Thread" + i);
		}
		for(int i = 0; i < 10; i++) {
			threads[i].start();
		}
	}
}
class SPrintQueue {
	Logger logger = LoggerFactory.getLogger(SPrintQueue.class);
	private final Semaphore semaphore;
	// controlling concurrent access to multiple copies of a resource
	private boolean freePrinters[];
	private Lock lockPrinters;
	
	public SPrintQueue() {
		semaphore = new Semaphore(3);
		// controlling concurrent access to multiple copies of a resource
		freePrinters = new boolean[3];
		for(int i = 0; i < 3; i++) {
			freePrinters[i] = true;
		}
		lockPrinters = new ReentrantLock();
	}
	public void printJob(Object document) {
		try {
			semaphore.acquire();
			int assignedPrinter = getPrinter(); // controlling concurrent access to multiple copies of a resource
			long duration = (long) (Math.random()*10);
			logger.info(String.format("%s: SPrintQueue: Printing a Job in Printer %d during %d seconds\n",Thread.currentThread().getName(), assignedPrinter, duration));
			Thread.sleep(duration);
			// controlling concurrent access to multiple copies of a resource
			freePrinters[assignedPrinter] = true;
		} catch(InterruptedException ie) {
			logger.error(ie.toString(), ie);
		} 
		finally {
			semaphore.release();
		}
	}
	private int getPrinter() {
		int returnVal = -1;
		try {
			lockPrinters.lock();
			for(int i = 0; i < freePrinters.length; i++) {
				if(freePrinters[i]) {
					returnVal = i;
					freePrinters[i] = false;
					break;
				}
			}
		} catch(Exception e) {
			logger.error(e.toString(), e);
		} finally {
			lockPrinters.unlock();
		}
		return returnVal;
	}
}
class SJob implements Runnable {
	Logger logger = LoggerFactory.getLogger(SJob.class);
	private SPrintQueue printQueue;
	
	public SJob(SPrintQueue printQueue) {
		this.printQueue = printQueue;
	}
	@Override
	public void run() {
		logger.info(String.format("%s: Going to print a job\n",Thread.currentThread().getName()));
		printQueue.printJob(new Object());
		logger.info(String.format("%s: The document has been printed\n",Thread.currentThread().getName()));
	}
}
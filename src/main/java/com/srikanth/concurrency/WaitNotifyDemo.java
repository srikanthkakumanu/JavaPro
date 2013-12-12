package com.srikanth.concurrency;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaitNotifyDemo {

	/**
	 * Java provides the wait(), notify(), and notifyAll() methods implemented
	 * in the Object class. A thread can call the wait() method inside a
	 * synchronized block of code. If it calls the wait() method outside a
	 * synchronized block of code, the JVM throws an
	 * IllegalMonitorStateException exception. When the thread calls the wait()
	 * method, the JVM puts the thread to sleep and releases the object that
	 * controls the synchronized block of code that it's executing and allows
	 * the other threads to execute other blocks of synchronized code protected
	 * by that object. To wake up the thread, you must call the notify() or
	 * notifyAll() method inside a block of code protected by the same object.
	 */
	public static void main(String[] args) {
		EventStorage storage = new EventStorage();
		Producer producer = new Producer(storage);
		Thread producerThread = new Thread(producer);
		Consumer consumer = new Consumer(storage);
		Thread consumerThread = new Thread(consumer);
		producerThread.start();
		consumerThread.start();
	}
}
class EventStorage {
	Logger logger = LoggerFactory.getLogger(EventStorage.class);
	private int maxSize;
	private List<Date> storage;
	
	public EventStorage() {
		maxSize = 10;
		storage = new LinkedList<>();
	}
	public synchronized void set() {
		while(storage.size() == maxSize) {
			try {
				wait();
			} catch(InterruptedException ie) {
				logger.error(ie.toString(), ie);
			}
		}
		((LinkedList<Date>) storage).offer(new Date());
		logger.info(String.format("Set: %d", storage.size()));
		notifyAll();
	}
	public synchronized void get() {
		while(storage.size() == 0) {
			try {
				wait();
			} catch(InterruptedException ie) {
				logger.error(ie.toString(), ie);
			}	
		}
		logger.info(String.format("Get: %d: %s", storage.size(), ((LinkedList<Date>) storage).poll()));
		notifyAll();
	}
}
class Producer implements Runnable {
	private EventStorage storage;
	public Producer(EventStorage storage) {
		this.storage = storage;
	}
	@Override
	public void run() {
		for(int i = 0; i < 100; i++) {
			storage.set();
		}
	}
}
class Consumer implements Runnable {
	private EventStorage storage;
	public Consumer(EventStorage storage) {
		this.storage = storage;
	}
	@Override
	public void run() {
		for(int i = 0; i < 100; i++) {
			storage.get();
		}
	}
}
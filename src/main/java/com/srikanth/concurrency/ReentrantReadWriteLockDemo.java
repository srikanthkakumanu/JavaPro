package com.srikanth.concurrency;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReentrantReadWriteLockDemo {

	/**
	 * One of the most significant improvements offered by locks is the
	 * ReadWriteLock interface and the ReentrantReadWriteLock class, the unique
	 * one that implements it. This class has two locks, one for read operations
	 * and one for write operations. There can be more than one thread using
	 * read operations simultaneously, but only one thread can be using write
	 * operations. When a thread is doing a write operation, there can't be any
	 * thread doing read operations. When you get the read lock of a Lock
	 * interface, you can't modify the value of the variable. Otherwise, you
	 * probably will have inconsistency data errors.
	 */
	public static void main(String[] args) {
		PricesInfo pricesInfo = new PricesInfo();
		Reader readers[] = new Reader[5];
		Thread threadsReader[]=new Thread[5];
		
		for (int i = 0; i < 5; i++) {
			readers[i] = new Reader(pricesInfo);
			threadsReader[i]=new Thread(readers[i]);
		}
		Writer writer=new Writer(pricesInfo);
		Thread threadWriter=new Thread(writer);
		
		for (int i = 0; i < 5; i++) {
			threadsReader[i].start();
		}
		threadWriter.start();
	}
}
class PricesInfo {
	private double price1;
	private double price2;
	private ReadWriteLock lock;
	
	public PricesInfo() {
		price1 = 1.0;
		price2 = 2.0;
		lock = new ReentrantReadWriteLock();
	}
	public double getPrice1() {
		lock.readLock().lock();
		double value = price1;
		lock.readLock().unlock();
		return value;
	}
	public double getPrice2() {
		lock.readLock().lock();
		double value = price2;
		lock.readLock().unlock();
		return value;
	}
	public void setPrices(double price1, double price2) {
		lock.writeLock().lock();
		this.price1 = price1;
		this.price2 = price2;
		lock.writeLock().unlock();
	}
}
class Reader implements Runnable {
	Logger logger = LoggerFactory.getLogger(Reader.class);
	private PricesInfo pricesInfo;
	
	public Reader(PricesInfo pricesInfo) {
		this.pricesInfo = pricesInfo;
	}
	@Override
	public void run() {
		for (int i=0; i<10; i++) {
			logger.info(String.format("%s: Price 1: %f\n", Thread.currentThread().getName(),pricesInfo.getPrice1()));
			logger.info(String.format("%s: Price 2: %f\n", Thread.currentThread().getName(),pricesInfo.getPrice2()));
		}
	}
}
class Writer implements Runnable {
	Logger logger = LoggerFactory.getLogger(Writer.class);
	private PricesInfo pricesInfo;
	
	public Writer(PricesInfo pricesInfo) {
		this.pricesInfo = pricesInfo;
	}
	@Override
	public void run() {
		for (int i=0; i<3; i++) {
			logger.info(String.format("Writer: Attempt to modify the prices.\n"));
			pricesInfo.setPrices(Math.random()*10, Math.random()*8);
			logger.info(String.format("Writer: Prices have been modified.\n"));
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				logger.error(e.toString(), e);
			}
		}	
	}
}
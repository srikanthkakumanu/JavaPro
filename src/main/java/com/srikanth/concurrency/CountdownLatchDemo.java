package com.srikanth.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountdownLatchDemo {

	/**
	 * ---Waiting for multiple concurrent events---
	 * The Java concurrency API provides a class that allows one or more threads
	 * to wait until a set of operations are made. It's the CountDownLatch
	 * class. This class is initialized with an integer number, which is the
	 * number of operations the threads are going to wait for. When a thread
	 * wants to wait for the execution of these operations, it uses the await()
	 * method. This method puts the thread to sleep until the operations are
	 * completed. When one of these operations finishes, it uses the countDown()
	 * method to decrement the internal counter of the CountDownLatch class.
	 * When the counter arrives to 0, the class wakes up all the threads that
	 * were sleeping in the await() method.
	 */
	public static void main(String[] args) {
		Videoconference conference = new Videoconference(10);
		Thread threadConference = new Thread(conference);
		threadConference.start();
		for (int i = 0; i < 10; i++) {
			Participant p = new Participant(conference, "Participant "+i);
			Thread t = new Thread(p);
			t.start();
		}
	}
}
class Videoconference implements Runnable {
	Logger logger = LoggerFactory.getLogger(Videoconference.class);
	private final CountDownLatch controller;
	
	public Videoconference(int number) {
		controller = new CountDownLatch(number);
	}
	@Override
	public void run() {
		logger.info(String.format("VideoConference: Initialization: %d participants.\n",controller.getCount()));
		try {
			controller.await();
			logger.info("VideoConference: All the participants have come\n");
			logger.info("VideoConference: Let's start...\n");
		} catch(InterruptedException ie) {
			logger.error(ie.toString(), ie);
		}
	}
	public void arrive(String name) {
		logger.info(String.format("%s has arrived.", name));
		controller.countDown();
		logger.info(String.format("VideoConference: Waiting for %d participants.\n",controller.getCount()));
	}
}
class Participant implements Runnable {
	Logger logger = LoggerFactory.getLogger(Participant.class);
	private Videoconference conference;
	private String name;
	
	public Participant(Videoconference conference, String name) {
		this.conference = conference;
		this.name = name;
	}
	@Override
	public void run() {
		long duration=(long)(Math.random()*10);
		
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException ie) {
			logger.error(ie.toString(), ie);
		}
		conference.arrive(name);
	}
}
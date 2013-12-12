package com.srikanth.concurrency;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JoinDemo {
	/**
	 * Threads scheduling is controlled by thread scheduler.So, you cannot
	 * guarantee the order of execution of threads under normal circumstances.
	 * However if you use join() ,it makes sure that as soon as a thread calls
	 * join,the current thread(yes,currently running thread) will not execute
	 * unless the thread you have called join is finished.
	 */
	public static void main(String[] args) {
		DataSourcesLoader dsLoader = new DataSourcesLoader();
		NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
		Thread dsThread = new Thread(dsLoader, "DataSourceThread");
		Thread ncThread = new Thread(ncLoader, "NetworkConnectionThread");
		dsThread.start();
		ncThread.start();
		try {
			dsThread.join();
			ncThread.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		System.out.printf("Main: Configuration has been loaded:	%s\n",
				new Date());
	}
}

class DataSourcesLoader implements Runnable {
	@Override
	public void run() {
		System.out.printf("Beginning data sources loading: %s\n", new Date());
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out
				.printf("Data sources loading has finished: %s\n", new Date());
	}
}

class NetworkConnectionsLoader implements Runnable {
	@Override
	public void run() {
		System.out.printf("Beginning network connections loading: %s\n",
				new Date());
		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Network Connections loading has finished: %s\n",
				new Date());
	}
}
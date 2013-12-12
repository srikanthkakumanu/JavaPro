package com.srikanth.concurrency;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.ArrayDeque;

public class DaemonThreadDemo {

	public static void main(String[] args) {
		Deque<Event> deque = new ArrayDeque<>();
		WriterTask writer = new WriterTask(deque);
		for(int i=0; i < 3; i++) {
			Thread thread = new Thread(writer);
			thread.start();
		}
		CleanerTask cleaner = new CleanerTask(deque);
		cleaner.start();
	}
}

class Event {
	Date date;
	String event;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}

class WriterTask implements Runnable {
	private Deque<Event> deque;

	public WriterTask(Deque<Event> deque) {
		this.deque = deque;
	}

	@Override
	public void run() {
		for (int i = 1; i < 100; i++) {
			Event event = new Event();
			event.setDate(new Date());
			event.setEvent(String.format(
					"The thread %s has generated an event", Thread
							.currentThread().getId()));
			deque.addFirst(event);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}

class CleanerTask extends Thread {
	private Deque<Event> deque;

	public CleanerTask(Deque<Event> deque) {
		this.deque = deque;
		setDaemon(true);
	}

	@Override
	public void run() {
		while (true) {
			Date date = new Date();
			clean(date);
		}
	}

	private void clean(Date date) {
		long difference;
		boolean delete;

		if (deque.size() == 0) {
			return;
		}
		
		delete = false;
		
		do {
			Event e = deque.getLast();
			difference = date.getTime() - e.getDate().getTime();
			if (difference > 10000) {
				System.out.printf("Cleaner: %s\n", e.getEvent());
				deque.removeLast();
				delete = true;
			}
		} while (difference > 10000);
		if (delete) {
			System.out.printf("Cleaner: Size of the queue: %d\n", deque.size());
		}
	}
}
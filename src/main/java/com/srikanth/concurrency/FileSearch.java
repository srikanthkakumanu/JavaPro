package com.srikanth.concurrency;

import java.util.concurrent.TimeUnit;
import java.io.File;

public class FileSearch implements Runnable {
	private String initPath;
	private String fileName;
	
	public FileSearch(String initPath, String fileName) {
		this.initPath = initPath;
		this.fileName = fileName;
	}
	@Override
	public void run() {
		File file = new File(initPath);
		if(file.isDirectory()) {
			try {
				processDirectory(file);
			} catch(InterruptedException ie) {
				System.out.printf("%s: The search has been	interrupted",Thread.currentThread().getName());
			}
		}
	}
	private void processDirectory(File file) throws InterruptedException {
		File list[] = file.listFiles();
		if(list != null) {
			for(int i = 0; i < list.length; i++) {
				if(list[i].isDirectory()) {
					processDirectory(list[i]);
				} else {
					processFile(list[i]);
				}
			}
		} 
		if(Thread.interrupted()) {
			throw new InterruptedException();
		}
	}
	private void processFile(File file) throws InterruptedException {
		
		if (file.getName().equals(fileName)) {
			System.out.printf("%s : %s\n",Thread.currentThread().getName() ,file.getAbsolutePath());
		}
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileSearch searcher=new FileSearch("C:\\","autoexec.bat");
		Thread thread=new Thread(searcher);
		thread.start();
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();
	}

}

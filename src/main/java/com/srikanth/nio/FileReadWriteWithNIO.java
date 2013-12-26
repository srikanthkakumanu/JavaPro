package com.srikanth.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileReadWriteWithNIO {
	static Logger logger = LoggerFactory.getLogger(FileReadWriteWithNIO.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		basicExample();
		bufferSlicing();
		fileLock();
	}

	@SuppressWarnings("resource")
	private static void basicExample() {
		try {
			FileInputStream fInputStream = new FileInputStream("src/main/resources/Read.txt");
			FileChannel fInChannel = fInputStream.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);

			FileOutputStream fOutputStream = new FileOutputStream("src/main/resources/Write.txt");
			FileChannel fOutChannel = fOutputStream.getChannel();
			while(true) {
				buffer.clear();
				int r = fInChannel.read(buffer);
				if(r == -1) {
					break;
				}
				buffer.flip();
				fOutChannel.write(buffer);
			}
		} catch (IOException e) {
			logger.error(e.toString(), e);
		}
	}
	private static void bufferSlicing() {
		ByteBuffer buffer = ByteBuffer.allocate(10);
		
		for(int i = 0; i < buffer.capacity(); i++) {
			buffer.put((byte)i);
		}
		buffer.position(3);
		buffer.limit(7);
		ByteBuffer slice = buffer.slice();
		
		for(int i = 0; i < slice.capacity(); i++) {
			byte b = slice.get(i);
			b *= 11;
			slice.put(i, b);
		}
		
		//observe the contents of original buffer
		buffer.position(0);
		buffer.limit(buffer.capacity());
		while(buffer.remaining() > 0) {
			logger.info(Byte.toString(buffer.get()) + " ");
		}
	}	
	@SuppressWarnings("resource")
	private static void fileLock() {
		try {
			RandomAccessFile accessFile = new RandomAccessFile("src/main/resources/filelock.txt", "rw");
			FileChannel fInChannel = accessFile.getChannel();
			FileLock lock = fInChannel.lock(0, accessFile.length()-1, false);
			// After you have the lock, you can carry out any sensitive operations that you need to, and then release the lock:
			lock.release();
		} catch (IOException e) {
			logger.error(e.toString(), e);
		}
	}
}

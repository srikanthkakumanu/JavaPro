package com.srikanth.simple;
/**
 * 
 * @author Srikanth
 * @
 */
public class JavaDocDemo {
	/**
	 * 
	 * @param b
	 * @return b
	 */
	public byte returnVal(byte b) {
		return b;
	}
	
	public static void main(String[] args) {
		JavaDocDemo d = new JavaDocDemo();
		d.returnVal((byte)39);
		System.out.print("Hello");
		System.out.print("\r");
		System.out.print("World");
	}
}

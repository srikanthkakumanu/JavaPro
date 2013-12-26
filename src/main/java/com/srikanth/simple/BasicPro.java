package com.srikanth.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 10/24/13
 * Time: 8:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class BasicPro {
	private static Logger logger = LoggerFactory.getLogger(BasicPro.class);
	
	/**
	 * 
	 */
	private BasicPro() {
		
	}
	/**
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
    	
    	String portalUser = "kdoolmit";
		String fileName = "kdoolmit_32424324_232.pdf";
		String fileN = fileName.substring(0, fileName.indexOf("_"));
		System.out.println(fileName.indexOf("_"));
		System.out.println(fileN);
		if(portalUser.equalsIgnoreCase(fileName.substring(0, fileName.indexOf("_")))) {}
        //logger.info("Present Working Directory: " + System.getProperty("user.dir"));
    }
}
class SimpleClass {
	public void sayHello() {
		System.out.println("sayHello");
	}
}
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
	//private static Logger logger = LoggerFactory.getLogger(BasicPro.class);
	
    public static void main(String[] args) {
    	findHashCode();
    }
    private static void findHashCode() {
    	String str = "aA";
    	System.out.println(str.hashCode());
    	// String hashcode algorithm : s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]

    }
}

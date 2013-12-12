package com.srikanth.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://www.javatpoint.com/enum-in-java
 * @author Srikanth
 *
 */
public class Java5Features {
	static Logger logger = LoggerFactory.getLogger(Java5Features.class);
	
	public enum Season {WINTER, SPRING, SUMMER, FALL}
	/**
	 * Enum improves type safety. Enum can be traversed. Enum can be used easily in switch. 
	 * Enum can have fields, constructors, methods. Enum constants are static and final implicitly.
	 * Enum can be defined within or outside of the class because it is similar to a class.
	 * Enum cannot be instantiated with new keyword because it contains only private constructors only.
	 * Enum can have abstract methods. 
	 * Enum may implements many interfaces but cannot extend any class because it internally extends Enum class.
	 */
	public static void showEnumValues() {
		for(Season s: Season.values()) {
			logger.info(s.toString());
		}
	}
	/**
	 * The automatic conversion of primitive data types into its equivalent wrapper type is Auto boxing 
	 * and opposite operation is unboxing. We do not need to convert explicitly.
	 * Advantage is less coding.
	 * The automatic conversion of wrapper class type into corresponding primitive type, is known as Unboxing.
	 * In method overloading, boxing and unboxing can be performed. There are some rules for method overloading with boxing:
	 * Widening beats boxing
	 * Widening beats varargs
	 * Boxing beats varargs 
	 */
	public static void autoBoxingAndUnboxingExample() {
		int a = 59;
		// Boxing
		Integer integer = new Integer(a);
		// Boxing
		Integer integer2 = 55;
		logger.info(integer + " " + integer2);
		
		//Unboxing
		Integer i = new Integer(20);
		int k = i;
		logger.info("" + k);
		
		// Autoboxing with comparison operator
		if(i < 100) {
			logger.info("" + i);
		}
	}
	public static void covariantReturnTypeExample() {
		logger.info(new Two().get().returnMsg());
	}
}
// Auto boxing where widening beats boxing
class BoxingOne {
	static Logger logger = LoggerFactory.getLogger(BoxingOne.class);
	static void m(int i) {
		logger.info("int");
	}
	static void m(Integer i) {
		logger.info("Integer");
	}
	public static void hitIt() {
	   short s=30;
	   m(s);
	 } 
}
// Auto boxing where widening beats varargs
class BoxingTwo {
	static Logger logger = LoggerFactory.getLogger(BoxingTwo.class);
	static void m(int i, int i2) {
		logger.info("int int");
	}
	static void m(Integer... i) {
		logger.info("Integer...");
	}
	public static void hitIt() {
		short s1=30,s2=40;
	   m(s1,s2);
	 } 
}
// Auto boxing where boxing beats variable argument
class BoxingThree {
	static Logger logger = LoggerFactory.getLogger(BoxingThree.class);
	static void m(Integer i) {
		logger.info("Integer");
	}
	static void m(Integer... i) {
		logger.info("Integer...");
	}
	  public static void hitIt() {
	   int a=30;
	   m(a);
	 } 
}
// Covariant return type
/**
 * The covariant return type specifies that the return type may vary in the same direction as the subclass. 
 * Before Java5, it was not possible to override any method by changing the return type. But now, since Java5,
 * it is possible to override method by changing the return type if subclass overrides any method whose return 
 * type is Non-Primitive but it changes its return type to subclass type. 
 */
class One {
	One get() {
		return this;
	}
}
class Two extends One {
	Two get() {
		return this;
	}
	public String returnMsg() {
		return "welcome to covariant return type";
	}
}
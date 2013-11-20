package com.srikanth.jdp.sp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FacadePatternDemo {
	private static Logger logger = LoggerFactory.getLogger(FacadePatternDemo.class);
/**
 * In the facade pattern, a facade class is used to provide a single interface to set of classes. 
 * The facade simplifies a clients interaction with a complex system by localizing the interactions 
 * into a single interface. As a result, the client can interact with a single object rather than being 
 * required to interact directly in complicated ways with the objects that make up the subsystem.
 */
	private FacadePatternDemo() {}
	
	public static void main(String...strings) {
		Facade facade = new Facade();
		int x = 3;
		logger.info("Cube of " + x + ":" + facade.cubeX(3));
		logger.info("Cube of " + x + " times 2:" + facade.cubeXTimes2(3));
		logger.info(x + " to sixth power times 2:" + facade.xToSixthPowerTimes2(3));
	}
}
class ClassOne {
	public int doSomethingComplicated(int x) {
		return x * x* x;
	}
}
class ClassTwo {
	public int doAnotherThing(ClassOne classOne, int x) {
		return 2 * classOne.doSomethingComplicated(x);
	}
}
class ClassThree {
	public int doMoreStuff(ClassOne classOne, ClassTwo classTwo, int x) {
		return classOne.doSomethingComplicated(x) * classTwo.doAnotherThing(classOne, x);
	}
}
class Facade {
	public int cubeX(int x) {
		ClassOne classOne = new ClassOne();
		return classOne.doSomethingComplicated(x);
	}
	public int cubeXTimes2(int x) {
		ClassOne classOne = new ClassOne();
		ClassTwo classTwo = new ClassTwo();
		return classTwo.doAnotherThing(classOne, x);
	}
	public int xToSixthPowerTimes2(int x) {
		ClassOne classOne = new ClassOne();
		ClassTwo classTwo = new ClassTwo();
		ClassThree classThree = new ClassThree();
		return classThree.doMoreStuff(classOne, classTwo, x);
	}
}

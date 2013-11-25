package com.srikanth.jdp.sp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BridgePatternDemo {

	/**
	 * In the bridge pattern, we separate an abstraction and its implementation and develop separate inheritance structures 
	 * for both the abstraction and the implementor. The abstraction is an interface or abstract class, and the implementor is 
	 * likewise an interface or abstract class. The abstraction contains a reference to the implementor. Children of the abstraction 
	 * are referred to as refined abstractions, and children of the implementor are concrete implementors. Since we can change 
	 * the reference to the implementor in the abstraction, we are able to change the abstraction's implementor at run-time. 
	 * Changes to the implementor do not affect client code.The bridge pattern can be demonstrated with an example. 
	 * Suppose we have a Vehicle class. We can extract out the implementation of the engine into an Engine class. 
	 * We can reference this Engine implementor in our Vehicle via an Engine field. We'll declare Vehicle to be an abstract class. 
	 * Subclasses of Vehicle need to implement the drive() method. Notice that the Engine reference can be changed via the 
	 * setEngine() method.
	 */
	private BridgePatternDemo() {
		
	}
	public static void main(String...strings) {
		Vehicle vehicle = new BigBus(new SmallEngine());
		vehicle.drive();
		vehicle.setEngine(new BigEngine());
		vehicle.drive();
		
		vehicle = new SmallCar(new SmallEngine());
		vehicle.drive();
		vehicle.setEngine(new BigEngine());
		vehicle.drive();
	}
}
abstract class Vehicle {
	Engine engine;
	int weightInKilos;
	Logger logger = LoggerFactory.getLogger(Vehicle.class);
	abstract void drive();
	
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	public void reportOnSpeed(int horsePower) {
		int ratio = weightInKilos / horsePower;
		if(ratio < 3) {
			logger.info("The Vehicle is going at a fast speed");
		} else if(ratio >= 3 && ratio < 8) {
			logger.info("The Vehicle is going at a average speed");
		} else {
			logger.info("The Vehicle is going at a slow speed");
		}
	}
}
class BigBus extends Vehicle {
	Logger logger = LoggerFactory.getLogger(BigBus.class);
	public BigBus(Engine engine) {
		this.weightInKilos = 3000;
		this.engine = engine;
	}
	@Override
	public void drive() {
		logger.info("The Big Bus is Driving");
		int horsePower = engine.go();
		reportOnSpeed(horsePower);
	}
}
class SmallCar extends Vehicle {
	Logger logger = LoggerFactory.getLogger(SmallCar.class);
	
	public SmallCar(Engine engine) {
		this.weightInKilos = 600;
		this.engine = engine;
	}
	@Override
	public void drive() {
		logger.info("The small car is driving");
		int horsepower = engine.go();
		reportOnSpeed(horsepower);
	}
}
interface Engine {
	int go();
}
class BigEngine implements Engine {
	Logger logger = LoggerFactory.getLogger(BigEngine.class);
	int horsePower;
	public BigEngine() {
		horsePower = 350;
	}
	@Override
	public int go() {
		logger.info("The Big Engine is Running");
		return horsePower;
	}
}
class SmallEngine implements Engine {
	Logger logger = LoggerFactory.getLogger(SmallEngine.class);
	int horsepower;

	public SmallEngine() {
		horsepower = 100;
	}
	@Override
	public int go() {
		logger.info("The small engine is running");
		return horsepower;
	}
}


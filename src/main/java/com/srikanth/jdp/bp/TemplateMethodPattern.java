
package com.srikanth.jdp.bp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A behavioral class pattern uses inheritance for distribution of behavior. In the template method pattern, a method 
 * (the 'template method') defines the steps of an algorithm. The implementation of these steps (ie, methods) can be 
 * deferred to subclasses. Thus, a particular algorithm is defined in the template method, but the exact steps of this 
 * algorithm can be defined in subclasses. The template method is implemented in an abstract class. The steps (methods) 
 * of the algorithm are declared in the abstract class, and the methods whose implementations are to be delegated to subclasses 
 * are declared abstract.
 * @author Srikanth
 *
 */
abstract class Meal {
	Logger logger = LoggerFactory.getLogger(Meal.class);
	public final void doMeal() {
		prepareIngredients();
		cook();
		eat();
		cleanUp();
	}
	public abstract void prepareIngredients();
	public abstract void cook();
	public void eat() {
		logger.info("Mmm.. Thats Good..");
	}
	public abstract void cleanUp();
}
class HamburgerMeal extends Meal {
	Logger logger = LoggerFactory.getLogger(HamburgerMeal.class);
	@Override
	public void prepareIngredients() {
		logger.info("Getting burgers, buns, and french fries");
	}
	@Override
	public void cook() {
		logger.info("Cooking burgers on grill and fries in oven");
	}
	@Override
	public void cleanUp() {
		logger.info("Throwing away paper plates");
	}
}
class TacoMeal extends Meal {
	Logger logger = LoggerFactory.getLogger(TacoMeal.class);

	@Override
	public void prepareIngredients() {
		logger.info("Getting ground beef and shells");
	}
	@Override
	public void cook() {
		logger.info("Cooking ground beef in pan");
	}
	@Override
	public void eat() {
		logger.info("The tacos are tasty");
	}
	@Override
	public void cleanUp() {
		logger.info("Doing the dishes");
	}
}

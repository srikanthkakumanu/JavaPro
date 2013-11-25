package com.srikanth.jdp.sp;

import org.junit.Test;

public class DecoratorPatternTest {

	@Test
	public void testDecoratorPattern() {
		Animal animal = new LivingAnimal();
		animal.describe();

		animal = new LegDecorator(animal);
		animal.describe();

		animal = new WingDecorator(animal);
		animal.describe();

		animal = new GrowlDecorator(animal);
		animal = new GrowlDecorator(animal);
		animal.describe();
	}
}

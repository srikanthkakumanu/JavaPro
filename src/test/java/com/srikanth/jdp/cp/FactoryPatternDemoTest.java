package com.srikanth.jdp.cp;



import org.junit.Test;

public class FactoryPatternDemoTest {

	@Test
	public void testFactoryPatternExample() {
		  Animal animal = AnimalFactory.getAnimal("DOG");
	        animal.makeSound();
	}

}

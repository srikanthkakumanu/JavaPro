package com.srikanth.jdp.cp;



import org.junit.Test;

public class AbstractFactoryDemoTest {

	@Test
	public void testAbstractFactoryExample() {
        AbstractFactory abstractFactory = new AbstractFactory();

        SpeciesFactory speciesFactory1 = abstractFactory.getSpeciesFactory("reptile");
        Animal a1 = speciesFactory1.getAnimal("tyrannosaurus");
        a1.makeSound();
        Animal a2 = speciesFactory1.getAnimal("snake");
        a2.makeSound();

        SpeciesFactory speciesFactory2 = abstractFactory.getSpeciesFactory("mammal");
        Animal a3 = speciesFactory2.getAnimal("dog");
        a3.makeSound();
        Animal a4 = speciesFactory2.getAnimal("cat");
        a4.makeSound();
	}

}

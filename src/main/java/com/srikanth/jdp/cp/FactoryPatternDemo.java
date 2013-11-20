package com.srikanth.jdp.cp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 11/4/13
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class FactoryPatternDemo {
	
    /**
     *  Creates objects without exposing the instantiation logic to the client and refers to the newly created
     *  object through a common interface.
     *  Factory pattern should be used when: - a framework delegate the creation of objects derived from a
     *  common superclass to the factory - we need flexibility in adding new types of objects that must be
     *  created by the class.
     *  If you use reflection for class registration, you can create concrete objects without changing the factory.
     */
	private FactoryPatternDemo() {}
	
    public static void main(String... args) {
        Animal animal = AnimalFactory.getAnimal("DOG");
        animal.makeSound();
    }

}
abstract class Animal {
    public abstract void makeSound();
}
class Dog extends Animal {
	private static Logger logger = LoggerFactory.getLogger(Dog.class);
    @Override
    public void makeSound() {
        logger.info("Woof");
    }
}
class Cat extends Animal {
	private static Logger logger = LoggerFactory.getLogger(Cat.class);
    @Override
    public void makeSound() {
        logger.info("Meow");
    }
}
class AnimalFactory {
    public static Animal getAnimal(String type) {
        if("DOG".equals(type)) {
            return new Dog();
        }
        else {
            return new Cat();
        }
    }
}


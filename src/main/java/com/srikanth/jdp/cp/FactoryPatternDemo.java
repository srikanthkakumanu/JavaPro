package com.srikanth.jdp.cp;

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
    public static void main(String... args) {
        Animal animal = AnimalFactory.getAnimal("DOG");
        animal.makeSound();
    }

}
abstract class Animal {
    public abstract void makeSound();
}
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof");
    }
}
class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow");
    }
}
class AnimalFactory {
    public static Animal getAnimal(String type) {
        if("DOG".equals(type))
            return new Dog();
        else
            return new Cat();
    }
}


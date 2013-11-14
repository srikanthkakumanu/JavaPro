package com.srikanth.jdp.cp;

/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 11/4/13
 * Time: 12:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractFactoryDemo {
    /**
     * It offers the interface for creating a family of related objects, without explicitly specifying their classes.
     * Abstract Factory should be used when:
     * A system should be configured with one of multiple families of products
     * A system should be independent of how its products are created, composed and represented
     * Products from the same family should be used all together, products from different families should
     * not be used together and this constraint must be ensured.
     * Only the product interfaces are revealed, the implementations remains hidden to the clients.
     *
     */

    public static void main(String... args) {
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
class AbstractFactory {
    public SpeciesFactory getSpeciesFactory(String type) {
        if("mammal".equals(type))
            return new MammalFactory();
        else
            return new ReptileFactory();
    }
}
abstract class SpeciesFactory {
    public abstract Animal getAnimal(String type);
}
class MammalFactory extends SpeciesFactory {

    @Override
    public Animal getAnimal(String type) {
        if ("dog".equals(type))
            return new Dog();
        else
            return new Cat();
    }
}
class ReptileFactory extends SpeciesFactory {

    @Override
    public Animal getAnimal(String type) {
        if ("snake".equals(type))
            return new Snake();
        else
            return new Tyrannosaurus();
    }
}
class Snake extends Animal {

    @Override
    public void makeSound() {
        System.out.println("Hiss");
    }
}
class Tyrannosaurus extends Animal {

    @Override
    public void makeSound() {
        System.out.println("Roar");
    }
}
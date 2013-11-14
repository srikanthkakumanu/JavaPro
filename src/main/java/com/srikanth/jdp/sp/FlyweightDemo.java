package com.srikanth.jdp.sp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 11/6/13
 * Time: 6:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class FlyweightDemo {
    /**
     * In the flyweight pattern, instead of creating large numbers of similar objects, objects are reused.
     * This can be used to reduce memory requirements and instantiation time and related costs.
     * Similarities between objects are stored inside of the objects, and differences are moved outside of
     * the objects and placed in client code. These differences are passed in to the objects when needed
     * via method calls on the objects. A Flyweight interface declares these methods. A Concrete Flyweight
     * class implements the Flyweight interface which is used to perform operations based on external state
     * and it also stores common state. A Flyweight Factory is used create and return Flyweight objects.
     */
    public static void main(String... args) {
        FlyweightFactory flyweightFactory = FlyweightFactory.getInstance();
        for (int i = 0; i < 5; i++) {
            Flyweight flyweightAdder = flyweightFactory.getFlyweight("add");
            flyweightAdder.doMath(i, i);
            Flyweight flyweightMultiplier = flyweightFactory.getFlyweight("multiply");
            flyweightMultiplier.doMath(i, i);
        }
    }
}
interface Flyweight {
    public void doMath(int a, int b);
}
/**
 The FlyweightAdder class is a concrete flyweight class. It contains an "operation" field that is used to
 store the name of an operation that is common to adder flyweights. Notice the call to Thread.sleep(3000).
 This simulates a construction process that is expensive in terms of time. Each FlyweightAdder object that
 is created takes 3 seconds to create, so we definitely want to minimize the number of flyweight objects that
 are created. The doMath() method is implemented. It displays the common "operation" field and displays the
 addition of a and b, which are external state values that are passed in and used by the FlyweightAdder
 when doMath() is executed.
 */
class FlyweightAdder implements Flyweight {
    String operation;
    public FlyweightAdder() {
        operation = "adding";
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void doMath(int a, int b) {
        System.out.println(operation + " " + a + " and " + b + ": " + (a + b));
    }
}
class FlyweightMultiplier implements Flyweight {
    String operation;
    public FlyweightMultiplier() {
        operation = "multiplying";
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void doMath(int a, int b) {
        System.out.println(operation + " " + a + " and " + b + ": " + (a * b));
    }
}
/**
 * The FlyweightFactory class is our flyweight factory. It utilizes the singleton pattern so that we only have once
 * instance of the factory, which we obtain via its static getInstance() method. The FlyweightFactory creates a
 * hashmap pool of flyweights. If a request is made for a flyweight object and that object doesn't exist, it is
 * created and placed in the flyweight pool. The flyweight pool of the FlyweightFactory stores all the instances
 * of the different types of flyweights (ie, FlyweightAdder object, FlyweightMultiplier object, etc). Thus, only
 * one instance of each type is created, and this occurs on-demand.
*/
class FlyweightFactory {
    private static FlyweightFactory flyweightFactory;
    private Map<String, Flyweight> flyweightPool;
    private FlyweightFactory() {
        flyweightPool = new HashMap<String, Flyweight>();
    }
    public static FlyweightFactory getInstance() {
        if (flyweightFactory == null) {
            flyweightFactory = new FlyweightFactory();
        }
        return flyweightFactory;
    }
    public Flyweight getFlyweight(String key) {
        if (flyweightPool.containsKey(key)) {
            return flyweightPool.get(key);
        } else {
            Flyweight flyweight;
            if ("add".equals(key)) {
                flyweight = new FlyweightAdder();
            } else {
                flyweight = new FlyweightMultiplier();
            }
            flyweightPool.put(key, flyweight);
            return flyweight;
        }
    }
}

package com.srikanth.jdp.cp;

/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 11/4/13
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class PrototypeDemo {
    /**
     * Prototype improves performance by cloning/copying the object. Rather than creation it uses cloning.
     * If the cost of creating a new object is large and creation is resource intensive, we clone the object.
     * It allows an object to create customized objects without knowing their class or any details of how to
     * create them. Up to this point it sounds a lot like the Factory Method pattern, the difference being
     * the fact that for the Factory the palette of prototypical objects never contains more than one object.
     * Intent: specifying the kind of objects to create using a prototypical instance, creating new objects
     * by copying this prototype.
     */
    public static void main(String... args) throws CloneNotSupportedException {
        ConcretePrototype obj1= new ConcretePrototype ();
        ConcretePrototype obj2 = (ConcretePrototype) obj1.clone();
    }
}
interface Prototype {
    public abstract Object clone ( ) throws CloneNotSupportedException;
}
class ConcretePrototype implements Prototype {
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

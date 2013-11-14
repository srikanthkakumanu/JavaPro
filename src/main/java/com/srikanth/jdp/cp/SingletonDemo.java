package com.srikanth.jdp.cp;

/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 11/4/13
 * Time: 12:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class SingletonDemo {
    /**
     * It ensures that only one instance of a class is created and provides global access point to the object.
     */
    public static void main(String... args) {
        Singleton singleton = Singleton.getInstance();
        singleton.sayHello();
    }
}
class Singleton {
    private static Singleton instance = null;
    public static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    public void sayHello() {
        System.out.println("Hello.!!!");
    }
}

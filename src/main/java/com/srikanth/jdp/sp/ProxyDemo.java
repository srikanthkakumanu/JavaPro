package com.srikanth.jdp.sp;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 11/6/13
 * Time: 6:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProxyDemo {
    /**
     * In the proxy pattern, a proxy class is used to control access to another class. The reasons for this control
     * can vary. As one example, a proxy may avoid instantiation of an object until the object is needed.
     * This can be useful if the object requires a lot of time or resources to create. Another reason to use a
     * proxy is to control access rights to an object. A client request may require certain credentials in order
     * to access the object.
     */
	/**
	 * 
	 */
	private ProxyDemo() {
		
	}
	
    public static void main(String... args) {
        Proxy proxy = new Proxy();
        FastThing fastThing = new FastThing();
        fastThing.sayHello();
        proxy.sayHello();
    }
}
abstract class Thing {
	private static Logger logger = LoggerFactory.getLogger(Thing.class);
    public void sayHello() {
        logger.info(this.getClass().getSimpleName() + " says howdy at " + new Date());
    }
}
// FastThing subclasses Thing.
class FastThing extends Thing {
    public FastThing() {
    }
}
// SlowThing also subclasses Thing. However, its constructor takes 5 seconds to execute.
class SlowThing extends Thing {
	private static Logger logger = LoggerFactory.getLogger(SlowThing.class);
    public SlowThing() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        	logger.error("Error: " + e.getLocalizedMessage() , e);
        	
        }
    }
}
// The Proxy class is a proxy to a SlowThing object. Since a SlowThing object takes 5 seconds to create,
// we'll use a proxy to a SlowThing so that a SlowThing object is only created on demand. This occurs when
// the proxy's sayHello() method is executed. It instantiates a SlowThing object if it doesn't already exist
// and then calls sayHello() on the SlowThing object.
class Proxy {
	private static Logger logger = LoggerFactory.getLogger(Proxy.class);
    SlowThing slowThing;
    public Proxy() {
        logger.info("Creating proxy at " + new Date());
    }
    public void sayHello() {
        if (slowThing == null) {
            slowThing = new SlowThing();
        }
        slowThing.sayHello();
    }
}
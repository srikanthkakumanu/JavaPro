package com.srikanth.jdp.cp;

/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 11/4/13
 * Time: 1:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ObjectPoolDemo {
    /**
     * Object Pool design pattern reuses and shares objects that are expensive to create.
     * Performance can be sometimes the key issue during the software development and the
     * object creation(class instantiation) is a costly step. While the Prototype pattern
     * helps in improving the performance by cloning the objects, the Object Pool pattern
     * offer a mechanism to reuse objects that are expensive to create.
     * Clients of an object pull "feel" like they are owners of a service although the
     * service is shared among many other clients.
     * Intent:-
     * Reuse and share objects that are expensive to create.
     * Implementation involves the following objects:
     * Reusable - Wraps the limited resource, will be shared by several clients for a limited amount of time.
     * Client - uses an instance of type Reusable.
     * ReusablePool - manage the reusable objects for use by Clients, creating and managing a pool of objects.
     * Example: Database Connection Pool
     */
    public static void main(String... args) {

    }
}


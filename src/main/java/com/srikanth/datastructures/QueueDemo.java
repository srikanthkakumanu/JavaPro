package com.srikanth.datastructures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 11/1/13
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
/**
 * @author Srikanth
 *
 */
public class QueueDemo {
 
	private static Logger logger = LoggerFactory.getLogger(QueueDemo.class);
	// Keep in mind that all of those classes are from SLF4J package!
	/**
     * A Queue is a type of abstract data type that can be implemented as a linear or circular list.
     * A Queue has a front and rear and it follows First In First Out (FIFO).
     * There are four types of Queue: Linear Queue, Circular Queue, Priority Queue, Double ended Queue (Dequeue)
     * Resources:-
     * http://www.youtube.com/watch?v=vs30u4J5Ufs
     */
	
    private static final int MAX = 5;
    private static int[] queue = new int[MAX];
    private static int front = -1;
    private static int rear = -1;
    /**
     * 
     *
     */
    private QueueDemo() {
    	
    }
    public static void main(String... args) {

        enQueue(10);
        enQueue(20);
        enQueue(30);
        enQueue(40);
        enQueue(50);
        display();
        deQueue();
        deQueue();
        deQueue();
        display();
    }
    public static void enQueue(int value) {
        if(rear == MAX-1) {
            logger.info("Error: Queue is Overflown.");
            return;
        }
        if(front == -1) {
            front++;
        }    
        rear = rear + 1;
        queue[rear] = value;
        logger.info("enQueue[value=" + queue[rear] + "||front=" + front +"||rear=" + rear + "]");
    }
    public static void deQueue() {
        if(front == -1 || front == rear+1) {
            logger.info("Error: Queue is Underflown.");
            return;
        }
        front = front + 1;
    }
    public static void display() {
        if(front == -1 || (front == rear+1)) {
            logger.info("Queue is empty.");
            return;
        }
        logger.info("----------------");
        for(int i = front; i <= rear; i++) {
            logger.info("queue[" + i + "]="+ queue[i]);

        }

    }
}

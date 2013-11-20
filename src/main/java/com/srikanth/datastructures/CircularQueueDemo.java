package com.srikanth.datastructures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 11/1/13
 * Time: 11:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class CircularQueueDemo {

	private static Logger logger = LoggerFactory.getLogger(CircularQueueDemo.class);
	// Keep in mind that all of those classes are from SLF4J package!
	
    /**
     * Resources:-
     * http://www.youtube.com/watch?v=UxHY8D-1fJY
     * http://www.youtube.com/watch?v=326CyXJ7ylU
     */
    private static final int MAX = 5;
    private static int[] queue = new int[MAX];
    private static int front = -1;
    private static int rear = -1;

    private CircularQueueDemo() {}
//    public static void main(String... args) {
//
//        enQueue(10);
//        enQueue(20);
//        enQueue(30);
//        enQueue(40);
//        enQueue(50);
//        display();
//        deQueue();
//        deQueue();
//        deQueue();
//        display();
//    }
    public static void enQueue(int value) {
        if((front == 0 && rear == MAX-1) || (front == rear+1)) {
            logger.info("Error: Queue is Overflown.");
            return;
        }
        if(front == -1) {
            front++;
        }   
        if(rear == MAX-1) {
            rear = 0;
        }    
        else {
            rear = rear + 1;
        }   
        queue[rear] = value;
        logger.info("enQueue[value=" + queue[rear] + "||front=" + front +"||rear=" + rear + "]");
    }
    public static void deQueue() {
        if(front == -1) {
            logger.info("Error: Queue is Underflown.");
            return;
        }
        if(front == rear) {
            front = -1;
            rear = -1;
        } else if(front == MAX-1)
            front = 0;
        else {
            front = front + 1;
        }    
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
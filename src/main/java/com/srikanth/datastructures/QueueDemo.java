package com.srikanth.datastructures;

/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 11/1/13
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueueDemo {
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
    private static void enQueue(int value) {
        if(rear == MAX-1) {
            System.out.println("Error: Queue is Overflown.");
            return;
        }
        if(front == -1)
            front++;

        rear = rear + 1;
        queue[rear] = value;
        System.out.println("enQueue[value=" + queue[rear] + "||front=" + front +"||rear=" + rear + "]");
    }
    private static void deQueue() {
        if(front == -1 || front == rear+1) {
            System.out.println("Error: Queue is Underflown.");
            return;
        }
        front = front + 1;
    }
    private static void display() {
        if(front == -1 || (front == rear+1)) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.println("----------------");
        for(int i = front; i <= rear; i++) {
            System.out.println("queue[" + i + "]="+ queue[i]);

        }

    }
}

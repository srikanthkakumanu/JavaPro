package com.srikanth.datastructures;

/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 11/1/13
 * Time: 11:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class CircularQueueDemo {

    /**
     * Resources:-
     * http://www.youtube.com/watch?v=UxHY8D-1fJY
     * http://www.youtube.com/watch?v=326CyXJ7ylU
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
        if((front == 0 && rear == MAX-1) || (front == rear+1)) {
            System.out.println("Error: Queue is Overflown.");
            return;
        }
        if(front == -1)
            front++;
        if(rear == MAX-1)
            rear = 0;
        else
            rear = rear + 1;

        queue[rear] = value;
        System.out.println("enQueue[value=" + queue[rear] + "||front=" + front +"||rear=" + rear + "]");
    }
    private static void deQueue() {
        if(front == -1) {
            System.out.println("Error: Queue is Underflown.");
            return;
        }
        if(front == rear) {
            front = -1;
            rear = -1;
        } else if(front == MAX-1)
            front = 0;
        else
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
package com.srikanth.datastructures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 11/3/13
 * Time: 5:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class LinkedListDemo {

	private static Logger logger = LoggerFactory.getLogger(LinkedListDemo.class);
	// Keep in mind that all of those classes are from SLF4J package!
    /**
     *   http://giridhar-mb.blogspot.in/2012/11/linked-list-implementation-in-java.html
     *   http://stackoverflow.com/questions/2034444/java-implementation-of-linked-list-data-structure
     */
	private LinkedListDemo() {}
	
    public static void main(String... args) {
        LinkedList ll = new LinkedList();

        ll.insert(10);
        ll.insert(2);
        ll.insert(3);
        ll.insert(4);
        ll.insert(7);
        ll.insert(10);
        ll.insert(11);
        ll.insert(12);
        ll.insert(13);

        logger.info("\n--- Display ---\n");
        ll.display();

        logger.info("\n--- Deleting 7 ---\n");
        ll.delete(7);

        logger.info("\n--- After Deleting 7 ---\n");
        ll.display();

        logger.info("\n--- Inserting 100 ---\n");
        ll.insert(100);

        logger.info("\n--- After Inserting 100 ---\n");
        ll.display();
    }
}
class Node {

    private int data;
    private Node next;

    public Node(int data) {
        this.data = data;
    }
    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }
    public Node getNext() {
        return next;
    }
    public void setNext(Node next) {
        this.next = next;
    }
    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}
class LinkedList {
	private static Logger logger = LoggerFactory.getLogger(LinkedList.class);
    private Node start;

    public LinkedList() {
        start = null;
    }
    public void insert(int x) {
        if(start == null) {
            start = new Node(x, start);
        } else {
            Node temp = start;

            while(temp.getNext() != null) {
                temp = temp.getNext();
            }
            Node newNode = new Node(x,null);
            temp.setNext(newNode);
        }
    }
    public void display() {
        int count = 0;

        if(start == null) {
            logger.info("\n List is empty !!");
        } else {
            Node temp = start;

            while(temp.getNext() != null) {
                logger.info("count("+count+") , node value="+temp.getData());
                count++;
                temp = temp.getNext();
            }
        }
    }
    public void delete(int x) {
        Node previous =  start;
        Node temp = start;

        while(temp.getData() != x) {
            if(temp.getNext() == null) {
                logger.info("\nElement "+ x + " not found !");
                break;
            }
            previous = temp;
            temp = temp.getNext();
        }
        if(temp == start) {
            start = start.getNext();
        } else {
            previous.setNext(temp.getNext());
        }
    }
}


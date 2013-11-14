package com.srikanth.datastructures;

/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 11/3/13
 * Time: 5:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class LinkedListDemo {

    /**
     *   http://giridhar-mb.blogspot.in/2012/11/linked-list-implementation-in-java.html
     *   http://stackoverflow.com/questions/2034444/java-implementation-of-linked-list-data-structure
     */
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

        System.out.println("\n--- Display ---\n");
        ll.display();

        System.out.println("\n--- Deleting 7 ---\n");
        ll.delete(7);

        System.out.println("\n--- After Deleting 7 ---\n");
        ll.display();

        System.out.println("\n--- Inserting 100 ---\n");
        ll.insert(100);

        System.out.println("\n--- After Inserting 100 ---\n");
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
            System.out.println("\n List is empty !!");
        } else {
            Node temp = start;

            while(temp.getNext() != null) {
                System.out.println("count("+count+") , node value="+temp.getData());
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
                System.out.println("\nElement "+ x + " not found !");
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


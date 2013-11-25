package com.srikanth.datastructures;

/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 11/3/13
 * Time: 5:14 PM
 *   http://giridhar-mb.blogspot.in/2012/11/linked-list-implementation-in-java.html
 *   http://stackoverflow.com/questions/2034444/java-implementation-of-linked-list-data-structure
 *   http://www.mycstutorials.com/articles/data_structures/linkedlists
 */
//Example:3
class Node {
	Node next;
	Object data;
	
	public Node(Object data) {
		next = null;
		this.data = data;
	}
	public Node(Object data, Node next) {
		this.next = next;
		this.data = data;
	}
	public Object getData() {
		return this.data;
	}
	public Node getNext() {
		return this.next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
}
class LinkedList {
	private Node head;
	private int listCount;
	
	public LinkedList() {
		head = new Node(null);
		listCount = 0;
	}
	public void add(Object data) {
		Node temp = new Node(data);
		Node current = head;
		// starting at the head node, crawl to the end of the list
		while(current.getNext() != null) {
			current = current.getNext();
		}
		// the last node's "next" reference set to our new node
		current.setNext(temp);
		listCount++;
	}
	public void add(Object data, int index) {
		Node temp = new Node(data);
		Node current = head;
		// crawl to the requested index or the last element in the list, whichever comes first
		for (int i = 1; i < index && current.getNext() != null; i++) {
			current = current.getNext();
		}
		// set the new node's next-node reference to this node's next-node reference
		temp.setNext(current.getNext());
		// now set this node's next-node reference to the new node
		current.setNext(temp);
		listCount++;
	}
	public Object get(int index) {
		// index must be 1 or higher
		if(index <= 0) {
			return null;
		}
		
		Node current = head.getNext();
		for(int i = 1; i < index; i++) {
		
			if(current.getNext() == null) {
				return null;
			}
			current = current.getNext();
		}
		return current.getData();
	}
	public boolean remove(int index) {
		// if the index is out of range, exit
		if(index < 1 || index > size()) {
			return false;
		}
		Node current = head;
		for(int i = 1; i < index; i++) {
			if(current.getNext() == null) {
				return false;
			}
			current = current.getNext();
		}
		current.setNext(current.getNext().getNext());
		listCount--; // decrement the number of elements variable
		return true;
	}
	public int size() {
		return listCount;
	}
	public String toString() {
		Node current = head.getNext();
		String output = "";
		while(current != null) {
			output += "[" + current.getData().toString() + "]";
			current = current.getNext();
		}
		return output;
	}
}

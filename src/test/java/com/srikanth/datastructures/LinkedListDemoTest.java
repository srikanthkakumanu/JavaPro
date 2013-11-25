package com.srikanth.datastructures;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LinkedListDemoTest {
	private static Logger logger = LoggerFactory.getLogger(LinkedListDemoTest.class);
	@Test
	public void testAddOperation() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(new Integer(100));
		linkedList.add(new Integer(200));
		linkedList.add(new Integer(300));
		linkedList.add(new Integer(400));
		linkedList.add(new Integer(500));
		logger.info(linkedList.toString());
	}
	@Test
	public void testRemoveOperation() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(new Integer(100));
		linkedList.add(new Integer(200));
		linkedList.add(new Integer(300));
		linkedList.add(new Integer(400));
		linkedList.add(new Integer(500));
		logger.info("After Add Operation: " + linkedList.toString());
		linkedList.remove(4);
		linkedList.remove(2);
		linkedList.remove(1);
		logger.info("After remove Operation: " + linkedList.toString());
	}
	@Test
	public void testAddAtIndexOperation() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(new Integer(100), 7);
		linkedList.add(new Integer(200), 6);
		linkedList.add(new Integer(300), 1);
		linkedList.add(new Integer(400), 2);
		linkedList.add(new Integer(500), 5);
		logger.info("After Add Operation: " + linkedList.toString());
		linkedList.add(new Integer(350), 3);
		linkedList.add(new Integer(450), 4);
		logger.info("After Add Operation at Specific Index: " + linkedList.toString());
	}
	@Test
	public void testGetOperation() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(new Integer(100));
		linkedList.add(new Integer(200));
		linkedList.add(new Integer(300));
		linkedList.add(new Integer(400));
		linkedList.add(new Integer(500));
		logger.info("Value at Specific Index: " + linkedList.get(4));
	}
	@Test
	public void testRemoveWithInvalidIndexOperation() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(new Integer(100));
		linkedList.add(new Integer(200));
		linkedList.add(new Integer(300));
		linkedList.add(new Integer(400));
		linkedList.add(new Integer(500));
		linkedList.remove(0);
	}
	@Test
	public void testGetWithInvalidIndexOperation() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(new Integer(100));
		linkedList.add(new Integer(200));
		linkedList.add(new Integer(300));
		linkedList.add(new Integer(400));
		linkedList.add(new Integer(500));
		linkedList.get(0);
	}
	
}

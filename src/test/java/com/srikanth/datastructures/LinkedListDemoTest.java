package com.srikanth.datastructures;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LinkedListDemoTest {
	private static Logger logger = LoggerFactory.getLogger(LinkedListDemoTest.class);
	@Test
	public void testInsert() {
		 LinkedList linkedList = new LinkedList();  	
		 	linkedList.insert(10);
		 	linkedList.insert(2);
		 	linkedList.insert(3);
		 	linkedList.insert(4);
		 	linkedList.insert(7);
		 	linkedList.insert(10);
		 	linkedList.insert(11);
		 	linkedList.insert(12);
		 	linkedList.insert(13);

	        logger.info("\n--- Display ---\n");
	        linkedList.display();
	}
	@Test
	public void testDelete() {
		 LinkedList linkedList = new LinkedList();  	
		 	linkedList.insert(10);
		 	linkedList.insert(2);
		 	linkedList.insert(3);
		 	linkedList.insert(4);
		 	linkedList.insert(7);
		 	linkedList.insert(10);
		 	linkedList.insert(11);
		 	linkedList.insert(12);
		 	linkedList.insert(13);

	        logger.info("\n--- Display ---\n");
	        linkedList.display();
	        
	        logger.info("\n--- Deleting 7 ---\n");
	        linkedList.delete(7);

	        logger.info("\n--- After Deleting 7 ---\n");
	        linkedList.display();

	        logger.info("\n--- Inserting 100 ---\n");
	        linkedList.insert(100);

	        logger.info("\n--- After Inserting 100 ---\n");
	        linkedList.display();
	        
	}

}

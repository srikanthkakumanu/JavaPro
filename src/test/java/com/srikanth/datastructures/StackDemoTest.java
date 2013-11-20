package com.srikanth.datastructures;


import org.junit.Test;

public class StackDemoTest {

	@Test
	public void testPush() {
		StackDemo.createStack();
		StackDemo.push(20);
		StackDemo.push(30);
		StackDemo.push(40);
		StackDemo.push(50);
	    //push(60);
		StackDemo.printStackData();
	}

	@Test
	public void testPop() {
	    StackDemo.createStack();
	    StackDemo.push(10);
	    StackDemo.push(20);
	    StackDemo.push(30);
	    StackDemo.push(40);
	    StackDemo.push(50);
	    StackDemo.pop();
	    StackDemo.pop();
	    StackDemo.pop();
	    StackDemo.printStackData();
	}

}

package com.srikanth.datastructures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 10/31/13
 * Time: 8:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class StackDemo {
	private static Logger logger = LoggerFactory.getLogger(StackDemo.class);
	// Keep in mind that all of those classes are from SLF4J package!
	
    /**
     *  Stack is a Linear data structure and stores set of homogeneous elements.
     *  It follows Last In First Out (LIFO). Stack can created using Array or Linked List
     *  It Performs the following operations
     *  PUSH - Insert an element on top of stack.
     *  POP - Removes an element from top of stack.
     *  PEEP/PEEK - View the element present on top of stack without removing it.
     *  ISEMPTY - Whether stack is empty.
     *  ISFULL - Whether stack is full.
     *  Resources:-
     *  http://www.youtube.com/watch?v=peCaqSJN6_k
     */
    private static int[] stack = null;
    private static int top = 0;

    private StackDemo() {}
    
    public static void main(String[] args) {
        performSimpleStackOperation();
        if(top == stack.length) {
            performDynamicStackOperation();
            performSimpleStackOperation();

        }
    }

    public static void performSimpleStackOperation() {
        if(stack == null) {
            createStack();
        }    
        //fillStackWithData();
        push(10);
        push(20);
        push(30);
        push(40);
        push(50);
        //push(60);
        printStackData();
//        pop();
//        pop();
//        pop();
//        pop();
//        pop();
        printStackData();
    }

    public static void performDynamicStackOperation() {
        int[] dynamicStack = new int[stack.length + 5];
        for(int i =0; i < stack.length; i++) {
            dynamicStack[i] = stack[i];
        }
        stack = dynamicStack;
    }
    public static void createStack() {
        stack = new int[5];
    }

    public static void push(int value) {
        if(stack != null) {
            if(top >= stack.length) {
                logger.info("Error: Stack Over Flown");
            } else {
                stack[top] = value;
                if(top < stack.length-1)
                    top++;
            }
        }
    }

    public static void pop() {
        if(top < 0) {
        	logger.info("Error: Stack Under Flown");
        } else {
            stack[top] = 0;
            top--;
            /*
            or just top--;
             */
        }
    }

    public static void printStackData() {
        if(stack != null) {
            for (int i = 0; i < stack.length; i++) {
            	logger.info(new Integer(stack[i]).toString());
            }
        }
        logger.info("Current top is : " + top);
    }

    public static void fillStackWithData() {
        if(stack != null) {
            for (int i = 0; i < stack.length; i++) {
                stack[i] = i;
            }
        }
    }
}

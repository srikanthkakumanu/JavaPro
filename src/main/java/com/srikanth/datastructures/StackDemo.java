package com.srikanth.datastructures;

/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 10/31/13
 * Time: 8:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class StackDemo {
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

    public static void main(String[] args) {
        performSimpleStackOperation();
        if(top == stack.length) {
            performDynamicStackOperation();
            performSimpleStackOperation();

        }
    }

    private static void performSimpleStackOperation() {
        if(stack == null)
            createStack();
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

    private static void performDynamicStackOperation() {
        int[] dynamicStack = new int[stack.length + 5];
        for(int i =0; i < stack.length; i++) {
            dynamicStack[i] = stack[i];
        }
        stack = dynamicStack;
    }
    private static void createStack() {
        stack = new int[5];
    }

    private static void push(int value) {
        if(stack != null) {
            if(top >= stack.length) {
                System.out.println("Error: Stack Over Flown");
            } else {
                stack[top] = value;
                if(top < stack.length-1)
                    top++;
            }
        }
    }

    private static void pop() {
        if(top < 0) {
            System.out.println("Error: Stack Under Flown");
        } else {
            stack[top] = 0;
            top--;
            /*
            or just top--;
             */
        }
    }

    private static void printStackData() {
        if(stack != null) {
            for (int i = 0; i < stack.length; i++) {
                System.out.println(stack[i]);
            }
        }
        System.out.println("Current top is : " + top);
    }

    private static void fillStackWithData() {
        if(stack != null) {
            for (int i = 0; i < stack.length; i++) {
                stack[i] = i;
            }
        }
    }
}

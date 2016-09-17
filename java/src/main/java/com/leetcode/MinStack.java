package com.leetcode;

/**
 * 155. Min Stack
 * 
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * 
 * Skill: Store node reversely.
 * */

public class MinStack {
    private static class Node {
        int value, min;
        Node next;
        
        public Node (int x) {
            next = null;
            value = x;
        }
    }
    
    private Node head = new Node(0);
    
    public void push(int x) {
        Node node = new Node(x);
        
        node.next = head.next;
        head.next = node;
        
        if (node.next == null)
            node.min = x;
        else
            node.min = Math.min(node.next.min, x);
    }

    public void pop() {
        if (head.next != null) 
            head.next = head.next.next;
    }

    public int top() {
        return head.next.value;
    }

    public int getMin() {
        return head.next.min;
    }
}

package com.leetcode;
/**
 * 146. LRU Cache
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 *
 * It should support the following operations: get and keys.
 *
 * get(key) - Get the value (will always be positive) of the key
 * if the key exists in the cache, otherwise return -1.
 *
 * keys(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate
 * the least recently used item before inserting a new item.
 * 
 * Skill: 
 * 使用双向链表，方便删除结点
 * 使用hashmap，方便查找内容是否在cache中
 * 记录dummy head，方便删除最久的node
 * 记录tail，方便最常用加到尾部
 * 
 * 注意空指针
 * 
 * */

import java.util.HashMap;
import java.util.LinkedHashMap;

public class LRUCache {
	/**************************************************************
     ******************  Original solution  ***********************
     **************************************************************/
    private static class ListNode {
        public int key;
        public int value;
        public ListNode prev;
        public ListNode next;
        public ListNode (int k, int v) { key = k; value = v; }
    }

    HashMap<Integer, ListNode> map;
    ListNode dummy;
    ListNode tail;
    int size;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        dummy = new ListNode(0, 0);
        tail = dummy;
        size = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            if (node == tail) {
                return node.value;
            }
            else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node.prev = tail;
                tail.next = node;
                tail = node;
                tail.next = null;
            }
            return node.value;
        }
        return -1;
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.value = value;
            if (node != tail) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node.prev = tail;
                tail.next = node;
                tail = node;
                tail.next = null;
            }
            return;
        }

        if (map.size() == size) {
            map.remove(dummy.next.key);
            dummy.next = dummy.next.next;
            if (dummy.next != null) {
                dummy.next.prev = dummy;
            }
        }

        tail.next = new ListNode(key, value);
        tail.next.prev = tail;
        tail = tail.next;
        map.put(key, tail);
    }

    /**************************************************************
     *******************  Minimal solution  ***********************
     **************************************************************/
    class LRUCache2 {

        LinkedHashMap<Integer, Integer> map;
        int capacity;

        public LRUCache2(int capacity) {
            map = new LinkedHashMap<>(capacity);
            this.capacity = capacity;
        }

        public int get(int key) {
            Integer value = map.get(key);
            if (value == null) {
                return -1;
            } else {
                map.remove(key);
                map.put(key, value);
                return value;
            }
        }

        public void set(int key, int value) {
            if (map.containsKey(key))
                map.remove(key);
            else if (map.size() == this.capacity)
                map.remove(map.keySet().iterator().next());

            map.put(key, value);
        }
    }

    /**************************************************************
     *********************  Other solution  ***********************
     **************************************************************/
    private static class LRUCacheExample {
        private class Node{
            Node prev;
            Node next;
            int key;
            int value;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
                this.prev = null;
                this.next = null;
            }
        }

        private int capacity;
        private HashMap<Integer, Node> hs = new HashMap<Integer, Node>();
        private Node head = new Node(-1, -1);
        private Node tail = new Node(-1, -1);

        LRUCacheExample(int capacity) {
            this.capacity = capacity;
            tail.prev = head;
            head.next = tail;
        }

        public int get(int key) {
            if( !hs.containsKey(key)) {
                return -1;
            }

            // remove current
            Node current = hs.get(key);
            current.prev.next = current.next;
            current.next.prev = current.prev;

            // move current to tail
            move_to_tail(current);

            return hs.get(key).value;
        }

        public void set(int key, int value) {
            if( get(key) != -1) {
                hs.get(key).value = value;
                return;
            }

            if (hs.size() == capacity) {
                hs.remove(head.next.key);
                head.next = head.next.next;
                head.next.prev = head;
            }

            Node insert = new Node(key, value);
            hs.put(key, insert);
            move_to_tail(insert);
        }

        private void move_to_tail(Node current) {
            current.prev = tail.prev;
            tail.prev = current;
            current.prev.next = current;
            current.next = tail;
        }
    }
}

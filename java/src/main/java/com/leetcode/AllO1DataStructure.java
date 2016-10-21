package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 432. All O`one Data Structure
 *
 * Implement a data structure supporting the following operations:
 *
 * Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
 * Dec(Key) - Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. Key is guaranteed to be a non-empty string. If the key does not exist, this function does nothing.
 * GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
 * GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
 * Challenge: Perform all these in O(1) time complexity.
 */
public class AllO1DataStructure {
    private static class AllOne {

        Map<String, Node> k2n;
        Node head, tail;

        private static class Node {
            Node prev, next;
            int val;
            Set<String> keys = new HashSet<>();
            Node(int v) { val = v; }
            Node(int v, Node p, Node n) { val = v; prev = p; next = n; prev.next = this; next.prev = this; }
        }

        /** Initialize your data structure here. */
        public AllOne() {
            k2n = new HashMap<>();
            head = new Node(Integer.MIN_VALUE);
            tail = new Node(Integer.MAX_VALUE);
            head.next = tail;
            tail.prev = head;
        }

        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
        public void inc(String key) {
            int value = 1;
            Node prev = head;

            // remove old value
            if (k2n.containsKey(key)) {
                Node node = k2n.get(key);
                value = node.val + 1;
                node.keys.remove(key);
                if (node.keys.isEmpty()) {
                    // remove the node from list
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                    prev = node.prev;
                } else {
                    prev = node;
                }
            }

            // add new value
            if (prev.next.val == value) {
                k2n.put(key, prev.next);
            } else {
                k2n.put(key, new Node(value, prev, prev.next));
            }
            k2n.get(key).keys.add(key);
        }

        /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
        public void dec(String key) {
            Node node = k2n.get(key);
            if (node != null) {
                int value = node.val - 1;
                Node next = node;

                // remove old value
                node.keys.remove(key);
                if (node.keys.isEmpty()) {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                    next = node.next;
                }
                k2n.remove(key);

                // add new value
                if (value != 0) {
                    if (next.prev.val == value) {
                        k2n.put(key, next.prev);
                        k2n.get(key).keys.add(key);
                    } else {
                        Node newNode = new Node(value, next.prev, next);
                        newNode.keys.add(key);
                        k2n.put(key, newNode);
                    }
                }
            }
        }

        /** Returns one of the keys with maximal value. */
        public String getMaxKey() {
            if (k2n.isEmpty()) return "";
            return tail.prev.keys.iterator().next();
        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {
            if (k2n.isEmpty()) return "";
            return head.next.keys.iterator().next();
        }
    }

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
}

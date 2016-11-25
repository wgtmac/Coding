package com.leetcode;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 460. LFU Cache
 *
 * Design and implement a data structure for Least Frequently Used (LFU) cache.
 * It should support the following operations: get and set.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists
 * in the cache, otherwise return -1.
 *
 * set(key, value) - Set or insert the value if the key is not already present.
 * When the cache reaches its capacity, it should invalidate the least frequently
 * used item before inserting a new item. For the purpose of this problem, when
 * there is a tie (i.e., two or more keys that have the same frequency), the
 * least recently used key would be evicted.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 * LFUCache cache = new LFUCache( 2 );
 *
 *        cache.set(1, 1);
 *        cache.set(2, 2);
 *        cache.get(1);       // returns 1
 *        cache.set(3, 3);    // evicts key 2
 *        cache.get(2);       // returns -1 (not found)
 *        cache.get(3);       // returns 3.
 *        cache.set(4, 4);    // evicts key 1.
 *        cache.get(1);       // returns -1 (not found)
 *        cache.get(3);       // returns 3
 *        cache.get(4);       // returns 4
 *
 */
public class LFUCache {
    private static class ListNode {
        int val;
        ListNode prev, next;
        ListNode(int v) { val = v; }
    }

    // key, value and frequency mapping
    private Map<Integer, Integer> k2v, k2f;
    private Map<Integer, Set<Integer>> f2k;
    private int capacity;

    // bi-directional linked list to store frequency increasingly
    private ListNode head, tail;
    private Map<Integer, ListNode> f2n;

    public LFUCache(int capacity) {
        this.capacity = capacity;

        k2v = new HashMap<>();
        k2f = new HashMap<>();
        f2k = new HashMap<>();
        f2n = new HashMap<>();

        head = new ListNode(Integer.MIN_VALUE);
        tail = new ListNode(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Integer value = k2v.get(key);
        if (value != null) {
            int freq = k2f.get(key);
            ListNode prevNode = f2k.get(freq).size() == 1 ?
                    f2n.get(freq).prev : f2n.get(freq);

            removeKeyWithFrequency(key, freq);
            addKeyIntoNewFrequency(key, freq + 1, prevNode);
        }

        return value == null ? -1 : value;
    }

    private void removeKeyWithFrequency(int key, int freq) {
        Set<Integer> keySet = f2k.get(freq);
        if (keySet.size() == 1) {
            removeNode(f2n.get(freq));
            f2k.remove(freq);
            f2n.remove(freq);
        } else {
            keySet.remove(key);
        }
    }

    public void set(int key, int value) {
        if (capacity == 0) return;

        if (k2v.containsKey(key)) {
            get(key);
        } else {
            if (capacity == k2v.size()) removeLeastUsed();
            addKeyIntoNewFrequency(key, 1, head);
        }

        k2v.put(key, value);
    }

    private void removeLeastUsed() {
        int leastFreq = head.next.val;
        int oldestKey = f2k.get(leastFreq).iterator().next();
        removeKeyWithFrequency(oldestKey, leastFreq);
        k2f.remove(oldestKey);
        k2v.remove(oldestKey);
    }

    private void addKeyIntoNewFrequency(int key, int freq, ListNode prevNode) {
        Set<Integer> keySet = f2k.get(freq);
        if (keySet == null) {
            keySet = new LinkedHashSet<>();
            f2k.put(freq, keySet);

            ListNode node = new ListNode(freq);
            addNode(prevNode, node);
            f2n.put(freq, node);
        }
        keySet.add(key);
        k2f.put(key, freq);
    }

    private void removeNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNode(ListNode prevNode, ListNode node) {
        node.prev = prevNode;
        node.next = prevNode.next;
        prevNode.next.prev = node;
        prevNode.next = node;
    }

    public static void main(String[] args) {
        LFUCache obj = new LFUCache(2);
        obj.set(1,1);
        obj.set(2,2);
        obj.get(1);
        obj.set(3,3);
        obj.get(2);
        obj.get(3);
        obj.set(4,4);
        obj.get(1);
        obj.get(3);
        obj.get(4);
    }
}

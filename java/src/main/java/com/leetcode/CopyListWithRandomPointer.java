package com.leetcode;

/**
 * 138. Copy List with Random Pointer
 * 
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 * 
 * Skill: 
 * 用hashmap存下新旧结点
 * 
 * TRICK 把新复制的作为当前的next连接前后
 * */

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
	private static class RandomListNode {
		int label;
		RandomListNode next, random;
		RandomListNode(int x) { this.label = x; }
	};
	
	public RandomListNode copyRandomList_Better(RandomListNode head) {
		RandomListNode p = head;
		// copy and insert after next
		while (p != null) {
			RandomListNode next = p.next;
			RandomListNode copy = new RandomListNode(p.label);
			p.next = copy;
			copy.next = next;
			p = next;
		}
		// connect random
		p = head;
		while (p != null) {
			p.next.random = (p.random != null) ? p.random.next : null;
			p = p.next.next;
		}
		// delete link between original and copied
		p = head;
		RandomListNode headCopy = (p != null) ? p.next : null;
		while (p != null) {
			RandomListNode copy = p.next;
			p.next = copy.next;
			p = p.next;
			copy.next = (p != null) ? p.next : null;
		}
		return headCopy;
	}
	
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode copyHead = new RandomListNode(head.label);
        RandomListNode node = head.next;
        RandomListNode prev = copyHead;
      
        map.put(head, copyHead);
        while (node != null) {
            RandomListNode temp = new RandomListNode(node.label);
            map.put(node, temp);
            prev.next = temp;
            prev = temp;
            node = node.next;
        }
        
        while (head != null) {
            if (head.random != null) {
                map.get(head).random = map.get(head.random);
            }
            head = head.next;
        }
        
        return copyHead;
    }
}

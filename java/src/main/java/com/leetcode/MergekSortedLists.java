package com.leetcode;

/**
 * 23. Merge k Sorted Lists
 *
 * Merge k sorted linked lists and return it as one sorted list.
 * Analyze and describe its complexity.
 */

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergekSortedLists {
	class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) { val = x; next = null; }
	}

	private static final Comparator<ListNode> listComparator = new Comparator<ListNode>() {
		@Override
		public int compare(ListNode x, ListNode y) {
			return x.val - y.val;
		}
	};

	public ListNode mergeKLists_Better(List<ListNode> lists) {
		if (lists.isEmpty()) return null;
		Queue<ListNode> queue = new PriorityQueue<>(lists.size(), listComparator);
		for (ListNode node : lists) {
			if (node != null) {
				queue.add(node);
			}
		}
		ListNode dummyHead = new ListNode(0);
		ListNode p = dummyHead;
		while (!queue.isEmpty()) {
			ListNode node = queue.poll();
			p.next = node;
			p = p.next;
			if (node.next != null) {
				queue.add(node.next);
			}
		}
		return dummyHead.next;
	}
}

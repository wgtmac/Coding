package com.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 295. Find Median from Data Stream
 *
 * Median is the middle value in an ordered integer list. If the size of the list
 * is even, there is no middle value. So the median is the mean of the two middle value.
 *
 * Examples:
 * [2,3,4] , the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * For example:
 *
 * add(1)
 * add(2)
 * findMedian() -> 1.5
 * add(3)
 * findMedian() -> 2
 */
public class FindMedianfromDataStream {
    private static class MedianFinder {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(),
                maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        // Adds a number into the data structure.
        public void addNum(int num) {
            if (minHeap.size() == maxHeap.size()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }

            if (!minHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(minHeap.poll());
            }
        }

        // Returns the median of current data stream
        public double findMedian() {
            if (minHeap.size() == maxHeap.size()) {
                return maxHeap.isEmpty() ? 0 : (maxHeap.peek() + minHeap.peek()) / 2.0;
            } else {
                return maxHeap.peek();
            }
        }
    };
}

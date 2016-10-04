package com.leetcode;

import java.util.*;

/**
 * 347. Top K Frequent Elements
 *
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);

//        PriorityQueue<Map.Entry<Integer, Integer>> pq =
//                new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
//                    @Override
//                    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
//                        return o1.getValue() - o2.getValue();
//                    }
//                });
//
//
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            pq.offer(entry);
//            if (pq.size() > k)
//                pq.poll();
//        }
//
//        while (!pq.isEmpty())
//            list.add(pq.poll().getKey());

        /**
         * Bucket sorting based O(n)
         */
        int max = 0;
        for (int value : map.values())
            max = Math.max(max, value);

        List<Integer>[] bucket = new List[max];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (bucket[entry.getValue() - 1] == null)
                bucket[entry.getValue() - 1] = new ArrayList<>();
            bucket[entry.getValue() - 1].add(entry.getKey());
        }

        for (int i = max - 1; i >= 0 && list.size() < k; --i) {
            if (bucket[i] != null) {
                for (int num : bucket[i]) {
                    list.add(num);
                }
            }
        }

        return list;
    }
}

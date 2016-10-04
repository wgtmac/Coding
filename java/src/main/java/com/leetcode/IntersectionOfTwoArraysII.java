package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 350. Intersection of Two Arrays II
 *
 * Given two arrays, write a function to compute their intersection.
 *
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 *
 * Note:
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 *
 * Follow up:
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such
 * that you cannot load all elements into the memory at once?
 */
public class IntersectionOfTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
        for (int num : nums1)
            map1.put(num, map1.getOrDefault(num, 0) + 1);
        for (int num : nums2)
            map2.put(num, map2.getOrDefault(num, 0) + 1);
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry1 : map1.entrySet()) {
            int num = entry1.getKey();
            if (map2.containsKey(num)) {
                for (int i = 0; i < Math.min(map1.get(num), map2.get(num)); ++i) {
                    list.add(num);
                }
            }
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; ++i)
            arr[i] = list.get(i);
        return arr;
    }
}

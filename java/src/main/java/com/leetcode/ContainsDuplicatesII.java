package com.leetcode;

import java.util.LinkedHashSet;

/**
 * 219. Contains Duplicate II
 *
 * Given an array of integers and an integer k, return true if and only if there
 * are two distinct indices i and j in the array such that nums[i] = nums[j]
 * and the difference between i and j is at most k.
*/

public class ContainsDuplicatesII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.size() == k + 1)
                set.remove(set.iterator().next());

            if (set.contains(nums[i]))
                return true;

            set.add(nums[i]);
        }
        return false;
    }
}
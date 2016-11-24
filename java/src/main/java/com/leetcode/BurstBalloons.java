package com.leetcode;

import java.util.*;

/**
 * 312. Burst Balloons
 *
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number
 * on it represented by array nums. You are asked to burst all the balloons. If
 * the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of i. After the burst, the left and
 * right then becomes adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Note:
 * (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you
 * can not burst them.
 * (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * Example:
 *
 * Given [3, 1, 5, 8]
 *
 * Return 167
 *
 * nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class BurstBalloons {
    /**
     * DP approach
     *
     * Reverse thinking:
     *   start from the last balloon to burst, its left, right boundary are fixed
     *   then divide and conquer, decide 2nd-last balloon to burst from left and right respectively
     *   use memorization to record max result can get from a range (start, end)
     *
     *
     * @link: https://discuss.leetcode.com/topic/30746/share-some-analysis-and-explanations
     */
    public int maxCoins(int[] nums) {
        return maxCoinsInFromRange(nums, -1, nums.length, new HashMap<>());
    }

    private int maxCoinsInFromRange(int[] nums, int start, int end, Map<Integer, Integer> map) {
        if (start == end) return 0;

        int key = getKey(nums.length, start, end);
        if (!map.containsKey(key)) {
            int max = 0;
            for (int i = start + 1; i < end; ++i) {
                int left = maxCoinsInFromRange(nums, start, i, map);
                int right = maxCoinsInFromRange(nums, i, end, map);
                int curr = getCoins(nums, i, start, end);
                max = Math.max(max, left + curr + right);
            }
            map.put(key, max);
        }
        return map.get(key);
    }

    private int getCoins(int[] nums, int i, int start, int end) {
        int left = start == -1 ? 1 : nums[start];
        int right = end == nums.length ? 1 : nums[end];
        return left * nums[i] * right;
    }

    private int getKey(int n, int start, int end) {
        return start * n + end;
    }

    /**
     * Intuitive but TLE approach
     */
    public int maxCoins_TLE(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums) list.add(num);

        // key: remaining list;  val: max score can get
        Map<List<Integer>, Integer> map = new HashMap<>();
        helper(list, map);
        return map.get(list);
    }

    private int helper(ArrayList<Integer> list, Map<List<Integer>, Integer> map) {
        if (list.isEmpty()) return 0;
        if (!map.containsKey(list)) {
            int max = 0;
            for (int i = 0; i < list.size(); ++i) {
                ArrayList<Integer> subList = (ArrayList<Integer>) list.clone();
                subList.remove(i);
                helper(subList, map);
                int left = i == 0 ? 1 : list.get(i - 1);
                int right = i == list.size() - 1 ? 1 : list.get(i + 1);
                max = Math.max(max, map.get(subList) + left * list.get(i) * right);
            }
            map.put(list, max);
        }
        return map.get(list);
    }
}

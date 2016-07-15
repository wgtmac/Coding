package com.leetcode;

import java.util.TreeMap;

/**
 * 334. Increasing Triplet Subsequence
 *
 Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

 Formally the function should:
 Return true if there exists i, j, k
 such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 Your algorithm should run in O(n) time complexity and O(1) space complexity.

 Examples:
 Given [1, 2, 3, 4, 5],
 return true.

 Given [5, 4, 3, 2, 1],
 return false.
 */
public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int num : nums) {
            int cnt = 0;
            for (Integer lowerKey = map.lowerKey(num); lowerKey != null; lowerKey = map.lowerKey(lowerKey)) {
                cnt = Math.max(cnt, map.get(lowerKey));
            }

            if (map.containsKey(num))
                map.put(num, Math.max(cnt + 1, map.get(num)));
            else
                map.put(num, cnt + 1);

            if (map.get(num) == 3) return true;
        }

        return false;
    }

    public boolean increasingTriplet_SLOW(int[] nums) {

        int[] k = new int[nums.length];  // how many no greater than nums[i]
        for (int i = 0; i < nums.length; ++i) {
            k[i] = 1;

            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    k[i] = Math.max(k[i], k[j] + 1);
                }
                if (k[i] == 3) return true;
            }

        }

        return false;
    }

    public static void main(String[] args) {
        IncreasingTripletSubsequence i = new IncreasingTripletSubsequence();
        System.out.println(i.increasingTriplet(new int[] {1, 2, 3, 4, 5}));
        System.out.println(i.increasingTriplet(new int[] {5, 4, 3, 2, 1}));
    }
}

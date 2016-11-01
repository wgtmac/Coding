package com.leetcode;

import java.util.*;

/**
 * 376. Wiggle Subsequence
 *
 * A sequence of numbers is called a wiggle sequence if the differences between
 * successive numbers strictly alternate between positive and negative. The first
 * difference (if one exists) may be either positive or negative. A sequence with
 * fewer than two elements is trivially a wiggle sequence.
 *
 * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences
 * (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5]
 * and [1,7,4,5,5] are not wiggle sequences, the first because its first two
 * differences are positive and the second because its last difference is zero.
 *
 * Given a sequence of integers, return the length of the longest subsequence
 * that is a wiggle sequence. A subsequence is obtained by deleting some number
 * of elements (eventually, also zero) from the original sequence, leaving the
 * remaining elements in their original order.
 *
 * Examples:
 * Input: [1,7,4,9,2,5]
 * Output: 6
 * The entire sequence is a wiggle sequence.
 *
 * Input: [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
 *
 * Input: [1,2,3,4,5,6,7,8,9]
 * Output: 2
 * Follow up:
 * Can you do it in O(n) time?
 */
public class WiggleSubsequence {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) return nums.length;

        // length -> numbers, with length in descending order.
        TreeMap<Integer, TreeSet<Integer>>
                incMap = new TreeMap<>(Comparator.reverseOrder()),
                decMap = new TreeMap<>(Comparator.reverseOrder());

        // pair[0]: max len of curr num, [1]: curr num
        Integer[] decPair = new Integer[2], incPair = new Integer[2];
        for (int num : nums) {
            decPair[1] = incPair[1] = num;

            // find the longest previous increasing SubSeq;
            // if none, use itself as staring decreasing SubSeq
            decPair[0] = 1;
            for (int length : incMap.keySet()) {
                if (incMap.get(length).higher(num) != null) {
                    decPair[0] = length + 1;
                    break;
                }
            }

            // find the longest previous decreasing SubSeq;
            // if none, use itself as staring increasing SubSeq
            incPair[0] = 1;
            for (int length : decMap.keySet()) {
                if (decMap.get(length).lower(num) != null) {
                    incPair[0] = length + 1;
                    break;
                }
            }

            // don't break computation in the middle
            update(decMap, decPair);
            update(incMap, incPair);
        }

        return Math.max(incMap.firstKey(), decMap.firstKey());

    }

    private void update(Map<Integer, TreeSet<Integer>> map, Integer[] pair) {
        if (!map.containsKey(pair[0]))
            map.put(pair[0], new TreeSet<>());
        map.get(pair[0]).add(pair[1]);
    }

    /**
     * DP solution
     *
     * Idea is if curr one is larger than prev one, it can replace prev for increasing
     * likewise, if curr one is smaller than prev one, it can replace prev for decreasing
     * Merging these two cases can result in the DP as follows.
     */
    public int wiggleMaxLength_DP(int[] nums) {
        if(nums.length <= 1) return nums.length;

        int[] inc = new int[nums.length], dec = new int[nums.length];
        inc[0] = dec[0] = 1;

        for(int i = 1; i < nums.length; i++){
            if (nums[i] > nums[i - 1]){
                inc[i] = dec[i - 1] + 1;
                dec[i] = dec[i - 1];
            } else if (nums[i] < nums[i - 1]){
                dec[i] = inc[i - 1] + 1;
                inc[i] = inc[i - 1];
            } else {
                dec[i] = dec[i - 1];
                inc[i] = inc[i - 1];
            }
        }

        return Math.max(inc[nums.length - 1], dec[nums.length - 1]);
    }

    public static void main(String[] args) {
        WiggleSubsequence w = new WiggleSubsequence();

        int[] nums1 = {1,7,4,9,2,5};
        System.out.println(w.wiggleMaxLength(nums1) == 6);

        int[] nums2 = {1,17,5,10,13,15,10,5,16,8};
        System.out.println(w.wiggleMaxLength(nums2) == 7);

        int[] nums3 = {1,2,3,4,5,6,7,8,9};
        System.out.println(w.wiggleMaxLength(nums3) == 2);
    }
}

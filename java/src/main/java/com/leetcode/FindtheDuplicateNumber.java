package com.leetcode;

/**
 * 287. Find the Duplicate Number
 *
 * Given an array nums containing n + 1 integers where each integer is between
 * 1 and n (inclusive), prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 *
 * Note:
 *   1. You must not modify the array (assume the array is read only).
 *   2. You must use only constant, O(1) extra space.
 *   3. Your runtime complexity should be less than O(n^2).
 *   4. There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class FindtheDuplicateNumber {

    // use binary search to guess the duplicate num
    public int findDuplicate(int[] nums) {
        int start = 1, end = nums.length - 1, mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            int cntNoGreaterThanMid = 0;
            for (int num : nums) {
                if (num <= mid) cntNoGreaterThanMid++;
            }

            if (cntNoGreaterThanMid <= mid)
                start = mid;
            else
                end = mid;
        }

        int cnt = 0;
        for (int num : nums) {
            if (num == start) cnt++;
        }
        return cnt > 1 ? start : end;
    }
}

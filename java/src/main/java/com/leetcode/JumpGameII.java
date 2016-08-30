package com.leetcode;

/**
 * 45. Jump Game II
 * 
 * Given an array of non-negative integers,
 * you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * For example:
 * Given array A = [2,3,1,1,4]
 * 
 * The minimum number of jumps to reach the last index is 2.
 * (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 * 
 * Hint:
 * Greedy: one jump to the furthest
 */
public class JumpGameII {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] range = {0, 0};
        int rightMost = 0, jump = 0;

        while (range[0] <= range[1] && rightMost < nums.length - 1) {
            for (int index = range[0]; index <= range[1]; ++index) {
                rightMost = Math.max(rightMost, index + nums[index]);
            }
            jump += 1;
            range[0] = range[1] + 1;
            range[1] = rightMost;
        }

        return jump;
    }
}

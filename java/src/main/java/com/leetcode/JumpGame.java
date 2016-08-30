package com.leetcode;

/**
 * 55. Jump Game
 * 
 * Given an array of non-negative integers,
 * you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 * 
 * Hint:
 * Greedy
 */
public class JumpGame {

    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        int[] range = {0, 0};
        int rightMost = 0;
        
        while (range[0] <= range[1] && rightMost < nums.length - 1) {
            for (int index = range[0]; index <= range[1]; ++index) {
                rightMost = Math.max(rightMost, index + nums[index]);
            }
            range[0] = range[1] + 1;
            range[1] = rightMost;
        }

        return rightMost >= nums.length - 1;
    }
}

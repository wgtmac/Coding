package com.leetcode;

/**
 * 198. House Robber
 * 
 * You are a professional robber planning to rob houses along a street. Each
 * house has a certain amount of money stashed, the only constraint stopping you
 * from robbing each of them is that adjacent houses have security system connected
 * and it will automatically contact the police if two adjacent houses were broken
 * into on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of each
 * house, determine the maximum amount of money you can rob tonight without alerting
 * the police.
 * 
 * Skill: 
 * 这家不抢最多 = max (隔壁没抢最多， 隔壁抢了最多)
 * 这家抢最多 = 隔壁没抢 +这家的钱
 */

public class HouseRobber {
	public int rob(int[] nums) {
        int left2 = 0, left1 = 0, curr;
        for (int num : nums) {
            curr = Math.max(left2 + num, left1);
            left2 = left1;
            left1 = curr;
        }

        return Math.max(left2, left1);
	}
}

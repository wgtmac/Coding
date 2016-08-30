package com.leetcode;

/**
 * 53. Maximum Subarray
 * 
 * Find the contiguous subarray within an array (containing at least one number)
 * which has the largest sum.
 * 
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 * 
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution
 * using the divide and conquer approach, which is more subtle.
 * 
 * Skill:
 * 连续求和，小于零则置0重新算
 * 
 * 可以分治，左右的max和当前点往前后延伸的max
 * 
 * OR DP:f(k) = max( f(k-1) + A[k], A[k] )  保留局部到当前k的最大
 */
public class MaximumSubarray {
    public int maxSubArray(int[] A) {
        if (A == null || A.length == 0) return 0;
        
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            max = Math.max(max, sum);
            if (sum < 0) sum = 0;
        }
        
        return max;
    }
}

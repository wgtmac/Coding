package com.leetcode;

/**
 * 164. Maximum Gap
 * 
 * Given an unsorted array, find the maximum difference between the successive
 * elements in its sorted form. Try to solve it in linear time/space. Return 0
 * if the array contains less than 2 elements. You may assume all elements in
 * the array are non-negative integers and fit in the 32-bit signed integer range.
 * 
 * Skill: 
 * 令Max和Min為A中的最大值和最小值。令d = (Max – Min)/(n-1)。
 * 我們可以把所有在[Min, Max]中的數字分成n-1個區間，第k的區間包含所有[Min + (k-1)d, Min + kd)之間的數字。
 * 亦即把元素A[i]放到第k個區間如果floor((A[i]-Min)/(n-1)) = k。
 * 因為除了Max和Min之外還有n-2個數字且有n-1個區間，所以至少有一個區間是空的，
 * 因此如果有兩個元素在同一個區間，那麼這兩個元素絕對不會是答案。
 * 所以針對每一個區間我們只要儲存最大值和最小值即可，
 * 接著循序的掃描所有區間，就可以得到一個已排序的數列，
 * 在此數列中，連續元素的最大差值即為所求。
 */
public class MaximumGap {
	private static class Data {
		int min, max;

		Data (int x) {
			min = max = x;
		}

		void update (int x) {
			min = Math.min(x, min);
			max = Math.max(max, x);
		}
	}

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        
        int maxNum = Integer.MIN_VALUE, minNum = Integer.MAX_VALUE;
        for (int num : nums) {
        	maxNum = Math.max(maxNum, num);
        	minNum = Math.min(minNum, num);
        }
        
        int bucketWidth = (maxNum - minNum) / (nums.length - 1) + 1;
        int bucketCount = (maxNum - minNum) / bucketWidth + 1;
        Data[] data = new Data[bucketCount];

        for (int num : nums) {
            int idx = (num - minNum) / bucketWidth;
            if (data[idx] == null)
                data[idx] = new Data(num);
            else
                data[idx].update(num);
        }

        int maxGap = data[0].max - data[0].min, prevMax = data[0].max;
        for (int i = 1; i < data.length; i++) {
        	if (data[i] != null) {
        		maxGap = Math.max(maxGap, data[i].min - prevMax);
        		prevMax = data[i].max;
        	}
        }
        
        return maxGap;
    }
}

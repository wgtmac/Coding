package com.leetcode;

/**
 * 11. Container With Most Water
 * 
 * DESCRIPTION:
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container.
 * 
 * Hint:
 * 两个指针 一左一右
 * 哪边短就先移哪边，因为不然的话，移动长的下一次只会面积减小	，因此把当前小的排除
 * 贪心算法
 */

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
    	if (height == null || height.length <= 1) return 0;
    	
    	int max = 0;
    	int i = 0, j = height.length - 1;
    	while (i < j) {
    	    max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
    	    if (height[i] < height[j]) {
    	        i++;
    	    } else {
    	        j--;
    	    }
    	}
    	
    	return max;
    }
}

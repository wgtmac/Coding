package com.leetcode;

/**
 * 96. Unique Binary Search Trees
 * 
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 * 
 * Skill:
 * 让每个数做一次root，递归计算左右子树可能的排列情况
 */

import java.util.HashMap;

public class UniqueBinarySearchTrees {
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	
    public int numTrees(int n) {
        if (n == 0) return 0;
    	return helper(0, n - 1);
    }
    
    public int helper (int start, int end) {
        if (start - end == 1 || start == end) return 1;

        if (map.containsKey(end - start + 1))
            return map.get(end - start + 1);

        int sum = 0;
        for (int i = start; i <= end; i++)
            sum += helper(start,i - 1) * helper(i + 1, end);
        map.put(end - start + 1, sum);

        return sum;
    }

    public int numTrees_Iter (int n) {
        int[] count = new int[n + 1];
        count[0] = 1;
        count[1] = 1;
        
        for(int i = 2; i <= n; i++){
            for(int j = 0; j < i; j++){
                count[i] += count[j] * count[i - j - 1];
            }
        }
        return count[n];
    }
}

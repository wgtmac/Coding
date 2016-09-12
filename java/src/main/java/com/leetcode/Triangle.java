package com.leetcode;

/**
 * 120. Triangle
 * 
 * Given a triangle, find the minimum path sum from top to bottom.
 * Each step you may move to adjacent numbers on the row below.
 *
 * For example, given the following triangle
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 *
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space,
 * where n is the total number of rows in the triangle.
 * 
 * Skill: 
 * 倒着算 每一层最小由下一层往上算
 * */

import java.util.List;

public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null) return 0;
        
        int[] sum = new int[triangle.size()];
        for (int i = 0; i < triangle.get(triangle.size() - 1).size(); i++) {
            sum[i] = triangle.get(triangle.size() - 1).get(i);
        }
        
        for(int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                sum[j] = Math.min(sum[j] + triangle.get(i).get(j), sum[j + 1] + triangle.get(i).get(j));
            }
        }
        
        return sum[0];
    }
}

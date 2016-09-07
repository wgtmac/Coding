package com.leetcode;

import java.util.List;

/**
 * 240. Search a 2D Matrix II
 * 
 * Write an efficient algorithm that searches for a value in an m x n matrix,
 * return the occurrence of it.
 * This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * Integers in each column are sorted from up to bottom.
 * No duplicate integers in each row or column. [[THIS ASSUMPTION IS IMPORTANT]]
 * 
 * Example
 * Consider the following matrix:
 * [
 *     [1, 3, 5, 7],
 *     [2, 4, 7, 8],
 *     [3, 5, 9, 10]
 * ]
 * Given target = 3, return 2.
 * Challenge:  O(m+n) time and O(1) extra space
 */

public class SearchA2DMatrixII {
    int searchMatrix(List<List<Integer>> matrix, int target)  {
        if (matrix == null || matrix.size() == 0 || matrix.get(0).size() == 0)
            return 0;

        int count = 0;
        // start from top right
        int i = 0, j = matrix.get(0).size() - 1;
        while (i != matrix.size() && j != -1) {
        	if (matrix.get(i).get(j) > target)
        	    j--;   // move left
        	else if (matrix.get(i).get(j) < target)
        	    i++;   // move down
        	else {
        		count++;
        		j--;  // or i++, just follow the previous order
        	}
        }
        return count;
    }
}

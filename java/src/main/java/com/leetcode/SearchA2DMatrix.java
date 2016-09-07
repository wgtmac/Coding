package com.leetcode;

/**
 * 74. Search a 2D Matrix
 * 
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * For example,
 * 
 * Consider the following matrix:
 * [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * Given target = 3, return true.
 *  
 * 可以当1D来做
 */
public class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length, cols = matrix[0].length;
        int start = 0, end = rows * cols - 1, mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            // Tricks to treat it as a 1-D array
            int num = matrix[mid / cols][mid % cols];
            if (target == num)
                return true;
            else if (target > num)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return false;
    }
}

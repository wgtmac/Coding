package com.leetcode;

/**
 * 54. Spiral Matrix
 * 
 * Given a matrix of m x n elements (m rows, n columns),
 * return all elements of the matrix in spiral order.
 * For example,
 * Given the following matrix:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5]
 * 
 * Skill: 
 * 按顺序写进去 层层递进
 * 注意行or列为奇数需要单独处理
 * */

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
    	List<Integer> list = new ArrayList<>();
    	if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
    	    return list;
    	
    	int row = matrix.length, col = matrix[0].length;

        // only process even rows & cols
    	for (int i = 0; i < Math.min(row / 2, col / 2); ++i) {
    		// upper row
    		for (int j = i; j < col - i; ++j)
    			list.add(matrix[i][j]);
    		
    		// right col
    		for (int j = i + 1; j < row - 1 - i; ++j)
    			list.add(matrix[j][col - 1 - i]);
    		
    		// bottom row
    		for (int j = (col - 1) - i; j >= i; --j)
    			list.add(matrix[row - 1 - i][j]);
    		
    		// left col
    		for (int j = (row - 1) - 1 - i; j > i; --j)
    			list.add(matrix[j][i]);
    	}

    	/**
         *  XXXXXXXX
         *  XOOOOOOX
         *  XXXXXXXX
         * */
    	// for the remaining odd row or col
    	if (row == col) {
    		if (row % 2 != 0)
        		list.add(matrix[row / 2][col / 2]);
    	} else {
        	if (row % 2 != 0) {
        		for (int j = row / 2; j < col - row / 2; j++)
        			list.add(matrix[row / 2][j]);
        	}

        	if (col % 2 != 0) {
        		for (int j = col / 2; j < row - col / 2; j++)
        			list.add(matrix[j][col / 2]);
        	}
    	}
    	
    	return list;
    }
}

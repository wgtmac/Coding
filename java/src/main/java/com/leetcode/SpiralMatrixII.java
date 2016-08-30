package com.leetcode;

/**
 * 59. Spiral Matrix II
 * 
 * Given an integer n, generate a square matrix filled with elements
 * from 1 to n^2 in spiral order.
 *
 * For example,
 * Given n = 3,
 * You should return the following matrix:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 * 
 * Skill: 
 * 按顺序写进去 层层递进
 * 注意行or列为奇数需要单独处理
 * */

public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int num = 1;
        int[][] matrix = new int[n][n];
        
        if (n <= 0) return matrix;
        
    	for (int i = 0; i < n / 2; i++) {
    		// upper row
    		for (int j = i; j < n - i; j++)
    			matrix[i][j] = num++;

    		// right col
    		for (int j = i + 1; j < n - 1 - i; j++)
    			matrix[j][n - 1 - i] = num++;

    		// bottom row
    		for (int j = n - i - 1; j >= i; j--)
    			matrix[n - 1 - i][j] = num++;
    		
    		// left col
    		for (int j = n - 2 - i; j > i; j--)
    			matrix[j][i] = num++;
    	}
    	
    	if (n % 2 != 0)
    		matrix[n / 2][n / 2] = num;
    	
        return matrix;
    }
}

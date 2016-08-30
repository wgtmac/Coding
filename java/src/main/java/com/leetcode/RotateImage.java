package com.leetcode;

/**
 * 48. Rotate Image
 * 
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * Follow up: Could you do this in-place?
 * 
 * Skill: 从外向内依次旋转，注意每次最右边点不需要转了
 */

public class RotateImage {
    public void rotate(int[][] matrix) {
    	if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
    		return;
    	int n = matrix.length;
    	
    	int tmp;
        for (int i = 0; i < n / 2; i++) {
        	for (int j = 0; j < n - 2 * i - 1; j++) {
            	tmp = matrix[i][i + j];
            	matrix[i][i + j] = matrix[n - 1 - i - j][i];
            	matrix[n - 1 - i - j][i] = matrix[n - 1 - i][n - 1 - i - j];
            	matrix[n - 1 - i][n - 1 - i - j] = matrix[i + j][n - 1 - i];
            	matrix[i + j][n - 1 - i] = tmp;
        	}
        }
    }
}

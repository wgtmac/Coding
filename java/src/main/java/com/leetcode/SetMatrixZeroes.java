package com.leetcode;

/**
 * 73. Set Matrix Zeroes
 * 
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0.
 * Do it in place.
 * 
 * Follow up:
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 * 
 * Skill: 
 * 0，0标记第一行和第一列
 * 第一行标记成0表示该列需要置0
 * 第一列元素标记同理，表示某一行需要置0
 * */
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
    	if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
    		return;
    	
    	int flag = 0;
    	
    	// 2 = col, 1 = row  3 = col & row
    	if (matrix[0][0] != 0) {
    		for (int i = 1; i < matrix[0].length; i++) {
    			if (matrix[0][i] == 0) {
    				flag += 1;
    				break;
    			}
    		}
    		for (int i = 1; i < matrix.length; i++) {
    			if (matrix[i][0] == 0) {
    				flag += 2;
    				break;
    			}
    		}
    	} else {
    		flag = 3;
    	}
    	
        for (int i = 1; i < matrix.length; i++) {
        	for (int j = 1; j < matrix[0].length; j++) {
        		if (matrix[i][j] == 0) {
        			matrix[i][0] = 0;
        			matrix[0][j] = 0;
        		}
        	}
        }
        
        for (int i = 1; i < matrix.length; i++) {
        	for (int j = 1; j < matrix[0].length; j++) {
        		if (matrix[i][0] == 0 || matrix[0][j] == 0) {
        			matrix[i][j] = 0;
        		}
        	}
        }
        
        if (flag % 2 != 0) {
    		for (int i = 1; i < matrix[0].length; i++) 
    			matrix[0][i] = 0;
        }
        if (flag > 1) {
    		for (int i = 1; i < matrix.length; i++) 
    			matrix[i][0] = 0;
        }
        if (flag != 0) {
            matrix[0][0] = 0;
        }
    }
}

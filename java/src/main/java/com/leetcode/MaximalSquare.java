package com.leetcode;

/**
 * 221. Maximal Square
 *
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest square containing all 1's and return its area.
 *
 * For example, given the following matrix:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 4.
 */

public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int[] rowOnes = new int[matrix.length];
        int[] colOnes = new int[matrix[0].length];
        int[][] maxEdgeToHere = new int[matrix.length][matrix[0].length];
        int max = 0;
		for (int i = 0; i < matrix.length; ++i) {
        	for (int j = 0; j < matrix[0].length; ++j) {
        		if (i == 0)
        			colOnes[j] = matrix[i][j] - '0';
        		else
        			colOnes[j] = (matrix[i][j] - '0') * (colOnes[j] + 1);

        		if (j == 0)
        			rowOnes[i] = matrix[i][j] - '0';
        		else
        			rowOnes[i] = (matrix[i][j] - '0') * (rowOnes[i] + 1);

                if (matrix[i][j] == '1') {
                    maxEdgeToHere[i][j] = 1;
                    if (i > 0 && j > 0)
                        maxEdgeToHere[i][j] = Math.min(maxEdgeToHere[i - 1][j - 1] + 1,
                                Math.min(colOnes[j], rowOnes[i]));
                    else
                        maxEdgeToHere[i][j] = 1;
                }
        		max = Math.max(max, maxEdgeToHere[i][j]);
        	}
        }

        return max * max;
    }
}
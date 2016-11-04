package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 311. Sparse Matrix Multiplication
 *
 * Given two sparse matrices A and B, return the result of AB.
 *
 * You may assume that A's column number is equal to B's row number.
 *
 * Example:
 *
 * A = [
 *       [ 1, 0, 0],
 *       [-1, 0, 3]
 *     ]
 *
 * B = [
 *       [ 7, 0, 0 ],
 *       [ 0, 0, 0 ],
 *       [ 0, 0, 1 ]
 *     ]
 *
 *
 *      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 *                   | 0 0 1 |
 */
public class SparseMatrixMultiplication {
    public int[][] multiply(int[][] A, int[][] B) {
        Map<Integer, Map<Integer, Integer>> a = new HashMap<>(), b = new HashMap<>();

        // init left matrix
        for (int i = 0; i < A.length; ++i) {
            Map<Integer, Integer> row = new HashMap<>();
            a.put(i, row);
            for (int j = 0; j < A[0].length; ++j) {
                if (A[i][j] != 0)
                    row.put(j, A[i][j]);
            }
        }

        // init right matrix
        for (int j = 0; j < B[0].length; ++j) {
            Map<Integer, Integer> col = new HashMap<>();
            b.put(j, col);
            for (int i = 0; i < B.length; ++i) {
                if (B[i][j] != 0)
                    col.put(i, B[i][j]);
            }
        }

        int[][] ans = new int[A.length][B[0].length];
        for (int i = 0; i < ans.length; ++i) {
            for (int j = 0; j < ans[0].length; ++j) {
                Map<Integer, Integer> left = a.get(i), right = b.get(j);
                for (Map.Entry<Integer, Integer> lEntry : left.entrySet()) {
                    if (right.containsKey(lEntry.getKey())) {
                        ans[i][j] += lEntry.getValue() * right.get(lEntry.getKey());
                    }
                }
            }
        }

        return ans;
    }
}

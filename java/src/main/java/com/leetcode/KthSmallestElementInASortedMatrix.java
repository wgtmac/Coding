package com.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 378. Kth Smallest Element in a Sorted Matrix
 *
 * Given a n x n matrix where each of the rows and columns are sorted in ascending
 * order, find the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth
 * distinct element.
 *
 * Example:
 *
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 *
 * return 13.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ n^2.
 */
public class KthSmallestElementInASortedMatrix {

    private static class Unit {
        int num, i, j;
        Unit(int n, int r, int c) {
            num = n;
            i = r;
            j = c;
        }
    }

    // O(k*logn)
    public int kthSmallest(int[][] matrix, int k) {
        // must guarantee that identical numbers are picked up from smaller
        // indices first
        PriorityQueue<Unit> pq = new PriorityQueue<>(new Comparator<Unit>() {
            @Override
            public int compare(Unit o1, Unit o2) {
                if (o1.num != o2.num) {
                    return o1.num - o2.num;
                } else if (o1.i != o2.i) {
                    return o1.i - o2.i;
                } else {
                    return o1.j - o2.j;
                }
            }
        });

        int row = matrix.length, col = matrix[0].length;
        int[] indices = new int[row];
        Arrays.fill(indices, -1);

        pq.offer(new Unit(matrix[0][0], 0, 0));
        while (k-- > 1) {
            Unit unit = pq.poll();
            if (unit.i + 1 < row && indices[unit.i + 1] < unit.j) {
                pq.offer(new Unit(matrix[unit.i + 1][unit.j], unit.i + 1, unit.j));
                indices[unit.i + 1] = unit.j;
            }
            if (unit.j + 1 < col && indices[unit.i] < unit.j + 1) {
                pq.offer(new Unit(matrix[unit.i][unit.j + 1], unit.i, unit.j + 1));
                indices[unit.i] = unit.j + 1;
            }
        }

        return pq.peek().num;
    }
}

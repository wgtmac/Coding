package com.leetcode;

/**
 * 88. Merge Sorted Array
 * 
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * 
 * Note:
 * You may assume that A has enough space (size that is greater or equal to m + n)
 * to hold additional elements from B.
 * The number of elements initialized in A and B are m and n respectively.
 */

public class MergeSortedArray {
    public void merge(int A[], int m, int B[], int n) {
        if (A == null || B == null) return;
		
		int idx = m + n - 1;
		int i = m - 1, j = n - 1;
		while (i != -1 && j != -1) {
			if (A[i] > B[j]) 
				A[idx--] = A[i--];
			else 
				A[idx--] = B[j--];
		}
		
		if (j == -1) return;
		while (j != -1) {
			A[idx--] = B[j--];
		}
    }
}

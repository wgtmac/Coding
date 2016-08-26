package com.leetcode;

/**
 * 26. Remove Duplicates from Sorted Array
 * 
 * DESCRIPTION:
 * Given a sorted array, remove the duplicates in place such that
 * each element appear only once and return the new length.
 *
 * Do not allocate extra space for another array,
 * you must do this in place with constant memory.
 * 
 * For example,
 * Given input array A = [1,1,2],
 * 
 * Your function should return length = 2, and A is now [1,2].
 * 
 * Skill:
 * 记下当前写到哪里了--index
 */
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        } else if (A.length == 1) {
            return 1;
        }
        
        int len = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] != A[len - 1]) {
                A[len++] = A[i];
            }
        }
        
        return len;
    }
}

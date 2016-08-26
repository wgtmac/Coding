package com.leetcode;

/**
 *
 * 4. Median of Two Sorted Arrays
 *
 * DESCRIPTION:
 * There are two sorted arrays A and B of size m and n respectively. 
 * Find the median of the two sorted arrays. 
 * The overall run time complexity should be O(log (m+n)).
 * 
 * Skill:
 * 二分
 * 写出找第k小的数 
 * k从1开始
 * 
 * 注意：如果出现上下两个array长度很不一样 导致取前k/2个时某个溢出，则短的那个不动 因为一定在长的里面
 * 
 */
public class MedianofTwoSortedArrays {
    public double findMedianSortedArrays(int A[], int B[]) {
        if (A == null || B == null || A.length + B.length == 0) {
            return 0.0f;
        }

        int len = A.length + B.length;
        if (len % 2 == 0) {
            return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1))/ 2.0;
        }
        else {
            return findKth(A, 0, B, 0, len / 2 + 1);
        }
    }

    public int findKth(int A[], int startA, int B[], int startB, int k) {
        if (startA >= A.length) {
            return B[startB + k - 1];
        }
        if (startB >= B.length) {
            return A[startA + k - 1];
        }

        if (k == 1) {
            return ((A[startA] < B[startB]) ? A[startA] : B[startB]);
        }

        int valueA = (startA + k / 2 - 1 < A.length) ? A[startA + k / 2 - 1] : Integer.MAX_VALUE;
        int valueB = (startB + k / 2 - 1 < B.length) ? B[startB + k / 2 - 1] : Integer.MAX_VALUE;

        if (valueA < valueB) {
            return findKth(A, startA + k / 2, B, startB, k - k / 2);
        }
        else {
            return findKth(A, startA, B,startB + k / 2, k - k / 2);
        }
    }

    /**
     * An iterative solution
     * */
    public double findMedianSortedArrays_iterative(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        if ((len % 2) == 0)
            return (findK(nums1, nums2, len / 2) +
                    findK(nums1, nums2, len / 2 + 1)) / 2;
        else
            return findK(nums1, nums2, len / 2 + 1);
    }

    private double findK(int[] nums1, int[] nums2, int k) {
        int i1 = 0, i2 = 0, mid1, mid2, offset;

        while (true) {
            if (i1 < nums1.length && i2 < nums2.length) {
                if (k == 1)
                    return Math.min(nums1[i1], nums2[i2]);

                offset = k / 2 - 1;
                mid1 = Math.min(i1 + offset, nums1.length - 1);
                mid2 = Math.min(i2 + offset, nums2.length - 1);

                if (nums1[mid1] <= nums2[mid2]) {
                    k -= mid1 - i1 + 1;  // avoid infinite loop, skip mid
                    i1 = mid1 + 1;
                } else if (nums1[mid1] > nums2[mid2]) {
                    k -= mid2 - i2 + 1;
                    i2 = mid2 + 1;
                }
            } else if (i1 < nums1.length) {
                return nums1[i1 + k - 1];
            } else { // i2 < len2
                return nums2[i2 + k - 1];
            }
        }
    }
}

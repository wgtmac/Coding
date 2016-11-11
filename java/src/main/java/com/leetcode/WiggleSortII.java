package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 324. Wiggle Sort II
 *
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 *
 * Example:
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 *
 * Note:
 * You may assume all input has valid answer.
 *
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 */
public class WiggleSortII {

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int quickFindK(int[] nums, int k, int start, int end) {
        if (start == end) return nums[start];

        int l = start, r = end + 1;
        int num = nums[start];
        while (true) {
            while (nums[++l] < num) if (l == end) break;
            while (nums[--r] > num) if (r == start) break;
            if (l >= r) break;
            swap(nums, l, r);
        }

        swap(nums, start, r);

        if (k == r) {
            return nums[k];
        } else if (k > r) {
            return quickFindK(nums, k, r + 1, end);
        } else {
            return quickFindK(nums, k, start, r - 1);
        }
    }

    public void wiggleSort(int[] nums) {
        int median = quickFindK(nums, (nums.length + 1) / 2 - 1, 0, nums.length - 1);
        int oddIndex = 1;
        // put small number reversely, avoid duplicate median numbers to be together
        int evenIndex = nums.length % 2 == 0 ? nums.length - 2 : nums.length - 1;
        int[] tmpArr = new int[nums.length];

        for(int i = 0; i < nums.length; i++){
            if(nums[i] > median){
                tmpArr[oddIndex] = nums[i];
                oddIndex += 2;
            } else if(nums[i] < median){
                tmpArr[evenIndex] = nums[i];
                evenIndex -= 2;
            }
        }

        for (; oddIndex < nums.length; oddIndex += 2){
            tmpArr[oddIndex] = median;
        }
        for (; evenIndex >= 0; evenIndex -= 2){
            tmpArr[evenIndex] = median;
        }

        System.arraycopy(tmpArr, 0, nums, 0, nums.length);
    }

    public static void main(String[] args) {
        WiggleSortII w = new WiggleSortII();

        int[] nums1 = new int[] {1, 5, 1, 1, 6, 4};
        w.wiggleSort(nums1);
        System.out.println(Arrays.toString(nums1));

        int[] nums2 = new int[] {1, 3, 2, 2, 3, 1};
        w.wiggleSort(nums2);
        System.out.println(Arrays.toString(nums2));

        int[] nums3 = new int[] {4,5,5,6};
        w.wiggleSort(nums3);
        System.out.println(Arrays.toString(nums3));

        int[] nums4 = new int[] {4,5,5,5,5,6,6,6};
        w.wiggleSort(nums4);
        System.out.println(Arrays.toString(nums4));
    }
}

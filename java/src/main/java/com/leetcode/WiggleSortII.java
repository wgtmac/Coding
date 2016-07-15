package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 324. Wiggle Sort II
 *
 Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

 Example:
 (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

 Note:
 You may assume all input has valid answer.

 Follow Up:
 Can you do it in O(n) time and/or in-place with O(1) extra space?
 */
public class WiggleSortII {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);

        // 5:1  4:0
        int offset = nums.length % 2;
        // 5: (1,2)  4:(0,1)
        int[] lowest = Arrays.copyOfRange(nums, offset, nums.length / 2 + offset);
        // 5:(3,4)   4:(2,3)
        int[] highest = Arrays.copyOfRange(nums, nums.length / 2 + offset, nums.length);

        reorder(nums, offset == 0 ? lowest : highest, offset == 1 ? lowest : highest, offset);
        checkAndShift(nums);
    }

    private void reorder(int[] nums, int[] first, int[] second, int offset) {
        int i = offset, j = 0;
        while (j < first.length) {
            nums[i++] = first[j];
            nums[i++] = second[j++];
        }
    }

    private void checkAndShift(int[] nums) {
        if (nums.length > 3 && nums.length % 2 == 0) {
            for (int i = 1; i < nums.length && i + 1 < nums.length; i += 2) {
                if (nums[i] == nums[i + 1]) {
                    Queue<Integer> queue = new LinkedList<>();

                    int j = 0, k = 0;
                    for (; j <= i; ++j)
                        queue.offer(nums[j]);
                    for (; j < nums.length; ++j, ++k)
                        nums[k] = nums[j];
                    for (; k < nums.length; ++k)
                        nums[k] = queue.poll();
                    break;
                }
            }
        }
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

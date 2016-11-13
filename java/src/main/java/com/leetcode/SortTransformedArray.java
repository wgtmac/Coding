package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 360 Sort Transformed Array
 *
 * Given a sorted array of integers nums and integer values a, b and c. Apply
 * a function of the form f(x) = ax^2 + bx + c to each element x in the array.
 *
 * The returned array must be in sorted order.
 *
 * Expected time complexity: O(n)
 *
 * Example:
 * nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
 *
 * Result: [3, 9, 15, 33]
 *
 * nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
 *
 * Result: [-23, -5, 1, 7]
 */
public class SortTransformedArray {
    int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            nums[i] = a * num * num + b * num + c;
        }

        int[] list = new int[nums.length];
        int start = 0, end = nums.length - 1;
        int idx = a >= 0 ? nums.length - 1 : 0;
        while (start <= end) {
            if (a >= 0) {
                if (nums[start] >= nums[end])
                    list[idx--] = nums[start++];
                else
                    list[idx--] = nums[end--];
            } else {
                if (nums[start] < nums[end])
                    list[idx++] = nums[start++];
                else
                    list[idx++] = nums[end--];
            }
        }

        return list;
    }
}

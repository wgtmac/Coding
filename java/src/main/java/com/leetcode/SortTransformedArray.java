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
    List<Integer> sortTransformedArray(List<Integer> nums, int a, int b, int c) {
        for (int i = 0; i < nums.size(); ++i) {
            int num = nums.get(i);
            nums.set(i, a * num * num + b * num + c);
        }

        List<Integer> list = new ArrayList<>(nums.size());
        for (int i = 0; i < nums.size(); ++i)
            list.add(0);
        int start = 0, end = nums.size() - 1;
        int idx = a >= 0 ? nums.size() - 1 : 0;
        while (start <= end) {
            if (a >= 0) {
                if (nums.get(start) >= nums.get(end))
                    list.set(idx--, nums.get(start++));
                else
                    list.set(idx--, nums.get(end--));
            } else {
                if (nums.get(start) < nums.get(end))
                    list.set(idx++, nums.get(start++));
                else
                    list.set(idx++, nums.get(end--));
            }
        }

        return list;
    }

    public static void main(String[] args) {
        SortTransformedArray s = new SortTransformedArray();
        List<Integer> nums = Arrays.asList(-4, -2, 2, 4);
        System.out.println(s.sortTransformedArray(nums, 1, 3, 5));

        nums = Arrays.asList(-4, -2, 2, 4);
        System.out.println(s.sortTransformedArray(nums, -1, 3, 5));

        nums = Arrays.asList(-4, -2, 2, 4);
        System.out.println(s.sortTransformedArray(nums, 0, 3, 5));
    }
}

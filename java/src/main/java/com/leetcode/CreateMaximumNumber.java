package com.leetcode;

import java.util.*;

/**
 * 321. Create Maximum Number
 *
 * Given two arrays of length m and n with digits 0-9 representing two numbers.
 * Create the maximum number of length k <= m + n from digits of the two. The
 * relative order of the digits from the same array must be preserved. Return an
 * array of the k digits. You should try to optimize your time and space complexity.
 *
 * Example 1:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * return [9, 8, 6, 5, 3]
 *
 * Example 2:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * return [6, 7, 6, 0, 4]
 *
 * Example 3:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * return [9, 8, 9]
 */
public class CreateMaximumNumber {

    /**
     * 1. Create the maximum number of one array
     * 2. Merge the maximum number of two array using all of their digits
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        int[] ans = new int[k];
        for (int i = Math.max(0, k - m); i <= k && i <= n; ++i) {
            int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i), k);
            if (greater(candidate, 0, ans, 0))
                ans = candidate;
        }
        return ans;
    }

    /**
     * Cannot just compare one digit from two arrays when tied
     * e.g. [6,7] and [6,0,4], we must choose 6 from 1st array first when
     * choosing 1st digit
     */
    private int[] merge(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        for (int i = 0, j = 0, r = 0; r < k; ++r)
            ans[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        return ans;
    }

    private boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++; j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }

    /**
     * Select k digits from nums.length to compose largest number
     *
     * Greedy, make the front digit as large as possible
     *   if find larger one later, pop the smaller ones in stack if it is sure
     *   we have enough digits not processed;
     *   otherwise, push to stack as we cannot find enough digits
     */
    private int[] maxArray(int[] nums, int k) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; ++i) {
            while (!stack.isEmpty() && nums.length - i + stack.size() > k
                    && stack.peek() < nums[i]) {
                stack.pop();
            }
            if (stack.size() < k)
                stack.push(nums[i]);
        }

        int i = 0;
        int[] ans = new int[k];
        for (int num : stack)
            ans[i++] = num;
        return ans;
    }

    private int[] maxArray_faster(int[] nums, int k) {
        int[] stack = new int[k];
        for (int i = 0, top = 0; i < nums.length; ++i) {
            while (nums.length - i + top > k && top > 0 && stack[top - 1] < nums[i])
                top--;

            if (top < k)
                stack[top++] = nums[i];
        }
        return stack;
    }

    /**
     * Naive O(n^2*k) DP solution
     */
    public int[] maxNumber_TLE(int[] nums1, int[] nums2, int k) {

        // f[i][j][k]: nums1(len i) and nums2(len j) construct max num of length k
        String[][][] f = new String[nums1.length + 1][nums2.length + 1][k + 1];
        for (int i = 0; i < f.length; ++i) {
            f[i] = new String[nums2.length + 1][k + 1];
            for (int j = 0; j < f[i].length; ++j) {
                f[i][j] = new String[k + 1];
                Arrays.fill(f[i][j], "");
            }
        }

        for (int len = 1; len <= k; ++len) {
            for (int i = 0; i < f.length; ++i) {
                for (int j = 0; j < f[i].length; ++j) {
                    if (i != 0) {
                        // use curr i
                        String cand = f[i - 1][j][len - 1] + nums1[i - 1];
                        if (f[i][j][len].compareTo(cand) < 0) {
                            f[i][j][len] = cand;
                        }
                        // compare prev len
                        if (f[i][j][len].compareTo(f[i - 1][j][len]) < 0) {
                            f[i][j][len] = f[i - 1][j][len];
                        }
                    }

                    if (j != 0) {
                        // use curr j
                        String cand = f[i][j - 1][len - 1] + nums2[j - 1];
                        if (f[i][j][len].compareTo(cand) < 0) {
                            f[i][j][len] = cand;
                        }
                        // compare prev len
                        if (f[i][j][len].compareTo(f[i][j - 1][len]) < 0) {
                            f[i][j][len] = f[i][j - 1][len];
                        }
                    }
                }
            }
        }

        char[] digits = f[nums1.length][nums2.length][k].toCharArray();

        int[] result = new int[digits.length];
        for (int i = 0; i < result.length; ++i)
            result[i] = digits[i] - '0';

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {6, 7};
        int[] nums2 = {6, 0, 4};
        int k = 5;
        CreateMaximumNumber c = new CreateMaximumNumber();
        System.out.println(Arrays.toString(c.maxNumber(nums1, nums2, k)));
    }
}
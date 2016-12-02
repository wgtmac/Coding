package com.leetcode;


/**
 * 330. Patching Array
 *
 * Given a sorted positive integer array nums and an integer n, add/patch elements
 * to the array such that any number in range [1, n] inclusive can be formed by
 * the sum of some elements in the array. Return the minimum number of patches
 * required.
 *
 * Example 1:
 * nums = [1, 3], n = 6
 * Return 1.
 *
 * Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 * Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 * Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 * So we only need 1 patch.
 *
 * Example 2:
 * nums = [1, 5, 10], n = 20
 * Return 2.
 * The two patches can be [2, 4].
 *
 * Example 3:
 * nums = [1, 2, 2], n = 5
 * Return 0.
 */

/**
 * A greedy solution
 * @link: https://discuss.leetcode.com/topic/35494/solution-explanation
 *
 */
public class PatchingArray {
    public static void main(String[] args) {
        PatchingArray p = new PatchingArray();
        System.out.println(p.minPatches(new int[] {1, 3}, 6));
        System.out.println(p.minPatches(new int[] {1, 5, 10}, 20));
        System.out.println(p.minPatches(new int[] {1, 2, 2}, 5));
    }

    public int minPatches(int[] nums, int n) {
        // initial range is [0, 1), after each iteration, the current guaranteed
        // range is [0, miss). For next iteration, the updated range is
        // [0, miss + nums[i]) when nums[i] < miss; otherwise miss should be patched
        long miss = 1;
        int patches = 0;
        int i = 0;        // current index in nums, which is sorted
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i++];
            } else {
                miss += miss;
                patches++;
            }
        }
        return patches;
    }
}

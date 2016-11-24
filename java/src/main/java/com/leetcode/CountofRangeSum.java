package com.leetcode;

/**
 * 327. Count of Range Sum
 *
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
 *
 * Note:
 * A naive algorithm of O(n^2) is trivial. You MUST do better than that.
 *
 * Example:
 * Given nums = [-2, 5, -1], lower = -2, upper = 2,
 * Return 3.
 * The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
 */
public class CountofRangeSum {
    /**
     * Approaches:
     * 1. Similar to "Count of Smaller Numbers After Self"
     * 2. Merge-sort based,
     *      1) compute valid ranges in left and right half,
     *      2) then compute valid ranges across left & right
     * 3. BIT
     *
     * @link: https://huntzhan.org/leetcode-count-of-range-sum/
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        TreeNode root = updateTree(null, 0);
        long sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            // lower <= sum - root.val <= upper
            // sum - upper <= root.val <= sum - lower
            count += getSmallerCount(root, sum - lower + 1) - getSmallerCount(root, sum - upper);
            root = updateTree(root, sum);
        }
        return count;
    }

    // maintain a BST with node count info
    private TreeNode updateTree(TreeNode root, long num) {
        if (root == null) return new TreeNode(num);
        if (root.val == num)     root.count++;
        else if (num < root.val) root.left = updateTree(root.left, num);
        else                     root.right = updateTree(root.right, num);
        root.totalCount = root.count
                + (root.left == null ? 0 : root.left.totalCount)
                + (root.right == null ? 0 : root.right.totalCount);
        return root;
    }

    private int getSmallerCount(TreeNode root, long upperBound) {
        int count = 0;
        while (root != null) {
            if (upperBound == root.val) {
                if (root.left != null)
                    count += root.left.totalCount;
                root = null;
            } else if (upperBound < root.val) {
                root = root.left;
            } else {
                if (root.left != null)
                    count += root.left.totalCount;
                count += root.count;
                root = root.right;
            }
        }
        return count;
    }

    private static class TreeNode {
        long val;
        int count, totalCount;   // count of curr val and all values in subtree
        TreeNode left = null, right = null;
        TreeNode(long num) {
            val = num;
            count = totalCount = 1;
        }
    }

    // TLE solution: O(n^2)
    public int countRangeSum_TLE(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            if (i == 0)
                sum[i] = nums[i];
            else
                sum[i] = sum[i - 1] + nums[i];
        }

        int cnt = 0;
        long rangeSum = 0;
        for (int end = 0; end < nums.length; ++end) {
            for (int start = 0; start <= end; ++start) {
                if (start == 0)
                    rangeSum = sum[end];
                else
                    rangeSum = sum[end] - sum[start - 1];
                if (lower <= rangeSum && rangeSum <= upper)
                    cnt++;
            }
        }
        return cnt;
    }
}

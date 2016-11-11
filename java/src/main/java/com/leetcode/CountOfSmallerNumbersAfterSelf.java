package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 315. Count of Smaller Numbers After Self
 *
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller
 * elements to the right of nums[i].
 *
 * Example:
 *
 * Given nums = [5, 2, 6, 1]
 *
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Return the array [2, 1, 1, 0].
 *
 * Indexed BST
 */
public class CountOfSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);

        TreeNode root = null;
        for (int i = nums.length - 1; i >= 0; --i) {
            list.add(getSmallerCount(root, nums[i]));
            root = updateTree(root, nums[i]);
        }

        Collections.reverse(list);
        return list;
    }

    // maintain a BST with node count info
    private TreeNode updateTree(TreeNode root, int num) {
        if (root == null) return new TreeNode(num);

        if (root.value == num)
            root.count++;
        else if (num < root.value)
            root.left = updateTree(root.left, num);
        else
            root.right = updateTree(root.right, num);

        root.totalCount = root.count + (root.left == null ? 0 : root.left.totalCount)
                + (root.right == null ? 0 : root.right.totalCount);

        return root;
    }

    private int getSmallerCount(TreeNode root, int num) {
        int count = 0;
        while (root != null) {
            if (num == root.value) {
                if (root.left != null)
                    count += root.left.totalCount;
                root = null;
            } else if (num < root.value) {
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
        int value, count, totalCount;   // count of curr value and all values in subtree
        TreeNode left = null, right = null;
        TreeNode(int num) {
            value = num;
            count = totalCount = 1;
        }
    }
}

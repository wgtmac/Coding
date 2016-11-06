package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 272. Closest Binary Search Tree Value II
 *
 * Given a non-empty binary search tree and a target value, find k values in the
 * BST that are closest to the target.
 *
 * Note:
 *   1. Given target value is a floating point.
 *   2. You may assume k is always valid, that is: k ≤ total nodes.
 *   3. You are guaranteed to have only one unique set of k values in the BST that
 *      are closest to the target.
 *
 *   Follow up:
 *   Assume that the BST is balanced, could you solve it in less than O(n) runtime
 *   (where n = total nodes)?
 */
public class ClosestBinarySearchTreeValueII {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> inorder = new ArrayList<>(), ans = new ArrayList<>();
        List<Integer> smaller = new ArrayList<>(), greater = new ArrayList<>();
        visit(root, inorder);

        for (int num : inorder) {
            if (num == target)     ans.add(num);
            else if (num < target) smaller.add(num);
            else                   greater.add(num);
        }
        Collections.reverse(smaller);

        // merge results
        int s = 0, g = 0;
        while (ans.size() < k) {
            if (s < smaller.size() && g < greater.size()) {
                ans.add( Math.abs(target - smaller.get(s)) <= Math.abs(target - greater.get(g)) ?
                        smaller.get(s++) : greater.get(g++) );
            } else if (s < smaller.size()) {
                ans.add(smaller.get(s++));
            } else {
                ans.add(greater.get(g++));
            }
        }

        return ans;
    }

    private void visit(TreeNode root, List<Integer> inorder) {
        if (root != null) {
            visit(root.left, inorder);
            inorder.add(root.val);
            visit(root.right, inorder);
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

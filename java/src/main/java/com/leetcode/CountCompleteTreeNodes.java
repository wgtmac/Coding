package com.leetcode;

import java.util.Map;
import java.util.Stack;

/**
 * 222. Count Complete Tree Nodes
 *
 * Given a complete binary tree, count the number of nodes.
 *
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled,
 * and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.
*/

public class CountCompleteTreeNodes {
    /**
     *               1
     *        2             3
     *     4    5       6      7
     *   8  9 10  11 12  13 14  15
     *   so total = 2^h - 1
     * */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        if (leftDepth == rightDepth) {
            return 1 + countNodes(root.right) + (int)Math.pow(2, rightDepth - 1);
        } else {
            return 1 + countNodes(root.left) +(int)Math.pow(2, rightDepth - 1);
        }
    }

    private int getDepth(TreeNode root) {
        int h = 0;
        for (; root != null; root = root.left) h++;
        return h;
    }

    /**
     * Another solution
     */
    public int countNodes_another(TreeNode root) {
        if (root == null) return 0;
        TreeNode node = root;
        int h = 0;
        for (; node != null; node = node.left) h++;
        int start = (int)Math.pow(2, h - 1), end = start * 2 - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (!exist(root, mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }

        boolean existsStart = exist(root, start);
        boolean existsEnd = (start == end ? existsStart : exist(root, end));
        if (!existsStart) return start - 1;
        else if (!existsEnd) return start;
        else return end;
    }

    private boolean exist(TreeNode root, int target) {
        Stack<Boolean> stack = new Stack<>();
        while (target != 1) {
            stack.push(target % 2 == 0);
            target >>= 1;
        }

        while (!stack.isEmpty() && root != null) {
            if (stack.pop())
                root = root.left;
            else
                root = root.right;
        }

        return root != null;
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
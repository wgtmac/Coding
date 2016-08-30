package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. Binary Tree Paths
 *
 * Given a binary tree, return all root-to-leaf paths.
 *
 * For example, given the following binary tree:
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * All root-to-leaf paths are:
 *
 * ["1->2->5", "1->3"]
 * */


public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        helper(root, "", list);
        return list;
    }

    private void helper(TreeNode root, String path, List<String> list) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                list.add(buildPath(path, root.val));
            } else {
                String nextPath = buildPath(path, root.val);
                if (root.left != null) {
                    helper(root.left, nextPath, list);
                }

                if (root.right != null) {
                    helper(root.right, nextPath, list);
                }
            }
        }
    }

    private String buildPath(String path, int val) {
        if (path.isEmpty()) {
            return Integer.toString(val);
        } else {
            return path + "->" + val;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

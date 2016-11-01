package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 366. Find Leaves of Binary Tree
 *
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect
 * and remove all leaves, repeat until the tree is empty.
 *
 * Example:
 * Given binary tree
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 * Returns [4, 5, 3], [2], [1].
 *
 * Explanation:
 * 1. Removing the leaves [4, 5, 3] would result in this tree:
 *
 *   1
 *  /
 * 2
 *
 * 2. Now removing the leaf [2] would result in this tree:
 *
 *   1
 *
 * 3. Now removing the leaf [1] would result in the empty tree:
 *
 * []
 *
 * Returns [4, 5, 3], [2], [1].
 */
public class FindLeavesOfBinaryTree {

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        visit(root, ans);
        return ans;
    }

    // @return bottom-up level, start from 0
    private int visit(TreeNode node, List<List<Integer>> ans) {
        if (node == null)
            return 0;

        int reverseDepth = Math.max(visit(node.left, ans),
                visit(node.right, ans));

        if (ans.size() <= reverseDepth)
            ans.add(new ArrayList<>());
        ans.get(reverseDepth).add(node.val);

        return reverseDepth + 1;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

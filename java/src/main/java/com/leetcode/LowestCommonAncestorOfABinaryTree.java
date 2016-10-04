package com.leetcode;

/**
 * 236. Lowest Common Ancestor of a Binary Tree
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes
 * in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor
 * is defined between two nodes v and w as the lowest node in T that has both v
 * and w as descendants (where we allow a node to be a descendant of itself).”
 *
 * _______3______
 * /              \
 * ___5__          ___1__
 * /      \        /      \
 * 6      _2       0       8
 * /  \
 * 7   4
 * For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another
 * example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of
 * itself according to the LCA definition.
 */
public class LowestCommonAncestorOfABinaryTree {
    private int FIND_P = 0x01;
    private int FIND_Q = 0x02;
    private int FIND_ALL = 0x03;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode[] ancestor = new TreeNode[1];
        helper(root, p, q, ancestor);
        return ancestor[0];
    }

    private int helper (TreeNode root, TreeNode p, TreeNode q, TreeNode[] ancestor) {
        if (root == null) return 0;
        int state = 0;
        if (root == p) {
            state = FIND_P;
        } else if (root == q) {
            state = FIND_Q;
        }

        state |= helper(root.left, p, q, ancestor);
        state |= helper(root.right, p, q, ancestor);

        if (state == FIND_ALL && ancestor[0] == null) {
            ancestor[0] = root;
        }
        return state;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

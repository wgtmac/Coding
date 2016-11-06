package com.leetcode;

/**
 * 285. Inorder Successor in BST
 *
 * Given a binary search tree and a node in it,
 * find the in-order successor of that node in the BST.
 *
 * Note: If the given node has no in-order successor in the tree, return null.
 */
public class InorderSuccessorinBST {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p.right != null) {
            // find left-most leaf node in the right sub-tree
            p = p.right;
            while (p.left != null) p = p.left;
            return p;
        } else {
            // find parent of p and parent.left == p
            TreeNode parent = null;
            while (root != null) {
                if (root == p) break;
                parent = root;
                if (root.val < p.val)
                    root = root.right;
                else
                    root = root.left;
            }

            if (parent != null && parent.left == p)
                return parent;
            else
                return null;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

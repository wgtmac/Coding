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

        TreeNode successor = null;

        while (root != null) {
            if (root.val < p.val) {
                root = root.right;
            } else if (root.val > p.val) {
                successor = root;   // has the potential to be successor
                root = root.left;
            } else {   // root.val == p.val
                root = root.right;
            }
        }

        return successor;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

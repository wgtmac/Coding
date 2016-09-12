package com.leetcode;

/**
 * 114. Flatten Binary Tree to Linked List
 * 
 * Given a binary tree, flatten it to a linked list in-place.
 * For example,
 * Given
 *
 *          1
 *         / \
 *        2   5
 *       / \   \
 *      3   4   6
 * The flattened tree should look like:
 *    1
 *     \
 *      2
 *       \
 *        3
 *         \
 *          4
 *           \
 *            5
 *             \
 *              6
 *
 *
 * Hints:
 * If you notice carefully in the flattened tree,
 * each node's right child points to the next node of a pre-order traversal.
 * 
 * Skill: 
 *  递归，把左右两边都变成符合要求的树
 *  再把左边的插入node与node.right之间
 */

public class FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        makeItRight(root);
    }
    
    // return the rightmost node of this tree
    private TreeNode makeItRight(TreeNode node) {
        if (node == null) return null;
        
        TreeNode left = makeItRight(node.left);
        TreeNode right = makeItRight(node.right);
        
        if (left != null && right != null) {
            left.right = node.right;
            node.right = node.left;
            node.left = null;
            return right;
        } else if (left != null) {
            node.right = node.left;
            node.left = null;
            return left;
        } else if (right != null) {
            return right;
        } else {
            return node;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

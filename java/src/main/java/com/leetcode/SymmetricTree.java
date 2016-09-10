package com.leetcode;

/**
 * 101. Symmetric Tree
 * 
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * But the following is not:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 * 
 * Hint:
 * 对树进行level-order遍历
 * 树的一行所有node放入队列，判断是不是首尾对称
 * 若对称，将这一行非null的结点左右child依次放入队列（child若为null也需要放入队列，用特殊dummy node代替）
 */

import java.util.LinkedList;

public class SymmetricTree {
	private static class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return helper(root.left, root.right);
    }

    private boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return left.val == right.val && helper(left.left, right.right) &&
                helper(left.right, right.left);
    }

    public boolean isSymmetric_Iterative(TreeNode root) {
        if (root == null) return true;
        
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0, j = size - 1; i < j; i++, j--) {
                if (queue.get(i).val != queue.get(j).val) {
                    return false;
                }
            }
            
            for(int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                if (tmp != null) {
                    TreeNode tmpL = tmp.left != null ? tmp.left : null;
                    TreeNode tmpR = tmp.right != null ? tmp.right : null;
                    queue.offer(tmpL);
                    queue.offer(tmpR);
                }
            }
        }
        
        return true;
    }
}

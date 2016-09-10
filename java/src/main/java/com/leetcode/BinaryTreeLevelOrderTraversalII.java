package com.leetcode;

/**
 * 107. Binary Tree Level Order Traversal II
 * 
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (ie, from left to right, level by level from leaf to root).
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *  * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 * 
 * Skill: 
 * 用一个队列，存每一层的node
 */

import java.util.*;

public class BinaryTreeLevelOrderTraversalII {
	private static class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
	
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int size;
        while (!queue.isEmpty()) {
            size = queue.size();
            List<Integer> levelList = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = queue.peek();
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
                levelList.add(queue.poll().val);
            }
            list.add(levelList);
        }

        Collections.reverse(list);
        return list;
    }

}

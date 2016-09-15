package com.leetcode;

/**
 * 145. Binary Tree Postorder Traversal
 * 
 * DESCRIPTION:
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * 
 * Skill: 
 * 几种情况：
 * prev为空---当前点为root，有孩子push孩子没孩子就不动 (只push一个 优先级左>右)
 * prev为node的parent---在下降，有孩子push孩子没孩子就不动
 * prev为node的左孩子---在回退，有右孩子就push，没有就不动
 * prev为node的右孩子---在回退，输出
 * prev为node自己，输出
 * 
 * 下降就push，刚pop左孩子就push右孩子，否则pop
 * */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<> ();
		Stack<TreeNode> stack = new Stack<> ();

		if (root == null) return list;
		
		TreeNode node = root, prev = null;
		stack.push(node);

		while (!stack.empty()) {
			node = stack.peek();
			
			// root
			if (prev == null) {
				if (node.left != null)
				    stack.push(node.left);
				else if (node.right != null)
				    stack.push(node.right);
			} else if (node == prev.left || node == prev.right) {  /* Down */
				if (node.left != null)
				    stack.push(node.left);
				else if (node.right != null)
				    stack.push(node.right);
				else {
					// it is leaf, peek needs to be popped
				}
			} else if (prev == node.left) {  /* UP with left child visited */
				if (node.right != null)
				    stack.push(node.right);
				else {
					// no right child, peek needs to be popped
				}
			} else if (prev == node.right) {  /* UP with all children visited */
				node = stack.pop();
				list.add(node.val);
			} else if (prev == node) {   /* STAY to be popped */
				node = stack.pop();
				list.add(node.val);
			}
				
			prev = node;
		}
		
		return list;
    }

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}

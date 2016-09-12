package com.leetcode;

/**
 * 117. Populating Next Right Pointers in Each Node II
 * 
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * Note:
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \    \
 *     4-> 5 -> 7 -> NULL
 * 
 * Skill: 
 * 每个node，先把自己左右相连
 * 因为自己要先跟同level右边连起来，所以需要先遍历右边，再左边
 */
public class PopulatingNextRightPointersinEachNodeII {
	public void connect(TreeLinkNode root) {
		if (root == null) return;
        // root level is ready
        TreeLinkNode right = nextRightNode(root.next);
		if (root.left != null && root.right != null) {
			root.left.next = root.right;
			root.right.next = right;
		} else if (root.left != null) {
			root.left.next = right;
		} else if (root.right != null)  {
			root.right.next = right;
		}
        connect(root.right);
        connect(root.left);
	}

	private TreeLinkNode nextRightNode(TreeLinkNode node) {
	    while (node != null && node.left == null && node.right == null)
	        node = node.next;
        if (node != null)
            node = node.left != null ? node.left : node.right;
        return node;
    }

	private static class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;
		TreeLinkNode(int x) { val = x; }
	}
}

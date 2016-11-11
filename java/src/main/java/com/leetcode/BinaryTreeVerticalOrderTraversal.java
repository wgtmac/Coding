package com.leetcode;

import java.util.*;

/**
 * 314. Binary Tree Vertical Order Traversal
 *
 * Given a binary tree, return the vertical order traversal of its nodes' values.
 * (ie, from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Examples:
 *
 * Given binary tree [3,9,20,null,null,15,7],
 *    3
 *   /\
 *  /  \
 * 9   20
 *     /\
 *    /  \
 *   15   7
 *
 * return its vertical order traversal as:
 * [
 *  [9],
 *  [3,15],
 *  [20],
 *  [7]
 * ]
 *
 * Given binary tree [3,9,8,4,0,1,7],
 *      3
 *     /\
 *    /  \
 *   9   8
 *  /\   /\
 * /  \ /  \
 * 4  0 1   7
 *
 * return its vertical order traversal as:
 * [
 *   [4],
 *   [9],
 *   [3,0,1],
 *   [8],
 *   [7]
 * ]
 *
 * Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5]
 * (0's right child is 2 and 1's left child is 5),
 *         3
 *        /\
 *       /  \
 *      9   8
 *     / \  /\
 *    /  \/  \
 *    4  01   7
 *       / \
 *      /  \
 *     5    2
 *
 * return its vertical order traversal as:
 * [
 *   [4],
 *   [9,5],
 *   [3,0,1],
 *   [8,2],
 *   [7]
 * ]
 */
public class BinaryTreeVerticalOrderTraversal {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();

        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Integer> indexQ = new LinkedList<Integer>() {{offer(0);}};
        Queue<TreeNode> nodeQ = new LinkedList<TreeNode>() {{offer(root);}};

        while (!nodeQ.isEmpty()) {
            int size = nodeQ.size();
            while (size-- > 0) {
                int index = indexQ.poll();
                TreeNode node = nodeQ.poll();

                if (!map.containsKey(index))
                    map.put(index, new ArrayList<>());
                map.get(index).add(node.val);

                if (node.left != null) {
                    nodeQ.offer(node.left);
                    indexQ.offer(index - 1);
                }
                if (node.right != null) {
                    nodeQ.offer(node.right);
                    indexQ.offer(index + 1);
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (List<Integer> list : map.values())
            ans.add(list);
        return ans;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

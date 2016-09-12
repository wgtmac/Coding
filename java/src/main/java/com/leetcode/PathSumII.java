package com.leetcode;

/**
 * 113. Path Sum II
 * 
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * 
 * For example:
 * Given the below binary tree and sum = 22,
 *   5
 *   / \
 *   4   8
 *   /   / \
 *   11  13  4
 *   /  \    / \
 *   7    2  5   1
 * return
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */

import java.util.ArrayList;
import java.util.List;

public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        helper(root, sum, 0, new ArrayList<>(), ret);
        return ret;
    }
    
	private void helper(TreeNode root, int sum, int currSum,
                        List<Integer> list, List<List<Integer>> ret) {
        if (root.left == null && root.right == null) {
            if(currSum + root.val == sum) {
                list.add(root.val);
                ret.add((List<Integer>) ((ArrayList<Integer>)list).clone());
                list.remove(list.size() - 1);
                return;
            }
        }
        
        list.add(root.val);
        if (root.left != null)
            helper(root.left, sum, currSum + root.val, list, ret);

        if (root.right != null)
            helper(root.right, sum, currSum + root.val, list, ret);
        list.remove(list.size() - 1);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

package com.leetcode;

/**
 * 95. Unique Binary Search Trees II
 * 
 * Given n, generate all structurally unique BST's
 * (binary search trees) that store values 1...n.
 * 
 * Skill: 
 * 找规律
 * 每次算好n-1的
 * 然后把以前每个结果
 * 1）作为左child  2）对每个右节点，break it，用当前点替换并把之后的部分作为当前的左child
 * 
 * */

import java.util.*;

public class UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        Map<String, List<TreeNode>> map = new HashMap<>();
        return generate(1, n, map);
    }
    
    private List<TreeNode> generate(int start, int end,
                                    Map<String, List<TreeNode>> map){
        if(start > end){
            ArrayList<TreeNode> trees = new ArrayList<>();
            trees.add(null);
            return trees;
        }

        String key = start + "-" + end;
        if (!map.containsKey(key)) {
            List<TreeNode> trees = new ArrayList<>();
            for(int i = start; i <= end; i++){
                List<TreeNode> left = generate(start, i - 1, map);
                List<TreeNode> right = generate(i + 1, end, map);
                for(TreeNode l : left) {
                    for(TreeNode r : right) {
                        TreeNode root = new TreeNode(i);
                        root.left = l;
                        root.right = r;
                        trees.add(root);
                    }
                }
            }
            map.put(key, trees);
        }

        return map.get(key);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

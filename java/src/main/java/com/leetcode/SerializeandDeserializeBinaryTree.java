package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 297. Serialize and Deserialize Binary Tree
 *
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 *
 * For example, you may serialize the following tree
 *
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree.
 * You do not necessarily need to follow this format, so please be creative and
 * come up with different approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. Your
 * serialize and deserialize algorithms should be stateless.
 */
public class SerializeandDeserializeBinaryTree {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            List<String> list = new ArrayList<>();
            visit(root, list);
            return String.join("/", list);
        }

        private void visit(TreeNode root, List<String> list) {
            if (root == null)
                list.add("#");
            else {
                list.add(Integer.toString(root.val));
                visit(root.left, list);
                visit(root.right, list);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] datas = data.split("/");
            int[] index = {0};
            return build(datas, index);
        }

        private TreeNode build(String[] data, int[] index) {
            if (index[0] >= data.length) return null;

            if (data[index[0]].equals("#")) {
                index[0]++;  // visited
                return null;
            }

            TreeNode root = new TreeNode(Integer.valueOf(data[index[0]++]));

            root.left = build(data, index);
            root.right = build(data, index);
            return root;
        }
    }
}

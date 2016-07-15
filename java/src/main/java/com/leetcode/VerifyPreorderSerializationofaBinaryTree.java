package com.leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * 331. Verify Preorder Serialization of a Binary Tree
 *
 One way to serialize a binary tree is to use pre-order traversal.
 When we encounter a non-null node, we record the node's value.
 If it is a null node, we record using a sentinel value such as #.

 _9_
 /   \
 3     2
 / \   / \
 4   1  #  6
 / \ / \   / \
 # # # #   # #

 For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#",
 where # represents a null node.

 Given a string of comma separated values,
 verify whether it is a correct preorder traversal serialization of a binary tree.
 Find an algorithm without reconstructing the tree.

 Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

 You may assume that the input format is always valid,
 for example it could never contain two consecutive commas such as "1,,3".

 Example 1:
 "9,3,4,#,#,1,#,#,2,#,6,#,#"
 Return true

 Example 2:
 "1,#"
 Return false

 Example 3:
 "9,#,#,1"
 Return false
 */
public class VerifyPreorderSerializationofaBinaryTree {
    public boolean isValidSerialization(String preorder) {
        List<String> list = new ArrayList<>();
        for (String node : preorder.split(",")) {
            list.add(node);

            int size = list.size();
            while (size >= 3 &&
                    list.get(size - 1).equals("#") &&
                    list.get(size - 2).equals("#") &&
                    !list.get(size - 3).equals("#")) {
                list.remove(size - 1);
                list.remove(size - 2);
                list.remove(size - 3);
                list.add("#");
                size = list.size();
            }
        }

        return list.size() == 1 && list.get(0).equals("#");
    }


    public static void main(String[] args) {
        VerifyPreorderSerializationofaBinaryTree v = new VerifyPreorderSerializationofaBinaryTree();
        System.out.println(v.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        System.out.println(v.isValidSerialization("1,#"));
        System.out.println(v.isValidSerialization("9,#,#,1"));
    }
}

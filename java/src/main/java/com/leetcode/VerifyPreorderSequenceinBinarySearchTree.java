package com.leetcode;

/**
 * 255. Verify Preorder Sequence in Binary Search Tree
 *
 * Given an array of numbers, verify whether it is the correct preorder traversal
 * sequence of a binary search tree.
 *
 * You may assume each number in the sequence is unique.
 *
 * Follow up:
 * Could you do it using only constant space complexity?
 */
public class VerifyPreorderSequenceinBinarySearchTree {
    public boolean verifyPreorder(int[] preorder) {
        return visit(preorder, 0, preorder.length - 1);
    }

    private boolean visit(int[] preorder, int start, int end) {
        if (start >= end) return true;

        int l = start, r = end + 1;

        // l will be the first one greater than preorder[start];
        // or the last num which is smaller than preorder[start]
        while (preorder[start] > preorder[++l]) if (l == end) break;

        // r will be the first one smaller than preorder[start]
        // or the last num which is greater than preorder[start]
        while (preorder[start] < preorder[--r]) if (l == r) break;

        if (l != r) return false;

        if (preorder[l] < preorder[start]) {
            return visit(preorder, start + 1, l);
        } else {
            return visit(preorder, start + 1, l - 1) && visit(preorder, l, end);
        }
    }

    /**
     * Kinda simulate the traversal, keeping a stack of nodes (just their values)
     * of which we're still in the left subtree. If the next number is smaller
     * than the last stack value, then we're still in the left subtree of all
     * stack nodes, so just push the new one onto the stack. But before that,
     * pop all smaller ancestor values, as we must now be in their right subtrees
     * (or even further, in the right subtree of an ancestor). Also, use the
     * popped values as a lower bound, since being in their right subtree means
     * we must never come across a smaller number anymore.
     */
    public boolean verifyPreorder_O1_Space(int[] preorder) {
        int low = Integer.MIN_VALUE, i = -1;
        for (int p : preorder) {
            if (p < low)
                return false;
            while (i >= 0 && p > preorder[i])
                low = preorder[i--];
            preorder[++i] = p;
        }
        return true;
    }
}

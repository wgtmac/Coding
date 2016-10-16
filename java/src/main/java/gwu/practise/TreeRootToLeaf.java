package gwu.practise;

import java.util.Stack;

/**
 * Print all paths from root to leaf without recursion.
 */
public class TreeRootToLeaf {

    private static class TreeNode {
        int val;
        TreeNode left, right;
    }

    public static void path(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null, curr = null;
        stack.push(root);

        while (!stack.isEmpty()) {
            curr = stack.peek();
            if (prev == null || curr == prev.left || curr == prev.right) {
                if (curr.left != null) stack.push(curr.left);
                else if (curr.right != null) stack.push(curr.right);
            } else if (curr.left == prev) {
                if (curr.right != null) stack.push(curr.right);
            } else {
                if (curr.left == null && curr.right == null) {
                    // is leaf node, path the stack for root to leaf
                    System.out.println(stack);
                }
                stack.pop();
            }
            prev = curr;
        }
    }
}

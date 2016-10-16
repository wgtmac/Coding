package gwu.practise;

import java.util.*;

/**
 * Created by wgtmac on 10/12/16.
 */
public class BSTRelated {

    private static class TreeNode {
        int val;
        TreeNode left, right;
    }

    List<Integer> inorderRecursion(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list;
    }

    private void helper(TreeNode root, List<Integer> list) {
        if (root == null) return;
        helper(root.left, list);
        list.add(root.val);
        helper(root.right, list);
    }

    List<Integer> inorderIteration(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                list.add(stack.peek().val);
                root = stack.pop().right;
            }
        }
        return list;
    }

    class BSTIterator implements Iterator<Integer> {
        TreeNode root;
        Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            this.root = root;
            stack = new Stack<>();
            refresh();
        }

        private void refresh() {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Integer next() {
            TreeNode node = stack.pop();
            root = node.right;
            refresh();
            return node.val;
        }
    }

    class TwoBSTIterator implements Iterator<Integer> {

        TreeNode bst1, bst2;
        Stack<TreeNode> stack1, stack2;

        public TwoBSTIterator(TreeNode bst1, TreeNode bst2) {
            this.bst1 = bst1;
            this.bst2 = bst2;
            stack1 = new Stack<>();
            stack2 = new Stack<>();
            refresh();
        }

        private void refresh() {
            while (bst1 != null) {
                stack1.push(bst1);
                bst1 = bst1.left;
            }
            while (bst2 != null) {
                stack2.push(bst2);
                bst2 = bst2.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack1.isEmpty() || !stack2.isEmpty();
        }

        @Override
        public Integer next() {
            int value;
            if (stack1.isEmpty()) {
                bst2 = stack2.peek().right;
                value = stack2.pop().val;
            } else if (stack2.isEmpty()) {
                bst1 = stack1.peek().right;
                value = stack1.pop().val;
            } else {
                if (stack1.peek().val <= stack2.peek().val) {
                    bst1 = stack1.peek().right;
                    value = stack1.pop().val;
                } else {
                    bst2 = stack2.peek().right;
                    value = stack2.pop().val;
                }
            }
            refresh();
            return value;
        }
    }

    class NBSTIterator implements Iterator<Integer> {

        PriorityQueue<Stack<TreeNode>> minPQ = new PriorityQueue<>(new Comparator<Stack<TreeNode>>() {
            @Override
            public int compare(Stack<TreeNode> o1, Stack<TreeNode> o2) {
                return Integer.compare(o1.peek().val, o2.peek().val);
            }
        });

        public NBSTIterator(TreeNode[] bsts) {
            for (int i = 0; i < bsts.length; ++i) {
                Stack<TreeNode> stack = new Stack<>();
                refresh(bsts[i], stack);
                minPQ.offer(stack);
            }
        }

        private void refresh(TreeNode root, Stack<TreeNode> stack) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !minPQ.isEmpty();
        }

        @Override
        public Integer next() {
            int value = minPQ.peek().peek().val;

            Stack<TreeNode> stack = minPQ.poll();
            TreeNode root = stack.pop();
            refresh(root.right, stack);
            if (!stack.isEmpty()) {
                minPQ.offer(stack);
            }
            return value;
        }
    }


}

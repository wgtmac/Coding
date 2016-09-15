package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 352. Data Stream as Disjoint Intervals
 *
 * Given a data stream input of non-negative integers a1, a2, ..., an, ...,
 * summarize the numbers seen so far as a currList of disjoint intervals.
 *
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ...,
 * then the summary will be:
 *
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 *
 * Follow up:
 * What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
 */
public class DataStreamasDisjointIntervals {

    private static class SummaryRanges {

        private static class IntervalTreeNode {
            IntervalTreeNode left = null, right = null;
            Interval value;

            IntervalTreeNode(int start, int end) {
                value = new Interval(start, end);
            }
        }

        // construct interval tree
        private IntervalTreeNode add(IntervalTreeNode root, int num) {
            if (root == null)
                return new IntervalTreeNode(num, num);

            if (num < root.value.start) {
                IntervalTreeNode left = add(root.left, num);
                IntervalTreeNode rightMost = getRightMost(left);

                while (rightMost != null && rightMost.value.end + 1 == root.value.start) {
                    // merge left
                    root.value.start = rightMost.value.start;
                    left = removeRightMost(left);
                    rightMost = getRightMost(left);
                }

                root.left = left;
            } else if (num > root.value.end) {
                IntervalTreeNode right = add(root.right, num);
                IntervalTreeNode leftMost = getLeftMost(right);

                while (leftMost != null && leftMost.value.start - 1 == root.value.end) {
                    // merge right
                    root.value.end = leftMost.value.end;
                    right = removeLeftMost(right);
                    leftMost = getLeftMost(right);
                }

                root.right = right;
            }

            return root;
        }

        private IntervalTreeNode getLeftMost(IntervalTreeNode root) {
            if (root == null) return null;
            while (root.left != null)
                root = root.left;
            return root;
        }

        private IntervalTreeNode getRightMost(IntervalTreeNode root) {
            if (root == null) return null;
            while (root.right != null)
                root = root.right;
            return root;
        }

        private IntervalTreeNode removeLeftMost(IntervalTreeNode root) {
            if (root == null) return null;
            IntervalTreeNode node = root, prev = null;
            while (node.left != null) {
                prev = node;
                node = node.left;
            }

            IntervalTreeNode newRoot = root;
            if (prev != null)
                prev.left = node.right;
            else
                newRoot = root.right;
            return newRoot;
        }

        private IntervalTreeNode removeRightMost(IntervalTreeNode root) {
            if (root == null) return null;
            IntervalTreeNode node = root, prev = null;
            while (node.right != null) {
                prev = node;
                node = node.right;
            }

            IntervalTreeNode newRoot = root;
            if (prev != null)
                prev.right = node.left;
            else
                newRoot = root.left;
            return newRoot;
        }

        private IntervalTreeNode root;

        /** Initialize your data structure here. */
        public SummaryRanges() {
            root = null;
        }

        public void addNum(int val) {
            root = add(root, val);
        }

        public List<Interval> getIntervals() {
            List<Interval> list = new ArrayList<>();
            inorder(root, list);
            return list;
        }

        private void inorder(IntervalTreeNode root, List<Interval> list) {
            if (root != null) {
                inorder(root.left, list);
                list.add(root.value);
                inorder(root.right, list);
            }
        }

        private static class Interval {
            int start;
            int end;
            Interval() { start = 0; end = 0; }
            Interval(int s, int e) { start = s; end = e; }

            @Override
            public String toString() {
                return "[" + start + ", " + end + "]";
            }
        }
    }

    /**
     * Your SummaryRanges object will be instantiated and called as such:
     * SummaryRanges obj = new SummaryRanges();
     * obj.addNum(val);
     * List<Interval> param_2 = obj.getIntervals();
     */
    public static void main(String[] args) {
        SummaryRanges obj = new SummaryRanges();
        obj.addNum(1);
        System.out.println(obj.getIntervals());
        obj.addNum(3);
        System.out.println(obj.getIntervals());
        obj.addNum(7);
        System.out.println(obj.getIntervals());
        obj.addNum(2);
        System.out.println(obj.getIntervals());
        obj.addNum(6);
        System.out.println(obj.getIntervals() + "\n");

        SummaryRanges obj2 = new SummaryRanges();
        obj2.addNum(1);
        System.out.println(obj2.getIntervals());
        obj2.addNum(9);
        System.out.println(obj2.getIntervals());
        obj2.addNum(2);
        System.out.println(obj2.getIntervals());
    }
}

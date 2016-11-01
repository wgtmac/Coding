package com.leetcode;

/**
 * 307. Range Sum Query - Mutable
 *
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * The update(i, val) function modifies nums by updating the element at index i to val.
 * Example:
 * Given nums = [1, 3, 5]
 *
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * Note:
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is distributed evenly.
 */

public class RangeSumQueryMutable {

    /**
     * Segment tree array implementation
     *
     * For Binary Indexed Tree:
     * http://www.geeksforgeeks.org/binary-indexed-tree-range-updates-point-queries/
     * https://discuss.leetcode.com/topic/31599/java-using-binary-indexed-tree-with-clear-explanation
     */
    private static class NumArray {

        private int[] segmentTree;
        private int left, right;

        // return sum of the range
        private int build(int[] nums, int start, int end, int index) {
            if (start == end) {
                segmentTree[index] = nums[start];
            } else {
                int mid = start + (end - start) / 2;
                segmentTree[index] = build(nums, start, mid, index * 2 + 1) +
                        build(nums, mid + 1, end, index * 2 + 2);
            }
            return segmentTree[index];
        }

        private int query(int queryStart, int queryEnd, int treeIndex, int rangeStart, int rangeEnd) {
            if (queryStart <= rangeStart && rangeEnd <= queryEnd) {
                return segmentTree[treeIndex];
            }

            int rangeMid = rangeStart + (rangeEnd - rangeStart) / 2;
            if (queryEnd <= rangeMid)
                return query(queryStart, queryEnd, treeIndex * 2 + 1, rangeStart, rangeMid);
            if (queryStart >= rangeMid + 1)
                return query(queryStart, queryEnd, treeIndex * 2 + 2, rangeMid + 1, rangeEnd);

            return query(queryStart, rangeMid, treeIndex * 2 + 1, rangeStart, rangeMid) +
                    query(rangeMid + 1, queryEnd, treeIndex * 2 + 2, rangeMid + 1, rangeEnd);
        }

        private int update(int rangeIndex, int val, int treeIndex, int start, int end) {
            if (start == end && start == rangeIndex) {
                segmentTree[treeIndex] = val;
            } else {
                int mid = start + (end - start) / 2;
                if (rangeIndex <= mid)
                    update(rangeIndex, val, treeIndex * 2 + 1, start, mid);
                else
                    update(rangeIndex, val, treeIndex * 2 + 2, mid + 1, end);

                segmentTree[treeIndex] = segmentTree[2 * treeIndex + 1]
                        + segmentTree[2 * treeIndex + 2];
            }
            return segmentTree[treeIndex];
        }

        public NumArray(int[] nums) {
            if (nums.length > 0) {
                left = 0;
                right = nums.length - 1;
                int height = (int) Math.ceil(Math.log(nums.length) / Math.log(2)) + 1;
                segmentTree = new int[(int) Math.pow(2, height) - 1];
                build(nums, 0, nums.length - 1, 0);
            }
        }

        void update(int i, int val) {
            update(i, val, 0, left, right);
        }

        public int sumRange(int i, int j) {
            return query(i, j, 0, left, right);
        }
    }

    private static class NumArray_SegmentTreeNodeImpl {

        private static class SegmentTreeNode {
            int start, end, val;
            SegmentTreeNode left, right;

            SegmentTreeNode(int s, int e) {
                start = s;
                end = e;
            }
        }

        private SegmentTreeNode build(int[] nums, int start, int end) {
            if (nums.length == 0) return null;
            SegmentTreeNode root = new SegmentTreeNode(start, end);
            if (start == end) {
                root.val = nums[start];
            } else {
                int mid = start + (end - start) / 2;
                root.left = build(nums, start, mid);
                root.right = build(nums, mid + 1, end);
                root.val = root.left.val + root.right.val;
            }
            return root;
        }

        private int query(SegmentTreeNode root, int start, int end) {
            if (root.start == start && root.end == end)
                return root.val;
            if (end <= root.left.end)
                return query(root.left, start, end);
            if (start >= root.right.start)
                return query(root.right, start, end);

            return query(root.left, start, root.left.end) +
                    query(root.right, root.right.start, end);
        }

        private void update(SegmentTreeNode root, int i, int val) {
            if (root.start == root.end && root.start == i) {
                root.val = val;
            } else {
                if (i <= root.left.end)
                    update(root.left, i, val);
                else
                    update(root.right, i, val);

                root.val = root.left.val + root.right.val;
            }
        }

        private SegmentTreeNode root;

        public NumArray_SegmentTreeNodeImpl(int[] nums) {
            root = build(nums, 0, nums.length - 1);
        }

        void update(int i, int val) {
            update(root, i, val);
        }

        public int sumRange(int i, int j) {
            return query(root, i, j);
        }
    }

    // Your NumArray object will be instantiated and called as such:
    // NumArray numArray = new NumArray(nums);
    // numArray.sumRange(0, 1);
    // numArray.update(1, 10);
    // numArray.sumRange(1, 2);
}

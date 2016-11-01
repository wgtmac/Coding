package com.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 364. Nested List Weight Sum II
 *
 * Given a nested list of integers, return the sum of all integers in the list
 * weighted by their depth.
 *
 * Each element is either an integer, or a list -- whose elements may also be
 * integers or other lists.
 *
 * Different from the previous question where weight is increasing from root to
 * leaf, now the weight is defined from bottom up. i.e., the leaf level integers
 * have weight 1, and the root level integers have the largest weight.
 *
 * Example 1:
 * Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
 *
 * Example 2:
 * Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
 */
public class NestedListWeightSumII {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList);

        Stack<Integer> stack = new Stack<>();
        while (!queue.isEmpty()) {
            int size = queue.size(), levelSum = 0;
            for (int i = 0; i < size; ++i) {
                NestedInteger ni = queue.poll();
                if (ni.isInteger())
                    levelSum += ni.getInteger();
                else
                    queue.addAll(ni.getList());
            }
            stack.push(levelSum);
        }

        int sum = 0, depth = 1;
        while (!stack.isEmpty())
            sum += stack.pop() * depth++;
        return sum;
    }

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}

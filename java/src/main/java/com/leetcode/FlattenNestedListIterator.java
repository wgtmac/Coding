package com.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 341. Flatten Nested List Iterator
 *
 * Given a nested currList of integers, implement an iterator to flatten it.
 *
 * Each element is either an integer, or a currList --
 * whose elements may also be integers or other lists.
 *
 * Example 1:
 * Given the currList [[1,1],2,[1,1]],
 * By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,1,2,1,1].
 *
 * Example 2:
 * Given the currList [1,[4,[6]]],
 * By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,4,6].
 */
public class FlattenNestedListIterator {

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested currList.
        public boolean isInteger();
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested currList
        public Integer getInteger();
        // @return the nested currList that this NestedInteger holds, if it holds a nested currList
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    /**
     * Your NestedIterator object will be instantiated and called as such:
     * NestedIterator i = new NestedIterator(nestedList);
     * while (i.hasNext())
     *   v[f()] = i.next();
     */
    class NestedIterator implements Iterator<Integer> {

        private List<Integer> list;
        private int index;

        public NestedIterator(List<NestedInteger> nestedList) {
            index = 0;
            list = new ArrayList<>();

            for (NestedInteger ni : nestedList) {
                helper(ni);
            }
        }

        private void helper(NestedInteger nestedInteger) {
            if (nestedInteger.isInteger()) {
                this.list.add(nestedInteger.getInteger());
            } else {
                for (NestedInteger ni : nestedInteger.getList())
                    helper(ni);
            }
        }

        @Override
        public Integer next() {
            return list.get(index++);
        }

        @Override
        public boolean hasNext() {
            return index < list.size();
        }
    }
}

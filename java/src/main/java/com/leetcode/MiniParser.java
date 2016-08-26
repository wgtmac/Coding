package com.leetcode;

import java.util.Collections;
import java.util.List;

/**
 * 385. Mini Parser
 *
 Given a nested list of integers represented as a string, implement a parser to deserialize it.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Note: You may assume that the string is well-formed:

 String is non-empty.
 String does not contain white spaces.
 String contains only digits 0-9, [, - ,, ].

 Example 1:
 Given s = "324",
 You should return a NestedInteger object which contains a single integer 324.

 Example 2:
 Given s = "[123,[456,[789]]]",
 Return a NestedInteger object containing a nested list with 2 elements:

 1. An integer containing value 123.
 2. A nested list containing two elements:
 i.  An integer containing value 456.
 ii. A nested list with one element:
 a. An integer containing value 789.
 *
 */

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
class NestedInteger {
    // Constructor initializes an empty nested list.
    public NestedInteger() {}

    // Constructor initializes a single integer.
    public NestedInteger(int value) {}

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() { return true; }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() { return 0; }

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value) {}

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni) {}

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList() { return Collections.emptyList(); }
}

public class MiniParser {

    int idx = 0;

    public NestedInteger deserialize(String s) {
        String modifiedToList = '[' + s + ']';
        return processList(modifiedToList).getList().get(0);
    }

    private NestedInteger processList(String s) {
        NestedInteger list = new NestedInteger();
        StringBuilder sb = new StringBuilder();

        for (++idx; idx < s.length() && s.charAt(idx) != ']'; ++idx) {
            switch (s.charAt(idx)) {
                case ',':
                    if (sb.length() > 0) {
                        list.add(new NestedInteger(Integer.parseInt(sb.toString())));
                        sb = new StringBuilder();
                    }
                    break;
                case '[':
                    // nested list
                    list.add(processList(s));

                    // edge case: [[]]
                    // current idx is the 2nd ]
                    // then this for-loop should break
                    if (s.charAt(idx) == ']') {
                        --idx;
                    }
                    break;
                default:
                    sb.append(s.charAt(idx));
            }
        }

        if (sb.length() > 0) {
            list.add(new NestedInteger(Integer.parseInt(sb.toString())));
        }

        // skip the last ]
        idx++;

        return list;
    }
}

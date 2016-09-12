package com.leetcode;

/**
 * 119. Pascal's Triangle II
 * 
 * Given an index k, return the kth row of the Pascal's triangle.
 * For example, given k = 3,
 * Return [1,3,3,1].
 * 
 * Note:
 * Could you optimize your algorithm to use only O(k) extra space?
 * 
 * Skill: 
 * 直接算
 * */

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleII {
    public List<Integer> getRow(int rowIndex) {
        rowIndex++;

        List<Integer> last = null;
        for (int i = 0; i < rowIndex; i++) {
            List<Integer> list = new ArrayList<Integer>();
            if (last == null) {
                list.add(1);
            }
            else {
                list.add(1);
                int size = last.size();
                for (int j = 1; j < size; j++) {
                    list.add(last.get(j - 1) + last.get(j));
                }
                list.add(1);
            }
            last = list;
        }
        return last;
    }
}

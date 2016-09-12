package com.leetcode;

/**
 * 118. Pascal's Triangle
 * 
 * Given numRows, generate the first numRows of Pascal's triangle.
 * 
 * For example, given numRows = 5,
 * Return
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 * 
 * Skill: 
 * 每一行都是首尾在list里add(1),中间值为上一个list(保存为last)每两个元素相加得到
 * */

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            if (ret.isEmpty()) {
                list.add(1);
            } else {
                list.add(1);
                List<Integer> prev = ret.get(ret.size() - 1);
                int size = prev.size();
                for (int j = 1; j < size; j++)
                    list.add(prev.get(j - 1) + prev.get(j));
                list.add(1);
            }
            ret.add(list);
        }
        return ret;
    }
}

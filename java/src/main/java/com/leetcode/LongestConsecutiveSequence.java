package com.leetcode;

/**
 * 128. Longest Consecutive Sequence
 * 
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * 
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * 
 * Your algorithm should run in O(n) complexity.
 * 
 * Skill: 
 * 用HashMap暂存全部数
 * 再找连续的序列
 * */

import java.util.*;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] num) {
        if (num == null || num.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        
        for (int n : num)
            set.add(n);
        
        int tmpMax, max = 1;
        while (!set.isEmpty()) {
            tmpMax = 1;
            int no = set.iterator().next();  // current first
            set.remove(no);
            
            int i = no + 1;
            while (set.contains(i)) {
                set.remove(i++);
                tmpMax++;
            }
            
            i = no - 1;
            while (set.contains(i)) {
                set.remove(i--);
                tmpMax++;
            } 
            max = Math.max(tmpMax, max);
        }
        
        return max;
    }
}

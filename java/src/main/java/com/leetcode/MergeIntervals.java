package com.leetcode;

/**
 * 56. Merge Intervals
 * 
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 * 
 * Skill:
 * 先对每个interval的起点排序
 * 如果两个重合就合并 否则放入结果
 * 
 * 注意引用别乱改
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> list = new ArrayList<>();
        if (intervals == null || intervals.size() == 0)
            return list;

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        Interval curr = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            if (curr.end < intervals.get(i).start) {
                list.add(curr);
                curr = intervals.get(i);
            } else if (curr.end >= intervals.get(i).start && curr.end < intervals.get(i).end) {
                curr.end = intervals.get(i).end;
            }
        }
        list.add(curr);
        
        return list;
    }

    private static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
}

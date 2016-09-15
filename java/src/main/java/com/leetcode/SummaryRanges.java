package com.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * 352. Data Stream as Disjoint Intervals
 */
public class SummaryRanges {

    private static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    private TreeSet<Interval> intervals;

    /** Initialize your data structure here. */
    public SummaryRanges() {
        intervals = new TreeSet<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1 == o2) return 0;
                else if (o1 == null) return -1;
                else if (o2 == null) return 1;
                else return o1.start - o2.start;
            }
        });
    }

    public void addNum(int val) {
        Interval curr = new Interval(val, val);
        Interval lower = intervals.floor(curr);
        Interval higher = intervals.higher(curr);

        if (lower == null || val > lower.end + 1) {
            if (higher == null || val + 1 < higher.start)
                intervals.add(curr);
            else
                higher.start = val;
        } else if (val == lower.end + 1) {
            if (higher == null || val + 1 < higher.start)
                lower.end += 1;
            else {
                lower.end = higher.end;
                intervals.remove(higher);
            }
        }
    }

    public List<Interval> getIntervals() {
        return new ArrayList<>(intervals);
    }
}

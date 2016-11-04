package com.leetcode;

import java.util.*;

/**
 * 253. Meeting Rooms II
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the
 * minimum number of conference rooms required.
 *
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
 */
public class MeetingRoomsII {
    private static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public int minMeetingRooms(Interval[] intervals) {

        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        PriorityQueue<List<Interval>> minHeap = new PriorityQueue<>(new Comparator<List<Interval>>() {
            @Override
            public int compare(List<Interval> o1, List<Interval> o2) {
                return o1.get(o1.size() - 1).end - o2.get(o2.size() - 1).end;
            }
        });

        for (Interval interval : intervals) {
            List<Interval> list = null;
            if (minHeap.isEmpty() || minHeap.peek().get(minHeap.peek().size() - 1).end > interval.start) {
                list = new ArrayList<>();
            } else {
                list = minHeap.poll();
            }
            list.add(interval);
            minHeap.offer(list);
        }

        return minHeap.size();
    }
}

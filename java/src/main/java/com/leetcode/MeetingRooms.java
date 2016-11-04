package com.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 252. Meeting Rooms
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine
 * if a person could attend all meetings.
 *
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return false.
 *
 */
public class MeetingRooms {

    private static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length < 2) return true;

        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        Interval prev = intervals[0];
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i].start < prev.end)
                return false;
            prev = intervals[i];
        }

        return true;
    }
}

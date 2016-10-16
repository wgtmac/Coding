package com.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 391. Perfect Rectangle
 *
 * Given N axis-aligned rectangles where N > 0, determine if they all together
 * form an exact cover of a rectangular region.
 *
 * Each rectangle is represented as a bottom-left point and a top-right point.
 * For example, a unit square is represented as [1,1,2,2]. (coordinate of
 * bottom-left point is (1, 1) and top-right point is (2, 2)).
 *
 *
 * Example 1:
 * rectangles = [
 * [1,1,3,3],
 * [3,1,4,2],
 * [3,2,4,4],
 * [1,3,2,4],
 * [2,3,3,4]
 * ]
 * Return true. All 5 rectangles together form an exact cover of a rectangular region.
 *
 * Example 2:
 * rectangles = [
 * [1,1,2,3],
 * [1,3,2,4],
 * [3,1,4,2],
 * [3,2,4,4]
 * ]
 * Return false. Because there is a gap between the two rectangular regions.
 *
 * Example 3:
 * rectangles = [
 * [1,1,3,3],
 * [3,1,4,2],
 * [1,3,2,4],
 * [3,2,4,4]
 * ]
 * Return false. Because there is a gap in the top center.
 *
 * Example 4:
 * rectangles = [
 * [1,1,3,3],
 * [3,1,4,2],
 * [1,3,2,4],
 * [2,2,4,4]
 * ]
 * Return false. Because two of the rectangles overlap with each other.
 */
public class PerfectRectangle {
    public boolean isRectangleCover(int[][] rectangles) {
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        int totalArea = 0;
        for (int i = 0; i < rectangles.length; ++i) {
            for (int j = i + 1; j < rectangles.length; ++j) {
                if (overlaps(rectangles[i], rectangles[j])) {
                    return false;
                }
            }

            totalArea += (rectangles[i][3] - rectangles[i][1])
                    * (rectangles[i][2] - rectangles[i][0]);
            minX = Math.min(minX, rectangles[i][0]);
            minY = Math.min(minY, rectangles[i][1]);
            maxX = Math.max(maxX, rectangles[i][2]);
            maxY = Math.max(maxY, rectangles[i][3]);
        }
        return (maxX - minX) * (maxY - minY) == totalArea;
    }

    private boolean overlaps(int[] r1, int[] r2) {
        // r1 is to the left of r2
        if (r1[2] <= r2[0]) return false;
        // r1 is to the right of r2
        if (r1[0] >= r2[2]) return false;
        // r1 is on the top of r2
        if (r1[1] >= r2[3]) return false;
        // r1 is under the bottom of r2
        if (r1[3] <= r2[1]) return false;
        // otherwise, overlaps
        return true;
    }

    public boolean isRectangleCover_On(int[][] rectangles) {
        if (rectangles.length == 0 || rectangles[0].length == 0) return false;
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        int totalArea = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int[] rec : rectangles) {
            totalArea += (rec[3] - rec[1])
                    * (rec[2] - rec[0]);
            minX = Math.min(minX, rec[0]);
            minY = Math.min(minY, rec[1]);
            maxX = Math.max(maxX, rec[2]);
            maxY = Math.max(maxY, rec[3]);
            //bottom-left
            if (overlap(rec[0] + " " + rec[1], 1, map)) return false;
            //top-left
            if (overlap(rec[0] + " " + rec[3], 2, map)) return false;
            //bottom-right
            if (overlap(rec[2] + " " + rec[1], 4, map)) return false;
            //top-right
            if (overlap(rec[2] + " " + rec[3], 8, map)) return false;
        }
        int count = 0;
        Iterator<Integer> iter = map.values().iterator();
        while (iter.hasNext()) {
            Integer i = iter.next();
            if (i != 15 && i != 12 && i != 10 && i != 9 && i != 6 && i != 5 && i != 3) count++;
        }
        return count == 4 && (maxX - minX) * (maxY - minY) == totalArea;
    }

    private boolean overlap(String corner, Integer type, Map<String, Integer> map) {
        Integer value = map.get(corner);
        if (value == null) value = type;
        else if ((value & type) != 0) return true;
        else value |= type;
        map.put(corner, value);
        return false;
    }
}

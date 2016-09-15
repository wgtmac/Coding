package com.leetcode;

/**
 * 149. Max Points on a Line
 * 
 * Given n points on a 2D plane, find the maximum number of points that lie on
 * the same straight line.
 * 
 * Skill: 
 * 对每个点，计算有多少个非重复点与之形成的直线（不同斜率），把频率计数放入hashmap
 * 将hashmap的value进行排序，最大的数加上与当前点重复的数量，即为与当前点共线最多的点数目
 * 将所有点的最大共线点数目进行比较，最大的就是返回值
 *
 * 注意全部是同一个点的极端情况
 * */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine {
    public int maxPoints(Point[] points) {
        if (points == null || points.length < 1) return 0;
        if (points.length == 1 || points.length == 2) 	return points.length;
        
        Map<Double, Integer> map = new HashMap<>(points.length);
        
        double k;
        int duplicate, max = Integer.MIN_VALUE;
        
        for (int i = 0; i < points.length; i++) {
            duplicate = 0;
            map.clear();
            
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;
                
                if (points[i].x != points[j].x)
                    k = (double) (points[i].y - points[j].y) / (points[i].x - points[j].x);
                else if (points[i].y == points[j].y) {
                    duplicate++;
                    continue;
                } else
                    k = Double.MAX_VALUE;
                
                if (!map.containsKey(k))
                    map.put(k, 2);
                else
                    map.put(k, map.get(k) + 1);
            }

            // if all points are same
            if (map.isEmpty()) {
                return points.length;
            } else {
                for (int count : map.values())
                    max = Math.max(max, duplicate + count);
            }
        }
        
        return max;
    }

    // Definition for a point.
    private static class Point {
        public int x;
        public int y;
        public Point() { x = 0; y = 0; }
        public Point(int a, int b) { x = a; y = b; }
    }
}

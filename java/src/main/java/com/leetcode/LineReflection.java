package com.leetcode;

import java.util.*;

/**
 * 356. Line Reflection
 *
 * Given n points on a 2D plane, find if there is such a line parallel to y-axis
 * that reflect the given points.
 *
 * Example 1:
 * Given points = [[1,1],[-1,1]], return true.
 *
 * Example 2:
 * Given points = [[1,1],[-1,-1]], return false.
 *
 * Follow up:
 * Could you do better than O(n^2)?
 */
public class LineReflection {
    public boolean isReflected(int[][] points) {
        // construct map for (y -> List(x))
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] point : points) {
            if (!map.containsKey(point[1]))
                map.put(point[1], new TreeSet<>());
            map.get(point[1]).add(point[0]);
        }

        Double reflectLine = null;
        for (Set<Integer> nums : map.values()) {
            List<Integer> list = new ArrayList<>(nums);
            int len = list.size();
            double mid = (len % 2) == 0 ? (list.get(len / 2) + list.get(len / 2 - 1)) / 2.0 : list.get(len / 2);
            for (int l = 0, r = len - 1; l < r; l++, r--) {
                if (list.get(l) + list.get(r) != mid * 2)
                    return false;
            }
            if (reflectLine == null)
                reflectLine = mid;
            else if (reflectLine != mid)
                return false;
        }

        return true;
    }
}

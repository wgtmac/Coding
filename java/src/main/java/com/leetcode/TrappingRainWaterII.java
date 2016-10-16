package com.leetcode;

import java.util.*;

/**
 * 407. Trapping Rain Water II
 *
 * Given an m x n matrix of positive integers representing the height of each
 * unit cell in a 2D elevation map, compute the volume of water it is able to
 * trap after raining.
 *
 * Note:
 * Both m and n are less than 110. The height of each unit cell is greater than
 * 0 and is less than 20,000.
 *
 * Example:
 *
 * Given the following 3x6 height map:
 * [
 * [1,4,3,1,3,2],
 * [3,2,1,3,2,4],
 * [2,3,3,2,3,1]
 * ]
 *
 * Return 4.
 *
 * Hint:
 * Use heap to store the (height, list of points) pair
 * Each time process the lowest height, find all neighboring points with same height;
 * If outside points are all higher than them, update height, otherwise drop them all.
 */
public class TrappingRainWaterII {
    private int[] v = {0, 1, 0, -1}, h = {1, 0, -1, 0};

    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length < 2 || heightMap[0].length < 2) return 0;

        int sum = 0, row = heightMap.length, col = heightMap[0].length;
        TreeMap<Integer, Set<Integer>> map = new TreeMap<>();
        for (int i = 1; i < row - 1; ++i) {
            for (int j = 1; j < col - 1; ++j) {
                if (!map.containsKey(heightMap[i][j]))
                    map.put(heightMap[i][j], new HashSet<>());
                map.get(heightMap[i][j]).add(i * col + j);
            }
        }

        // get lowest height each time
        while (!map.isEmpty()) {
            int height = map.firstKey();
            Set<Integer> set = map.get(height);

            while (!set.isEmpty()) {
                // get all connected points with same height
                Set<Integer> points = getEqualNeighbours(heightMap, set.iterator().next());
                // find min height outside these points
                int minOutsideHeight = findMinHeight(points, heightMap);
                if (minOutsideHeight <= height)
                    set.removeAll(points);
                else {
                    for (int point : points)
                        heightMap[point / col][point % col] = minOutsideHeight;
                    set.removeAll(points);

                    if (!map.containsKey(minOutsideHeight))
                        map.put(minOutsideHeight, new HashSet<>());
                    map.get(minOutsideHeight).addAll(points);

                    sum += (minOutsideHeight - height) * points.size();
                }
            }

            map.remove(height);
        }

        return sum;
    }

    private int findMinHeight(Set<Integer> set, int[][] heightMap) {
        int minH = Integer.MAX_VALUE, col = heightMap[0].length, value, i, j;
        for (int point : set) {
            i = point / col; j = point % col;

            for (int k = 0; k < h.length; ++k) {
                value = (i + h[k]) * col + j + v[k];
                if (!set.contains(value))
                    minH = Math.min(minH, heightMap[i + h[k]][j + v[k]]);
            }
        }
        return minH;
    }

    private Set<Integer> getEqualNeighbours(int[][] heightMap, int value) {
        final int point = value;
        int col = heightMap[0].length, i = value / col, j = value % col, height = heightMap[i][j];
        Queue<Integer> queue = new LinkedList<Integer>() {{offer(point);}};
        Set<Integer> set = new HashSet<Integer>() {{add(point);}};

        while (!queue.isEmpty()) {
            value = queue.poll();
            i = value / col; j = value % col;
            for (int k = 0; k < h.length; ++k)
                compareAndAdd(heightMap, i + v[k], j + h[k], height, queue, set);
        }
        return set;
    }

    private boolean isEdge(int[][] heightMap, int i, int j) {
        return (i == 0 || i == heightMap.length - 1 ||
                j == 0 || j == heightMap[0].length - 1);
    }

    private void compareAndAdd(int[][] heightMap, int i, int j, int h,
                               Queue<Integer> queue, Set<Integer> set) {
        int value = i * heightMap[0].length + j;
        if (!isEdge(heightMap, i, j) && heightMap[i][j] == h
                && !set.contains(value)) {
            set.add(value);
            queue.offer(value);
        }
    }
}

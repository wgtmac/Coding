package com.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 317. Shortest Distance from All Buildings
 *
 * You want to build a house on an empty land which reaches all buildings in the
 * shortest amount of distance. You can only move up, down, left and right. You
 * are given a 2D grid of values 0, 1 or 2, where:
 *
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
 *
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * The point (1,2) is an ideal empty land to build a house, as the total travel
 * distance of 3+3+1=7 is minimal. So return 7.
 *
 * Note:
 * There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 */
public class ShortestDistancefromAllBuildings {

    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;

        int row = grid.length, col = grid[0].length;
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        int totalBuildings = 0, dist = 0, value, size, r, c, next_r, next_c;
        int[][] reachable = new int[row][col], path = new int[row][col];

        // move helper
        int[] x = {-1, 0, 1, 0}, y = {0, -1, 0, 1};

        // for each building, do BFS to go over all places
        // mark unreach
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (grid[i][j] == 1) {
                    totalBuildings++;

                    // reset values
                    visited.clear();
                    dist = 0;

                    // process the building
                    value = i * col + j;
                    visited.add(value);
                    queue.offer(value);

                    while (!queue.isEmpty()) {
                        dist++;
                        size = queue.size();

                        for (int s = 0; s < size; ++s) {
                            value = queue.poll();
                            r = value / col; c = value % col;

                            // find next reachable empty places and put into queue
                            // for next visit
                            for (int k = 0; k < 4; ++k) {
                                next_r = r + x[k]; next_c = c + y[k];
                                if (isValid(grid, row, col, next_r, next_c, visited)) {
                                    path[next_r][next_c] += dist;
                                    reachable[next_r][next_c] += 1;

                                    value = next_r * col + next_c;
                                    visited.add(value);
                                    queue.offer(value);
                                }
                            }
                        }
                    }
                }
            }
        }

        return findShortest(reachable, path, totalBuildings);
    }

    private boolean isValid(int[][] grid, int row, int col, int r, int c, Set<Integer> visited) {
        return 0 <= r && r < row && 0 <= c && c < col && grid[r][c] == 0 && !visited.contains(r * col + c);
    }

    private int findShortest(int[][] reachable, int[][] dist, int countBuilding) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < reachable.length; ++i) {
            for (int j = 0; j < reachable[0].length; ++j) {
                if (reachable[i][j] == countBuilding) {
                    min = Math.min(min, dist[i][j]);
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}

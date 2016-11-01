package com.leetcode;

import java.util.*;

/**
 * 305. Number of Islands II
 *
 * A 2d grid map of m rows and n columns is initially filled with water. We may
 * perform an addLand operation which turns the water at position (row, col) into
 * a land. Given a list of positions to operate, count the number of islands after
 * each addLand operation. An island is surrounded by water and is formed by
 * connecting adjacent lands horizontally or vertically. You may assume all four
 * edges of the grid are all surrounded by water.
 *
 * Example:
 *
 * Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
 *
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 *
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 *
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 *
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 *
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 * We return the result as an array: [1, 1, 2, 3]
 *
 * Challenge:
 *
 * Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */
public class NumberofIslandsII {
    /**
     * Dynamic union-find utilities
     */
    private int find(List<Integer> parents, int id) {
        while (id != parents.get(id)) {
            parents.set(id, parents.get(parents.get(id)));
            id = parents.get(id);
        }
        return id;
    }

    private void union(List<Integer> parents, List<Integer> ranks, int id1, int id2, int[] count) {
        if (id1 == id2) return;
        int parent1 = find(parents, id1), parent2 = find(parents, id2);
        if (parent1 != parent2) {
            parents.set(parent1, parent2);
            if (ranks.get(parent1) < ranks.get(parent2)) {
                parents.set(parent1, parent2);
            } else if (ranks.get(parent2) < ranks.get(parent1)) {
                parents.set(parent2, parent1);
            } else {
                parents.set(parent2, parent2);
                ranks.set(parent2, ranks.get(parent2) + 1);
            }
            count[0]--;
        }
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        // for union-find
        List<Integer> parents = new ArrayList<>(), ranks = new ArrayList<>();
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(grid[i], -1);
        }

        int[] count = {0};
        for (int i = 0; i < positions.length; ++i) {
            update(grid, count, positions[i][0], positions[i][1], parents, ranks);
            ans.add(count[0]);
        }

        return ans;
    }

    private void update(int[][] grid, int[] count, int r, int c,
                        List<Integer> parents, List<Integer> ranks) {
        Set<Integer> metIslands = new HashSet<>();
        if (r - 1 >= 0 && grid[r - 1][c] != -1)
            metIslands.add(find(parents, grid[r - 1][c]));
        if (r + 1 < grid.length && grid[r + 1][c] != -1)
            metIslands.add(find(parents, grid[r + 1][c]));
        if (c - 1 >= 0 && grid[r][c - 1] != -1)
            metIslands.add(find(parents, grid[r][c - 1]));
        if (c + 1 < grid[0].length && grid[r][c + 1] != -1)
            metIslands.add(find(parents, grid[r][c + 1]));

        if (metIslands.isEmpty()) {
            grid[r][c] = parents.size();
            parents.add(grid[r][c]);  // self as parent since it is isolated
            ranks.add(0);
            count[0]++;
        } else {
            // merge all met islands
            int parent = -1;
            for (int island : metIslands) {
                if (parent == -1)
                    parent = island;
                else
                    union(parents, ranks, parent, island, count);
            }
            grid[r][c] = parent;
        }
    }
}

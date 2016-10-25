package com.leetcode;

import java.util.*;

/**
 * 286. Walls and Gates 
 *
 * You are given a m x n 2D grid initialized with these three possible values.
 *
 * -1  - A wall or an obstacle.
 * 0   - A gate.
 * INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to
 *       represent INF as you may assume that the distance to a gate is less
 *       than 2147483647.
 *
 * Fill each empty room with the distance to its nearest gate. If it is impossible
 * to reach a gate, it should be filled with INF.
 *
 * For example, given the 2D grid:
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 * 0  -1 INF INF
 *
 * After running your function, the 2D grid should be:
 * 3  -1   0   1
 * 2   2   1  -1
 * 1  -1   2  -1
 * 0  -1   3   4
 */
public class WallsAndGates {
    void wallsAndGates(List<List<Integer>> rooms) {
        int row = rooms.size(), col = rooms.get(0).size();
        // i * col + j
        Set<Integer> processed = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        // find all gates
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                switch (rooms.get(i).get(j)) {
                    case 0:  queue.offer(i * col + j);   // gate
                    case -1: processed.add(i * col + j); // wall
                }
            }
        }

        // all gates go through BFS, stop at the same level
        // until all blocks are processed
        int dist = 0;
        int[] x = {-1, 0, 1, 0}, y = {0, -1, 0, 1};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int value = queue.poll(), r = value / col, c = value % col;
                rooms.get(r).set(c, dist);

                // try neighbours
                for (int j = 0; j < 4; ++j) {
                    int R = r + x[j], C = c + y[j], neigbour = R * col + C;
                    if (0 <= R && R < row && 0 <= C && C < col
                            && !processed.contains(neigbour)) {
                        queue.offer(neigbour);
                        processed.add(neigbour);
                    }
                }
            }

            dist++;
        }
    }

    public static void main(String[] args) {
        WallsAndGates w = new WallsAndGates();
        int INF = Integer.MAX_VALUE;
        List<List<Integer>> rooms = new ArrayList<List<Integer>>() {{
            add(Arrays.asList(INF, -1, 0, INF));
            add(Arrays.asList(INF, INF, INF, -1));
            add(Arrays.asList(INF, -1, INF, -1));
            add(Arrays.asList(0, -1, INF, INF));
        }};
        w.wallsAndGates(rooms);
        System.out.println(rooms);
    }
}

package com.leetcode;

import java.util.*;

/**
 * 261. Graph Valid Tree
 *
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge
 * is a pair of nodes), write a function to check whether these edges make up a
 * valid tree.
 *
 * For example:
 *
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 *
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 *
 * Hint:
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is
 * this case a valid tree?
 * According to the definition of tree on Wikipedia: “a tree is an undirected
 * graph in which any two vertices are connected by exactly one path. In other
 * words, any connected graph without simple cycles is a tree.”
 *
 * Note: you can assume that no duplicate edges will appear in edges. Since all
 * edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear
 * together in edges.
 *
 */
public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        // init graph
        List<Integer>[] adjList = new List[n];
        for (int i = 0; i < n; ++i)
            adjList[i] = new ArrayList<>();

        for (int[] edge : edges) {
            adjList[edge[0]].add(edge[1]);
            adjList[edge[1]].add(edge[0]);
        }

        // BFS, 0 as the starting point
        Queue<Integer> queue = new LinkedList<Integer>() {{offer(0);}};
        Set<Integer> visited = new HashSet<Integer>() {{add(0);}};
        int[] level = new int[n];

        while (!queue.isEmpty()) {
            for (int size = queue.size(); size > 0; size--) {
                int node = queue.poll();
                for (int child : adjList[node]) {
                    if (!visited.contains(child)) {
                        visited.add(child);
                        queue.offer(child);
                        level[child] = level[node] + 1;
                    } else if (level[child] + 1 != level[node]) {
                        // visited child is not a parent node
                        return false;
                    }
                }
            }
        }

        return visited.size() == n;   // all nodes are connected
    }
}

package com.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 323. Number of Connected Components in an Undirected Graph
 *
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges
 * (each edge is a pair of nodes), write a function to find the number of
 * connected components in an undirected graph.
 *
 * Example 1:
 * 0          3
 * |          |
 * 1 --- 2    4
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
 *
 * Example 2:
 * 0           4
 * |           |
 * 1 --- 2 --- 3
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
 *
 * Note:
 * You can assume that no duplicate edges will appear in edges. Since all edges
 * are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */
public class NumberofConnectedComponentsInAnUndirectedGraph {

    private void union(int[] parents, int[] ranks, int a, int b) {
        int parent_a = find(parents, a);
        int parent_b = find(parents, b);

        if (parent_a != parent_b) {
            if (ranks[parent_a] < ranks[parent_b]) {
                parents[parent_a] = parent_b;
            } else if (ranks[parent_a] > ranks[parent_b]) {
                parents[parent_b] = parent_a;
            } else {
                ranks[parent_a]++;
                parents[parent_b] = parent_a;
            }
        }
    }

    private int find(int[] parents, int value) {
        while (parents[value] != value) {
            parents[value] = parents[parents[value]];
            value = parents[value];
        }
        return value;
    }

    public int countComponents(int n, int[][] edges) {
        int[] parents = new int[n], ranks = new int[n];
        for (int i = 0; i < n; ++i)
            parents[i] = i;

        for (int[] edge : edges) {
            union(parents, ranks, edge[0], edge[1]);
        }

        Set<Integer> isolated = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            isolated.add(find(parents, i));
        }

        return isolated.size();
    }
}

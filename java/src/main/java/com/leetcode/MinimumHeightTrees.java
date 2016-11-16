package com.leetcode;

import java.util.*;

/**
 * 310. Minimum Height Trees
 *
 * For a undirected graph with tree characteristics, we can choose any node as
 * the root. The result graph is then a rooted tree. Among all possible rooted
 * trees, those with minimum height are called minimum height trees (MHTs).
 * Given such a graph, write a function to find all the MHTs and return a list
 * of their root labels.
 *
 * Format
 * The graph contains n nodes which are labeled from 0 to n - 1. You will be
 * given the number n and a list of undirected edges (each edge is a pair of labels).
 *
 * You can assume that no duplicate edges will appear in edges. Since all edges
 * are undirected, [0, 1] is the same as [1, 0] and thus will not appear together
 * in edges.
 *
 * Example 1:
 *
 * Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 *
 *   0
 *   |
 *   1
 *  / \
 * 2   3
 * return [1]
 *
 * Example 2:
 *
 * Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 *
 * 0  1  2
 *  \ | /
 *    3
 *    |
 *    4
 *    |
 *    5
 * return [3, 4]
 *
 * Note:
 * (1) According to the definition of tree on Wikipedia: “a tree is an undirected
 * graph in which any two vertices are connected by exactly one path. In other
 * words, any connected graph without simple cycles is a tree.”
 * (2) The height of a rooted tree is the number of edges on the longest downward
 * path between the root and a leaf.
 */
public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // init graph
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; ++i)
            graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        // randomly pickup one node to start for BFS and mark the last node
        int oneFurthestNode = BFS(graph, 0, null);

        // find another furthest node (may have many but one is enough)
        int[] dist1 = new int[n];
        int anotherFurthestNode = BFS(graph, oneFurthestNode, dist1);

        int[] dist2 = new int[n];
        BFS(graph, anotherFurthestNode, dist2);


        // iterate all nodes to calculate depth
        List<Integer> ans = new ArrayList<>();
        int[] depth = new int[n];
        int min = n;
        for (int i = 0; i < n; ++i) {
            depth[i] = Math.max(dist1[i], dist2[i]);
            if (min > depth[i]) {
                ans.clear();
                min = depth[i];
            }
            if (min == depth[i]) {
                ans.add(i);
            }
        }
        return ans;
    }

    // @param distTo: the distance to node (start)
    // @return the last visited node
    private int BFS(List<Integer>[] graph, int start, int[] distTo) {
        Queue<Integer> queue = new LinkedList<Integer>() {{offer(start);}};
        Set<Integer> visited = new HashSet<Integer>() {{add(start);}};

        if (distTo != null) distTo[start] = 0;
        int last = start, depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                List<Integer> children = graph[last = queue.poll()];
                if (distTo != null) distTo[last] = depth;
                for (int child : children) {
                    if (!visited.contains(child)) {
                        visited.add(child);
                        queue.offer(child);
                    }
                }
            }
            depth++;
        }
        return last;
    }
}

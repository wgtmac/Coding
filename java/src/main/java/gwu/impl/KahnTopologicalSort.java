package gwu.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Topological sort by indegree
 */
public class KahnTopologicalSort {
    private static class Graph {
        private int numOfVertices;
        private List<Integer>[] adjacencyList;

        public Graph(int v) {
            numOfVertices = v;
            adjacencyList = new List[v];
            for (int i = 0; i < v; ++i)
                adjacencyList[i] = new ArrayList<>();
        }

        public void addEdge(int start, int end) {
            adjacencyList[start].add(end);
        }
    }

    public List<Integer> topoSort(Graph graph) {
        int visited = 0;
        List<Integer> list = new ArrayList<>();

        // init in-degree and out-degree
        int[] inDegree = new int[graph.numOfVertices];

        for (int i = 0; i < graph.numOfVertices; ++i) {
            for (int j : graph.adjacencyList[i]) {
                inDegree[j]++;
            }
        }

        // put all 0 indegree node into queue since there are starting points
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.numOfVertices; ++i) {
            if (inDegree[i] == 0)
                queue.offer(i);
        }

        // loop over to find out all 0 indegree nodes
        int vertex;
        while (!queue.isEmpty()) {
            vertex = queue.poll();
            list.add(vertex);

            for (int adj : graph.adjacencyList[vertex]) {
                if (--inDegree[adj] == 0)
                    queue.offer(adj);
            }

            visited++;
        }

        // a cyclic graph has at least ONE non-zero indegree vertex
        if (visited != graph.numOfVertices) {
            throw new RuntimeException("Cycle is existing!");
        }

        return list;
    }
}

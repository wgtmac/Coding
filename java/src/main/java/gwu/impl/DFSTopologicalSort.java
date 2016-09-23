package gwu.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * A DFS implementation of DAG topological sorting, O(V+E).
 */
public class DFSTopologicalSort {

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

    private void DFS(Graph graph, int currNode, Stack<Integer> stack, boolean[] visited) {
        visited[currNode] = true;
        for (int adjacentNode : graph.adjacencyList[currNode]) {
            if (!visited[adjacentNode])
                DFS(graph, adjacentNode, stack, visited);
        }
        stack.push(currNode);
    }

    public List<Integer> topoSort(Graph graph) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.numOfVertices];

        for (int i = 0; i < graph.numOfVertices; ++i) {
            if (!visited[i])
                DFS(graph, i, stack, visited);
        }
        List<Integer> list = new LinkedList<>();
        while (!stack.isEmpty())
            list.add(stack.pop());
        return list;
    }
}

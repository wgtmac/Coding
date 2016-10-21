package gwu.impl;


import java.util.*;

/**
 * Created by wgtmac on 10/19/16.
 *
 * Implementation of shortest path problem
 */
public class ShortestPath {

    private static class Edge {
        int from, to, weight;
    }

    // O(V + E), a recursive implementation of topological sorting
    private void tsort(int vertex, List<Edge>[] edges, boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;
        for (Edge edge: edges[vertex]) {
            if (!visited[edge.to])
                tsort(edge.to, edges, visited, stack);
        }
        stack.push(vertex);
    }

    /**
     * Shortest path based on topological sorting: O(V + E)
     */
    public void tsortbased(int numVertices, List<Edge>[] edges, int start) {
        // topological sorting
        boolean[] visited = new boolean[numVertices];
        Stack<Integer> topoOrder = new Stack<>();
        for (int i = 0; i < numVertices; ++i) {
            if (!visited[i])
                tsort(i, edges, visited, topoOrder);
        }

        // iterate over topo order
        int[] pathTo = new int[numVertices], distTo = new int[numVertices];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[start] = 0;
        while (!topoOrder.isEmpty()) {
            int vertex = topoOrder.pop();   // iterate every vertex
            for (Edge edge : edges[vertex]) {   // iterate all its edges
                if (distTo[edge.to] > distTo[edge.from] + edge.weight) {
                    distTo[edge.to] = distTo[edge.from] + edge.weight;
                    pathTo[edge.to] = edge.from;
                }
            }
        }
    }

    /**
     * Longest path based on topological sorting
     */
    public void longestPath(int numVertices, List<Edge>[] edges, int start) {
        // topological sorting
        boolean[] visited = new boolean[numVertices];
        Stack<Integer> topoOrder = new Stack<>();
        for (int i = 0; i < numVertices; ++i) {
            if (!visited[i])
                tsort(i, edges, visited, topoOrder);
        }

        // iterate over topo order
        int[] pathTo = new int[numVertices], distTo = new int[numVertices];
        Arrays.fill(distTo, Integer.MIN_VALUE);
        distTo[start] = 0;
        while (!topoOrder.isEmpty()) {
            int vertex = topoOrder.pop();
            for (Edge edge : edges[vertex]) {
                if (distTo[edge.to] < distTo[edge.from] + edge.weight) {
                    distTo[edge.to] = distTo[edge.from] + edge.weight;
                    pathTo[edge.to] = edge.from;
                }
            }
        }
    }

    /**
     * Dijkstra solution
     *
     * O(V*logV)
     * Cannot have negative weight
     */
    public void dijkstra(int numVertices, List<Edge>[] edges, int start) {
        int[] pathTo = new int[numVertices], distTo = new int[numVertices];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[start] = 0;

        // dist <-> list of vertices
        TreeMap<Integer, HashSet<Integer>> minHeap = new TreeMap<>();
        minHeap.put(0, new HashSet<Integer>() {{add(start);}});

        // each iteration processes a vertex which is guaranteed
        // to have the shortest path to visited vertices
        while (!minHeap.isEmpty()) {
            // get the current closest vertex that is not processed
            Map.Entry<Integer, HashSet<Integer>> currMinDistEntry =
                    minHeap.firstEntry();
            int from = currMinDistEntry.getValue().iterator().next();
            if (currMinDistEntry.getValue().size() == 1) {
                minHeap.remove(currMinDistEntry.getKey());
            } else {
                currMinDistEntry.getValue().remove(from);
            }

            // update its edges
            for (Edge edge : edges[from]) {
                int oldDist = distTo[edge.to];
                int newDist = distTo[edge.from] + edge.weight;
                if (oldDist > newDist) {  // processed vertex can't pass here
                    // remove old dist for the 'to' vertex
                    if (oldDist != Integer.MAX_VALUE) {
                        Set<Integer> verticesOfSameDist = minHeap.get(oldDist);
                        if (verticesOfSameDist.size() == 1) {
                            minHeap.remove(oldDist);
                        } else {
                            verticesOfSameDist.remove(edge.to);
                        }
                    }

                    // update new dist for the 'to' vertex
                    if (!minHeap.containsKey(newDist)) {
                        minHeap.put(newDist, new HashSet<>());
                    }
                    minHeap.get(newDist).add(edge.to);
                    distTo[edge.to] = distTo[edge.from] + edge.weight;
                    pathTo[edge.to] = edge.from;
                }
            }
        }
    }

    /**
     * Bellman-Ford algorithm
     *
     * O(VE), can detect negative cycle
     */
    public void BellmanFord(int numVertices, List<Edge> edges, int start) {
        int[] pathTo = new int[numVertices], distTo = new int[numVertices];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[start] = 0;

        // assumption, the longest path contains (V - 1) edges
        for (int i = 1; i < numVertices; ++i) {
            for (Edge edge : edges) {
                if (distTo[edge.to] > distTo[edge.from] + edge.weight) {
                    distTo[edge.to] = distTo[edge.from] + edge.weight;
                    pathTo[edge.to] = edge.from;
                }
            }
        }

        // detect negative cycle
        for (Edge edge : edges) {
            if (distTo[edge.to] > distTo[edge.from] + edge.weight) {
                System.out.println("Has negative cycle!");
            }
        }
    }

}

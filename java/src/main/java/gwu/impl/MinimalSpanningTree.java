package gwu.impl;

import java.util.*;

/**
 * Created by wgtmac on 10/19/16.
 */
public class MinimalSpanningTree {

    private static class Edge {
        int from, to, weight;
    }

    /**
     * Union-find helper
     */
    private int find(int[] parents, int val) {
        int parent = parents[val];
        while (parents[parent] != parent) {
            parent = parents[parent];
        }
        parents[val] = parent;
        return parents[val];
    }

    private void union(int[] parents, int[] rank, int a, int b) {
        int parent_a = find(parents, a);
        int parent_b = find(parents, b);

        if (parent_a != parent_b) {
            if (rank[parent_a] < rank[parent_b]) {
                parents[a] = parent_b;
            } else if (rank[parent_a] < rank[parent_b]) {
                parents[b] = parent_a;
            } else {
                rank[parent_b]++;
                parents[a] = parent_b;
            }
        }
    }

    /**
     * Kruskal's algorithm
     *
     * O(ElogE) or O(ElogV).
     */
    public List<Edge> kruskal(int numVertices, List<Edge> edges) {
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        });

        // put all edges in graph, O(ElogE)
        for (Edge edge : edges) {
            minHeap.offer(edge);
        }

        // init union-find
        int[] parents = new int[numVertices], ranks = new int[numVertices];
        for (int i = 0; i < numVertices; ++i)
            parents[i] = i;

        List<Edge> mst = new ArrayList<>();

        // iterate every edge to union vertices
        while (mst.size() < numVertices - 1 && !minHeap.isEmpty()) {
            Edge edge = minHeap.poll();

            if (find(parents, edge.from) != find(parents, edge.to)) {
                union(parents, ranks, edge.from, edge.to);
                mst.add(edge);
            }
        }

        return mst;
    }

    /**
     * Prim's algorithm
     *
     * Go over every unvisited vertex
     * ....Iterate every edge of the vertex
     * ........this edge belongs to MST
     * ........Put every edge touching to unvisited vertex into min heap
     */
    public List<Edge> prim_edge(int numVertices, List<Edge>[] edges) {
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        });

        boolean[] visited = new boolean[numVertices];
        List<Edge> mst = new ArrayList<>();

        for (int i = 0; i < numVertices; ++i) {
            if (!visited[i]) {
                visit(edges, i, visited, minHeap);

                while (!minHeap.isEmpty()) {
                    Edge edge = minHeap.poll();
                    if (visited[edge.from] && visited[edge.to])
                        continue;

                    mst.add(edge);

                    if (!visited[edge.from])
                        visit(edges, edge.from, visited, minHeap);
                    if (!visited[edge.to])
                        visit(edges, edge.to, visited, minHeap);
                }

            }
        }

        return mst;
    }

    private void visit(List<Edge>[] edges, int vertex, boolean[] visited,
                       PriorityQueue<Edge> minHeap) {
        visited[vertex] = true;
        for (Edge edge : edges[vertex]) {
            if (!visited[edge.to]) {
                minHeap.offer(edge);
            }
        }
    }

    /**
     * Prim's algorithm
     *
     * Similar to dijkstra
     */
    public List<Edge> prim_vertex(int numVertices, List<Edge>[] edges) {
        int[] distTo = new int[numVertices];
        Edge[] edgeTo = new Edge[numVertices];
        Arrays.fill(distTo, Integer.MAX_VALUE);

        // dist <-> list of vertices
        TreeMap<Integer, HashSet<Integer>> minHeap = new TreeMap<>();

        // each iteration processes a vertex which is guaranteed
        // to have the shortest path to visited vertices
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; ++i) {
            if (!visited[i]) {
                int vertex = i;
                minHeap.put(i, new HashSet<Integer>() {{add(vertex);}});
                distTo[i] = 0;

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

                    // update dist to unconnected vertices
                    for (Edge edge : edges[from]) {
                        if (visited[edge.to]) continue;

                        // processed vertex can't pass here
                        int oldDist = distTo[edge.to];
                        int newDist = distTo[edge.from] + edge.weight;
                        if (oldDist > newDist) {
                            // remove old dist for the 'to' vertex
                            // if this is a new vertex then it is not in minHeap
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

                            // used to construct mst
                            edgeTo[edge.to] = edge;
                        }
                    }

                    visited[from] = true;
                }
            }
        }

        // construct mst
        List<Edge> mst = new ArrayList<>();
        for (int i = 0; i < numVertices; ++i) {
            if (edgeTo[i] != null)
                mst.add(edgeTo[i]);
        }

        return mst;
    }
}

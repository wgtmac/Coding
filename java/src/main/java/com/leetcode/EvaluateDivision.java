package com.leetcode;

import java.util.*;

/**
 * 399. Evaluate Division
 *
 * Equations are given in the format A / B = k, where A and B are variables
 * represented as strings, and k is a real number (floating point number).
 * Given some queries, return the answers. If the answer does not exist, return -1.0.
 *
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * The input is: vector<pair<string, string>> equations, vector<double>& values,
 * vector<pair<string, string>> queries , where equations.size() == values.size(),
 * and the values are positive. This represents the equations. Return vector<double>.
 *
 * According to the example above:
 *
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 * The input is always valid. You may assume that evaluating the queries will
 * result in no division by zero and there is no contradiction.
 */
public class EvaluateDivision {

    private static class Edge {
        String from, to;
        double val;
        Edge(double v, String f, String t) { val = v; from = f; to = t; }
    }

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        // init directed graph
        Map<String, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < values.length; ++i) {
            String n1 = equations[i][0], n2 = equations[i][1];
            if (!graph.containsKey(n1))
                graph.put(n1, new ArrayList<>());
            if (!graph.containsKey(n2))
                graph.put(n2, new ArrayList<>());
            graph.get(n1).add(new Edge(values[i], n1, n2));
            graph.get(n2).add(new Edge(1 / values[i], n2, n1)); // reverse edge
        }

        // BFS
        double[] ans = new double[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            String from = queries[i][0], to = queries[i][1];
            ans[i] = -1.0;

            if (graph.containsKey(from) && graph.containsKey(to)) {
                if (from.equals(to)) {
                    ans[i] = 1.0;
                    continue;
                }
                Set<String> visited = new HashSet<>();
                Queue<String> nQ = new LinkedList<>();
                Queue<Double> vQ = new LinkedList<>();

                // start point
                nQ.offer(from);
                vQ.offer(1.0);
                visited.add(from);

                boolean finished = false;
                while (!nQ.isEmpty() && !finished) {
                    int size = nQ.size();

                    for (int j = 0; j < size && !finished; ++j) {
                        String node = nQ.poll();
                        double result = vQ.poll();

                        // find its edges
                        for (Edge e : graph.get(node)) {
                            if (!visited.contains(e.to)) {
                                if (e.to.equals(to)) {
                                    // find destination
                                    ans[i] = result * e.val;
                                    finished = true;
                                    break;
                                } else {
                                    nQ.offer(e.to);
                                    vQ.offer(result * e.val);
                                    visited.add(e.to);
                                }

                            }
                        }
                    }
                }
            }
        }

        return ans;
    }
}

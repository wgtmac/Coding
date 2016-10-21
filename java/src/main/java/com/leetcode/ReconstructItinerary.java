package com.leetcode;


import java.util.*;

/**
 * 332. Reconstruct Itinerary
 *
 * Given a list of airline tickets represented by pairs of departure and arrival
 * airports [from, to], reconstruct the itinerary in order. All of the tickets
 * belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 *
 * Note:
 * If there are multiple valid itineraries, you should return the itinerary that
 * has the smallest lexical order when read as a single string. For example, the
 * itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 *
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 *
 * Example 1:
 * tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 *
 * Example 2:
 * tickets = [["JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"]]
 * Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 * Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But
 * it is larger in lexical order.
 */
public class ReconstructItinerary {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, Map<String, Integer>> nodes = new HashMap<>();

        // init graph
        for (int i = 0; i < tickets.length; ++i) {
            String from = tickets[i][0], to = tickets[i][1];
            if (!nodes.containsKey(from))
                nodes.put(from, new TreeMap<>());
            nodes.get(from).put(to, nodes.get(from).getOrDefault(to, 0) + 1);
        }

        List<String> list = new ArrayList<String>() {{add("JFK");}};
        DFS(nodes, "JFK", list, tickets.length);
        return list;
    }

    private boolean DFS(Map<String, Map<String, Integer>> nodes,
                        String from, List<String> list, int remaining) {
        if (remaining == 0) {
            return true;
        }

        if (nodes.containsKey(from)) {
            for (Map.Entry<String, Integer> to : nodes.get(from).entrySet()) {
                if (to.getValue() > 0) {
                    nodes.get(from).put(to.getKey(), to.getValue() - 1);
                    list.add(to.getKey());
                    if (DFS(nodes, to.getKey(), list, remaining - 1)) {
                        return true;
                    }
                    nodes.get(from).put(to.getKey(), to.getValue() + 1);
                    list.remove(list.size() - 1);
                }
            }
        }

        return false;
    }

    /**
     * Topological sorting solution
     *
     * @link: https://discuss.leetcode.com/topic/36370/short-ruby-python-java-c/2
     */
    private static class TopoRecursion {
        public List<String> findItinerary(String[][] tickets) {
            for (String[] ticket : tickets)
                targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
            visit("JFK");
            return route;
        }

        Map<String, PriorityQueue<String>> targets = new HashMap<>();
        List<String> route = new LinkedList();

        void visit(String airport) {
            while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
                visit(targets.get(airport).poll());
            route.add(0, airport);
        }
    }

    private static class TopoIteration {
        public List<String> findItinerary(String[][] tickets) {
            Map<String, PriorityQueue<String>> targets = new HashMap<>();
            for (String[] ticket : tickets)
                targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
            List<String> route = new LinkedList();
            Stack<String> stack = new Stack<>();
            stack.push("JFK");
            while (!stack.empty()) {
                while (targets.containsKey(stack.peek()) && !targets.get(stack.peek()).isEmpty())
                    stack.push(targets.get(stack.peek()).poll());
                route.add(0, stack.pop());
            }
            return route;
        }
    }
}

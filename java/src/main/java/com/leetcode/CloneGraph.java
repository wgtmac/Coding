package com.leetcode;

/**
 * 133. Clone Graph
 * 
 * Clone an undirected graph.
 * Each node in the graph contains a label and a list of its neighbors.
 * 
 * Skill: 
 *  同克隆linkedlist with random pointer
 * */

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;

        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        // DFS traversal
        DFS(node, map);
        return map.get(node);
    }
    
    private void DFS(UndirectedGraphNode node,
                     Map<UndirectedGraphNode, UndirectedGraphNode> map) {
        if (!map.containsKey(node)) {
            UndirectedGraphNode cloneNode = new UndirectedGraphNode(node.label);
            map.put(node, cloneNode);
        }

        UndirectedGraphNode cloneNode = map.get(node);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            if (!map.containsKey(neighbor)) {
                UndirectedGraphNode cloneNeighbor = new UndirectedGraphNode(neighbor.label);
                map.put(neighbor, cloneNeighbor);
                DFS(neighbor, map);
            }

            cloneNode.neighbors.add(map.get(neighbor));
        }
    }
    
	public UndirectedGraphNode cloneGraph_BFS (UndirectedGraphNode node) {
		if (node == null) return null;

		Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
		map.put(node, new UndirectedGraphNode(node.label));

        Queue<UndirectedGraphNode> q = new LinkedList<>();
        q.add(node);
		while (!q.isEmpty()) {
			UndirectedGraphNode currNode = q.poll();
			for (UndirectedGraphNode neighbor : currNode.neighbors) {
			    if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    q.add(neighbor);
                }
                map.get(currNode).neighbors.add(map.get(neighbor));
			}
		}
		return map.get(node);
	}

    private static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<>(); }
    }
}

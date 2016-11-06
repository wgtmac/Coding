package com.leetcode;

/**
 * 269. Alien Dictionary
 *
 * There is a new alien language which uses the latin alphabet. However, the order
 * among letters are unknown to you. You receive a list of words from the dictionary,
 * where words are sorted lexicographically by the rules of this new language.
 * Derive the order of letters in this language.
 *
 * For example,
 * Given the following words in dictionary,
 *
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 * The correct order is: "wertf".
 *
 * Note:
 *   You may assume all letters are in lowercase.
 *   If the order is invalid, return an empty string.
 *   There may be multiple valid order of letters, return any one of them is fine.
 */

import java.util.*;

/**
 * Solution:
 *   it's like a graph, reconstruct all edges
 *   topological sorting
 */
public class AlienDictionary {

    private Map<Character, Set<Character>> initGraph(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                if (!graph.containsKey(ch))
                    graph.put(ch, new HashSet<>());
            }
        }
        return graph;
    }

    private boolean validateAndAddEdges(String[] words, Map<Character, Set<Character>> graph) {
        for (int i = 1, j; i < words.length; ++i) {
            for (j = 0; j < Math.min(words[i].length(), words[i - 1].length()); ++j) {
                if (words[i - 1].charAt(j) != words[i].charAt(j)) {
                    graph.get(words[i - 1].charAt(j)).add(words[i].charAt(j));
                    break;
                }
            }
            // check violation: "wrtkj","wrt"
            if (j == words[i].length() && j < words[i - 1].length())
                return false;
        }
        return true;
    }

    private boolean topoSort(Map<Character, Set<Character>> graph, StringBuilder sorted) {
        Map<Character, Integer> inDegree = new HashMap<>();
        for (char src : graph.keySet())
            inDegree.put(src, 0);
        for (Set<Character> edges : graph.values()) {
            for (char dst : edges) {
                inDegree.put(dst, inDegree.get(dst) + 1);
            }
        }

        // BFS
        Queue<Character> queue = new LinkedList<>();
        for (Map.Entry<Character, Integer> vertices : inDegree.entrySet()) {
            if (vertices.getValue() == 0) queue.offer(vertices.getKey());
        }

        while (!queue.isEmpty()) {
            char src = queue.poll();
            sorted.append(src);

            // decrease indegree
            for (char dst : graph.get(src)) {
                inDegree.put(dst, inDegree.get(dst) - 1);
                if (inDegree.get(dst) == 0) queue.offer(dst);
            }
        }

        return sorted.length() == graph.size();   // check cycle
    }

    public String alienOrder(String[] words) {
        StringBuilder ans = new StringBuilder();

        Map<Character, Set<Character>> graph = initGraph(words);
        if (validateAndAddEdges(words, graph) && topoSort(graph, ans))
            return ans.toString();
        else
            return "";
    }

    public static void main(String[] args) {
        AlienDictionary a = new AlienDictionary();
//        String[] words = {"wrtkj","wrt"};
        String[] words = {"abc","abd"};
        System.out.println(a.alienOrder(words));
    }
}

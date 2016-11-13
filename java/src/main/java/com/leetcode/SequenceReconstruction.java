package com.leetcode;

import java.util.*;

/**
 * 444. Sequence Reconstruction
 *
 * Check whether the original sequence org can be uniquely reconstructed from the
 * sequences in seqs. The org sequence is a permutation of the integers from 1
 * to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common
 * supersequence of the sequences in seqs (i.e., a shortest sequence so that all
 * sequences in seqs are subsequences of it). Determine whether there is only one
 * sequence that can be reconstructed from seqs and it is the org sequence.
 *
 * Example 1:
 *
 * Input:
 * org: [1,2,3], seqs: [[1,2],[1,3]]
 *
 * Output:
 * false
 *
 * Explanation:
 * [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2]
 * is also a valid sequence that can be reconstructed.
 *
 * Example 2:
 *
 * Input:
 * org: [1,2,3], seqs: [[1,2]]
 *
 * Output:
 * false
 *
 * Explanation:
 * The reconstructed sequence can only be [1,2].
 *
 * Example 3:
 *
 * Input:
 * org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
 *
 * Output:
 * true
 *
 * Explanation:
 * The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
 *
 * Example 4:
 *
 * Input:
 * org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
 *
 * Output:
 * true
 */
public class SequenceReconstruction {
    /**
     * This is a graph problem.
     * Try to do topological sorting first.
     *   - If there are more than 1 number has 0-in-degree, then return false
     * Compare the sorted result with org
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        int[] indegree = new int[org.length + 1];

        // init neighbour list
        List<Integer>[] nexts = new List[org.length + 1];
        for (int i = 1; i < nexts.length; ++i) {
            nexts[i] = new ArrayList<>();
        }

        // add edges
        Set<Integer> nums = new HashSet<>();
        for (int[] edges : seqs) {
            if (edges.length > 0) nums.add(edges[0]);
            for (int i = 1; i < edges.length; ++i) {
                int src = edges[i - 1], dst = edges[i];
                if (src > org.length || dst > org.length || src < 1 || dst < 1)
                    return false;
                nexts[src].add(dst);
                indegree[dst]++;
                nums.add(dst);
            }
        }

        if (nums.size() != org.length) return false;

        // BFS topological sorting
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < indegree.length; ++i) {
            if (indegree[i] == 0)
                queue.offer(i);
        }

        // has no head or more than 1 number can be head
        if (queue.size() != 1) return false;

        List<Integer> sorted = new ArrayList<>();
        while (!queue.isEmpty()) {
            int src = queue.poll();
            sorted.add(src);

            for (int dst : nexts[src]) {
                if (--indegree[dst] == 0)
                    queue.offer(dst);
            }

            if (queue.size() > 1) return false;
        }

        // compare unique result
        if (sorted.size() != org.length) return false;

        for (int i = 0; i < org.length; ++i) {
            if (sorted.get(i) != org[i]) return false;
        }

        return true;
    }
}

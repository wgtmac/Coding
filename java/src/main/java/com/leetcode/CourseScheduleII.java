package com.leetcode;

/**
 * 210. Course Schedule II
 *
 *  There are a total of n courses you have to take, labeled from 0 to n - 1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs,
 * return the ordering of courses you should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them.
 * If it is impossible to finish all courses, return an empty array.
 *
 * For example:
 *
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0.
 * So the correct course order is [0,1]
 *
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
 * Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3].
 * Another correct ordering is[0,2,1,3].
*/

import java.util.*;

public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Graph[] course = new Graph[numCourses];
        Graph[] reverse = new Graph[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            course[i] = new Graph(i);
            reverse[i] = new Graph(i);
        }
        for (int i = 0; i < prerequisites.length; ++i) {
            int pre = prerequisites[i][1], cur = prerequisites[i][0];
            course[pre].edges.add(cur);
            reverse[cur].edges.add(pre);
        }
        // Topological sorting for reverse graph
        Stack<Integer> topological = new Stack<Integer>();
        for (int i = 0; i < numCourses; ++i) {
            if (!reverse[i].marked)
                DFS(reverse, i, topological);
        }
        
        // Forward DFS to find cycle
        while (!topological.empty()) {
            int v = topological.pop();
            if (DFS(course, v, null))
                return new int[0];
        }
        
        topological = new Stack<Integer>();
        for (int i = 0; i < numCourses; ++i) 
            course[i].marked = false;
        
        for (int i = 0; i < numCourses; ++i) {
            if (!course[i].marked)
                DFS(course, i, topological);
        }
        
        int[] order = new int[numCourses];
        for (int i = 0; i < numCourses; ++i) 
            order[i] = topological.pop();
        
        return order;
    }
    
    private boolean DFS (Graph[] g, int v, Stack<Integer> t) {
        boolean hasUnmarkedEdge = false;
        g[v].marked = true;
        
        if (g[v].edges.size() != 0) {
            for (int e : g[v].edges) {
                if (!g[e].marked) {
                    hasUnmarkedEdge = true;
                    DFS(g, e, t);
                }
            }
        }
        
        if (t != null) t.push(v);
        
        return hasUnmarkedEdge;
    }
    
    public static class Graph {
        public int vertex;
        private List<Integer> edges;
        public boolean marked;
        public Graph(int i) {
            vertex = i;
            marked = false;
            edges = new ArrayList<>();
        }
    }

    private static class OptimizedSolution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // construct graph
            List<Integer>[] edges = new List[numCourses];
            for (int i = 0; i < numCourses; ++i)
                edges[i] = new ArrayList<>();
            for (int i = 0; i < prerequisites.length; ++i)
                edges[prerequisites[i][1]].add(prerequisites[i][0]);

            boolean[] visited = new boolean[numCourses];
            Set<Integer> path = new HashSet<Integer>();
            Stack<Integer> stack = new Stack<Integer>();

            for (int i = 0; i < numCourses; ++i) {
                if (!visited[i]) {
                    if (!DFS(edges, i, visited, path, stack))
                        return new int[0];
                }
            }

            int[] sorted = new int[numCourses];
            for (int i = 0; i < numCourses; ++i)
                sorted[i] = stack.pop();
            return sorted;
        }

        private boolean DFS(List<Integer>[] edges, int index, boolean[] visited,
                            Set<Integer> path, Stack<Integer> stack) {
            visited[index] = true;
            path.add(index);

            for (int next : edges[index]) {
                if (path.contains(next))
                    return false;

                if (!visited[next])
                    if (!DFS(edges, next, visited, path, stack))
                        return false;
            }

            path.remove(index);
            stack.push(index);
            return true;
        }
    }
}
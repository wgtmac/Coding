package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 207. Course Schedule
 *
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have to
 * first take course 1, which is expressed as a pair: [0,1]. Given the total
 * number of courses and a list of prerequisite pairs, is it possible for you to
 * finish all courses?
 *
 * For example:
 *
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have
 * finished course 0. So it is possible.
 *
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have
 * finished course 0, and to take course 0 you should also have finished course
 * 1. So it is impossible.
 *
 * Kosaraju–Sharir algorithm: Find strongly-connected components
 * 图反向后拓扑排序
 * 根据反向拓扑排序的点，进行正向的DFS
 */

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
                return false;
        }
        
        return true;
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
    
    private static class Graph {
        public int vertex;
        public List<Integer> edges;
        public boolean marked;
        public Graph(int i) {
            vertex = i;
            marked = false;
            edges = new ArrayList<>();
        }
    }
}
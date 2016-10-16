package gwu.impl;

import java.util.Arrays;

/**
 * Created by wgtmac on 10/8/16.
 *
 * Union Find algorithm is very efficient to find it two objects are inside the
 * same subsets (connected).
 *
 * e.g. try to union every edge of a graph. if find the two vertexes of a edge is
 * already connected before unionizing them, then the graph is cyclic.
 */
public class UnionFind {

    private int[] parents = null;
    private int[] ranks = null;

    public UnionFind(int num) {
        parents = new int[num];
        ranks = new int[num];
        for (int i = 0; i < num; ++i)
            parents[i] = i;
    }

    /**
     * find if two objects are inside same subset
     *
     * Time complexity: O(n)
     */
    public boolean isSameSubset(int x, int y) {
        return find(x) == find(y);
    }

    /**
     * find the parent of one object
     */
    public int find(int x) {
        int parent = x;
        while (parents[parent] != parent)
            parent = parents[parent];
        parents[x] = parent;
        return parents[x];
    }

    /**
     * union two objects in the same subset
     *
     * Time complexity: O(n)
     */
    public void union(int x, int y) {
        int parentX = find(x), parentY = find(y);

        if (parentX != parentY) {
            if (ranks[parentX] < ranks[parentY]) {
                parents[x] = parentY;
            } else if (ranks[parentX] > ranks[parentY]) {
                parents[y] = parentX;
            } else {
                parents[x] = parentY;
                ranks[parentY] += 1;
            }
        }
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
        uf.union(8, 7);
        uf.union(7, 6);
        uf.union(6, 5);
        uf.union(5, 4);
        uf.union(4, 3);
        uf.union(3, 2);
        System.out.println(Arrays.toString(uf.parents));
        System.out.println(uf.isSameSubset(3, 5));
        System.out.println(uf.isSameSubset(0, 8));
    }
}

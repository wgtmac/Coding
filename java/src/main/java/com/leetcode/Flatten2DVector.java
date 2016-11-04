package com.leetcode;

import java.util.Iterator;
import java.util.List;

/**
 * 251. Flatten 2D Vector
 *
 * Implement an iterator to flatten a 2d vector.
 *
 * For example,
 * Given 2d vector =
 *
 * [
 *   [1,2],
 *   [3],
 *   [4,5,6]
 * ]
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
 *
 */
public class Flatten2DVector {
    private static class Vector2D implements Iterator<Integer> {

        int idx1, idx2;
        List<List<Integer>> vec2d;

        public Vector2D(List<List<Integer>> vec2d) {
            idx1 = idx2 = 0;
            this.vec2d = vec2d;
        }

        @Override
        public Integer next() {
            return vec2d.get(idx1).get(idx2++);
        }

        @Override
        public boolean hasNext() {
            if (vec2d == null || vec2d.isEmpty())
                return false;

            while (idx1 < vec2d.size() && idx2 >= vec2d.get(idx1).size()) {
                idx1++;
                idx2 = 0;
            }

            return idx1 < vec2d.size() && idx2 < vec2d.get(idx1).size();
        }
    }
}

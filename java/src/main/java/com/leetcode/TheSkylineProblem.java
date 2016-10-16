package com.leetcode;

import java.util.*;

/**
 * 218. The Skyline Problem
 *
 * A city's skyline is the outer contour of the silhouette formed by all the
 * buildings in that city when viewed from a distance. Now suppose you are given
 * the locations and height of all the buildings as shown on a cityscape photo
 * (Figure A), write a program to output the skyline formed by these buildings
 * collectively (Figure B).
 *
 * The geometric information of each building is represented by a triplet of
 * integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and
 * right edge of the ith building, respectively, and Hi is its height. It is
 * guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You
 * may assume all buildings are perfect rectangles grounded on an absolutely flat
 * surface at height 0.
 *
 * For instance, the dimensions of all buildings in Figure A are recorded as:
 * [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 * The output is a list of "key points" (red dots in Figure B) in the format of
 * [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key
 * point is the left endpoint of a horizontal line segment. Note that the last
 * key point, where the rightmost building ends, is merely used to mark the
 * termination of the skyline, and always has zero height. Also, the ground in
 * between any two adjacent buildings should be considered part of the skyline contour.
 *
 * For instance, the skyline in Figure B should be represented as:
 * [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 *
 * Notes:
 *
 * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 * The input list is already sorted in ascending order by the left x position Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output skyline.
 * For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable;
 * the three lines of height 5 should be merged into one in the final output as
 * such: [...[2 3], [4 5], [12 7], ...]
 */

public class TheSkylineProblem {
    public List<int[]> getSkyline(int[][] buildings) {
        // store all blocks that will not overlap with subsequent buildings
        List<int[]> resultList = new ArrayList<> ();

        // store all blocks that before current which may overlap with current
        // elements inside may be further split;
        // NOTE: all blocks inside are back to back w/o gap
        Queue<int[]> queue = new LinkedList<> ();

        for (int[] curr : buildings) {
            /**
             * all blocks on the left of curr[0] should be put into list
             */
            Queue<int[]> tmpQueue = new LinkedList<> ();
            while (!queue.isEmpty()) {
                int[] prev = queue.poll();

                if (curr == null) {
                    // current building is processed
                    tmpQueue.offer(prev);
                } else if (prev[1] <= curr[0]) {
                    // prev is totally on the left of curr w/o overlapping
                    // there won't be any case that curr is on the left of prev
                    // since all blocks in queue are back to back
                    resultList.add(prev);
                } else if (curr[1] <= prev[1]) {
                    /**
                     * 1. curr block is inside prev block, so [prev.left, curr.left]
                     * can be thrown into list, it won't overlap
                     * 2. partition prev into 3 parts
                     */
                    // left part is non-zero
                    if (prev[0] != curr[0])
                        resultList.add(new int[] {prev[0], curr[0], prev[2]});

                    // compare the height of middle part: overlapping with curr
                    if (prev[2] < curr[2]) {
                        tmpQueue.offer(curr);
                        prev[0] = curr[1];
                    } else
                        prev[0] = curr[0];

                    // right part is non-zero
                    if (prev[0] != prev[1])
                        tmpQueue.offer(prev);

                    curr = null;  // curr is fully covered, no need to process it
                } else {
                    /**
                     * prev and curr overlaps but curr has a tail
                     * partition prev and curr into 2 parts
                     */
                    // left prev is non-zero
                    if (prev[0] != curr[0])
                        resultList.add(new int[] {prev[0], curr[0], prev[2]});

                    // mid part updates height: overlapping of prev and curr
                    prev[0] = curr[0];
                    prev[2] = Math.max(prev[2], curr[2]);
                    tmpQueue.offer(prev);

                    // right part is the remaining part of curr block
                    curr[0] = prev[1];
                }
            }

            if (curr != null) tmpQueue.offer(curr);
            queue = tmpQueue;
        }

        resultList.addAll(queue);

        // merge
        List<int[]> result = new ArrayList<> ();
        int[] prev = null;
        for (int[] curr : resultList) {
            if (prev == null) {                // first building
                result.add(new int[]{curr[0], curr[2]});
            } else if (prev[1] != curr[0]) {   // not continuous
                result.add(new int[]{prev[1], 0});
                result.add(new int[]{curr[0], curr[2]});
            } else if (prev[2] != curr[2]) {   // diff height
                result.add(new int[]{curr[0], curr[2]});
            }
            prev = curr;
        }
        if (prev != null)                      // last building
            result.add(new int[]{prev[1], 0});
        return result;
    }
}

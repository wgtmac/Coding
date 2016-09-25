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
        Deque<Building> todoQueue = new LinkedList<>(), processedQueue = new LinkedList<>();
        Queue<Building> resultQueue = new LinkedList<>();

        for (int i = 0; i < buildings.length; ++i) {
            Building curr = new Building(buildings[i]);

            // dequeue all buildings that do not intersect with current one
            while (!todoQueue.isEmpty() && todoQueue.peek().right <= curr.left)
                resultQueue.offer(todoQueue.poll());

            // break curr building to merge with prev buildings
            while (!todoQueue.isEmpty()) {
                Building prev = todoQueue.poll();

                if (curr == null) {
                    processedQueue.offer(prev);
                } else if (prev.right >= curr.right) {
                    /**
                     * Prev building fully contains curr building
                     * so prev building needs to be divided into 3 parts:
                     * PREV=(PREV1, CURR+PREV2, PREV3)
                     */
                    if (curr.height > prev.height) {
                        if (prev.left != curr.left)
                            processedQueue.offer(new Building(prev.left, curr.left, prev.height));
                        if (prev.right != curr.right) {
                            prev.left = curr.right;
                            todoQueue.offerFirst(prev);
                        }
                        todoQueue.offerFirst(curr);
                    } else  // fully covered, put it back
                        todoQueue.offerFirst(prev);
                    curr = null;
                } else {
                    /**
                     * Prev building only overlaps with curr building
                     * PREV=(PREV1, PREV2+CURR1), CURR2
                     */
                    int boundary = prev.right;
                    if (prev.height < curr.height) {
                        if (prev.left != curr.left)
                            processedQueue.offer(new Building(prev.left, curr.left, prev.height));
                        prev.left = curr.left;
                        prev.height = curr.height;
                        processedQueue.offer(prev);
                    } else
                        processedQueue.offer(prev);

                    // break curr for further comparision
                    curr.left = boundary;
                }
            }

            // Easy to be ignored, last building in the list
            if (curr != null) processedQueue.offer(curr);

            // switch queues
            Deque<Building> tmp = processedQueue;
            processedQueue = todoQueue;
            todoQueue = tmp;
        }

        // Easy to be ignored, un-compared buildings
        while (!todoQueue.isEmpty())
            resultQueue.offer(todoQueue.poll());

        // merge outputs
        List<int[]> list = new ArrayList<>();
        Building prev = resultQueue.isEmpty() ? null : resultQueue.poll();

        while (!resultQueue.isEmpty()) {
            Building curr = resultQueue.poll();
            if (prev != null && prev.height == curr.height) {
                if (prev.right != curr.left)
                    addNewBuilding(prev, list);
                curr.left = prev.left;
            } else if (prev != null) {
                if (prev.right == curr.left)
                    list.add(new int[] {prev.left, prev.height});
                else
                    addNewBuilding(prev, list);
            }
            prev = curr;
        }

        // Easy to be ignored, last building in the list
        if (prev != null)
            addNewBuilding(prev, list);

        return list;
    }

    private void addNewBuilding(Building building, List<int[]> list) {
        list.add(new int[] {building.left, building.height});
        list.add(new int[] {building.right, 0});
    }

    private static class Building {
        int left, right, height;

        public Building(int[] building) {
            left = building[0];
            right = building[1];
            height = building[2];
        }

        public Building(int l, int r, int h) {
            left = l;
            right = r;
            height = h;
        }
    }
}

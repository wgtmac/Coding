package com.leetcode;

import java.util.*;

/**
 * 406. Queue Reconstruction by Height
 *
 * Suppose you have a random list of people standing in a queue. Each person is
 * described by a pair of integers (h, k), where h is the height of the person
 * and k is the number of people in front of this person who have a height greater
 * than or equal to h. Write an algorithm to reconstruct the queue.
 *
 * Note:
 * The number of people is less than 1,100.
 *
 * Example
 *
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */

/**
 * Each time choose the current least number. Since all remaining numbers are
 * no-smaller than it, so its index is the final index in all vacant spots.
 */
public class QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return Integer.compare(o2[1], o1[1]);
                else
                    return Integer.compare(o1[0], o2[0]);
            }
        });

        int[][] result = new int[people.length][];
        for (int[] person : people) {
            for (int i = 0, count = 0; i < result.length; ++i) {
                if (result[i] == null) {
                    if (count++ == person[1]) {
                        result[i] = person;
                        break;
                    }
                }
            }
        }
        return result;
    }
}

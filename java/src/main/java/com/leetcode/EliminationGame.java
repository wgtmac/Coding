package com.leetcode;

/**
 * 390. Elimination Game
 *
 * There is a list of sorted integers from 1 to n. Starting from left to right,
 * remove the first number and every other number afterward until you reach the
 * end of the list.
 *
 * Repeat the previous step again, but this time from right to left, remove the
 * right most number and every other number from the remaining numbers.
 *
 * We keep repeating the steps again, alternating left to right and right to left,
 * until a single number remains.
 *
 * Find the last number that remains starting with a list of length n.
 *
 * Example:
 *
 * Input:
 * n = 9,
 * 1 2 3 4 5 6 7 8 9
 * 2 4 6 8
 * 2 6
 * 6
 *
 * Output:
 * 6
 */

/**
 * Hint:
 * diff is doubled, track head and tail.
 */
public class EliminationGame {
    public int lastRemaining(int n) {
        int head = 1, tail = n, diff = 1;
        boolean fromLeft = true;
        while (head != tail) {
            if (fromLeft) {
                if (shouldRemoveOtherSide(tail, head, diff << 1))
                    tail -= diff;
                head += diff;
            } else {
                if (shouldRemoveOtherSide(tail, head, diff << 1))
                    head += diff;
                tail -= diff;
            }
            diff <<= 1;
            fromLeft = !fromLeft;
        }
        return head;
    }

    private boolean shouldRemoveOtherSide(int tail, int head, int nextDiffUnit) {
        int gap = tail - head;
        return gap >= nextDiffUnit && gap % nextDiffUnit == 0;
    }
}

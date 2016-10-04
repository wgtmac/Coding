package com.leetcode;

/**
 * 335. Self Crossing
 *
 * You are given an array x of n positive numbers. You start at point (0,0) and
 * moves x[0] metres to the north, then x[1] metres to the west, x[2] metres to
 * the south, x[3] metres to the east and so on. In other words, after each move
 * your direction changes counter-clockwise.
 *
 * Write a one-pass algorithm with O(1) extra space to determine, if your path
 * crosses itself, or not.
 *
 * Example 1:
 * Given x = [2, 1, 1, 2],
 * ┌───┐
 * │   │
 * └───┼──>
 * │
 * Return true (self crossing)
 *
 * Example 2:
 * Given x = [1, 2, 3, 4],
 * ┌──────┐
 * │      │
 * │
 * │
 * └────────────>
 * Return false (not self crossing)
 *
 * Example 3:
 * Given x = [1, 1, 1, 1],
 * ┌───┐
 * │   │
 * └───┼>
 * Return true (self crossing)
 *
 * */

/**
 * Hint:
 * Only following cases are acceptable:
 * 1. ascending;
 * 2. ascending first then descending within safe distance;
 * 3. descending within safe distance.
 * Others will self-cross absolutely
 * */
public class SelfCrossing {
    enum State {
        UNDECIDED, ASCENDING, DESCENDING, ASCE_THEN_DESC;
    }

    public boolean isSelfCrossing(int[] x) {
        State state = State.UNDECIDED;
        for (int i = 2; i < x.length; i++) {
            switch (state) {
                case UNDECIDED:
                    if (x[i] > x[i - 2])      state = State.ASCENDING;
                    else if (x[i] < x[i - 2]) state = State.DESCENDING;
                    else                      if (i == 3) return true;  // square
                    break;
                case ASCENDING:
                    if (x[i] <= x[i - 2])     state = State.ASCE_THEN_DESC;
                    break;
                case DESCENDING:
                    if (x[i] >= x[i - 2])     return true;
                    break;
                case ASCE_THEN_DESC:
                    if (isCrossed(x, i) && isCrossed(x, i - 1))
                        return true;
                    else  // only descending is allowed
                        state = State.DESCENDING;
                    break;
                default:    // other cases are all self-crossing
                    return true;
            }

        }
        return false;
    }

    private boolean isCrossed(int[] x, int i) {
        return x[i] >= Math.abs(x[i - 2] - (i >= 4 ? x[i - 4] : 0));
    }

    public static void main(String[] args) {
        SelfCrossing sc = new SelfCrossing();
        int[] a = {2, 1, 1, 2};
        System.out.println(sc.isSelfCrossing(a));

        int[] b = {1, 2, 3, 4};
        System.out.println(sc.isSelfCrossing(b));

        int[] c = {1, 1, 1, 1};
        System.out.println(sc.isSelfCrossing(c));

        int[] d = {3, 3, 4, 2, 2};
        System.out.println(sc.isSelfCrossing(d));

        int[] e = {1,1,2,2,3,3,4,4,10,4,4,3,3,2,2,1,1};
        System.out.println(sc.isSelfCrossing(e));
    }
}

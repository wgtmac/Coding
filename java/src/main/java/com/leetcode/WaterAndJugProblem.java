package com.leetcode;

/**
 * 365. Water and Jug Problem
 *
 * You are given two jugs with capacities x and y litres. There is an infinite
 * amount of water supply available. You need to determine whether it is possible
 * to measure exactly z litres using these two jugs.
 *
 * If z liters of water is measurable, you must have z liters of water contained
 * within one or both buckets by the end.
 *
 * Operations allowed:
 *
 * 1) Fill any of the jugs completely with water.
 * 2) Empty any of the jugs.
 * 3) Pour water from one jug into another till the other jug is completely full
 *    or the first jug itself is empty.
 *
 * Example 1: (From the famous "Die Hard" example)
 * Input: x = 3, y = 5, z = 4
 * Output: True
 *
 * Example 2:
 * Input: x = 2, y = 6, z = 5
 * Output: False
 */
public class WaterAndJugProblem {
    /**
     * m * x + n * y = z has solutions: z = k * greatest_common_divisor(x, y)
     */
    public boolean canMeasureWater(int x, int y, int z) {
        int gcd = greatest_common_divisor(x, y);
        return z == 0 || ((x + y) >= z && gcd != 0 && (z / gcd * gcd) == z);
    }

    private int greatest_common_divisor(int x, int y) {
        do {
            if (x > y) {
                x ^= y; y ^= x; x ^= y;
            }
            y = y - x;
            if (x > y) {
                x ^= y; y ^= x; x ^= y;
            }
        } while (x != 0);
        return y;
    }


    public static void main(String[] args) {
        WaterAndJugProblem wjp = new WaterAndJugProblem();

        System.out.println(wjp.canMeasureWater(0, 0, 0));
        System.out.println(wjp.canMeasureWater(3, 5, 4));
        System.out.println(wjp.canMeasureWater(2, 6, 5));
        System.out.println(wjp.canMeasureWater(104681, 104683, 54));
        System.out.println(wjp.canMeasureWater(1, 1, 12));
    }
}

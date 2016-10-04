package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 264. Ugly Number II
 *
 * Write a program to find the n-th ugly number.
 *
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 *
 * Note that 1 is typically treated as an ugly number.
 *
 * Hint: growth approach
 * for every
 */
public class UglyNumberII {
    public int nthUglyNumber(int n) {
        Queue<Integer> multiple2 = new LinkedList<Integer>() {{offer(2);}},
                multiple3 = new LinkedList<Integer>() {{offer(3);}},
                multiple5 = new LinkedList<Integer>() {{offer(5);}};

        int num = 1;
        for(; n > 1; --n) {
            num = Math.min(multiple2.peek(), Math.min(multiple3.peek(), multiple5.peek()));

            if (multiple2.peek() == num) multiple2.poll();
            if (multiple3.peek() == num) multiple2.poll();
            if (multiple5.peek() == num) multiple2.poll();

            multiple2.offer(num * 2);
            multiple3.offer(num * 3);
            multiple5.offer(num * 5);
        }

        return num;
    }


    public static void main(String[] args) {
        UglyNumberII u = new UglyNumberII();
        System.out.println(u.nthUglyNumber(1));
        System.out.println(u.nthUglyNumber(2));
        System.out.println(u.nthUglyNumber(3));
        System.out.println(u.nthUglyNumber(4));
        System.out.println(u.nthUglyNumber(9));
        System.out.println(u.nthUglyNumber(10));
        System.out.println(u.nthUglyNumber(11));

    }
}

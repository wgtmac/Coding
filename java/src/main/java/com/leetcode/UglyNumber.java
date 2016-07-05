package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class UglyNumber {
    /**
     * 263. Ugly Number

     Write a program to check whether a given number is an ugly number.

     Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
     For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.

     Note that 1 is typically treated as an ugly number.
     */
    public boolean isUgly(int num) {
        if (num == 0) return false;

        while (num % 2 == 0) num /= 2;
        while (num % 3 == 0) num /= 3;
        while (num % 5 == 0) num /= 5;

        if (num == 1) return true;
        else return false;
    }

    /**
     * 264. Ugly Number II
     *
     Write a program to find the n-th ugly number.

     Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
     For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

     Note that 1 is typically treated as an ugly number.
     */
    public int nthUglyNumber(int n) {
        int num = 1;

        Queue<Integer> l2 = new LinkedList<>();
        Queue<Integer> l3 = new LinkedList<>();
        Queue<Integer> l5 = new LinkedList<>();
        l2.offer(2);
        l3.offer(3);
        l5.offer(5);

        while (n > 1) {
            while (l2.peek() <= num) {
                l2.poll();
            }
            while (l3.peek() <= num) {
                l3.poll();
            }
            while (l5.peek() <= num) {
                l5.poll();
            }

            if (l2.peek() <= l3.peek() && l2.peek() <= l5.peek()) {
                num = l2.poll();
            } else if (l3.peek() <= l2.peek() && l3.peek() <= l5.peek()) {
                num = l3.poll();
            } else {
                num = l5.poll();
            }

            l2.offer(num * 2);
            l3.offer(num * 3);
            l5.offer(num * 5);

            --n;
        }

        return num;
    }

    public static void main(String[] args) {
        UglyNumber u = new UglyNumber();
        System.out.println(u.nthUglyNumber(1));
        System.out.println(u.nthUglyNumber(2));
        System.out.println(u.nthUglyNumber(3));
        System.out.println(u.nthUglyNumber(4));
        System.out.println(u.nthUglyNumber(9));
        System.out.println(u.nthUglyNumber(10));
        System.out.println(u.nthUglyNumber(11));

    }
}

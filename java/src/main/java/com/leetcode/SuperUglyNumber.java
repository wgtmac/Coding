package com.leetcode;

import java.util.*;

/**
 * 313. Super Ugly Number
 *
 * Write a program to find the n-th super ugly number.
 *
 * Super ugly numbers are positive numbers whose all prime factors are in the
 * given prime list _primes_ of size _k_. For example, [1, 2, 4, 7, 8, 13, 14, 16,
 * 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given
 * primes = [2, 7, 13, 19] of size 4.
 *
 * Note:
 * (1) 1 is a super ugly number for any given primes.
 * (2) The given numbers in primes are in ascending order.
 * (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 */
public class SuperUglyNumber {

    /**
     * If every time getting an ugly number, we use it times every prime and append
     * to the prime's list, then we will get TLE because many unused numbers are
     * generated. Instead, use a lazy approach: record all ugly numbers that are
     * generated, then compute corresponding ugly number in each queue when needed
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];

        // for each prime, we imagine an invisible list for all multiplication of ugly numbers
        // i.e. the list for prime k=primes[i] will be ugly[0]*k, ugly[1]*k, ugly[2]*k, etc.
        // index[i] is the current index in the list of prime k=primes[i]
        int[] index = new int[primes.length];

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer idx1, Integer idx2) {
                return getCurrUglyForPrimeI(index, ugly, primes, idx1) -
                        getCurrUglyForPrimeI(index, ugly, primes, idx2);
            }
        });

        ugly[0] = 1;
        int count = 1;

        for (int i = 0; i < primes.length; ++i) minHeap.offer(i);

        while (count < n) {
            // de-duplicate
            while (ugly[count - 1] >= getCurrUglyForPrimeI(index, ugly, primes, minHeap.peek())) {
                ++index[minHeap.peek()];
                minHeap.offer(minHeap.poll());
            }

            ugly[count++] = getCurrUglyForPrimeI(index, ugly, primes, minHeap.peek());
            index[minHeap.peek()]++;
            minHeap.offer(minHeap.poll());
        }

        return ugly[n - 1];
    }

    private int getCurrUglyForPrimeI(int[] index, int[] ugly, int[] primes, int idx) {
        return ugly[index[idx]] * primes[idx];
    }
}

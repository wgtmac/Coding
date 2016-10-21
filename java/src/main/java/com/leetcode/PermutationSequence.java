package com.leetcode;

/**
 * 60. Permutation Sequence
 * 
 * The keys [1,2,3,…,n] contains a total of n! unique permutations.
 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3):
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 *
 * Given n and k, return the kth permutation sequence.
 * 
 * Note: Given n will be between 1 and 9 inclusive.
 * 
 * hint:
 * 数学方法计算  assign k = 0 ~ n! - 1
 * n位共有n!种排列  k / (i-1)! % 10 为几就能确定第i位是剩余的第几小的数
 *      k      k/2!   (k/2!)%2!
 * 123  0       0         0
 * 132  1       0         1
 * 213  2       1         0
 * 231  3       1         1
 * 312  4       2         0
 * 321  5       2         1
 */

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n + 1];
        List<Integer> list = new ArrayList<>();

        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = i * factorial[i - 1];
            list.add(i);
        }

        k -= 1;

        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            sb.append(list.remove(k / factorial[i]));
            k %= factorial[i];
        }
        
        return sb.toString();
    }
}

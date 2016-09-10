package com.leetcode;

/**
 * 89. Gray Code
 * 
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * Given a non-negative integer n representing the total number of bits in the code,
 * print the sequence of gray code.
 * A gray code sequence must begin with 0.
 * 
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * Note:
 * For a given n, a gray code sequence is not uniquely defined.
 * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
 * For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 *  
 * Skill:
 * HashSet存已经添加的数
 * 每次循环，从右到左更改一位
 * 直到重复数出现
 */

import java.util.*;

public class GrayCode {

    public List<Integer> grayCode(int n) {
        Set<Integer> set = new LinkedHashSet<>();
        
        int num = 0, length = 1 << n;
        set.add(num);

        int nextNum;
        while (set.size() < length) {
            for (int i = 0; i < n; i++) {
                nextNum = num ^ (1 << i);
                if (!set.contains(nextNum)) {
                    num = nextNum;
                    break;
                }
            }
            set.add(num);
        }

        return new ArrayList<>(set);
    }
}

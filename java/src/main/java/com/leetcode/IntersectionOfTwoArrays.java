package com.leetcode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 349. Intersection of Two Arrays
 *
 *
 */
public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
        for (int num1 : nums1) set1.add(num1);
        for (int num2 : nums2) set2.add(num2);
        set1.retainAll(set2);
        int[] result = new int[set1.size()];
        Iterator<Integer> setIter = set1.iterator();
        for (int i = 0; i < result.length; ++i)
            result[i] = setIter.next();
        return result;
    }
}

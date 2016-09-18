package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 179. Largest Number
 * 
 * Given a list of non negative integers, arrange them such that they form the
 * largest number. For example, given [3, 30, 34, 5, 9], the largest formed number
 * is 9534330.
 *
 * Note: The result may be very large, so you need to return a string instead of an integer.
 */

public class LargestNumber {
	
    public String largestNumber(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1 = Integer.toString(o1);
                String s2 = Integer.toString(o2);
                return (s2 + s1).compareTo(s1 + s2);
            }
        });

        for (int num : nums)
            pq.offer(num);

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty())
            sb.append(pq.poll());

        // check starting zeros
        String number = sb.toString();
        int i = 0;
        while (i < number.length() && number.charAt(i) == '0') i++;

        if (i == number.length()) return "0";
        else return number.substring(i);
    }
    
    public static void main (String[] args) {
    	LargestNumber t = new LargestNumber ();
    	System.out.println(t.largestNumber(new int[] {0,0}));
    }
}

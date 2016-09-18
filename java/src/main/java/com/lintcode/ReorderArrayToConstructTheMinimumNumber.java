package com.lintcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 379. Reorder array to construct the minimum number
 *
 * Construct minimum number by reordering a given non-negative integer array.
 * Arrange them such that they form the minimum number.
 *
 * Example
 * Given [3, 32, 321], there are 6 possible numbers can be constructed by reordering the array:
 *
 * 3+32+321=332321
 * 3+321+32=332132
 * 32+3+321=323321
 * 32+321+3=323213
 * 321+3+32=321332
 * 321+32+3=321323
 * So after reordering, the minimum number is 321323, and return it.
 */
public class ReorderArrayToConstructTheMinimumNumber {

    /**
     * @param nums n non-negative integer array
     * @return a string
     */
    public String minNumber(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == o2) return 0;
                else if (o1 == null) return -1;
                else if (o2 == null) return 1;
                String s1 = Integer.toString(o1);
                String s2 = Integer.toString(o2);
                return (s1 + s2).compareTo(s2 + s1);
            }
        });

        for (int num : nums)
            pq.offer(num);

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty())
            sb.append(pq.poll());

        return sb.toString();
    }

    public static void main(String[] args) {
        ReorderArrayToConstructTheMinimumNumber s = new ReorderArrayToConstructTheMinimumNumber();
        System.out.println(s.minNumber(new int[] {3, 32, 321}));
    }
}

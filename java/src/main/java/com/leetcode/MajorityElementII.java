package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 229. Majority Element II
 *
 * Given an integer array of size n, find all elements that appear more than
 * ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.
 *
 * Hint: How many majority elements could it possibly have?
 *
 * Boyer–Moore majority vote algorithm
 */

public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        int major1 = -1, major2 = -1, major1Cnt = 0, major2Cnt = 0;
        for (int num : nums) {
            if (major1Cnt == 0 || major2Cnt == 0) {
                if (major1Cnt == 0 && major2Cnt == 0) {
                    major1 = num;
                    major1Cnt = 1;
                } else if (major2Cnt == 0 && major1 != num) {
                    major2 = num;
                    major2Cnt = 1;
                } else if (major2Cnt == 0 && major1 == num) {
                    major1Cnt++;
                } else if (major1Cnt == 0 && major2 != num) {
                    major1 = num;
                    major1Cnt = 1;
                } else {
                    major2Cnt++;
                }
            } else {
                if (major1 == num)
                    major1Cnt++;
                else if (major2 == num)
                    major2Cnt++;
                else {
                    major1Cnt--;
                    major2Cnt--;
                }
            }
        }

        // should be two-pass
        major1Cnt = major2Cnt = 0;
        for (int num : nums) {
            if (num == major1)
                major1Cnt++;
            else if (num == major2)
                major2Cnt++;
        }

        List<Integer> list = new ArrayList<>();
        if (major1Cnt > nums.length / 3)
            list.add(major1);
        if (major2Cnt > nums.length / 3)
            list.add(major2);
        return list;
    }

    public static void main(String[] args) {
        MajorityElementII m = new MajorityElementII();
        System.out.println(m.majorityElement(new int[] {8,8,7,7,7}));
    }
}

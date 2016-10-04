package com.leetcode;

/**
 * 275. H-Index II
 * Follow up for H-Index: What if the citations array is sorted in ascending order?
 * Could you optimize your algorithm?
 * */

public class HIndexII {

    public int hIndexOpt(int[] citations) {
        for (int h = citations.length; h > 0; --h) {
            if (citations[citations.length - h] >= h) {
                return h;
            }
        }

        return 0;
    }

}

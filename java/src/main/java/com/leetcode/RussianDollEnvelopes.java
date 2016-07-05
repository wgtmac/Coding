package com.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 354. Russian Doll Envelopes
 You have a number of envelopes with widths and heights given as a pair of integers (w, h).
 One envelope can fit into another if and only if both the width and height of one envelope
 is greater than the width and height of the other envelope.

 What is the maximum number of envelopes can you Russian doll? (put one inside other)

 Example:
 Given envelopes = [[5,4],[6,4],[6,7],[2,3]],
 the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
public class RussianDollEnvelopes {
    // Find longest increasing sub-array
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // k[i]: longest increasing subarray for envelopes[i][1]
        int[] k = new int[envelopes.length];
        int maxLen = 0;
        for (int i = 0; i < envelopes.length; ++i) {
            k[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (envelopes[j][0] != envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                    k[i] = Math.max(k[i], k[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, k[i]);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        RussianDollEnvelopes r = new RussianDollEnvelopes();
        System.out.println(r.maxEnvelopes(new int[][] {{5,4},{6,4},{6,7},{2,3}}));
        System.out.println(r.maxEnvelopes(new int[][] {{4,5},{4,6},{6,7},{2,3},{1,1}}));
    }
}

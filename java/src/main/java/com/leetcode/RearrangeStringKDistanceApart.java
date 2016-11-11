package com.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 358. Rearrange String k Distance Apart
 *
 * Given a non-empty string str and an integer k, rearrange the string such that
 * the same characters are at least distance k from each other.
 *
 * All input strings are given in lowercase letters. If it is not possible to
 * rearrange the string, return an empty string "".
 *
 * Example 1:
 * str = "aabbcc", k = 3
 * Result: "abcabc"
 * The same letters are at least distance 3 from each other.
 *
 * Example 2:
 * str = "aaabc", k = 3
 * Answer: ""
 * It is not possible to rearrange the string.
 *
 * Example 3:
 * str = "aaadbbcc", k = 2
 * Answer: "abacabcd"
 * Another possible answer is: "abcabcda"
 * The same letters are at least distance 2 from each other.
 */

public class RearrangeStringKDistanceApart {

    public static void main(String[] args) {
        RearrangeStringKDistanceApart r = new RearrangeStringKDistanceApart();
        System.out.println(r.rearrangeString("aabbcc", 4));  // ""
        System.out.println(r.rearrangeString("aabbcc", 3));  // "acbacb"
        System.out.println(r.rearrangeString("a", 2));       // "a"
        System.out.println(r.rearrangeString("abcabcabcd", 4));
    }

    public String rearrangeString(String str, int k) {

        // init map for (char -> count) and find the char of most count
        Map<Character, Integer> map = new HashMap<>();
        int max = 0, maxCnt = 0;
        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            if (map.get(ch) > max) {
                maxCnt = 1;
                max = map.get(ch);
            } else if (map.get(ch) == max) {
                maxCnt++;
            }
        }

        if (k == 0 || max <= 1) return str;

        int threshold = str.length() % k == 0 ? str.length() / k : str.length() / k + 1;
        if (map.size() < k || max > threshold) return "";

        // can't align for cases like abcabcabcd, k=4
        // abcd abcx abc => need at least  (k - maxCnt) * (maxCnt - 1) other chars
        // only have  str.length() - maxCnt * max  chars
        if (str.length() - maxCnt * max < (k - maxCnt) * (maxCnt - 1)) return "";

        // use max heap to arrange chars
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap =
                new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        maxHeap.addAll(map.entrySet());

        // construct string
        char[] ans = new char[str.length()];
        char ch = 0;
        int count = 0;
        for (int i = 0; i < k; ++i) {
            for (int j = 0; i + k * j < str.length(); ++j) {
                if (count == 0) {
                    ch = maxHeap.peek().getKey();
                    count = maxHeap.poll().getValue();
                }

                ans[i + k * j] = ch;
                count--;
            }
        }
        return new String(ans);
    }
}

package com.leetcode;

import java.util.*;

/**
 * 243. Shortest Word Distance
 *
 * Given a list of words and two words word1 and word2, return the shortest
 * distance between these two words in the list.
 *
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 *
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class ShortestWordDistance {
    public int shortestDistance(String[] words, String word1, String word2) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < words.length; ++i) {
            if (!map.containsKey(words[i]))
                map.put(words[i], new ArrayList<>());
            map.get(words[i]).add(i);
        }

        Iterator<Integer> iter1 = map.get(word1).iterator(),
                iter2 = map.get(word2).iterator();
        int i1 = iter1.next(), i2 = iter2.next(), minDist = Math.abs(i1 - i2);
        while (iter1.hasNext() || iter2.hasNext()) {
            if (iter1.hasNext() && iter2.hasNext()) {
                if (i1 <= i2) i1 = iter1.next();
                else          i2 = iter2.next();
            } else if (iter1.hasNext()) {
                i1 = iter1.next();
            } else {
                i2 = iter2.next();
            }
            minDist = Math.min(minDist, Math.abs(i1 - i2));
        }
        return minDist;
    }
}

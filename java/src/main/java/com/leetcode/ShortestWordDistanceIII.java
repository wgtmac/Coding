package com.leetcode;

import java.util.*;

/**
 * 245. Shortest Word Distance III
 *
 * This is a follow up of Shortest Word Distance. The only difference is now word1
 * could be the same as word2.
 *
 * Given a list of words and two words word1 and word2, return the shortest distance
 * between these two words in the list.
 *
 * word1 and word2 may be the same and they represent two individual words in the list.
 *
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Given word1 = “makes”, word2 = “coding”, return 1.
 * Given word1 = "makes", word2 = "makes", return 3.
 *
 * Note:
 * You may assume word1 and word2 are both in the list.
 */
public class ShortestWordDistanceIII {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < words.length; ++i) {
            if (!map.containsKey(words[i]))
                map.put(words[i], new ArrayList<>());
            map.get(words[i]).add(i);
        }

        Iterator<Integer> iter1 = map.get(word1).iterator(),
                iter2 = map.get(word2).iterator();
        Integer i1 = iter1.next(), i2 = iter2.next(),
                minDist = i1 == i2 ? Integer.MAX_VALUE : Math.abs(i1 - i2);
        while (iter1.hasNext() || iter2.hasNext()) {
            if (iter1.hasNext() && iter2.hasNext()) {
                if (i1 <= i2) i1 = iter1.next();
                else          i2 = iter2.next();
            } else if (iter1.hasNext()) {
                i1 = iter1.next();
            } else {
                i2 = iter2.next();
            }
            if (i1 != i2)
                minDist = Math.min(minDist, Math.abs(i1 - i2));
        }
        return minDist;
    }
}

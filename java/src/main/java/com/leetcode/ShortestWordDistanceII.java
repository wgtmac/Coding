package com.leetcode;

import java.util.*;

/**
 * 244. Shortest Word Distance II
 *
 * This is a follow up of Shortest Word Distance. The only difference is now you
 * are given the list of words and your method will be called repeatedly many
 * times with different parameters. How would you optimize it?
 *
 * Design a class which receives a list of words in the constructor, and implements
 * a method that takes two words word1 and word2 and return the shortest distance
 * between these two words in the list.
 *
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 *
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are
 * both in the list.
 */
public class ShortestWordDistanceII {
    // Your WordDistance object will be instantiated and called as such:
    // WordDistance wordDistance = new WordDistance(words);
    // wordDistance.shortest("word1", "word2");
    // wordDistance.shortest("anotherWord1", "anotherWord2");

    private static class WordDistance {
        private Map<String, List<Integer>> map;
        private Map<String, Integer> cache;

        public WordDistance(String[] words) {
            map = new HashMap<>();
            for (int i = 0; i < words.length; ++i) {
                if (!map.containsKey(words[i]))
                    map.put(words[i], new ArrayList<>());
                map.get(words[i]).add(i);
            }
            cache = new HashMap<>();
        }

        public int shortest(String word1, String word2) {
            String key = word1.compareTo(word2) < 0 ? word1 + word2 : word2 + word1;
            if (!cache.containsKey(key)) {
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
                cache.put(key, minDist);
            }
            return cache.get(key);
        }
    }

}

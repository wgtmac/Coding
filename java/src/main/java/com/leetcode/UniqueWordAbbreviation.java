package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 288. Unique Word Abbreviation
 *
 * An abbreviation of a word follows the form <first letter><number><last letter>.
 * Below are some examples of word abbreviations:
 *
 * a) it                      --> it    (no abbreviation)
 *
 *      1
 * b) d|o|g                   --> d1g
 *
 *               1    1  1
 *      1---5----0----5--8
 * c) i|nternationalizatio|n  --> i18n
 *
 *               1
 *      1---5----0
 * d) l|ocalizatio|n          --> l10n
 *
 * Assume you have a dictionary and given a word, find whether its abbreviation
 * is unique in the dictionary. A word's abbreviation is unique if no other word
 * from the dictionary has the same abbreviation.
 *
 * Example:
 * Given dictionary = [ "deer", "door", "cake", "card" ]
 *
 * isUnique("dear") -> false
 * isUnique("cart") -> true
 * isUnique("cane") -> false
 * isUnique("make") -> true
 */
public class UniqueWordAbbreviation {
    public class ValidWordAbbr {

        Map<String, Set<String>> map;

        public ValidWordAbbr(String[] dictionary) {
            map = new HashMap<>();
            for (String word : dictionary) {
                String abbrWord = abbr(word);
                if (!map.containsKey(abbrWord)) {
                    map.put(abbrWord, new HashSet<>());
                }
                map.get(abbrWord).add(word);
            }
        }

        public boolean isUnique(String word) {
            String abbrWord = abbr(word);
            return !map.containsKey(abbrWord) ||
                    (map.get(abbrWord).contains(word) && map.get(abbrWord).size() == 1);
        }

        private String abbr(String word) {
            if (word.length() > 2) {
                return word.charAt(0) + Integer.toString(word.length() - 2) +
                        word.charAt(word.length() - 1);
            } else {
                return word;
            }
        }
    }
}

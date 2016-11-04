package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 249. Group Shifted Strings
 *
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep
 * "shifting" which forms the sequence:
 *
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
 *
 * For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 * A solution is:
 *
 * [
 *   ["abc","bcd","xyz"],
 *   ["az","ba"],
 *   ["acef"],
 *   ["a","z"]
 * ]
 */
public class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strings) {
            StringBuilder sb = new StringBuilder();
            char firstChar = str.charAt(0);   // assume there is no empty string

            for (char ch : str.toCharArray())
                sb.append((char)('a' + (ch >= firstChar ? ch - firstChar : 26 + ch - firstChar)));

            if (!map.containsKey(sb.toString()))
                map.put(sb.toString(), new ArrayList<>());

            map.get(sb.toString()).add(str);
        }

        List<List<String>> ans = new ArrayList<>();
        ans.addAll(map.values());
        return ans;
    }
}

package com.leetcode;

/**
 * 17. Letter Combinations of a Phone Number
 * 
 * DESCRIPTION:
 * Given a digit string, return all possible letter combinations that the number could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 * 
 * Skill:
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsofaPhoneNumber {
    public List<String> letterCombinations(String digits) {
        ArrayList<String> result = new ArrayList<>();

        if (digits == null) {
            return result;
        }

        Map<Character, char[]> map = new HashMap<>();
        map.put('0', new char[] {});
        map.put('1', new char[] {});
        map.put('2', new char[] { 'a', 'b', 'c' });
        map.put('3', new char[] { 'd', 'e', 'f' });
        map.put('4', new char[] { 'g', 'h', 'i' });
        map.put('5', new char[] { 'j', 'k', 'l' });
        map.put('6', new char[] { 'm', 'n', 'o' });
        map.put('7', new char[] { 'p', 'q', 'r', 's' });
        map.put('8', new char[] { 't', 'u', 'v'});
        map.put('9', new char[] { 'w', 'x', 'y', 'z' });

        helper(map, 0, digits, new StringBuilder(), result);

        return result;
    }

    private void helper(Map<Character, char[]> map, int index, String digits,
        StringBuilder sb, ArrayList<String> result) {
        if (index == digits.length()) {
            if (sb.length() > 0)
                result.add(sb.toString());
            return;
        }

        char[] chars = map.get(digits.charAt(index));
        if (chars.length == 0) {
            helper(map, index + 1, digits, sb, result);
        } else {
            for (char c : chars) {
                sb.append(c);
                helper(map, index + 1, digits, sb, result);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}

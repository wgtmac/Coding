package com.leetcode;

/**
 * 28. Implement strStr()
 *
 * Returns the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 * 
 * Skill: KMP
 */
public class ImplementstrStr {
	public int strStr(String haystack, String needle) {
		for (int i = 0, j; i < haystack.length() - needle.length(); ++i) {
			for (j = 0;
				 j < needle.length() && haystack.charAt(i + j) == needle.charAt(j);
				 ++j);

			if (j == needle.length()) return i;
		}

		return needle.isEmpty() ? 0 : -1;
	}
}

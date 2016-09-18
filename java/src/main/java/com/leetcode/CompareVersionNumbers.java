package com.leetcode;

/**
 * 165. Compare Version Numbers
 * 
 * Compare two version numbers version1 and version2. If version1 > version2
 * return 1, if version1 < version2 return -1, otherwise return 0. You may assume
 * that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number
 * sequences. For instance, 2.5 is not "two and a half" or "half way to version
 * three", it is the fifth second-level revision of the second first-level revision.
 * 
 * Here is an example of version numbers ordering:
 * 0.1 < 1.1 < 1.2 < 13.37
 * 
 * Skill: 
 * 计算长度
 * 先把字符串按"."分割开
 * 然后一个个数字比较
 * 注意：可能前面全部一样 但长的那个数字拖尾全是0
 * */ 
public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        String[] ver1 = version1.split("\\."), ver2 = version2.split("\\.");

        int subversion1, subversion2, cmp;
        for (int i = 0; i < Math.max(ver1.length, ver2.length); ++i) {
            subversion1 = i < ver1.length ? Integer.parseInt(ver1[i]) : 0;
            subversion2 = i < ver2.length ? Integer.parseInt(ver2[i]) : 0;
            cmp = subversion1 - subversion2;
            if (cmp < 0)
                return -1;
            else if (cmp > 0)
                return 1;
        }

        return 0;
    }
    
    public static void main(String[] args) {
    	CompareVersionNumbers t = new CompareVersionNumbers();
    	System.out.println(t.compareVersion("1.1", "1"));
    }
}

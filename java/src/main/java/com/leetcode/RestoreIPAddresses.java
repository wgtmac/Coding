package com.leetcode;

/**
 * 93. Restore IP Addresses
 * 
 * Given a string containing only digits,
 * restore it by returning all possible valid IP address combinations.
 * For example:
 * Given "25525511135",
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * 
 * Skill: 
 * 每次找到一个数 递归调用即可
 * 注意特殊情况： 0xx
 * */
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
    	List<String> list = new ArrayList<>();
    	if (s != null && s.length() >= 4)
            processOneNumber(s, 0, new ArrayList<>(), list);
    	return list;
    }
    
    public void processOneNumber(String s, int start, List<String> nums, List<String> list) {
        if (nums.size() == 4) {
            if (start == s.length())
                list.add(nums.get(0) + "." + nums.get(1) + "."
                        + nums.get(2) + "." + nums.get(3));
            return;
        }

        StringBuilder sb = new StringBuilder(3);
        for (int i = start; i < Math.min(s.length(), start + 3); i++) {
            sb.append(s.charAt(i));
            int currNum = Integer.valueOf(sb.toString());
            if (currNum < 256) {
                nums.add(sb.toString());
                processOneNumber(s, i + 1, nums, list);
                nums.remove(nums.size() - 1);
            }
            if (currNum == 0) break;
        }
    }
    
    public static void main(String[] args) {
    	RestoreIPAddresses t = new RestoreIPAddresses();
    	System.out.println(t.restoreIpAddresses("0000"));
		System.out.println(t.restoreIpAddresses("255255255255"));
    }
}

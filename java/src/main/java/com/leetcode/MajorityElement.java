package com.leetcode;

/**
 * 169. Majority Element
 * 
 * Given an array of size n, find the majority element.
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 * 
 * Skill: 
 * 用一个数，存最近出现次数最多的数
 * 另一个数给它计数，重复出现+1，不是它就-1，变成0就不管
 * */

import java.util.ArrayList;
import java.util.HashMap;

public class MajorityElement {
    public int majorityElement(int[] num) {
        int cnt = 0, majorityNumber = Integer.MIN_VALUE;
        for (int i = 0; i < num.length; i++) {
            if (majorityNumber != num[i]) {
                if (cnt > 1)
                    cnt--;
                else {
                    majorityNumber = num[i];
                    cnt = 1;
                }
            }
            else cnt++;
        }
        return majorityNumber;
    }
    
    
    
    ///////////////////////////////////////////////////
    /*
    Majority Number
    http://lintcode.com/en/problem/majority-number/
    Given an array of integers, the majority number is the number that occurs more than half of the size of the array. Find it.
    Example         For [1, 1, 1, 1, 2, 2, 2], return 1
    Challenge       O(n) time and O(1) space

    Majority Number II
    http://lintcode.com/en/problem/majority-number-ii
    Given an array of integers, the majority number is the number that occurs more than 1/3 of the size of the array. Find it.
    Note                   There is only one majority number in the array
    Example             For [1, 2, 1, 2, 1, 3, 3] return 1
    Challenge          O(n) time and O(1) space

    Majority Number III
    http://lintcode.com/en/problem/majority-number-iii/
    Given an array of integers and a number k, the majority number is the number that occurs more than 1/k of the size of the array. Find it.
    Note                        There is only one majority number in the array.
    Example                  For [3,1,2,3,2,3,3,4,4,4] and k = 3, return 3
    Challenge      O(n) time and O(k) extra space
    */
    
    /**
     * @param nums: A list of integers
     * @return: The majority number that occurs more than 1/3
     */
    public static int majorityNumberII(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer> ();
        for (int i : nums) {
            if (map.containsKey(i)){
                map.put(i, map.get(i) + 1);
            } else if (map.size() < 3) {
                map.put(i, 1);
            } else {
                for (Object key : map.keySet().toArray()) {
                    if (map.get((Integer)key) == 1) {
                        map.remove((Integer)key);
                    } else {
                        map.put((Integer)key, map.get((Integer)key) - 1);
                    }
                }
            }
        }
        
        int max = 0;
        int maxNum = 0;
        for (Object key : map.keySet().toArray()) {
            if (map.get((Integer)key) > max) {
                max = map.get((Integer)key);
                maxNum = (Integer)key;
            }
        }
        
        return maxNum;
    }
    
    
     /**
     * @param nums: A list of integers
     * @param k: As described
     * @return: The majority number
     */
    public static int majorityNumberIII(ArrayList<Integer> nums, int k) {
        // write your code
        if (nums == null || nums.size() == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer> ();
        for (int i : nums) {
            if (map.containsKey(i)){
                map.put(i, map.get(i) + 1);
            } else if (map.size() < k) {
                map.put(i, 1);
            } else {
                for (Object key : map.keySet().toArray()) {
                    if (map.get((Integer)key) == 1) {
                        map.remove((Integer)key);
                    } else {
                        map.put((Integer)key, map.get((Integer)key) - 1);
                    }
                }
            }
        }
        
        int max = 0;
        int maxNum = 0;
        for (Object key : map.keySet().toArray()) {
            if (map.get((Integer)key) > max) {
                max = map.get((Integer)key);
                maxNum = (Integer)key;
            }
        }

        return maxNum;
    }
}

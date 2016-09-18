package com.leetcode;

/**
 * 163. Missing Ranges
 * 
 * Given a sorted integer array where the range of elements are [lower, upper]
 * inclusive, return its missing ranges.
 * 
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99,
 * return ["2", "4->49", "51->74", "76->99"].
 * 
 * Skill:
 * 用一个变量表明下一个期待的值 如果不一样就遇到了gap
 * 
 * 注意特殊情况 如数组为空，如果数组范围超过了low和up之间的值 需要丢弃
 */
import java.util.ArrayList;
import java.util.List;

public class MissingRanges {
    public List<String> findMissingRanges(int[] A, int lower, int upper) {
        List<String> ret = new ArrayList<>();
        
        if (A == null || lower > upper) return ret;
        
        int expected = lower;
        for (int i = 0; i < A.length; i++) {
        	if (A[i] < expected) continue;
        	if (A[i] != expected)
        		ret.add(constructRange(expected, A[i] - 1));
        	expected = A[i] + 1;
        }

        if (expected <= upper)
            ret.add(constructRange(expected, upper));
        
        return ret;
    }

    private String constructRange(int expected, int current) {
        if (expected == current) {
            return Integer.toString(expected);
        } else {
            return expected + "->" + current;
        }
    }
    
	public static void main(String[] args) {
		MissingRanges t = new MissingRanges();
		System.out.println(t.findMissingRanges(new int[] {0, 1, 3, 50, 75}, 0, 99));
	}

}

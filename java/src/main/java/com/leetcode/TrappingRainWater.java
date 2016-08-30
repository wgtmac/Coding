package com.leetcode;

/**
 * 42. Trapping Rain Water
 * 
 * Given n non-negative integers representing an elevation map
 * where the width of each bar is 1, compute how much water
 * it is able to trap after raining.
 *
 * For example, 
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * 
 * Skill: 
 * 栈里面按递减顺序存高度以及他们的序号
 * 如果出现一个高的比栈顶高，则弹出所有比当前矮的高度，计算弹出的和新peek之间的高度差
 * 与当前index的距离，然后栈里只剩下比当前高的
 */

import java.util.Stack;

public class TrappingRainWater {
	private static class Helper {
		int height;
		int pos;
		Helper(int h, int p) {height = h; pos = p;}
	}
    public int trap(int[] A) {
    	if (A == null || A.length == 0) return 0;
    	Stack<Helper> stack = new Stack<>();
    	int volume = 0;
    	
    	for (int i = 0; i < A.length; i++) {
    		if (stack.empty()) {
    			if (A[i] != 0)
    				stack.push(new Helper(A[i], i));
    		} else if (stack.peek().height >= A[i]) {
    			stack.push(new Helper(A[i], i));
    		} else {
    			Helper last = stack.pop();
    			while (!stack.empty()) {
    				Helper top = stack.peek();
    				volume += (Math.min(top.height, A[i]) - last.height) * (i - top.pos - 1);

    				if (top.height >= A[i])
    					break;
    				else
    					last = stack.pop();
    			}
    			stack.push(new Helper(A[i], i));
			}
    	}
    	
    	return volume;
    }

    /**
     * 先找到最高的
     * 针对左半边, 让每次与前面路过最高比,如果高了就把它到最高之间新增的高度填上
     * 需要减去当前自己占掉的面积
     * 右边的也同理
     * */
    private static class Solution {
        public int trap(int[] A) {
            int sum = 0;
            int max = -1;
            int maxIndex = -1;
            int prev;

            // find the highest bar
            for (int i = 0; i < A.length; i++) {
                if (max < A[i]) {
                    max = A[i];
                    maxIndex = i;
                }
            }

            // process all bars left to the highest bar
            prev = 0;
            for (int i = 0; i < maxIndex; i++) {
                if (A[i] > prev) {
                    sum += (A[i] - prev) * (maxIndex - i);
                    prev = A[i];
                }
                sum -= A[i];
            }

            // process all bars right to the highest bar
            prev = 0;
            for (int i = A.length - 1; i > maxIndex; i--) {
                if (A[i] > prev) {
                    sum += (A[i] - prev) * (i - maxIndex);
                    prev = A[i];
                }
                sum -= A[i];
            }

            return sum;
        }
    }
}

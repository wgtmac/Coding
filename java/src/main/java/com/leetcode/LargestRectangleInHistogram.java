package com.leetcode;

/**
 * 84. Largest Rectangle in Histogram
 * 
 * Given n non-negative integers representing the histogram's bar height
 * where the width of each bar is 1, find the area of largest rectangle in the histogram.
 * For example,
 * Given height = [2,1,5,6,2,3],
 * return 10.
 *  
 * Skill:
 * 用一个栈，维护使里面的元素高度为递增
 * 如果某次当前高度h[i]比栈顶元素低，则pop出所有比h[i]高或者相等的元素，同时记下pop出的元素个数
 * 每当一个元素被pop出去的时候，代表该高度的面积可以计算了
 */

import java.util.Stack;

public class LargestRectangleInHistogram {
    /**
     * 一个栈，Helper类分别存高度和栈内pop掉的比当前高的高度个数加上自己的1
     * 如果当前栈顶比当前h[i]低，则push; 否则pop到当前栈顶低于h[i]or为空
     */
    private static class Helper{
        int height;  // 高度
        int width;   // 栈内比当前高度高的连续总宽度
        Helper(int h, int w) { height = h; width = w; }
    }
   
    public int largestRectangleArea(int[] height) {
        int[] h = height;
        if (h == null || h.length == 0) return 0;
        
        Stack<Helper> stack = new Stack<>();
        
        int maxArea = 0, width;
        for (int i = 0; i < h.length; i++) {
            width = 1;
            while (!stack.empty() && stack.peek().height >= h[i]) {
                Helper helper = stack.pop();
                width += helper.width;
                maxArea = Math.max(helper.height * width, maxArea);
            }
            
            if (h[i] != 0)
                stack.push(new Helper(h[i], width));
        }
        
        // pop out all elements, stored in increasing order
        width = 1;
        while (!stack.empty()) {
            Helper helper = stack.pop();
            width += helper.width;
            maxArea = Math.max(helper.height * width, maxArea);
        }
        
        return maxArea;
    }
}

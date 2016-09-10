package com.leetcode;

/**
 * 85. Maximal Rectangle
 * 
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest rectangle containing all ones and return its area
 * 
 * Skill: 复用 LargestRectangleInHistogram 代码 计算每行到最上面最高的面积
 */

import java.util.Stack;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
    	if (matrix == null || matrix.length == 0 ||
                matrix[0] == null || matrix[0].length == 0)
    	    return 0;

    	int[] h = new int[matrix[0].length];
    	int maxArea = 0;
    	for (int i = 0; i < matrix.length; i++){
    		for (int j = 0; j < matrix[0].length; j++) {
    			if (matrix[i][j] == '1')
    			    h[j]++;
    			else
    			    h[j] = 0;
    		}
    		maxArea = Math.max(maxArea, largestRectangleArea(h));
    	}
    	
    	return maxArea;
    }
    
    private static class Helper{
        public int height;  // 高度
        public int width;   // 栈内左边一个元素与当前高度之间已pop出的数量（他们都比两端高度高）
        public Helper(int h, int w) { height = h; width = w; }
    }
    
    public int largestRectangleArea(int[] height) {
        int[] h = height;
        Stack<Helper> stack = new Stack<>();
        int maxArea = 0, area, width;
        
        for (int i = 0; i < h.length; i++) {
            width = 0;
            while (!stack.empty() && stack.peek().height >= h[i]) {
                Helper helper = stack.pop();
                width += helper.width + 1;
                area = helper.height * width;
                if (helper.height != h[i] && maxArea < area)
                    maxArea = area;
            }
            if (h[i] != 0)
                stack.push(new Helper(h[i], width));
        }
        
        width = 0;
        while (!stack.empty()) {
            Helper helper = stack.pop();
            width += helper.width + 1;
            area = helper.height * width;
            if (maxArea < area)
                maxArea = area;
        }
        return maxArea;
    }
}

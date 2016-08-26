package com.leetcode;

/**
 * 6. ZigZag Conversion
 * 
 * DESCRIPTION:
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * 
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * 
 * Skill:
 * 计算出对应映射关系就好了
 * 每个上升部分在原字符串中反序
 * 
 * 
 * NC的做法 不反序
 * 除了首尾两行 中间的每次+2×row-2的时候加两次点 找规律 一个+j 一个2×row-2-j
 * 
 */
public class ZigZagConversion {
	public String convert_Better (String s, int nRows) {
	    if(s == null || s.length()==0 || nRows <=0)
	        return "";
	    if(nRows == 1)
	        return s;
	    StringBuilder res = new StringBuilder();
	    int size = 2*nRows-2;
	    for(int i=0;i<nRows;i++)
	    {
	        for(int j=i;j<s.length();j+=size)
	        {
	            res.append(s.charAt(j));
	            if(i!=0 && i!=nRows-1 && j+size-2*i<s.length())
	                res.append(s.charAt(j+size-2*i));
	        }                
	    }
	    return res.toString();
	}
	
	/////////////////////////////////////
    public String convert(String s, int nRows) {
        if (s == null || s.length() == 0 || nRows <= 1) return s;
        char[] ch = s.toCharArray();
        
        int validLen = s.length() / (2 * nRows - 2) * (2 * nRows - 2);
        // reverse each ascending chars
        for (int i = nRows; i < validLen; i += (2 * nRows - 2)) {
            int start = i;
            int end = i + nRows - 3;
            while (start < end) {
                char tmp = ch[start];
                ch[start++] = ch[end];
                ch[end--] = tmp;
            }
        }
        
        int trails = s.length() - validLen;
        StringBuilder ret = new StringBuilder();
        if (trails <= nRows) {
            for (int i = 0; i < nRows; i++) {
                if (i == 0 || i == nRows - 1) {
                    for (int j = i; j < ch.length; j += (2 * nRows - 2)) {
                        ret.append(ch[j]);
                    }
                }
                else {
                    for (int j = i; j < ch.length; j += (nRows - 1)) {
                        ret.append(ch[j]);
                    }
                }
            }
        }
        else {
            trails -= nRows;
            int k = 0;
            for (int i = 0; i < nRows; i++) {
                if (i == 0 || i == nRows - 1) {
                    for (int j = i; j < ch.length; j += (2 * nRows - 2)) {
                        ret.append(ch[j]);
                    }
                }
                else {
                    for (int j = i; j < validLen + nRows; j += (nRows - 1)) {
                        ret.append(ch[j]);
                    }
                    if (i >= nRows - 1 - trails) {
                        ret.append(ch[ch.length - 1 - k]);
                        k++;
                    }
                }
            }
        }
        
        return ret.toString();
    }
}

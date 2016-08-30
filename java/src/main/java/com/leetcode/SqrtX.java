package com.leetcode;

/**
 * 69. Sqrt(x)
 * 
 * Implement int sqrt(int x).
 * Compute and return the square root of x.
 * 
 * Skill: 
 * 二分法去做，注意溢出需要long来暂存
 */


public class SqrtX {
    public static int sqrt(int x) {
    	if (x < 1) return 0;

        // x/2 + 1 > sqrt(x) if x >= 1
    	int start = 1, end = (x / 2) + 1, mid = 1;
    	long p1, p2;
    	while (start < end) {
    		mid = start + (end - start) / 2;
    		p1 = (long)mid * mid;
    		p2 =  (long)(mid + 1) * (mid + 1);
    		if (p1 <= x && x < p2)
    			return mid;
    		else if (p2 <= x)
    			start = mid;
    		else
    			end = mid;
    	}
    	
    	return mid;
    }

  /*
   Newton Iteration
   const float EPS = 0.000000001;
   int sqrt(int x) {
        if (x==0) return x;
        float dividend = x;
        float val = x;  //最终
        float last;     //保存上一个计算的值
        do {
          last = val;
          val =(val + dividend/val) / 2;
        } while(abs(val-last) > EPS);
        int result = val;
        if (result * result > x)
          result--;
        return result;
    }
   */
    
    public static void main(String[] args) {
    	System.out.println(sqrt(2147395599));
    	System.out.println(sqrt(5));
    	System.out.println(sqrt(2));

    }
}

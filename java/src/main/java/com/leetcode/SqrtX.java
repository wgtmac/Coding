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
		int start = 1, end = (x / 2) + 1, mid;
		while (start + 1 < end) {
			mid = start + (end - start) / 2;
			long square = (long)mid * mid;
			if (square == x)
				return mid;
			else if (square < x)
				start = mid;
			else
				end = mid;
		}

		return start + (end - start) / 2;
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

package com.leetcode;

/**
 * 34. Search for a Range
 *
 * Given a sorted array of integers,
 * find the starting and ending position of a given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 * 
 * Skill:
 * 二分查找
 * 我的做法不太好 是先找到随便一个 然后往左右找界
 * 应该先找最左边 再找最右边
 */
public class SearchForARange {
	class Solution {
	    public int[] searchRange(int[] A, int target) {
	        int start, end, mid;
	        int[] bound = new int[2]; 
	        
	        // search for left bound
	        start = 0; 
	        end = A.length - 1;
	        while (start + 1 < end) {
	            mid = start + (end - start) / 2;
	            if (A[mid] < target) {
	                start = mid;
	            } else {
	                end = mid;
	            }
	        }
	        if (A[start] == target) {
	            bound[0] = start;
	        } else if (A[end] == target) {
	            bound[0] = end;
	        } else {
	            bound[0] = bound[1] = -1;
	            return bound;
	        }
	        
	        // search for right bound
	        start = 0;
	        end = A.length - 1;
	        while (start + 1 < end) {
	            mid = start + (end - start) / 2;
	            if (A[mid] <= target) {
	                start = mid;
	            } else {
	                end = mid;
	            }
	        }
	        if (A[end] == target) {
	            bound[1] = end;
	        } else if (A[start] == target) {
	            bound[1] = start;
	        } else {
	            bound[0] = bound[1] = -1;
	        }
	        
	        return bound;
	    }
	}
	
	/////////////////////////////////////////////////
    public int[] searchRange(int[] A, int target) {
        if(A == null || A.length == 0){
            return null;
        }
        if(A.length == 1){
        	if(A[0] == target){
        		int[] result = {0,0};
        		return result;
        	}
        	else{
        		int[] result = {-1,-1};
        		return result;
        	}
        }
        int start = 0, end = A.length - 1;
        int mid;
        while(start + 1 != end){
            mid = start + (end - start)/2;
            if(A[mid] == target){
                start = end = mid;
                break;
            }
            else if(A[mid] < target){
                start = mid;
            }
            else{
                end = mid;
            }
        }
        if(A[start] == target || A[end] == target){
            if(A[start] == target){
                mid = end = start;
            }else{
                mid = start = end;
            }
            for(int i = mid; i >= 0; i--){
                if(A[i] == target){
                    start = i;
                }
            }
            for(int i = mid; i < A.length; i++){
                if(A[i] == target){
                    end = i;
                }
            }
            int[] result = {start, end};
            return result;
        }
        else{
            int[] result = {-1,-1};
            return result;
        }
    }
}

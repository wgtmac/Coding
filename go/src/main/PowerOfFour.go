package main

/**
342. Power of Four

Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example:
Given num = 16, return true. Given num = 5, return false.

Follow up: Could you solve it without loops/recursion?
*/

import "math"

func isPowerOfFour(num int) bool {
	if num < 1 || !(num & (num - 1) == 0) {
		return false
	}

	sqrtRoot := int(math.Sqrt(float64(num)))
	return sqrtRoot * sqrtRoot == num
}

package main

/**
349. Intersection of Two Arrays

Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.
*/

func intersection(nums1 []int, nums2 []int) []int {
	set1, set2 := make(map[int]bool), make(map[int]bool)

	for _, num := range nums1 {
		set1[num] = true
	}

	for _, num := range nums2 {
		_, containsNum := set1[num]
		if containsNum {
			set2[num] = true
		}
	}

	intersects := make([]int, len(set2))

	i := 0
	for num := range set2 {
		intersects[i] = num
		i++
	}

	return intersects
}

/**
350. Intersection of Two Arrays II

Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that
you cannot load all elements into the memory at once?
*/

func intersect(nums1 []int, nums2 []int) []int {
	map1, map2 := countArray(nums1), countArray(nums2)

	intersects := make([]int, 0)

	for num1, cnt1 := range map1 {
		cnt2, contains := map2[num1]
		if (contains) {
			for i := 0; i < min(cnt1, cnt2); i++ {
				intersects = append(intersects, num1)
			}
		}
	}

	return intersects
}

func min(x, y int) int {
	if (x < y) {
		return x
	} else {
		return y
	}
}

func countArray(nums []int) map[int]int {
	countMap := make(map[int]int)
	for _, num := range nums {
		count, ok := countMap[num]
		if (ok) {
			countMap[num] = count + 1
		} else {
			countMap[num] = 1
		}
	}
	return countMap
}

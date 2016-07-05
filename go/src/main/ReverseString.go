package main

/**
344. Reverse String

Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".
*/

func reverseString(s string) string {
	str := []byte(s)
	for l, r := 0, len(str) - 1; l < r; l, r = l + 1, r - 1 {
		str[l], str[r] = str[r], str[l]
	}
	return string(str)
}

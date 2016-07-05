package main

/**
345. Reverse Vowels of a String

Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".
*/
func reverseVowels(s string) string {
	str := []byte(s)

	for l, r := 0, len(str) - 1; l < r; l, r = l + 1, r - 1 {
		for l < r && !isVowel(str, l) { l++ }
		for l < r && !isVowel(str, r) { r-- }
		if l < r {
			str[l], str[r] = str[r], str[l]
		}
	}

	return string(str)
}

func isVowel(str []byte, index int) (vowel bool) {
	switch str[index] {
	case 'a', 'e', 'i', 'o', 'u': vowel = true
	case 'A', 'E', 'I', 'O', 'U': vowel = true
	default: vowel = false
	}
	return
}
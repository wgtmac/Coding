package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 405. Convert a Number to Hexadecimal
 *
 * Given an integer, write an algorithm to convert it to hexadecimal.
 * For negative integer, two’s complement method is used.
 *
 * Note:
 *
 * All letters in hexadecimal (a-f) must be in lowercase.
 * The hexadecimal string must not contain extra leading 0s. If the number is zero,
 * it is represented by a single zero character '0'; otherwise, the first character
 * in the hexadecimal string will not be the zero character.
 * The given number is guaranteed to fit within the range of a 32-bit signed integer.
 * You must not use any method provided by the library which converts/formats the
 * number to hex directly.
 *
 * Example 1:
 * Input:
 * 26
 * Output:
 * "1a"
 *
 * Example 2:
 * Input:
 * -1
 * Output:
 * "ffffffff"
 */
public class ConvertANumberToHexadecimal {
    public String toHex(int num) {
        if (num == Integer.MIN_VALUE)
            return "80000000";
        else if (num == 0)
            return "0";

        List<Character> list = new ArrayList<>();
        for (int absNum = Math.abs(num), value; absNum != 0; absNum /= 16) {
            value = absNum % 16;
            if (value < 10)
                list.add((char)('0' + value));
            else
                list.add((char)('a' + value - 10));
        }

        if (num < 0) {
            while (list.size() < 8) list.add('0');

            for (int i = 0, carry = 1; i < list.size(); ++i) {
                char ch = reverse(list.get(i));
                if (carry == 1) {
                    if (ch == 'f') {
                        ch = '0';
                    } else if (ch == '9') {
                        ch = 'a';
                        carry = 0;
                    } else {
                        ch = (char)(ch + 1);
                        carry = 0;
                    }
                }
                list.set(i, ch);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; --i)
            sb.append(list.get(i));
        return sb.toString();
    }

    private char reverse(char ch) {
        switch (ch) {
            case '0': return 'f';
            case '1': return 'e';
            case '2': return 'd';
            case '3': return 'c';
            case '4': return 'b';
            case '5': return 'a';
            case '6': return '9';
            case '7': return '8';
            case '8': return '7';
            case '9': return '6';
            case 'a': return '5';
            case 'b': return '4';
            case 'c': return '3';
            case 'd': return '2';
            case 'e': return '1';
            case 'f': return '0';
            default: return '0';
        }
    }

    public static void main(String[] args) {
        ConvertANumberToHexadecimal s = new ConvertANumberToHexadecimal();
        System.out.println(s.toHex(-111110));
    }
}

package com.leetcode;

/**
 * 273. Integer to English Words
 Convert a non-negative integer to its english words representation.
 Given input is guaranteed to be less than 2^31 - 1.

 For example,
 123 -> "One Hundred Twenty Three"
 12345 -> "Twelve Thousand Three Hundred Forty Five"
 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

 */
public class IntegertoEnglishWords {
    // billion, million, thousand, one
    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        StringBuilder sb = new StringBuilder();
        String[] name = {"Billion", "Million", "Thousand", ""};
        int[] level = {1000000000, 1000000, 1000, 1};

        for (int i = 0; i < name.length; ++i) {
            if (num >= level[i]) {
                sb.append(" ").append(processThreeDigits(num / level[i]));
                sb.append(" ").append(name[i]);
                num %= level[i];
            }
        }

        return sb.toString().trim();
    }

    private String processThreeDigits(int num) {
        StringBuilder sb = new StringBuilder();
        if (num >= 100) {
            sb.append(processOneDigit(num / 100)).append(" Hundred");
            num = num % 100;
        }

        if (sb.length() > 0) {
            sb.append(" ");
        }

        if (num < 10) {
            sb.append(processOneDigit(num));
        } else if (num < 20) {
            sb.append(processTwoDigits(num));
        } else {
            int h = num / 10 * 10, l = num % 10;
            sb.append(processTwoDigits(h));
            sb.append(" ");
            sb.append(processOneDigit(l));
        }

        return sb.toString().trim();
    }

    private String processTwoDigits(int num) {
        String digits;
        switch (num) {
            case 10: digits = "Ten";       break;
            case 11: digits = "Eleven";    break;
            case 12: digits = "Twelve";    break;
            case 13: digits = "Thirteen";  break;
            case 14: digits = "Fourteen";  break;
            case 15: digits = "Fifteen";   break;
            case 16: digits = "Sixteen";   break;
            case 17: digits = "Seventeen"; break;
            case 18: digits = "Eighteen";  break;
            case 19: digits = "Nineteen";  break;
            case 20: digits = "Twenty";    break;
            case 30: digits = "Thirty";    break;
            case 40: digits = "Forty";     break;
            case 50: digits = "Fifty";     break;
            case 60: digits = "Sixty";     break;
            case 70: digits = "Seventy";   break;
            case 80: digits = "Eighty";    break;
            case 90: digits = "Ninety";    break;
            default: digits = "";
        }
        return digits;
    }

    private String processOneDigit(int num) {
        String digit;
        switch (num) {
            case 1: digit = "One";   break;
            case 2: digit = "Two";   break;
            case 3: digit = "Three"; break;
            case 4: digit = "Four";  break;
            case 5: digit = "Five";  break;
            case 6: digit = "Six";   break;
            case 7: digit = "Seven"; break;
            case 8: digit = "Eight"; break;
            case 9: digit = "Nine";  break;
            default: digit = "";
        }
        return digit;
    }

    public static void main(String[] args) {
        IntegertoEnglishWords i = new IntegertoEnglishWords();
//        System.out.println(i.numberToWords(123));
//        System.out.println(i.numberToWords(12345));
//        System.out.println(i.numberToWords(1234567));
//        System.out.println(i.numberToWords(0));
//        System.out.println(i.numberToWords(1));
        System.out.println(i.numberToWords(100));
    }
}

package com.leetcode;

/**
 * 273. Integer to English Words
 * Convert a non-negative integer to its english words representation.
 * Given input is guaranteed to be less than 2^31 - 1.
 *
 * For example,
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
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
                update(sb, processThreeDigits(num / level[i]));
                update(sb, name[i]);
                num %= level[i];
            }
        }

        return sb.toString();
    }

    private String processThreeDigits(int num) {
        if (num == 0) return  "";

        StringBuilder sb = new StringBuilder();
        if (num >= 100) {
            update(sb, processOneDigit(num / 100));
            update(sb, "Hundred");
            num %= 100;
        }

        if (num < 10)
            update(sb, processOneDigit(num));
        else if (num < 20)
            update(sb, processTwoDigits(num));
        else {
            update(sb, processTwoDigits(num / 10 * 10));
            update(sb, processOneDigit(num % 10));
        }

        return sb.toString();
    }

    private String processTwoDigits(int num) {
        switch (num) {
            case 10: return "Ten";
            case 11: return "Eleven";
            case 12: return "Twelve";
            case 13: return "Thirteen";
            case 14: return "Fourteen";
            case 15: return "Fifteen";
            case 16: return "Sixteen";
            case 17: return "Seventeen";
            case 18: return "Eighteen";
            case 19: return "Nineteen";
            case 20: return "Twenty";
            case 30: return "Thirty";
            case 40: return "Forty";
            case 50: return "Fifty";
            case 60: return "Sixty";
            case 70: return "Seventy";
            case 80: return "Eighty";
            case 90: return "Ninety";
            default: return "";
        }
    }

    private String processOneDigit(int num) {
        switch (num) {
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
            default: return "";
        }
    }

    private void update(StringBuilder sb, String input) {
        if (input.isEmpty()) return;
        if (sb.length() == 0 || Character.isSpaceChar(
                sb.toString().charAt(sb.length() - 1)))
            sb.append(input);
        else
            sb.append(' ').append(input);
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

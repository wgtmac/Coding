package com.leetcode;

/**
 * 374. Guess Number Higher or Lower
 *
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 *
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 *
 * -1 : My number is lower
 * 1 : My number is higher
 * 0 : Congrats! You got it!
 * Example:
 * n = 10, I pick 6.
 *
 * Return 6.
 */
public class GuessNumberHigherOrLower {
    /*
    The guess API is defined in the parent class GuessGame.
    @param num, your guess
    @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
    int guess(int num);
    */

    int guess(int num) {return 0;}

    public int guessNumber(int n) {
        int start = 1, end = n, mid, cmp;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            switch (guess(mid)) {
                case 0: return mid;
                case -1:
                    end = mid;
                    break;
                case 1:
                    start = mid;
                    break;
            }
        }
        if (guess(start) == 0) return start;
        else return end;
    }
}

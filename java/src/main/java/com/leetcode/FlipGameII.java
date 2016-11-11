package com.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 294. Flip Game II
 *
 * You are playing the following Flip Game with your friend: Given a string that
 * contains only these two characters: + and -, you and your friend take turns
 * to flip two consecutive "++" into "--". The game ends when a person can no
 * longer make a move and therefore the other person will be the winner.
 *
 * Write a function to determine if the starting player can guarantee a win.
 *
 * For example, given s = "++++", return true. The starting player can guarantee
 * a win by flipping the middle "++" to become "+--+".
 *
 * Follow up:
 * Derive your algorithm's runtime complexity.
 */
public class FlipGameII {
    public boolean canWin(String s) {
        List<String> myMoves = getNextMoves(s);

        for (String next : myMoves) {
            List<String> oppoNextMoves = getNextMoves(next);
            boolean canWin = true;
            for (String oppoNext : oppoNextMoves) {
                if (!canWin(oppoNext)) {
                    canWin = false;
                    break;
                }
            }
            if (canWin) return true;
        }

        return false;
    }

    private List<String> getNextMoves(String s) {
        List<String> nexts = new LinkedList<>();

        char[] arr = s.toCharArray();
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] == arr[i - 1] && arr[i] == '+') {
                arr[i] = arr[i - 1] = '-';
                nexts.add(new String(arr));
                arr[i] = arr[i - 1] = '+';
            }
        }

        return nexts;
    }
}

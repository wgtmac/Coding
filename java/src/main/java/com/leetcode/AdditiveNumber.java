package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 306. Additive Number
 *
 * Additive number is a string whose digits can form additive sequence.
 *
 * A valid additive sequence should contain at least three numbers. Except for
 * the first two numbers, each subsequent number in the sequence must be the sum
 * of the preceding two.
 *
 * For example:
 * "112358" is an additive number because the digits can form an additive
 * sequence: 1, 1, 2, 3, 5, 8.
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 *
 * "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
 * 1 + 99 = 100, 99 + 100 = 199
 *
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence
 * 1, 2, 03 or 1, 02, 3 is invalid.
 *
 * Given a string containing only digits '0'-'9', write a function to determine
 * if it's an additive number.
 *
 * Follow up:
 * How would you handle overflow for very large input integers?
 */
public class AdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        return helper(num, 0, new ArrayList<>());
    }

    private boolean helper(String num, int index, List<Long> nums) {
        if (index == num.length() && nums.size() >= 3) {
            return true;
        }

        long currNum = 0;
        for (int i = index; i < num.length(); ++i) {

            currNum = currNum * 10 + num.charAt(i) - '0';

            if (nums.size() < 2 || nums.get(nums.size() - 1) + nums.get(nums.size() - 2) == currNum) {
                nums.add(currNum);
                if (helper(num, i + 1, nums)) {
                    return true;    // one branch can get correct result!
                }
                nums.remove(nums.size() - 1);
            }

            if (i == index && currNum == 0) break;
        }

        return false;
    }
}

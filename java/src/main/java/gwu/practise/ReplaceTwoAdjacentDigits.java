package gwu.practise;

/**
 * You are given an integer X. You must choose two adjacent digits and replace
 * them with the larger of these two digits.
 *
 * For example, from the integer X = 233614, you can obtain:
 * 33614 (by replacing 23 with 3);
 * 23614 (by replacing 33 with 3 or 36 with 6);
 * 23364 (by replacing 61 with 6 or 14 with 4);
 *
 * You want to find the smallest number that can be obtained from X by replacing
 * two adjacent digits with the larger of the two. In the above example, the
 * smallest such number is 23364.
 *
 * Write a function:
 *
 * class Solution {public int solution (int X);}
 * that, given a positive integer X, returns the smallest number that can be obtained
 * from X by replacing two adjacent digits with the larger of the two.
 *
 * For example, given X = 233614, the function should return 23364, as explained above.
 *
 * Assume that:
 * X is an integer within the range [10..1,000,000,000].
 * In your solution, focus on correctness. The performance of your solution will
 * not be the focus of the assessment.
 *
 */
public class ReplaceTwoAdjacentDigits {
    public int solution (int num) {
        String str = Integer.toString(num);
        if (str.length() < 2)
            return num;

        String min = (char)Math.max(str.charAt(0), str.charAt(1)) + str.substring(2);
        for (int i = 1; i < str.length() - 1; ++i) {
            String tmp = str.substring(0, i) + (char)Math.max(str.charAt(i), str.charAt(i + 1));
            if (i + 2 < str.length())
                tmp += str.substring(i + 2);
            if (min.compareTo(tmp) > 0)
                min = tmp;
        }

        return Integer.valueOf(min);
    }

    public static void main(String[] args) {
        ReplaceTwoAdjacentDigits t = new ReplaceTwoAdjacentDigits();
        System.out.println(t.solution(233614) == 23364);
        System.out.println(t.solution(654321) == 64321);
        System.out.println(t.solution(123456) == 12346);
    }
}

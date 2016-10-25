package gwu.practise;

import java.util.*;

/**
 * Lucy has N numbers from 1 to N. Her arrangement of those numbers is called a
 * beautiful arrangement if any of the following is true:
 *
 * The number present at the ith position is divisible by i.
 * i is divisible by the number present at the ith position.
 *
 * For a given N, how many beautiful arrangements are possible?
 *
 * Constraints
 *
 * 1 < N < 20
 *
 * Function
 *
 * You are given a function arrangement that takes N as its argument and will
 * return the total number of all possible beautiful arrangements.
 *
 * Sample Input
 * 2
 *
 * Sample Output
 * 2
 *
 * Explanation
 * The number of possible beautiful arrangements for N=2 will be:
 * 1 2
 * 2 1
 *
 * Consider the arrangement [1, 2] then:
 *
 * number present at 1st position (i = 1) is 1, and 1 is divisible by i.
 * number present at 2nd position (i = 2) is 2, and 2 is divisible by i.
 *
 * Consider the arrangement [2, 1] then:
 *
 * number present at 1st position (i = 1) is 2, and 2 is divisible by i.
 * number present at 2nd position (i = 2) is 1, and i is divisible by 1.
 */
public class BeautifulArrangements {

    public int arrange(int N) {
        // decide available numbers for each i
        List<Integer>[] availableNums = new List[N + 1];
        Set<Integer> remainNums = new HashSet<>();
        for (int i = 1; i <= N; ++i) {
            remainNums.add(i);
            availableNums[i] = new ArrayList<Integer>();
            for (int j = 1; j <= N; ++j) {
                if (j % i == 0 || i % j == 0)
                    availableNums[i].add(j);
            }
        }

        int[] count = {0};
        helper(1, remainNums, availableNums, count);

        return count[0];
    }

    private void helper(int i, Set<Integer> remNums,
                        List<Integer>[] availNums, int[] count) {
        if (remNums.isEmpty()) {
            count[0] += 1;
        } else {
            for (int num : availNums[i]) {
                if (remNums.contains(num)) {
                    remNums.remove(num);
                    helper(i + 1, remNums, availNums, count);
                    remNums.add(num);
                }
            }
        }
    }

    public static void main(String[] args) {
        BeautifulArrangements b = new BeautifulArrangements();
        System.out.println(b.arrange(1) == 1);
        System.out.println(b.arrange(2) == 2);
        System.out.println(b.arrange(3) == 3);
    }
}

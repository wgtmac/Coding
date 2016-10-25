package com.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 421. Maximum XOR of Two Numbers in an Array
 *
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 2^31.
 *
 * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 *
 * Could you do this in O(n) runtime?
 *
 * Example:
 *
 * Input: [3, 10, 5, 25, 2, 8]
 *
 * Output: 28
 *
 * Explanation: The maximum result is 5 ^ 25 = 28.
 */
public class MaximumXOROfTwoNumbersInAnArray {

    private static class TrieNode {
        TrieNode next[] = new TrieNode[2];
        int num;
    }

    public int findMaximumXOR(int[] nums) {
        // find most significant 1
        int bit = 32;
        boolean flag = true;
        while (flag && --bit >= 1) {
            for (int num : nums) {
                if ((num & (1 << bit)) != 0) {
                    flag = false;
                    break;
                }
            }
        }

        // construct trie
        TrieNode root = new TrieNode();
        for (int num : nums) {
            TrieNode node = root;
            for (int b = bit; b >= 0; b--) {
                int next = (((num & (1 << b)) == 0) ? 0 : 1);
                if (node.next[next] == null) {
                    node.next[next] = new TrieNode();
                }
                node = node.next[next];
                node.num = num;
            }
        }

        // find max
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            TrieNode node = root;
            for (int b = bit; b >= 0; b--) {
                int next = (((num & (1 << b)) == 0) ? 1 : 0);
                if (node.next[next] == null) {
                    node = node.next[1 - next];
                } else {
                    node = node.next[next];
                }
            }
            max = Math.max(max, (num ^ node.num));
        }

        return max;
    }

    private static class Faster {
        // divide the list into two based on mask
        private List<Integer>[] divide(List<Integer> list, int mask) {
            List<Integer> zeros = new ArrayList<>(), ones = new ArrayList<>();
            for (int num : list) {
                if ((num & mask) == 0)
                    zeros.add(num);
                else
                    ones.add(num);
            }
            return new List[] {zeros, ones};
        }

        private int helper(List<Integer> list, int mask) {
            if (list.size() <= 1) return 0;

            // try to divide the list into two based on the most significant bit
            List<Integer>[] lists = null;
            for (; mask > 0; mask >>= 1) {
                lists = divide(list, mask);
                if (!lists[0].isEmpty() && !lists[1].isEmpty())
                    break;
            }

            if (lists[0].isEmpty()) return lists[1].get(0);
            if (lists[1].isEmpty()) return lists[0].get(0);
            if (lists[0].size() == 1 || lists[1].size() == 1) {
                int max = Integer.MIN_VALUE;
                for (int n1 : lists[0])
                    for (int n2 : lists[1])
                        max = Math.max(max, n1 ^ n2);
                return max;
            }

            mask >>= 1;
            List<Integer> l0r1 = new ArrayList<>(), l1r0 = new ArrayList<>();
            separateList(lists[0], l0r1, l1r0, mask);
            separateList(lists[1], l1r0, l0r1, mask);

            return Math.max(helper(l0r1, mask), helper(l1r0, mask));
        }

        private void separateList(List<Integer> list, List<Integer> l0,
                                  List<Integer> l1, int mask) {
            for (int num : list) {
                if ((num & mask) == 0)
                    l0.add(num);
                else
                    l1.add(num);
            }
        }

        public int findMaximumXOR(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) set.add(num);
            return helper(new ArrayList<>(set), 1 << 30);
        }
    }

}

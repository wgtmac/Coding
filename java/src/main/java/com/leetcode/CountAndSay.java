package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 38. Count and Say
 * 
 * The width-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 * 
 * Note: The sequence of integers will be represented as a string.
 *
 *
 */
public class CountAndSay {
    public String countAndSay(int n) {
        Queue<Integer> queue = new LinkedList<Integer>() {{ offer(1);}};
        int size, prev, curr, cnt;
        for (int i = 2; i <= n; i++) {
            prev = curr = queue.poll();
            cnt = 1;

            size = queue.size();
            while (size-- > 0) {
                curr = queue.poll();
                if (prev != curr) {
                    queue.offer(cnt);
                    queue.offer(prev);
                    prev = curr;
                    cnt = 1;
                } else {
                    cnt++;
                }
            }

            queue.offer(cnt);
            queue.offer(curr);
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            sb.append(queue.poll());
        }
        
        return sb.toString();
    }
}

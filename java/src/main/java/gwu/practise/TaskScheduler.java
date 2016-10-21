package gwu.practise;

import java.util.*;

/**
 * Created by wgtmac on 10/20/16.
 *
 * Given a string representing different tasks in order, e.g. "AABABCD". Each
 * character is a task which takes 1 time unit to finish, and task is scheduled
 * one at a time, and one task cannot start until the previous one has finished.
 *
 * There is a cool down time k for same tasks, meaning that one task cannot start
 * if the previous same task cool down time has not been reached.
 *
 * e.g
 * "AABABCD", k = 2, then the task is scheduled as: A__AB_ABCD, _ means cool down
 * time. The overall time is 10.
 *
 * Return the overall time.
 */
public class TaskScheduler {
    public int schedule(String tasks, int k) {
        Deque<Character> queue = new LinkedList<>();

        int time = 0;
        for (int i = 0; i < tasks.length(); ++i) {
            char ch = tasks.charAt(i);

            int gap = 0;
            Iterator<Character> descIter = queue.descendingIterator();
            while (descIter.hasNext()) {
                if (descIter.next() != ch)
                    gap++;
                else {
                    for (int cool = k - gap; cool > 0; cool--)
                        queue.offerLast('_');
                    break;
                }
            }
            queue.offerLast(ch);

            while (queue.size() > k) {
                queue.pollFirst();
                time++;
            }
        }

        return queue.size() + time;
    }

    public static void main(String[] args) {
        TaskScheduler t = new TaskScheduler();
        System.out.println(t.schedule("AABABCD", 2) == 10);
        System.out.println(t.schedule("ABABAB", 2) == 8);
    }
}

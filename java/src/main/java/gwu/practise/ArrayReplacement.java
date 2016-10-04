package gwu.practise;

import java.util.Arrays;

/**
 * 输入是一个字符串数组，一个int数组，输入的字符串数组是另外一个字符串数组通过int数组变换得到的，
 * int数组的值代表的是原来这位置上的字符串经过变换后的坐标，然后输出是求变换之前的字符串数组，
 * 要求用线性时间，O(1)额外空间.
 *
 * 打个比方，比如一个字符串数组是"cat", "rabbit","dog", "mouse"，int数组给的2,0,3,1，
 * 意思是string数组第0个词是cat，它本来的位置是在哪呢，我们要看int数组，int数组的0在index 1上，
 * 所以说cat之前应该是1号位的，同理rabbit在string数组的1号位，而index数组3号位的值是1，
 * 说明rabbit这个词之前应该在3号位上的，依次类推，所以变换前的字符串数组应该是 dog, cat, mouse, rabbit
 */
public class ArrayReplacement {

    public static void restore(String[] words, int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            int j = nums[i];
            while (j < i)
                j = nums[j];
            swap(words, i, j);
        }
    }

    private static void swap(String[] words, int i, int j) {
        if (i != j) {
            String tmp = words[i];
            words[i] = words[j];
            words[j] = tmp;
        }
    }


    public static void main(String[] args) {
        String[] strs = {"cat", "rabbit","dog", "mouse"};
        int[] nums = {2,0,3,1};
        restore(strs, nums);
        System.out.println(Arrays.toString(strs));  // dog, cat, mouse, rabbit
    }
}

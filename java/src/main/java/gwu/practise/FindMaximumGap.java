package gwu.practise;

/**
 * Given an unsorted array nums, find the maximum (j - i) that satisfying
 * nums[i] <= nums[j]
 *
 * Follow up: time and space complexity should be O(n)
 */
public class FindMaximumGap {

    public static int findMaxGap(int[] nums) {
        int[] runningMin = new int[nums.length];
        runningMin[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            runningMin[i] = Math.min(nums[i], runningMin[i - 1]);
        }

        /**
         * start from the smallest gap, then expand it.
         */
        int max = 0;
        for (int i = nums.length - 1; i > max; --i) {
            // for each nums[i], find the furthest one it can reach
            while (i > max && nums[i] >= runningMin[i - max - 1]) {
                max++;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(findMaxGap(new int[]{1, 2, 3, 4, 5}) == 4);
        System.out.println(findMaxGap(new int[]{3, 2, 1, 4, 5}) == 4);
        System.out.println(findMaxGap(new int[]{1, 5, 4, 3, 2}) == 4);
        System.out.println(findMaxGap(new int[]{5, 4, 3, 1, 1}) == 1);
        System.out.println(findMaxGap(new int[]{5, 4, 3, 2, 6}) == 4);
        System.out.println(findMaxGap(new int[]{2, 1, 4, 5, 6}) == 4);
        System.out.println(findMaxGap(new int[]{6, 2, 3, 4, 3, 5, 6}) == 6);
    }
}

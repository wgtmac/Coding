package com.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 380. Insert Delete GetRandom O(1)
 *
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * insert(val): Inserts an item val to the keys if not already present.
 * remove(val): Removes an item val from the keys if present.
 * getRandom: Returns a random element from current keys of elements.
 * Each element must have the same probability of being returned.
 *
 * Example:
 * // Init an empty keys.
 * RandomizedSet randomSet = new RandomizedSet();
 *
 * // Inserts 1 to the keys. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 *
 * // Returns false as 2 does not exist in the keys.
 * randomSet.remove(2);
 *
 * // Inserts 2 to the keys, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 *
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 *
 * // Removes 1 from the keys, returns true. Set now contains [2].
 * randomSet.remove(1);
 *
 * // 2 was already in the keys, so return false.
 * randomSet.insert(2);
 *
 * // Since 1 is the only number in the keys, getRandom always return 1.
 * randomSet.getRandom();
 */
public class InsertDeleteGetRandomO1 {
    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */
    class RandomizedSet {

        private Map<Integer, Integer> num2Idx, idx2Num;
        private Random rnd;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            num2Idx = new HashMap<>();
            idx2Num = new HashMap<>();
            rnd = new Random();
            rnd.setSeed(System.currentTimeMillis());
        }

        /** Inserts a value to the keys. Returns true if the keys did not already contain the specified element. */
        public boolean insert(int val) {
            if (num2Idx.containsKey(val)) {
                return false;
            }

            int index = idx2Num.size();
            idx2Num.put(index, val);
            num2Idx.put(val, index);
            return true;
        }

        /** Removes a value from the keys. Returns true if the keys contained the specified element. */
        public boolean remove(int val) {
            if (!num2Idx.containsKey(val)) {
                return false;
            }

            int index = num2Idx.get(val);
            int lastIndex = num2Idx.size() - 1;

            num2Idx.remove(val);
            if (index == lastIndex) {
                idx2Num.remove(index);
            } else {
                int lastNum = idx2Num.get(lastIndex);
                idx2Num.put(index, lastNum);
                num2Idx.put(lastNum, index);
                idx2Num.remove(lastIndex);
            }

            return true;
        }

        /** Get a random element from the keys. */
        public int getRandom() {
            return idx2Num.get(rnd.nextInt(num2Idx.size()));
        }
    }
}

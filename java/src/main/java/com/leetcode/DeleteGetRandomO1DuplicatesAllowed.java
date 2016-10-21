package com.leetcode;

import java.util.*;

/**
 * 381. Insert Delete GetRandom O(1) - Duplicates allowed
 *
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * Note: Duplicate elements are allowed.
 * insert(val): Inserts an item val to the collection.
 * remove(val): Removes an item val from the collection if present.
 * getRandom: Returns a random element from current collection of elements. The
 * probability of each element being returned is linearly related to the number
 * of same value the collection contains.
 *
 * Example:
 * // Init an empty collection.
 * RandomizedCollection collection = new RandomizedCollection();
 *
 * // Inserts 1 to the collection. Returns true as the collection did not contain 1.
 * collection.insert(1);
 *
 * // Inserts another 1 to the collection. Returns false as the collection contained 1.
 * Collection now contains [1,1].
 * collection.insert(1);
 *
 * // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
 * collection.insert(2);
 *
 * // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
 * collection.getRandom();
 *
 * // Removes 1 from the collection, returns true. Collection now contains [1,2].
 * collection.remove(1);
 *
 * // getRandom should return 1 and 2 both equally likely.
 * collection.getRandom();
 */
public class DeleteGetRandomO1DuplicatesAllowed {

    /**
     * Your RandomizedCollection object will be instantiated and called as such:
     * RandomizedCollection obj = new RandomizedCollection();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */

    static class RandomizedCollection {

        private Random rnd;
        private Map<Integer, Integer> idx2Num;
        private Map<Integer, Set<Integer>> num2Idx;

        /** Initialize your data structure here. */
        public RandomizedCollection() {
            num2Idx = new HashMap<>();
            idx2Num = new HashMap<>();

            rnd = new Random();
            rnd.setSeed(System.currentTimeMillis());
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            int index = idx2Num.size();
            idx2Num.put(index, val);

            if (!num2Idx.containsKey(val)) {
                num2Idx.put(val, new HashSet<>());
            }

            num2Idx.get(val).add(index);
            return num2Idx.get(val).size() == 1;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            if (!num2Idx.containsKey(val)) {
                return false;
            }

            int idx2Delete = num2Idx.get(val).iterator().next();
            int idx2Move = idx2Num.size() - 1;

            // remove an index of val; remove the list if it's the last one
            if (num2Idx.get(val).size() == 1) {
                num2Idx.remove(val);
            } else {
                num2Idx.get(val).remove(idx2Delete);
            }

            if (idx2Delete == idx2Move) {
                idx2Num.remove(idx2Delete);
            } else {
                int lastNum = idx2Num.get(idx2Move);
                idx2Num.put(idx2Delete, lastNum);
                idx2Num.remove(idx2Move);
                num2Idx.get(lastNum).remove(idx2Move);
                num2Idx.get(lastNum).add(idx2Delete);
            }

            return true;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            return idx2Num.get(rnd.nextInt(idx2Num.size()));
        }
    }

    public static void main(String[] args) {
        RandomizedCollection obj = new RandomizedCollection();

        obj.insert(1);
        obj.insert(1);

        obj.remove(1);
        obj.remove(1);



//        System.out.println(obj.getRandom());
//        System.out.println(obj.getRandom());
//        System.out.println(obj.getRandom());
//        System.out.println(obj.getRandom());

    }
}

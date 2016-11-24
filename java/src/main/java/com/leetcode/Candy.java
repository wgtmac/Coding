package com.leetcode;

import java.util.Arrays;

/**
 * 135. Candy
 * 
 * There are N children standing in a line. Each child is assigned a rating val.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * 
 * Skill: 
 * 先顺序走
 * 当前比前面的大，前面的糖数目+1
 * 当前等于前面的，就等于前面糖数目
 * 如果当前比前面小，则该糖为1
 *
 * 第二遍倒着检查
 * （如果，需要往前回退都依次+1（如果往左边回退一直递增，到左边某个数变小为止）
 * */
public class Candy {
    public int candy(int[] ratings) {
        if (ratings == null) return 0;
        if (ratings.length <= 1) return ratings.length;
        
        int[] can = new int[ratings.length];
        Arrays.fill(can, 1);

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i])
                can[i] = can[i - 1] + 1;
            //else if (ratings[i - 1] == ratings[i])
            //    can[i] = can[i - 1];
        }
        
        int sum = can[ratings.length - 1];
        for (int i = ratings.length - 1; i >= 1; i--) {
            if ((ratings[i - 1] > ratings[i]) && !(can[i - 1] > can[i])) {
                can[i - 1] = can[i] + 1;
            }
            sum += can[i - 1];
        }
        
        return sum;
    }
}

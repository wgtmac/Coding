package com.leetcode;

/**
 * 134. Gas Station
 * 
 * There are N gas stations along a circular route, where the amount of gas at
 * station i is gas[i]. You have a car with an unlimited gas tank and it costs
 * cost[i] of gas to travel from station i to its next station (i+1). You begin
 * the journey with an empty tank at one of the gas stations. Return the
 * starting gas station's index if you can travel around the circuit once,
 * otherwise return -1.
 * 
 * Note:The solution is guaranteed to be unique.
 * 
 * Hint:
 * 两个指针，前一个发现负的后一个指针就倒着找能填平负数的地方位置
 * */

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0, i = 0, start = 0;

        while ((start == 0 && i < gas.length) || i < start) {
            if (sum >= 0) {
                sum += gas[i] - cost[i];
                i++;
            } else {
                if (start == 0)
                    start = gas.length - 1;
                else
                    start--;
                sum += gas[start] - cost[start];
            }
        }

        return sum >= 0 ? start : -1;
    }
}

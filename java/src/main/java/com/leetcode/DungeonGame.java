package com.leetcode;

/**
 * 174. Dungeon Game
 * 
 * The demons had captured the princess (P) and imprisoned her in the bottom-right
 * corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid.
 * Our valiant knight (K) was initially positioned in the top-left room and must
 * fight his way through the dungeon to rescue the princess. The knight has an
 * initial health point represented by a positive integer. If at any point his
 * health point drops to 0 or below, he dies immediately. Some of the rooms are
 * guarded by demons, so the knight loses health (negative integers) upon entering
 * these rooms; other rooms are either empty (0's) or contain magic orbs that
 * increase the knight's health (positive integers). In order to reach the princess
 * as quickly as possible, the knight decides to move only rightward or downward
 * in each step.
 * 
 * Skill: 
 * 从右下角往左上角进行计算
 * k代表到达该地方需要最少的血量（在扣血之前）
 * 返回k[0][0]
 * k[i][j] = max(1, min(k[i+1][j], k[i][j+1]) - d[i][j])
 */
public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
    	int row = dungeon.length, col = dungeon[0].length;
    	
        int[][] minHPToHere = new int[row][col];
        if (dungeon[row - 1][col - 1] < 0)
            minHPToHere[row - 1][col - 1] = 1 - minHPToHere[row - 1][col - 1];
        else
            minHPToHere[row - 1][col - 1] = 1;

        for (int i = col - 2; i >= 0; i--)
        	minHPToHere[row - 1][i] =
                    Math.max(1, minHPToHere[row - 1][i + 1] - dungeon[row - 1][i]);
        for (int i = row - 2; i >= 0; i--)
        	minHPToHere[i][col - 1] =
                    Math.max(1, minHPToHere[i + 1][col - 1] - dungeon[i][col - 1]);
        
        for (int i = row - 2; i >= 0; i--) {
        	for (int j = col - 2; j >= 0; j--) {
            	minHPToHere[i][j] = Math.max(1,
                        Math.min(minHPToHere[i + 1][j], minHPToHere[i][j + 1]) - dungeon[i][j]);
            }
        }
        
        return minHPToHere[0][0];
    }
}

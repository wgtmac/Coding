package com.leetcode;

import java.util.LinkedHashSet;

/**
 * 353. Design Snake Game
 *
 * Design a Snake game that is played on a device with screen size = width x height.
 * Play the game online if you are not familiar with the game.
 *
 * The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
 *
 * You are given a list of food's positions in row-column order. When a snake eats
 * the food, its length and the game's score both increase by 1.
 *
 * Each food appears one by one on the screen. For example, the second food will
 * not appear until the first food was eaten by the snake.
 *
 * When a food does appear on the screen, it is guaranteed that it will not appear
 * on a block occupied by the snake.
 *
 * Example:
 * Given width = 3, height = 2, and food = [[1,2],[0,1]].
 *
 * Snake snake = new Snake(width, height, food);
 *
 * Initially the snake appears at position (0,0) and the food at (1,2).
 *
 * |S| | |
 * | | |F|
 *
 * snake.move("R"); -> Returns 0
 *
 * | |S| |
 * | | |F|
 *
 * snake.move("D"); -> Returns 0
 *
 * | | | |
 * | |S|F|
 *
 * snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
 *
 * | |F| |
 * | |S|S|
 *
 * snake.move("U"); -> Returns 1
 *
 * | |F|S|
 * | | |S|
 *
 * snake.move("L"); -> Returns 2 (Snake eats the second food)
 *
 * | |S|S|
 * | | |S|
 *
 * snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */
public class DesignSnakeGame {

    class SnakeGame {

        private int width, height;
        private int[][] food;
        private int posFood;
        private int score = 0;

        // first element is the tail, last element is the head
        private LinkedHashSet<Integer> snake;
        private int[] head;

        /** Initialize your data structure here.
         @param width - screen width
         @param height - screen height
         @param food - A list of food positions
         E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.food = food;
            this.posFood = 0;
            this.score = 0;

            // init the snake with length 1 on the top left corner
            this.head = new int[] {0, 0};
            this.snake = new LinkedHashSet<Integer>() {{add(0);}};
        }

        // note: h is row, w is col
        private boolean isValidPos(int h, int w) {
            return 0 <= w && w < width && 0 <= h && h < height;
        }

        /** Moves the snake.
         @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
         @return The game's score after the move. Return -1 if game over.
         Game over when snake crosses the screen boundary or bites its body. */
        public int move(String direction) {
            int deltaH = 0, deltaW = 0;
            switch (direction) {
                case "U": deltaH = -1; break;
                case "L": deltaW = -1; break;
                case "R": deltaW = 1;  break;
                case "D": deltaH = 1;  break;
            }

            head = new int[] {head[0] + deltaH, head[1] + deltaW};

            // head is out of bound, DEAD
            if (!isValidPos(head[0], head[1])) {
                return -1;
            }

            // eats own body, DEAD
            if (snake.contains(head[0] * width + head[1])) {
                return -1;
            }

            // hit the food
            if (posFood < food.length && food[posFood][0] == head[0] &&
                    food[posFood][1] == head[1]) {
                score++;
            } else {
                // tail is moved away
                snake.remove(snake.iterator().next());
            }

            snake.add(head[0] * width + head[1]);

            return score;
        }
    }

}

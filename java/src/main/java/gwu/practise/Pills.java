package gwu.practise;

import java.util.Random;

/**
 * 题目是给一瓶药，里面100颗完整的药片，每天需要吃半颗。每天吃的方法是随机从瓶子里取一颗药，
 * 如果是整颗就吃半颗，剩下半颗扔回瓶子里；如果取出的是半颗，那就直接吃掉。第一小问是simulate
 * 这个过程，然后print每天瓶中剩下的整颗和半颗的数量，直到空瓶。第二问是，求整个simulation
 * 过程中，瓶中剩下1整颗，0半颗的概率。最后问了running time。
 */
public class Pills {
    public static void simulate(int n) {
        Random rnd = new Random(System.currentTimeMillis());
        int ones = n, halves = 0, day = 1;
        while (ones > 0 || halves > 0) {
            int num = rnd.nextInt(ones + halves);
            if (num < ones) {
                ones--;
                halves++;
            } else {
                halves--;
            }
            System.out.printf("[Day %d]: remain %d pills and %d half pills%n", day++, ones, halves);
        }
    }

    public static double probability(int n) {
        double[][] prob = new double[n + 1][2 * n + 1];
        prob[n][0] = 1.0;
        for (int day = 1, sum = 2 * n - 1; day <= 2 * n - 2; day++, sum--) {
            for (int one = sum / 2, half = sum - 2 * one; one >= 0 && half >= 0; one--, half = sum - 2 * one) {
                double eatOne = half >= 1 ? prob[one + 1][half - 1] * (one + 1) / (one + half) : 0;
                double eatHalf = prob[one][half + 1] * (half + 1) / (one + half + 1);
                prob[one][half] = eatHalf + eatOne;
            }
        }
        return prob[1][0];
    }

    public static void main(String[] args) {
        simulate(100);
        System.out.println(probability(100));
    }
}

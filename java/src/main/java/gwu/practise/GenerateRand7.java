package gwu.practise;

import java.util.Random;

/**
 * Give rand4(), write a function rand7()
 */
public class GenerateRand7 {
    int rand5() {
        return new Random(System.currentTimeMillis()).nextInt(5);
    }

    int rand7() {
        while (true) {
            int result = 5 * rand5() + rand5();
            if (result < 21) {
                return result & 7;
            }
        }
    }

    int rnd7() {
        while (true) {
            int result = 5 * rand5() + rand5();
            if (result < 24) {
                return result / 3;
            }
        }
    }
}

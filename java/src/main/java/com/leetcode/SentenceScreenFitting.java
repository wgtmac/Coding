package com.leetcode;

/**
 * 418. Sentence Screen Fitting
 *
 * Given a rows x cols screen and a sentence represented by a list of words,
 * find how many times the given sentence can be fitted on the screen.
 *
 * Note:
 *
 * A word cannot be split into two lines.
 * The order of words in the sentence must remain unchanged.
 * Two consecutive words in a line must be separated by a single space.
 * Total words in the sentence won't exceed 100.
 * Length of each word won't exceed 10.
 * 1 ≤ rows, cols ≤ 20,000.
 *
 * Example 1:
 *
 * Input:
 * rows = 2, cols = 8, sentence = ["hello", "world"]
 *
 * Output:
 * 1
 *
 * Explanation:
 * hello---
 * world---
 *
 * The character '-' signifies an empty space on the screen.
 *
 * Example 2:
 *
 * Input:
 * rows = 3, cols = 6, sentence = ["a", "bcd", "e"]
 *
 * Output:
 * 2
 *
 * Explanation:
 * a-bcd-
 * e-a---
 * bcd-e-
 *
 * The character '-' signifies an empty space on the screen.
 *
 * Example 3:
 *
 * Input:
 * rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]
 *
 * Output:
 * 1
 *
 * Explanation:
 * I-had
 * apple
 * pie-I
 * had--
 *
 * The character '-' signifies an empty space on the screen.
 */
public class SentenceScreenFitting {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int times = 0, word = 0, wordLen;

        for (int row = 0, lineLen = 0; row < rows; ++row, lineLen = 0) {
            wordLen = sentence[word].length();

            while ((lineLen + (lineLen == 0 ? wordLen : (wordLen + 1))) <= cols) {
                lineLen += (lineLen == 0 ? wordLen : (wordLen + 1));
                word++;
                if (word == sentence.length) {
                    word = 0;
                    times++;
                }

                wordLen = sentence[word].length();
            }

            if (word == 0) {
                times *= (double)rows / (row + 1);
                break;
            }
        }

        return times;
    }

    public static void main(String[] args) {
        SentenceScreenFitting s = new SentenceScreenFitting();
        String[] sentence = {"f","p","q"};
        System.out.println(s.wordsTyping(sentence, 8, 7));
    }
}

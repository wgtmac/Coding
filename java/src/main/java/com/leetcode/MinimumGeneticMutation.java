package com.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 433. Minimum Genetic Mutation
 *
 * A gene string can be represented by an 8-character long string, with choices from "A","C","G","T".
 * Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.
 * For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
 * Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.
 *
 * Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.
 *
 * NOTE: 1. Starting point is assumed to be valid, so it might not be included in the bank. 2. If multiple mutations are needed, all mutations during in the sequence must be valid. For example,
 * bank: "AACCGGTA"
 * start: "AACCGGTT"
 * end: "AACCGGTA"
 * return: 1
 *
 * bank: "AACCGGTA", "AACCGCTA", "AAACGGTA"
 * start: "AACCGGTT"
 * end: "AAACGGTA"
 * return: 2
 * bank: "AAAACCCC", "AAACCCCC", "AACCCCCC"
 * start: "AAAAACCC"
 * end: "AACCCCCC"
 * return: 3
 */
public class MinimumGeneticMutation {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> geneBank = new HashSet<>();
        for (String gene : bank)
            geneBank.add(gene);

        char[] candidates = {'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<String>() {{offer(start);}};
        int mutation = 0;
        while (!queue.isEmpty()) {
            mutation += 1;

            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                char[] gene = queue.poll().toCharArray();

                for (int j = 0; j < 8; ++j) {
                    char origin = gene[j];

                    for (char candidate : candidates) {
                        if (candidate != origin) {
                            gene[j] = candidate;
                            String mutated = new String(gene);
                            if (geneBank.contains(mutated)) {
                                if (mutated.equals(end)) return mutation;
                                queue.offer(mutated);
                                geneBank.remove(mutated);
                            }
                        }
                    }

                    gene[j] = origin;
                }
            }
        }

        return -1;
    }
}

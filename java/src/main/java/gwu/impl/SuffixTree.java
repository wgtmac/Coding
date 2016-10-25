package gwu.impl;

import java.util.*;

/**
 * Created by wgtmac on 10/23/16.
 *
 * Implementation of suffix tree for string search
 * Efficient to search many small strings inside current string
 */
public class SuffixTree {

    private static class SuffixTreeNode {
        char val;
        Map<Character, SuffixTreeNode> next = new HashMap<>();
        List<Integer> indexes = new ArrayList<>();

        public void add(String s, int index) {
            indexes.add(index);
            if (s != null && !s.isEmpty()) {
                val = s.charAt(0);
                if (!next.containsKey(val)) {
                    next.put(val, new SuffixTreeNode());
                }
                next.get(val).add(s.substring(1), index);
            }
        }

        public List<Integer> search(String s) {
            if (s == null || s.isEmpty()) {
                return indexes;
            } else {
                if (next.containsKey(s.charAt(0))) {
                    return next.get(s.charAt(0)).search(s.substring(1));
                }
                return Collections.EMPTY_LIST;
            }
        }
    }

    // from root to leaf node, all nodes store indexes
    void add(SuffixTreeNode root, String s, int index) {
        SuffixTreeNode node = root;
        for (int i = 0; i < s.length(); ++i) {
            node.indexes.add(index);
            char ch = s.charAt(i);
            if (!node.next.containsKey(ch))
                node.next.put(ch, new SuffixTreeNode());
            node = node.next.get(ch);
        }
        node.indexes.add(index);
    }

    SuffixTreeNode root = new SuffixTreeNode();
}

package gwu.practise;

import java.util.*;

/**
 * 假设有一个double linked list A<->B<->C<->D<->E。给定a list of node（unique）
 * 返回里面connected component 的数量比如针对这个list，给定[A,C,B,E] 返回2.
 * ACB 是一个connected component, E 单独是一个
 */
public class ConnectedComponents {
    private static class ListNode {
        ListNode prev, next;
        char val;
        ListNode(char v) { val = v; }
    }

    private char find(Map<Character, Character> parents, char ch) {
        char parent = parents.get(ch);
        while (parent != parents.get(parent)) {
            parent = parents.get(parent);
        }
        parents.put(ch, parent);
        return parent;
    }

    private void union(Map<Character, Character> parents,
                       Map<Character, Integer> ranks, char a, char b) {
        char parent_a = find(parents, a);
        char parent_b = find(parents, b);
        if (parent_a != parent_b) {
            if (ranks.get(parent_a) < ranks.get(parent_b)) {
                parents.put(a, parent_b);
            } else if (ranks.get(parent_a) > ranks.get(parent_b)) {
                parents.put(b, parent_a);
            } else {
                parents.put(a, parent_b);
                ranks.put(parent_b, ranks.get(parent_b) + 1);
            }
        }
    }

    public int connectedComponents (List<ListNode> nodes) {
        Map<Character, Character> parents = new HashMap<>(nodes.size());
        Map<Character, Integer> ranks = new HashMap<>(nodes.size());

        // 1st pass, init union-find as self-parenting
        for (ListNode node : nodes) {
            parents.put(node.val, node.val);
            ranks.put(node.val, 0);
        }

        // union appeared nodes
        for (ListNode node : nodes) {
            if (node.prev != null && parents.containsKey(node.prev.val)) {
                union(parents, ranks, node.prev.val, node.val);
            }
            if (node.next != null && parents.containsKey(node.next.val)) {
                union(parents, ranks, node.next.val, node.val);
            }
        }

        // find connected components
        Set<Character> set = new HashSet<>();
        for (ListNode node : nodes) {
            set.add(find(parents, node.val));
        }

        return set.size();
    }

    public static void main(String[] args) {
        //A<->B<->C<->D<->E

        ListNode a = new ListNode('A');
        ListNode b = new ListNode('B');
        ListNode c = new ListNode('C');
        ListNode d = new ListNode('D');
        ListNode e = new ListNode('E');
        a.next = b; b.next = c; c.next = d; d.next = e;
        b.prev = a; c.prev = b; d.prev = e; e.prev = d;
        List<ListNode> list = Arrays.asList(a, c, b, e);

        ConnectedComponents solution = new ConnectedComponents();
        System.out.println(solution.connectedComponents(list));
    }
}

package gwu.practise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a million email lists as follows:
 * list 1: a@a.com, b@b.com
 * list 2: b@b.com, c@c.com
 * list 3: e@e.com
 * list 4: a@a.com
 * Combine lists with identical emails, and output tuples:
 * (list 1, list 2, list 4) (a@a.com, b@b.com, c@c.com)
 * (list 3) (e@e.com)
 */
public class MergeEmailList {
    public List<String> merge(List<Set<String>> lists) {

        // union find
        int[] parents = new int[lists.size()];
        int[] ranks = new int[lists.size()];

        for (int i = 0; i < parents.length; ++i)
            parents[i] = i;

        // union all lists O(n^2*logn*m)
        for (int i = 0; i < lists.size(); ++i) {
            for (int j = i + 1; j < lists.size(); ++j) {
                Set<String> left = lists.get(i);
                Set<String> right = lists.get(j);
                for (String r : right) {
                    if (left.contains(r)) {
                        union(parents, ranks, i, j);
                        break;
                    }
                }
            }
        }

        List<String> list = new ArrayList<>();
        // foreach subset, merge them all
        boolean[] visited = new boolean[lists.size()];
        for (int i = 0; i < visited.length; ++i) {
            if (visited[i]) continue;
            visited[i] = true;

            List<Integer> nums = new ArrayList<>();
            Set<String> emails = new HashSet<>();
            nums.add(i);
            emails.addAll(lists.get(i));

            int parent = find(parents, i);
            for (int j = i + 1; j < visited.length; ++j) {
                if (!visited[j] && find(parents, j) == parent) {
                    visited[j] = true;
                    nums.add(j);
                    emails.addAll(lists.get(j));
                }
            }

            StringBuilder listStr = new StringBuilder(), emailStr = new StringBuilder();
            for (int num : nums)
                listStr.append(num).append(" ");
            for (String email : emails)
                emailStr.append(email).append(" ");
            list.add(listStr.toString().trim());
            list.add(emailStr.toString().trim());
        }

        return list;
    }

    private int find(int[] parents, int num) {
        int x = num;
        while (parents[x] != x)
            x = parents[x];
        parents[num] = x;
        return x;
    }

    private void union(int[] parents, int[] ranks, int x, int y) {
        int xp = find(parents, x);
        int yp = find(parents, y);
        if (xp != yp) {
            if (ranks[xp] < ranks[yp]) {
                parents[x] = yp;
            } else if (ranks[xp] > ranks[yp]) {
                parents[y] = xp;
            } else {
                parents[x] = yp;
                ranks[yp]++;
            }
        }
    }

    public static void main(String[] args) {
        MergeEmailList m = new MergeEmailList();
        List<Set<String>> lists = new ArrayList<Set<String>>() {{
            add(new HashSet<String>() {{add("a@a.com"); add("b@b.com");}});
            add(new HashSet<String>() {{add("b@b.com"); add("c@c.com");}});
            add(new HashSet<String>() {{add("e@e.com");}});
            add(new HashSet<String>() {{add("a@a.com");}});
        }};

        System.out.println(m.merge(lists));
    }
}

package gwu.practise;

import java.util.*;

/**
 * Imagine you have a lineage tree that tells for a given person what are his parents.
 * For example:
 *
 * A's parents are B and C
 * B's only known parent is D
 * We don't have information about E's parent
 * +----->B+------>D
 * |
 * +
 * A
 * +      +------->E
 * |      +
 * +----->C
 *        +
 *        +------->F
 *        +------->I
 *        +
 *        G
 *        +
 *        +------->J
 *                 +
 *                 +------>
 *        H        +------>L
 *        +        +
 *        +------->K
 * c1) Write a program to determine if two elements have an ancestor in common.
 * Some examples:
 * F(A,H) = false
 * F(G,H) = true
 * F(G,C) = false
 * 3.2) Follow up: Change it now to return the node in common
 * 3.3) Follow up: Change it to return the distance to the ancestor also
 */
public class GetAncestorForLineageTree {

    private static class Person {
        char name;
        List<Person> parents = new ArrayList<>();
        Person(char name) { this.name = name; }
        void addParent(Person parent) { this.parents.add(parent); }
    }

    Map<Character, Set<Character>> create(List<Person> persons) {
        Map<Character, Set<Character>> map = new HashMap<>();
        for (Person person : persons) {
            if (!map.containsKey(person.name)) {
                link(person, map);
            }
        }

        return map;
    }

    void link(Person person, Map<Character, Set<Character>> map) {
        map.put(person.name, new HashSet<>());

        for (Person parent : person.parents) {
            map.get(person.name).add(parent.name);

            if (!map.containsKey(parent.name)) {
                link(parent, map);
            }

            // merge parents
            map.get(person.name).addAll(map.get(parent.name));
        }
    }

    boolean hasCommonAncestor(char a, char b, Map<Character, Set<Character>> ancestors) {
        Set<Character> ancestorsOfB = ancestors.get(b);
        for (char ancestor_a : ancestors.get(a)) {
            if (ancestorsOfB.contains(ancestor_a))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Person d = new Person('D');
        Person e = new Person('E');
        Person f = new Person('F');
        Person i = new Person('I');
        Person j = new Person('J');
        Person l = new Person('L');
        j.addParent(l);
        Person k = new Person('K');
        k.addParent(l);
        Person h = new Person('H');
        h.addParent(k);
        Person g = new Person('G');
        g.addParent(i); g.addParent(j);
        Person c = new Person('C');
        c.addParent(e); c.addParent(f);
        Person b = new Person('B');
        b.addParent(d);
        Person a = new Person('A');
        a.addParent(b); a.addParent(c);

        GetAncestorForLineageTree sol = new GetAncestorForLineageTree();

        List<Person> list = new ArrayList<>();
        list.add(d); list.add(e); list.add(f); list.add(i);
        list.add(j); list.add(k); list.add(l); list.add(h);
        list.add(g); list.add(c); list.add(b); list.add(a);

        Map<Character, Set<Character>> map = sol.create(list);

        System.out.println(map);
        System.out.println(sol.hasCommonAncestor('A', 'H', map));
        System.out.println(sol.hasCommonAncestor('G', 'H', map));
        System.out.println(sol.hasCommonAncestor('G', 'C', map));
    }
}

package gwu.java.generics;

import java.util.*;

/**
 * Created by wgtmac on 9/25/16.
 *
 * This example code are mostly picked up from Effective Java Book 2nd Edition.
 *
 * Term                       Example
 *
 * Parameterized type         List<String>
 * Actual type parameter      String
 * Generic type               List<E>
 * Formal type parameter      E
 * Unbounded wildcard type    List<?>
 * Raw type                   List
 * Bounded type parameter     <E extends Number>
 * Recursive type bound       <T extends Comparable<T>>
 * Bounded wildcard type      List<? extends Number>
 * Generic method             static <E> List<E> asList(E[] a)
 * Type token                 String.class
 */
public class GenericsTest {

    // Unbounded wildcard type - typesafe and flexible
    // you can’t put any element (other than null) into a Collection<?>.
    static int numElementsInCommon(Set<?> s1, Set<?> s2) {
        int result = 0;
        for (Object o1 : s1)
            if (s2.contains(o1))
                result++;
        return result;
    }

    static void rawtype(Object o) {
        // Legitimate use of raw type - instanceof operator
        if (o instanceof Set) {       // Raw type
            Set<?> m = (Set<?>) o;    // Wildcard type
        } else if (o instanceof Map<?, ?>) { // Wildcard type
            Map<?, ?> m = ( Map<?, ?>) o;
        }
    }

    // arrays are covariant
    static void covariantTest() {
        A[] arrayOfA = new B[1];   // arrays are covariant.
        //List<A> listOfA = new ArrayList<B>();  // Generics are invariant
    }

    // arrays are reified: arrays know and enforce their element types at runtime
    // illegal to create an array of a generic type, a parameterized type,
    // or a type parameter
    static <E> void reifiedTest(Object[] a) {
        List<String>[] rawTyoeListArray = new List[5];
        //List<String>[] listArray = new List<String>[5];  // won't compile
        //E[] genericArray = new E[5];  // won't compile
        E[] array = (E[]) a;
        /**
         * Types such as E, List<E>, and List<String> are technically known as
         * non-reifiable types. Intuitively speaking, a non-reifiable type is
         * one whose runtime representation contains less information than its
         * compile-time representation. The only parameterized types that are
         * reifiable are unbounded wildcard types such as List<?> and Map<?,?>.
         * It is legal, though infrequently useful, to create arrays of unbounded
         * wildcard types.
         */

        E[] array2 = (E[]) new Object[10];
    }

    /**
     * Set<Number> numbers = Union.<Number>union(integers, doubles);
     */
    public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
        Set<E> result = new HashSet<E>(s1); result.addAll(s2);
        return result;
    }

    public interface UnaryFunction<T> {
        T apply(T arg);
    }

    // Generic singleton factory pattern
    private static UnaryFunction<Object> IDENTITY_FUNCTION =
            new UnaryFunction<Object>() {
                public Object apply(Object arg) {
                    return arg;
                }
            };

    // IDENTITY_FUNCTION is stateless and its type parameter is
    // unbounded so it's safe to share one instance across all types.
    @SuppressWarnings("unchecked")
    public static <T> UnaryFunction<T> identityFunction() {
        return (UnaryFunction<T>) IDENTITY_FUNCTION;
    }

    // Sample program to exercise generic singleton
    public static void main(String[] args) {
        String[] strings = { "jute", "hemp", "nylon" };
        UnaryFunction<String> sameString = identityFunction(); for (String s : strings)
            System.out.println(sameString.apply(s));
        Number[] numbers = { 1, 2.0, 3L };
        UnaryFunction<Number> sameNumber = identityFunction();
        for (Number n : numbers)
            System.out.println(sameNumber.apply(n));
    }

    // recursive type bound
    // Returns the maximum value in a list - uses recursive type bound
    public static <T extends Comparable<T>> T max(List<T> list) {
        Iterator<T> i = list.iterator();
        T result = i.next();
        while (i.hasNext()) {
            T t = i.next();
            if (t.compareTo(result) > 0)
                result = t;
        }
        return result;
    }

    /**
     * Generic containers - invariant
     */
    private static class Base {
        @Override
        public String toString() {
            return "Base";
        }
    }

    private static class Child extends Base {
        @Override
        public String toString() {
            return "Child";
        }
    }

    private static abstract class A {
        abstract <T extends Base> Map<String, T> get();
    }

    private static class B extends A {
        @Override
        Map<String, Child> get() {
            return new HashMap<String, Child>() {{put("1", new Child());}};
        }
    }

    public static <T extends Base> void func(Map<String, T> map) {
        System.out.println(map.toString());
    }

    /**
     * PECS stands for producer-extends, consumer-super.
     */
    // Wildcard type for parameter that serves as an E producer
    public <E> void pushAll(Iterable<? extends E> src) {
       for (E e : src)
           push(e);
    }

    // Wildcard type for parameter that serves as an E consumer
    public <E> void popAll(Collection<? super E> dst) {
       while (!isEmpty())
            dst.add(pop());
    }

    private <E> E pop() { return null;}
    private boolean isEmpty() {return true;}
    private <E> void push(E e) {}

    /**
     * Comparables are always consumers, so you should always use
     * Comparable<? super T> in preference to Comparable<T>. The same is true
     * of comparators, so you should always use Comparator<? super T> in
     * preference to Comparator<T>
     */
}

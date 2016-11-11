package com.leetcode;

import java.util.Iterator;

/**
 * 284. Peeking Iterator
 *
 * Given an Iterator class interface with methods: next() and hasNext(), design
 * and implement a PeekingIterator that support the peek() operation -- it
 * essentially peek() at the element that will be returned by the next call to next().
 *
 * Here is an example. Assume that the iterator is initialized to the beginning
 * of the list: [1, 2, 3].
 *
 * Call next() gets you 1, the first element in the list.
 *
 * Now you call peek() and it returns 2, the next element. Calling next() after
 * that still return 2.
 *
 * You call next() the final time and it returns 3, the last element. Calling
 * hasNext() after that should return false.
 */
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
public class PeekingIterator implements Iterator<Integer> {

    Integer buffer;
    Iterator<Integer> iterator;

    public PeekingIterator(Iterator<Integer> iterator) {
        buffer = null;
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (buffer == null)
            buffer = iterator.next();
        return buffer;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer ans;
        if (buffer != null) {
            ans = buffer;
            buffer = null;
        } else {
            ans = iterator.next();
        }
        return ans;
    }

    @Override
    public boolean hasNext() {
        return buffer != null || iterator.hasNext();
    }
}

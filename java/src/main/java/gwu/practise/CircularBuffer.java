package gwu.practise;

import java.util.Arrays;

public class CircularBuffer {

    private int capacity;
    private int[] nums;
    private int start, end, size;

    public CircularBuffer(int capacity) {
        this.capacity = capacity;
        this.nums = new int[capacity];
        start = end = size = 0;
    }

    public void add(int num) {
        if (!isFull()) {
            size++;
            nums[end] = num;
            end = (end + 1) % capacity;
        } else {
            throw new RuntimeException("Cannot add to a full buffer!");
        }
    }

    public int remove() {
        if (!isEmpty()) {
            int value = nums[start];
            start = (start + 1) % capacity;
            size--;
            return value;
        } else {
            throw new RuntimeException("Cannot remove from an empty buffer!");
        }
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "CircularBuffer{" +
                "capacity=" + capacity +
                ", nums=" + Arrays.toString(nums) +
                ", start=" + start +
                ", end=" + end +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {
        CircularBuffer buffer = new CircularBuffer(4);
        buffer.add(1);
        System.out.println(buffer);
        buffer.add(2);
        System.out.println(buffer);
        buffer.add(3);
        System.out.println(buffer);
        buffer.add(4);
        System.out.println(buffer);
        buffer.remove();
        System.out.println(buffer);
        buffer.remove();
        System.out.println(buffer);
    }

}

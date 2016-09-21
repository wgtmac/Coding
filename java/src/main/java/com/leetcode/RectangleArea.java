package com.leetcode;

import java.util.Arrays;

/**
 * 223. Rectangle Area
 *
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * Each rectangle is defined by its bottom left corner and top right corner as
 * shown in the figure. Assume that the total area is never beyond the maximum
 * possible value of int.
 */

public class RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        Rectangle r1 = new Rectangle(A, B, C, D);
        Rectangle r2 = new Rectangle(E, F, G, H);
        return r1.area + r2.area - r1.overlappingArea(r2);
    }

    private static class Rectangle {
        int left, bottom, right, top, area;

        public Rectangle(int l, int b, int r, int t) {
            left = l;
            bottom = b;
            right = r;
            top = t;
            area = (right - left) * (top - bottom);
        }

        private boolean intersects(Rectangle that) {
            if (that.left > this.right || that.right < this.left
                    || that.top < this.bottom || that.bottom > this.top)
                return false;
            return true;
        }

        public int overlappingArea(Rectangle that) {
            if (this.intersects(that)){
                int[] x = {this.left, this.right, that.left, that.right};
                int[] y = {this.top, this.bottom, that.top, that.bottom};
                Arrays.sort(x);
                Arrays.sort(y);
                return (x[2] - x[1]) * (y[2] - y[1]);
            } else
                return 0;
        }
    }

    public static void main(String[] args) {
        RectangleArea rectangleArea = new RectangleArea();
        System.out.println(rectangleArea.computeArea(-5, -2, 5, 1, -3, -3, 3, 3));
    }
}
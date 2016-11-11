package com.leetcode;

/**
 * 302. Smallest Rectangle Enclosing Black Pixels
 *
 * An image is represented by a binary matrix with 0 as a white pixel and 1 as a
 * black pixel. The black pixels are connected, i.e., there is only one black
 * region. Pixels are connected horizontally and vertically. Given the location
 * (x, y) of one of the black pixels, return the area of the smallest (axis-aligned)
 * rectangle that encloses all black pixels.
 *
 * For example, given the following image:
 *
 * [
 *   "0010",
 *   "0110",
 *   "0100"
 * ]
 * and x = 0, y = 2,
 * Return 6.
 */

/**
 * Use binary search, just search in 4 directions for the farthest boundary.
 */
public class SmallestRectangleEnclosingBlackPixels {
    public int minArea(char[][] image, int x, int y) {
        int top, bottom, left, right, start, end, mid;

        // find top bound
        start = 0; end = x;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (checkRow(image, mid))
                end = mid;
            else
                start = mid;
        }
        top = checkRow(image, start) ? start : end;

        // find bottom bound
        start = x; end = image.length - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (checkRow(image, mid))
                start = mid;
            else
                end = mid;
        }
        bottom = checkRow(image, end) ? end : start;

        // find left bound
        start = 0; end = y;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (checkCol(image, mid))
                end = mid;
            else
                start = mid;
        }
        left = checkCol(image, start) ? start : end;

        // find right bound
        start = y; end = image[0].length - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (checkCol(image, mid))
                start = mid;
            else
                end = mid;
        }
        right = checkCol(image, end) ? end : start;

        return (right - left + 1) * (bottom - top + 1);
    }

    private boolean checkRow(char[][] image, int row) {
        for (int i = 0; i < image[row].length; ++i) {
            if (image[row][i] == '1') return true;
        }
        return false;
    }

    private boolean checkCol(char[][] image, int col) {
        for (int i = 0; i < image.length; ++i) {
            if (image[i][col] == '1') return true;
        }
        return false;
    }
}

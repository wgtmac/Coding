package com.leetcode;

import java.util.Stack;

/**
 * 388. Longest Absolute File Path
 *
 * Suppose we abstract our file system by a string in the following manner:
 *
 * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
 *
 * dir
 *    subdir1
 *    subdir2
 *       file.ext
 * The directory dir contains an empty sub-directory subdir1 and a sub-directory
 * subdir2 containing a file file.ext.
 *
 * The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
 *
 * dir
 *    subdir1
 *       file1.ext
 *       subsubdir1
 *    subdir2
 *       subsubdir2
 *          file2.ext
 * The directory dir contains two sub-directories subdir1 and subdir2. subdir1
 * contains a file file1.ext and an empty second-level sub-directory subsubdir1.
 * subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.
 *
 * We are interested in finding the longest (number of characters) absolute path
 * to a file within our file system. For example, in the second example above,
 * the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length
 * is 32 (not including the double quotes).
 *
 * Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.
 *
 * Note:
 * The name of a file contains at least a '.' and an extension.
 * The name of a directory or sub-directory will not contain a '.'.
 * Time complexity required: O(n) where n is the size of the input string.
 *is another
 * Notice that a/aa/aaa/file1.txt is not the longest file path, if there
 * path aaaaaaaaaaaaaaaaaaaaa/sth.png.
 */
public class LongestAbsoluteFilePath {
    public int lengthLongestPath(String input) {

        input = "\n" + input;
        Stack<Integer> stack = new Stack<>();

        // depth: count of '\t'
        int depth = 0, totalLen = 0, fileLen = 0, maxLen = 0;
        boolean hasDot = false;
        for (int i = 0, start = 0; i < input.length(); ) {
            ++i;  // skip '\n'
            depth = 0;
            while (i < input.length() && input.charAt(i) == '\t') {
                depth += 1;
                i += 1;
            }

            // match a file / dir
            hasDot = false;
            start = i;
            while (i < input.length() && input.charAt(i) != '\n') {
                if (input.charAt(i) == '.')
                    hasDot = true;
                ++i;
            }

            fileLen = i - start;

            // throw dir of same depth
            while (depth < stack.size()) {
                totalLen -= stack.pop();
            }

            if (hasDot) {
                // is file, compute path length
                maxLen = Math.max(maxLen, totalLen + fileLen);
            } else {
                stack.push(fileLen + 1);
                totalLen += fileLen + 1;
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        LongestAbsoluteFilePath l = new LongestAbsoluteFilePath();
        System.out.println(l.lengthLongestPath("a"));
        System.out.println(l.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
        System.out.println(l.lengthLongestPath("dir\\n\\tsubdir1\\n\\t\\tfile1.ext\\n\\t\\tsubsubdir1\\n\\tsubdir2\\n\\t\\tsubsubdir2\\n\\t\\t\\tfile2.ext"));
    }
}

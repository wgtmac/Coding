package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 158. Read N Characters Given Read4 II - Call multiple times
 * 
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * The return value is the actual number of characters read. For example, it
 * returns 3 if there is only 3 characters left in the file. By using the read4
 * API, implement the function int read(char *buf, int n) that reads n characters
 * from the file.
 * 
 * Note:
 * The read function may be called multiple times.
 * 
 * Skill:
 * 前面每次读4个
 * 最后比较能读的和剩下未读的谁小，读最小值
 */
public class ReadNCharactersGivenRead4IICallMultipleTimes {
	/* The read4 API is defined in the parent class Reader4.
    int read4(char[] buf); */
	int read4(char[] buf) { return 4; }
	
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */

    private char[] buffer = new char[4];
    private int start = 0, end = 0;
	
    public int read(char[] buf, int n) {
        int totalRead = 0, oneRead = 4, bytesToCopy = 4;
        while (start < end && totalRead < n) {
            buf[totalRead++] = buffer[start++];
        }

        while (oneRead == 4 && totalRead < n) {
            oneRead = read4(buffer);
            bytesToCopy = Math.min(n - totalRead, oneRead);
            System.arraycopy(buffer, 0, buf, totalRead, bytesToCopy);
            totalRead += bytesToCopy;
        }

        if (bytesToCopy != oneRead) {
            start = bytesToCopy;
            end = oneRead;
        }

        return totalRead;
    }
}

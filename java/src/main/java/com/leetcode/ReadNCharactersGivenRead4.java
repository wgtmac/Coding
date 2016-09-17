package com.leetcode;

/**
 * 157. Read N Characters Given Read4
 * 
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * 
 * The return value is the actual number of characters read. For example, it
 * returns 3 if there is only 3 characters left in the file. By using the read4
 * API, implement the function int read(char *buf, int n) that reads n characters
 * from the file.
 * 
 * Note:
 * The read function will only be called once for each test case.
 * 
 * Skill:
 * 前面每次读4个
 * 最后比较能读的和剩下未读的谁小，读最小值
 * 
 */
public class ReadNCharactersGivenRead4 {
	/* The read4 API is defined in the parent class Reader4.
    int read4(char[] buf); */
	int read4(char[] buf) { return 4; }
	
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        if (n <= 0) return 0;
        n = Math.min(n, buf.length);
        
        char[] tmpBuf = new char[4];
        int ret = 0, pos = 0;
        for (int i = 0; i < n / 4; i++, pos += 4) {
        	ret += read4(tmpBuf);
        	for (int j = 0; j < 4; j++) {
        		buf[pos + j] = tmpBuf[j];
        	}
        }
        
        if (n % 4 != 0) {
        	int charRead = Math.min(read4(tmpBuf), n % 4);
        	for (int j = 0; j < charRead; j++) {
        		buf[pos + j] = tmpBuf[j];
        	}
        	ret += charRead;
        }

        return ret;
    }
}

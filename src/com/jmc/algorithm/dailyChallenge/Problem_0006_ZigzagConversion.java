package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode-cn.com/problems/zigzag-conversion/
 */
public class Problem_0006_ZigzagConversion {
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        // PAYPALISHIRING
        // 3列
        // P   A   H   N
        // A P L S I I G
        // Y   I   R

        // 4列
        // P     I    N
        // A   L S  I G
        // Y A   H R
        // P     I
        // 一周期需要r + r - 2行，需要1 + r - 2 = r - 1列
        // 周期数：len(s)/(2r-2)向上取整，列数：len(s)/(r-1)
        int cycle = roundedUp(s.length(), (numRows << 1) - 2);
        int numCols = (numRows - 1) * cycle;
        char[][] str = new char[numRows][numCols];
        int row = 0, col = 0;
        for (int i = 0; i < s.length(); i++) {
            str[row][col] = s.charAt(i);
            if (i % ((numRows << 1) - 2) < numRows - 1) {
                row++;
            } else {
                row--;
                col++;
            }
        }
        
        // 收集答案
        StringBuffer sb = new StringBuffer();
        for (char[] rowChars : str) {
            for (char ch : rowChars) {
                if (ch != 0) {
                    sb.append(ch);
                }
            }
        }

        return sb.toString();
    }

    public static int roundedUp(int a, int b) {
        return (a + b - 1) / b;
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 1;
        System.out.println(convert(s, numRows));
        System.out.println(roundedUp(16, 5));
    }
}

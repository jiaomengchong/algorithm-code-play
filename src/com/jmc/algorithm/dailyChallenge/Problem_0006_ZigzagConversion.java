package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode-cn.com/problems/zigzag-conversion/
 */
public class Problem_0006_ZigzagConversion {
    public static String convert(String s, int numRows) {
        // PAYPALISHIRING
        // P   A   H   N
        // A P L S I I G
        // Y   I   R
        // 一周期需要r + r - 2行，需要1 + r - 2 = r - 1列
        // 周期数：len(s)/(2r-2)向上取整，列数：len(s)/(r-1)
        int cycle = roundedUp(s.length(), (numRows << 1) - 2);
        int numCols = s.length() / (numRows - 1);
        char[][] str = new char[numRows][numCols];
        int row = 0, col = 0;
        for (int i = 0; i < s.length(); i++) {
            str[row][col] = s.charAt(i);
            if (i % ((numRows << 1) - 2) < numCols - 1) {
                row++;
            } else {
                row--;
                col++;
            }
        }

        return "";
    }

    public static int roundedUp(int a, int b) {
        return (a + b - 1) / b;
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        System.out.println(convert(s, numRows));
        System.out.println(roundedUp(16, 5));
    }
}

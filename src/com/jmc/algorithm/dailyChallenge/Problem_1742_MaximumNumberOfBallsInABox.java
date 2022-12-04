package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/maximum-number-of-balls-in-a-box
 */
public class Problem_1742_MaximumNumberOfBallsInABox {
    // 5 - 15
    // 5 6 7 8 9 10 11 12 13 14 15
    // 5 6 7 8 9 1  2  3  4  5  6

    // 19 - 28
    // 19 20 21 22 23 24 25 26 27 28
    // 10 2  3  4  5  6  7  8  9  10
    public static int countBalls(int lowLimit, int highLimit) {
        int ans = 0;
        int[] counts = new int[38];
        for (int i = lowLimit; i <= highLimit; i++) {
            String s = String.valueOf(i);
            int tmp = 0;
            for (char ch : s.toCharArray()) {
                tmp += ch - '0';
            }
            counts[tmp]++;
            ans = Math.max(ans, counts[tmp]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int lowLimit = 19, highLimit = 29;
        System.out.println(countBalls(lowLimit, highLimit));
    }
}

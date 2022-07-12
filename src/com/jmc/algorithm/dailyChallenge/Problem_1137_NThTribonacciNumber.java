package com.jmc.algorithm.dailyChallenge;

import com.sun.org.apache.xpath.internal.operations.String;

/**
 * 测试链接：https://leetcode.cn/problems/n-th-tribonacci-number/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/12 18:52
 **/
public class Problem_1137_NThTribonacciNumber {
    public static int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
    }

    public static int tribonacci1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }

    public static int tribonacci2(int n) {
        int[] map = new int[]{1, 1, 2, 4, 7, 13, 24, 44, 81, 149, 274, 504, 927, 1705, 3136, 5768, 10609, 19513, 35890, 66012, 121415, 223317, 410744, 755476, 1389537, 2555757, 4700770, 8646064, 15902591, 29249425, 53798080, 98950096, 181997601, 334745777, 615693474, 1132436852, 2082876103};
        return map[n];
    }

    public static void main(String[] args) {
        System.out.println(tribonacci(37));
        System.out.println(tribonacci1(37));
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 38; i++) {
            sb.append(tribonacci1(i));
        }
        System.out.println(sb.toString());
    }
}
package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/factorial-trailing-zeroes/
 */
public class Problem_0172_FactorialTrailingZeroes {
    public static int trailingZeroes(int n) {
        if (n == 0) {
            return 0;
        }

        int ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }

    public static long factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return factorial(n - 1) * n;
    }

    public static void main(String[] args) {
        int n = 15;
        System.out.println(factorial(n));
        System.out.println(trailingZeroes(n));
    }
}

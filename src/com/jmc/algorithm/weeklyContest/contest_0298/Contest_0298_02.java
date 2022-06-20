package com.jmc.algorithm.weeklyContest.contest_0298;

/**
 * 测试链接：https://leetcode.cn/contest/weekly-contest-298/problems/sum-of-numbers-with-units-digit-k/
 */
public class Contest_0298_02 {
    public static int minimumNumbers(int num, int k) {
        if (num == 0) {
            return 0;
        }

        if (num == k) {
            return 1;
        }

        if (k % 2 == 0 && num % 2 != 0) {
            return -1;
        }

        int ans = 0;
        k = k == 0 ? 10 : k;
        while (num > 0) {
            num -= k;
            ans++;
            if (num % 10 == k) {
                ans++;
                return ans;
            }
        }

        return num == 0 ? ans : -1;
    }

    public static int minimumNumbers1(int num, int k) {
        if (num == 0) {
            return 0;
        }

        // 输入：num = 58, k = 9
        // 输出：2
        // 58 拆成 40 + 2 * 9 -> 10的倍数 + n*k = num
        // (58 - n * k) % 10 == 0 同模
        for (int n = 1; n <= num && num - n * k >= 0; n++) {
            if ((num - n * k) % 10 == 0) {
                return n;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int num = 58;
        int k = 9;
        System.out.println(minimumNumbers(num, k));
        System.out.println(minimumNumbers1(num, k));
    }
}

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

    public static void main(String[] args) {
        int num = 14;
        int k = 4;
        System.out.println(minimumNumbers(num, k));
    }
}

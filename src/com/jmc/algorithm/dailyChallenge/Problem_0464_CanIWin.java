package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/can-i-win/
 */
public class Problem_0464_CanIWin {
    public static boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) {
            return false;
        }
        int[] dp = new int[1 << 21];
        return dfs(maxChoosableInteger, desiredTotal, 0, 0, dp);
    }

    private static boolean dfs(int maxChoosableInteger, int desiredTotal, int sum, int state, int[] dp) {
        if (dp[state] == 1) {
            return true;
        }
        if (dp[state] == 2) {
            return false;
        }
        for (int i = 1; i <= maxChoosableInteger; i++) {
            if (((1 << i) & state) != 0) {
                continue;
            }
            if (sum + i >= desiredTotal) {
                dp[state] = 1;
                return true;
            }
            if (!dfs(maxChoosableInteger, desiredTotal, sum + i, (1 << i) | state, dp)) {
                dp[state] = 1;
                return true;
            }
        }
        dp[state] = 2;
        return false;
    }

    public static void main(String[] args) {
        // 1 2 3 4 5 6 7 8
        // n*(n-1)/2
        int maxChoosableInteger = 4;
        int desiredTotal = 6;
        System.out.println(canIWin(maxChoosableInteger, desiredTotal));
    }
}

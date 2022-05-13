package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/one-away-lcci/
 *
 * @Author jmc
 * @Description
 * @Date 2022/5/13 20:27
 **/
public class Problem_01Dot05_OneAwayLcci {

    public static boolean oneEditAway(String first, String second) {
        if ((first == null) && (second == null)) {
            return true;
        }

        if (first.length() == 0 && second.length() == 0) {
            return true;
        }

        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }

        int N = first.length();
        int M = second.length();
        int add = 1, del = 1, rep = 1;
        int[][] dp = new int[N + 1][M + 1];
        // pale
        // ple
        for (int i = 0; i <= M; i++) {
            dp[0][i] = i * add;
        }
        for (int i = 0; i <= N; i++) {
            dp[i][0] = i * del;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                // 1) dp[i][j]，变成dp[i][j-1] + a
                // 2) dp[i][j]，变成dp[i-1][j] + d
                // 3) dp[i][j]，i==j，dp[i-1][j-1]
                // 4) dp[i][j]，i!=j，dp[i-1][j-1] + r
                dp[i][j] = dp[i - 1][j - 1] + (first.charAt(i - 1) == second.charAt(j - 1) ? 0 : rep);
                dp[i][j] = Math.min(dp[i][j], Math.min(dp[i][j - 1] + add, dp[i - 1][j] + del));
            }
        }

        return dp[N][M] <= 1;
    }
}

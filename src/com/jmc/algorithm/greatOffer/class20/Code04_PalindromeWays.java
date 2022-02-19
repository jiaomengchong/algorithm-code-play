package com.jmc.algorithm.greatOffer.class20;

/**
 * 给定一个字符串str，当然可以生成很多子序列
 * 返回有多少个子序列是回文子序列，空序列不算回文
 * 比如，str = “aba”
 * 回文子序列：{a}、{a}、 {a,a}、 {b}、{a,b,a}
 * 返回5
 *
 * @author jmc
 * @version 1.0
 * @date 2021/7/6 16:01
 */
public class Code04_PalindromeWays {
    public static int waysDp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int N = s.length();
        char[] str = s.toCharArray();
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < N - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 3 : 2;
        }
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                if (str[i] == str[j]) {
                    dp[i][j] += dp[i + 1][j - 1] + 1;
                }
            }
        }

        return dp[0][N - 1];
    }

    public static void main(String[] args) {
        String s = "aba";
        System.out.println(waysDp(s));
    }
}

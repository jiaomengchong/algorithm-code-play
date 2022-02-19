package com.jmc.algorithm.jjb.class47;

/**
 * 测试链接：https://leetcode.com/problems/strange-printer/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/4/15 19:40
 */
public class Code01_StrangePrinter {

    public static int strangePrinter(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return process(s.toCharArray(), 0, s.length() - 1);
    }

    private static int process(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }

        int K = R - L + 1;
        for (int i = L; i < R; i++) {
            K = Math.min(K, process(str, L, i) + process(str, i + 1, R) - (str[L] == str[i + 1] ? 1 : 0));
        }
        return K;
    }

    public static int strangePrinterDp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int N = s.length();
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }

        return processDp(s.toCharArray(), 0, N - 1, dp);
    }

    private static int processDp(char[] str, int L, int R, int[][] dp) {
        if (dp[L][R] != -1) {
            return dp[L][R];
        }

        if (L == R) {
            return 1;
        }

        int ans = R - L + 1;
        for (int i = L; i < R; i++) {
            ans = Math.min(ans, processDp(str, L, i, dp) + processDp(str, i + 1, R, dp) - (str[L] == str[i + 1] ? 1 : 0));
        }

        dp[L][R] = ans;
        return ans;
    }

    public static int strangePrinterBest(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int N = s.length();
        char[] str = s.toCharArray();
        int[][] dp = new int[N + 1][N + 1];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = (str[i] == str[i + 1] ? 1 : 2);
        }

        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                int ans = R - L + 1;
                for (int i = L; i < R; i++) {
                    ans = Math.min(ans, dp[L][i] +dp[i + 1][R] - (str[L] == str[i + 1] ? 1 : 0));
                }
                dp[L][R] = ans;
            }
        }

        return dp[0][N - 1];
    }

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        long start = System.currentTimeMillis();
//        System.out.println(strangePrinter(s));
        long end1 = System.currentTimeMillis();
        System.out.println(strangePrinterDp(s));
        System.out.println(strangePrinterBest(s));
        long end2 = System.currentTimeMillis();
        System.out.println("方法1执行时间：" + (end1 - start));
        System.out.println("方法2执行时间：" + (end2 - end1));
    }
}

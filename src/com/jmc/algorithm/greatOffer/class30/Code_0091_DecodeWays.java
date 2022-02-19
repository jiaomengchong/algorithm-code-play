package com.jmc.algorithm.greatOffer.class30;

/**
 * @author jmc
 * @version 1.0
 * @date 2021/8/18 20:17
 */
public class Code_0091_DecodeWays {
    public static int numDecodings1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return process1(s.toCharArray(), 0);
    }

    private static int process1(char[] str, int index) {
        if (index == str.length) {
            return 1;
        }

        if (str[index] == '0') {
            return 0;
        }

        // 单转
        int ways = process1(str, index + 1);

        // 合并转
        if (index + 1 < str.length && ((str[index] - '0') * 10 + str[index + 1] - '0' < 27)) {
            ways += process1(str, index + 2);
        }

        return ways;
    }

    public static int numDecodings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] dp = new int[s.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            dp[i] = -1;
        }

        return process2(s.toCharArray(), 0, dp);
    }

    private static int process2(char[] str, int index, int[] dp) {
        if (dp[index] != -1) {
            return dp[index];
        }

        if (index == str.length) {
            return 1;
        }

        if (str[index] == '0') {
            return 0;
        }

        // 单转
        int ways = process2(str, index + 1, dp);

        // 合并转
        if (index + 1 < str.length && ((str[index] - '0') * 10 + str[index + 1] - '0' < 27)) {
            ways += process2(str, index + 2, dp);
        }

        dp[index] = ways;
        return ways;
    }

    public static int numDecodings3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;

        for (int index = N - 1; index >= 0; index--) {
            if (str[index] != '0') {
                // 单转
                int ways = dp[index + 1];

                // 合并转
                if (index + 1 < str.length && ((str[index] - '0') * 10 + str[index + 1] - '0' < 27)) {
                    ways += dp[index + 2];
                }
                dp[index] = ways;
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        String s = "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
        System.out.println(numDecodings2(s));
        System.out.println(numDecodings1(s));
    }
}

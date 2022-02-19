package com.jmc.algorithm.jjb.class19;

/**
 * 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 * 比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/6 16:58
 */
public class Code04_LongestCommonSubsequence {
    public static int longestCommonSubsequence1(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return 0;
        }

        return process1(str1.toCharArray(), str2.toCharArray(), str1.length() - 1, str2.length() - 1);
    }

    private static int process1(char[] str1, char[] str2, int index1, int index2) {
        if (index1 == 0 && index2 == 0) {
            return str1[index1] == str2[index2] ? 1 : 0;
        } else {
            if (index1 == 0) {
                return str1[index1] == str2[index2] ? 1 : process1(str1, str2, index1, index2 - 1);
            } else if (index2 == 0) {
                return str1[index1] == str2[index2] ? 1 : process1(str1, str2, index1 - 1, index2);
            } else {
                int p1 = process1(str1, str2, index1 - 1, index2);
                int p2 = process1(str1, str2, index1, index2 - 1);
                int p3 = str1[index1] == str2[index2] ? 1 + process1(str1, str2, index1 - 1, index2 - 1) : 0;
                return Math.max(p1, Math.max(p2, p3));
            }
        }
    }

    public static int longestCommonSubsequence2(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return 0;
        }

        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int M = str1.length();
        int N = str2.length();
        int[][] dp = new int[M][N];
        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;

        for (int index2 = 1; index2 < N; index2++) {
            dp[0][index2] = (chars1[0] == chars2[index2]) ? 1 : dp[0][index2 - 1];
        }

        for (int index1 = 1; index1 < M; index1++) {
            dp[index1][0] = (chars1[index1] == chars2[0]) ? 1 : dp[index1 - 1][0];
        }

        for (int index1 = 1; index1 < M; index1++) {
            for (int index2 = 1; index2 < N; index2++) {
                int p1 = dp[index1 - 1][index2];
                int p2 = dp[index1][index2 - 1];
                int p3 = (chars1[index1] == chars2[index2]) ? (1 + dp[index1 - 1][index2 - 1]) : 0;
                dp[index1][index2] = Math.max(p1, Math.max(p2, p3));
            }
        }

        return dp[M - 1][N - 1];
    }

    public static void main(String[] args) {
        String str1 = "a12b3c43def2ghi1kpm";
        String str2 = "mpk1ihg2fed34c3b21a";
        System.out.println(longestCommonSubsequence1(str1, str2));
        System.out.println(longestCommonSubsequence2(str1, str2));
    }
}

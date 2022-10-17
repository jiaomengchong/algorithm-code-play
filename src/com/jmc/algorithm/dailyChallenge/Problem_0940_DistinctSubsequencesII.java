package com.jmc.algorithm.dailyChallenge;

import java.util.HashSet;
import java.util.Set;

/**
 * 测试链接：https://leetcode.cn/problems/distinct-subsequences-ii/
 */
public class Problem_0940_DistinctSubsequencesII {
    public static int distinctSubseqII(String s) {
        char[] str = s.toCharArray();
        Set<String> sets = new HashSet<>();
        process(str, 0, sets, "");

        return sets.size() - 1;
    }

    private static void process(char[] str, int index, Set<String> sets, String sb) {
        if (index == str.length) {
            sets.add(sb);
            return;
        }

        process(str, index + 1, sets, sb);
        process(str, index + 1, sets, sb + str[index]);
    }

    public static int distinctSubseqII2(String s) {
        char[] str = s.toCharArray();
        Set<String> sets = new HashSet<>();
        long[][] dp = new long[2001][26];
        for (int i = 0; i <= 2000; i++) {
            for (int j = 0; j < 26; j++) {
                dp[i][j] = -1;
            }
        }

        long ans = 0l;
        for (int i = 0; i < 26; i++) {
            ans +=processDp(str, 0, i, sets, "", dp);
        }

        return (int) ans;
    }

    private static long processDp(char[] str, int index, int j, Set<String> sets, String sb, long[][] dp) {
        if (index == str.length) {
            sets.add(sb);
            dp[index][j] = sets.size() - 1;
            return dp[index][j];
        }

        if (dp[index][j] != -1) {
            return dp[index][j];
        }

        long p1 = processDp(str, index + 1, j, sets, sb, dp);
        long p2 = processDp(str, index + 1, j, sets, sb + str[index], dp);
        dp[index][j] = p1 + p2;

        return p1 + p2;
    }

    public static void main(String[] args) {
        String s = "aba";
        System.out.println(distinctSubseqII(s));
        System.out.println(distinctSubseqII2(s));
    }
}

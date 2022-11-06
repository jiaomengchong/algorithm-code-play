package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/maximum-repeating-substring/description/
 */
public class Problem_1668_MaximumRepeatingSubstring {
    public static int maxRepeating(String sequence, String word) {
        int M = sequence.length(), N = word.length();
        int[] dp = new int[M];
        for (int i = N - 1; i < M; i++) {
            boolean valid = true;
            for (int j = 0; j < N; j++) {
                if (sequence.charAt(i - N + j + 1) != word.charAt(j)) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                dp[i] = (i == N - 1 ? 0 : dp[i - N]) + 1;
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public static void main(String[] args) {
        String sequence = "ababc", word = "ab";
        System.out.println(maxRepeating(sequence, word));
    }
}

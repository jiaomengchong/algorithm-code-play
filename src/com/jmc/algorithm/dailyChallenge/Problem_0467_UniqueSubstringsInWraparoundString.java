package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/unique-substrings-in-wraparound-string/
 *
 * @Author jmc
 * @Description
 * @Date 2022/5/25 18:55
 **/
public class Problem_0467_UniqueSubstringsInWraparoundString {
    public static int findSubstringInWraproundString(String p) {
        // 输入: p = "zab"
        // 输出: 6
        int[] dp = new int[26];
        int k = 0;
        for (int i = 0; i < p.length(); i++) {
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) + 26) % 26 == 1) {
                k++;
            } else {
                k = 1;
            }

            int index = p.charAt(i) - 'a';
            dp[index] = Math.max(dp[index], k);
        }
        return Arrays.stream(dp).sum();
    }

    public static void main(String[] args) {
        String p = "zaba";
        System.out.println(findSubstringInWraproundString(p));
    }
}

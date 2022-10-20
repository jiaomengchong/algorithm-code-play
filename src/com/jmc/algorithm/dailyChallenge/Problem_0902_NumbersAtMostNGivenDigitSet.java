package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/numbers-at-most-n-given-digit-set/
 */
public class Problem_0902_NumbersAtMostNGivenDigitSet {
    public static int atMostNGivenDigitSet(String[] digits, int n) {
        char[] str = String.valueOf(n).toCharArray();
        int[] dp = new int[str.length + 1];
        Arrays.fill(dp, -1);
        return process(digits, str, 0, true, false, dp);
    }

    private static int process(String[] digits, char[] str, int index, boolean isLimit, boolean isNum, int[] dp) {
        if (index == str.length) {
            return isNum ? 1 : 0;
        }

        if (!isLimit && isNum && dp[index] > 0) {
            return dp[index];
        }

        int ans = 0;
        if (!isNum) {
            ans = process(digits, str, index + 1, false, false, dp);
        }

        char up = isLimit ? str[index] : '9';
        for (String s : digits) {
            if (s.charAt(0) > up) {
                break;
            }
            ans += process(digits, str, index + 1, isLimit && s.charAt(0) == up, true, dp);
        }

        if (!isLimit && isNum) {
            dp[index] = ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] digits = new String[]{"1","3","5","7"};
        int n = 100;
        System.out.println(atMostNGivenDigitSet(digits, n));
    }
}

package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/flip-string-to-monotone-increasing/
 */
public class Problem_0926_FlipStringToMonotoneIncreasing {
    public static int minFlipsMonoIncr(String s) {
        // 输入：s = "00110"
        // 输出：1
        // 解释：翻转最后一位得到 00111.
        return process(s.toCharArray(), 0);
    }

    private static int process(char[] str, int index) {
        if (index == str.length) {
            return 0;
        }

        int p1 = Integer.MAX_VALUE;
        int p2 = Integer.MAX_VALUE;
        if (str[index] == '1') {

        } else {
            p1 = 0;
        }
        return 0;
    }
}

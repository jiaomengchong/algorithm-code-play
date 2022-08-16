package com.jmc.algorithm.biweeklyContest.contest_0081;

import java.util.Map;

/**
 * 测试链接：https://leetcode.cn/problems/maximum-score-after-splitting-a-string/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/14 18:46
 **/
public class Problem_1422_MaximumScoreAfterSplittingAString {
    public static int maxScore(String s) {
        int N = s.length(), ans = 0;
        int sum1 = 0;
        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == '1') {
                sum1++;
            }
        }

        int left0 = 0, left1 = 0;
        for (int i = 0; i < N - 1; i++) {
            if (s.charAt(i) == '0') {
                left0++;
            } else {
                left1++;
            }
            ans = Math.max(ans, left0 + sum1 - left1);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "1111";
        System.out.println(maxScore(s));
    }
}

package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/consecutive-characters
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/15 17:33
 **/
public class Problem_1446_ConsecutiveCharacters {
    public static int maxPower(String s) {
        int N = s.length();
        int count = 1;
        int ans = 1;
        for (int i = 1; i < N; i++) {
            if (s.charAt(i - 1) == s.charAt(i)) {
                count++;
            } else {
                count = 1;
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }
}

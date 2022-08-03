package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 测试链接：https://leetcode.cn/problems/calculate-digit-sum-of-a-string/
 */
public class Problem_2243_CalculateDigitSumOfAString {
    public static String digitSum(String s, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            queue.offer(ch - '0');
        }

        while (k < queue.size()) {
            int cycle = (queue.size() + k - 1) / k;
            for (int i = 0; i < cycle; i++) {
                int ans = 0;
                for (int j = 0; j < k; j++) {
                    Integer poll = queue.poll();
                    ans += poll;
                }
                while (ans != 0) {
                    queue.offer(ans % 10);
                    ans /= 10;
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String s = "11111222223";
        int k = 3;
        System.out.println(digitSum(s, k));
    }
}

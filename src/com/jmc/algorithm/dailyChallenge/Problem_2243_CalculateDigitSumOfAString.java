package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
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

        if (s.length() % k != 0) {

        }

        while (k < queue.size()) {
            int cycle = (queue.size() + k - 1) / k;
            int total = queue.size();
            for (int i = 0; i < cycle; i++) {
                int ans = 0;
                int limit = i == cycle - 1 ? total % k == 0 ? k : total % k : k;
                for (int j = 0; j < limit; j++) {
                    Integer poll = queue.poll();
                    ans += poll;
                }

                if (ans == 0) {
                    queue.offer(ans);
                } else {
                    List<Integer> values = getValues(ans);
                    for (int j = values.size() - 1; j >= 0; j--) {
                        queue.offer(values.get(j));
                    }
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        while (!queue.isEmpty()) {
            sb.append(queue.poll());
        }
        return sb.toString();
    }

    private static List<Integer> getValues(int value) {
        List<Integer> ret = new ArrayList<>();
        while (value != 0) {
            ret.add(value % 10);
            value /= 10;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(getValues(103));
        String s = "01234567890";
        int k = 10;
        System.out.println(digitSum(s, k));
        // "01234567890"
        // 10
        // 15913170
        // 61047
        // 747
        // 117
        // 27
    }
}

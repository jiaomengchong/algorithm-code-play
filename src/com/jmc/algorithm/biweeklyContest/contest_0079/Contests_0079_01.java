package com.jmc.algorithm.biweeklyContest.contest_0079;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试链接：https://leetcode.cn/contest/biweekly-contest-79/problems/check-if-number-has-equal-digit-count-and-digit-value/
 */
public class Contests_0079_01 {
    public static boolean digitCount(String num) {
        // 输入：num = "1210"
        // 输出：true
        int N = num.length();
        char[] chars = num.toCharArray();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int aChar = chars[i] - '0';
            map.put(aChar, map.getOrDefault(aChar, 0) + 1);
        }

        for (int i = 0; i < N; i++) {
            if (map.getOrDefault(i, 0) != chars[i] - '0') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String num = "030";
        System.out.println(digitCount(num));
    }
}

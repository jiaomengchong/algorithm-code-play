package com.jmc.algorithm.dailyChallenge;

import java.util.HashSet;
import java.util.Set;

/**
 * 测试链接：https://leetcode.cn/problems/rotate-string/
 */
public class Problem_0796_RotateString {
    public static boolean rotateString(String s, String goal) {
        int N = s.length();
        Set<String> sets = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String sub1 = s.substring(0, i);
            String sub2 = s.substring(i);
            String s1 = sub2 + sub1;
            sets.add(s1);
            if (sets.contains(goal)) {
                return true;
            }
        }
        return false;
    }

    public static boolean rotateString1(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        return (s + s).contains(goal);
    }

    public static void main(String[] args) {
        // abcdeabcde
        // cdeab
        String s = "abcde";
        String gogal = "abced";
        System.out.println(rotateString(s, gogal));
        System.out.println(rotateString1(s, gogal));
    }
}

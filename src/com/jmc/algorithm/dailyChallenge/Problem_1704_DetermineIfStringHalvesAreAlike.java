package com.jmc.algorithm.dailyChallenge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 测试链接：https://leetcode.cn/problems/determine-if-string-halves-are-alike/
 */
public class Problem_1704_DetermineIfStringHalvesAreAlike {
    public static boolean halvesAreAlike(String s) {
        Set<Character> sets = new HashSet<>();
        sets.add('a');
        sets.add('e');
        sets.add('i');
        sets.add('o');
        sets.add('u');
        sets.add('A');
        sets.add('E');
        sets.add('I');
        sets.add('O');
        sets.add('U');
        int N = s.length();
        String left = s.substring(0, N / 2);
        String right = s.substring(N / 2);
        int leftCnt = 0, rightCnt = 0;
        for (Character ch : left.toCharArray()) {
            if (sets.contains(ch)) {
                leftCnt++;
            }
        }
        for (Character ch : right.toCharArray()) {
            if (sets.contains(ch)) {
                rightCnt++;
            }
        }

        return leftCnt == rightCnt;
    }

    public static void main(String[] args) {
        String s = "AbCdEfGh";
        System.out.println(halvesAreAlike(s));
    }
}

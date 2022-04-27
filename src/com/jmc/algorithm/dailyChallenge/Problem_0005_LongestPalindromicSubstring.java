package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * @Author jmc
 * @Description
 * @Date 2022/4/25 18:01
 **/
public class Problem_0005_LongestPalindromicSubstring {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] manacher = manacherString(s);
        // 回文中心点
        int C = -1;
        // 回文右边界
        int R = -1;
        int[] pArr = new int[manacher.length];

        //   [                         ]
        // y  a  b  a  t  s  t  a  b  a  x
        // 0  1  2  3  4  5  6  7  8  9  10
        //      i'        C        i     R
        int max = 0;
        int maxC = 0;
        for (int i = 0; i < manacher.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < manacher.length && i - pArr[i] > -1) {
                if (manacher[i + pArr[i]] == manacher[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                C = i;
                R = i + pArr[i];
            }
            if (pArr[i] > max) {
                max = pArr[i];
                maxC = C;
            }
        }
        System.out.println(maxC);
        System.out.println(max - 1);
        int start = (maxC - max + 1) >> 1;
        return s.substring(start, start + (max - 1));
    }

    // 121 -> #1#2#1#
    private static char[] manacherString(String s) {
        char[] str = s.toCharArray();
        int n = (s.length() << 1) + 1;
        char[] ms = new char[n];
        for (int i = 0; i < n; i++) {
            ms[i] = (i & 1) == 0 ? '#' : str[i >> 1];
        }
        return ms;
    }

    public static void main(String[] args) {
        String s = "babad";
        char[] chars = manacherString(s);
        System.out.println(Arrays.toString(chars));
        System.out.println(longestPalindrome(s));

        s = "cbbd";
        char[] chars1 = manacherString(s);
        System.out.println(Arrays.toString(chars1));
        System.out.println(longestPalindrome(s));
    }
}

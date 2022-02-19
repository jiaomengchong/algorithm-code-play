package com.jmc.algorithm.greatOffer.class13;

/**
 * 扰乱字符串
 * 测试链接：https://leetcode-cn.com/problems/scramble-string/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/10 15:33
 */
public class Code03_ScrambleString {
    public static boolean isScramble0(String s1, String s2) {
        if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
            return false;
        }
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1.length() == 1 && s2.length() == 1) {
            return s1.equals(s2);
        }
        if (!check(s1, s2)) {
            return false;
        }

        return process0(s1.toCharArray(), s2.toCharArray(), 0, s1.length() - 1, 0, s2.length() - 1);
    }

    private static boolean process0(char[] str1, char[] str2, int L1, int R1, int L2, int R2) {
        if (L1 == R1) {
            return str1[L1] == str2[L2];
        }
        boolean p1, p2;
        for (int leftEnd = L1; leftEnd < R1; leftEnd++) {
            p1 = process0(str1, str2, L1, leftEnd, L2, L2 + (leftEnd - L1)) && process0(str1, str2, leftEnd + 1, R1, L2 + (leftEnd - L1) + 1, R2);
            p2 = process0(str1, str2, L1, leftEnd, R2 - (leftEnd - L1), R2) && process0(str1, str2, leftEnd + 1, R1, L2, R2 - (leftEnd - L1) - 1);
            if (p1 || p2) {
                return true;
            }
        }
        return false;
    }

    public static boolean isScramble1(String s1, String s2) {
        if ((s1 == null && s2 != null)) {
            return false;
        }
        if (s1 != null && s2 == null) {
            return false;
        }
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1.equals(s2)) {
            return true;
        }
        if (!check(s1, s2)) {
            return false;
        }
        return process1(s1.toCharArray(), s2.toCharArray(), 0, 0, s1.length());
    }

    private static boolean process1(char[] str1, char[] str2, int L1, int L2, int size) {
        if (size == 1) {
            return str1[L1] == str2[L2];
        }

        boolean p1, p2;
        for (int leftEnd = L1; leftEnd < size; leftEnd++) {
            // |...|......|   str1
            // 6   9      17
            // |...|......|   str2
            // 17  20     38
            p1 = process1(str1, str2, leftEnd, L2, leftEnd - L1 + 1) && process1(str1, str2, leftEnd, L2, size);

        }
        return false;
    }

    private static boolean check(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] count = new int[256];

        for (int i = 0; i < str1.length; i++) {
            count[str1[i]]++;
        }
        for (int i = 0; i < str2.length; i++) {
            if (--count[str2[i]] < 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String s1 = "ccabcbabcbabbbbcbb";
        String s2 = "bbbbabccccbbbabcba";
        System.out.println(isScramble0(s1, s2));
        System.out.println(System.currentTimeMillis());
    }
}

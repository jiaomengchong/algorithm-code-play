package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/check-permutation-lcci/
 */
public class Problem_01Dot02_CheckPermutationLcci {
    public static boolean CheckPermutation(String s1, String s2) {
        int[] count = new int[256];
        int len1 = s1.length(), len2 = s2.length();
        if (len1 != len2) {
            return false;
        }

        int total = 0;
        for (int i = 0; i < len1; i++) {
            if (++count[s1.charAt(i)] == 1) {
                total++;
            }
            if (--count[s2.charAt(i)] == 0) {
                total--;
            }
        }
        return total == 0;
    }

    public static void main(String[] args) {
        String s1 = "你爱我", s2 = "我爱你";
        System.out.println(CheckPermutation(s1, s2));
    }
}

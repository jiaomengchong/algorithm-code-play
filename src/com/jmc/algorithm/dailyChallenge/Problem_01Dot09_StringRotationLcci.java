package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/string-rotation-lcci/
 */
public class Problem_01Dot09_StringRotationLcci {
    public static boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        return (s1 + s1).contains(s2);
    }
}

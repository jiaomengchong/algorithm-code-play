package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/count-number-of-texts/
 */
public class Problem_2266_CountNumberOfTexts {
    public static int countTexts(String pressedKeys) {
        int[] phones = new int[]{3, 3, 3, 3, 3, 4, 3, 4};
        return process(pressedKeys.toCharArray(), phones, 0);
    }

    private static int process(char[] keys, int[] phones, int index) {
        if (index == keys.length) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        // 输入：pressedKeys = "22233"
        // 输出：8
    }
}

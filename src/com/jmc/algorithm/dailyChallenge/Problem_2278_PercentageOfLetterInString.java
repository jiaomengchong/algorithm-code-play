package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/percentage-of-letter-in-string/
 */
public class Problem_2278_PercentageOfLetterInString {
    public static int percentageLetter(String s, char letter) {
        // 输入：s = "foobar", letter = "o"
        // 输出：33
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += s.charAt(i) == letter ? 1 : 0;
        }

        return 100 * count / s.length();
    }

    public static void main(String[] args) {
        String s = "jjjj";
        char letter = 'k';
        System.out.println(percentageLetter(s, letter));
    }
}

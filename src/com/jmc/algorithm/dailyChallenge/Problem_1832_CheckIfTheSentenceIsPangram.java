package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/check-if-the-sentence-is-pangram/
 */
public class Problem_1832_CheckIfTheSentenceIsPangram {
    public static boolean checkIfPangram(String sentence) {
        int N = sentence.length();
        if (N < 26) {
            return false;
        }

        int[] counts = new int[26];
        for (int i = 0; i < N; i++) {
            counts[sentence.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (counts[i] == 0) {
                return false;
            }
        }
        return true;
    }
}

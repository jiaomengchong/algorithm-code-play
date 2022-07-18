package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/counting-words-with-a-given-prefix/
 */
public class Problem_2185_CountingWordsWithAGivenPrefix {
    public static int prefixCount(String[] words, String pref) {
        int ans = 0;
        for (String word : words) {
            if (word.startsWith(pref)) {
                ans++;
            }
        }
        return ans;
    }
}

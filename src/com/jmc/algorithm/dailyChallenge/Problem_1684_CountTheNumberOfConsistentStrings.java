package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/count-the-number-of-consistent-strings/description/
 */
public class Problem_1684_CountTheNumberOfConsistentStrings {
    public static int countConsistentStrings(String allowed, String[] words) {
        int ans = 0;
        int[] allow = new int[26];
        for (char ch : allowed.toCharArray()) {
            allow[ch - 'a'] = 1;
        }
        for (String word : words) {
            boolean check = true;
            for (char ch : word.toCharArray()) {
                if (allow[ch - 'a'] != 1) {
                    check = false;
                    break;
                }
            }
            if (check) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String allowed = "acd";
        String[] words = new String[]{"cc", "acd", "b", "ba", "bac", "bad", "ac", "d"};
        System.out.println(countConsistentStrings(allowed, words));
    }
}

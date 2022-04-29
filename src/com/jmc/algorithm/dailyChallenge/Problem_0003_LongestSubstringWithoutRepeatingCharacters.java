package com.jmc.algorithm.dailyChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class Problem_0003_LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int ans = 0;
        // abcabcbb
        int left = 0;
        int right = 0;
        char[] str = s.toCharArray();
        Map<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            counts.putIfAbsent(str[i], 0);
            counts.put(str[i], counts.get(str[i]) + 1);
            while (left < right && counts.get(str[i]) > 1) {
                if (str[left] == str[i]) {
                    counts.put(str[i], counts.get(str[i]) - 1);
                } else {
                    counts.put(str[left], counts.get(str[left]) - 1);
                }
                left++;
            }
            right = i + 1;
            ans = Math.max(ans, right - left);
        }
        System.out.println(String.format("left:%s,right:%s", left, right));
        return ans;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
